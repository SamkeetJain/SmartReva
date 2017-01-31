package com.samkeet.smartreva.Placement2;

import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

import org.w3c.dom.Text;

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

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
                    mcaSem2 = mMcaSem3.getText().toString().trim();
                    mcaSem3 = mMcaSem4.getText().toString().trim();
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
            proceedToBackLog();
        } else {
            if (!yearValidation(btechBeYoj, mBtechBeYoj, tBtechBeYoj)) {
                return;
            }
            if (!backlogValidation(btechBacklog)) {
                return;
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
        String bb = "";
        if (getIntent().getStringExtra("DATA").equals("btech")) {
            bb = mBtechBacklog.getText().toString();
        } else if (getIntent().getStringExtra("DATA").equals("mtech")) {
            bb = mMtechBacklog.getText().toString();
        } else if (getIntent().getStringExtra("DATA").equals("mba_mcom")) {
            bb = mMmBacklog.getText().toString();
        } else if (getIntent().getStringExtra("DATA").equals("degree")) {
            bb = mDegBacklog.getText().toString();
        } else if (getIntent().getStringExtra("DATA").equals("mca")) {
            bb = mMcaBacklog.getText().toString();
        }
        Intent intent = new Intent(getApplicationContext(), PlacementRegistration4.class);
        intent.putExtra("DATA", bb);
        startActivity(intent);
    }
}
