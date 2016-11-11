package com.samkeet.smartreva;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.samkeet.smartreva.Attendence.AttendenceMainActivity;
import com.samkeet.smartreva.Councling.CounclingMainActivity;
import com.samkeet.smartreva.Events.EventsMainActivity;
import com.samkeet.smartreva.Library.LibraryMainActivity;
import com.samkeet.smartreva.Notes.NotesMainActivity;
import com.samkeet.smartreva.Notification.NotificationMainActivity;
import com.samkeet.smartreva.Results.ResultsMainActivity;

import static android.R.attr.button;

public class LauncherActivity extends AppCompatActivity {


    public Button Attendence, Results, Notes, Notification, Events, Councling, Library;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);

        Attendence = (Button) findViewById(R.id.attendence_button);
        Results = (Button) findViewById(R.id.result_button);
        Notes = (Button) findViewById(R.id.notes_button);
        Notification = (Button) findViewById(R.id.notification_button);
        Events = (Button) findViewById(R.id.events_button);
        Councling = (Button) findViewById(R.id.councling_button);
        Library = (Button) findViewById(R.id.library_button);


    }

    public void Attendence(View v) {
        Intent intent=new Intent(getApplicationContext(), AttendenceMainActivity.class);
        startActivity(intent);
    }

    public void Results(View v) {
        Intent intent=new Intent(getApplicationContext(), ResultsMainActivity.class);
        startActivity(intent);
    }

    public void Notes(View v) {
        Intent intent=new Intent(getApplicationContext(), NotesMainActivity.class);
        startActivity(intent);
    }

    public void Notification(View v) {
        Intent intent=new Intent(getApplicationContext(), NotificationMainActivity.class);
        startActivity(intent);

    }

    public void Events(View v) {
        Intent intent=new Intent(getApplicationContext(), EventsMainActivity.class);
        startActivity(intent);
    }

    public void Councling(View v) {
        Intent intent=new Intent(getApplicationContext(), CounclingMainActivity.class);
        startActivity(intent);

    }

    public void Library(View v) {
        Intent intent=new Intent(getApplicationContext(), LibraryMainActivity.class);
        startActivity(intent);

    }
}
