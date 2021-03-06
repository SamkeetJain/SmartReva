package com.samkeet.smartreva.Library;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

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

public class LibraryMainActivity extends AppCompatActivity {

    public SpotsDialog pd;
    public Context progressDialogContext;

    public String[] mNotesID, mTitle, mMessage, mFilename;
    public JSONObject notesObjects[];

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    public boolean authenticationError;
    public String errorMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_library_main);
        progressDialogContext = this;

        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        if (Constants.Methods.networkState(getApplicationContext(), (ConnectivityManager) getSystemService(getApplicationContext().CONNECTIVITY_SERVICE))) {
            GetNotes getNotes = new GetNotes();
            getNotes.execute();
        }
    }

    public void BackButton(View v) {
        finish();
    }

    private class GetNotes extends AsyncTask<Void, Void, Integer> {


        protected void onPreExecute() {
            pd = new SpotsDialog(progressDialogContext, R.style.CustomPD);
            pd.setTitle("Loading...");
            pd.setCancelable(false);
            pd.show();
        }

        protected Integer doInBackground(Void... params) {
            try {
                String original = "http://revacounselling.16mb.com/notes.php";
                String derived = Constants.URLs.BASE + Constants.URLs.NOTESS;
                URL url = new URL(Constants.URLs.BASE + Constants.URLs.NOTESS);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setDoInput(true);
                connection.setDoOutput(true);
                connection.setRequestMethod("POST");

                Uri.Builder _data = new Uri.Builder().appendQueryParameter("token", Constants.SharedPreferenceData.getTOKEN())
                        .appendQueryParameter("type", "get")
                        .appendQueryParameter("title", "NAN")
                        .appendQueryParameter("message", "NAN")
                        .appendQueryParameter("filename", "NAN");
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

                    JSONArray jsonArray = new JSONArray(jsonResults.toString());
                    mNotesID = new String[jsonArray.length()];
                    mMessage = new String[jsonArray.length()];
                    mFilename = new String[jsonArray.length()];
                    mTitle = new String[jsonArray.length()];
                    notesObjects = new JSONObject[jsonArray.length()];
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        notesObjects[i] = jsonArray.getJSONObject(i);
                        mNotesID[i] = jsonObject.getString("Id");
                        mMessage[i] = jsonObject.getString("message");
                        mTitle[i] = jsonObject.getString("title");
                        mFilename[i] = jsonObject.getString("filename");
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

            mAdapter = new LibraryAdapter(mNotesID, mMessage, mTitle, mFilename);
            mRecyclerView.setAdapter(mAdapter);


        }
    }


}