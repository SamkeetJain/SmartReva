package com.samkeet.smartreva.Attendence;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.samkeet.smartreva.R;

public class AttendenceMainActivity extends AppCompatActivity {

    public Button TakeAttendence, ViewAttendence, GenerateAttendenceReport;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendence_main);

        TakeAttendence = (Button) findViewById(R.id.take_attendence_button);
        ViewAttendence = (Button) findViewById(R.id.view_attendence_button);
        GenerateAttendenceReport = (Button) findViewById(R.id.generate_attendence_report_button);

    }

    public void TakeAttendence(View v){

        //TODO Connect to server for Authentication and then proceed.
        Intent intent =new Intent(getApplicationContext(),TakeAttendence.class);
        startActivity(intent);
    }

    public void ViewAttendence(View v){

        //TODO Connect to server for Authentication and then proceed.

        Intent intent =new Intent(getApplicationContext(),ViewAttendence.class);
        startActivity(intent);
    }

    public void GenerateAttendenceReport(View v) {

        //TODO Connect to server for Authentication and then proceed.

        Intent intent = new Intent(getApplicationContext(),GenerateAttendenceReport.class);
        startActivity(intent);

    }

}
