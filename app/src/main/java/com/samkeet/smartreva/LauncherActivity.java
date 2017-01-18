package com.samkeet.smartreva;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.crash.FirebaseCrash;
import com.samkeet.smartreva.AlumniCell.AlumniLoginActivity;
import com.samkeet.smartreva.Attendence.AttendenceMainActivity;
import com.samkeet.smartreva.Councling.CounclingMainActivity;
import com.samkeet.smartreva.Events.EventsMainActivity;
import com.samkeet.smartreva.Fees.FeesMainActivity;
import com.samkeet.smartreva.Notes.NotesMainActivity;
import com.samkeet.smartreva.Notes.NotesViewNotes;
import com.samkeet.smartreva.Notification.NotificationMainActivity;
import com.samkeet.smartreva.Placement.PlacementMainActivity;
import com.samkeet.smartreva.Placement2.Placement2MainActivity;
import com.samkeet.smartreva.Results.ResultsMainActivity;
import com.samkeet.smartreva.Timetable.TimetableMainActivity;
import com.samkeet.smartreva.Mentor.MentorMainActivity;

import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import dmax.dialog.SpotsDialog;

public class LauncherActivity extends AppCompatActivity {

    public boolean authenticationError = true;
    public String errorMessage = "Data Corupted";

    private SpotsDialog pd;
    private Context progressDialogContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);

        if (Constants.SharedPreferenceData.isSharedPreferenceInited()) {
            Constants.SharedPreferenceData.initSharedPreferenceData(getSharedPreferences(Constants.SharedPreferenceData.SHAREDPREFERENCES, MODE_PRIVATE));
        }

        progressDialogContext = this;

        if (Constants.FireBase.token != null) {
            UpdateToken updateToken = new UpdateToken();
            updateToken.execute();
        }
    }

    public void Attendence(View v) {
        Intent intent = new Intent(getApplicationContext(), AttendenceMainActivity.class);
        startActivity(intent);
    }

    public void Developers(View v) {
        Intent intent = new Intent(getApplicationContext(), SplashActivity.class);
        startActivity(intent);
    }
    public void ContactUs (View v){
        Intent intent =new Intent(getApplicationContext(),ProfileActivity.class);
        startActivity(intent);
    }

    public void Mentor(View v) {
        Intent intent = new Intent(getApplicationContext(), MentorMainActivity.class);
        startActivity(intent);
    }

    public void Result(View v) {
        Intent intent = new Intent(getApplicationContext(), ResultsMainActivity.class);
        startActivity(intent);
    }


    public void Notes(View v) {
        Intent intent = new Intent(getApplicationContext(), NotesMainActivity.class);
        startActivity(intent);
    }

    public void Placements(View v) {
        Intent intent = new Intent(getApplicationContext(), Placement2MainActivity.class);
        startActivity(intent);
    }

    public void Notification(View v) {
        Intent intent = new Intent(getApplicationContext(), NotificationMainActivity.class);
        startActivity(intent);
    }


    public void Events(View v) {
        Intent intent = new Intent(getApplicationContext(), EventsMainActivity.class);
        startActivity(intent);
    }

    public void Councling(View v) {
        Intent intent = new Intent(getApplicationContext(), CounclingMainActivity.class);
        startActivity(intent);
    }

    public void Library(View v) {
        //TODO Change to LibraryMainActivity
//        Intent intent = new Intent(getApplicationContext(), LibraryMainActivity.class);
//        startActivity(intent);

        Intent intent = new Intent(getApplicationContext(), NotesViewNotes.class);
        startActivity(intent);

    }

    public void Profile(View v) {
        Intent intent = new Intent(getApplicationContext(), ProfileStudentActivity.class);
        startActivity(intent);
    }

    public void Timetable(View v) {
        Intent intent = new Intent(getApplicationContext(), TimetableMainActivity.class);
        startActivity(intent);
    }

    public void Fees(View v) {
        if (Constants.Methods.networkState(getApplicationContext(), (ConnectivityManager) getSystemService(getApplicationContext().CONNECTIVITY_SERVICE))) {
            GetStudentAuthentication getStudentAuthentication = new GetStudentAuthentication();
            getStudentAuthentication.execute();
        }
    }

    public void Revamp(View v) {
        Toast.makeText(getApplicationContext(), "Coming Soon", Toast.LENGTH_SHORT).show();
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
                Log.d("POST", "DATA ready to sent");

                Uri.Builder _data = new Uri.Builder().appendQueryParameter("token", Constants.SharedPreferenceData.getTOKEN()).appendQueryParameter("type", "student");
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
                ex.printStackTrace();
                FirebaseCrash.report(new Exception(ex));
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
                Intent intent = new Intent(getApplicationContext(), FeesMainActivity.class);
                startActivity(intent);
            }

        }
    }

    private class UpdateToken extends AsyncTask<Void, Void, Integer> {


        protected void onPreExecute() {
            pd = new SpotsDialog(progressDialogContext, R.style.CustomPD);
            pd.setTitle("Loading...");
            pd.setCancelable(false);
            pd.show();
        }

        protected Integer doInBackground(Void... params) {
            try {
                java.net.URL url = new URL("http://revacounselling.16mb.com/firebase_reg.php");
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setDoInput(true);
                connection.setDoOutput(true);
                connection.setRequestMethod("POST");
                Log.d("POST", "DATA ready to sent");

                Uri.Builder _data = new Uri.Builder().appendQueryParameter("token", Constants.SharedPreferenceData.getTOKEN()).appendQueryParameter("firebasetoken", Constants.FireBase.token);
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

            }

        }
    }

}
