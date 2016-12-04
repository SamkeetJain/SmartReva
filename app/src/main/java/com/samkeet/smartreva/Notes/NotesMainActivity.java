package com.samkeet.smartreva.Notes;

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

import com.samkeet.smartreva.Attendence.ViewAttendence;
import com.samkeet.smartreva.Constants;
import com.samkeet.smartreva.R;

import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class NotesMainActivity extends AppCompatActivity {

    public Button UploadNotes, ViewNotes;

    private ProgressDialog pd;
    private Context progressDialogContext;

    String results;

    public boolean authenticationError;
    public String errorMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes_main);
        progressDialogContext = this;

        UploadNotes = (Button) findViewById(R.id.upload_notes_button);
        ViewNotes = (Button) findViewById(R.id.view_notes_button);
    }

    public void UploadNotes(View v) {

        GetFacultyAuthentication getFacultyAuthentication = new GetFacultyAuthentication();
        getFacultyAuthentication.execute();
    }

    public void ViewNotes(View v) {
        Intent intent = new Intent(getApplicationContext(), ViewNotes.class);
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
                java.net.URL url = new URL("http://revacounselling.16mb.com/authenticaton.php");
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
                if (authenticationError) {
                    errorMessage = jsonResults.toString();
                } else {
                    JSONObject jsonObject = new JSONObject(jsonResults.toString());
                    results = jsonObject.getString("status");
                    if(!results.equals("success")){
                        authenticationError = true;
                        errorMessage = results;
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
                Intent intent = new Intent(getApplicationContext(), UploadNotes.class);
                startActivity(intent);
            }

        }
    }

}
