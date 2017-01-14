package com.samkeet.smartreva.mentor;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.samkeet.smartreva.R;

public class MentorMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mentor_main);
    }
    public void BackButton (View v){finish();}

    public void StudentDetails (View v){
        Intent intent = new Intent(getApplicationContext(), MentorStudentListActivity.class);
        startActivity(intent);
    }
    public void StudentAcademicDetails (View v){
        Intent intent = new Intent(getApplicationContext(),MentorStudentAcademicDetailsActivity.class);
        startActivity(intent);
    }
    public void SendNotification (View v){
        Intent intent = new Intent(getApplicationContext(), MentorSendNotificationActivity.class);
        startActivity(intent);
    }

}
