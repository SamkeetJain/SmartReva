package com.samkeet.smartreva.Councling;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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
import java.text.SimpleDateFormat;
import java.util.Date;

import dmax.dialog.SpotsDialog;

public class CounclingNewPost extends AppCompatActivity {

    public EditText mTitle, mDesc;
    public SpotsDialog pd;
    public Context progressDialogContext;

    public String title, desc, datetext;
    public String responce;

    public boolean authenticationError;
    public String errorMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_councling_new_post);

        progressDialogContext = this;

        mTitle = (EditText) findViewById(R.id.title);
        mDesc = (EditText) findViewById(R.id.message);


    }

    public void BackButton(View v) {
        finish();
    }

    public void Send(View v) {
        desc = mDesc.getText().toString();
        title = mTitle.getText().toString();
        datetext = new SimpleDateFormat("dd-MM-yyyy").format(new Date());

        if (validation()) {
            if (Constants.Methods.networkState(getApplicationContext(), (ConnectivityManager) getSystemService(getApplicationContext().CONNECTIVITY_SERVICE))) {
                SendNewPost sendNewPost = new SendNewPost();
                sendNewPost.execute();
            }
        }
    }

    public boolean validation() {
        datetext = datetext.trim();
        desc = desc.trim();
        title = title.trim();

        if (Constants.Methods.checkForSpecial(datetext)) {
            Toast.makeText(getApplicationContext(), "Please remove special charecters in date field", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (Constants.Methods.checkForSpecial(desc)) {
            Toast.makeText(getApplicationContext(), "Please remove special charecters in description field", Toast.LENGTH_SHORT).show();
            return false;
        }


        if (!((datetext.length() <= 20) && (datetext.length() >= 1))) {
            Toast.makeText(getApplicationContext(), "Title should be less than 20 charecters", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (!((desc.length() <= 1000) && (desc.length() >= 1))) {
            Toast.makeText(getApplicationContext(), "Message should be less than 1000 charecters", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (!((title.length() <= 60) && (title.length() >= 1))) {
            Toast.makeText(getApplicationContext(), "Message should be less than 60 charecters", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    public class SendNewPost extends AsyncTask<Void, Void, Integer> {


        protected void onPreExecute() {
            pd = new SpotsDialog(progressDialogContext, R.style.CustomPD);
            pd.setTitle("Loading...");
            pd.setCancelable(false);
            pd.show();
        }

        protected Integer doInBackground(Void... params) {
            try {

                URL url = new URL(Constants.URLs.BASE + Constants.URLs.COUNC_WALL);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setDoInput(true);
                connection.setDoOutput(true);
                connection.setRequestMethod("POST");
                Log.d("POST", "DATA ready to sent");

                Uri.Builder _data = new Uri.Builder().appendQueryParameter("token", Constants.SharedPreferenceData.getTOKEN())
                        .appendQueryParameter("type", "add")
                        .appendQueryParameter("postTime", datetext)
                        .appendQueryParameter("title", title)
                        .appendQueryParameter("message", desc);
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
                    JSONObject jsonObject = new JSONObject(jsonResults.toString());
                    responce = jsonObject.getString("status");
                    if (!responce.equals("success")) {
                        authenticationError = true;
                        errorMessage = responce;
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
                Toast.makeText(getApplicationContext(), responce, Toast.LENGTH_SHORT).show();
                finish();
            }

        }
    }
}
