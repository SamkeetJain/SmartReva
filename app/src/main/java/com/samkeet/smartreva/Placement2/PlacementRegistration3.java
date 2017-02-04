package com.samkeet.smartreva.Placement2;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.Toast;

import com.samkeet.smartreva.Constants;
import com.samkeet.smartreva.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import dmax.dialog.SpotsDialog;

public class PlacementRegistration3 extends AppCompatActivity {

    public TextInputLayout tBtechDplSchoolname, tBtechDplPercentage, tBtechDplYop, tBtechBeYoj, tBtechBeSem1, tBtechBeSem2, tBtechBeSem3, tBtechBeSem4, tBtechBeSem5, tBtechBeSem6, tBtechBeSem7, tBtechBeSem8, tBtechYeargap;
    public EditText mBtechDplSchoolname, mBtechDplPercentage, mBtechDplYop, mBtechBeYoj, mBtechBeSem1, mBtechBeSem2, mBtechBeSem3, mBtechBeSem4, mBtechBeSem5, mBtechBeSem6, mBtechBeSem7, mBtechBeSem8, mBtechYeargap, mBtechBacklog;
    public String btechDplSchoolname, btechDplPercentage, btechDplYop, btechBeYoj, btechBeSem1, btechBeSem2, btechBeSem3, btechBeSem4, btechBeSem5, btechBeSem6, btechBeSem7, btechBeSem8, btechYeargap, btechBacklog;

    public Button BtechSubbmit;
    public Switch btechDipSwitch;
    public LinearLayout btechDipLayout;

    public TextInputLayout tMtechDplSchoolname, tMtechDplPercentage, tMtechDplYop, tMtechBePercentage, tMtechBeUniversity, tMtechBeCollagename, tMtechBeYop, tMtechMtYoj, tMtechMtSem1, tMtechMtSem2, tMtechMtSem3, tMtechMtSem4, tMtechWorkxp, tMtechYearGap;
    public EditText mMtechDplSchoolname, mMtechDplPercentage, mMtechDplYop, mMtechBePercentage, mMtechBeUniversity, mMtechBeCollagename, mMtechBeYop, mMtechMtYoj, mMtechMtSem1, mMtechMtSem2, mMtechMtSem3, mMtechMtSem4, mMtechWorkxp, mMtechYearGap, mMtechBacklog;
    public String mtechDplSchoolname, mtechDplPercentage, mtechDplYop, mtechBePercentage, mtechBeUniversity, mtechBeCollagename, mtechBeYop, mtechMtYoj, mtechMtSem1, mtechMtSem2, mtechMtSem3, mtechMtSem4, mtechWorkxp, mtechYearGap, mtechBacklog;

    public Button MtechSubbmit;
    public Switch mtechDipSwitch;
    public LinearLayout mtechDipLayout;

    public TextInputLayout tDegCourse, tDegYoj, tDegSem1, tDegSem2, tDegSem3, tDegSem4, tDegSem5, tDegSem6, tDegYeargap;
    public EditText mDegCourse, mDegYoj, mDegSem1, mDegSem2, mDegSem3, mDegSem4, mDegSem5, mDegSem6, mDegYeargap, mDegBacklog;
    public String degCourse, degYoj, degSem1, degSem2, degSem3, degSem4, degSem5, degSem6, degYeargap, degBacklog;

    public Button DegSubbmit;

    public TextInputLayout tMmDegPercentage, tMmDegUniversity, tMmDegCollagename, tMmDegYop, tMmyoj, tMmSem1, tMmSem2, tMmSem3, tMmSem4, tMmWorkxp, tMmYeargap;
    public EditText mMmDegPercentage, mMmDegUniversity, mMmDegCollagename, mMmDegYop, mMmyoj, mMmSem1, mMmSem2, mMmSem3, mMmSem4, mMmWorkxp, mMmBacklog, mMmYeargap;
    public String mmDegPercentage, mmDegUniversity, mmDegCollagename, mmDegYop, mmyoj, mmSem1, mmSem2, mmSem3, mmSem4, mmWorkxp, mmBacklog, mmYeargap;

    public Button MmSubmit;

    public TextInputLayout tMcaDegPercentage, tMcaDegCourse, tMcaDegUniversity, tMcaDegCollagename, tMcaDegYop, tMcaYoj, tMcaSem1, tMcaSem2, tMcaSem3, tMcaSem4, tMcaSem5, tMcaSem6, tMcaWorkxp, tMcaYeargap;
    public EditText mMcaDegPercentage, mMcaDegCourse, mMcaDegUniversity, mMcaDegCollagename, mMcaDegYop, mMcaYoj, mMcaSem1, mMcaSem2, mMcaSem3, mMcaSem4, mMcaSem5, mMcaSem6, mMcaWorkxp, mMcaYeargap, mMcaBacklog;
    public String mcaDegPercentage, mcaDegCourse, mcaDegUniversity, mcaDegCollagename, mcaDegYop, mcaYoj, mcaSem1, mcaSem2, mcaSem3, mcaSem4, mcaSem5, mcaSem6, mcaWorkxp, mcaYeargap, mcaBacklog;

    public Button McaSubmit;

    public String object1, object2;
    public String course;
    public JSONObject jsonObject = new JSONObject();
    public JSONObject jsonObject1;
    public JSONObject jsonObject2;

    public SpotsDialog pd;
    public Context progressDialogContext;
    public boolean authenticationError;
    public String errorMessage;
    public Uri.Builder form3;

    public String bb;
    public String YEARGAP, WORKEXP, URLFORM;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        progressDialogContext = this;
        object1 = getIntent().getStringExtra("OBJECT1");
        object2 = getIntent().getStringExtra("OBJECT2");
        course = getIntent().getStringExtra("DATA");

        try {
            jsonObject1 = new JSONObject(object1);
            jsonObject2 = new JSONObject(object2);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        if (getIntent().getStringExtra("DATA").equals("btech")) {
            setContentView(R.layout.activity_placement2_reg_btech);
            /* all oncreate methods*/

            tBtechDplSchoolname = (TextInputLayout) findViewById(R.id.btech_dpl_school_name);
            tBtechDplPercentage = (TextInputLayout) findViewById(R.id.btech_dpl_percentage);
            tBtechDplYop = (TextInputLayout) findViewById(R.id.btech_dpl_yop);
            tBtechBeYoj = (TextInputLayout) findViewById(R.id.btech_be_yoj);
            tBtechBeSem1 = (TextInputLayout) findViewById(R.id.btech_be_sem1_marks);
            tBtechBeSem2 = (TextInputLayout) findViewById(R.id.btech_be_sem2_marks);
            tBtechBeSem3 = (TextInputLayout) findViewById(R.id.btech_be_sem3_marks);
            tBtechBeSem4 = (TextInputLayout) findViewById(R.id.btech_be_sem4_marks);
            tBtechBeSem5 = (TextInputLayout) findViewById(R.id.btech_be_sem5_marks);
            tBtechBeSem6 = (TextInputLayout) findViewById(R.id.btech_be_sem6_marks);
            tBtechBeSem7 = (TextInputLayout) findViewById(R.id.btech_be_sem7_marks);
            tBtechBeSem8 = (TextInputLayout) findViewById(R.id.btech_be_sem8_marks);
            tBtechYeargap = (TextInputLayout) findViewById(R.id.btech_year_gap);


            //Edit text
            mBtechDplSchoolname = (EditText) findViewById(R.id.btech_dpl_school_name_et);
            mBtechDplPercentage = (EditText) findViewById(R.id.btech_dpl_percentage_et);
            mBtechDplYop = (EditText) findViewById(R.id.btech_dpl_yop_et);
            mBtechBeYoj = (EditText) findViewById(R.id.btech_be_yoj_et);
            mBtechBeSem1 = (EditText) findViewById(R.id.btech_be_sem1_marks_et);
            mBtechBeSem2 = (EditText) findViewById(R.id.btech_be_sem2_marks_et);
            mBtechBeSem3 = (EditText) findViewById(R.id.btech_be_sem3_marks_et);
            mBtechBeSem4 = (EditText) findViewById(R.id.btech_be_sem4_marks_et);
            mBtechBeSem5 = (EditText) findViewById(R.id.btech_be_sem5_marks_et);
            mBtechBeSem6 = (EditText) findViewById(R.id.btech_be_sem6_marks_et);
            mBtechBeSem7 = (EditText) findViewById(R.id.btech_be_sem7_marks_et);
            mBtechBeSem8 = (EditText) findViewById(R.id.btech_be_sem8_marks_et);
            mBtechYeargap = (EditText) findViewById(R.id.btech_year_gap_et);
            mBtechBacklog = (EditText) findViewById(R.id.btech_back_et);

            btechDipLayout = (LinearLayout) findViewById(R.id.diplomaView);
            btechDipSwitch = (Switch) findViewById(R.id.diplomaSwitcher);
            btechDipSwitch.setChecked(false);
            btechDipLayout.setVisibility(View.GONE);
            btechDipSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                    if (isChecked) {
                        btechDipLayout.setVisibility(View.VISIBLE);
                    } else {
                        btechDipLayout.setVisibility(View.GONE);
                    }
                }
            });

            BtechSubbmit = (Button) findViewById(R.id.btech_subbmit_button);

            BtechSubbmit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    btechDplSchoolname = mBtechDplSchoolname.getText().toString().trim();
                    btechDplPercentage = mBtechDplPercentage.getText().toString().trim();
                    btechDplYop = mBtechDplYop.getText().toString().trim();
                    btechBeYoj = mBtechBeYoj.getText().toString().trim();
                    btechBeSem1 = mBtechBeSem1.getText().toString().trim();
                    btechBeSem2 = mBtechBeSem2.getText().toString().trim();
                    btechBeSem3 = mBtechBeSem3.getText().toString().trim();
                    btechBeSem4 = mBtechBeSem4.getText().toString().trim();
                    btechBeSem5 = mBtechBeSem5.getText().toString().trim();
                    btechBeSem6 = mBtechBeSem6.getText().toString().trim();
                    btechBeSem7 = mBtechBeSem7.getText().toString().trim();
                    btechBeSem8 = mBtechBeSem8.getText().toString().trim();
                    btechYeargap = mBtechYeargap.getText().toString().trim();
                    btechBacklog = mBtechBacklog.getText().toString().trim();

                    btechvaliditaion();
                }
            });

        } else if (getIntent().getStringExtra("DATA").equals("mtech")) {
            setContentView(R.layout.activity_placement2_reg_mtech);

            //Text input layout
            tMtechDplSchoolname = (TextInputLayout) findViewById(R.id.mtech_dpl_schoolname);
            tMtechDplPercentage = (TextInputLayout) findViewById(R.id.mtech_dpl_percentage);
            tMtechDplYop = (TextInputLayout) findViewById(R.id.mtech_dpl_yop);
            tMtechBePercentage = (TextInputLayout) findViewById(R.id.mtech_be_percentage);
            tMtechBeUniversity = (TextInputLayout) findViewById(R.id.mtech_be_university);
            tMtechBeCollagename = (TextInputLayout) findViewById(R.id.mtech_be_collegename);
            tMtechBeYop = (TextInputLayout) findViewById(R.id.mtech_be_yop);
            tMtechMtYoj = (TextInputLayout) findViewById(R.id.mtech_mt_yoj);
            tMtechMtSem1 = (TextInputLayout) findViewById(R.id.mtech_mt_sem1_marks);
            tMtechMtSem2 = (TextInputLayout) findViewById(R.id.mtech_mt_sem2_marks);
            tMtechMtSem3 = (TextInputLayout) findViewById(R.id.mtech_mt_sem3_marks);
            tMtechMtSem4 = (TextInputLayout) findViewById(R.id.mtech_mt_sem4_marks);
            tMtechWorkxp = (TextInputLayout) findViewById(R.id.mtech_workxp);
            tMtechYearGap = (TextInputLayout) findViewById(R.id.mtech_year_gap);

            //Edit text
            mMtechDplSchoolname = (EditText) findViewById(R.id.mtech_dpl_schoolname_et);
            mMtechDplPercentage = (EditText) findViewById(R.id.mtech_dpl_percentage_et);
            mMtechDplYop = (EditText) findViewById(R.id.mtech_dpl_yop_et);
            mMtechBePercentage = (EditText) findViewById(R.id.mtech_be_percentage_et);
            mMtechBeUniversity = (EditText) findViewById(R.id.mtech_be_university_et);
            mMtechBeCollagename = (EditText) findViewById(R.id.mtech_be_collegename_et);
            mMtechBeYop = (EditText) findViewById(R.id.mtech_be_yop_et);
            mMtechMtYoj = (EditText) findViewById(R.id.mtech_mt_yoj_et);
            mMtechMtSem1 = (EditText) findViewById(R.id.mtech_mt_sem1_marks_et);
            mMtechMtSem2 = (EditText) findViewById(R.id.mtech_mt_sem2_marks_et);
            mMtechMtSem3 = (EditText) findViewById(R.id.mtech_mt_sem3_marks_et);
            mMtechMtSem4 = (EditText) findViewById(R.id.mtech_mt_sem4_marks_et);
            mMtechWorkxp = (EditText) findViewById(R.id.mtech_workxp_et);
            mMtechYearGap = (EditText) findViewById(R.id.mtech_year_gap_et);
            mMtechBacklog = (EditText) findViewById(R.id.mtech_backlog);

            mtechDipLayout = (LinearLayout) findViewById(R.id.diplomaView);
            mtechDipSwitch = (Switch) findViewById(R.id.diplomaSwitcher);
            mtechDipSwitch.setChecked(false);
            mtechDipLayout.setVisibility(View.GONE);
            mtechDipSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                    if (isChecked) {
                        mtechDipLayout.setVisibility(View.VISIBLE);
                    } else {
                        mtechDipLayout.setVisibility(View.GONE);
                    }
                }
            });
            MtechSubbmit = (Button) findViewById(R.id.mtech_subbmit_button);

            MtechSubbmit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    mtechDplSchoolname = mMtechDplSchoolname.getText().toString().trim();
                    mtechDplPercentage = mMtechDplPercentage.getText().toString().trim();
                    mtechDplYop = mMtechDplYop.getText().toString().trim();
                    mtechBePercentage = mMtechBePercentage.getText().toString().trim();
                    mtechBeUniversity = mMtechBeUniversity.getText().toString().trim();
                    mtechBeCollagename = mMtechBeCollagename.getText().toString().trim();
                    mtechBeYop = mMtechBeYop.getText().toString().trim();
                    mtechMtYoj = mMtechMtYoj.getText().toString().trim();
                    mtechMtSem1 = mMtechMtSem1.getText().toString().trim();
                    mtechMtSem2 = mMtechMtSem2.getText().toString().trim();
                    mtechMtSem3 = mMtechMtSem3.getText().toString().trim();
                    mtechMtSem4 = mMtechMtSem4.getText().toString().trim();
                    mtechBacklog = mMtechBacklog.getText().toString().trim();
                    mtechWorkxp = mMtechWorkxp.getText().toString().trim();
                    mtechYearGap = mMtechYearGap.getText().toString().trim();

                    mtechvalidation();
                }
            });


        } else if (getIntent().getStringExtra("DATA").equals("mba_mcom")) {
            setContentView(R.layout.activity_placement2_reg_mba_mcom);

            //TextInputLayout
            tMmDegPercentage = (TextInputLayout) findViewById(R.id.mm_deg_percentage);
            tMmDegUniversity = (TextInputLayout) findViewById(R.id.mm_deg_university);
            tMmDegCollagename = (TextInputLayout) findViewById(R.id.mm_deg_College);
            tMmDegYop = (TextInputLayout) findViewById(R.id.mm_deg_yop);
            tMmyoj = (TextInputLayout) findViewById(R.id.yoj_mm);
            tMmSem1 = (TextInputLayout) findViewById(R.id.sem1_marks_mm);
            tMmSem2 = (TextInputLayout) findViewById(R.id.sem2_marks_mm);
            tMmSem3 = (TextInputLayout) findViewById(R.id.sem3_marks_mm);
            tMmSem4 = (TextInputLayout) findViewById(R.id.sem4_marks_mm);
            tMmWorkxp = (TextInputLayout) findViewById(R.id.workxp_mm);
            tMmYeargap = (TextInputLayout) findViewById(R.id.year_gap_mm);


            //EditText
            mMmDegPercentage = (EditText) findViewById(R.id.mm_deg_percentage_et);
            mMmDegUniversity = (EditText) findViewById(R.id.mm_deg_university_et);
            mMmDegCollagename = (EditText) findViewById(R.id.mm_deg_College_et);
            mMmDegYop = (EditText) findViewById(R.id.mm_deg_yop_et);
            mMmyoj = (EditText) findViewById(R.id.yoj_mm_et);
            mMmSem1 = (EditText) findViewById(R.id.sem1_marks_mm_et);
            mMmSem2 = (EditText) findViewById(R.id.sem2_marks_mm_et);
            mMmSem3 = (EditText) findViewById(R.id.sem3_marks_mm_et);
            mMmSem4 = (EditText) findViewById(R.id.sem4_marks_mm_et);
            mMmWorkxp = (EditText) findViewById(R.id.workxp_mm_et);
            mMmBacklog = (EditText) findViewById(R.id.mm_backlog);
            mMmYeargap = (EditText) findViewById(R.id.year_gap_mm_et);
            MmSubmit = (Button) findViewById(R.id.mm_submit_button);

            MmSubmit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    //Strings
                    mmDegPercentage = mMmDegPercentage.getText().toString().trim();
                    mmDegUniversity = mMmDegUniversity.getText().toString().trim();
                    mmDegCollagename = mMmDegCollagename.getText().toString().trim();
                    mmDegYop = mMmDegYop.getText().toString().trim();
                    mmyoj = mMmyoj.getText().toString().trim();
                    mmSem1 = mMmSem1.getText().toString().trim();
                    mmSem2 = mMmSem2.getText().toString().trim();
                    mmSem3 = mMmSem3.getText().toString().trim();
                    mmSem4 = mMmSem4.getText().toString().trim();
                    mmWorkxp = mMmWorkxp.getText().toString().trim();
                    mmYeargap = mMmYeargap.getText().toString().trim();
                    mmBacklog = mMmBacklog.getText().toString().trim();

                    mmValidation();
                }
            });


        } else if (getIntent().getStringExtra("DATA").equals("degree")) {
            setContentView(R.layout.activity_placement2_reg_deg);

            //TextInputLayout

            tDegCourse = (TextInputLayout) findViewById(R.id.course_deg);
            tDegYoj = (TextInputLayout) findViewById(R.id.yoj_deg);
            tDegSem1 = (TextInputLayout) findViewById(R.id.sem1_marks_deg);
            tDegSem2 = (TextInputLayout) findViewById(R.id.sem2_marks_deg);
            tDegSem3 = (TextInputLayout) findViewById(R.id.sem3_marks_deg);
            tDegSem4 = (TextInputLayout) findViewById(R.id.sem4_marks_deg);
            tDegSem5 = (TextInputLayout) findViewById(R.id.sem5_marks_deg);
            tDegSem6 = (TextInputLayout) findViewById(R.id.sem6_marks_deg);
            tDegYeargap = (TextInputLayout) findViewById(R.id.deg_year_gap);

            //EditText

            mDegCourse = (EditText) findViewById(R.id.course_deg_et);
            mDegYoj = (EditText) findViewById(R.id.yoj_deg_et);
            mDegSem1 = (EditText) findViewById(R.id.sem1_marks_deg_et);
            mDegSem2 = (EditText) findViewById(R.id.sem2_marks_deg_et);
            mDegSem3 = (EditText) findViewById(R.id.sem3_marks_deg_et);
            mDegSem4 = (EditText) findViewById(R.id.sem4_marks_deg_et);
            mDegSem5 = (EditText) findViewById(R.id.sem5_marks_deg_et);
            mDegSem6 = (EditText) findViewById(R.id.sem6_marks_deg_et);
            mDegYeargap = (EditText) findViewById(R.id.deg_year_gap_et);
            mDegBacklog = (EditText) findViewById(R.id.deg_backlog);

            DegSubbmit = (Button) findViewById(R.id.deg_submit_button);

            DegSubbmit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    //String
                    degCourse = mDegCourse.getText().toString().trim();
                    degYoj = mDegYoj.getText().toString().trim();
                    degSem1 = mDegSem1.getText().toString().trim();
                    degSem2 = mDegSem2.getText().toString().trim();
                    degSem3 = mDegSem3.getText().toString().trim();
                    degSem4 = mDegSem4.getText().toString().trim();
                    degSem5 = mDegSem5.getText().toString().trim();
                    degSem6 = mDegSem6.getText().toString().trim();
                    degYeargap = mDegYeargap.getText().toString().trim();
                    degBacklog = mDegBacklog.getText().toString().trim();

                    degreeValidation();
                }
            });


        } else if (getIntent().getStringExtra("DATA").equals("mca")) {
            setContentView(R.layout.activity_placement2_reg_mca);


            //TextInputLayout
            tMcaDegPercentage = (TextInputLayout) findViewById(R.id.mca_deg_percentage);
            tMcaDegCourse = (TextInputLayout) findViewById(R.id.course_mca_deg);
            tMcaDegUniversity = (TextInputLayout) findViewById(R.id.university_mca_deg);
            tMcaDegCollagename = (TextInputLayout) findViewById(R.id.College_mca_deg);
            tMcaDegYop = (TextInputLayout) findViewById(R.id.yop_mcad_deg);
            tMcaYoj = (TextInputLayout) findViewById(R.id.yoj_mca);
            tMcaSem1 = (TextInputLayout) findViewById(R.id.sem1_marks_mca);
            tMcaSem2 = (TextInputLayout) findViewById(R.id.sem2_marks_mca);
            tMcaSem3 = (TextInputLayout) findViewById(R.id.sem3_marks_mca);
            tMcaSem4 = (TextInputLayout) findViewById(R.id.sem4_marks_mca);
            tMcaSem5 = (TextInputLayout) findViewById(R.id.sem5_marks_mca);
            tMcaSem6 = (TextInputLayout) findViewById(R.id.sem6_marks_mca);
            tMcaWorkxp = (TextInputLayout) findViewById(R.id.mca_workxp);
            tMcaYeargap = (TextInputLayout) findViewById(R.id.year_gap_mca);

            //EditText

            mMcaDegPercentage = (EditText) findViewById(R.id.mca_deg_percentage_et);
            mMcaDegCourse = (EditText) findViewById(R.id.course_mca_deg_et);
            mMcaDegUniversity = (EditText) findViewById(R.id.university_mca_deg_et);
            mMcaDegCollagename = (EditText) findViewById(R.id.College_mca_deg_et);
            mMcaDegYop = (EditText) findViewById(R.id.yop_mcad_deg_et);
            mMcaYoj = (EditText) findViewById(R.id.yoj_mca_et);
            mMcaSem1 = (EditText) findViewById(R.id.sem1_marks_mca_et);
            mMcaSem2 = (EditText) findViewById(R.id.sem2_marks_mca_et);
            mMcaSem3 = (EditText) findViewById(R.id.sem3_marks_mca_et);
            mMcaSem4 = (EditText) findViewById(R.id.sem4_marks_mca_et);
            mMcaSem5 = (EditText) findViewById(R.id.sem5_marks_mca_et);
            mMcaSem6 = (EditText) findViewById(R.id.sem6_marks_mca_et);
            mMcaWorkxp = (EditText) findViewById(R.id.mca_workxp_et);
            mMcaYeargap = (EditText) findViewById(R.id.year_gap_mca_et);
            mMcaBacklog = (EditText) findViewById(R.id.mca_backlog);

            McaSubmit = (Button) findViewById(R.id.Mca_submit_button);

            McaSubmit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //String
                    mcaDegPercentage = mMcaDegPercentage.getText().toString().trim();
                    mcaDegCourse = mMcaDegCourse.getText().toString().trim();
                    mcaDegUniversity = mMcaDegUniversity.getText().toString().trim();
                    mcaDegCollagename = mMcaDegCollagename.getText().toString().trim();
                    mcaDegYop = mMcaDegYop.getText().toString().trim();
                    mcaYoj = mMcaYoj.getText().toString().trim();
                    mcaSem1 = mMcaSem1.getText().toString().trim();
                    mcaSem2 = mMcaSem2.getText().toString().trim();
                    mcaSem3 = mMcaSem3.getText().toString().trim();
                    mcaSem4 = mMcaSem4.getText().toString().trim();
                    mcaSem5 = mMcaSem5.getText().toString().trim();
                    mcaSem6 = mMcaSem6.getText().toString().trim();
                    mcaWorkxp = mMcaWorkxp.getText().toString().trim();
                    mcaYeargap = mMcaYeargap.getText().toString().trim();
                    mcaBacklog = mMcaBacklog.getText().toString().trim();

                    mcaValidate();
                }
            });

        }
    }

    public void mmValidation() {
        if (!percentageValidation(mmDegPercentage, mMmDegPercentage, tMmDegPercentage)) {
            return;
        }
        if (!universityValidation(mmDegUniversity, mMmDegUniversity, tMmDegUniversity)) {
            return;
        }
        if (!collegeNameValidation(mmDegCollagename, mMmDegCollagename, tMmDegCollagename)) {
            return;
        }
        if (!yearValidation(mmDegYop, mMmDegYop, tMmDegYop)) {
            return;
        }
        if (!yearValidation(mmyoj, mMmyoj, tMmyoj)) {
            return;
        }
        if (!backlogValidation(mmBacklog)) {
            return;
        }
        try {
            jsonObject.put("mmDegPer", mmDegPercentage);
            jsonObject.put("mmDegUni", mmDegUniversity);
            jsonObject.put("mmDegCol", mmDegCollagename);
            jsonObject.put("mmDegYop", mmDegYop);
            jsonObject.put("mmYoj", mmyoj);
            jsonObject.put("backlog", mmBacklog);
            jsonObject.put("s1", mmSem1.isEmpty() ? "NA" : mmSem1);
            jsonObject.put("s2", mmSem2.isEmpty() ? "NA" : mmSem2);
            jsonObject.put("s3", mmSem3.isEmpty() ? "NA" : mmSem3);
            jsonObject.put("s4", mmSem4.isEmpty() ? "NA" : mmSem4);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        proceedToBackLog();
    }

    public void degreeValidation() {
        if (!universityValidation(degCourse, mDegCourse, tDegCourse)) {
            return;
        }
        if (!yearValidation(degYoj, mDegYoj, tDegYoj)) {
            return;
        }
        if (!backlogValidation(degBacklog)) {
            return;
        }
        try {
            jsonObject.put("degCourse", degCourse);
            jsonObject.put("degYoj", degYoj);
            jsonObject.put("s1", degSem1.isEmpty() ? "NA" : degSem1);
            jsonObject.put("s2", degSem2.isEmpty() ? "NA" : degSem2);
            jsonObject.put("s3", degSem3.isEmpty() ? "NA" : degSem3);
            jsonObject.put("s4", degSem4.isEmpty() ? "NA" : degSem4);
            jsonObject.put("s5", degSem5.isEmpty() ? "NA" : degSem5);
            jsonObject.put("s6", degSem6.isEmpty() ? "NA" : degSem6);
            jsonObject.put("backlog", degBacklog);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        proceedToBackLog();
    }

    public void mtechvalidation() {
        if (mtechDipSwitch.isChecked()) {
            if (!collegeNameValidation(mtechDplSchoolname, mMtechDplSchoolname, tMtechDplSchoolname)) {
                return;
            }
            if (!percentageValidation(mtechDplPercentage, mMtechDplPercentage, tMtechDplPercentage)) {
                return;
            }
            if (!yearValidation(mtechDplYop, mMtechDplYop, tMtechDplYop)) {
                return;
            }
            if (!percentageValidation(mtechBePercentage, mMtechBePercentage, tMtechBePercentage)) {
                return;
            }
            if (!universityValidation(mtechBeUniversity, mMtechBeUniversity, tMtechBeUniversity)) {
                return;
            }
            if (!collegeNameValidation(mtechBeCollagename, mMtechBeCollagename, tMtechBeCollagename)) {
                return;
            }
            if (!yearValidation(mtechBeYop, mMtechBeYop, tMtechBeYop)) {
                return;
            }
            if (!yearValidation(mtechMtYoj, mMtechMtYoj, tMtechMtYoj)) {
                return;
            }
            if (!backlogValidation(mtechBacklog)) {
                return;
            }

            try {
                jsonObject.put("mtDipSch", mtechDplSchoolname);
                jsonObject.put("mtDipPer", mtechDplPercentage);
                jsonObject.put("mtDipYop", mtechDplYop);
                jsonObject.put("mtBePer", mtechBePercentage);
                jsonObject.put("mtBeUni", mtechBeUniversity);
                jsonObject.put("mtBeCol", mtechBeCollagename);
                jsonObject.put("mtBeYop", mtechBeYop);
                jsonObject.put("mtMtYoj", mtechMtYoj);
                jsonObject.put("s1", mtechMtSem1.isEmpty() ? "NA" : mtechMtSem1);
                jsonObject.put("s2", mtechMtSem2.isEmpty() ? "NA" : mtechMtSem2);
                jsonObject.put("s3", mtechMtSem3.isEmpty() ? "NA" : mtechMtSem3);
                jsonObject.put("s4", mtechMtSem4.isEmpty() ? "NA" : mtechMtSem4);
                jsonObject.put("backlog", mtechBacklog);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            proceedToBackLog();
        } else {
            if (!percentageValidation(mtechBePercentage, mMtechBePercentage, tMtechBePercentage)) {
                return;
            }
            if (!universityValidation(mtechBeUniversity, mMtechBeUniversity, tMtechBeUniversity)) {
                return;
            }
            if (!collegeNameValidation(mtechBeCollagename, mMtechBeCollagename, tMtechBeCollagename)) {
                return;
            }
            if (!yearValidation(mtechBeYop, mMtechBeYop, tMtechBeYop)) {
                return;
            }
            if (!yearValidation(mtechMtYoj, mMtechMtYoj, tMtechMtYoj)) {
                return;
            }
            if (!backlogValidation(mtechBacklog)) {
                return;
            }
            try {
                jsonObject.put("mtDipSch", "NA");
                jsonObject.put("mtDipPer", "NA");
                jsonObject.put("mtDipYop", "NA");
                jsonObject.put("mtBePer", mtechBePercentage);
                jsonObject.put("mtBeUni", mtechBeUniversity);
                jsonObject.put("mtBeCol", mtechBeCollagename);
                jsonObject.put("mtBeYop", mtechBeYop);
                jsonObject.put("mtMtYoj", mtechMtYoj);
                jsonObject.put("s1", mtechMtSem1.isEmpty() ? "NA" : mtechMtSem1);
                jsonObject.put("s2", mtechMtSem2.isEmpty() ? "NA" : mtechMtSem2);
                jsonObject.put("s3", mtechMtSem3.isEmpty() ? "NA" : mtechMtSem3);
                jsonObject.put("s4", mtechMtSem4.isEmpty() ? "NA" : mtechMtSem4);
                jsonObject.put("backlog", mtechBacklog);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            proceedToBackLog();
        }
    }

    public void btechvaliditaion() {
        if (btechDipSwitch.isChecked()) {
            if (!collegeNameValidation(btechDplSchoolname, mBtechDplSchoolname, tBtechDplSchoolname)) {
                return;
            }
            if (!percentageValidation(btechDplPercentage, mBtechDplPercentage, tBtechDplPercentage)) {
                return;
            }
            if (!yearValidation(btechDplYop, mBtechDplYop, tBtechDplYop)) {
                return;
            }
            if (!yearValidation(btechBeYoj, mBtechBeYoj, tBtechBeYoj)) {
                return;
            }
            if (!backlogValidation(btechBacklog)) {
                return;
            }
            try {
                jsonObject.put("btDipSch", btechDplSchoolname);
                jsonObject.put("btDipPer", btechDplPercentage);
                jsonObject.put("btDipYop", btechDplYop);
                jsonObject.put("BtBeYoj", btechBeYoj);
                jsonObject.put("backlog", btechBacklog);
                jsonObject.put("s1", btechBeSem1.isEmpty() ? "NA" : btechBeSem1);
                jsonObject.put("s2", btechBeSem2.isEmpty() ? "NA" : btechBeSem2);
                jsonObject.put("s3", btechBeSem3.isEmpty() ? "NA" : btechBeSem3);
                jsonObject.put("s4", btechBeSem4.isEmpty() ? "NA" : btechBeSem4);
                jsonObject.put("s5", btechBeSem5.isEmpty() ? "NA" : btechBeSem5);
                jsonObject.put("s6", btechBeSem6.isEmpty() ? "NA" : btechBeSem6);
                jsonObject.put("s7", btechBeSem7.isEmpty() ? "NA" : btechBeSem7);
                jsonObject.put("s8", btechBeSem8.isEmpty() ? "NA" : btechBeSem8);

            } catch (JSONException e) {
                e.printStackTrace();
            }
            proceedToBackLog();
        } else {
            if (!yearValidation(btechBeYoj, mBtechBeYoj, tBtechBeYoj)) {
                return;
            }
            if (!backlogValidation(btechBacklog)) {
                return;
            }
            try {
                jsonObject.put("btDipSch", "NA");
                jsonObject.put("btDipPer", "NA");
                jsonObject.put("btDipYop", "NA");
                jsonObject.put("BtBeYoj", btechBeYoj);
                jsonObject.put("backlog", btechBacklog);
                jsonObject.put("s1", btechBeSem1.isEmpty() ? "NA" : btechBeSem1);
                jsonObject.put("s2", btechBeSem2.isEmpty() ? "NA" : btechBeSem2);
                jsonObject.put("s3", btechBeSem3.isEmpty() ? "NA" : btechBeSem3);
                jsonObject.put("s4", btechBeSem4.isEmpty() ? "NA" : btechBeSem4);
                jsonObject.put("s5", btechBeSem5.isEmpty() ? "NA" : btechBeSem5);
                jsonObject.put("s6", btechBeSem6.isEmpty() ? "NA" : btechBeSem6);
                jsonObject.put("s7", btechBeSem7.isEmpty() ? "NA" : btechBeSem7);
                jsonObject.put("s8", btechBeSem8.isEmpty() ? "NA" : btechBeSem8);

            } catch (JSONException e) {
                e.printStackTrace();
            }
            proceedToBackLog();
        }
    }

    public void mcaValidate() {
        if (!percentageValidation(mcaDegPercentage, mMcaDegPercentage, tMcaDegPercentage)) {
            return;
        }
        if (!courseValidation(mcaDegCourse, mMcaDegCourse, tMcaDegCourse)) {
            return;
        }
        if (!universityValidation(mcaDegUniversity, mMcaDegUniversity, tMcaDegUniversity)) {
            return;
        }
        if (!collegeNameValidation(mcaDegCollagename, mMcaDegCollagename, tMcaDegCollagename)) {
            return;
        }
        if (!yearValidation(mcaDegYop, mMcaDegYop, tMcaDegYop)) {
            return;
        }
        if (!yearValidation(mcaYoj, mMcaYoj, tMcaYoj)) {
            return;
        }
        if (!backlogValidation(mcaBacklog)) {
            return;
        }
        try {
            jsonObject.put("deg_per", mcaDegPercentage);
            jsonObject.put("deg_course", mcaDegCourse);
            jsonObject.put("deg_uni", mcaDegUniversity);
            jsonObject.put("deg_cname", mcaDegCollagename);
            jsonObject.put("deg_yop", mcaDegYop);
            jsonObject.put("mca_yoj", mcaYoj);
            jsonObject.put("s1", mcaSem1.isEmpty() ? "NA" : mcaSem1);
            jsonObject.put("s2", mcaSem2.isEmpty() ? "NA" : mcaSem2);
            jsonObject.put("s3", mcaSem3.isEmpty() ? "NA" : mcaSem3);
            jsonObject.put("s4", mcaSem4.isEmpty() ? "NA" : mcaSem4);
            jsonObject.put("s5", mcaSem5.isEmpty() ? "NA" : mcaSem5);
            jsonObject.put("s6", mcaSem6.isEmpty() ? "NA" : mcaSem6);
            jsonObject.put("backlog", mcaBacklog);


        } catch (JSONException e) {
            e.printStackTrace();
        }
        proceedToBackLog();
    }

    private boolean percentageValidation(String check, EditText mCheck, TextInputLayout tCheck) {
        if (check.isEmpty()) {
            tCheck.setError("Invalid Percentage");
            requestFocus(mCheck);
            return false;
        }
        if (check.length() > 5) {
            tCheck.setError("Percentage Name should be less then 5 Characters");
            requestFocus(mCheck);
            return false;
        }
        if (check.length() < 2) {
            tCheck.setError("Invalid Percentage");
            requestFocus(mCheck);
            return false;

        }
        if (Constants.Methods.checkForSpecial(check)) {
            tCheck.setError("Remove Special Characters");
            requestFocus(mCheck);
            return false;
        } else {
            tCheck.setErrorEnabled(false);
        }
        return true;
    }

    private boolean courseValidation(String check, EditText mCheck, TextInputLayout tCheck) {
        if (check.isEmpty()) {
            tCheck.setError("Invalid Course");
            requestFocus(mCheck);
            return false;
        }
        if (check.length() > 10) {
            tCheck.setError("Courseshould be less then 32 Characters");
            requestFocus(mMcaDegCourse);
            return false;
        }
        if (Constants.Methods.checkForSpecial(check)) {
            tCheck.setError("Remove Special Characters");
            requestFocus(mCheck);
            return false;
        } else {
            tCheck.setErrorEnabled(false);
        }
        return true;
    }

    private boolean universityValidation(String check, EditText mCheck, TextInputLayout tCheck) {
        if (check.isEmpty()) {
            tCheck.setError("Invalid University Name");
            requestFocus(mCheck);
            return false;
        }
        if (check.length() > 32) {
            tCheck.setError("University Name should be less then 32 Characters");
            requestFocus(mCheck);
            return false;
        }
        if (Constants.Methods.checkForSpecial(check)) {
            tCheck.setError("Remove Special Characters");
            requestFocus(mCheck);
            return false;
        } else {
            tCheck.setErrorEnabled(false);
        }
        return true;
    }

    private boolean collegeNameValidation(String check, EditText mCheck, TextInputLayout tCheck) {
        if (check.isEmpty()) {
            tCheck.setError("Invalid College Name");
            requestFocus(mCheck);
            return false;
        }
        if (check.length() > 128) {
            tCheck.setError("College name should be less then 128 Characters");
            requestFocus(mCheck);
            return false;
        }
        if (Constants.Methods.checkForSpecial(check)) {
            tCheck.setError("Remove Special Characters");
            requestFocus(mCheck);
            return false;
        } else {
            tCheck.setErrorEnabled(false);
        }
        return true;
    }

    private boolean backlogValidation(String check) {
        if (check.length() > 2 || check.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Invalid Backlog", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (Constants.Methods.checkForSpecial(check)) {
            Toast.makeText(getApplicationContext(), "Remove Special Charecters", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private boolean yearValidation(String check, EditText mCheck, TextInputLayout tCheck) {
        if (check.isEmpty()) {
            tCheck.setError("Invalid year");
            requestFocus(mCheck);
            return false;
        }
        if (!((check.length() == 4))) {
            tCheck.setError("Year should be of 4 characters");
            requestFocus(mCheck);
            return false;
        }
        if (Constants.Methods.checkForSpecial(check)) {
            tCheck.setError("Remove Special Characters");
            requestFocus(mCheck);
            return false;
        } else {
            tCheck.setErrorEnabled(false);
        }
        return true;
    }

    public void BackButton(View v) {
        finish();
    }

    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }

    public void proceedToBackLog() {
        bb = "";
        if (getIntent().getStringExtra("DATA").equals("btech")) {
            bb = mBtechBacklog.getText().toString().trim();
            YEARGAP = btechYeargap.isEmpty() ? "NA" : btechYeargap;
            WORKEXP = "NA";
        } else if (getIntent().getStringExtra("DATA").equals("mtech")) {
            bb = mMtechBacklog.getText().toString().trim();
            YEARGAP = mtechYearGap.isEmpty() ? "NA" : mtechYearGap;
            WORKEXP = mtechWorkxp.isEmpty() ? "NA" : mtechWorkxp;
        } else if (getIntent().getStringExtra("DATA").equals("mba_mcom")) {
            bb = mMmBacklog.getText().toString().trim();
            YEARGAP = mmYeargap.isEmpty() ? "NA" : mmYeargap;
            WORKEXP = mmWorkxp.isEmpty() ? "NA" : mmWorkxp;
        } else if (getIntent().getStringExtra("DATA").equals("degree")) {
            bb = mDegBacklog.getText().toString().trim();
            YEARGAP = degYeargap.isEmpty() ? "NA" : degYeargap;
            WORKEXP = "NA";
        } else if (getIntent().getStringExtra("DATA").equals("mca")) {
            bb = mMcaBacklog.getText().toString().trim();
            YEARGAP = mcaYeargap.isEmpty() ? "NA" : mcaYeargap;
            WORKEXP = mcaWorkxp.isEmpty() ? "NA" : mcaWorkxp;
        }

        PutRegForm1 putRegForm1 = new PutRegForm1();
        putRegForm1.execute();
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
                        .appendQueryParameter("name", jsonObject1.getString("name"))
                        .appendQueryParameter("gender", jsonObject1.getString("gender"))
                        .appendQueryParameter("dob", jsonObject1.getString("dob"))
                        .appendQueryParameter("PhoneNo", jsonObject1.getString("phoneno"))
                        .appendQueryParameter("CurrentAddress", jsonObject1.getString("ca"))
                        .appendQueryParameter("PermanentAddress", jsonObject1.getString("pa"))
                        .appendQueryParameter("email", jsonObject1.getString("email"))
                        .appendQueryParameter("branch", jsonObject2.getString("branch"))
                        .appendQueryParameter("course", course)
                        .appendQueryParameter("yeargap", YEARGAP)
                        .appendQueryParameter("workexperience", WORKEXP);
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
                Log.d("return from server", jsonResults.toString());

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
                PutRegForm2 putRegForm2 = new PutRegForm2();
                putRegForm2.execute();
            }

        }
    }

    private class PutRegForm2 extends AsyncTask<Void, Void, Integer> {

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
                java.net.URL url = new URL(Constants.URLs.PLACEMENT_BASE + Constants.URLs.PLACEMENT_ACADEMIC);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setDoInput(true);
                connection.setDoOutput(true);
                connection.setRequestMethod("POST");
                Uri.Builder _data = new Uri.Builder().appendQueryParameter("token", Constants.SharedPreferenceData.getTOKEN())
                        .appendQueryParameter("requestType", "put")
                        .appendQueryParameter("tenth_per", jsonObject2.getString("tenths"))
                        .appendQueryParameter("tenth_board", jsonObject2.getString("tenthb"))
                        .appendQueryParameter("tenth_sname", jsonObject2.getString("tenthsn"))
                        .appendQueryParameter("tenth_yop", jsonObject2.getString("tenthpy"))
                        .appendQueryParameter("twelth_per", jsonObject2.getString("twelths"))
                        .appendQueryParameter("twelth_board", jsonObject2.getString("twelthb"))
                        .appendQueryParameter("twelth_sname", jsonObject2.getString("twelthsn"))
                        .appendQueryParameter("twelth_yop", jsonObject2.getString("twelthpy"));
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

    public void putRegForm3() {

        if (course.equals("btech")) {
            try {

                form3 = new Uri.Builder().appendQueryParameter("token", Constants.SharedPreferenceData.getTOKEN())
                        .appendQueryParameter("requestType", "put")
                        .appendQueryParameter("diploma_per", jsonObject.getString("btDipPer"))
                        .appendQueryParameter("diploma_cname", jsonObject.getString("btDipSch"))
                        .appendQueryParameter("diploma_yop", jsonObject.getString("btDipYop"))
                        .appendQueryParameter("bt_yoj", jsonObject.getString("BtBeYoj"))
                        .appendQueryParameter("bt_sem1", jsonObject.getString("s1"))
                        .appendQueryParameter("bt_sem2", jsonObject.getString("s2"))
                        .appendQueryParameter("bt_sem3", jsonObject.getString("s3"))
                        .appendQueryParameter("bt_sem4", jsonObject.getString("s4"))
                        .appendQueryParameter("bt_sem5", jsonObject.getString("s5"))
                        .appendQueryParameter("bt_sem6", jsonObject.getString("s6"))
                        .appendQueryParameter("bt_sem7", jsonObject.getString("s7"))
                        .appendQueryParameter("bt_sem8", jsonObject.getString("s8"));

                URLFORM = Constants.URLs.PLACEMENT_BASE + Constants.URLs.PLACEMENT_BTECH;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else if (course.equals("mtech")) {
            try {
                form3 = new Uri.Builder().appendQueryParameter("token", Constants.SharedPreferenceData.getTOKEN())
                        .appendQueryParameter("requestType", "put")
                        .appendQueryParameter("diploma_per", jsonObject.getString("mtDipPer"))
                        .appendQueryParameter("diploma_cname", jsonObject.getString("mtDipSch"))
                        .appendQueryParameter("diploma_yop", jsonObject.getString("mtDipYop"))
                        .appendQueryParameter("bt_per", jsonObject.getString("mtBePer"))
                        .appendQueryParameter("bt_university", jsonObject.getString("mtBeUni"))
                        .appendQueryParameter("bt_cname", jsonObject.getString("mtBeCol"))
                        .appendQueryParameter("bt_yop", jsonObject.getString("mtBeYop"))
                        .appendQueryParameter("mt_yoj", jsonObject.getString("mtMtYoj"))
                        .appendQueryParameter("mt_sem1", jsonObject.getString("s1"))
                        .appendQueryParameter("mt_sem2", jsonObject.getString("s2"))
                        .appendQueryParameter("mt_sem3", jsonObject.getString("s3"))
                        .appendQueryParameter("mt_sem4", jsonObject.getString("s4"));

                URLFORM = Constants.URLs.PLACEMENT_BASE + Constants.URLs.PLACEMENT_MTECH;

            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else if (course.equals("mba_mcom")) {
            try {
                form3 = new Uri.Builder().appendQueryParameter("token", Constants.SharedPreferenceData.getTOKEN())
                        .appendQueryParameter("requestType", "put")
                        .appendQueryParameter("degree_per", jsonObject.getString("mmDegPer"))
                        .appendQueryParameter("degree_university", jsonObject.getString("mmDegUni"))
                        .appendQueryParameter("degree_cname", jsonObject.getString("mmDegCol"))
                        .appendQueryParameter("degree_yop", jsonObject.getString("mmDegYop"))
                        .appendQueryParameter("mba_mcom_yoj", jsonObject.getString("mmYoj"))
                        .appendQueryParameter("mba_mcom_sem1", jsonObject.getString("s1"))
                        .appendQueryParameter("mba_mcom_sem2", jsonObject.getString("s2"))
                        .appendQueryParameter("mba_mcom_sem3", jsonObject.getString("s3"))
                        .appendQueryParameter("mba_mcom_sem4", jsonObject.getString("s4"));

                URLFORM = Constants.URLs.PLACEMENT_BASE + Constants.URLs.PLACEMENT_MBAMCOM;

            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else if (course.equals("degree")) {
            try {
                form3 = new Uri.Builder().appendQueryParameter("token", Constants.SharedPreferenceData.getTOKEN())
                        .appendQueryParameter("requestType", "put")
                        .appendQueryParameter("degree_coursename", jsonObject.getString("degCourse"))
                        .appendQueryParameter("degree_yoj", jsonObject.getString("degYoj"))
                        .appendQueryParameter("degree_sem1", jsonObject.getString("s1"))
                        .appendQueryParameter("degree_sem2", jsonObject.getString("s2"))
                        .appendQueryParameter("degree_sem3", jsonObject.getString("s3"))
                        .appendQueryParameter("degree_sem4", jsonObject.getString("s4"))
                        .appendQueryParameter("degree_sem5", jsonObject.getString("s5"))
                        .appendQueryParameter("degree_sem6", jsonObject.getString("s6"));

                URLFORM = Constants.URLs.PLACEMENT_BASE + Constants.URLs.PLACEMENT_DEGREE;

            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else if (course.equals("mca")) {
            try {
                form3 = new Uri.Builder().appendQueryParameter("token", Constants.SharedPreferenceData.getTOKEN())
                        .appendQueryParameter("requestType", "put")
                        .appendQueryParameter("degree_per", jsonObject.getString("deg_per"))
                        .appendQueryParameter("degree_coursename", jsonObject.getString("deg_course"))
                        .appendQueryParameter("degree_university", jsonObject.getString("deg_uni"))
                        .appendQueryParameter("degree_cname", jsonObject.getString("deg_cname"))
                        .appendQueryParameter("degree_yop", jsonObject.getString("deg_yop"))
                        .appendQueryParameter("mca_yoj", jsonObject.getString("mca_yoj"))
                        .appendQueryParameter("mca_sem1", jsonObject.getString("s1"))
                        .appendQueryParameter("mca_sem2", jsonObject.getString("s2"))
                        .appendQueryParameter("mca_sem3", jsonObject.getString("s3"))
                        .appendQueryParameter("mca_sem4", jsonObject.getString("s4"))
                        .appendQueryParameter("mca_sem5", jsonObject.getString("s5"))
                        .appendQueryParameter("mca_sem6", jsonObject.getString("s6"));

                URLFORM = Constants.URLs.PLACEMENT_BASE + Constants.URLs.PLACEMENT_MCA;
            } catch (JSONException e) {
                e.printStackTrace();
            }
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
                Log.d("return from server", jsonResults.toString());
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
                Toast.makeText(getApplicationContext(), "success full", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(getApplicationContext(), PlacementRegistration4.class);
                intent.putExtra("DATA", bb);
                startActivity(intent);
            }

        }
    }
}
