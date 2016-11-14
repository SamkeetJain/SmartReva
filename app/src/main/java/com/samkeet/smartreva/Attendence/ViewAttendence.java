package com.samkeet.smartreva.Attendence;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
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

import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class ViewAttendence extends AppCompatActivity {

    public String token;
    private ProgressDialog pd;
    private Context progressDialogContext;

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    ArrayList<String> list =new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_attendence);
        token = Constants.SharedPreferenceData.getTOKEN();
        progressDialogContext=this;

        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        GetMyAttendence getMyAttendence=new GetMyAttendence();
        getMyAttendence.execute();


    }

    public void BackButton(View v){
        finish();
    }

    private class GetMyAttendence extends AsyncTask<Void, Void, Integer> {

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
                java.net.URL url = new URL(Constants.URLs.GETATTENDENCE);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setDoInput(true);
                connection.setDoOutput(true);
                connection.setRequestMethod("POST");
                Log.d("POST", "DATA ready to sent");

                Uri.Builder _data = new Uri.Builder().appendQueryParameter("token", Constants.SharedPreferenceData.getTOKEN());
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

                JSONObject jsonObject=new JSONObject(jsonResults.toString());
                list.add(jsonObject.getString("btcs14f5100"));
                list.add(jsonObject.getString("btcs14f5200"));
                list.add(jsonObject.getString("btcs14f5300"));
                list.add(jsonObject.getString("btcs14f5400"));
                list.add(jsonObject.getString("btcs14f5500"));
                list.add(jsonObject.getString("btcs14f5650"));
                list.add(jsonObject.getString("btcs14f5660"));
                list.add(jsonObject.getString("btcs14f5700"));
                list.add(jsonObject.getString("btcs14f5800"));

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

            String lists[]=list.toArray(new String[list.size()]);
            mAdapter = new ViewAttendenceAdapter(lists);
            mRecyclerView.setAdapter(mAdapter);

        }
    }

}
