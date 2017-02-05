package com.samkeet.smartreva.Mentor;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.samkeet.smartreva.R;

public class MentorPlavementMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mentor_plavement_main);
    }
    public void BackButton (View v){finish();}

    public void AddStudents (View v){
        Intent intent= new Intent(getApplicationContext(),MentorPlacementAddStudentActivity.class);
        startActivity(intent);
    }

    public void StudentAcademicDetails(View v){
        Intent intent = new Intent(getApplicationContext(),MentorPlacementAcademicDetailsActivity.class);
        startActivity(intent);
    }
}
