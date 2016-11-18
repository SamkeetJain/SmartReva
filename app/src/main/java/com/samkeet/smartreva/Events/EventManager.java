package com.samkeet.smartreva.Events;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.textservice.TextInfo;
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

public class EventManager extends AppCompatActivity {

    public String event_ID,name,type,desc,datetext;
    public String data;
    public JSONObject jsonObject;
    public TextView mName,mType,mDesc,mDate;

    public ProgressDialog pd;
    public Context progressDialogContext;

    public String results;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_manager);
        progressDialogContext=this;
        data=getIntent().getStringExtra("DATA");
        try {
            jsonObject=new JSONObject(data);
            event_ID=jsonObject.getString("event_ID");
            name=jsonObject.getString("name");
            type=jsonObject.getString("type");
            desc=jsonObject.getString("description");
            datetext=jsonObject.getString("edate");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        mName= (TextView) findViewById(R.id.name);
        mType= (TextView) findViewById(R.id.type);
        mDate= (TextView) findViewById(R.id.date);
        mDesc= (TextView) findViewById(R.id.desc);

        mName.setText(name);
        mType.setText(type);
        mDate.setText(datetext);
        mDesc.setText(desc);

    }

    public void Register(View v){
        GetStudentAuthentication getStudentAuthentication=new GetStudentAuthentication();
        getStudentAuthentication.execute();
    }

    public void GetList(View v){
        GetFacultyAuthentication getFacultyAuthentication=new GetFacultyAuthentication();
        getFacultyAuthentication.execute();
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


            } else {
                Toast.makeText(getApplicationContext(), "You are not Autherised for this task!!!", Toast.LENGTH_SHORT).show();
            }

        }
    }


}
