package com.samkeet.smartreva.Attendence;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.samkeet.smartreva.Constants;
import com.samkeet.smartreva.R;

import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class AttendenceMainActivity extends AppCompatActivity {

    public final static int GET_SUBJECT_LIST = 101;
    public final static int GET_CLASS_LIST = 102;

    public Button TakeAttendence, ViewAttendence, GenerateAttendenceReport;

    private ProgressDialog pd;
    private Context progressDialogContext;

    String type;
    String results;
    String subject_value;
    String class_value;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendence_main);

        progressDialogContext = this;

        TakeAttendence = (Button) findViewById(R.id.take_attendence_button);
        ViewAttendence = (Button) findViewById(R.id.view_attendence_button);
        GenerateAttendenceReport = (Button) findViewById(R.id.generate_attendence_report_button);

    }

    public void TakeAttendence(View v) {

        GetFacultyAuthentication getFacultyAuthentication = new GetFacultyAuthentication();
        getFacultyAuthentication.execute();
    }

    public void ViewAttendence(View v) {

        GetStudentAuthentication getStudentAuthentication = new GetStudentAuthentication();
        getStudentAuthentication.execute();
    }

    public void GenerateAttendenceReport(View v) {

        //TODO Connect to server for Authentication and then proceed.

        Intent intent = new Intent(getApplicationContext(), GenerateAttendenceReport.class);
        startActivity(intent);

    }

    public void BackButton(View v) {
        finish();
    }

    private class GetFacultyAuthentication extends AsyncTask<Void, Void, Integer> {


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
                java.net.URL url = new URL(Constants.URLs.AUTHENTICATION);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setDoInput(true);
                connection.setDoOutput(true);
                connection.setRequestMethod("POST");
                Log.d("POST", "DATA ready to sent");

                Uri.Builder _data = new Uri.Builder().appendQueryParameter("token", Constants.SharedPreferenceData.getTOKEN()).appendQueryParameter("type", "faculty");
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
                JSONObject jsonObject = new JSONObject(jsonResults.toString());
                results = jsonObject.getString("status");


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
            if (results.equals("true")) {
                GetGeneralList getGeneralList = new GetGeneralList();
                type = "subject_list";
                getGeneralList.execute();
            } else {
                Toast.makeText(getApplicationContext(), "You are not Autherised for this task!!!", Toast.LENGTH_SHORT).show();
            }

        }
    }

    private class GetStudentAuthentication extends AsyncTask<Void, Void, Integer> {


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
                java.net.URL url = new URL(Constants.URLs.AUTHENTICATION);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setDoInput(true);
                connection.setDoOutput(true);
                connection.setRequestMethod("POST");
                Log.d("POST", "DATA ready to sent");

                Uri.Builder _data = new Uri.Builder().appendQueryParameter("token", Constants.SharedPreferenceData.getTOKEN()).appendQueryParameter("type", "student");
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(connection.getOutputStream(), "UTF-8"));
                writer.write(_data.build().getEncodedQuery());
                writer.flush();
                writer.close();
                Log.d("POST", _data.toString());

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
                JSONObject jsonObject = new JSONObject(jsonResults.toString());
                results = jsonObject.getString("status");


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
            if (results.equals("true")) {
                Intent intent =new Intent(getApplicationContext(),ViewAttendence.class);
                startActivity(intent);

            } else {
                Toast.makeText(getApplicationContext(), "You are not Autherised for this task!!!", Toast.LENGTH_SHORT).show();
            }

        }
    }

    private class GetGeneralList extends AsyncTask<Void, Void, Integer> {


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
                java.net.URL url = new URL(Constants.URLs.GENERALLIST);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setDoInput(true);
                connection.setDoOutput(true);
                connection.setRequestMethod("POST");
                Log.d("POST", "DATA ready to sent");

                Uri.Builder _data = new Uri.Builder().appendQueryParameter("token", Constants.SharedPreferenceData.getTOKEN()).appendQueryParameter("type", type);
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(connection.getOutputStream(), "UTF-8"));
                writer.write(_data.build().getEncodedQuery());
                writer.flush();
                writer.close();
                Log.d("POST", _data.toString());

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

                results = jsonResults.toString();

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

            if (results.length() > 0) {
                Intent intent = new Intent(getApplicationContext(), GeneralListActivity.class);
                intent.putExtra("RESULTS", results);
                if (type.equals("subject_list")) {
                    startActivityForResult(intent, GET_SUBJECT_LIST);
                }else if(type.equals(("class_list"))){
                    startActivityForResult(intent,GET_CLASS_LIST);
                }
            } else {
                Toast.makeText(getApplicationContext(), "No Data Found", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == GET_SUBJECT_LIST) {
            if (resultCode == RESULT_OK) {
                subject_value = data.getStringExtra("returnValue");
                subject_value=subject_value.trim();
                subject_value=subject_value.replaceAll("\\t","");
                type = "class_list";
                GetGeneralList getGeneralList = new GetGeneralList();
                getGeneralList.execute();
            }
        } else if (requestCode == GET_CLASS_LIST) {
            if (resultCode == RESULT_OK) {
                class_value = data.getStringExtra("returnValue");
                class_value=class_value.trim();
                class_value=class_value.replaceAll("\\t","");
                Intent intent = new Intent(getApplicationContext(), TakeAttendence.class);
                intent.putExtra("TABLE", class_value+"_"+subject_value);
                startActivity(intent);
            }
        }
    }
}
