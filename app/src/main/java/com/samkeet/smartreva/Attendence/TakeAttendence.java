package com.samkeet.smartreva.Attendence;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.samkeet.smartreva.Constants;
import com.samkeet.smartreva.R;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;

import dmax.dialog.SpotsDialog;

public class TakeAttendence extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private Context progressDialogContext;
    public SpotsDialog pd;

    public String class_code, subject_code;
    public String studentsList[];

    public String datetext;
    public TextView dateView;
    public EditText period;
    public String finalValues, finalFields;

    public boolean authenticationError;
    public String errorMessage;
    public String periodNo;
    String res;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_attendence);

        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        progressDialogContext = this;
        dateView = (TextView) findViewById(R.id.date);
        period = (EditText) findViewById((R.id.period));

        class_code = getIntent().getStringExtra("CLASSCODE");
        subject_code = getIntent().getStringExtra("SUBJECTCODE");

        if(Constants.Methods.networkState(getApplicationContext(), (ConnectivityManager) getSystemService(getApplicationContext().CONNECTIVITY_SERVICE))) {
            GetStudentsList getStudentsList = new GetStudentsList();
            getStudentsList.execute();
        }

    }

    public void BackButton(View v) {
        finish();
    }

    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        String temp;
        if (dayOfMonth < 10) {
            temp = "0" + dayOfMonth;
        } else {
            temp = "" + dayOfMonth;
        }
        String date = "" + temp + "-" + (++monthOfYear) + "-" + year;
        dateView.setText(date);
    }

    private class GetStudentsList extends AsyncTask<Void, Void, Integer> {


        protected void onPreExecute() {
            pd = new SpotsDialog(progressDialogContext,R.style.CustomPD);
            pd.setTitle("Loading...");
            pd.setCancelable(false);
            pd.show();
        }

        protected Integer doInBackground(Void... params) {
            try {
                java.net.URL url = new URL(Constants.URLs.BASE + Constants.URLs.GET_STUDENTLIST);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setDoInput(true);
                connection.setDoOutput(true);
                connection.setRequestMethod("POST");
                Log.d("POST", "DATA ready to sent");

                Uri.Builder _data = new Uri.Builder().appendQueryParameter("classCode", class_code).appendQueryParameter("token",Constants.SharedPreferenceData.getTOKEN());
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
                    JSONArray jsonArray = new JSONArray(jsonResults.toString());
                    studentsList = new String[jsonArray.length()];
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        studentsList[i] = jsonObject.getString("UserID");
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

                mAdapter = new TakeAttendenceAdapter(studentsList);
                mRecyclerView.setAdapter(mAdapter);
            }


        }
    }

    public void Submit(View v) {
        periodNo = period.getText().toString();
        datetext = dateView.getText().toString();
        boolean check[] = ((TakeAttendenceAdapter) mAdapter).getCheckBoxes();
        String values[] = new String[check.length];
        for (int i = 0; i < check.length; i++) {
            if (check[i] == true) {
                values[i] = "P";
            } else if (check[i] == false) {
                values[i] = "A";
            }
        }
        String[] fields = ((TakeAttendenceAdapter) mAdapter).getTitles();
        String temp = fields[0];
        for (int i = 1; i < fields.length; i++) {
            temp = temp.concat("|" + fields[i]);
        }
        finalFields = temp;
        temp = values[0];
        for (int i = 1; i < values.length; i++) {
            temp = temp.concat("|" + values[i]);
        }
        finalValues = temp;


        InsertAttendence insertAttendence = new InsertAttendence();
        insertAttendence.execute();

    }

    public void Date(View v) {
        Calendar now = Calendar.getInstance();
        DatePickerDialog dpd = DatePickerDialog.newInstance(
                TakeAttendence.this,
                now.get(Calendar.YEAR),
                now.get(Calendar.MONTH),
                now.get(Calendar.DAY_OF_MONTH)
        );
        Calendar minDate = Calendar.getInstance();
        minDate.set(now.get(Calendar.YEAR), now.get(Calendar.MONTH), now.get(Calendar.DAY_OF_MONTH));
        dpd.setMinDate(minDate);
        dpd.show(getFragmentManager(), "Datepickerdialog");
    }

    private class InsertAttendence extends AsyncTask<Void, Void, Integer> {


        protected void onPreExecute() {
            pd = new SpotsDialog(progressDialogContext);
            pd.setTitle("Loading...");
            pd.setCancelable(false);
            pd.show();
        }

        protected Integer doInBackground(Void... params) {
            try {
                java.net.URL url = new URL(Constants.URLs.BASE + Constants.URLs.PUT_ATTENDANCE);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setDoInput(true);
                connection.setDoOutput(true);
                connection.setRequestMethod("POST");
                Log.d("POST", "DATA ready to sent");

                Uri.Builder _data = new Uri.Builder().appendQueryParameter("token", Constants.SharedPreferenceData.getTOKEN()).appendQueryParameter("period", periodNo).appendQueryParameter("subjectCode", subject_code).appendQueryParameter("ddate", datetext).appendQueryParameter("classCode",class_code).appendQueryParameter("statusList", finalValues).appendQueryParameter("userIDList", finalFields);
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
                    JSONObject jsonObject=new JSONObject(jsonResults.toString());
                    res=jsonObject.getString("status");
                    if(!res.equals("success")) {
                        authenticationError=true;
                        errorMessage=res;
                    }
                }


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
                    Toast.makeText(getApplicationContext(), "Attendence Uploaded", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
    }


}
