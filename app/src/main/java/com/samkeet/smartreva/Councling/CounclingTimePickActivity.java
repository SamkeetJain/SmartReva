package com.samkeet.smartreva.Councling;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
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

public class CounclingTimePickActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    public ProgressDialog pd;
    public Context progressDialogContext;

    public JSONArray jsonArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_councling_time_pick);

        progressDialogContext = this;

        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        final GestureDetector mGestureDetector = new GestureDetector(getApplicationContext(), new GestureDetector.SimpleOnGestureListener() {

            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                return true;
            }

        });

        mRecyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent motionEvent) {

                View child = mRecyclerView.findChildViewUnder(motionEvent.getX(), motionEvent.getY());
                if (child != null && mGestureDetector.onTouchEvent(motionEvent)) {
                    int temp = mRecyclerView.getChildPosition(child);
                    if (Constants.TimeSlots.AVALIBILITY.get(temp).equals("0")) {
                        Intent returnIntent = new Intent();
                        returnIntent.putExtra("result", Constants.TimeSlots.SLOTS.get(temp));

                        setResult(Activity.RESULT_OK, returnIntent);
                        finish();
                    } else {
                        Toast.makeText(getApplicationContext(), "Not Avalible, Please select another", Toast.LENGTH_SHORT).show();
                    }

                }
                return false;
            }

            @Override
            public void onTouchEvent(RecyclerView rv, MotionEvent e) {

            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

            }
        });

        GetResIDs getResIDs = new GetResIDs();
        getResIDs.execute();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent returnIntent = new Intent();
        setResult(Activity.RESULT_CANCELED, returnIntent);
        finish();
    }

    public class GetResIDs extends AsyncTask<Void, Void, Integer> {


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
                URL url = new URL(Constants.URLs.GET_RESERVATION_DETAILS);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setDoInput(true);
                connection.setDoOutput(true);
                connection.setRequestMethod("POST");
                Log.d("POST", "DATA ready to sent");

                Uri.Builder _data = new Uri.Builder().appendQueryParameter("usn", "R14cs171");
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

                // Create a JSON object hierarchy from the results
                jsonArray = new JSONArray(jsonResults.toString());

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

            try {
                Constants.TimeSlots.clearData();
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    String slot = jsonObject.getString("resID");
                    String avalibility = jsonObject.getString("availability");
//                    Constant.TimeSlots.SLOTS.add("" + slot.charAt(9) + slot.charAt(10) + ":00");
                    Constants.TimeSlots.SLOTS.add(slot);
                    Constants.TimeSlots.AVALIBILITY.add(avalibility);
                }
                mAdapter = new CounclingTimePickAdapter(Constants.TimeSlots.SLOTS.toArray(new String[Constants.TimeSlots.SLOTS.size()]), Constants.TimeSlots.AVALIBILITY.toArray(new String[Constants.TimeSlots.AVALIBILITY.size()]));
                mRecyclerView.setAdapter(mAdapter);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
