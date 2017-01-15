package com.samkeet.smartreva.Mentor;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.samkeet.smartreva.R;

public class MentorStudentAcademicDetailsManager extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mentor_student_academic_details_manager);
    }
    public void BackButton (View v){finish();}
}
