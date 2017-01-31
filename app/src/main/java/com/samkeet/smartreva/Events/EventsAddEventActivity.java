package com.samkeet.smartreva.Events;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.samkeet.smartreva.Constants;
import com.samkeet.smartreva.R;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Calendar;

import dmax.dialog.SpotsDialog;

public class EventsAddEventActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    EditText mName, mType, mDates, mDesc;
    String name, type, dates, desc;

    public SpotsDialog pd;
    public Context progressDialogContext;

    String responce;

    public boolean authenticationError;
    public String errorMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events_add_event);
        mName = (EditText) findViewById(R.id.name);
        mType = (EditText) findViewById(R.id.type);
        mDates = (EditText) findViewById(R.id.date);
        mDesc = (EditText) findViewById(R.id.desc);
        progressDialogContext = this;
    }

    public void BackButton(View v) {
        finish();
    }

    public void Send(View v) {
        name = mName.getText().toString();
        type = mType.getText().toString();
        dates = mDates.getText().toString();
        desc = mDesc.getText().toString();

        if (validation()) {
            if (Constants.Methods.networkState(getApplicationContext(), (ConnectivityManager) getSystemService(getApplicationContext().CONNECTIVITY_SERVICE))) {
                NewEvent newEvent = new NewEvent();
                newEvent.execute();
            }
        }
    }

    public boolean validation() {
        if (Constants.Methods.checkForSpecial(name)) {
            Toast.makeText(getApplicationContext(), "Name should not include special charecters", Toast.LENGTH_SHORT).show();
        }

        if (!(name.length() <= 60) && (name.length() >= 1)) {
            Toast.makeText(getApplicationContext(), "Name should be less than 60 charecters", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (Constants.Methods.checkForSpecial(dates)) {
            Toast.makeText(getApplicationContext(), "Dates should not include special charecters", Toast.LENGTH_SHORT).show();

        }
        if (!(dates.length() <= 20) && (dates.length() >= 1)) {
            Toast.makeText(getApplicationContext(), "Dates should be less than 20 charecters", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (Constants.Methods.checkForSpecial(type)) {
            Toast.makeText(getApplicationContext(), "Type should not include special charecters", Toast.LENGTH_SHORT).show();
        }
        if (!(type.length() <= 60) && (type.length() >= 1)) {
            Toast.makeText(getApplicationContext(), "Type should be less than 60 charecters", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (Constants.Methods.checkForSpecial(desc)) {
            Toast.makeText(getApplicationContext(), "Description should not include special charecters", Toast.LENGTH_SHORT).show();
        }
        if (!(desc.length() <= 5000) && (desc.length() >= 1)) {
            Toast.makeText(getApplicationContext(), "Description should be less than 5000 charecters", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    public void DateButton(View v) {
        Calendar now = Calendar.getInstance();
        DatePickerDialog dpd = DatePickerDialog.newInstance(
                EventsAddEventActivity.this,
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
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        String fullDate;
        String dayofmonth = "" + dayOfMonth;
        String monthofyear = "" + monthOfYear;
        if (dayOfMonth < 10)
            dayofmonth = "0" + dayOfMonth;
        if (++monthOfYear < 10) {
            monthofyear = "0" + monthOfYear;
        }

        fullDate = year + "-" + (monthofyear) + "-" + dayofmonth;

        mDates.setText(fullDate);
    }

    public class NewEvent extends AsyncTask<Void, Void, Integer> {


        protected void onPreExecute() {
            pd = new SpotsDialog(progressDialogContext, R.style.CustomPD);
            pd.setTitle("Loading...");
            pd.setCancelable(false);
            pd.show();
        }

        protected Integer doInBackground(Void... params) {
            try {

                URL url = new URL(Constants.URLs.BASE + Constants.URLs.EVENTS);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setDoInput(true);
                connection.setDoOutput(true);
                connection.setRequestMethod("POST");

                Uri.Builder _data = new Uri.Builder().appendQueryParameter("token", Constants.SharedPreferenceData.getTOKEN())
                        .appendQueryParameter("requestType", "add")
                        .appendQueryParameter("name", name)
                        .appendQueryParameter("date", dates)
                        .appendQueryParameter("type", type)
                        .appendQueryParameter("desc", desc);
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
                    responce = jsonObject.getString("status");
                    if (!responce.equals("success")) {
                        authenticationError = true;
                        errorMessage = responce;
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
                Toast.makeText(getApplicationContext(), responce, Toast.LENGTH_SHORT).show();
            }
            finish();


        }
    }


}
