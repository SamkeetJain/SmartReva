package com.samkeet.smartreva.Attendence;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.crash.FirebaseCrash;
import com.samkeet.smartreva.Constants;
import com.samkeet.smartreva.R;

import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import dmax.dialog.SpotsDialog;


public class AttendenceMainActivity extends AppCompatActivity {

    public final static int GET_SUBJECT_LIST = 101;
    public final static int GET_CLASS_LIST = 102;

    public Button TakeAttendence, ViewAttendence, AbsentDetails, GenerateAttendenceReport;

    private SpotsDialog pd;
    private Context progressDialogContext;

    String type;
    String results;
    String subject_value;
    String class_value;

    public boolean authenticationError=true;
    public String errorMessage="Data Corupted";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendence_main);

        progressDialogContext = this;

        TakeAttendence = (Button) findViewById(R.id.take_attendence_button);
        ViewAttendence = (Button) findViewById(R.id.view_attendence_button);
        GenerateAttendenceReport = (Button) findViewById(R.id.generate_attendence_report_button);
        AbsentDetails = (Button) findViewById(R.id.absent_details);

    }

    public void AbsentDetails(View v) {
        if (Constants.Methods.networkState(getApplicationContext(), (ConnectivityManager) getSystemService(getApplicationContext().CONNECTIVITY_SERVICE))) {
            GetStudentAuthentication2 getStudentAuthentication = new GetStudentAuthentication2();
            getStudentAuthentication.execute();
        }
    }

    public void TakeAttendence(View v) {
        if (Constants.Methods.networkState(getApplicationContext(), (ConnectivityManager) getSystemService(getApplicationContext().CONNECTIVITY_SERVICE))) {
            GetFacultyAuthentication getFacultyAuthentication = new GetFacultyAuthentication();
            getFacultyAuthentication.execute();
        }
    }

    public void ViewAttendence(View v) {
        if (Constants.Methods.networkState(getApplicationContext(), (ConnectivityManager) getSystemService(getApplicationContext().CONNECTIVITY_SERVICE))) {
            GetStudentAuthentication getStudentAuthentication = new GetStudentAuthentication();
            getStudentAuthentication.execute();
        }
    }

    public void GenerateAttendenceReport(View v) {

        //TODO Connect to server for Authentication and then proceed.

        Intent intent = new Intent(getApplicationContext(), AttendenceGenerateAttendenceReport.class);
        startActivity(intent);

    }

    public void BackButton(View v) {
        finish();
    }

    private class GetFacultyAuthentication extends AsyncTask<Void, Void, Integer> {

        protected void onPreExecute() {
            pd = new SpotsDialog(progressDialogContext, R.style.CustomPD);
            pd.setTitle("Loading...");
            pd.setCancelable(false);
            pd.show();
        }

        protected Integer doInBackground(Void... params) {
            try {
                java.net.URL url = new URL(Constants.URLs.BASE + Constants.URLs.AUTHENTICATION);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setDoInput(true);
                connection.setDoOutput(true);
                connection.setRequestMethod("POST");

                Uri.Builder _data = new Uri.Builder().appendQueryParameter("token", Constants.SharedPreferenceData.getTOKEN()).appendQueryParameter("type", "faculty");
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
                    // Create a JSON object hierarchy from the results
                    JSONObject jsonObj = new JSONObject(jsonResults.toString());
                    String status = jsonObj.getString("status");
                    if (status.equals("success")) {

                    } else {
                        authenticationError = true;
                        errorMessage = status;
                    }
                }

                return 1;

            } catch (Exception ex) {
                FirebaseCrash.report(new Exception(ex));
                ex.printStackTrace();
                finish();
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
                GetGeneralList getGeneralList = new GetGeneralList();
                type = "subject_code";
                getGeneralList.execute();
            }

        }
    }

    private class GetStudentAuthentication extends AsyncTask<Void, Void, Integer> {


        protected void onPreExecute() {
            pd = new SpotsDialog(progressDialogContext, R.style.CustomPD);
            pd.setTitle("Loading...");
            pd.setCancelable(false);
            pd.show();
        }

        protected Integer doInBackground(Void... params) {
            try {
                java.net.URL url = new URL(Constants.URLs.BASE + Constants.URLs.AUTHENTICATION);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setDoInput(true);
                connection.setDoOutput(true);
                connection.setRequestMethod("POST");

                Uri.Builder _data = new Uri.Builder().appendQueryParameter("token", Constants.SharedPreferenceData.getTOKEN()).appendQueryParameter("type", "student");
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
                    // Create a JSON object hierarchy from the results
                    JSONObject jsonObj = new JSONObject(jsonResults.toString());
                    String status = jsonObj.getString("status");
                    if (status.equals("success")) {

                    } else {
                        authenticationError = true;
                        errorMessage = status;
                    }
                }


                return 1;

            } catch (Exception ex) {
                FirebaseCrash.report(new Exception(ex));
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
                Intent intent = new Intent(getApplicationContext(), AttendenceViewAttendence.class);
                startActivity(intent);
            }

        }
    }

    private class GetStudentAuthentication2 extends AsyncTask<Void, Void, Integer> {


        protected void onPreExecute() {
            pd = new SpotsDialog(progressDialogContext, R.style.CustomPD);
            pd.setTitle("Loading...");
            pd.setCancelable(false);
            pd.show();
        }

        protected Integer doInBackground(Void... params) {
            try {
                java.net.URL url = new URL(Constants.URLs.BASE + Constants.URLs.AUTHENTICATION);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setDoInput(true);
                connection.setDoOutput(true);
                connection.setRequestMethod("POST");

                Uri.Builder _data = new Uri.Builder().appendQueryParameter("token", Constants.SharedPreferenceData.getTOKEN()).appendQueryParameter("type", "student");
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
                    // Create a JSON object hierarchy from the results
                    JSONObject jsonObj = new JSONObject(jsonResults.toString());
                    String status = jsonObj.getString("status");
                    if (status.equals("success")) {

                    } else {
                        authenticationError = true;
                        errorMessage = status;
                    }
                }


                return 1;

            } catch (Exception ex) {
                FirebaseCrash.report(new Exception(ex));
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
                Intent intent = new Intent(getApplicationContext(), AttendenceAbsentDetailsActivity.class);
                startActivity(intent);
            }

        }
    }

    private class GetGeneralList extends AsyncTask<Void, Void, Integer> {


        protected void onPreExecute() {
            pd = new SpotsDialog(progressDialogContext, R.style.CustomPD);
            pd.setTitle("Loading...");
            pd.setCancelable(false);
            pd.show();
        }

        protected Integer doInBackground(Void... params) {
            try {
                java.net.URL url = new URL(Constants.URLs.BASE + Constants.URLs.GENERALLIST);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setDoInput(true);
                connection.setDoOutput(true);
                connection.setRequestMethod("POST");

                Uri.Builder _data = new Uri.Builder().appendQueryParameter("token", Constants.SharedPreferenceData.getTOKEN()).appendQueryParameter("type", type);
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
                    results = jsonResults.toString();
                }

                return 1;

            } catch (Exception ex) {
                FirebaseCrash.report(new Exception(ex));
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
                Intent intent = new Intent(getApplicationContext(), AttendenceGenerateListActivity.class);
                intent.putExtra("RESULTS", results);
                intent.putExtra("TYPE", type);
                if (type.equals("subject_code")) {
                    startActivityForResult(intent, GET_SUBJECT_LIST);
                } else if (type.equals(("class_code"))) {
                    startActivityForResult(intent, GET_CLASS_LIST);
                }
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == GET_SUBJECT_LIST) {
            if (resultCode == RESULT_OK) {
                subject_value = data.getStringExtra("returnValue");
                subject_value = subject_value.trim();
                subject_value = subject_value.replaceAll("\\t", "");
                type = "class_code";
                GetGeneralList getGeneralList = new GetGeneralList();
                getGeneralList.execute();
            }
        } else if (requestCode == GET_CLASS_LIST) {
            if (resultCode == RESULT_OK) {
                class_value = data.getStringExtra("returnValue");
                class_value = class_value.trim();
                class_value = class_value.replaceAll("\\t", "");
                Intent intent = new Intent(getApplicationContext(), AttendenceTakeAttendence.class);
                intent.putExtra("CLASSCODE", class_value);
                intent.putExtra("SUBJECTCODE", subject_value);
                intent.putExtra("TABLE", class_value + "_" + subject_value);
                startActivity(intent);
            }
        }
    }
}
