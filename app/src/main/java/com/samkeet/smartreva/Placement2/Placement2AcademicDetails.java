package com.samkeet.smartreva.Placement2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

import com.samkeet.smartreva.R;

public class Placement2AcademicDetails extends AppCompatActivity {

    public EditText PDname, PDgender, PDdob, PDphoneno, PDemail, PDcourse, PDbranch, PDyeargap, PDworkxp, PDcaddress, PDpaddress;
    public EditText AD10score, AD10board, AD10schoolname, AD10yop, AD12score, AD12board, AD12schoolname, ADyop;
    public EditText BTdplScore, BTdplschoolname, BTdplyop, BTyoj, BTsem1, BTsem2, BTsem3, BTsem4, BTsem5, BTsem6, BTsem7, BTsem8;
    public EditText MTdplscore, MTdplschoolname, MTdplyop, MTbtscore, MTbtuniversity, MTbtcollegename, MTbtyop, MTyoj, MTsem1, MTsem2, MTsem3, MTsem4;
    public EditText Degcourse, Degyoj, Degsem1, Degsem2, Degsem3, Degsem4, Degsem5, Degsem6;
    public EditText MCAdegcourse, MCAdeguniversity, MCAdegyop, MCAdegscore, MCAdegcollegename, MCAyoj, MCAsem1, MCAsem2, MCAsem3, MCAsem4, MCAsem5, MCAsem6;
    public EditText MMdegscore, MMdeguniversity, MMdegcollegename, MMdegyop, MMyoj, MMsem1, MMsem2, MMsem3, MMsem4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_placement2_academic_details);

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
        ADyop = (EditText) findViewById(R.id.AD_12_yop);

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

    }
}
