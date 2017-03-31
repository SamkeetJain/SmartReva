package com.samkeet.smartreva.Mentor;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.LinearLayout;
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

public class MentorPlacementAcademicDetailsManager extends AppCompatActivity {

    public Context progressDialogContext;
    public SpotsDialog pd;
    public boolean authenticationError;
    public String errorMessage;

    public JSONObject courseObject;
    public JSONObject[] backlogObject;

    public TextView PDname, PDgender, PDdob, PDphoneno, PDemail, PDcourse, PDbranch, PDyeargap, PDworkxp, PDcaddress, PDpaddress;
    public TextView AD10score, AD10board, AD10schoolname, AD10yop, AD12score, AD12board, AD12schoolname, AD12yop;
    public TextView BTdplScore, BTdplschoolname, BTdplyop, BTyoj, BTsem1, BTsem2, BTsem3, BTsem4, BTsem5, BTsem6, BTsem7, BTsem8;
    public TextView MTdplscore, MTdplschoolname, MTdplyop, MTbtscore, MTbtuniversity, MTbtcollegename, MTbtyop, MTyoj, MTsem1, MTsem2, MTsem3, MTsem4;
    public TextView Degcourse, Degyoj, Degsem1, Degsem2, Degsem3, Degsem4, Degsem5, Degsem6;
    public TextView MCAdegcourse, MCAdeguniversity, MCAdegyop, MCAdegscore, MCAdegcollegename, MCAyoj, MCAsem1, MCAsem2, MCAsem3, MCAsem4, MCAsem5, MCAsem6;
    public TextView MMdegscore, MMdeguniversity, MMdegcollegename, MMdegyop, MMyoj, MMsem1, MMsem2, MMsem3, MMsem4;

    public String pdname, pdgender, pddob, pdphoneno, pdemail, pdcourse, pdbranch, pdyeargap, pdworkxp, pdcaddress, pdpaddress, pdbacklog;
    public String ad10score, ad10board, ad10schoolname, ad10yop, ad12score, ad12board, ad12schoolname, ad12yop;
    public String btdplScore, btdplschoolname, btdplyop, btyoj, btsem1, btsem2, btsem3, btsem4, btsem5, btsem6, btsem7, btsem8;
    public String mtdplscore, mtdplschoolname, mtdplyop, mtbtscore, mtbtuniversity, mtbtcollegename, mtbtyop, mtyoj, mtsem1, mtsem2, mtsem3, mtsem4;
    public String degcourse, degyoj, degsem1, degsem2, degsem3, degsem4, degsem5, degsem6;
    public String mcadegcourse, mcadeguniversity, mcadegyop, mcadegscore, mcadegcollegename, mcayoj, mcasem1, mcasem2, mcasem3, mcasem4, mcasem5, mcasem6;
    public String mmdegscore, mmdeguniversity, mmdegcollegename, mmdegyop, mmyoj, mmsem1, mmsem2, mmsem3, mmsem4;

    public CardView btechView, mtechView, degreeView, mcaView, mmView;
    public LinearLayout backlogView;
    public TextView[] semister, subject;
    public CheckBox[] check;
    public Button mAccept, mReject;

    public String updateStatus;

    public String SRN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mentor_placement_academic_details_manager);
        progressDialogContext = this;

        SRN = getIntent().getStringExtra("DATA");

        setViewByID();

        mAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Accept();
            }
        });

        mReject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Reject();
            }
        });

        GetProfile getProfile = new GetProfile();
        getProfile.execute();

    }

    public void BackButton(View v) {
        finish();
    }

    public void Accept() {
        updateStatus = "APPROVE";
        UpdateStatus updateStatus = new UpdateStatus();
        updateStatus.execute();
    }

    public void Reject() {
        updateStatus = "APPROVE";
        UpdateStatus updateStatus = new UpdateStatus();
        updateStatus.execute();
    }

    public void setViewByID() {
        //persnal details
        PDname = (TextView) findViewById(R.id.PD_name);
        PDgender = (TextView) findViewById(R.id.PD_gender);
        PDdob = (TextView) findViewById(R.id.PD_dob);
        PDphoneno = (TextView) findViewById(R.id.PD_phoneno);
        PDemail = (TextView) findViewById(R.id.PD_email);
        PDcourse = (TextView) findViewById(R.id.PD_course);
        PDbranch = (TextView) findViewById(R.id.PD_branch);
        PDyeargap = (TextView) findViewById(R.id.PD_yeargap);
        PDworkxp = (TextView) findViewById(R.id.PD_workxp);
        PDcaddress = (TextView) findViewById(R.id.PD_current_address);
        PDpaddress = (TextView) findViewById(R.id.PD_permanent_address);

        //academic details
        AD10score = (TextView) findViewById(R.id.AD_10_score);
        AD10board = (TextView) findViewById(R.id.AD_10_board);
        AD10schoolname = (TextView) findViewById(R.id.AD_10_school_name);
        AD10yop = (TextView) findViewById(R.id.AD_10_yop);
        AD12score = (TextView) findViewById(R.id.AD_12_score);
        AD12board = (TextView) findViewById(R.id.AD_12_board);
        AD12schoolname = (TextView) findViewById(R.id.AD_12_school_name);
        AD12yop = (TextView) findViewById(R.id.AD_12_yop);

        //b tech
        BTdplScore = (TextView) findViewById(R.id.BT_dpl_score);
        BTdplschoolname = (TextView) findViewById(R.id.BT_dpl_school_name);
        BTdplyop = (TextView) findViewById(R.id.BT_dpl_yop);
        BTyoj = (TextView) findViewById(R.id.BT_yoj);
        BTsem1 = (TextView) findViewById(R.id.BT_sem1);
        BTsem2 = (TextView) findViewById(R.id.BT_sem2);
        BTsem3 = (TextView) findViewById(R.id.BT_sem3);
        BTsem4 = (TextView) findViewById(R.id.BT_sem4);
        BTsem5 = (TextView) findViewById(R.id.BT_sem5);
        BTsem6 = (TextView) findViewById(R.id.BT_sem6);
        BTsem7 = (TextView) findViewById(R.id.BT_sem7);
        BTsem8 = (TextView) findViewById(R.id.BT_sem8);

        //m tech
        MTdplscore = (TextView) findViewById(R.id.MT_dpl_score);
        MTdplschoolname = (TextView) findViewById(R.id.MT_dpl_college_name);
        MTdplyop = (TextView) findViewById(R.id.MT_dpl_yop);
        MTbtscore = (TextView) findViewById(R.id.MT_bt_score);
        MTbtuniversity = (TextView) findViewById(R.id.MT_bt_university);
        MTbtcollegename = (TextView) findViewById(R.id.MT_bt_collage_name);
        MTbtyop = (TextView) findViewById(R.id.MT_bt_yop);
        MTyoj = (TextView) findViewById(R.id.MT_yoj);
        MTsem1 = (TextView) findViewById(R.id.MT_sem1);
        MTsem2 = (TextView) findViewById(R.id.MT_sem2);
        MTsem3 = (TextView) findViewById(R.id.MT_sem3);
        MTsem4 = (TextView) findViewById(R.id.MT_sem4);

        //degree
        Degcourse = (TextView) findViewById(R.id.DEG_course);
        Degyoj = (TextView) findViewById(R.id.DEG_yoj);
        Degsem1 = (TextView) findViewById(R.id.DEG_sem1);
        Degsem2 = (TextView) findViewById(R.id.DEG_sem2);
        Degsem3 = (TextView) findViewById(R.id.DEG_sem3);
        Degsem4 = (TextView) findViewById(R.id.DEG_sem4);
        Degsem5 = (TextView) findViewById(R.id.DEG_sem5);
        Degsem6 = (TextView) findViewById(R.id.DEG_sem6);

        //mca
        MCAdegcourse = (TextView) findViewById(R.id.MCA_deg_course);
        MCAdeguniversity = (TextView) findViewById(R.id.MCA_deg_university);
        MCAdegyop = (TextView) findViewById(R.id.MCA_deg_yop);
        MCAdegscore = (TextView) findViewById(R.id.MCA_deg_score);
        MCAdegcollegename = (TextView) findViewById(R.id.MCA_deg_college_name);
        MCAyoj = (TextView) findViewById(R.id.MCA_yoj);
        MCAsem1 = (TextView) findViewById(R.id.MCA_sem1);
        MCAsem2 = (TextView) findViewById(R.id.MCA_sem2);
        MCAsem3 = (TextView) findViewById(R.id.MCA_sem3);
        MCAsem4 = (TextView) findViewById(R.id.MCA_sem4);
        MCAsem5 = (TextView) findViewById(R.id.MCA_sem5);
        MCAsem6 = (TextView) findViewById(R.id.MCA_sem6);

        //mba and mcom
        MMdegscore = (TextView) findViewById(R.id.MM_deg_score);
        MMdeguniversity = (TextView) findViewById(R.id.MM_deg_university);
        MMdegcollegename = (TextView) findViewById(R.id.MM_deg_college_name);
        MMdegyop = (TextView) findViewById(R.id.MM_deg_yop);
        MMyoj = (TextView) findViewById(R.id.MM_yoj);
        MMsem1 = (TextView) findViewById(R.id.MM_sem1);
        MMsem2 = (TextView) findViewById(R.id.MM_sem2);
        MMsem3 = (TextView) findViewById(R.id.MM_sem3);
        MMsem4 = (TextView) findViewById(R.id.MM_sem4);

        //card view
        btechView = (CardView) findViewById(R.id.CardBtech);
        mtechView = (CardView) findViewById(R.id.CardMtech);
        degreeView = (CardView) findViewById(R.id.CardDegree);
        mcaView = (CardView) findViewById(R.id.CardMca);
        mmView = (CardView) findViewById(R.id.CardMM);

        backlogView = (LinearLayout) findViewById(R.id.backlogView);

        btechView.setVisibility(View.GONE);
        mtechView.setVisibility(View.GONE);
        degreeView.setVisibility(View.GONE);
        mcaView.setVisibility(View.GONE);
        mmView.setVisibility(View.GONE);

        mAccept = (Button) findViewById(R.id.accept);
        mReject = (Button) findViewById(R.id.reject);

    }

    public void setProfileView() {
        PDname.setText(pdname);
        PDgender.setText(pdgender);
        PDdob.setText(pddob);
        PDphoneno.setText(pdphoneno);
        PDemail.setText(pdemail);
        PDcourse.setText(pdcourse);
        PDbranch.setText(pdbranch);
        PDyeargap.setText(pdyeargap);
        PDworkxp.setText(pdworkxp);
        PDcaddress.setText(pdcaddress);
        PDpaddress.setText(pdpaddress);
    }

    public void setAcademicView() {
        AD10score.setText(ad12score);
        AD10board.setText(ad10board);
        AD10schoolname.setText(ad10schoolname);
        AD10yop.setText(ad10yop);
        AD12score.setText(ad12score);
        AD12board.setText(ad12board);
        AD12schoolname.setText(ad12schoolname);
        AD12yop.setText(ad12yop);
    }

    public void setCourseView() {
        if (pdcourse.equals("btech")) {
            btechView.setVisibility(View.VISIBLE);
            try {
                BTdplScore.setText(courseObject.getString("diploma_per"));
                BTdplschoolname.setText(courseObject.getString("diploma_cname"));
                BTdplyop.setText(courseObject.getString("diploma_yop"));
                BTyoj.setText(courseObject.getString("bt_yoj"));
                BTsem1.setText(courseObject.getString("bt_sem1"));
                BTsem2.setText(courseObject.getString("bt_sem2"));
                BTsem3.setText(courseObject.getString("bt_sem3"));
                BTsem4.setText(courseObject.getString("bt_sem4"));
                BTsem5.setText(courseObject.getString("bt_sem5"));
                BTsem6.setText(courseObject.getString("bt_sem6"));
                BTsem7.setText(courseObject.getString("bt_sem7"));
                BTsem8.setText(courseObject.getString("bt_sem8"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else if (pdcourse.equals("mtech")) {
            mtechView.setVisibility(View.VISIBLE);
            try {
                MTdplscore.setText(courseObject.getString("diploma_per"));
                MTdplschoolname.setText(courseObject.getString("diploma_cname"));
                MTdplyop.setText(courseObject.getString("diploma_yop"));
                MTbtscore.setText(courseObject.getString("bt_per"));
                MTbtuniversity.setText(courseObject.getString("bt_university"));
                MTbtcollegename.setText(courseObject.getString("bt_cname"));
                MTbtyop.setText(courseObject.getString("bt_yop"));
                MTyoj.setText(courseObject.getString("mt_yoj"));
                MTsem1.setText(courseObject.getString("mt_sem1"));
                MTsem2.setText(courseObject.getString("mt_sem2"));
                MTsem3.setText(courseObject.getString("mt_sem3"));
                MTsem4.setText(courseObject.getString("mt_sem4"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else if (pdcourse.equals("degree")) {
            degreeView.setVisibility(View.VISIBLE);
            try {
                Degcourse.setText(courseObject.getString("degree_coursename"));
                Degyoj.setText(courseObject.getString("degree_yoj"));
                Degsem1.setText(courseObject.getString("degree_sem1"));
                Degsem2.setText(courseObject.getString("degree_sem2"));
                Degsem3.setText(courseObject.getString("degree_sem3"));
                Degsem4.setText(courseObject.getString("degree_sem4"));
                Degsem5.setText(courseObject.getString("degree_sem5"));
                Degsem6.setText(courseObject.getString("degree_sem6"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else if (pdcourse.equals("mca")) {
            mcaView.setVisibility(View.VISIBLE);
            try {
                MCAdegscore.setText(courseObject.getString("degree_per"));
                MCAdegcourse.setText(courseObject.getString("degree_coursename"));
                MCAdeguniversity.setText(courseObject.getString("degree_university"));
                MCAdegcollegename.setText(courseObject.getString("degree_cname"));
                MCAdegyop.setText(courseObject.getString("degree_yop"));
                MCAyoj.setText(courseObject.getString("mca_yoj"));
                MCAsem1.setText(courseObject.getString("mca_sem1"));
                MCAsem2.setText(courseObject.getString("mca_sem2"));
                MCAsem3.setText(courseObject.getString("mca_sem3"));
                MCAsem4.setText(courseObject.getString("mca_sem4"));
                MCAsem5.setText(courseObject.getString("mca_sem5"));
                Degsem6.setText(courseObject.getString("mca_sem6"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else if (pdcourse.equals("mba_mcom")) {
            mmView.setVisibility(View.VISIBLE);
            try {
                MMdegscore.setText(courseObject.getString("degree_per"));
                MMdeguniversity.setText(courseObject.getString("degree_university"));
                MMdegcollegename.setText(courseObject.getString("degree_cname"));
                MMdegyop.setText(courseObject.getString("degree_yop"));
                MMyoj.setText(courseObject.getString("mba_mcom_yoj"));
                MMsem1.setText(courseObject.getString("mba_mcom_sem1"));
                MMsem2.setText(courseObject.getString("mba_mcom_sem2"));
                MMsem3.setText(courseObject.getString("mba_mcom_sem3"));
                MMsem4.setText(courseObject.getString("mba_mcom_sem4"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public void setBacklogView() {

        semister = new TextView[backlogObject.length];
        subject = new TextView[backlogObject.length];
        check = new CheckBox[backlogObject.length];

        for (int i = 0; i < backlogObject.length; i++) {

            String sem = "", name = "", ch = "";
            try {
                name = backlogObject[i].getString("sub_name");
                sem = "Semester " + backlogObject[i].getString("sub_sem");
                ch = backlogObject[i].getString("cleared");
            } catch (JSONException e) {
                e.printStackTrace();
            }

            LinearLayout parent = new LinearLayout(this);
            parent.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            parent.setOrientation(LinearLayout.HORIZONTAL);

            LinearLayout parentChild1 = new LinearLayout(this);
            parentChild1.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, 1f));
            parentChild1.setOrientation(LinearLayout.VERTICAL);

            LinearLayout parentChild2 = new LinearLayout(this);
            parentChild2.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, 3f));
            parentChild2.setOrientation(LinearLayout.VERTICAL);
            parentChild2.setGravity(Gravity.CENTER);

            semister[i] = new TextView(this);
            semister[i].setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
            semister[i].setGravity(Gravity.CENTER);
            semister[i].setText(sem);
            semister[i].setTextSize(12);
            semister[i].setTextColor(Color.parseColor("#808080"));

            subject[i] = new TextView(this);
            subject[i].setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
            subject[i].setGravity(Gravity.CENTER);
            subject[i].setText(name);
            subject[i].setTextSize(12);
            subject[i].setTextColor(Color.parseColor("#808080"));

            parentChild1.addView(semister[i]);
            parentChild1.addView(subject[i]);

            check[i] = new CheckBox(this);
            check[i].setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT));
            check[i].setGravity(Gravity.CENTER);
            check[i].setEnabled(false);
            if (ch.equals("YES")) {
                check[i].setChecked(true);
            } else {
                check[i].setChecked(false);
            }

            parentChild2.addView(check[i]);

            parent.addView(parentChild1);
            parent.addView(parentChild2);

            backlogView.addView(parent);
        }
    }

    private class GetProfile extends AsyncTask<Void, Void, Integer> {


        protected void onPreExecute() {
            authenticationError = true;
            errorMessage = "Data Courpted";
            pd = new SpotsDialog(progressDialogContext, R.style.CustomPD);
            pd.setTitle("Loading...");
            pd.setCancelable(false);
            pd.show();
        }

        protected Integer doInBackground(Void... params) {
            try {
                URL url = new URL(Constants.URLs.PLACEMENT_BASE + Constants.URLs.PLACEMENT_MENTOR_VIEWPROFILE);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setDoInput(true);
                connection.setDoOutput(true);
                connection.setRequestMethod("POST");

                Uri.Builder _data = new Uri.Builder().appendQueryParameter("UserID", SRN).appendQueryParameter("table", "profile");
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
                        pdname = jsonObject.getString("name");
                        pdgender = jsonObject.getString("gender");
                        pddob = jsonObject.getString("dob");
                        pdphoneno = jsonObject.getString("PhoneNo");
                        pdcaddress = jsonObject.getString("CurrentAddress");
                        pdpaddress = jsonObject.getString("PermanentAddress");
                        pdemail = jsonObject.getString("email");
                        pdbranch = jsonObject.getString("branch");
                        pdcourse = jsonObject.getString("course");
                        pdyeargap = jsonObject.getString("yeargap");
                        pdworkxp = jsonObject.getString("workexperience");
                        pdbacklog = jsonObject.getString("noOfBacklog");
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
                Toast.makeText(getApplicationContext(), errorMessage, Toast.LENGTH_LONG).show();
            } else {
                setProfileView();
                GetAcademicDetails getAcademicDetails = new GetAcademicDetails();
                getAcademicDetails.execute();
            }
        }
    }

    private class GetAcademicDetails extends AsyncTask<Void, Void, Integer> {


        protected void onPreExecute() {
            authenticationError = true;
            errorMessage = "Data Courpted";
            pd = new SpotsDialog(progressDialogContext, R.style.CustomPD);
            pd.setTitle("Loading...");
            pd.setCancelable(false);
            pd.show();
        }

        protected Integer doInBackground(Void... params) {
            try {
                URL url = new URL(Constants.URLs.PLACEMENT_BASE + Constants.URLs.PLACEMENT_MENTOR_VIEWPROFILE);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setDoInput(true);
                connection.setDoOutput(true);
                connection.setRequestMethod("POST");

                Uri.Builder _data = new Uri.Builder().appendQueryParameter("UserID", SRN).appendQueryParameter("table", "academic_details");
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
                        ad10score = jsonObject.getString("tenth_per");
                        ad10board = jsonObject.getString("tenth_board");
                        ad10schoolname = jsonObject.getString("tenth_sname");
                        ad10yop = jsonObject.getString("tenth_yop");
                        ad12score = jsonObject.getString("twelth_per");
                        ad12board = jsonObject.getString("twelth_board");
                        ad12schoolname = jsonObject.getString("twelth_sname");
                        ad12yop = jsonObject.getString("twelth_yop");
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
                Toast.makeText(getApplicationContext(), errorMessage, Toast.LENGTH_LONG).show();
            } else {
                setAcademicView();
                GetCourseDetails getCourseDetails = new GetCourseDetails();
                getCourseDetails.execute();
            }
        }
    }

    private class GetCourseDetails extends AsyncTask<Void, Void, Integer> {


        protected void onPreExecute() {
            authenticationError = true;
            errorMessage = "Data Courpted";
            pd = new SpotsDialog(progressDialogContext, R.style.CustomPD);
            pd.setTitle("Loading...");
            pd.setCancelable(false);
            pd.show();
        }

        protected Integer doInBackground(Void... params) {
            try {
                URL url = new URL(Constants.URLs.PLACEMENT_BASE + Constants.URLs.PLACEMENT_MENTOR_VIEWPROFILE);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setDoInput(true);
                connection.setDoOutput(true);
                connection.setRequestMethod("POST");

                Uri.Builder _data = new Uri.Builder().appendQueryParameter("UserID", SRN).appendQueryParameter("table", pdcourse);
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
                        courseObject = jsonObject;
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
                Toast.makeText(getApplicationContext(), errorMessage, Toast.LENGTH_LONG).show();
            } else {
                setCourseView();
                GetBackLog getBackLog = new GetBackLog();
                getBackLog.execute();
            }
        }
    }

    private class GetBackLog extends AsyncTask<Void, Void, Integer> {


        protected void onPreExecute() {
            authenticationError = true;
            errorMessage = "Data Courpted";
            pd = new SpotsDialog(progressDialogContext, R.style.CustomPD);
            pd.setTitle("Loading...");
            pd.setCancelable(false);
            pd.show();
        }

        protected Integer doInBackground(Void... params) {
            try {
                URL url = new URL(Constants.URLs.PLACEMENT_BASE + Constants.URLs.PLACEMENT_MENTOR_VIEWPROFILE);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setDoInput(true);
                connection.setDoOutput(true);
                connection.setRequestMethod("POST");

                Uri.Builder _data = new Uri.Builder().appendQueryParameter("UserID", SRN).appendQueryParameter("table", "back_log");
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

                Log.d("return from server", jsonResults.toString());
                authenticationError = jsonResults.toString().contains("Authentication Error");

                if (authenticationError) {
                    errorMessage = jsonResults.toString();
                } else {
                    JSONArray jsonArray = new JSONArray(jsonResults.toString());
                    backlogObject = new JSONObject[jsonArray.length()];
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        backlogObject[i] = jsonObject;
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
                Toast.makeText(getApplicationContext(), errorMessage, Toast.LENGTH_LONG).show();
            } else {
                setBacklogView();
            }
        }
    }

    private class UpdateStatus extends AsyncTask<Void, Void, Integer> {


        protected void onPreExecute() {
            authenticationError = true;
            errorMessage = "Data Courpted";
            pd = new SpotsDialog(progressDialogContext, R.style.CustomPD);
            pd.setTitle("Loading...");
            pd.setCancelable(false);
            pd.show();
        }

        protected Integer doInBackground(Void... params) {
            try {
                URL url = new URL(Constants.URLs.PLACEMENT_BASE + Constants.URLs.PLACEMENT_MENTOR);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setDoInput(true);
                connection.setDoOutput(true);
                connection.setRequestMethod("POST");

                Uri.Builder _data = new Uri.Builder().appendQueryParameter("StudentID", SRN).appendQueryParameter("requestType", updateStatus);
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
                        errorMessage = status;
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
                Toast.makeText(getApplicationContext(), errorMessage, Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(getApplicationContext(), errorMessage, Toast.LENGTH_LONG).show();
                finish();
            }
        }
    }


}
