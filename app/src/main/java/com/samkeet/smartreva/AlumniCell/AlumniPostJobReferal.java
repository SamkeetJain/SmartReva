package com.samkeet.smartreva.AlumniCell;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

import com.samkeet.smartreva.Constants;
import com.samkeet.smartreva.R;
import com.satsuware.usefulviews.LabelledSpinner;

import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import dmax.dialog.SpotsDialog;

public class AlumniPostJobReferal extends AppCompatActivity {

    public LabelledSpinner mJobType;
    public TextView mCompany,mRole,mDesc;
    public String company,role,desc,jobType;
    public String[] types= {"Internship","Full-Time Job"};

    public SpotsDialog pd;
    public Context progressDialogContext;

    public boolean authenticationError = true;
    public String errorMessage = "Data Corrupted";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alumni_post_job_referal);

        progressDialogContext=this;

        mJobType= (LabelledSpinner) findViewById(R.id.type);
        mCompany = (TextView) findViewById(R.id.company);
        mRole = (TextView) findViewById(R.id.role);
        mDesc = (TextView) findViewById(R.id.desc);

        mJobType.setItemsArray(types);
        mJobType.setOnItemChosenListener(new LabelledSpinner.OnItemChosenListener() {
            @Override
            public void onItemChosen(View labelledSpinner, AdapterView<?> adapterView, View itemView, int position, long id) {
                jobType=types[position];
            }

            @Override
            public void onNothingChosen(View labelledSpinner, AdapterView<?> adapterView) {
                jobType=types[1];
            }
        });

    }

    public void BackButton(View v){
        finish();
    }
    public void Submit(View v){
        company=mCompany.getText().toString();
        role=mRole.getText().toString();
        desc=mDesc.getText().toString();

        NewPost newPost=new NewPost();
        newPost.execute();
    }

    private class NewPost extends AsyncTask<Void, Void, Integer> {

        protected void onPreExecute() {
            pd = new SpotsDialog(progressDialogContext, R.style.CustomPD);
            pd.setTitle("Loading...");
            pd.setCancelable(false);
            pd.show();
        }

        protected Integer doInBackground(Void... params) {
            try {
                java.net.URL url = new URL(Constants.URLs.ALUMNI_BASE + Constants.URLs.ALUMNI_REFER_JOB);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setDoInput(true);
                connection.setDoOutput(true);
                connection.setRequestMethod("POST");
                Log.d("POST", "DATA ready to sent");

                Uri.Builder _data = new Uri.Builder()
                        .appendQueryParameter("token",Constants.SharedPreferenceData.getTOKEN())
                        .appendQueryParameter("type","new")
                        .appendQueryParameter("company",company)
                        .appendQueryParameter("role",role)
                        .appendQueryParameter("jobType",jobType)
                        .appendQueryParameter("desc",desc);

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
                    JSONObject jsonObj = new JSONObject(jsonResults.toString());
                    String status = jsonObj.getString("status");
                    if (status.equals("success")) {
                        authenticationError = false;
                    }else {
                        authenticationError=true;
                        errorMessage=status;
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
                finish();
            } else {
                Toast.makeText(getApplicationContext(),"Your Referal Request is Recieved",Toast.LENGTH_SHORT).show();
                finish();
            }

        }
    }

}
