package com.samkeet.smartreva.Placement;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Switch;
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

public class PlacementRegInit extends AppCompatActivity {

    public SpotsDialog pd;
    public Context progressDialogContext;
    public boolean authenticationError = true;
    public String errorMessage = "Data Courpted";

    public ArrayList<String> UGPrograms = new ArrayList<String>();
    public ArrayList<String> UGSem = new ArrayList<String>();
    public ArrayList<String> PGSem = new ArrayList<String>();
    public ArrayList<String> PGPrograms = new ArrayList<String>();

    public Spinner UGSpinner;
    public Spinner PGSpinner;
    public Switch PGStudent;
    public Switch DiplomaStudent;
    public CheckBox TermCheck;
    public Button AcademicDetailsForm, UnderGraduateForm, PostGraduateForm;

    public String UGCourse;
    public String PGCourse;
    public boolean isPGStudent = false;
    public boolean isDiplomaStudent = false;
    public boolean termsAgreed = false;

    public LinearLayout PGComponent,PGButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_placement_reg_init);

        progressDialogContext = this;

        UGSpinner = (Spinner) findViewById(R.id.UGSpinner);
        PGSpinner = (Spinner) findViewById(R.id.PGSpinner);
        PGStudent = (Switch) findViewById(R.id.PGCheck);
        DiplomaStudent = (Switch) findViewById(R.id.DiplomaCheck);
        PGComponent = (LinearLayout) findViewById(R.id.pgcomponent);
        PGComponent.setVisibility(View.INVISIBLE);
        PGButton= (LinearLayout) findViewById(R.id.pgbutton);
        PGButton.setVisibility(View.INVISIBLE);
        TermCheck = (CheckBox) findViewById(R.id.termcheck);
        AcademicDetailsForm = (Button) findViewById(R.id.button1);
        UnderGraduateForm = (Button) findViewById(R.id.button2);
        PostGraduateForm = (Button) findViewById(R.id.button3);

        GetCourseDetails getCourseDetails = new GetCourseDetails();
        getCourseDetails.execute();

        TermCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    termsAgreed = true;
                } else {
                    termsAgreed = false;
                }
            }
        });

        PGStudent.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    isPGStudent = true;
                    PGComponent.setVisibility(View.VISIBLE);
                    PGButton.setVisibility(View.VISIBLE);
                } else {
                    isPGStudent = false;
                    PGComponent.setVisibility(View.INVISIBLE);
                    PGButton.setVisibility(View.INVISIBLE);
                }
            }
        });

        DiplomaStudent.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    isDiplomaStudent = true;
                } else {
                    isDiplomaStudent = true;
                }
            }
        });

        AcademicDetailsForm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (termsAgreed) {
                    Intent intent = new Intent(getApplicationContext(), PlacementRegForm1.class);
                    if (isDiplomaStudent) {
                        intent.putExtra("DIP", "yes");
                    } else {
                        intent.putExtra("DIP", "no");
                    }
                    if (isPGStudent) {
                        intent.putExtra("PGS", "yes");
                        intent.putExtra("PG", PGCourse);
                    } else {
                        intent.putExtra("PGS", "no");
                        intent.putExtra("PG", "no");
                    }
                    intent.putExtra("UG", UGCourse);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(), "You must agree Terms and Conditions!!!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        UnderGraduateForm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (termsAgreed) {
                    Intent intent=new Intent(getApplicationContext(),PlacementRegForm2.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "You must agree Terms and Conditions!!!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        PostGraduateForm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (termsAgreed) {
                    Intent intent=new Intent(getApplicationContext(),PlacementRegForm3.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "You must agree Terms and Conditions!!!", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    public void BackButton(View v) {
        finish();
    }

    public void spinnerInit() {
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, UGPrograms.toArray(new String[UGPrograms.size()]));
        UGSpinner.setAdapter(adapter1);
        UGSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {

                UGCourse = UGPrograms.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, PGPrograms.toArray(new String[PGPrograms.size()]));
        PGSpinner.setAdapter(adapter2);
        PGSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {

                PGCourse = PGPrograms.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

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
                java.net.URL url = new URL(Constants.URLs.BASE + Constants.URLs.COURSE_SEM);
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
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        String courseType = jsonObject.getString("courseType");
                        String course = jsonObject.getString("course");
                        String semester = jsonObject.getString("semester");
                        courseType = courseType.trim();
                        if (courseType.equals("UG")) {
                            UGPrograms.add(course);
                            UGSem.add(semester);
                        } else if (courseType.equals("PG")) {
                            PGPrograms.add(course);
                            PGSem.add(semester);
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

                spinnerInit();
            }

        }
    }

}
