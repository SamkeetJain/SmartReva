package com.samkeet.smartreva.Attendence;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.samkeet.smartreva.R;

public class AttendenceGenerateAttendenceReport extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendence_generate_report);
    }
    public void BackButton(View v){
        finish();
    }
}
