package com.samkeet.smartreva;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.samkeet.smartreva.AlumniCell.AlumniCellActivity;
import com.samkeet.smartreva.Attendence.AttendenceMainActivity;
import com.samkeet.smartreva.Councling.CounclingMainActivity;
import com.samkeet.smartreva.Events.EventsMainActivity;
import com.samkeet.smartreva.Fees.FeesMainActivity;
import com.samkeet.smartreva.Library.LibraryMainActivity;
import com.samkeet.smartreva.Notes.NotesMainActivity;
import com.samkeet.smartreva.Notification.NotificationMainActivity;
import com.samkeet.smartreva.Results.ResultsMainActivity;
import com.samkeet.smartreva.Setings.SettingsMainActivity;
import com.samkeet.smartreva.Timetable.TimetableMainActivity;

import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class LauncherActivity extends AppCompatActivity {

    public boolean authenticationError;
    public String errorMessage;

    private ProgressDialog pd;
    private Context progressDialogContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);

        progressDialogContext=this;

        Constants.SharedPreferenceData.initSharedPreferenceData(getSharedPreferences(Constants.SharedPreferenceData.SHAREDPREFERENCES, MODE_PRIVATE));

        if (!Constants.SharedPreferenceData.getIsLoggedIn().equals("yes")) {
            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
            startActivityForResult(intent, 201);
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 201) {
            if (resultCode == RESULT_OK) {
                if (!Constants.SharedPreferenceData.getIsLoggedIn().equals("yes")) {
                    Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                    startActivityForResult(intent, 201);
                }
            }
        }
    }

    public void Attendence(View v) {
        Intent intent = new Intent(getApplicationContext(), AttendenceMainActivity.class);
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

        Constants.SharedPreferenceData.clearData();
//        Intent intent = new Intent(getApplicationContext(), NotificationMainActivity.class);
//        startActivity(intent);
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
        Intent intent = new Intent(getApplicationContext(), LibraryMainActivity.class);
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

    public void Alumini(View v) {
        Intent intent = new Intent(getApplicationContext(), AlumniCellActivity.class);
        startActivity(intent);
    }

    public void Fees(View v) {
        GetStudentAuthentication getStudentAuthentication=new GetStudentAuthentication();
        getStudentAuthentication.execute();
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

                if(authenticationError) {
                    errorMessage = jsonResults.toString();
                }else {
                    // Create a JSON object hierarchy from the results
                    JSONObject jsonObj = new JSONObject(jsonResults.toString());
                    String status = jsonObj.getString("status");
                    if(status.equals("success")){

                    }else {
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
            if(authenticationError){
                Toast.makeText(getApplicationContext(),errorMessage,Toast.LENGTH_SHORT).show();
            }else{
                Intent intent = new Intent(getApplicationContext(), FeesMainActivity.class);
                startActivity(intent);
            }

        }
    }

}
