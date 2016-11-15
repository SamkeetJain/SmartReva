package com.samkeet.smartreva.Events;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.samkeet.smartreva.Constants;
import com.samkeet.smartreva.Councling.CounclingNewAppointment;
import com.samkeet.smartreva.R;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Calendar;

public class AddEventActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener{

    EditText mName, mType, mDates, mDesc;
    String name, type, dates, desc;

    public ProgressDialog pd;
    public Context progressDialogContext;

    String responce;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);
        mName = (EditText) findViewById(R.id.name);
        mType = (EditText) findViewById(R.id.type);
        mDates = (EditText) findViewById(R.id.date);
        mDesc = (EditText) findViewById(R.id.desc);
        progressDialogContext=this;
    }

    public void BackButton(View v) {
        finish();
    }

    public void Send(View v) {
        name = mName.getText().toString();
        type = mType.getText().toString();
        dates = mDates.getText().toString();
        desc = mDesc.getText().toString();

        NewEvent newEvent = new NewEvent();
        newEvent.execute();

    }

    public void DateButton(View v){
        Calendar now = Calendar.getInstance();
        DatePickerDialog dpd = DatePickerDialog.newInstance(
                AddEventActivity.this,
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
        if (dayOfMonth < 10)
            fullDate = "0" + dayOfMonth + " / " + (++monthOfYear) + " / " + year;
        else
            fullDate = dayOfMonth + " / " + (++monthOfYear) + " / " + year;

        mDates.setText(fullDate);
    }

    public class NewEvent extends AsyncTask<Void, Void, Integer> {


        protected void onPreExecute() {
            pd = new ProgressDialog(progressDialogContext);
            pd.setTitle("Loading...");
            pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            pd.setMessage("Please wait.");
            pd.setCancelable(false);
            pd.setIndeterminate(true);
            pd.show();
        }

        protected Integer doInBackground(Void... params) {
            try {

                int random = (int) (Math.random() * 5000 + 1);
                String postID = String.valueOf(random);
                URL url = new URL(Constants.URLs.NEWEVENT);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setDoInput(true);
                connection.setDoOutput(true);
                connection.setRequestMethod("POST");
                Log.d("POST", "DATA ready to sent");

                Uri.Builder _data = new Uri.Builder().appendQueryParameter("event_ID", postID).appendQueryParameter("name", name).appendQueryParameter("edate", dates).appendQueryParameter("type", type).appendQueryParameter("description", desc);
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(connection.getOutputStream(), "UTF-8"));
                writer.write(_data.build().getEncodedQuery());
                writer.flush();
                writer.close();
                Log.d("POST", "DATA SENT");

                InputStreamReader in = new InputStreamReader(connection.getInputStream());

                StringBuilder jsonResults = new StringBuilder();
                // Load the results into a StringBuilder
                int read;
                char[] buff = new char[1024];
                while ((read = in.read(buff)) != -1) {
                    jsonResults.append(buff, 0, read);
                }
                connection.disconnect();
                Log.d("return from server", jsonResults.toString());
                responce = jsonResults.toString();


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

            responce = responce.replaceAll("\\t", "");
            if (!responce.equals("Event Added")) {
                Toast.makeText(getApplicationContext(), "Error: Event Not Added!!!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getApplicationContext(), responce, Toast.LENGTH_SHORT).show();
            }
            finish();


        }
    }


}
