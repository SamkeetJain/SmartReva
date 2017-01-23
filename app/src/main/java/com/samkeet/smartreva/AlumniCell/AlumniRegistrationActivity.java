package com.samkeet.smartreva.AlumniCell;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
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

import static com.samkeet.smartreva.R.id.mobileno;

public class AlumniRegistrationActivity extends AppCompatActivity {

    public SpotsDialog pd;
    public Context progressDialogContext;

    public boolean authenticationError = true;
    public String errorMessage = "Data Corrupted";

    public TextInputLayout ip_name, ip_mobile, ip_email, ip_password, ip_cpassword, ip_loc;
    public Button send;
    public String sname, smobileno, semail, spassword;

    public String course, dept, year;
    public JSONObject[] objects;

    public LabelledSpinner Gcourse, Gdept, Gyog;
    public String[] courseList = {"course"};
    public String[] years = {"1990", "1991", "1992", "1993", "1994", "1995", "1996", "1997", "1998", "1999", "2000", "2001", "2002", "2003", "2004", "2005", "2006", "2007", "2008", "2009", "2010", "2011", "2012", "2013", "2014", "2015", "2016", "2017", "2018", "2019", "2020"};
    public String[] deptList = {"deptCode"};

    public EditText mMobileNo, mPassword, mCPassword, mFullname, mEmail, mSRN, mCompany, mDesg, mLoc;
    public String mobileNo, password, cpassword, fullname, email, srn, company, desg, loc;

    public String countryCode;
    public Spinner mCountryCode;
    public String[] list;
    public String[] cc, cn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alunmi_registration);
        progressDialogContext = this;

        cc = Constants.Country_code;
        cn = Constants.Country_name;
        list = new String[cn.length];
        for (int i = 0; i < cn.length; i++) {
            list[i] = cc[i] + "  " + cn[i];
        }

        mCountryCode = (Spinner) findViewById(R.id.countrycode);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, list);
        mCountryCode.setAdapter(adapter2);
        mCountryCode.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                countryCode = cc[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                countryCode = cc[0];
            }
        });

        mMobileNo = (EditText) findViewById(mobileno);
        mPassword = (EditText) findViewById(R.id.password);
        mCPassword = (EditText) findViewById(R.id.cpassword);
        mFullname = (EditText) findViewById(R.id.name);
        mEmail = (EditText) findViewById(R.id.email);
        mSRN = (EditText) findViewById(R.id.srn);
        mCompany = (EditText) findViewById(R.id.company);
        mDesg = (EditText) findViewById(R.id.designation);
        mLoc = (EditText) findViewById(R.id.loc);

        ip_email = (TextInputLayout) findViewById(R.id.input_layout_email);
        ip_password = (TextInputLayout) findViewById(R.id.input_layout_password);
        ip_cpassword = (TextInputLayout) findViewById(R.id.input_layout_cpassword);
        ip_name = (TextInputLayout) findViewById(R.id.input_layout_name);
        ip_mobile = (TextInputLayout) findViewById(R.id.input_layout_mobilemo);
        ip_loc = (TextInputLayout) findViewById(R.id.input_layout_loc);
        send = (Button) findViewById(R.id.send_button);

        mEmail.addTextChangedListener(new MyTextWatcher(mEmail));


        Gcourse = (LabelledSpinner) findViewById(R.id.Gcourse);
        Gdept = (LabelledSpinner) findViewById(R.id.Gdept);
        Gyog = (LabelledSpinner) findViewById(R.id.Gyog);

        initSpinner();

        if (Constants.Methods.networkState(getApplicationContext(), (ConnectivityManager) getSystemService(getApplicationContext().CONNECTIVITY_SERVICE))) {
            GetCourseAndDeptDetails getCourseAndDeptDetails = new GetCourseAndDeptDetails();
            getCourseAndDeptDetails.execute();
        }

        send.setOnClickListener(new View.OnClickListener() {

                                    @Override
                                    public void onClick(View v) {
                                        appLogin();
                                    }
                                }
        );

    }


    public void appLogin() {
        if (!validateMobilenumber()) {
            return;
        }
        if (!validatePassword()) {
            return;
        }
        if (!validateCPassword()) {
            return;
        }
        if (!validateName()) {
            return;
        }
        if (!validateEmail()) {
            return;
        }
        if (!validateLoc()) {
            return;
        }

        mobileNo = mMobileNo.getText().toString().trim();
        password = mPassword.getText().toString().trim();
        cpassword = mCPassword.getText().toString().trim();
        fullname = mFullname.getText().toString().trim();
        email = mEmail.getText().toString().trim();
        srn = mSRN.getText().toString().trim();
        company = mCompany.getText().toString().trim();
        desg = mDesg.getText().toString().trim();
        loc = mLoc.getText().toString().trim();

        Registration registration = new Registration();
        registration.execute();

    }

    private boolean validateMobilenumber() {
        String mobile = mMobileNo.getText().toString().trim();

        if (mobile.isEmpty() || !isValidMobilenumber(mobile) || mobile.length() > 15) {

            ip_mobile.setError("Invalid Phone Number");
            requestFocus(mMobileNo);
            return false;
        } else {
            ip_mobile.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validatePassword() {

        if (mPassword.getText().toString().trim().isEmpty()) {
            ip_password.setError("Password you entered is not valid");
            requestFocus(mPassword);
            return false;
        } else {
            ip_password.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validateCPassword() {

        if (mCPassword.getText().toString().trim().isEmpty()) {
            ip_cpassword.setError("Password you entered is not valid");
            requestFocus(mCPassword);
            return false;
        } else if (!mCPassword.getText().toString().trim().equals(mPassword.getText().toString().trim())) {
            ip_cpassword.setError("Password doesnt match");
            requestFocus(mCPassword);
            return false;
        } else {
            ip_cpassword.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validateEmail() {
        email = mEmail.getText().toString().trim();
        if (email.isEmpty() || !isValidEmail(email)) {
            ip_email.setError("Email you entered is not valid");
            requestFocus(mEmail);
            return false;
        } else {
            ip_email.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validateName() {
        if (mFullname.getText().toString().trim().isEmpty()) {
            ip_name.setError("Name you entered is not valid");
            requestFocus(mFullname);
            return false;
        } else {
            ip_name.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validateLoc() {
        if (mLoc.getText().toString().trim().isEmpty()) {
            ip_loc.setError("Location you entered is not valid");
            requestFocus(mLoc);
            return false;
        } else {
            ip_loc.setErrorEnabled(false);
        }
        return true;
    }

    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }

    private static boolean isValidMobilenumber(String mobileNo) {
        return !TextUtils.isEmpty(mobileNo) && Patterns.PHONE.matcher(mobileNo).matches();
    }

    private static boolean isValidEmail(String email) {
        return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    public class MyTextWatcher implements TextWatcher {

        private View view;

        private MyTextWatcher(View view) {
            this.view = view;
        }

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        public void afterTextChanged(Editable editable) {
            switch (view.getId()) {
                case R.id.input_layout_email:
                    validateEmail();
                    break;

            }
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
                java.net.URL url = new URL(Constants.URLs.ALUMNI_BASE + Constants.URLs.ALUMNI_REG);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setDoInput(true);
                connection.setDoOutput(true);
                connection.setRequestMethod("POST");

                Uri.Builder _data = new Uri.Builder()
                        .appendQueryParameter("mobileNo", mobileNo)
                        .appendQueryParameter("password", password)
                        .appendQueryParameter("name", fullname)
                        .appendQueryParameter("email", email)
                        .appendQueryParameter("srn", srn)
                        .appendQueryParameter("course", course)
                        .appendQueryParameter("dept", dept)
                        .appendQueryParameter("yog", year)
                        .appendQueryParameter("company", company)
                        .appendQueryParameter("desg", desg)
                        .appendQueryParameter("loc", loc)
                        .appendQueryParameter("code", countryCode);

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
                    JSONObject jsonObj = new JSONObject(jsonResults.toString());
                    String status = jsonObj.getString("status");
                    if (status.equals("success")) {
                        authenticationError = false;
                    } else {
                        authenticationError = true;
                        errorMessage = status;
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
                AlertDialog.Builder builder = new AlertDialog.Builder(AlumniRegistrationActivity.this);
                builder.setTitle("Oops!!! We cannot process your request at this time");
                builder.setMessage("Response: " + "\n" + errorMessage);
                String positiveText = "Retry";
                builder.setPositiveButton(positiveText,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                String negativeText = "Cancel";
                builder.setNegativeButton(negativeText,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                finish();
                            }
                        });

                AlertDialog dialog = builder.create();
                // display dialog
                dialog.show();
            } else {
                AlertDialog.Builder builder = new AlertDialog.Builder(AlumniRegistrationActivity.this);
                builder.setTitle("Registration Successful");
                builder.setMessage("Your registration request is received, We will further verify your details. Thank you for being a part of our family");
                String positiveText = "Login now";
                builder.setPositiveButton(positiveText,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                finish();
                            }
                        });

                builder.setOnCancelListener(new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialogInterface) {
                        finish();
                    }
                });

                AlertDialog dialog = builder.create();
                // display dialog
                dialog.show();
            }

        }
    }

}
