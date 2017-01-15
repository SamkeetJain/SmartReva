package com.samkeet.smartreva.Placement;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
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

import dmax.dialog.SpotsDialog;

public class PlacementRegForm1 extends AppCompatActivity {

    public SpotsDialog pd;
    public Context progressDialogContext;
    public boolean authenticationError = true;
    public String errorMessage = "Data Courpted";

    public EditText mName, mDob, mGender, mNationality, mPaddress, mCaddress, mPhone, mMobile, mEmail;
    public EditText mFname, mFmobile, mFoccupation, mFemail;
    public EditText mMname, mMmobile, mMoccupation, mMemail;
    public EditText mTs, mTpy, mTb, mTn, mTm;
    public EditText mTWs, mTWpy, mTWb, mTWn, mTWm;
    public EditText mDs, mDpy, mDb, mDn, mDm;
    public EditText mDc;

    public String name, dob, gender, nationality, paddress, caddress, phone, mobile, email;
    public String fname, fmobile, foccupation, femail;
    public String mname, mmobile, moccupation, memail;
    public String ts, tpy, tb, tn, tm;
    public String tws, twpy, twb, twn, twm;
    public String ds, dpy, db, dn, dm;
    public String dc;

    public CardView DiplomaCard;
    public String isDiploma;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_placement_regform1);
        progressDialogContext=this;

        setFindViewById();

        isDiploma = getIntent().getStringExtra("DIP");

        if (isDiploma.equals("no")) {
            DiplomaCard.setVisibility(View.GONE);
        }
    }

    public void sendData() {
        name = mName.getText().toString();
        dob = mDob.getText().toString();
        gender = mGender.getText().toString();
        nationality = mNationality.getText().toString();
        paddress = mPaddress.getText().toString();
        caddress = mCaddress.getText().toString();
        phone = mPhone.getText().toString();
        mobile = mMobile.getText().toString();
        email = mEmail.getText().toString();

        fname = mFname.getText().toString();
        fmobile = mFmobile.getText().toString();
        foccupation = mFoccupation.getText().toString();
        femail = mFemail.getText().toString();

        mname = mMname.getText().toString();
        mmobile = mMmobile.getText().toString();
        moccupation = mMoccupation.getText().toString();
        memail = mMemail.getText().toString();

        ts = mTs.getText().toString();
        tpy = mTpy.getText().toString();
        tb = mTb.getText().toString();
        tn = mTn.getText().toString();
        tm = mTm.getText().toString();

        tws = mTWs.getText().toString();
        twpy = mTWpy.getText().toString();
        twb = mTWb.getText().toString();
        twn = mTWn.getText().toString();
        twm = mTWm.getText().toString();

        if (isDiploma.equals("yes")) {
            ds = mDs.getText().toString();
            dpy = mDpy.getText().toString();
            db = mDb.getText().toString();
            dn = mDn.getText().toString();
            dm = mDm.getText().toString();
        } else {
            ds = "NA";
            dpy = "NA";
            db = "NA";
            dn = "NA";
            dm = "NA";
        }

        dc=mDc.getText().toString();

    }

    public void setFindViewById() {

        mName = (EditText) findViewById(R.id.full_name);
        mDob = (EditText) findViewById(R.id.DOB);
        mGender = (EditText) findViewById(R.id.gender);
        mNationality = (EditText) findViewById(R.id.Nationality);
        mPaddress = (EditText) findViewById(R.id.paddress);
        mCaddress = (EditText) findViewById(R.id.caddress);
        mPhone = (EditText) findViewById(R.id.phone);
        mMobile = (EditText) findViewById(R.id.mobile);
        mEmail = (EditText) findViewById(R.id.email);

        mFname = (EditText) findViewById(R.id.f_full_name);
        mFoccupation = (EditText) findViewById(R.id.f_occupation);
        mFmobile = (EditText) findViewById(R.id.f_mobile);
        mFemail = (EditText) findViewById(R.id.f_email);

        mMname = (EditText) findViewById(R.id.m_full_name);
        mMoccupation = (EditText) findViewById(R.id.m_occupation);
        mMmobile = (EditText) findViewById(R.id.m_mobile);
        mMemail = (EditText) findViewById(R.id.m_email);

        mMname = (EditText) findViewById(R.id.m_full_name);
        mMoccupation = (EditText) findViewById(R.id.m_occupation);
        mMmobile = (EditText) findViewById(R.id.m_mobile);
        mMemail = (EditText) findViewById(R.id.m_email);

        DiplomaCard = (CardView) findViewById(R.id.diplomaCard);

        mTs = (EditText) findViewById(R.id.t_s);
        mTpy = (EditText) findViewById(R.id.t_py);
        mTb = (EditText) findViewById(R.id.t_b);
        mTn = (EditText) findViewById(R.id.t_n);
        mTm = (EditText) findViewById(R.id.t_m);

        mTWs = (EditText) findViewById(R.id.tw_s);
        mTWpy = (EditText) findViewById(R.id.tw_py);
        mTWb = (EditText) findViewById(R.id.tw_b);
        mTWn = (EditText) findViewById(R.id.tw_n);
        mTWm = (EditText) findViewById(R.id.tw_m);

        mDs = (EditText) findViewById(R.id.d_s);
        mDpy = (EditText) findViewById(R.id.d_py);
        mDb = (EditText) findViewById(R.id.d_b);
        mDn = (EditText) findViewById(R.id.d_n);
        mDm = (EditText) findViewById(R.id.d_m);

        mDc = (EditText) findViewById(R.id.dc);

    }

    public void BackButton(View v) {
        finish();
    }

    public void submit(View v) {
        sendData();
        SendPersonalDetails sendPersonalDetails=new SendPersonalDetails();
        sendPersonalDetails.execute();
    }

    private class SendPersonalDetails extends AsyncTask<Void, Void, Integer> {

        protected void onPreExecute() {
            pd = new SpotsDialog(progressDialogContext, R.style.CustomPD);
            pd.setTitle("Loading...");
            pd.setCancelable(false);
            pd.show();
        }

        protected Integer doInBackground(Void... params) {
            try {
                java.net.URL url = new URL(Constants.URLs.BASE + Constants.URLs.PLACEMENT_PERSONAL_DETAILS);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setDoInput(true);
                connection.setDoOutput(true);
                connection.setRequestMethod("POST");
                Log.d("POST", "DATA ready to sent");

                Uri.Builder _data = new Uri.Builder().appendQueryParameter("token", Constants.SharedPreferenceData.getTOKEN())
                        .appendQueryParameter("type", "put")
                        .appendQueryParameter("img_file", "NA")
                        .appendQueryParameter("fulname", name)
                        .appendQueryParameter("dateofb", dob)
                        .appendQueryParameter("gender", gender)
                        .appendQueryParameter("nationality", nationality)
                        .appendQueryParameter("permanent_add", paddress)
                        .appendQueryParameter("current_add", caddress)
                        .appendQueryParameter("phone_no", phone)
                        .appendQueryParameter("mobile_no", mobile)
                        .appendQueryParameter("email", email)
                        .appendQueryParameter("father_name", fname)
                        .appendQueryParameter("foccupation", foccupation)
                        .appendQueryParameter("fmobile_no", fmobile)
                        .appendQueryParameter("femail", femail)
                        .appendQueryParameter("mother_name", mname)
                        .appendQueryParameter("moccupation", moccupation)
                        .appendQueryParameter("mmobile_no", mmobile)
                        .appendQueryParameter("memail", memail)
                        .appendQueryParameter("dream_company", dc)
                        .appendQueryParameter("sign_file", "NA");
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

                    JSONObject jsonObject = new JSONObject(jsonResults.toString());
                    String status = jsonObject.getString("status");
                    if (status.equals("Failed")) {
                        authenticationError = true;
                        errorMessage = status;
                    } else if(status.equals("success")) {
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
                Toast.makeText(getApplicationContext(), "Personal details\n"+errorMessage, Toast.LENGTH_SHORT).show();
            } else {
                authenticationError=true;
                errorMessage = "Data Courpted";
                SendAcademicDetails sendAcademicDetails = new SendAcademicDetails();
                sendAcademicDetails.execute();
            }

        }
    }

    private class SendAcademicDetails extends AsyncTask<Void, Void, Integer> {

        protected void onPreExecute() {
            pd = new SpotsDialog(progressDialogContext, R.style.CustomPD);
            pd.setTitle("Loading...");
            pd.setCancelable(false);
            pd.show();
        }

        protected Integer doInBackground(Void... params) {
            try {
                java.net.URL url = new URL(Constants.URLs.BASE + Constants.URLs.PLACEMENT_ACADEMIC_DETAILS);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setDoInput(true);
                connection.setDoOutput(true);
                connection.setRequestMethod("POST");
                Log.d("POST", "DATA ready to sent");

                Uri.Builder _data = new Uri.Builder().appendQueryParameter("token", Constants.SharedPreferenceData.getTOKEN())
                        .appendQueryParameter("type", "put")
                        .appendQueryParameter("tenthm",tm)
                        .appendQueryParameter("tenth_py", tpy)
                        .appendQueryParameter("tenth_board", tb)
                        .appendQueryParameter("tenth_sn", tn)
                        .appendQueryParameter("tenth_s", ts)
                        .appendQueryParameter("twelfthm", twm)
                        .appendQueryParameter("twelfth_py", twpy)
                        .appendQueryParameter("twelfth_board", twb)
                        .appendQueryParameter("twelfth_sn", twn)
                        .appendQueryParameter("twelfth_s", tws)
                        .appendQueryParameter("diplomam", dm)
                        .appendQueryParameter("diploma_py", dpy)
                        .appendQueryParameter("diploma_board", db)
                        .appendQueryParameter("diploma_sn", dn)
                        .appendQueryParameter("diploma_s", ds);
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

                    JSONObject jsonObject = new JSONObject(jsonResults.toString());
                    String status = jsonObject.getString("status");
                    if (status.equals("Failed")) {
                        authenticationError = true;
                        errorMessage = status;
                    } else if(status.equals("success")) {
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
                Toast.makeText(getApplicationContext(), "Academic details\n"+errorMessage, Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_SHORT).show();
                finish();
            }

        }
    }

}
