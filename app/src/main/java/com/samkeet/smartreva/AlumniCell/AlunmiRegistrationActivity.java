package com.samkeet.smartreva.AlumniCell;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.crash.FirebaseCrash;
import com.samkeet.smartreva.Constants;
import com.samkeet.smartreva.R;
import com.satsuware.usefulviews.LabelledSpinner;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import dmax.dialog.SpotsDialog;

public class AlunmiRegistrationActivity extends AppCompatActivity {

    public SpotsDialog pd;
    public Context progressDialogContext;

    public boolean authenticationError = true;
    public String errorMessage = "Data Corrupted";

    public String course, dept, year;
    public JSONObject[] objects;

    public LabelledSpinner Gcourse, Gdept, Gyog;
    public String[] courseList = {"course"};
    public String[] years = {"1990", "1991", "1992", "1993", "1994", "1995", "1996", "1997", "1998", "1999", "2000","2001", "2002", "2003", "2004", "2005", "2006", "2007", "2008", "2009", "2010", "2011","2012","2013","2014","2015","2016","2017","2018","2019","2020"};
    public String[] deptList = {"deptCode"};

    public EditText mMobileNo,mPassword,mFullname,mEmail,mSRN,mCompany,mDesg,mLoc;
    public String mobileNo,password,fullname,email,srn,company,desg,loc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alunmi_registration);
        progressDialogContext = this;

        mMobileNo = (EditText) findViewById(R.id.mobileno);
        mPassword = (EditText) findViewById(R.id.password);
        mFullname = (EditText) findViewById(R.id.name);
        mEmail = (EditText) findViewById(R.id.email);
        mSRN = (EditText) findViewById(R.id.srn);
        mCompany= (EditText) findViewById(R.id.company);
        mDesg= (EditText) findViewById(R.id.designation);
        mLoc= (EditText) findViewById(R.id.loc);

        Gcourse = (LabelledSpinner) findViewById(R.id.Gcourse);
        Gdept = (LabelledSpinner) findViewById(R.id.Gdept);
        Gyog = (LabelledSpinner) findViewById(R.id.Gyog);

        initSpinner();

        if (Constants.Methods.networkState(getApplicationContext(), (ConnectivityManager) getSystemService(getApplicationContext().CONNECTIVITY_SERVICE))) {
            GetCourseAndDeptDetails getCourseAndDeptDetails = new GetCourseAndDeptDetails();
            getCourseAndDeptDetails.execute();
        }

    }

    public void initSpinner() {

        Gcourse.setItemsArray(courseList);
        Gcourse.setOnItemChosenListener(new LabelledSpinner.OnItemChosenListener() {
            @Override
            public void onItemChosen(View labelledSpinner, AdapterView<?> adapterView, View itemView, int position, long id) {
                course = courseList[position];
                if (!course.equals("course")) {
                    generateDeptList(course);
                }
            }

            @Override
            public void onNothingChosen(View labelledSpinner, AdapterView<?> adapterView) {

            }
        });

        Gdept.setItemsArray(deptList);
        Gdept.setOnItemChosenListener(new LabelledSpinner.OnItemChosenListener() {
            @Override
            public void onItemChosen(View labelledSpinner, AdapterView<?> adapterView, View itemView, int position, long id) {
                dept = deptList[position];
            }

            @Override
            public void onNothingChosen(View labelledSpinner, AdapterView<?> adapterView) {

            }
        });

        Gyog.setItemsArray(years);
        Gyog.setOnItemChosenListener(new LabelledSpinner.OnItemChosenListener() {
            @Override
            public void onItemChosen(View labelledSpinner, AdapterView<?> adapterView, View itemView, int position, long id) {
                year = years[position];
            }

            @Override
            public void onNothingChosen(View labelledSpinner, AdapterView<?> adapterView) {

            }
        });
    }

    public void BackButton(View v) {
        finish();
    }

    public void Send(View v) {
        mobileNo = mMobileNo.getText().toString();
        password = mPassword.getText().toString();
        fullname = mFullname.getText().toString();
        email = mEmail.getText().toString();
        srn = mSRN.getText().toString();
        company=mCompany.getText().toString();
        desg=mDesg.getText().toString();
        loc=mLoc.getText().toString();

        Registration registration = new Registration();
        registration.execute();
    }

    private class GetCourseAndDeptDetails extends AsyncTask<Void, Void, Integer> {

        protected void onPreExecute() {
            pd = new SpotsDialog(progressDialogContext, R.style.CustomPD);
            pd.setTitle("Loading...");
            pd.setCancelable(false);
            pd.show();
        }

        protected Integer doInBackground(Void... params) {
            try {
                java.net.URL url = new URL(Constants.URLs.BASE + Constants.URLs.COURSE_DEPT);
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
                    objects = new JSONObject[jsonArray.length()];
                    for (int i = 0; i < jsonArray.length(); i++) {
                        objects[i] = jsonArray.getJSONObject(i);
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
                generateCourseList();
            }

        }
    }

    public void generateCourseList() {

        ArrayList<String> temp = new ArrayList<String>();
        for (int i = 0; i < objects.length; i++) {
            boolean exist = false;
            String coursetemp = null;
            try {
                coursetemp = objects[i].getString("course");
            } catch (JSONException e) {
                e.printStackTrace();
                FirebaseCrash.report(new Exception(e));
            }
            for (int j = 0; j < temp.size(); j++) {
                if (temp.get(j).equals(coursetemp)) {
                    exist = true;
                    break;
                }
            }
            if (!exist) {
                temp.add(coursetemp);
            }
        }
        courseList = null;
        courseList = temp.toArray(new String[temp.size()]);

        Gcourse.setItemsArray(courseList);
    }

    public void generateDeptList(String courseTemp) {
        ArrayList<String> temp = new ArrayList<String>();
        for (int i = 0; i < objects.length; i++) {
            String c = null, d = null;
            try {
                c = objects[i].getString("course");
                d = objects[i].getString("department");
            } catch (JSONException e) {
                e.printStackTrace();
                FirebaseCrash.report(new Exception(e));
            }
            if (c.equals(courseTemp)) {
                temp.add(d);
            }
        }
        deptList = null;
        deptList = temp.toArray(new String[temp.size()]);
        Gdept.setItemsArray(deptList);
    }

    private class Registration extends AsyncTask<Void, Void, Integer> {

        protected void onPreExecute() {
            pd = new SpotsDialog(progressDialogContext, R.style.CustomPD);
            pd.setTitle("Loading...");
            pd.setCancelable(false);
            pd.show();
        }

        protected Integer doInBackground(Void... params) {
            try {
                java.net.URL url = new URL(Constants.URLs.BASE + Constants.URLs.ALUMNI_REG);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setDoInput(true);
                connection.setDoOutput(true);
                connection.setRequestMethod("POST");
                Log.d("POST", "DATA ready to sent");

                Uri.Builder _data = new Uri.Builder()
                        .appendQueryParameter("mobileNo",mobileNo)
                        .appendQueryParameter("password",password)
                        .appendQueryParameter("name",fullname)
                        .appendQueryParameter("email",email)
                        .appendQueryParameter("srn",srn)
                        .appendQueryParameter("course",course)
                        .appendQueryParameter("dept",dept)
                        .appendQueryParameter("yog",year)
                        .appendQueryParameter("company",company)
                        .appendQueryParameter("desg",desg)
                        .appendQueryParameter("loc",loc);

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
                    JSONObject jsonObj = new JSONObject(jsonResults.toString());
                    String status = jsonObj.getString("status");
                    if (status.equals("success")) {
                        authenticationError = false;
                    }else {
                        authenticationError=true;
                        errorMessage=status;
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
                Toast.makeText(getApplicationContext(),"Your Registration Request is Recieved",Toast.LENGTH_SHORT).show();
            }

        }
    }



}
