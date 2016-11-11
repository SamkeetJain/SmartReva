package com.samkeet.smartreva;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import static android.R.attr.button;

public class LauncherActivity extends AppCompatActivity {


    public Button Attendence, Results, Notes, Notification, Events, Councling, Library;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);

        Attendence= (Button) findViewById(R.id.attendence_button);
        Results= (Button) findViewById(R.id.result_button);
        Notes= (Button) findViewById(R.id.notes_button);
        Notification= (Button) findViewById(R.id.notification_button);
        Events= (Button) findViewById(R.id.events_button);
        Councling= (Button) findViewById(R.id.councling_button);
        Library= (Button) findViewById(R.id.library_button);


    }

    public void Attendence(View v){

    }
    public void Results(View v){

    }
    public void Notes(View v){

    }
    public void Notification(View v){

    }
    public void Events(View v){

    }
    public void Councling(View v){

    }
    public void Library(View v){

    }
}
