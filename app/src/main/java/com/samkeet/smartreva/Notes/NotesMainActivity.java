package com.samkeet.smartreva.Notes;

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

import com.samkeet.smartreva.Constants;
import com.samkeet.smartreva.R;

import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.File;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import dmax.dialog.SpotsDialog;
import droidninja.filepicker.FilePickerBuilder;
import droidninja.filepicker.FilePickerConst;

public class NotesMainActivity extends AppCompatActivity {

    public Button UploadNotes, ViewNotes;

    private SpotsDialog pd;
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
        if (Constants.Methods.networkState(getApplicationContext(), (ConnectivityManager) getSystemService(getApplicationContext().CONNECTIVITY_SERVICE))) {
            GetFacultyAuthentication getFacultyAuthentication = new GetFacultyAuthentication();
            getFacultyAuthentication.execute();
        }
    }

    public void ViewNotes(View v) {
        Intent intent = new Intent(getApplicationContext(), NotesViewNotes.class);
        startActivity(intent);
    }

    public void BackButton(View v) {
        finish();
    }

    public void NotesUploadSelectFile() {

        FilePickerBuilder.getInstance().setMaxCount(1)
                .setActivityTheme(R.style.FilePickerTheme)
                .pickDocument(this);

//        Intent intent = new Intent();
//        intent.setType("*/*");
//        intent.setAction(Intent.ACTION_GET_CONTENT);
//        startActivityForResult(Intent.createChooser(intent, "Select a File"), 101);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 101) {
//            if (requestCode == RESULT_OK) {
            Uri post = data.getData();
            File postFile = new File(post.getPath());
            String loc=postFile.getAbsolutePath();
            Intent intent = new Intent(getApplicationContext(), NotesUploadToServer.class);
            intent.putExtra("POST",loc);
            startActivity(intent);
        }
        if(requestCode == FilePickerConst.REQUEST_CODE){
            if(resultCode==RESULT_OK && data!=null)
            {
                ArrayList<String> filePaths = data.getStringArrayListExtra(FilePickerConst.KEY_SELECTED_PHOTOS);
                String filepath = filePaths.get(0);
                Intent intent = new Intent(getApplicationContext(), NotesUploadToServer.class);
                intent.putExtra("POST",filepath);
                startActivity(intent);
            }
        }
//        }
    }

    private class GetFacultyAuthentication extends AsyncTask<Void, Void, Integer> {


        protected void onPreExecute() {
            pd = new SpotsDialog(progressDialogContext,R.style.CustomPD);
            pd.setTitle("Loading...");
            pd.setCancelable(false);
            pd.show();
        }

        protected Integer doInBackground(Void... params) {
            try {
                java.net.URL url = new URL("http://revacounselling.16mb.com/authenticaton.php");
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
                    JSONObject jsonObject = new JSONObject(jsonResults.toString());
                    results = jsonObject.getString("status");
                    if (!results.equals("success")) {
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
                NotesUploadSelectFile();
            }

        }
    }

}
