package com.samkeet.smartreva;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.samkeet.smartreva.Attendence.AttendenceMainActivity;
import com.samkeet.smartreva.Councling.CounclingMainActivity;
import com.samkeet.smartreva.Events.EventsMainActivity;
import com.samkeet.smartreva.Library.LibraryMainActivity;
import com.samkeet.smartreva.Notes.NotesMainActivity;
import com.samkeet.smartreva.Notification.NotificationMainActivity;
import com.samkeet.smartreva.Results.ResultsMainActivity;

import static android.R.attr.button;

public class LauncherActivity extends AppCompatActivity {


    public ImageView Attendence, Results, Notes, Notification, Events, Councling, Library, Profile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);

        Constants.SharedPreferenceData.initSharedPreferenceData(getSharedPreferences(Constants.SharedPreferenceData.SHAREDPREFERENCES,MODE_PRIVATE));

        Attendence = (ImageView) findViewById(R.id.attendence_button);
        Results = (ImageView) findViewById(R.id.result_button);
        Notes = (ImageView) findViewById(R.id.notes_button);
        Notification = (ImageView) findViewById(R.id.notification_button);
        Events = (ImageView) findViewById(R.id.events_button);
        Councling = (ImageView) findViewById(R.id.councling_button);
        Library = (ImageView) findViewById(R.id.library_button);
        Profile = (ImageView) findViewById(R.id.Profile_button);


        if(!Constants.SharedPreferenceData.getIsLoggedIn().equals("yes")){
            Intent intent=new Intent(getApplicationContext(),LoginActivity.class);
            startActivity(intent);
        }

    }

    public void Attendence(View v) {
        Intent intent=new Intent(getApplicationContext(), AttendenceMainActivity.class);
        startActivity(intent);
    }

    public void Result(View v) {
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

    public void Profile(View v){
        Intent intent=new Intent(getApplicationContext(), ProfileStudentActivity.class);
        startActivity(intent);
    }

    /*public void LogOut(View v){
        Constants.UserData.USER_ID=null;
        Constants.UserData.TOKEN=null;
        Constants.SharedPreferenceData.clearData();
        Toast.makeText(getApplicationContext(),"Loged Out",Toast.LENGTH_SHORT).show();
    }*/

    @Override
    protected void onResume() {
        super.onResume();
        if(!Constants.SharedPreferenceData.getIsLoggedIn().equals("yes")){
            Intent intent=new Intent(getApplicationContext(),LoginActivity.class);
            startActivity(intent);
        }
    }
}
