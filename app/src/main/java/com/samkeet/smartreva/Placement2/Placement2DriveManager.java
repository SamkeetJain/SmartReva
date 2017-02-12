package com.samkeet.smartreva.Placement2;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.samkeet.smartreva.Constants;
import com.samkeet.smartreva.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import dmax.dialog.SpotsDialog;

public class Placement2DriveManager extends AppCompatActivity {

    public TextView mName, mDate, mCourse, mBranch, mDetails;
    public String id, name, ddate, course, branch, details;
    public Button register;
    public JSONObject jsonObject;

    public boolean authenticationError = true;
    public String errorMessage = "Data Corrupted";
    public SpotsDialog pd;
    public Context progressDialogContext;
    public String type;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_placement2_drive_manager);
        progressDialogContext = this;

        mName = (TextView) findViewById(R.id.name);
        mDate = (TextView) findViewById(R.id.ddate);
        mCourse = (TextView) findViewById(R.id.course);
        mBranch = (TextView) findViewById(R.id.branch);
        mDetails = (TextView) findViewById(R.id.details);

        String temp = getIntent().getStringExtra("DATA");
        type = getIntent().getStringExtra("TYPE");

        register = (Button) findViewById(R.id.register);
        if (type.equals("ALL")) {
            register.setText("register");
        } else if (type.equals("MY")) {
            register.setText("unregister");
        }
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (type.equals("ALL")) {
                    Registration registration = new Registration();
                    registration.execute();
                } else if (type.equals("MY")) {
                    Unregistration unregistration = new Unregistration();
                    unregistration.execute();
                }
            }
        });


        try {
            jsonObject = new JSONObject(temp);
            id = jsonObject.getString("ID");
            name = jsonObject.getString("comp_name");
            ddate = jsonObject.getString("ddate");
            course = jsonObject.getString("course");
            branch = jsonObject.getString("branch");
            details = jsonObject.getString("details");

            ddate = "On " + ddate;
            course = "Course - " + course.toUpperCase();
            branch = "Branch - " + branch.toUpperCase();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        mName.setText(name);
        mDate.setText(ddate);
        mCourse.setText(course);
        mBranch.setText(branch);
        mDetails.setText(details);


    }

    public void BackButton(View v) {
        finish();
    }

    public class Registration extends AsyncTask<Void, Void, Integer> {


        protected void onPreExecute() {
            pd = new SpotsDialog(progressDialogContext, R.style.CustomPD);
            pd.setTitle("Loading...");
            pd.setCancelable(false);
            pd.show();
        }

        protected Integer doInBackground(Void... params) {
            try {


                URL url = new URL(Constants.URLs.PLACEMENT_BASE + Constants.URLs.PLACEMENT_DRIVE_REG);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setDoInput(true);
                connection.setDoOutput(true);
                connection.setRequestMethod("POST");

                Uri.Builder _data = new Uri.Builder().appendQueryParameter("token", Constants.SharedPreferenceData.getTOKEN()).appendQueryParameter("requestType", "put").appendQueryParameter("id", id);
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

                Log.d("return from server", jsonResults.toString());
                authenticationError = jsonResults.toString().contains("Authentication Error");

                if (authenticationError) {
                    errorMessage = jsonResults.toString();
                } else {
                    JSONObject jsonObj = new JSONObject(jsonResults.toString());
                    String status = jsonObj.getString("status");
                    if (status.equals("success")) {
                        authenticationError = false;
                        errorMessage = status;
                    } else {
                        authenticationError = true;
                        errorMessage = status;
                    }
                    authenticationError = false;
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
                Toast.makeText(getApplicationContext(), errorMessage, Toast.LENGTH_SHORT).show();
            }
        }

    }

    public class Unregistration extends AsyncTask<Void, Void, Integer> {


        protected void onPreExecute() {
            pd = new SpotsDialog(progressDialogContext, R.style.CustomPD);
            pd.setTitle("Loading...");
            pd.setCancelable(false);
            pd.show();
        }

        protected Integer doInBackground(Void... params) {
            try {


                URL url = new URL(Constants.URLs.PLACEMENT_BASE + Constants.URLs.PLACEMENT_MY_DRIVE);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setDoInput(true);
                connection.setDoOutput(true);
                connection.setRequestMethod("POST");

                Uri.Builder _data = new Uri.Builder().appendQueryParameter("token", Constants.SharedPreferenceData.getTOKEN()).appendQueryParameter("requestType", "unregistered").appendQueryParameter("placement_id", id);
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

                Log.d("return from server", jsonResults.toString());
                authenticationError = jsonResults.toString().contains("Authentication Error");

                if (authenticationError) {
                    errorMessage = jsonResults.toString();
                } else {
                    JSONObject jsonObj = new JSONObject(jsonResults.toString());
                    String status = jsonObj.getString("status");
                    if (status.equals("success")) {
                        authenticationError = false;
                        errorMessage = status;
                    } else {
                        authenticationError = true;
                        errorMessage = status;
                    }
                    authenticationError = false;
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
                Toast.makeText(getApplicationContext(), errorMessage, Toast.LENGTH_SHORT).show();
            }
        }

    }

}
