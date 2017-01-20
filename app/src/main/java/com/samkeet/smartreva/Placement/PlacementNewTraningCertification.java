package com.samkeet.smartreva.Placement;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.samkeet.smartreva.Constants;
import com.samkeet.smartreva.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import dmax.dialog.SpotsDialog;

public class PlacementNewTraningCertification extends AppCompatActivity {

    public EditText mTitle, mOrg, mSub, mDur, mFP, mTP;
    public String title, org, sub, dur, fp, tp;

    public SpotsDialog pd;
    public Context progressDialogContext;
    public boolean authenticationError = true;
    public String errorMessage = "Data Corrupted";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_placement_new_traning_certification);
        progressDialogContext=this;

        mTitle= (EditText) findViewById(R.id.title);
        mOrg= (EditText) findViewById(R.id.org);
        mSub= (EditText) findViewById(R.id.sub);
        mDur = (EditText) findViewById(R.id.dur);
        mFP = (EditText) findViewById(R.id.fp);
        mTP= (EditText) findViewById(R.id.tp);
    }

    public void BackButton(View v) {
        finish();
    }

    public void submit(View v) {

        title = mTitle.getText().toString();
        org=mOrg.getText().toString();
        sub=mSub.getText().toString();
        dur=mDur.getText().toString();
        fp=mFP.getText().toString();
        tp=mTP.getText().toString();

        AddTandC addTandC=new AddTandC();
        addTandC.execute();

    }

    public class AddTandC extends AsyncTask<Void, Void, Integer> {


        protected void onPreExecute() {
            pd = new SpotsDialog(progressDialogContext,R.style.CustomPD);
            pd.setTitle("Loading...");
            pd.setCancelable(false);
            pd.show();
        }

        protected Integer doInBackground(Void... params) {
            try {


                URL url = new URL(Constants.URLs.BASE + Constants.URLs.PLACEMENT_TANDC);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setDoInput(true);
                connection.setDoOutput(true);
                connection.setRequestMethod("POST");

                Uri.Builder _data = new Uri.Builder().appendQueryParameter("token", Constants.SharedPreferenceData.getTOKEN())
                        .appendQueryParameter("type", "put")
                        .appendQueryParameter("title",title)
                        .appendQueryParameter("duration",dur)
                        .appendQueryParameter("from_period",fp)
                        .appendQueryParameter("to_period",tp)
                        .appendQueryParameter("organisation",org)
                        .appendQueryParameter("subject_area",sub);
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
                    String status = jsonObject.getString("status");
                    if (status.equals("Failed")) {
                        authenticationError = true;
                        errorMessage = status;
                    } else if (status.equals("success")) {
                        authenticationError = false;
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
                Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_SHORT).show();
                finish();
            }
        }

    }

}
