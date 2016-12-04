package com.samkeet.smartreva.Notes;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
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

public class ViewNotes extends AppCompatActivity {

    public ProgressDialog pd;
    public Context progressDialogContext;

    public String[] mNotesID,mTitle,mMessage,mFilename;
    public JSONObject notesObjects[];

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    public boolean authenticationError;
    public String errorMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_notes);
        progressDialogContext=this;

        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        GetNotes getNotes=new GetNotes();
        getNotes.execute();

    }

    public void BackButton(View v){
        finish();
    }

    private class GetNotes extends AsyncTask<Void, Void, Integer> {


        protected void onPreExecute() {
            pd = new ProgressDialog(progressDialogContext);
            pd.setTitle("Logging...");
            pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            pd.setMessage("Please wait.");
            pd.setCancelable(false);
            pd.setIndeterminate(true);
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
                Log.d("POST", "DATA ready to sent");

                Uri.Builder _data = new Uri.Builder().appendQueryParameter("token", Constants.SharedPreferenceData.getTOKEN())
                        .appendQueryParameter("type","get")
                        .appendQueryParameter("title","NAN")
                        .appendQueryParameter("message","NAN")
                        .appendQueryParameter("filename","NAN");
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

                if(authenticationError) {
                    errorMessage = jsonResults.toString();
                }else {

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

            mAdapter = new ViewNotesAdapter(mNotesID,mMessage,mTitle,mFilename);
            mRecyclerView.setAdapter(mAdapter);



        }
    }


}
