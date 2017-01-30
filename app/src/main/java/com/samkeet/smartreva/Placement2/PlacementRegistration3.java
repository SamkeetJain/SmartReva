package com.samkeet.smartreva.Placement2;

import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.samkeet.smartreva.Constants;
import com.samkeet.smartreva.R;

import org.w3c.dom.Text;

public class PlacementRegistration3 extends AppCompatActivity {

    public TextInputLayout tBtechDplSchoolname, tBtechDplPercentage, tBtechDplYop, tBtechBeYoj, tBtechBeSem1, tBtechBeSem2, tBtechBeSem3, tBtechBeSem4, tBtechBeSem5, tBtechBeSem6, tBtechBeSem7, tBtechBeSem8, tBtechYeargap;
    public EditText mBtechDplSchoolname, mBtechDplPercentage, mBtechDplYop, mBtechBeYoj, mBtechBeSem1, mBtechBeSem2, mBtechBeSem3, mBtechBeSem4, mBtechBeSem5, mBtechBeSem6, mBtechBeSem7, mBtechBeSem8, mBtechYeargap, mBtechBacklog;
    public String btechDplSchoolname, btechDplPercentage, btechDplYop, btechBeYoj, btechBeSem1, btechBeSem2, btechBeSem3, btechBeSem4, btechBeSem5, btechBeSem6, btechBeSem7, btechBeSem8, btechYeargap, btechBacklog;

    public Button BtechSubbmit;

    public TextInputLayout tMtechDplSchoolname, tMtechDplPercentage, tMtechDplYop, tMtechBePercentage, tMtechBeUniversity, tMtechBeCollagename, tMtechBeYop, tMtechMtYoj, tMtechMtSem1, tMtechMtSem2, tMtechMtSem3, tMtechMtSem4, tMtechWorkxp, tMtechYearGap;
    public EditText mMtechDplSchoolname, mMtechDplPercentage, mMtechDplYop, mMtechBePercentage, mMtechBeUniversity, mMtechBeCollagename, mMtechBeYop, mMtechMtYoj, mMtechMtSem1, mMtechMtSem2, mMtechMtSem3, mMtechMtSem4, mMtechWorkxp, mMtechYearGap, mMtechBacklog;
    public String mtechDplSchoolname, mtechDplPercentage, mtechDplYop, mtechBePercentage, mtechBeUniversity, mtechBeCollagename, mtechBeYop, mtechMtYoj, mtechMtSem1, mtechMtSem2, mtechMtSem3, mtechMtSem4, mtechWorkxp, mtechYearGap, mtechBacklog;

    public Button MtechSubbmit;

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
        if (getIntent().getStringExtra("DATA").equals("B Tech.")) {
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

        } else if (getIntent().getStringExtra("DATA").equals("M Tech.")) {
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


        } else if (getIntent().getStringExtra("DATA").equals("M.B.A./M Com.")) {
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


        } else if (getIntent().getStringExtra("DATA").equals("Degree")) {
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


        } else if (getIntent().getStringExtra("DATA").equals("M.C.A.")) {
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
        if (!validMmDegPercentage()) {
            return;
        }
        if (!validMmDegUniversity()) {
            return;
        }
        if (!validMmDegCollagename()) {
            return;
        }
        if (!validMmDegYop()) {
            return;
        }
        if (!validMmYoj()) {
            return;
        }
        /*if (!validMmS1()) {
            return;
        }
        if (!validMmS2()) {
            return;
        }
        if (!validMmS3()) {
            return;
        }
        if (!validMmS4()) {
            return;
        }*/
        if (!validMmBacklog()) {
            return;
        }
        /*if (!validMmWorkxp()) {
            return;
        }
        if (!validMmyeargap()) {
            return;
        }*/
    }

    private boolean validMmDegPercentage() {
        if (mmDegPercentage.isEmpty()) {
            tMmDegPercentage.setError("Invalid Percentage");
            requestFocus(mMmDegPercentage);
            return false;
        }
        if (mmDegPercentage.length() > 5) {
            tMmDegPercentage.setError("Percentage should be less then 5 Characters");
            requestFocus(mMmDegPercentage);
            return false;
        }
        if (mmDegPercentage.length() < 2) {
            tMmDegPercentage.setError("Invalid Percentage");
            requestFocus(mMmDegPercentage);
            return false;

        }
        if (Constants.Methods.checkForSpecial(mmDegPercentage)) {
            tMmDegPercentage.setError("Remove Special Characters");
            requestFocus(mMmDegPercentage);
            return false;
        } else {
            tMmDegPercentage.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validMmDegUniversity() {
        if (mmDegUniversity.isEmpty()) {
            tMmDegUniversity.setError("Invalid University Name");
            requestFocus(mMmDegUniversity);
            return false;
        }
        if (mmDegUniversity.length() > 32) {
            tMmDegUniversity.setError("University Name should be less then 32 Characters");
            requestFocus(mMmDegUniversity);
            return false;
        }
        if (Constants.Methods.checkForSpecial(mmDegUniversity)) {
            tMmDegUniversity.setError("Remove Special Characters");
            requestFocus(mMmDegUniversity);
            return false;
        } else {
            tMmDegUniversity.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validMmDegCollagename() {
        if (mmDegCollagename.isEmpty()) {
            tMmDegCollagename.setError("Invalid Collage Name");
            requestFocus(mMmDegCollagename);
            return false;
        }
        if (mmDegCollagename.length() > 128) {
            tMmDegCollagename.setError("Collage Name should be less then 128 Characters");
            requestFocus(mMmDegCollagename);
            return false;
        }
        if (Constants.Methods.checkForSpecial(mmDegCollagename)) {
            tMmDegCollagename.setError("Remove Special Characters");
            requestFocus(mMmDegCollagename);
            return false;
        } else {
            tMmDegCollagename.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validMmDegYop() {
        if (mmDegYop.isEmpty()) {
            tMmDegYop.setError("Invalid YOP");
            requestFocus(mMmDegYop);
            return false;
        }
        if (!(mmDegYop.length() == 4)) {
            tMmDegYop.setError("YOP should be of 4 characters");
            requestFocus(mMmDegYop);
            return false;
        }
        if (Constants.Methods.checkForSpecial(mmDegYop)) {
            tMmDegYop.setError("Remove Special Characters");
            requestFocus(mMmDegYop);
            return false;
        } else {
            tMmDegYop.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validMmYoj() {
        if (mmyoj.isEmpty()) {
            tMmyoj.setError("Invalid YOP");
            requestFocus(mMmyoj);
            return false;
        }
        if (!(mmyoj.length() == 4)) {
            tMmyoj.setError("YOP should be of 4 characters");
            requestFocus(mMmyoj);
            return false;
        }
        if (Constants.Methods.checkForSpecial(mmyoj)) {
            tMmyoj.setError("Remove Special Characters");
            requestFocus(mMmyoj);
            return false;
        } else {
            tMmyoj.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validMmS1() {
        if (mmSem1.isEmpty() || mmSem1.length() > 5 || mmSem1.length() < 2) {
            tMmSem1.setError("Invalid Marks");
            requestFocus(mMmSem1);
            return false;
        }
        if (Constants.Methods.checkForSpecial(mmSem1)) {
            tMmSem1.setError("Remove Special Characters");
            requestFocus(mMmSem1);
            return false;
        } else {
            tMmSem1.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validMmS2() {
        if (mmSem2.isEmpty() || mmSem2.length() > 5 || mmSem2.length() < 2) {
            tMmSem2.setError("Invalid Marks");
            requestFocus(mMmSem2);
            return false;
        }
        if (Constants.Methods.checkForSpecial(mmSem2)) {
            tMmSem2.setError("Remove Special Characters");
            requestFocus(mMmSem2);
            return false;
        } else {
            tMmSem2.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validMmS3() {
        if (mmSem3.isEmpty() || mmSem3.length() > 5 || mmSem3.length() < 2) {
            tMmSem3.setError("Invalid Marks");
            requestFocus(mMmSem3);
            return false;
        }
        if (Constants.Methods.checkForSpecial(mmSem3)) {
            tMmSem3.setError("Remove Special Characters");
            requestFocus(mMmSem3);
            return false;
        } else {
            tMmSem3.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validMmS4() {
        if (mmSem4.isEmpty() || mmSem4.length() > 5 || mmSem4.length() < 2) {
            tMmSem4.setError("Invalid Marks");
            requestFocus(mMmSem4);
            return false;
        }
        if (Constants.Methods.checkForSpecial(mmSem4)) {
            tMmSem4.setError("Remove Special Characters");
            requestFocus(mMmSem4);
            return false;
        } else {
            tMmSem4.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validMmBacklog() {
        if (mmBacklog.length() > 2 || mmBacklog.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Invalid Backlog", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (Constants.Methods.checkForSpecial(mmBacklog)) {
            Toast.makeText(getApplicationContext(), "Remove Special Charecters", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private boolean validMmWorkxp() {
        if (mmWorkxp.isEmpty()) {
            tMmWorkxp.setError("Invalid Work Experience");
            requestFocus(mMmWorkxp);
            return false;
        }

        if (Constants.Methods.checkForSpecial(mmWorkxp)) {
            tMmWorkxp.setError("Remove Special Characters");
            requestFocus(mMmWorkxp);
            return false;
        } else {
            tMmWorkxp.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validMmyeargap() {
        if (mmYeargap.isEmpty()) {
            tMmYeargap.setError("Invalid number of Year Gap");
            requestFocus(mMmYeargap);
            return false;
        }
        if (mmYeargap.length() > 2) {
            tMmYeargap.setError("Invalid Input");
            requestFocus(mMmYeargap);
            return false;
        }
        if (Constants.Methods.checkForSpecial(mmYeargap)) {
            tMmYeargap.setError("Remove Special Characters");
            requestFocus(mMmYeargap);
            return false;
        } else {
            tMmYeargap.setErrorEnabled(false);
        }
        return true;
    }


    public void degreeValidation() {
        if (!validDegCourse()) {
            return;
        }
        if (!validDegyoj()) {
            return;
        }
        /*if (!validDegS1()) {
            return;
        }
        if (!validDegS2()) {
            return;
        }
        if (!validDegS3()) {
            return;
        }
        if (!validDegS4()) {
            return;
        }
        if (!validDegS5()) {
            return;
        }
        if (!validDegS6()) {
            return;
        }*/
        if (!validDegBacklog()) {
            return;
        }
        /*if (!validDegYeargap()) {
            return;
        }*/
    }

    public boolean validDegCourse() {
        if (degCourse.isEmpty()) {
            tDegCourse.setError("Invalid Course");
            requestFocus(mDegCourse);
            return false;
        }
        if (degCourse.length() > 32) {
            tDegCourse.setError("Course should be less than 32 characters");
            requestFocus(mDegCourse);
            return false;
        }
        if (Constants.Methods.checkForSpecial(degCourse)) {
            tDegCourse.setError("Remove special charecters");
            requestFocus(mDegCourse);
            return false;
        } else {
            tDegCourse.setErrorEnabled(false);
        }
        return true;
    }

    public boolean validDegyoj() {
        if (degYoj.isEmpty()) {
            tDegYoj.setError("Invalid YOP");
            requestFocus(mDegYoj);
            return false;
        }
        if(!(degYoj.length() == 4)){
            tDegYoj.setError("YOP should be of 4 character");
            requestFocus(mDegYoj);
            return false;
        }
        if (Constants.Methods.checkForSpecial(degYoj)) {
            tDegYoj.setError("Remove Special Characters");
            requestFocus(mDegYoj);
            return false;
        } else {
            tDegYoj.setErrorEnabled(false);
        }
        return true;
    }

    public boolean validDegS1() {
        if (degSem1.isEmpty() || degSem1.length() > 5 || degSem1.length() < 2) {
            tDegSem1.setError("Invalid Marks");
            requestFocus(mDegSem1);
            return false;
        }
        if (Constants.Methods.checkForSpecial(degSem1)) {
            tDegSem1.setError("Remove Special Characters");
            requestFocus(mDegSem1);
            return false;
        } else {
            tDegSem1.setErrorEnabled(false);
        }
        return true;
    }

    public boolean validDegS2() {
        if (degSem2.isEmpty() || degSem2.length() > 5 || degSem2.length() < 2) {
            tDegSem2.setError("Invalid Marks");
            requestFocus(mDegSem2);
            return false;
        }
        if (Constants.Methods.checkForSpecial(degSem2)) {
            tDegSem2.setError("Remove Special Characters");
            requestFocus(mDegSem2);
            return false;
        } else {
            tDegSem2.setErrorEnabled(false);
        }
        return true;
    }

    public boolean validDegS3() {
        if (degSem3.isEmpty() || degSem3.length() > 5 || degSem3.length() < 2) {
            tDegSem3.setError("Invalid Marks");
            requestFocus(mDegSem3);
            return false;
        }
        if (Constants.Methods.checkForSpecial(degSem3)) {
            tDegSem3.setError("Remove Special Characters");
            requestFocus(mDegSem3);
            return false;
        } else {
            tDegSem3.setErrorEnabled(false);
        }
        return true;
    }

    public boolean validDegS4() {
        if (degSem4.isEmpty() || degSem4.length() > 5 || degSem4.length() < 2) {
            tDegSem4.setError("Invalid Marks");
            requestFocus(mDegSem4);
            return false;
        }
        if (Constants.Methods.checkForSpecial(degSem4)) {
            tDegSem4.setError("Remove Special Characters");
            requestFocus(mDegSem4);
            return false;
        } else {
            tDegSem4.setErrorEnabled(false);
        }
        return true;
    }

    public boolean validDegS5() {
        if (degSem5.isEmpty() || degSem5.length() > 5 || degSem5.length() < 2) {
            tDegSem5.setError("Invalid Marks");
            requestFocus(mDegSem5);
            return false;
        }
        if (Constants.Methods.checkForSpecial(degSem5)) {
            tDegSem5.setError("Remove Special Characters");
            requestFocus(mDegSem5);
            return false;
        } else {
            tDegSem5.setErrorEnabled(false);
        }
        return true;
    }

    public boolean validDegS6() {
        if (degSem6.isEmpty() || degSem6.length() > 5 || degSem6.length() < 2) {
            tDegSem6.setError("Invalid Marks");
            requestFocus(mDegSem6);
            return false;
        }
        if (Constants.Methods.checkForSpecial(degSem6)) {
            tDegSem6.setError("Remove Special Characters");
            requestFocus(mDegSem6);
            return false;
        } else {
            tDegSem6.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validDegBacklog() {
        if (degBacklog.length() > 2 || degBacklog.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Invalid Backlog", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }


    public boolean validDegYeargap() {
        if (degYeargap.isEmpty()) {
            tDegYeargap.setError("Invalid number of Year Gap");
            requestFocus(mDegYeargap);
            return false;
        }
        if (degYeargap.length() > 2) {
            tDegYeargap.setError("Invalid Input");
            requestFocus(mDegYeargap);
            return false;
        }
        if (Constants.Methods.checkForSpecial(degYeargap)) {
            tDegYeargap.setError("Remove Special Characters");
            requestFocus(mDegYeargap);
            return false;
        } else {
            tDegYeargap.setErrorEnabled(false);
        }
        return true;
    }


    public void mtechvalidation() {
        if (!validmtechDlpSchoolname()) {
            return;
        }
        if (!validmtechDlpPercentage()) {
            return;
        }
        if (!validmtechDlpYop()) {
            return;
        }
        if (!validatemtechBePercentage()) {
            return;
        }
        if (!validmtechBeUniversity()) {
            return;
        }
        if (!validmtechBeCollagename()) {
            return;
        }
        if (!validmtechBeYop()) {
            return;
        }
        if (!validmtechMtYoj()) {
            return;
        }
        /*if (!validmtechMtS1()) {
            return;
        }
        if (!validmtechMtS2()) {
            return;
        }
        if (!validmtechMtS3()) {
            return;
        }
        if (!validmtechMtS4()) {
            return;
        }*/
        if (!validmtechBacklog()) {
            return;
        }
        /*if (!validmtechWorkxp()) {
            return;
        }
        if (!validmtechYeargap()) {
            return;
        }*/
    }

    private boolean validmtechDlpSchoolname() {
        if (mtechDplSchoolname.isEmpty()) {
            tMtechDplSchoolname.setError("Invalid School Name");
            requestFocus(mMtechDplSchoolname);
            return false;
        }
        if (mtechDplSchoolname.length() > 128) {
            tMtechDplSchoolname.setError("School Name should be less then 128 Characters");
            requestFocus(mMtechDplSchoolname);
            return false;
        }
        if (Constants.Methods.checkForSpecial(mtechDplSchoolname)) {
            tMtechDplSchoolname.setError("Remove Special Characters");
            requestFocus(mMtechDplSchoolname);
            return false;
        } else {
            tMtechDplSchoolname.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validmtechDlpPercentage() {
        if (mtechDplPercentage.isEmpty()) {
            tMtechDplPercentage.setError("Invalid Percentage");
            requestFocus(mMtechDplPercentage);
            return false;
        }
        if (mtechDplPercentage.length() > 5) {
            tMtechDplPercentage.setError("Percentage Name should be less then 5 Characters");
            requestFocus(mMtechDplPercentage);
            return false;
        }
        if (mtechDplPercentage.length() < 2) {
            tMtechDplPercentage.setError("Invalid Percentage");
            requestFocus(mMtechDplPercentage);
            return false;

        }
        if (Constants.Methods.checkForSpecial(mtechDplPercentage)) {
            tMtechDplPercentage.setError("Remove Special Characters");
            requestFocus(mMtechDplPercentage);
            return false;
        } else {
            tMtechDplPercentage.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validmtechDlpYop() {
        if (mtechDplYop.isEmpty()) {
            tMtechDplYop.setError("Invalid YOP");
            requestFocus(mMtechDplYop);
            return false;
        }
        if (!(mtechDplYop.length() == 4)) {
            tMtechDplYop.setError("YOP should be of 4 characters");
            requestFocus(mMtechDplYop);
            return false;
        }
        if (Constants.Methods.checkForSpecial(mtechDplYop)) {
            tMtechDplYop.setError("Remove Special Characters");
            requestFocus(mMtechDplYop);
            return false;
        } else {
            tMtechDplYop.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validatemtechBePercentage() {
        if (mtechBePercentage.isEmpty()) {
            tMtechBePercentage.setError("Invalid Percentage");
            requestFocus(mMtechBePercentage);
            return false;
        }
        if (mtechBePercentage.length() > 5) {
            tMtechBePercentage.setError("Percentage Name should be less then 5 Characters");
            requestFocus(mMtechBePercentage);
            return false;
        }
        if (mtechBePercentage.length() < 2) {
            tMtechBePercentage.setError("Invalid Percentage");
            requestFocus(mMtechBePercentage);
            return false;

        }
        if (Constants.Methods.checkForSpecial(mtechBePercentage)) {
            tMtechBePercentage.setError("Remove Special Characters");
            requestFocus(mMtechBePercentage);
            return false;
        } else {
            tMtechBePercentage.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validmtechBeUniversity() {
        if (mtechBeUniversity.isEmpty()) {
            tMtechBeUniversity.setError("Invalid University Name");
            requestFocus(mMtechBeUniversity);
            return false;
        }
        if (mtechBeUniversity.length() > 32) {
            tMtechBeUniversity.setError("University Name should be less then 32 Characters");
            requestFocus(mMtechBeUniversity);
            return false;
        }
        if (Constants.Methods.checkForSpecial(mtechBeUniversity)) {
            tMtechBeUniversity.setError("Remove Special Characters");
            requestFocus(mMtechBeUniversity);
            return false;
        } else {
            tMtechBeUniversity.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validmtechBeCollagename() {
        if (mtechBeCollagename.isEmpty()) {
            tMtechBeCollagename.setError("Invalid Collage Name");
            requestFocus(mMtechBeCollagename);
            return false;
        }
        if (mtechBeCollagename.length() > 32) {
            tMtechBeCollagename.setError("Collage Name should be less then 32 Characters");
            requestFocus(mMtechBeCollagename);
            return false;
        }
        if (Constants.Methods.checkForSpecial(mtechBeCollagename)) {
            tMtechBeCollagename.setError("Remove Special Characters");
            requestFocus(mMtechBeCollagename);
            return false;
        } else {
            tMtechBeCollagename.setErrorEnabled(false);
        }
        return true;
    }


    private boolean validmtechBeYop() {
        if (mtechBeYop.isEmpty()) {
            tMtechBeYop.setError("Invalid YOP");
            requestFocus(mMtechBeYop);
            return false;
        }
        if (!(mtechBeYop.length() == 4)) {
            tMtechBeYop.setError("YOP should be of 4 characters");
            requestFocus(mMtechBeYop);
            return false;
        }
        if (Constants.Methods.checkForSpecial(mtechBeYop)) {
            tMtechBeYop.setError("Remove Special Characters");
            requestFocus(mMtechBeYop);
            return false;
        } else {
            tMtechBeYop.setErrorEnabled(false);
        }
        return true;
    }


    private boolean validmtechMtYoj() {
        if (mtechMtYoj.isEmpty() || !(mtechMtYoj.length() == 4)) {
            tMtechMtYoj.setError("Invalid YOP");
            requestFocus(mMtechMtYoj);
            return false;
        }
        if (!(mtechMtYoj.length() == 4)) {
            tMtechMtYoj.setError("YOP should be of 4 characters");
            requestFocus(mMtechMtYoj);
            return false;
        }
        if (Constants.Methods.checkForSpecial(mtechMtYoj)) {
            tMtechMtYoj.setError("Remove Special Characters");
            requestFocus(mMtechMtYoj);
            return false;
        } else {
            tMtechMtYoj.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validmtechMtS1() {
        if (mtechMtSem1.isEmpty() || mtechMtSem1.length() > 5 || mtechMtSem1.length() < 2) {
            tMtechMtSem1.setError("Invalid Marks");
            requestFocus(mMtechMtSem1);
            return false;
        }
        if (Constants.Methods.checkForSpecial(mtechMtSem1)) {
            tMtechMtSem1.setError("Remove Special Characters");
            requestFocus(mMtechMtSem1);
            return false;
        } else {
            tMtechMtSem1.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validmtechMtS2() {
        if (mtechMtSem2.isEmpty() || mtechMtSem2.length() > 5 || mtechMtSem2.length() < 2) {
            tMtechMtSem2.setError("Invalid Marks");
            requestFocus(mMtechMtSem2);
            return false;
        }
        if (Constants.Methods.checkForSpecial(mtechMtSem2)) {
            tMtechMtSem2.setError("Remove Special Characters");
            requestFocus(mMtechMtSem2);
            return false;
        } else {
            tMtechMtSem2.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validmtechMtS3() {
        if (mtechMtSem3.isEmpty() || mtechMtSem3.length() > 5 || mtechMtSem3.length() < 2) {
            tMtechMtSem3.setError("Invalid Marks");
            requestFocus(mMtechMtSem3);
            return false;
        }
        if (Constants.Methods.checkForSpecial(mtechMtSem3)) {
            tMtechMtSem3.setError("Remove Special Characters");
            requestFocus(mMtechMtSem3);
            return false;
        } else {
            tMtechMtSem3.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validmtechMtS4() {
        if (mtechMtSem4.isEmpty() || mtechMtSem4.length() > 5 || mtechMtSem4.length() < 2) {
            tMtechMtSem4.setError("Invalid Marks");
            requestFocus(mMtechMtSem4);
            return false;
        }
        if (Constants.Methods.checkForSpecial(mtechMtSem4)) {
            tMtechMtSem4.setError("Remove Special Characters");
            requestFocus(mMtechMtSem4);
            return false;
        } else {
            tMtechMtSem4.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validmtechBacklog() {
        if (mtechBacklog.length() > 2 || mtechBacklog.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Invalid Backlog", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (Constants.Methods.checkForSpecial(mtechBacklog)) {
            Toast.makeText(getApplicationContext(), "Remove Special Characters", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private boolean validmtechWorkxp() {
        if (mtechWorkxp.isEmpty()) {
            tMtechWorkxp.setError("Invalid Work Experience");
            requestFocus(mMtechWorkxp);
            return false;
        }

        if (Constants.Methods.checkForSpecial(mtechWorkxp)) {
            tMtechWorkxp.setError("Remove Special Characters");
            requestFocus(mMtechWorkxp);
            return false;
        } else {
            tMtechWorkxp.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validmtechYeargap() {
        if (mtechYearGap.isEmpty()) {
            tMtechYearGap.setError("Invalid number of Year Gap");
            requestFocus(mMtechYearGap);
            return false;
        }
        if (mtechYearGap.length() > 2) {
            tMtechYearGap.setError("Invalid Input");
            requestFocus(mMtechYearGap);
            return false;
        }
        if (Constants.Methods.checkForSpecial(mtechYearGap)) {
            tMtechYearGap.setError("Remove Special Characters");
            requestFocus(mMtechYearGap);
            return false;
        } else {
            tMtechYearGap.setErrorEnabled(false);
        }
        return true;
    }


    public void btechvaliditaion() {
        if (!validbtechDplSchoolname()) {
            return;
        }
        if (!validbtechDplPercentage()) {
            return;
        }
        if (!validbtechDplYop()) {
            return;
        }
        if (!validbtechBeYoj()) {
            return;
        }
        /*if (!validbtechBeS1()) {
            return;
        }
        if (!validbtechBeS2()) {
            return;
        }
        if (!validbtechBeS3()) {
            return;
        }
        if (!validbtechBeS4()) {
            return;
        }
        if (!validbtechBeS5()) {
            return;
        }
        if (!validbtechBeS6()) {
            return;
        }
        if (!validbtechBeS7()) {
            return;
        }
        if (!validbtechBeS8()) {
            return;
        }*/
        if (!validbtechBacklog()) {
            return;
        }
        /*if (!validbtechYeargap()) {
            return;
        }*/

    }

    public boolean validbtechDplSchoolname() {
        if (btechDplSchoolname.isEmpty()) {
            tBtechDplSchoolname.setError("Invalid School Name");
            requestFocus(mBtechDplSchoolname);
            return false;
        }
        if (btechDplSchoolname.length() > 128) {
            tBtechDplSchoolname.setError("School Name should be less then 128 Characters");
            requestFocus(mBtechDplSchoolname);
            return false;
        }
        if (Constants.Methods.checkForSpecial(btechDplSchoolname)) {
            tBtechDplSchoolname.setError("Remove Special Characters");
            requestFocus(mBtechDplSchoolname);
            return false;
        } else {
            tBtechDplSchoolname.setErrorEnabled(false);
        }
        return true;
    }

    public boolean validbtechDplPercentage() {
        if (btechDplPercentage.isEmpty()) {
            tBtechDplPercentage.setError("Invalid Percentage");
            requestFocus(mBtechDplPercentage);
            return false;
        }
        if (btechDplPercentage.length() > 5) {
            tBtechDplPercentage.setError("YOP Name should be less then 5 Characters");
            requestFocus(mBtechDplPercentage);
            return false;
        }
        if (btechDplPercentage.length() < 2) {
            tBtechDplPercentage.setError("Invalid Percentage");
            requestFocus(mBtechDplPercentage);
            return false;

        }
        if (Constants.Methods.checkForSpecial(btechDplPercentage)) {
            tBtechDplPercentage.setError("Remove Special Characters");
            requestFocus(mBtechDplPercentage);
            return false;
        } else {
            tBtechDplPercentage.setErrorEnabled(false);
        }
        return true;
    }

    public boolean validbtechDplYop() {
        if (btechDplYop.isEmpty()) {
            tBtechDplYop.setError("Invalid YOP");
            requestFocus(mBtechDplYop);
            return false;
        }
        if (!(btechDplYop.length() == 4)) {
            tBtechDplYop.setError("YOP should be of 4 characters");
            requestFocus(mBtechDplYop);
            return false;
        }

        if (Constants.Methods.checkForSpecial(btechDplYop)) {
            tBtechDplYop.setError("Remove Special Characters");
            requestFocus(mBtechDplYop);
            return false;
        } else {
            tBtechDplYop.setErrorEnabled(false);
        }
        return true;
    }

    public boolean validbtechBeYoj() {
        if (btechBeYoj.isEmpty() || btechBeYoj.length() != 4) {
            tBtechBeYoj.setError("Invalid YOJ");
            requestFocus(mBtechBeYoj);
            return false;
        }

        if (Constants.Methods.checkForSpecial(btechBeYoj)) {
            tBtechBeYoj.setError("Remove Special Characters");
            requestFocus(mBtechBeYoj);
            return false;
        } else {
            tBtechBeYoj.setErrorEnabled(false);
        }
        return true;
    }

    public boolean validbtechBeS1() {
        if (btechBeSem1.isEmpty() || btechBeSem1.length() > 5 || btechBeSem1.length() < 2) {
            tBtechBeSem1.setError("Invalid Marks");
            requestFocus(mBtechBeSem1);
            return false;
        }
        if (Constants.Methods.checkForSpecial(btechDplSchoolname)) {
            tBtechBeSem1.setError("Remove Special Characters");
            requestFocus(mBtechBeSem1);
            return false;
        } else {
            tBtechBeSem1.setErrorEnabled(false);
        }
        return true;
    }

    public boolean validbtechBeS2() {
        if (btechBeSem2.isEmpty() || btechBeSem2.length() > 5 || btechBeSem2.length() < 2) {
            tBtechBeSem2.setError("Invalid Marks");
            requestFocus(mBtechBeSem2);
            return false;
        }
        if (Constants.Methods.checkForSpecial(btechDplSchoolname)) {
            tBtechBeSem2.setError("Remove Special Characters");
            requestFocus(mBtechBeSem2);
            return false;
        } else {
            tBtechBeSem2.setErrorEnabled(false);
        }
        return true;
    }

    public boolean validbtechBeS3() {
        if (btechBeSem3.isEmpty() || btechBeSem3.length() > 5 || btechBeSem3.length() < 2) {
            tBtechBeSem3.setError("Invalid Marks");
            requestFocus(mBtechBeSem3);
            return false;
        }
        if (Constants.Methods.checkForSpecial(btechDplSchoolname)) {
            tBtechBeSem3.setError("Remove Special Characters");
            requestFocus(mBtechBeSem3);
            return false;
        } else {
            tBtechBeSem3.setErrorEnabled(false);
        }
        return true;
    }

    public boolean validbtechBeS4() {
        if (btechBeSem4.isEmpty() || btechBeSem4.length() > 5 || btechBeSem4.length() < 2) {
            tBtechBeSem4.setError("Invalid Marks");
            requestFocus(mBtechBeSem4);
            return false;
        }
        if (Constants.Methods.checkForSpecial(btechDplSchoolname)) {
            tBtechBeSem4.setError("Remove Special Characters");
            requestFocus(mBtechBeSem4);
            return false;
        } else {
            tBtechBeSem4.setErrorEnabled(false);
        }
        return true;
    }

    public boolean validbtechBeS5() {
        if (btechBeSem5.isEmpty() || btechBeSem5.length() > 5 || btechBeSem5.length() < 2) {
            tBtechBeSem5.setError("Invalid Marks");
            requestFocus(mBtechBeSem5);
            return false;
        }
        if (Constants.Methods.checkForSpecial(btechDplSchoolname)) {
            tBtechBeSem5.setError("Remove Special Characters");
            requestFocus(mBtechBeSem5);
            return false;
        } else {
            tBtechBeSem5.setErrorEnabled(false);
        }
        return true;
    }

    public boolean validbtechBeS6() {
        if (btechBeSem6.isEmpty() || btechBeSem6.length() > 5 || btechBeSem6.length() < 2) {
            tBtechBeSem6.setError("Invalid Marks");
            requestFocus(mBtechBeSem6);
            return false;
        }
        if (Constants.Methods.checkForSpecial(btechDplSchoolname)) {
            tBtechBeSem6.setError("Remove Special Characters");
            requestFocus(mBtechBeSem6);
            return false;
        } else {
            tBtechBeSem6.setErrorEnabled(false);
        }
        return true;
    }

    public boolean validbtechBeS7() {
        if (btechBeSem7.isEmpty() || btechBeSem7.length() > 5 || btechBeSem7.length() < 2) {
            tBtechBeSem7.setError("Invalid Marks");
            requestFocus(mBtechBeSem7);
            return false;
        }
        if (Constants.Methods.checkForSpecial(btechDplSchoolname)) {
            tBtechBeSem7.setError("Remove Special Characters");
            requestFocus(mBtechBeSem7);
            return false;
        } else {
            tBtechBeSem7.setErrorEnabled(false);
        }
        return true;
    }

    public boolean validbtechBeS8() {
        if (btechBeSem8.isEmpty() || btechBeSem8.length() > 5 || btechBeSem8.length() < 2) {
            tBtechBeSem8.setError("Invalid Marks");
            requestFocus(mBtechBeSem8);
            return false;
        }
        if (Constants.Methods.checkForSpecial(btechDplSchoolname)) {
            tBtechBeSem8.setError("Remove Special Characters");
            requestFocus(mBtechBeSem8);
            return false;
        } else {
            tBtechBeSem8.setErrorEnabled(false);
        }
        return true;
    }

    public boolean validbtechBacklog() {
        if (btechBacklog.length() > 2 || btechBacklog.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Invalid Backlog", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }


    public boolean validbtechYeargap() {
        if (btechYeargap.isEmpty()) {
            tBtechYeargap.setError("Invalid number of Year Gap");
            requestFocus(mBtechYeargap);
            return false;
        }
        if (btechYeargap.length() > 2) {
            tBtechYeargap.setError("Invalid Input");
            requestFocus(mBtechYeargap);
            return false;
        }
        if (Constants.Methods.checkForSpecial(btechYeargap)) {
            tBtechYeargap.setError("Remove Special Characters");
            requestFocus(mBtechYeargap);
            return false;
        } else {
            tBtechYeargap.setErrorEnabled(false);
        }
        return true;
    }


    public void mcaValidate() {
        if (!validMcaDegPercentage()) {
            return;
        }
        if (!validMcaDegCourse()) {
            return;
        }
        if (!validMcaDegUniversity()) {
            return;
        }
        if (!validMcaDegCollagename()) {
            return;
        }
        if (!validMcaDegYop()) {
            return;
        }
        if (!validMcaYoj()) {
            return;
        }
        /*if (!validMcaS1()) {
            return;
        }
        if (!validMcaS2()) {
            return;
        }
        if (!validMcaS3()) {
            return;
        }
        if (!validMcaS4()) {
            return;
        }
        if (!validMcaS5()) {
            return;
        }
        if (!validMcaS6()) {
            return;
        }*/
        if (!validMcaBacklog()) {
            return;
        }
        /*if (!validMcaWorkxp()) {
            return;
        }
        if (!validMcaYeargap()) {
            return;
        }*/
    }

    private boolean validMcaDegPercentage() {
        if (mcaDegPercentage.isEmpty()) {
            tMcaDegPercentage.setError("Invalid Percentage");
            requestFocus(mMcaDegPercentage);
            return false;
        }
        if (mcaDegPercentage.length() > 5) {
            tMcaDegPercentage.setError("Percentage Name should be less then 5 Characters");
            requestFocus(mMcaDegPercentage);
            return false;
        }
        if (mcaDegPercentage.length() < 2) {
            tMcaDegPercentage.setError("Invalid Percentage");
            requestFocus(mMcaDegPercentage);
            return false;

        }
        if (Constants.Methods.checkForSpecial(mcaDegPercentage)) {
            tMcaDegPercentage.setError("Remove Special Characters");
            requestFocus(mMcaDegPercentage);
            return false;
        } else {
            tMcaDegPercentage.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validMcaDegCourse() {
        if (mcaDegCourse.isEmpty()) {
            tMcaDegCourse.setError("Invalid Course");
            requestFocus(mMcaDegCourse);
            return false;
        }
        if (mcaDegCourse.length() > 10) {
            tMcaDegCourse.setError("Courseshould be less then 32 Characters");
            requestFocus(mMcaDegCourse);
            return false;
        }
        if (Constants.Methods.checkForSpecial(mcaDegCourse)) {
            tMcaDegCourse.setError("Remove Special Characters");
            requestFocus(mMcaDegCourse);
            return false;
        } else {
            tMcaDegCourse.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validMcaDegUniversity() {
        if (mcaDegUniversity.isEmpty()) {
            tMcaDegUniversity.setError("Invalid University Name");
            requestFocus(mMcaDegUniversity);
            return false;
        }
        if (mcaDegUniversity.length() > 32) {
            tMcaDegUniversity.setError("University Name should be less then 32 Characters");
            requestFocus(mMcaDegUniversity);
            return false;
        }
        if (Constants.Methods.checkForSpecial(mcaDegUniversity)) {
            tMcaDegUniversity.setError("Remove Special Characters");
            requestFocus(mMcaDegUniversity);
            return false;
        } else {
            tMcaDegUniversity.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validMcaDegCollagename() {
        if (mcaDegCollagename.isEmpty()) {
            tMcaDegCollagename.setError("Invalid Collage Name");
            requestFocus(mMcaDegCollagename);
            return false;
        }
        if (mcaDegCollagename.length() > 128) {
            tMcaDegCollagename.setError("Collage Name should be less then 128 Characters");
            requestFocus(mMcaDegCollagename);
            return false;
        }
        if (Constants.Methods.checkForSpecial(mcaDegCollagename)) {
            tMcaDegCollagename.setError("Remove Special Characters");
            requestFocus(mMcaDegCollagename);
            return false;
        } else {
            tMcaDegCollagename.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validMcaDegYop() {
        if (mcaDegYop.isEmpty()) {
            tMcaDegYop.setError("Invalid YOP");
            requestFocus(mMcaDegYop);
            return false;
        }
        if (!((mcaDegYop.length() == 4))){
            tMcaDegYop.setError("YOP should be of 4 characters");
            requestFocus(mMcaDegYop);
            return false;
        }
        if (Constants.Methods.checkForSpecial(mcaDegYop)) {
            tMcaDegYop.setError("Remove Special Characters");
            requestFocus(mMcaDegYop);
            return false;
        } else {
            tMcaDegYop.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validMcaYoj() {
        if (mcaYoj.isEmpty()) {
            tMcaYoj.setError("Invalid YOP");
            requestFocus(mMcaYoj);
            return false;
        }
        if (!((mcaYoj.length() == 4))){
            tMcaYoj.setError("YOP should be of 4 characters");
            requestFocus(mMcaYoj);
            return false;
        }
        if (Constants.Methods.checkForSpecial(mcaYoj)) {
            tMcaYoj.setError("Remove Special Characters");
            requestFocus(mMcaYoj);
            return false;
        } else {
            tMcaYoj.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validMcaS1() {
        if (mcaSem1.isEmpty() || mcaSem1.length() > 5 || mcaSem1.length() < 2) {
            tMcaSem1.setError("Invalid Marks");
            requestFocus(mMcaSem1);
            return false;
        }
        if (Constants.Methods.checkForSpecial(mcaSem1)) {
            tMcaSem1.setError("Remove Special Characters");
            requestFocus(mMcaSem1);
            return false;
        } else {
            tMcaSem1.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validMcaS2() {
        if (mcaSem2.isEmpty() || mcaSem2.length() > 5 || mcaSem2.length() < 2) {
            tMcaSem2.setError("Invalid Marks");
            requestFocus(mMcaSem2);
            return false;
        }
        if (Constants.Methods.checkForSpecial(mcaSem2)) {
            tMcaSem2.setError("Remove Special Characters");
            requestFocus(mMcaSem2);
            return false;
        } else {
            tMcaSem2.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validMcaS3() {
        if (mcaSem3.isEmpty() || mcaSem3.length() > 5 || mcaSem3.length() < 2) {
            tMcaSem3.setError("Invalid Marks");
            requestFocus(mMcaSem3);
            return false;
        }
        if (Constants.Methods.checkForSpecial(mcaSem3)) {
            tMcaSem3.setError("Remove Special Characters");
            requestFocus(mMcaSem3);
            return false;
        } else {
            tMcaSem3.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validMcaS4() {
        if (mcaSem4.isEmpty() || mcaSem4.length() > 5 || mcaSem4.length() < 2) {
            tMcaSem4.setError("Invalid Marks");
            requestFocus(mMcaSem4);
            return false;
        }
        if (Constants.Methods.checkForSpecial(mcaSem4)) {
            tMcaSem4.setError("Remove Special Characters");
            requestFocus(mMcaSem4);
            return false;
        } else {
            tMcaSem4.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validMcaS5() {
        if (mcaSem5.isEmpty() || mcaSem5.length() > 5 || mcaSem5.length() < 2) {
            tMcaSem5.setError("Invalid Marks");
            requestFocus(mMcaSem5);
            return false;
        }
        if (Constants.Methods.checkForSpecial(mcaSem5)) {
            tMcaSem5.setError("Remove Special Characters");
            requestFocus(mMcaSem5);
            return false;
        } else {
            tMcaSem5.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validMcaS6() {
        if (mcaSem6.isEmpty() || mcaSem6.length() > 5 || mcaSem6.length() < 2) {
            tMcaSem6.setError("Invalid Marks");
            requestFocus(mMcaSem6);
            return false;
        }
        if (Constants.Methods.checkForSpecial(mcaSem6)) {
            tMcaSem6.setError("Remove Special Characters");
            requestFocus(mMcaSem6);
            return false;
        } else {
            tMcaSem6.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validMcaBacklog() {
        if (mcaBacklog.length() > 2 || mcaBacklog.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Invalid Backlog", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (Constants.Methods.checkForSpecial(mcaBacklog)) {
            Toast.makeText(getApplicationContext(), "Remove Special Charecters", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private boolean validMcaWorkxp() {
        if (mcaWorkxp.isEmpty()) {
            tMcaWorkxp.setError("Invalid Work Experience");
            requestFocus(mMcaWorkxp);
            return false;
        }

        if (Constants.Methods.checkForSpecial(mcaWorkxp)) {
            tMcaWorkxp.setError("Remove Special Characters");
            requestFocus(mMcaWorkxp);
            return false;
        } else {
            tMcaWorkxp.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validMcaYeargap() {
        if (mcaYeargap.isEmpty()) {
            tMcaYeargap.setError("Invalid number of Year Gap");
            requestFocus(mMcaYeargap);
            return false;
        }
        if (mcaYeargap.length() > 2) {
            tMcaYeargap.setError("Invalid Input");
            requestFocus(mMcaYeargap);
            return false;
        }
        if (Constants.Methods.checkForSpecial(mcaYeargap)) {
            tMcaYeargap.setError("Remove Special Characters");
            requestFocus(mMcaYeargap);
            return false;
        } else {
            tMcaYeargap.setErrorEnabled(false);
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
}
