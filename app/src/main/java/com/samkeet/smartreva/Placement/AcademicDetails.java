package com.samkeet.smartreva.Placement;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TableRow;
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

public class AcademicDetails extends AppCompatActivity {

    public TextView mTenthB, mTenthSN, mTenthC, mTenthM, mTenthPY;
    public TextView mTwelB, mTwelSN, mTwelC, mTwelM, mTwelPY;
    public TextView mDiplomaB, mDiplomaSN, mDiplomaC, mDiplomaM, mDiplomaPY;
    public TextView mEngB, mEng1, mEng2, mEng3, mEng4, mEng5, mEng6, mEng7, mEng8;


    public String tenthB, tenthSN, tenthC, tenthM, tenthPY;
    public String twelB, twelSN, twelC, twelM, twelPY;
    public String diplomaB, diplomaSN, diplomaC, diplomaM, diplomaPY;
    public String engB, eng1, eng2, eng3, eng4, eng5, eng6, eng7, eng8;

    public LinearLayout backlogDetails;
    public JSONArray backLogArray;

    public SpotsDialog pd;
    public Context progressDialogContext;
    public boolean authenticationError = true;
    public String errorMessage = "Data Corrupt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grades);

        progressDialogContext = this;

        backlogDetails = (LinearLayout) findViewById(R.id.backlogs);

        mTenthB = (TextView) findViewById(R.id.tenthB);
        mTenthSN = (TextView) findViewById(R.id.tenthN);
        mTenthC = (TextView) findViewById(R.id.tenthC);
        mTenthM = (TextView) findViewById(R.id.tenthM);

        mTwelB = (TextView) findViewById(R.id.twelB);
        mTwelSN = (TextView) findViewById(R.id.twelSC);
        mTwelC = (TextView) findViewById(R.id.twelC);
        mTwelM = (TextView) findViewById(R.id.twelM);

        mDiplomaB = (TextView) findViewById(R.id.dipB);
        mDiplomaSN = (TextView) findViewById(R.id.dipSN);
        mDiplomaC = (TextView) findViewById(R.id.dipC);
        mDiplomaM = (TextView) findViewById(R.id.dipS);

        mEngB = (TextView) findViewById(R.id.engB);
        mEng1 = (TextView) findViewById(R.id.eng1);
        mEng2 = (TextView) findViewById(R.id.eng2);
        mEng3 = (TextView) findViewById(R.id.eng3);
        mEng4 = (TextView) findViewById(R.id.eng4);
        mEng5 = (TextView) findViewById(R.id.eng5);
        mEng6 = (TextView) findViewById(R.id.eng6);
        mEng7 = (TextView) findViewById(R.id.eng7);
        mEng8 = (TextView) findViewById(R.id.eng8);

        if (Constants.Methods.networkState(getApplicationContext(), (ConnectivityManager) getSystemService(getApplicationContext().CONNECTIVITY_SERVICE))) {
            GetAcademicDetails getAcademicDetails = new GetAcademicDetails();
            getAcademicDetails.execute();
        }
    }

    public void setTextView() {
        mTenthB.setText(tenthB);
        mTenthSN.setText(tenthSN);
        mTenthC.setText(tenthC);
        mTenthM.setText(tenthM);

        mTwelB.setText(twelB);
        mTwelSN.setText(twelSN);
        mTwelC.setText(twelC);
        mTwelM.setText(twelM);

        mDiplomaB.setText(diplomaB);
        mDiplomaSN.setText(diplomaSN);
        mDiplomaC.setText(diplomaC);
        mDiplomaM.setText(diplomaM);

        mEngB.setText(engB);
        mEng1.setText(eng1);
        mEng2.setText(eng2);
        mEng3.setText(eng3);
        mEng4.setText(eng4);
        mEng5.setText(eng5);
        mEng6.setText(eng6);
        mEng7.setText(eng7);
        mEng8.setText(eng8);


    }

    public void setBackLogView() {
        for (int i = 0; i < backLogArray.length(); i++) {
            try {

                int marginInDp = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 15, getResources().getDisplayMetrics());

                JSONObject jsonObject = backLogArray.getJSONObject(i);

                LinearLayout one = new LinearLayout(getApplicationContext());
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                params.setMargins(0,0,0,marginInDp);
                one.setLayoutParams(params);
                one.setOrientation(LinearLayout.VERTICAL);

                LinearLayout two1 = new LinearLayout(getApplicationContext());
                two1.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                two1.setOrientation(LinearLayout.HORIZONTAL);

                TextView subjectCode1 = new TextView(getApplicationContext());
                subjectCode1.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT, 1.5f));
                subjectCode1.setTextSize(14);
                subjectCode1.setGravity(Gravity.CENTER);
                String data = "Subject Code: ";
                subjectCode1.setText(data);
                subjectCode1.setTextColor(Color.parseColor("#808080"));

                TextView subjectCode2 = new TextView(getApplicationContext());
                subjectCode2.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT, 1f));
                subjectCode2.setTextSize(14);
                subjectCode2.setGravity(Gravity.LEFT);
                data = jsonObject.getString("subjectCode");
                subjectCode2.setText(data);
                subjectCode2.setTextColor(Color.parseColor("#808080"));

                two1.addView(subjectCode1);
                two1.addView(subjectCode2);

                LinearLayout two2 = new LinearLayout(getApplicationContext());
                two2.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                two2.setOrientation(LinearLayout.HORIZONTAL);

                TextView subjectName1 = new TextView(getApplicationContext());
                subjectName1.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT, 1.5f));
                subjectName1.setTextSize(14);
                subjectName1.setGravity(Gravity.CENTER);
                data = "Subject Name: ";
                subjectName1.setText(data);
                subjectName1.setTextColor(Color.parseColor("#808080"));

                TextView subjectName2 = new TextView(getApplicationContext());
                subjectName2.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT, 1f));
                subjectName2.setTextSize(14);
                subjectName2.setGravity(Gravity.LEFT);
                data = jsonObject.getString("subjectName");
                subjectName2.setText(data);
                subjectName2.setTextColor(Color.parseColor("#808080"));

                two2.addView(subjectName1);
                two2.addView(subjectName2);

                LinearLayout two3 = new LinearLayout(getApplicationContext());
                two3.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                two3.setOrientation(LinearLayout.HORIZONTAL);

                TextView FailedSem1 = new TextView(getApplicationContext());
                FailedSem1.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT, 1.5f));
                FailedSem1.setTextSize(14);
                FailedSem1.setGravity(Gravity.CENTER);
                data = "Failed Sem : ";
                FailedSem1.setText(data);
                FailedSem1.setTextColor(Color.parseColor("#808080"));

                TextView FailedSem2 = new TextView(getApplicationContext());
                FailedSem2.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT, 1f));
                FailedSem2.setTextSize(14);
                FailedSem2.setGravity(Gravity.LEFT);
                data = jsonObject.getString("Fsem") + " | " + jsonObject.getString("Fyear");
                FailedSem2.setText(data);
                FailedSem2.setTextColor(Color.parseColor("#808080"));

                two3.addView(FailedSem1);
                two3.addView(FailedSem2);

                LinearLayout two4 = new LinearLayout(getApplicationContext());
                two4.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                two4.setOrientation(LinearLayout.HORIZONTAL);

                TextView PassedSem1 = new TextView(getApplicationContext());
                PassedSem1.setLayoutParams(new TableRow.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT, 1.5f));
                PassedSem1.setTextSize(14);
                PassedSem1.setGravity(Gravity.CENTER);
                data = "Passed Sem : ";
                PassedSem1.setText(data);
                PassedSem1.setTextColor(Color.parseColor("#808080"));

                TextView PassedSem2 = new TextView(getApplicationContext());
                PassedSem2.setLayoutParams(new TableRow.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT, 1f));
                PassedSem2.setTextSize(14);
                PassedSem2.setGravity(Gravity.LEFT);
                data = jsonObject.getString("Psem") + " | " + jsonObject.getString("Pyear");
                PassedSem2.setText(data);
                PassedSem2.setTextColor(Color.parseColor("#808080"));

                two4.addView(PassedSem1);
                two4.addView(PassedSem2);

                one.addView(two1);
                one.addView(two2);
                one.addView(two3);
                one.addView(two4);

                backlogDetails.addView(one);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public void BackButton(View v) {
        finish();
    }

    private class GetAcademicDetails extends AsyncTask<Void, Void, Integer> {


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

                Uri.Builder _data = new Uri.Builder().appendQueryParameter("token", Constants.SharedPreferenceData.getTOKEN()).appendQueryParameter("type", "get");
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
                    JSONObject jsonObject = jsonArray.getJSONObject(0);
                    tenthB = jsonObject.getString("tenth_board");
                    tenthC = jsonObject.getString("tenth_s");
                    tenthSN = jsonObject.getString("tenth_sn");
                    tenthM = jsonObject.getString("tenthm");
                    tenthPY = jsonObject.getString("tenth_py");

                    twelB = jsonObject.getString("twelfth_board");
                    twelC = jsonObject.getString("twelfth_s");
                    twelSN = jsonObject.getString("twelfth_sn");
                    twelM = jsonObject.getString("twelfthm");
                    twelPY = jsonObject.getString("twelfth_py");

                    diplomaB = jsonObject.getString("diploma_board");
                    diplomaC = jsonObject.getString("diploma_s");
                    diplomaSN = jsonObject.getString("diploma_sn");
                    diplomaM = jsonObject.getString("diplomam");
                    diplomaPY = jsonObject.getString("diploma_py");

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

                if (Constants.Methods.networkState(getApplicationContext(), (ConnectivityManager) getSystemService(getApplicationContext().CONNECTIVITY_SERVICE))) {
                    GetUgDetails getUgDetails = new GetUgDetails();
                    getUgDetails.execute();
                }
            }

        }
    }

    private class GetUgDetails extends AsyncTask<Void, Void, Integer> {


        protected void onPreExecute() {
            pd = new SpotsDialog(progressDialogContext, R.style.CustomPD);
            pd.setTitle("Loading...");
            pd.setCancelable(false);
            pd.show();
        }

        protected Integer doInBackground(Void... params) {
            try {
                authenticationError = true;
                errorMessage = "DATA COURPTED";
                java.net.URL url = new URL(Constants.URLs.BASE + Constants.URLs.PLACEMENT_ENGG_DETAILS);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setDoInput(true);
                connection.setDoOutput(true);
                connection.setRequestMethod("POST");
                Log.d("POST", "DATA ready to sent");

                Uri.Builder _data = new Uri.Builder().appendQueryParameter("token", Constants.SharedPreferenceData.getTOKEN()).appendQueryParameter("type", "get");
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
                    JSONObject jsonObject = jsonArray.getJSONObject(0);

                    engB = jsonObject.getString("branch");
                    eng1 = jsonObject.getString("first") + " - " + jsonObject.getString("first_py");
                    eng2 = jsonObject.getString("second") + " - " + jsonObject.getString("second_py");
                    eng3 = jsonObject.getString("third") + " - " + jsonObject.getString("third_py");
                    eng4 = jsonObject.getString("forth") + " - " + jsonObject.getString("forth_py");
                    eng5 = jsonObject.getString("fifth") + " - " + jsonObject.getString("fifth_py");
                    eng6 = jsonObject.getString("sixth") + " - " + jsonObject.getString("sixth_py");
                    eng7 = jsonObject.getString("seventh") + " - " + jsonObject.getString("seventh_py");
                    eng8 = jsonObject.getString("eighth") + " - " + jsonObject.getString("eighth_py");
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

                if (Constants.Methods.networkState(getApplicationContext(), (ConnectivityManager) getSystemService(getApplicationContext().CONNECTIVITY_SERVICE))) {
                    GetBacklogDetails getBacklogDetails = new GetBacklogDetails();
                    getBacklogDetails.execute();
                    setTextView();
                }

            }
        }
    }

    private class GetBacklogDetails extends AsyncTask<Void, Void, Integer> {


        protected void onPreExecute() {
            pd = new SpotsDialog(progressDialogContext, R.style.CustomPD);
            pd.setTitle("Loading...");
            pd.setCancelable(false);
            pd.show();
        }

        protected Integer doInBackground(Void... params) {
            try {
                java.net.URL url = new URL(Constants.URLs.BASE + Constants.URLs.PLACEMENT_BACK_DETAILS);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setDoInput(true);
                connection.setDoOutput(true);
                connection.setRequestMethod("POST");
                Log.d("POST", "DATA ready to sent");

                Uri.Builder _data = new Uri.Builder().appendQueryParameter("token", Constants.SharedPreferenceData.getTOKEN()).appendQueryParameter("type", "get");
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
                    backLogArray = new JSONArray(jsonResults.toString());
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
                setBackLogView();
            }

        }
    }

}
