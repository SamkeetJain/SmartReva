package com.samkeet.smartreva.Placement2;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutCompat;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.GridLayoutAnimationController;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Switch;
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

public class Placement2AcademicDetails extends AppCompatActivity {

    public Context progressDialogContext;
    public SpotsDialog pd;
    public boolean authenticationError;
    public String errorMessage;

    public JSONObject courseObject;
    public JSONObject[] backlogObject;

    public EditText PDname, PDgender, PDdob, PDphoneno, PDemail, PDcourse, PDbranch, PDyeargap, PDworkxp, PDcaddress, PDpaddress;
    public EditText AD10score, AD10board, AD10schoolname, AD10yop, AD12score, AD12board, AD12schoolname, AD12yop;
    public EditText BTdplScore, BTdplschoolname, BTdplyop, BTyoj, BTsem1, BTsem2, BTsem3, BTsem4, BTsem5, BTsem6, BTsem7, BTsem8;
    public EditText MTdplscore, MTdplschoolname, MTdplyop, MTbtscore, MTbtuniversity, MTbtcollegename, MTbtyop, MTyoj, MTsem1, MTsem2, MTsem3, MTsem4;
    public EditText Degcourse, Degyoj, Degsem1, Degsem2, Degsem3, Degsem4, Degsem5, Degsem6;
    public EditText MCAdegcourse, MCAdeguniversity, MCAdegyop, MCAdegscore, MCAdegcollegename, MCAyoj, MCAsem1, MCAsem2, MCAsem3, MCAsem4, MCAsem5, MCAsem6;
    public EditText MMdegscore, MMdeguniversity, MMdegcollegename, MMdegyop, MMyoj, MMsem1, MMsem2, MMsem3, MMsem4;

    public String pdname, pdgender, pddob, pdphoneno, pdemail, pdcourse, pdbranch, pdyeargap, pdworkxp, pdcaddress, pdpaddress, pdbacklog;
    public String ad10score, ad10board, ad10schoolname, ad10yop, ad12score, ad12board, ad12schoolname, ad12yop;
    public String btdplScore, btdplschoolname, btdplyop, btyoj, btsem1, btsem2, btsem3, btsem4, btsem5, btsem6, btsem7, btsem8;
    public String mtdplscore, mtdplschoolname, mtdplyop, mtbtscore, mtbtuniversity, mtbtcollegename, mtbtyop, mtyoj, mtsem1, mtsem2, mtsem3, mtsem4;
    public String degcourse, degyoj, degsem1, degsem2, degsem3, degsem4, degsem5, degsem6;
    public String mcadegcourse, mcadeguniversity, mcadegyop, mcadegscore, mcadegcollegename, mcayoj, mcasem1, mcasem2, mcasem3, mcasem4, mcasem5, mcasem6;
    public String mmdegscore, mmdeguniversity, mmdegcollegename, mmdegyop, mmyoj, mmsem1, mmsem2, mmsem3, mmsem4;

    public CardView btechView, mtechView, degreeView, mcaView, mmView;
    public LinearLayout backlogView;
    public EditText[] semister, subject;
    public CheckBox[] check;
    public Button mSubmit;
    public Boolean editable = false;

    public Uri.Builder form3;
    public String URLFORM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_placement2_academic_details);
        progressDialogContext = this;

        setViewByID();

        mSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!editable) {
                    editable = !editable;
                    setEditable(editable);
                    mSubmit.setText("SAVE");
                } else {

                    checkData();
                }
            }
        });
        GetProfile getProfile = new GetProfile();
        getProfile.execute();

    }

    public void sendData() {
        if (Constants.Methods.networkState(getApplicationContext(), (ConnectivityManager) getSystemService(getApplicationContext().CONNECTIVITY_SERVICE))) {
            editable = !editable;
            setEditable(editable);
            mSubmit.setText("EDIT");
            PutRegForm1 putRegForm1 = new PutRegForm1();
            putRegForm1.execute();
        }
    }

    public void BackButton(View v) {
        finish();
    }

    public void setViewByID() {
        //persnal details
        PDname = (EditText) findViewById(R.id.PD_name);
        PDgender = (EditText) findViewById(R.id.PD_gender);
        PDdob = (EditText) findViewById(R.id.PD_dob);
        PDphoneno = (EditText) findViewById(R.id.PD_phoneno);
        PDemail = (EditText) findViewById(R.id.PD_email);
        PDcourse = (EditText) findViewById(R.id.PD_course);
        PDbranch = (EditText) findViewById(R.id.PD_branch);
        PDyeargap = (EditText) findViewById(R.id.PD_yeargap);
        PDworkxp = (EditText) findViewById(R.id.PD_workxp);
        PDcaddress = (EditText) findViewById(R.id.PD_current_address);
        PDpaddress = (EditText) findViewById(R.id.PD_permanent_address);

        //academic details
        AD10score = (EditText) findViewById(R.id.AD_10_score);
        AD10board = (EditText) findViewById(R.id.AD_10_board);
        AD10schoolname = (EditText) findViewById(R.id.AD_10_school_name);
        AD10yop = (EditText) findViewById(R.id.AD_10_yop);
        AD12score = (EditText) findViewById(R.id.AD_12_score);
        AD12board = (EditText) findViewById(R.id.AD_12_board);
        AD12schoolname = (EditText) findViewById(R.id.AD_12_school_name);
        AD12yop = (EditText) findViewById(R.id.AD_12_yop);

        //b tech
        BTdplScore = (EditText) findViewById(R.id.BT_dpl_score);
        BTdplschoolname = (EditText) findViewById(R.id.BT_dpl_school_name);
        BTdplyop = (EditText) findViewById(R.id.BT_dpl_yop);
        BTyoj = (EditText) findViewById(R.id.BT_yoj);
        BTsem1 = (EditText) findViewById(R.id.BT_sem1);
        BTsem2 = (EditText) findViewById(R.id.BT_sem2);
        BTsem3 = (EditText) findViewById(R.id.BT_sem3);
        BTsem4 = (EditText) findViewById(R.id.BT_sem4);
        BTsem5 = (EditText) findViewById(R.id.BT_sem5);
        BTsem6 = (EditText) findViewById(R.id.BT_sem6);
        BTsem7 = (EditText) findViewById(R.id.BT_sem7);
        BTsem8 = (EditText) findViewById(R.id.BT_sem8);

        //m tech
        MTdplscore = (EditText) findViewById(R.id.MT_dpl_score);
        MTdplschoolname = (EditText) findViewById(R.id.MT_dpl_college_name);
        MTdplyop = (EditText) findViewById(R.id.MT_dpl_yop);
        MTbtscore = (EditText) findViewById(R.id.MT_bt_score);
        MTbtuniversity = (EditText) findViewById(R.id.MT_bt_university);
        MTbtcollegename = (EditText) findViewById(R.id.MT_bt_collage_name);
        MTbtyop = (EditText) findViewById(R.id.MT_bt_yop);
        MTyoj = (EditText) findViewById(R.id.MT_yoj);
        MTsem1 = (EditText) findViewById(R.id.MT_sem1);
        MTsem2 = (EditText) findViewById(R.id.MT_sem2);
        MTsem3 = (EditText) findViewById(R.id.MT_sem3);
        MTsem4 = (EditText) findViewById(R.id.MT_sem4);

        //degree
        Degcourse = (EditText) findViewById(R.id.DEG_course);
        Degyoj = (EditText) findViewById(R.id.DEG_yoj);
        Degsem1 = (EditText) findViewById(R.id.DEG_sem1);
        Degsem2 = (EditText) findViewById(R.id.DEG_sem2);
        Degsem3 = (EditText) findViewById(R.id.DEG_sem3);
        Degsem4 = (EditText) findViewById(R.id.DEG_sem4);
        Degsem5 = (EditText) findViewById(R.id.DEG_sem5);
        Degsem6 = (EditText) findViewById(R.id.DEG_sem6);

        //mca
        MCAdegcourse = (EditText) findViewById(R.id.MCA_deg_course);
        MCAdeguniversity = (EditText) findViewById(R.id.MCA_deg_university);
        MCAdegyop = (EditText) findViewById(R.id.MCA_deg_yop);
        MCAdegscore = (EditText) findViewById(R.id.MCA_deg_score);
        MCAdegcollegename = (EditText) findViewById(R.id.MCA_deg_college_name);
        MCAyoj = (EditText) findViewById(R.id.MCA_yoj);
        MCAsem1 = (EditText) findViewById(R.id.MCA_sem1);
        MCAsem2 = (EditText) findViewById(R.id.MCA_sem2);
        MCAsem3 = (EditText) findViewById(R.id.MCA_sem3);
        MCAsem4 = (EditText) findViewById(R.id.MCA_sem4);
        MCAsem5 = (EditText) findViewById(R.id.MCA_sem5);
        MCAsem6 = (EditText) findViewById(R.id.MCA_sem6);

        //mba and mcom
        MMdegscore = (EditText) findViewById(R.id.MM_deg_score);
        MMdeguniversity = (EditText) findViewById(R.id.MM_deg_university);
        MMdegcollegename = (EditText) findViewById(R.id.MM_deg_college_name);
        MMdegyop = (EditText) findViewById(R.id.MM_deg_yop);
        MMyoj = (EditText) findViewById(R.id.MM_yoj);
        MMsem1 = (EditText) findViewById(R.id.MM_sem1);
        MMsem2 = (EditText) findViewById(R.id.MM_sem2);
        MMsem3 = (EditText) findViewById(R.id.MM_sem3);
        MMsem4 = (EditText) findViewById(R.id.MM_sem4);

        //card view
        btechView = (CardView) findViewById(R.id.CardBtech);
        mtechView = (CardView) findViewById(R.id.CardMtech);
        degreeView = (CardView) findViewById(R.id.CardDegree);
        mcaView = (CardView) findViewById(R.id.CardMca);
        mmView = (CardView) findViewById(R.id.CardMM);

//        backlogView = (LinearLayout) findViewById(R.id.backlogView);

        btechView.setVisibility(View.GONE);
        mtechView.setVisibility(View.GONE);
        degreeView.setVisibility(View.GONE);
        mcaView.setVisibility(View.GONE);
        mmView.setVisibility(View.GONE);

        mSubmit = (Button) findViewById(R.id.submit);

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

        setEditable(false);
    }

    public void setBacklogView() {

        semister = new EditText[backlogObject.length];
        subject = new EditText[backlogObject.length];
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

            semister[i] = new EditText(this);
            semister[i].setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
            semister[i].setGravity(Gravity.CENTER);
            semister[i].setText(sem);
            semister[i].setTextSize(12);
            semister[i].setTextColor(Color.parseColor("#808080"));

            subject[i] = new EditText(this);
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

        setEditable(editable);

    }

    public void setEditable(boolean edit) {
        PDname.setEnabled(false);
        PDgender.setEnabled(false);
        PDdob.setEnabled(false);
        PDphoneno.setEnabled(edit);
        PDemail.setEnabled(edit);
        PDcourse.setEnabled(false);
        PDbranch.setEnabled(false);
        PDyeargap.setEnabled(edit);
        PDworkxp.setEnabled(edit);
        PDcaddress.setEnabled(edit);
        PDpaddress.setEnabled(edit);

        //academic details
        AD10score.setEnabled(false);
        AD10board.setEnabled(false);
        AD10schoolname.setEnabled(false);
        AD10yop.setEnabled(false);
        AD12score.setEnabled(false);
        AD12board.setEnabled(false);
        AD12schoolname.setEnabled(false);
        AD12yop.setEnabled(false);

        //b tech
        BTdplScore.setEnabled(edit);
        BTdplschoolname.setEnabled(edit);
        BTdplyop.setEnabled(edit);
        BTyoj.setEnabled(edit);
        BTsem1.setEnabled(edit);
        BTsem2.setEnabled(edit);
        BTsem3.setEnabled(edit);
        BTsem4.setEnabled(edit);
        BTsem5.setEnabled(edit);
        BTsem6.setEnabled(edit);
        BTsem7.setEnabled(edit);
        BTsem8.setEnabled(edit);

        //m tech
        MTdplscore.setEnabled(edit);
        MTdplschoolname.setEnabled(edit);
        MTdplyop.setEnabled(edit);
        MTbtscore.setEnabled(edit);
        MTbtuniversity.setEnabled(edit);
        MTbtcollegename.setEnabled(edit);
        MTbtyop.setEnabled(edit);
        MTyoj.setEnabled(edit);
        MTsem1.setEnabled(edit);
        MTsem2.setEnabled(edit);
        MTsem3.setEnabled(edit);
        MTsem4.setEnabled(edit);

        //degree
        Degcourse.setEnabled(edit);
        Degyoj.setEnabled(edit);
        Degsem1.setEnabled(edit);
        Degsem2.setEnabled(edit);
        Degsem3.setEnabled(edit);
        Degsem4.setEnabled(edit);
        Degsem5.setEnabled(edit);
        Degsem6.setEnabled(edit);

        //mca
        MCAdegcourse.setEnabled(edit);
        MCAdeguniversity.setEnabled(edit);
        MCAdegyop.setEnabled(edit);
        MCAdegscore.setEnabled(edit);
        MCAdegcollegename.setEnabled(edit);
        MCAyoj.setEnabled(edit);
        MCAsem1.setEnabled(edit);
        MCAsem2.setEnabled(edit);
        MCAsem3.setEnabled(edit);
        MCAsem4.setEnabled(edit);
        MCAsem5.setEnabled(edit);
        MCAsem6.setEnabled(edit);

        //mba and mcom
        MMdegscore.setEnabled(edit);
        MMdeguniversity.setEnabled(edit);
        MMdegcollegename.setEnabled(edit);
        MMdegyop.setEnabled(edit);
        MMyoj.setEnabled(edit);
        MMsem1.setEnabled(edit);
        MMsem2.setEnabled(edit);
        MMsem3.setEnabled(edit);
        MMsem4.setEnabled(edit);

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
                URL url = new URL(Constants.URLs.PLACEMENT_BASE + Constants.URLs.PLACEMENT_VIEW);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setDoInput(true);
                connection.setDoOutput(true);
                connection.setRequestMethod("POST");

                Uri.Builder _data = new Uri.Builder().appendQueryParameter("token", Constants.SharedPreferenceData.getTOKEN()).appendQueryParameter("table", "profile");
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
                URL url = new URL(Constants.URLs.PLACEMENT_BASE + Constants.URLs.PLACEMENT_VIEW);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setDoInput(true);
                connection.setDoOutput(true);
                connection.setRequestMethod("POST");

                Uri.Builder _data = new Uri.Builder().appendQueryParameter("token", Constants.SharedPreferenceData.getTOKEN()).appendQueryParameter("table", "academic_details");
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
                URL url = new URL(Constants.URLs.PLACEMENT_BASE + Constants.URLs.PLACEMENT_VIEW);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setDoInput(true);
                connection.setDoOutput(true);
                connection.setRequestMethod("POST");

                Uri.Builder _data = new Uri.Builder().appendQueryParameter("token", Constants.SharedPreferenceData.getTOKEN()).appendQueryParameter("table", pdcourse);
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
//                GetBackLog getBackLog = new GetBackLog();
//                getBackLog.execute();
            }
        }
    }

//    private class GetBackLog extends AsyncTask<Void, Void, Integer> {
//
//
//        protected void onPreExecute() {
//            authenticationError = true;
//            errorMessage = "Data Courpted";
//            pd = new SpotsDialog(progressDialogContext, R.style.CustomPD);
//            pd.setTitle("Loading...");
//            pd.setCancelable(false);
//            pd.show();
//        }
//
//        protected Integer doInBackground(Void... params) {
//            try {
//                URL url = new URL(Constants.URLs.PLACEMENT_BASE + Constants.URLs.PLACEMENT_VIEW);
//                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//                connection.setDoInput(true);
//                connection.setDoOutput(true);
//                connection.setRequestMethod("POST");
//
//                Uri.Builder _data = new Uri.Builder().appendQueryParameter("token", Constants.SharedPreferenceData.getTOKEN()).appendQueryParameter("table", "back_log");
//                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(connection.getOutputStream(), "UTF-8"));
//                writer.write(_data.build().getEncodedQuery());
//                writer.flush();
//                writer.close();
//
//                InputStreamReader in = new InputStreamReader(connection.getInputStream());
//
//                StringBuilder jsonResults = new StringBuilder();
//                // Load the results into a StringBuilder
//                int read;
//                char[] buff = new char[1024];
//                while ((read = in.read(buff)) != -1) {
//                    jsonResults.append(buff, 0, read);
//                }
//                connection.disconnect();
//
//                authenticationError = jsonResults.toString().contains("Authentication Error");
//
//                if (authenticationError) {
//                    errorMessage = jsonResults.toString();
//                } else {
//                    JSONArray jsonArray = new JSONArray(jsonResults.toString());
//                    backlogObject = new JSONObject[jsonArray.length()];
//                    for (int i = 0; i < jsonArray.length(); i++) {
//                        JSONObject jsonObject = jsonArray.getJSONObject(i);
//                        backlogObject[i] = jsonObject;
//                    }
//                }
//
//                return 1;
//
//            } catch (Exception ex) {
//                ex.printStackTrace();
//            }
//
//            return 1;
//        }
//
//        protected void onPostExecute(Integer result) {
//            if (pd != null) {
//                pd.dismiss();
//            }
//            if (authenticationError) {
//                Toast.makeText(getApplicationContext(), errorMessage, Toast.LENGTH_LONG).show();
//            } else {
//                setBacklogView();
//            }
//        }
//    }

    public void checkData() {
        pdname = PDname.getText().toString().trim();
        pdgender = PDgender.getText().toString().trim();
        pddob = PDdob.getText().toString().trim();
        pdphoneno = PDphoneno.getText().toString().trim();
        pdemail = PDemail.getText().toString().trim();
        pdcaddress = PDcaddress.getText().toString().trim();
        pdpaddress = PDpaddress.getText().toString().trim();
        pdbranch = PDbranch.getText().toString().trim();
        pdcourse = PDcourse.getText().toString().trim();
        pdyeargap = PDyeargap.getText().toString().trim();
        pdworkxp = PDworkxp.getText().toString().trim();

        ad10score = AD10score.getText().toString().trim();
        ad10schoolname = AD10schoolname.getText().toString().trim();
        ad10board = AD10board.getText().toString().trim();
        ad10yop = AD10yop.getText().toString().trim();
        ad12score = AD12score.getText().toString().trim();
        ad12schoolname = AD12schoolname.getText().toString().trim();
        ad12board = AD12board.getText().toString().trim();
        ad12yop = AD12yop.getText().toString().trim();

        if (pdcourse.equals("btech")) {
            btdplScore = BTdplScore.getText().toString().trim();
            btdplschoolname = BTdplschoolname.getText().toString().trim();
            btdplyop = BTdplyop.getText().toString().trim();
            btyoj = BTyoj.getText().toString().trim();
            btsem1 = BTsem1.getText().toString().trim();
            btsem2 = BTsem2.getText().toString().trim();
            btsem3 = BTsem3.getText().toString().trim();
            btsem4 = BTsem4.getText().toString().trim();
            btsem5 = BTsem5.getText().toString().trim();
            btsem6 = BTsem6.getText().toString().trim();
            btsem7 = BTsem7.getText().toString().trim();
            btsem8 = BTsem8.getText().toString().trim();

            validbt();
        } else if (pdcourse.equals("mtech")) {
            mtdplscore = MTdplscore.getText().toString().trim();
            mtdplschoolname = MTdplschoolname.getText().toString().trim();
            mtdplyop = MTdplyop.getText().toString().trim();
            mtbtscore = MTbtscore.getText().toString().trim();
            mtbtuniversity = MTbtuniversity.getText().toString().trim();
            mtbtcollegename = MTbtcollegename.getText().toString().trim();
            mtbtyop = MTbtyop.getText().toString().trim();
            mtyoj = MTyoj.getText().toString().trim();
            mtsem1 = MTsem1.getText().toString().trim();
            mtsem2 = MTsem2.getText().toString().trim();
            mtsem3 = MTsem3.getText().toString().trim();
            mtsem4 = MTsem4.getText().toString().trim();

            validmt();

        } else if (pdcourse.equals("degree")) {
            degcourse = Degcourse.getText().toString().trim();
            degyoj = Degyoj.getText().toString().trim();
            degsem1 = Degsem1.getText().toString().trim();
            degsem2 = Degsem2.getText().toString().trim();
            degsem3 = Degsem3.getText().toString().trim();
            degsem4 = Degsem4.getText().toString().trim();
            degsem5 = Degsem5.getText().toString().trim();
            degsem6 = Degsem6.getText().toString().trim();

            validDeg();

        } else if (pdcourse.equals("mca")) {
            mcadegscore = MCAdegscore.getText().toString().trim();
            mcadegcourse = MCAdegcourse.getText().toString().trim();
            mcadeguniversity = MCAdeguniversity.getText().toString().trim();
            mcadegcollegename = MCAdegcollegename.getText().toString().trim();
            mcadegyop = MCAdegyop.getText().toString().trim();
            mcayoj = MCAyoj.getText().toString().trim();
            mcasem1 = MCAsem1.getText().toString().trim();
            mcasem2 = MCAsem2.getText().toString().trim();
            mcasem3 = MCAsem3.getText().toString().trim();
            mcasem4 = MCAsem4.getText().toString().trim();
            mcasem5 = MCAsem5.getText().toString().trim();
            mcasem6 = MCAsem6.getText().toString().trim();

            validMca();
        } else if (pdcourse.equals("mba_mcom")) {
            mmdegscore = MMdegscore.getText().toString().trim();
            mmdeguniversity = MMdeguniversity.getText().toString().trim();
            mmdegcollegename = MMdegcollegename.getText().toString().trim();
            mmdegyop = MMdegyop.getText().toString().trim();
            mmyoj = MMyoj.getText().toString().trim();
            mmsem1 = MMsem1.getText().toString().trim();
            mmsem2 = MMsem2.getText().toString().trim();
            mmsem3 = MMsem3.getText().toString().trim();
            mmsem4 = MMsem4.getText().toString().trim();

            validmm();

        }


    }

    public void validmm() {
        if (!validpd()) {
            return;
        }
        if (!validScore(mmdegscore, MMdegscore)) {
            return;
        }
        if (!validUniversity(mmdeguniversity, MMdeguniversity)) {
            return;
        }
        if (!validSchoolname(mmdegcollegename, MMdegcollegename)) {
            return;
        }
        if (!validYear(mmdegyop, MMdegyop)) {
            return;
        }
        if (!validYear(mmyoj, MMyoj)) {
            return;
        }
        if (!validSem(mmsem1, MMsem1)) {
            return;
        }
        if (!validSem(mmsem2, MMsem2)) {
            return;
        }
        if (!validSem(mmsem3, MMsem3)) {
            return;
        }
        if (!validSem(mmsem4, MMsem4)) {
            return;
        }
        sendData();
    }

    public void validMca() {
        if (!validpd()) {
            return;
        }
        if (!validScore(mcadegscore, MCAdegscore)) {
            return;
        }
        if (!validCourse(mcadegcourse, MCAdegcourse)) {
            return;
        }
        if (!validUniversity(mcadeguniversity, MCAdeguniversity)) {
            return;
        }
        if (!validSchoolname(mcadegcollegename, MCAdegcollegename)) {
            return;
        }
        if (!validYear(mcadegyop, MCAdegyop)) {
            return;
        }
        if (!validYear(mcayoj, MCAyoj)) {
            return;
        }
        if (!validSem(mcasem1, MCAsem1)) {
            return;
        }
        if (!validSem(mcasem2, MCAsem2)) {
            return;
        }
        if (!validSem(mcasem3, MCAsem3)) {
            return;
        }
        if (!validSem(mcasem4, MCAsem4)) {
            return;
        }
        if (!validSem(mcasem5, MCAsem5)) {
            return;
        }
        if (!validSem(mcasem6, MCAsem6)) {
            return;
        }
        sendData();
    }

    public void validDeg() {
        if (!validpd()) {
            return;
        }
        if (!validCourse(degcourse, Degcourse)) {
            return;
        }
        if (!validYear(degyoj, Degyoj)) {
            return;
        }
        if (!validSem(degsem1, Degsem1)) {
            return;
        }
        if (!validSem(degsem2, Degsem2)) {
            return;
        }
        if (!validSem(degsem3, Degsem3)) {
            return;
        }
        if (!validSem(degsem4, Degsem4)) {
            return;
        }
        if (!validSem(degsem5, Degsem5)) {
            return;
        }
        if (!validSem(degsem6, Degsem6)) {
            return;
        }
        sendData();
    }

    private boolean validCourse(String course, EditText course_et) {
        if (course.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Invalid Course Name", Toast.LENGTH_SHORT).show();
            requestFocus(course_et);
            return false;
        }
        if (Constants.Methods.checkForSpecial(course)) {
            Toast.makeText(getApplicationContext(), "Remove Special Charecters from Course Name", Toast.LENGTH_SHORT).show();
            requestFocus(course_et);
            return false;
        }
        if (course.length() > 32) {
            Toast.makeText(getApplicationContext(), "Course Name should be less than 32 charecters", Toast.LENGTH_SHORT).show();
            requestFocus(course_et);
            return false;
        }
        return true;
    }

    public void validmt() {
        if (!validpd()) {
            return;
        }
        if (!validScore(mtdplscore, MTdplscore)) {
            return;
        }
        if (!validSchoolname(mtdplschoolname, MTdplschoolname)) {
            return;
        }
        if (!validYear(mtdplyop, MTdplyop)) {
            return;
        }
        if (!validScore(mtbtscore, MTbtscore)) {
            return;
        }
        if (!validSchoolname(mtbtcollegename, MTbtcollegename)) {
            return;
        }
        if (!validUniversity(mtbtuniversity, MTbtuniversity)) {
            return;
        }
        if (!validYear(mtbtyop, MTbtyop)) {
            return;
        }
        if (!validYear(mtyoj, MTyoj)) {
            return;
        }
        if (!validSem(mtsem1, MTsem1)) {
            return;
        }
        if (!validSem(mtsem2, MTsem2)) {
            return;
        }
        if (!validSem(mtsem3, MTsem3)) {
            return;
        }
        if (!validSem(mtsem4, MTsem4)) {
            return;
        }
        sendData();
    }

    private boolean validUniversity(String university, EditText university_et) {
        if (university.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Invalid University Name", Toast.LENGTH_SHORT).show();
            requestFocus(university_et);
            return false;
        }
        if (university.length() > 32) {
            Toast.makeText(getApplicationContext(), "University Name should be less than 32 charecters", Toast.LENGTH_SHORT).show();
            requestFocus(university_et);
            return false;
        }
        if (Constants.Methods.checkForSpecial(university)) {
            Toast.makeText(getApplicationContext(), "Remove special charecters from University Name", Toast.LENGTH_SHORT).show();
            requestFocus(university_et);
            return false;
        }
        return true;
    }

    public void validbt() {
        if (!validpd()) {
            return;
        }
        if (!validSchoolname(btdplschoolname, BTdplschoolname)) {
            return;
        }
        if (!validScore(btdplScore, BTdplScore)) {
            return;
        }
        if (!validYear(btdplyop, BTdplyop)) {
            return;
        }
        if (!validYear(btyoj, BTyoj)) {
            return;
        }
        if (!validSem(btsem1, BTsem1)) {
            return;
        }
        if (!validSem(btsem2, BTsem2)) {
            return;
        }
        if (!validSem(btsem3, BTsem3)) {
            return;
        }
        if (!validSem(btsem4, BTsem4)) {
            return;
        }
        if (!validSem(btsem5, BTsem5)) {
            return;
        }
        if (!validSem(btsem6, BTsem6)) {
            return;
        }
        if (!validSem(btsem7, BTsem7)) {
            return;
        }
        if (!validSem(btsem8, BTsem8)) {
            return;
        }
        sendData();
    }

    private boolean validSem(String sem, EditText sem_et) {
        if (sem.isEmpty() || sem.length() > 5) {
            Toast.makeText(getApplicationContext(), "Invalid Marks", Toast.LENGTH_SHORT).show();
            requestFocus(sem_et);
            return false;
        }
        if (Constants.Methods.checkForSpecial(sem)) {
            Toast.makeText(getApplicationContext(), "Remove special charecters from Marks", Toast.LENGTH_SHORT).show();
            requestFocus(sem_et);
            return false;
        }

        return true;
    }

    private boolean validYear(String year, EditText year_et) {
        if (year.isEmpty() || year.length() != 4) {
            Toast.makeText(getApplicationContext(), "Invalid Year", Toast.LENGTH_SHORT).show();
            requestFocus(year_et);
            return false;
        }
        if (Constants.Methods.checkForSpecial(year)) {
            Toast.makeText(getApplicationContext(), "Remove special charecters from Year", Toast.LENGTH_SHORT).show();
            requestFocus(year_et);
            return false;
        }
        return true;
    }

    private boolean validScore(String score, EditText score_et) {
        if (score.isEmpty() || score.length() > 5) {
            Toast.makeText(getApplicationContext(), "Invalid Score", Toast.LENGTH_SHORT).show();
            requestFocus(score_et);
            return false;
        }
        if (Constants.Methods.checkForSpecial(score)) {
            Toast.makeText(getApplicationContext(), "Remove special charecters from Score", Toast.LENGTH_SHORT).show();
            requestFocus(score_et);
            return false;
        }
        return true;
    }

    private boolean validSchoolname(String school, EditText school_et) {
        if (school.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Invalid School Name", Toast.LENGTH_SHORT).show();
            requestFocus(school_et);
            return false;
        }
        if (Constants.Methods.checkForSpecial(school)) {
            Toast.makeText(getApplicationContext(), "Remove special charecters from School Name", Toast.LENGTH_SHORT).show();
            requestFocus(school_et);
            return false;
        }
        if (school.length() > 128) {
            Toast.makeText(getApplicationContext(), "School Name should be less than 128", Toast.LENGTH_SHORT).show();
            requestFocus(school_et);
            return false;
        }
        return true;
    }

    public boolean validpd() {
        if (!validPhone(pdphoneno, PDphoneno)) {
            return false;
        }
        if (!validEmail(pdemail, PDemail)) {
            return false;
        }
        if (!validYeargap(pdyeargap, PDyeargap)) {
            return false;
        }
        if (!validAddress(pdpaddress, PDpaddress)) {
            return false;
        }
        if (!validAddress(pdcaddress, PDcaddress)) {
            return false;
        }
        return true;
    }

    private boolean validPhone(String phone, EditText phone_et) {
        if (!isValidMobilenumber(phone) || phone.isEmpty() || phone.length() < 5 || phone.length() > 15) {
            Toast.makeText(getApplicationContext(), "Invalid Phone number", Toast.LENGTH_SHORT).show();
            requestFocus(phone_et);
            return false;
        }
        if (Constants.Methods.checkForSpecial(phone)) {
            Toast.makeText(getApplicationContext(), "Remove Special Charecters from phone number", Toast.LENGTH_SHORT).show();
            requestFocus(phone_et);
            return false;
        }
        return true;
    }

    private boolean validEmail(String email, EditText email_et) {
        if (!isValidEmail(email) || email.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Invalid Email", Toast.LENGTH_SHORT).show();
            requestFocus(email_et);
            return false;
        }
        if (email.length() > 32) {
            Toast.makeText(getApplicationContext(), "Email should be less than 32 charecters", Toast.LENGTH_SHORT).show();
            requestFocus(email_et);
            return false;
        }
        if (Constants.Methods.checkForSpecial(email)) {
            Toast.makeText(getApplicationContext(), "Remove Special Charecters from Email", Toast.LENGTH_SHORT).show();
            requestFocus(email_et);
            return false;
        }
        return true;
    }

    private boolean validYeargap(String yg, EditText yg_et) {
        if (yg.isEmpty() || yg.length() > 2) {
            Toast.makeText(getApplicationContext(), "Invalid Year Gap", Toast.LENGTH_SHORT).show();
            requestFocus(yg_et);
            return false;
        }
        if (Constants.Methods.checkForSpecial(yg)) {
            Toast.makeText(getApplicationContext(), "Remove Special Charecters from Year gap", Toast.LENGTH_SHORT).show();
            requestFocus(yg_et);
            return false;
        }
        return true;
    }

    private boolean validAddress(String address, EditText address_et) {
        if (address.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Invalid Address", Toast.LENGTH_SHORT).show();
            requestFocus(address_et);
            return false;
        }
        if (Constants.Methods.checkForSpecial(address)) {
            Toast.makeText(getApplicationContext(), "Remove Special Charecters from Address", Toast.LENGTH_SHORT).show();
            requestFocus(address_et);
            return false;
        }
        if (address.length() > 256) {
            Toast.makeText(getApplicationContext(), "Address should be less than 256 charecters", Toast.LENGTH_SHORT).show();
            requestFocus(address_et);
            return false;
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

    private class PutRegForm1 extends AsyncTask<Void, Void, Integer> {

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
                java.net.URL url = new URL(Constants.URLs.PLACEMENT_BASE + Constants.URLs.PLACEMENT_PROFILE);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setDoInput(true);
                connection.setDoOutput(true);
                connection.setRequestMethod("POST");
                Uri.Builder _data = new Uri.Builder().appendQueryParameter("token", Constants.SharedPreferenceData.getTOKEN())
                        .appendQueryParameter("requestType", "put")
                        .appendQueryParameter("name", pdname)
                        .appendQueryParameter("gender", pdgender)
                        .appendQueryParameter("dob", pddob)
                        .appendQueryParameter("PhoneNo", pdphoneno)
                        .appendQueryParameter("CurrentAddress", pdcaddress)
                        .appendQueryParameter("PermanentAddress", pdcaddress)
                        .appendQueryParameter("email", pdemail)
                        .appendQueryParameter("branch", pdbranch)
                        .appendQueryParameter("course", pdcourse)
                        .appendQueryParameter("yeargap", pdyeargap)
                        .appendQueryParameter("workexperience", pdworkxp)
                        .appendQueryParameter("backlogs", pdbacklog);
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
                Toast.makeText(getApplicationContext(), errorMessage, Toast.LENGTH_SHORT).show();
            } else {
                putRegForm3();
            }

        }
    }

//    private class PutRegForm2 extends AsyncTask<Void, Void, Integer> {
//
//        protected void onPreExecute() {
//            authenticationError = true;
//            errorMessage = "Data Courpted";
//            pd = new SpotsDialog(progressDialogContext, R.style.CustomPD);
//            pd.setTitle("Loading...");
//            pd.setCancelable(false);
//            pd.show();
//        }
//
//        protected Integer doInBackground(Void... params) {
//            try {
//                java.net.URL url = new URL(Constants.URLs.PLACEMENT_BASE + Constants.URLs.PLACEMENT_ACADEMIC);
//                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//                connection.setDoInput(true);
//                connection.setDoOutput(true);
//                connection.setRequestMethod("POST");
//                Uri.Builder _data = new Uri.Builder().appendQueryParameter("token", Constants.SharedPreferenceData.getTOKEN())
//                        .appendQueryParameter("requestType", "put")
//                        .appendQueryParameter("tenth_per", ad10score)
//                        .appendQueryParameter("tenth_board", ad10board)
//                        .appendQueryParameter("tenth_sname", ad10schoolname)
//                        .appendQueryParameter("tenth_yop", ad10yop)
//                        .appendQueryParameter("twelth_per", ad12score)
//                        .appendQueryParameter("twelth_board", ad12board)
//                        .appendQueryParameter("twelth_sname", ad12schoolname)
//                        .appendQueryParameter("twelth_yop", ad12yop);
//                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(connection.getOutputStream(), "UTF-8"));
//                writer.write(_data.build().getEncodedQuery());
//                writer.flush();
//                writer.close();
//
//                InputStreamReader in = new InputStreamReader(connection.getInputStream());
//
//                StringBuilder jsonResults = new StringBuilder();
//                // Load the results into a StringBuilder
//                int read;
//                char[] buff = new char[1024];
//                while ((read = in.read(buff)) != -1) {
//                    jsonResults.append(buff, 0, read);
//                }
//                connection.disconnect();
//
//                authenticationError = jsonResults.toString().contains("Authentication Error");
//
//                if (authenticationError) {
//                    errorMessage = jsonResults.toString();
//                } else {
//                    JSONObject jsonObj = new JSONObject(jsonResults.toString());
//                    String status = jsonObj.getString("status");
//                    if (status.equals("success")) {
//                        authenticationError = false;
//                        errorMessage = status;
//                    } else {
//                        authenticationError = true;
//                        errorMessage = status;
//                    }
//                }
//                return 1;
//
//            } catch (Exception ex) {
//                ex.printStackTrace();
//            }
//
//            return 1;
//        }
//
//        protected void onPostExecute(Integer result) {
//            if (pd != null) {
//                pd.dismiss();
//            }
//            if (authenticationError) {
//                Toast.makeText(getApplicationContext(), errorMessage, Toast.LENGTH_SHORT).show();
//            } else {
//                putRegForm3();
//            }
//
//        }
//    }

    public void putRegForm3() {

        if (pdcourse.equals("btech")) {
            form3 = new Uri.Builder().appendQueryParameter("token", Constants.SharedPreferenceData.getTOKEN())
                    .appendQueryParameter("requestType", "put")
                    .appendQueryParameter("diploma_per", btdplScore)
                    .appendQueryParameter("diploma_cname", btdplschoolname)
                    .appendQueryParameter("diploma_yop", btdplyop)
                    .appendQueryParameter("bt_yoj", btyoj)
                    .appendQueryParameter("bt_sem1", btsem1)
                    .appendQueryParameter("bt_sem2", btsem2)
                    .appendQueryParameter("bt_sem3", btsem3)
                    .appendQueryParameter("bt_sem4", btsem4)
                    .appendQueryParameter("bt_sem5", btsem5)
                    .appendQueryParameter("bt_sem6", btsem6)
                    .appendQueryParameter("bt_sem7", btsem7)
                    .appendQueryParameter("bt_sem8", btsem8);

            URLFORM = Constants.URLs.PLACEMENT_BASE + Constants.URLs.PLACEMENT_BTECH;

        } else if (pdcourse.equals("mtech")) {
            form3 = new Uri.Builder().appendQueryParameter("token", Constants.SharedPreferenceData.getTOKEN())
                    .appendQueryParameter("requestType", "put")
                    .appendQueryParameter("diploma_per", mtdplscore)
                    .appendQueryParameter("diploma_cname", mtdplschoolname)
                    .appendQueryParameter("diploma_yop", mtdplyop)
                    .appendQueryParameter("bt_per", mtbtscore)
                    .appendQueryParameter("bt_university", mtbtuniversity)
                    .appendQueryParameter("bt_cname", mtbtcollegename)
                    .appendQueryParameter("bt_yop", mtbtyop)
                    .appendQueryParameter("mt_yoj", mtyoj)
                    .appendQueryParameter("mt_sem1", mtsem1)
                    .appendQueryParameter("mt_sem2", mtsem2)
                    .appendQueryParameter("mt_sem3", mtsem3)
                    .appendQueryParameter("mt_sem4", mtsem4);

            URLFORM = Constants.URLs.PLACEMENT_BASE + Constants.URLs.PLACEMENT_MTECH;

        } else if (pdcourse.equals("mba_mcom")) {
            form3 = new Uri.Builder().appendQueryParameter("token", Constants.SharedPreferenceData.getTOKEN())
                    .appendQueryParameter("requestType", "put")
                    .appendQueryParameter("degree_per", mmdegscore)
                    .appendQueryParameter("degree_university", mmdeguniversity)
                    .appendQueryParameter("degree_cname", mmdegcollegename)
                    .appendQueryParameter("degree_yop", mmdegyop)
                    .appendQueryParameter("mba_mcom_yoj", mmyoj)
                    .appendQueryParameter("mba_mcom_sem1", mmsem1)
                    .appendQueryParameter("mba_mcom_sem2", mmsem2)
                    .appendQueryParameter("mba_mcom_sem3", mmsem3)
                    .appendQueryParameter("mba_mcom_sem4", mmsem4);

            URLFORM = Constants.URLs.PLACEMENT_BASE + Constants.URLs.PLACEMENT_MBAMCOM;

        } else if (pdcourse.equals("degree")) {
            form3 = new Uri.Builder().appendQueryParameter("token", Constants.SharedPreferenceData.getTOKEN())
                    .appendQueryParameter("requestType", "put")
                    .appendQueryParameter("degree_coursename", degcourse)
                    .appendQueryParameter("degree_yoj", degyoj)
                    .appendQueryParameter("degree_sem1", degsem1)
                    .appendQueryParameter("degree_sem2", degsem2)
                    .appendQueryParameter("degree_sem3", degsem3)
                    .appendQueryParameter("degree_sem4", degsem4)
                    .appendQueryParameter("degree_sem5", degsem5)
                    .appendQueryParameter("degree_sem6", degsem6);

            URLFORM = Constants.URLs.PLACEMENT_BASE + Constants.URLs.PLACEMENT_DEGREE;

        } else if (pdcourse.equals("mca")) {
            form3 = new Uri.Builder().appendQueryParameter("token", Constants.SharedPreferenceData.getTOKEN())
                    .appendQueryParameter("requestType", "put")
                    .appendQueryParameter("degree_per", mcadegscore)
                    .appendQueryParameter("degree_coursename", mcadegcourse)
                    .appendQueryParameter("degree_university", mcadeguniversity)
                    .appendQueryParameter("degree_cname", mcadegcollegename)
                    .appendQueryParameter("degree_yop", mcadegyop)
                    .appendQueryParameter("mca_yoj", mcayoj)
                    .appendQueryParameter("mca_sem1", mcasem1)
                    .appendQueryParameter("mca_sem2", mcasem2)
                    .appendQueryParameter("mca_sem3", mcasem3)
                    .appendQueryParameter("mca_sem4", mcasem4)
                    .appendQueryParameter("mca_sem5", mcasem5)
                    .appendQueryParameter("mca_sem6", mcasem6);

            URLFORM = Constants.URLs.PLACEMENT_BASE + Constants.URLs.PLACEMENT_MCA;

        }

        PutRegForm3 putRegForm3 = new PutRegForm3();
        putRegForm3.execute();
    }

    private class PutRegForm3 extends AsyncTask<Void, Void, Integer> {

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
                java.net.URL url = new URL(URLFORM);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setDoInput(true);
                connection.setDoOutput(true);
                connection.setRequestMethod("POST");
                Uri.Builder _data = form3;
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
                Toast.makeText(getApplicationContext(), errorMessage, Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getApplicationContext(), errorMessage, Toast.LENGTH_SHORT).show();
            }

        }
    }


}
