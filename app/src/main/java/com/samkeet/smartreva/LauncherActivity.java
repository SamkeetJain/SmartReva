package com.samkeet.smartreva;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

public class LauncherActivity extends AppCompatActivity {


    public Button Attendence, Results, Notes, Notification, Events, Councling, Library;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);

        Attendence= (Button) findViewById(R.id.attendence_button);


    }
}
