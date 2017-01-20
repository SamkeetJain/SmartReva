
package com.samkeet.smartreva.Timetable;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TableRow;
import android.widget.TextView;
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
import java.util.ArrayList;

import dmax.dialog.SpotsDialog;

public class TimetableMainActivity extends AppCompatActivity {

    public Spinner daySpinner;
    public EditText mClassCode;
    public String day, classCode;
    public String dayItems[] = new String[]{"Monday", "Tuesday", "Wednesday", "Thusday", "Friday", "Saturday"};

    public SpotsDialog pd;
    public Context progressDialogContext;
    public boolean authenticationError = true;
    public String errorMessage = "Data Corrupted";

    public LinearLayout TimetableLayout;

    public ArrayList<String> tableTimings = new ArrayList<String>();
    public ArrayList<String> tableClasses = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timetable_main);

        progressDialogContext=this;

        mClassCode = (EditText) findViewById(R.id.classCode);
        daySpinner = (Spinner) findViewById(R.id.dayspinner);
        TimetableLayout = (LinearLayout) findViewById(R.id.TimetableLayout);
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, dayItems);
        daySpinner.setAdapter(adapter1);
        daySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                day = (position + 1) + "";
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


    }

    public void generateView() {



        for (int i = 0; i < tableClasses.size(); i++) {

            LinearLayout blockLinearLayout = new LinearLayout(this);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            params.setMargins(25, 15, 15,0);
            blockLinearLayout.setLayoutParams(params);
            blockLinearLayout.setOrientation(LinearLayout.HORIZONTAL);

            TextView timeView = new TextView(this);
            timeView.setLayoutParams(new TableRow.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT,1.0f));
            timeView.setTextSize(16);
            timeView.setGravity(Gravity.CENTER);
            timeView.setText(tableTimings.get(i));
            timeView.setTextColor(Color.parseColor("#000000"));

            TextView classView = new TextView(this);
            classView.setLayoutParams(new TableRow.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT,1.0f));
            classView.setTextSize(16);
            classView.setGravity(Gravity.CENTER);
            classView.setText(tableClasses.get(i));
            classView.setTextColor(Color.parseColor("#000000"));

            blockLinearLayout.addView(timeView);
            blockLinearLayout.addView(classView);

            TimetableLayout.addView(blockLinearLayout);
        }

    }

    public void BackButton(View v) {
        finish();
    }

    public void Send(View v){
        TimetableLayout.removeAllViews();
        TimetableLayout.removeAllViewsInLayout();
        TimetableLayout.refreshDrawableState();
        tableTimings.clear();
        tableClasses.clear();
        classCode=mClassCode.getText().toString();
        GetTimeTable getTimeTable= new GetTimeTable();
        getTimeTable.execute();
    }

    private class GetTimeTable extends AsyncTask<Void, Void, Integer> {


        protected void onPreExecute() {
            pd = new SpotsDialog(progressDialogContext, R.style.CustomPD);
            pd.setTitle("Loading...");
            pd.setCancelable(false);
            pd.show();
        }

        protected Integer doInBackground(Void... params) {
            try {
                URL url = new URL(Constants.URLs.BASE + Constants.URLs.GET_TIMETABLE);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setDoInput(true);
                connection.setDoOutput(true);
                connection.setRequestMethod("POST");

                Uri.Builder _data = new Uri.Builder().appendQueryParameter("token", Constants.SharedPreferenceData.getTOKEN())
                        .appendQueryParameter("tableName", classCode)
                        .appendQueryParameter("dayOfWeek", day);
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
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        if (!jsonObject.getString("p1").equals("na")) {
                            tableClasses.add(jsonObject.getString("p1"));
                            tableTimings.add(jsonObject.getString("p1t"));
                        }
                        if (!jsonObject.getString("p2").equals("na")) {
                            tableClasses.add(jsonObject.getString("p2"));
                            tableTimings.add(jsonObject.getString("p2t"));
                        }
                        if (!jsonObject.getString("p3").equals("na")) {
                            tableClasses.add(jsonObject.getString("p3"));
                            tableTimings.add(jsonObject.getString("p3t"));
                        }
                        if (!jsonObject.getString("p4").equals("na")) {
                            tableClasses.add(jsonObject.getString("p4"));
                            tableTimings.add(jsonObject.getString("p4t"));
                        }
                        if (!jsonObject.getString("p5").equals("na")) {
                            tableClasses.add(jsonObject.getString("p5"));
                            tableTimings.add(jsonObject.getString("p5t"));
                        }
                        if (!jsonObject.getString("p6").equals("na")) {
                            tableClasses.add(jsonObject.getString("p6"));
                            tableTimings.add(jsonObject.getString("p6t"));
                        }
                        if (!jsonObject.getString("p7").equals("na")) {
                            tableClasses.add(jsonObject.getString("p7"));
                            tableTimings.add(jsonObject.getString("p7t"));
                        }
                        if (!jsonObject.getString("p8").equals("na")) {
                            tableClasses.add(jsonObject.getString("p8"));
                            tableTimings.add(jsonObject.getString("p8t"));
                        }
                        if (!jsonObject.getString("p9").equals("na")) {
                            tableClasses.add(jsonObject.getString("p9"));
                            tableTimings.add(jsonObject.getString("p9t"));
                        }


                    }
                    authenticationError = false;
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
                generateView();
            }
        }
    }

}
