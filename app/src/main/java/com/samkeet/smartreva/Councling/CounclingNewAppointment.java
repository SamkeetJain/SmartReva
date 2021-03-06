package com.samkeet.smartreva.Councling;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.samkeet.smartreva.Constants;
import com.samkeet.smartreva.R;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;
import com.wdullaer.materialdatetimepicker.time.RadialPickerLayout;
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog;

import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Calendar;

import dmax.dialog.SpotsDialog;

public class CounclingNewAppointment extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener, DatePickerDialog.OnDateSetListener {

    TextView title, date, time, summary;
    String mtitle, mdate, mTime, mSummary;

    String result;
    String usn;

    public SpotsDialog pd;
    public Context progressDialogContext;

    public String response;

    public boolean authenticationError;
    public String errorMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        progressDialogContext = this;
        setContentView(R.layout.activity_councling_new_appointment);
        date = (TextView) findViewById(R.id.date);
        time = (TextView) findViewById(R.id.timetext);
        title = (TextView) findViewById(R.id.title);
        summary = (TextView) findViewById(R.id.desc);
    }

    public void GoBack(View v) {
        finish();
    }

    public void DateButton(View v) {
        Calendar now = Calendar.getInstance();
        DatePickerDialog dpd = DatePickerDialog.newInstance(
                CounclingNewAppointment.this,
                now.get(Calendar.YEAR),
                now.get(Calendar.MONTH),
                now.get(Calendar.DAY_OF_MONTH)
        );
        Calendar minDate = Calendar.getInstance();
        minDate.set(now.get(Calendar.YEAR), now.get(Calendar.MONTH), now.get(Calendar.DAY_OF_MONTH));
        dpd.setMinDate(minDate);
        dpd.show(getFragmentManager(), "Datepickerdialog");
    }

    @Override
    public void onTimeSet(RadialPickerLayout view, int hourOfDay, int minute, int second) {

    }

    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        String fullDate;
        String day, month;
        if (dayOfMonth < 10)
            day = "0" + dayOfMonth;
        else
            day = "" + dayOfMonth;

        ++monthOfYear;

        if (monthOfYear < 10)
            month = "0" + monthOfYear;
        else
            month = "" + monthOfYear;

        fullDate = "" + day + "-" + (month) + "-" + year;


        date.setText(fullDate);

        Intent intent = new Intent(getApplicationContext(), CounclingTimePickActivity.class);
        intent.putExtra("DATE", fullDate);
        startActivityForResult(intent, 101);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 101) {

            if (resultCode == RESULT_OK) {
                result = data.getStringExtra("result");
                time.setText("" + result.charAt(11) + result.charAt(12) + ":00");
            }
            if (requestCode == RESULT_CANCELED) {
                Toast.makeText(getApplicationContext(), "Please Select a Slot", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void Send(View v) {
        mtitle = title.getText().toString();
        mSummary = summary.getText().toString();
        mTime = result;
//        mTime=""+result.charAt(11)+result.charAt(12)+":00";
        usn = Constants.SharedPreferenceData.getUserId();

        validation();
    }

    public void validation() {
        if (!validateTitle()) {
            return;
        }
        if (!validateSummary()) {
            return;
        }
        if (!validateTime()) {
            return;
        }
        if (Constants.Methods.networkState(getApplicationContext(), (ConnectivityManager) getSystemService(getApplicationContext().CONNECTIVITY_SERVICE))) {
            MakeAppointment makeAppointment = new MakeAppointment();
            makeAppointment.execute();
        }
    }

    private boolean validateTitle() {

        if (Constants.Methods.checkForSpecial(mtitle)) {
            Toast.makeText(getApplicationContext(), "Title should not include special charecters", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (mtitle.length() <= 40) {
            Toast.makeText(getApplicationContext(), "Title should be less than 40 charecters", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (mTime.isEmpty()){
            Toast.makeText(getApplicationContext(),"Invalid Time",Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }

    private boolean validateSummary() {
        if (Constants.Methods.checkForSpecial(mSummary)) {
            Toast.makeText(getApplicationContext(), "Message should not include special charecters", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (mSummary.length() <= 1000) {
            Toast.makeText(getApplicationContext(), "Message should be less than 1000 charecters", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (mSummary.isEmpty()){
            Toast.makeText(getApplicationContext(),"Invalid Summary",Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }

    private boolean validateTime() {
        if (Constants.Methods.checkForSpecial(mTime)) {
            Toast.makeText(getApplicationContext(), "Time should not include special charecters", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (mTime.length() <= 20) {
            Toast.makeText(getApplicationContext(), "Time should be less than 20 charecters", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (mTime.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Invalid Time",Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }


    public class MakeAppointment extends AsyncTask<Void, Void, Integer> {


        protected void onPreExecute() {
            pd = new SpotsDialog(progressDialogContext, R.style.CustomPD);
            pd.setTitle("Loading...");
            pd.setCancelable(false);
            pd.show();
        }

        protected Integer doInBackground(Void... params) {
            try {

                URL url = new URL(Constants.URLs.BASE + Constants.URLs.COUNC_APPOINTMENTS);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setDoInput(true);
                connection.setDoOutput(true);
                connection.setRequestMethod("POST");
                Uri.Builder _data = new Uri.Builder().appendQueryParameter("token", Constants.SharedPreferenceData.getTOKEN())
                        .appendQueryParameter("type", "make")
                        .appendQueryParameter("title", mtitle)
                        .appendQueryParameter("message", mSummary)
                        .appendQueryParameter("reserID", mTime);
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(connection.getOutputStream(), "UTF-8"));
                writer.write(_data.build().getEncodedQuery());
                writer.flush();
                writer.close();

                InputStreamReader in = new InputStreamReader(connection.getInputStream());

                StringBuilder jsonResults = new StringBuilder();
                // Load the results into a StringBuilder
                int read;
                char[] buff = new char[1024];
                while ((read = in.read(buff)) != -1) {
                    jsonResults.append(buff, 0, read);
                }
                connection.disconnect();

                authenticationError = jsonResults.toString().contains("Authentication Error");

                if (authenticationError) {
                    errorMessage = jsonResults.toString();
                } else {
                    JSONObject jsonObject = new JSONObject(jsonResults.toString());
                    response = jsonObject.getString("status");
                    if (!response.equals("success")) {
                        authenticationError = true;
                        errorMessage = response;
                    }
                }

                return 1;

            } catch (Exception ex) {
                ex.printStackTrace();
            }

            return 1;
        }

        protected void onPostExecute(Integer result) {
            if (pd != null) {
                pd.dismiss();
            }
            if (authenticationError) {
                Toast.makeText(getApplicationContext(), errorMessage, Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getApplicationContext(), response, Toast.LENGTH_SHORT).show();
            }
        }
    }

}
