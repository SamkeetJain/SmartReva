package com.samkeet.smartreva.AlumniCell;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.samkeet.smartreva.Constants;
import com.samkeet.smartreva.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import dmax.dialog.SpotsDialog;

public class AlumniDisscussionReplyActivity extends AppCompatActivity {

    public JSONObject jsonObject;
    public String data;
    public TextView mName, mloc, mTitle, mDesc, mTime, mStars, mReplies;
    public String name, loc, title, desc, time, stars, replies,starStatus,ID;
    public ImageView mImageView;

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    public SpotsDialog pd;
    public Context progressDialogContext;

    public boolean authenticationError = true;
    public String errorMessage = "Data Corupted";

    public JSONObject[] object;
    public String[] nameList,ddateList,descList;

    public EditText mMessage;
    public String message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alumni_disscussion_reply);

        String temp = getIntent().getStringExtra("DATA");
        try {
            jsonObject = new JSONObject(temp);
            name = jsonObject.getString("name");
            loc = jsonObject.getString("loc");
            title = jsonObject.getString("title");
            desc = jsonObject.getString("message");
            time = jsonObject.getString("ddate");
            stars = jsonObject.getString("starscount");
            replies = jsonObject.getString("repliescount");
            starStatus=jsonObject.getString("starStatus");
            ID = jsonObject.getString("Id");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        mName = (TextView) findViewById(R.id.name);
        mloc = (TextView) findViewById(R.id.loc);
        mTitle = (TextView) findViewById(R.id.title);
        mDesc = (TextView) findViewById(R.id.desc);
        mTime = (TextView) findViewById(R.id.time);
        mStars = (TextView) findViewById(R.id.starscount);
        mReplies = (TextView) findViewById(R.id.repiescount);
        mImageView = (ImageView) findViewById(R.id.imageView);

        mMessage = (EditText) findViewById(R.id.messageList);

        mName.setText(name);
        mloc.setText(loc);
        mTitle.setText(title);
        mDesc.setText(desc);
        mTime.setText(time);
        temp = stars+ " Stars";
        mStars.setText(temp);
        temp = replies+ " Replies";
        mReplies.setText(temp);

        if (starStatus.equals("YES")) {
            mImageView.setImageResource(R.drawable.ic_star_10dp);
        } else {
            mImageView.setImageResource(R.drawable.ic_star_border_10dp);
        }

        mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (starStatus.equals("YES")) {
                    starStatus = "NO";
                    mImageView.setImageResource(R.drawable.ic_star_border_10dp);
                    StarDisscussion starDisscussion = new StarDisscussion();
                    starDisscussion.execute();
                } else {
                    starStatus = "YES";
                    mImageView.setImageResource(R.drawable.ic_star_10dp);
                    StarDisscussion starDisscussion = new StarDisscussion();
                    starDisscussion.execute();
                }
            }
        });

        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        progressDialogContext = this;

        GetReplyies getReplyies=new GetReplyies();
        getReplyies.execute();
    }

    public class GetReplyies extends AsyncTask<Void, Void, Integer> {


        protected void onPreExecute() {
            pd = new SpotsDialog(progressDialogContext,R.style.CustomPD);
            pd.setTitle("Loading...");
            pd.setCancelable(false);
            pd.show();
        }

        protected Integer doInBackground(Void... params) {
            try {


                URL url = new URL(Constants.URLs.ALUMNI_BASE + Constants.URLs.ALUMNI_DISSCUSSION_REPLY);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setDoInput(true);
                connection.setDoOutput(true);
                connection.setRequestMethod("POST");

                Uri.Builder _data = new Uri.Builder().appendQueryParameter("token", Constants.SharedPreferenceData.getTOKEN())
                        .appendQueryParameter("type", "get")
                        .appendQueryParameter("dID", ID);
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
                    object = new JSONObject[jsonArray.length()];
                    nameList = new String[jsonArray.length()];
                    ddateList = new String[jsonArray.length()];
                    descList = new String[jsonArray.length()];
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        object[i] = jsonObject;
                        nameList[i] = jsonObject.getString("name");
                        ddateList[i] = jsonObject.getString("ddate");
                        descList[i] = jsonObject.getString("message");
                    }
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
            if (authenticationError) {
                Toast.makeText(getApplicationContext(), errorMessage, Toast.LENGTH_SHORT).show();
            } else {
                mAdapter = new AlumniDisscussionReplyAdapter(nameList, ddateList, descList);
                mRecyclerView.setAdapter(mAdapter);
            }
        }

    }

    public class PutReply extends AsyncTask<Void, Void, Integer> {


        protected void onPreExecute() {
            pd = new SpotsDialog(progressDialogContext,R.style.CustomPD);
            pd.setTitle("Loading...");
            pd.setCancelable(false);
            pd.show();
        }

        protected Integer doInBackground(Void... params) {
            try {


                URL url = new URL(Constants.URLs.ALUMNI_BASE + Constants.URLs.ALUMNI_DISSCUSSION_REPLY);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setDoInput(true);
                connection.setDoOutput(true);
                connection.setRequestMethod("POST");

                Uri.Builder _data = new Uri.Builder().appendQueryParameter("token", Constants.SharedPreferenceData.getTOKEN())
                        .appendQueryParameter("type", "put")
                        .appendQueryParameter("dID", ID)
                        .appendQueryParameter("message",message);
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
                    object = new JSONObject[jsonArray.length()];
                    nameList = new String[jsonArray.length()];
                    ddateList = new String[jsonArray.length()];
                    descList = new String[jsonArray.length()];
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        object[i] = jsonObject;
                        nameList[i] = jsonObject.getString("name");
                        ddateList[i] = jsonObject.getString("ddate");
                        descList[i] = jsonObject.getString("message");
                    }
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
            if (authenticationError) {
                Toast.makeText(getApplicationContext(), errorMessage, Toast.LENGTH_SHORT).show();
            } else {
                mAdapter = new AlumniDisscussionReplyAdapter(nameList, ddateList, descList);
                mRecyclerView.setAdapter(mAdapter);
            }
        }

    }

    public void BackButton(View v){
        finish();
    }

    public void Send(View v){
        message=mMessage.getText().toString().trim();
        mMessage.setText("");
        PutReply putReply = new PutReply();
        putReply.execute();
        InputMethodManager inputManager =(InputMethodManager) getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        inputManager.hideSoftInputFromWindow(this.getCurrentFocus().getWindowToken(),InputMethodManager.HIDE_NOT_ALWAYS);
    }

    public class StarDisscussion extends AsyncTask<Void, Void, Integer> {

        protected void onPreExecute() {
        }

        protected Integer doInBackground(Void... params) {
            try {

                URL url = new URL(Constants.URLs.ALUMNI_BASE + Constants.URLs.ALUMNI_DISSCUSSION_STARS);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setDoInput(true);
                connection.setDoOutput(true);
                connection.setRequestMethod("POST");
                Uri.Builder _data = new Uri.Builder().appendQueryParameter("token", Constants.SharedPreferenceData.getTOKEN())
                        .appendQueryParameter("type", "put")
                        .appendQueryParameter("ID", ID);
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
                return 1;

            } catch (Exception ex) {
                ex.printStackTrace();
            }

            return 1;
        }

        protected void onPostExecute(Integer result) {
        }

    }


}
