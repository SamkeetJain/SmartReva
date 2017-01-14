package com.samkeet.smartreva.Notes;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.crash.FirebaseCrash;
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
import java.util.ArrayList;

import dmax.dialog.SpotsDialog;

public class NotesViewNotes extends AppCompatActivity {

    public SpotsDialog pd;
    public Context progressDialogContext;

    public String[] mTitle, mMessage;
    public JSONObject notesObjects[];

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    public boolean authenticationError=true;
    public String errorMessage="Data Corrupted";
    public String[] courseList = {"course"};
    public String[] semList = {"One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten"};
    public String[] deptList = {"deptCode"};
    public Spinner courseSpin, deptSpin, semSpin;
    public String course, deptCode, sem;
    public JSONObject[] objects;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes_view_notes);
        progressDialogContext = this;

        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        courseSpin = (Spinner) findViewById(R.id.courseSpin);
        deptSpin = (Spinner) findViewById(R.id.deptSpin);
        semSpin = (Spinner) findViewById(R.id.SemSpin);

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
                    Intent intent = new Intent(getApplicationContext(), NotesManager.class);
                    intent.putExtra("DATA", notesObjects[temp].toString());
                    startActivity(intent);

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

        initSpinner();

        if (Constants.Methods.networkState(getApplicationContext(), (ConnectivityManager) getSystemService(getApplicationContext().CONNECTIVITY_SERVICE))) {
            GetCourseAndDeptDetails getCourseAndDeptDetails = new GetCourseAndDeptDetails();
            getCourseAndDeptDetails.execute();
        }
    }

    public void initSpinner() {

        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, courseList);
        courseSpin.setAdapter(adapter1);
        courseSpin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                course = courseList[position];
                if(!course.equals("course")){
                    generateDeptList(course);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, deptList);
        deptSpin.setAdapter(adapter2);
        deptSpin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                deptCode = deptList[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, semList);
        semSpin.setAdapter(adapter3);
        semSpin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                sem = semList[position];

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    public void generateDeptList(String courseTemp){
        ArrayList<String> temp=new ArrayList<String>();
        for(int i=0;i<objects.length;i++){
            String c=null,d=null;
            try {
                c=objects[i].getString("course");
                d=objects[i].getString("department");
            } catch (JSONException e) {
                e.printStackTrace();
                FirebaseCrash.report(new Exception(e));
            }
            if (c.equals(courseTemp)){
                temp.add(d);
            }
        }
        deptList=null;
        deptList=temp.toArray(new String[temp.size()]);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, deptList);
        deptSpin.setAdapter(adapter2);

    }

    public void generateCourseList() {

        ArrayList<String> temp = new ArrayList<String>();
        for(int i=0;i<objects.length;i++){
            boolean exist=false;
            String coursetemp= null;
            try {
                coursetemp = objects[i].getString("course");
            } catch (JSONException e) {
                e.printStackTrace();
                FirebaseCrash.report(new Exception(e));
            }
            for(int j=0;j<temp.size();j++){
                if(temp.get(j).equals(coursetemp)){
                    exist=true;
                    break;
                }
            }
            if (!exist){
                temp.add(coursetemp);
            }
        }
        courseList=null;
        courseList=temp.toArray(new String[temp.size()]);

        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, courseList);
        courseSpin.setAdapter(adapter1);

    }

    public void BackButton(View v) {
        finish();
    }

    private class GetNotes extends AsyncTask<Void, Void, Integer> {


        protected void onPreExecute() {
            pd = new SpotsDialog(progressDialogContext, R.style.CustomPD);
            pd.setTitle("Logging...");
            pd.setCancelable(false);
            pd.show();
        }

        protected Integer doInBackground(Void... params) {
            try {
                URL url = new URL(Constants.URLs.BASE + Constants.URLs.NOTESS);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setDoInput(true);
                connection.setDoOutput(true);
                connection.setRequestMethod("POST");
                Log.d("POST", "DATA ready to sent");

                Uri.Builder _data = new Uri.Builder().appendQueryParameter("token", Constants.SharedPreferenceData.getTOKEN())
                        .appendQueryParameter("type", "get")
                        .appendQueryParameter("title", "NAN")
                        .appendQueryParameter("message", "NAN")
                        .appendQueryParameter("filename", "NAN")
                        .appendQueryParameter("course", course)
                        .appendQueryParameter("sem",sem)
                        .appendQueryParameter("deptCode", deptCode);
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
                    mMessage = new String[jsonArray.length()];
                    mTitle = new String[jsonArray.length()];
                    notesObjects = new JSONObject[jsonArray.length()];
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        notesObjects[i] = jsonArray.getJSONObject(i);
                        mMessage[i] = jsonObject.getString("message");
                        mTitle[i] = jsonObject.getString("title");
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
                mAdapter = new NotesViewNotesAdapter(mTitle, mMessage);
                mRecyclerView.setAdapter(mAdapter);
            }
        }
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

    public void submit(View v) {
        if (course==null || course.equals("course")){
            Toast.makeText(getApplicationContext(), "Please select valid Course...",Toast.LENGTH_SHORT).show();
            return;
        }
        else if (deptCode==null || deptCode.equals("deptCode")){
            Toast.makeText(getApplicationContext(), "Please select valid Department Code...",Toast.LENGTH_SHORT).show();
            return;
        }
        else if (Constants.Methods.networkState(getApplicationContext(), (ConnectivityManager) getSystemService(getApplicationContext().CONNECTIVITY_SERVICE))) {
            GetNotes getNotes = new GetNotes();
            getNotes.execute();
        }
    }


}
