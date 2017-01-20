package com.samkeet.smartreva.Placement;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.samkeet.smartreva.Constants;
import com.samkeet.smartreva.R;

import org.json.JSONArray;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import dmax.dialog.SpotsDialog;

public class PlacementRegForm2 extends AppCompatActivity {

    public SpotsDialog pd;
    public Context progressDialogContext;
    public boolean authenticationError = true;
    public String errorMessage = "Data Courpted";

    public Spinner passingyear[];
    public String course, semister, py[], s[];
    public LinearLayout passingLayout[];
    public String passingYears[];
    public TextView courseTitle;
    public Spinner deptSpinner;
    public String deptartment;
    public ArrayList<String> deptList = new ArrayList<String>();
    public EditText score[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_placement_reg_form2);
        progressDialogContext = this;

        course = getIntent().getStringExtra("COURSE");
        semister = getIntent().getStringExtra("SEMESTER");

        passingyear = new Spinner[10];
        score = new EditText[10];
        s = new String[10];
        py = new String[10];
        passingLayout = new LinearLayout[10];

        setFindViewById();

        courseTitle.setText(course.toUpperCase());

        passingYears = Constants.passingYears;
        for (int i = 0; i < passingYears.length; i++) {
            passingYears[i] = passingYears[i].toUpperCase();
        }

        GetCourseDetails getCourseDetails = new GetCourseDetails();
        getCourseDetails.execute();

        initSpinner();

        int counter = Integer.valueOf(semister);
        for (int i = counter; i < 10; i++) {
            passingLayout[i].setVisibility(View.GONE);
        }
    }

    public void sendData() {
        for (int i = 0; i < Integer.valueOf(semister); i++) {
            s[i] = score[i].getText().toString();
        }
        for (int i = Integer.valueOf(semister); i < 10; i++) {
            s[i] = "NA";
            py[i] = "NA";
        }
        for (int i = 0; i < 10; i++) {
            if (s[i].length() == 0) {
                s[i] = "NA";
                py[i] = "NA";
            }
        }

    }

    public void setFindViewById() {

        score[0] = (EditText) findViewById(R.id.s1);
        score[1] = (EditText) findViewById(R.id.s2);
        score[2] = (EditText) findViewById(R.id.s3);
        score[3] = (EditText) findViewById(R.id.s4);
        score[4] = (EditText) findViewById(R.id.s5);
        score[5] = (EditText) findViewById(R.id.s6);
        score[6] = (EditText) findViewById(R.id.s7);
        score[7] = (EditText) findViewById(R.id.s8);
        score[8] = (EditText) findViewById(R.id.s9);
        score[9] = (EditText) findViewById(R.id.s10);

        deptSpinner = (Spinner) findViewById(R.id.depttitle);

        courseTitle = (TextView) findViewById(R.id.coursetitle);

        passingyear[0] = (Spinner) findViewById(R.id.spinner0);
        passingyear[1] = (Spinner) findViewById(R.id.spinner1);
        passingyear[2] = (Spinner) findViewById(R.id.spinner2);
        passingyear[3] = (Spinner) findViewById(R.id.spinner3);
        passingyear[4] = (Spinner) findViewById(R.id.spinner4);
        passingyear[5] = (Spinner) findViewById(R.id.spinner5);
        passingyear[6] = (Spinner) findViewById(R.id.spinner6);
        passingyear[7] = (Spinner) findViewById(R.id.spinner7);
        passingyear[8] = (Spinner) findViewById(R.id.spinner8);
        passingyear[9] = (Spinner) findViewById(R.id.spinner9);

        passingLayout[0] = (LinearLayout) findViewById(R.id.slab0);
        passingLayout[1] = (LinearLayout) findViewById(R.id.slab1);
        passingLayout[2] = (LinearLayout) findViewById(R.id.slab2);
        passingLayout[3] = (LinearLayout) findViewById(R.id.slab3);
        passingLayout[4] = (LinearLayout) findViewById(R.id.slab4);
        passingLayout[5] = (LinearLayout) findViewById(R.id.slab5);
        passingLayout[6] = (LinearLayout) findViewById(R.id.slab6);
        passingLayout[7] = (LinearLayout) findViewById(R.id.slab7);
        passingLayout[8] = (LinearLayout) findViewById(R.id.slab8);
        passingLayout[9] = (LinearLayout) findViewById(R.id.slab9);
    }

    public void initSpinner() {

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, passingYears);

        passingyear[0].setPrompt("Select one");

        passingyear[0].setAdapter(adapter);
        passingyear[0].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                py[0] = Constants.passingYears[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        passingyear[1].setAdapter(adapter);
        passingyear[1].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                py[1] = Constants.passingYears[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        passingyear[2].setAdapter(adapter);
        passingyear[2].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                py[2] = Constants.passingYears[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        passingyear[3].setAdapter(adapter);
        passingyear[3].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                py[3] = Constants.passingYears[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        passingyear[4].setAdapter(adapter);
        passingyear[4].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                py[4] = Constants.passingYears[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        passingyear[5].setAdapter(adapter);
        passingyear[5].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                py[5] = Constants.passingYears[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        passingyear[6].setAdapter(adapter);
        passingyear[6].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                py[6] = Constants.passingYears[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        passingyear[7].setAdapter(adapter);
        passingyear[7].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                py[7] = Constants.passingYears[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        passingyear[8].setAdapter(adapter);
        passingyear[8].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                py[8] = Constants.passingYears[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        passingyear[9].setAdapter(adapter);
        passingyear[9].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                py[9] = Constants.passingYears[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


    }

    public void initDeptSpinner() {
        final String list[] = deptList.toArray(new String[deptList.size()]);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, list);

        deptSpinner.setPrompt("Select one");

        deptSpinner.setAdapter(adapter);
        deptSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                deptartment = list[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    public void BackButton(View v) {
        finish();
    }

    public void submit(View v) {
        sendData();
        authenticationError = true;
        errorMessage = "Data Courpted";
        SendUGDetails sendUGDetails = new SendUGDetails();
        sendUGDetails.execute();
    }

    private class GetCourseDetails extends AsyncTask<Void, Void, Integer> {

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
                Uri.Builder _data = new Uri.Builder().appendQueryParameter("token", Constants.SharedPreferenceData.getTOKEN());
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
                        String coursetemp = jsonObject.getString("course");
                        if (coursetemp.equals(course)) {
                            String dept = jsonObject.getString("department");
                            deptList.add(dept);
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
                initDeptSpinner();
            }

        }
    }

    private class SendUGDetails extends AsyncTask<Void, Void, Integer> {

        protected void onPreExecute() {
            pd = new SpotsDialog(progressDialogContext, R.style.CustomPD);
            pd.setTitle("Loading...");
            pd.setCancelable(false);
            pd.show();
        }

        protected Integer doInBackground(Void... params) {
            try {
                java.net.URL url = new URL(Constants.URLs.BASE + Constants.URLs.PLACEMENT_UG_DETAILS);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setDoInput(true);
                connection.setDoOutput(true);
                connection.setRequestMethod("POST");

                Uri.Builder _data = new Uri.Builder().appendQueryParameter("token", Constants.SharedPreferenceData.getTOKEN())
                        .appendQueryParameter("type", "put")
                        .appendQueryParameter("course", course)
                        .appendQueryParameter("branch", deptartment)
                        .appendQueryParameter("first", s[0])
                        .appendQueryParameter("first_py", py[0])
                        .appendQueryParameter("second", s[1])
                        .appendQueryParameter("second_py", py[1])
                        .appendQueryParameter("third", s[2])
                        .appendQueryParameter("third_py", py[2])
                        .appendQueryParameter("forth", s[3])
                        .appendQueryParameter("forth_py", py[3])
                        .appendQueryParameter("fifth", s[4])
                        .appendQueryParameter("fifth_py", py[4])
                        .appendQueryParameter("sisth", s[5])
                        .appendQueryParameter("sixth_py", py[5])
                        .appendQueryParameter("seventh", s[6])
                        .appendQueryParameter("seventh_py", py[6])
                        .appendQueryParameter("eighth", s[7])
                        .appendQueryParameter("eighth_py", py[7])
                        .appendQueryParameter("ninth", s[8])
                        .appendQueryParameter("ninth_py", py[8])
                        .appendQueryParameter("tenth", s[9])
                        .appendQueryParameter("tenth_py", py[9]);
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
                    String status = jsonObject.getString("status");
                    if (status.equals("Failed")) {
                        authenticationError = true;
                        errorMessage = status;
                    } else if (status.equals("success")) {
                        authenticationError = false;
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
                Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_SHORT).show();
                finish();
            }

        }
    }


}
