package com.samkeet.smartreva.Placement;

import android.content.Context;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TableLayout;
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

public class PlacementAcademicDetails extends AppCompatActivity {

    public TextView mTenthB, mTenthSN, mTenthC, mTenthM, mTenthPY;
    public TextView mTwelB, mTwelSN, mTwelC, mTwelM, mTwelPY;
    public TextView mDiplomaB, mDiplomaSN, mDiplomaC, mDiplomaM, mDiplomaPY;
    public TextView mUgB, mUgC, mUg1, mUg2, mUg3, mUg4, mUg5, mUg6, mUg7, mUg8, mUg9, mUg10;
    public TextView mPgB, mPgC, mPg1, mPg2, mPg3, mPg4, mPg5, mPg6, mPg7, mPg8, mPgp, mPg10;


    public String tenthB, tenthSN, tenthC, tenthM, tenthPY;
    public String twelB, twelSN, twelC, twelM, twelPY;
    public String diplomaB, diplomaSN, diplomaC, diplomaM, diplomaPY;
    public String ugB, ugC, ug1, ug2, ug3, ug4, ug5, ug6, ug7, ug8, ug9, ug10;
    public String pgB, pgC, pg1, pg2, pg3, pg4, pg5, pg6, pg7, pg8, pg9, pg10;

    public LinearLayout backlogDetails;
    public JSONArray backLogArray;

    public SpotsDialog pd;
    public Context progressDialogContext;
    public boolean authenticationError = true;
    public String errorMessage = "Data Corrupt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_placement_academic_details);

        progressDialogContext = this;

        backlogDetails = (LinearLayout) findViewById(R.id.backlogs);

        findViewById();

        if (Constants.Methods.networkState(getApplicationContext(), (ConnectivityManager) getSystemService(getApplicationContext().CONNECTIVITY_SERVICE))) {
            GetAcademicDetails getAcademicDetails = new GetAcademicDetails();
            getAcademicDetails.execute();
        }
    }

    public void findViewById() {
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

        mUgB = (TextView) findViewById(R.id.ugB);
        mUgC = (TextView) findViewById(R.id.ugC);
        mUg1 = (TextView) findViewById(R.id.ug1);
        mUg2 = (TextView) findViewById(R.id.ug2);
        mUg3 = (TextView) findViewById(R.id.ug3);
        mUg4 = (TextView) findViewById(R.id.ug4);
        mUg5 = (TextView) findViewById(R.id.ug5);
        mUg6 = (TextView) findViewById(R.id.ug6);
        mUg7 = (TextView) findViewById(R.id.ug7);
        mUg8 = (TextView) findViewById(R.id.ug8);
        mUg9 = (TextView) findViewById(R.id.ug9);
        mUg10 = (TextView) findViewById(R.id.ug10);

        mPgB = (TextView) findViewById(R.id.pgB);
        mPgC = (TextView) findViewById(R.id.pgC);
        mPg1 = (TextView) findViewById(R.id.pg1);
        mPg2 = (TextView) findViewById(R.id.pg2);
        mPg3 = (TextView) findViewById(R.id.pg3);
        mPg4 = (TextView) findViewById(R.id.pg4);
        mPg5 = (TextView) findViewById(R.id.pg5);
        mPg6 = (TextView) findViewById(R.id.pg6);
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

        mUgB.setText(ugB);
        mUgC.setText(ugC);
        mUg1.setText(ug1);
        mUg2.setText(ug2);
        mUg3.setText(ug3);
        mUg4.setText(ug4);
        mUg5.setText(ug5);
        mUg6.setText(ug6);
        mUg7.setText(ug7);
        mUg8.setText(ug8);
        mUg9.setText(ug9);
        mUg10.setText(ug10);

        mPgB.setText(pgB);
        mPgC.setText(pgC);
        mPg1.setText(pg1);
        mPg2.setText(pg2);
        mPg3.setText(pg3);
        mPg4.setText(pg4);
        mPg5.setText(pg5);
        mPg6.setText(pg6);

    }

    public void setBackLogView() {
        for (int i = 0; i < backLogArray.length(); i++) {
            try {

                int marginInDp = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 15, getResources().getDisplayMetrics());

                JSONObject jsonObject = backLogArray.getJSONObject(i);

                LinearLayout one = new LinearLayout(getApplicationContext());
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                params.setMargins(0, 0, 0, marginInDp);
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

                Uri.Builder _data = new Uri.Builder().appendQueryParameter("token", Constants.SharedPreferenceData.getTOKEN()).appendQueryParameter("type", "get");
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
                java.net.URL url = new URL(Constants.URLs.BASE + Constants.URLs.PLACEMENT_UG_DETAILS);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setDoInput(true);
                connection.setDoOutput(true);
                connection.setRequestMethod("POST");

                Uri.Builder _data = new Uri.Builder().appendQueryParameter("token", Constants.SharedPreferenceData.getTOKEN()).appendQueryParameter("type", "get");
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
                    JSONObject jsonObject = jsonArray.getJSONObject(0);

                    ugB = jsonObject.getString("branch");
                    ugC = jsonObject.getString("course");
                    ug1 = jsonObject.getString("first") + " - " + jsonObject.getString("first_py");
                    ug2 = jsonObject.getString("second") + " - " + jsonObject.getString("second_py");
                    ug3 = jsonObject.getString("third") + " - " + jsonObject.getString("third_py");
                    ug4 = jsonObject.getString("forth") + " - " + jsonObject.getString("forth_py");
                    ug5 = jsonObject.getString("fifth") + " - " + jsonObject.getString("fifth_py");
                    ug6 = jsonObject.getString("sixth") + " - " + jsonObject.getString("sixth_py");
                    ug7 = jsonObject.getString("seventh") + " - " + jsonObject.getString("seventh_py");
                    ug8 = jsonObject.getString("eighth") + " - " + jsonObject.getString("eighth_py");
                    ug9 = jsonObject.getString("ninth") + " - " + jsonObject.getString("ninth_py");
                    ug10 = jsonObject.getString("tenth") + " - " + jsonObject.getString("tenth_py");
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
                    GetPgDetails getPgDetails = new GetPgDetails();
                    getPgDetails.execute();
                }

            }
        }
    }

    private class GetPgDetails extends AsyncTask<Void, Void, Integer> {


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
                java.net.URL url = new URL(Constants.URLs.BASE + Constants.URLs.PLACEMENT_PG_DETAILS);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setDoInput(true);
                connection.setDoOutput(true);
                connection.setRequestMethod("POST");

                Uri.Builder _data = new Uri.Builder().appendQueryParameter("token", Constants.SharedPreferenceData.getTOKEN()).appendQueryParameter("type", "get");
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
                    if(jsonArray.length()==0){
                        pgB = "NA";
                        pgC = "NA";
                        pg1 = "NA";
                        pg2 = "NA";
                        pg3 = "NA";
                        pg4 = "NA";
                        pg5 = "NA";
                        pg6 = "NA";
                    }
                    if (jsonArray.length() > 0) {
                        JSONObject jsonObject = jsonArray.getJSONObject(0);

                        pgB = jsonObject.getString("branch");
                        pgC = jsonObject.getString("course");
                        pg1 = jsonObject.getString("first") + " - " + jsonObject.getString("first_py");
                        pg2 = jsonObject.getString("second") + " - " + jsonObject.getString("second_py");
                        pg3 = jsonObject.getString("third") + " - " + jsonObject.getString("third_py");
                        pg4 = jsonObject.getString("forth") + " - " + jsonObject.getString("forth_py");
                        pg5 = jsonObject.getString("fifth") + " - " + jsonObject.getString("fifth_py");
                        pg6 = jsonObject.getString("sixth") + " - " + jsonObject.getString("sixth_py");
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

                Uri.Builder _data = new Uri.Builder().appendQueryParameter("token", Constants.SharedPreferenceData.getTOKEN()).appendQueryParameter("type", "get");
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
