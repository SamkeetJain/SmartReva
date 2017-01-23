package com.samkeet.smartreva.AlumniCell;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.samkeet.smartreva.Constants;
import com.samkeet.smartreva.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import dmax.dialog.SpotsDialog;

public class AlumniEventManager extends AppCompatActivity {

    public String event_ID, name, type, desc, datetext;
    public String data;
    public JSONObject jsonObject;
    public TextView mName, mType, mDesc, mDate;

    public SpotsDialog pd;
    public Context progressDialogContext;

    public String results;
    public String request;

    public boolean authenticationError = true;
    public String errorMessage = "Data Corupted";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alumni_event_manager);

        progressDialogContext = this;
        data = getIntent().getStringExtra("DATA");
        try {
            jsonObject = new JSONObject(data);
            event_ID = jsonObject.getString("Id");
            name = jsonObject.getString("name");
            type = jsonObject.getString("type");
            desc = jsonObject.getString("description");
            datetext = jsonObject.getString("edate");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        mName = (TextView) findViewById(R.id.name);
        mType = (TextView) findViewById(R.id.type);
        mDate = (TextView) findViewById(R.id.date);
        mDesc = (TextView) findViewById(R.id.desc);

        mName.setText(name);
        mType.setText(type);
        mDate.setText(datetext);
        mDesc.setText(desc);
    }

    public void BackButton(View v) {
        finish();
    }

    public void Register(View v) {
        if (Constants.Methods.networkState(getApplicationContext(), (ConnectivityManager) getSystemService(getApplicationContext().CONNECTIVITY_SERVICE))) {
            EManager eManager=new EManager();
            eManager.execute();
        }
    }

    private class EManager extends AsyncTask<Void, Void, Integer> {


        protected void onPreExecute() {
            pd = new SpotsDialog(progressDialogContext, R.style.CustomPD);
            pd.setTitle("Loading...");
            pd.setCancelable(false);
            pd.show();
        }

        protected Integer doInBackground(Void... params) {
            try {
                java.net.URL url = new URL(Constants.URLs.ALUMNI_BASE + Constants.URLs.ALUMNI_EVENTMANAGER);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setDoInput(true);
                connection.setDoOutput(true);
                connection.setRequestMethod("POST");

                Uri.Builder _data = new Uri.Builder().appendQueryParameter("token", Constants.SharedPreferenceData.getTOKEN()).appendQueryParameter("type", "add").appendQueryParameter("id", event_ID).appendQueryParameter("member", Constants.SharedPreferenceData.getUserId());
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
                    authenticationError=false;
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
            String msg = "";
            if (authenticationError) {
                Toast.makeText(getApplicationContext(), errorMessage, Toast.LENGTH_SHORT).show();
            } else {
                try {
                    JSONObject jsonObject = new JSONObject(results);
                    msg = jsonObject.getString("status");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
        }
    }

}
