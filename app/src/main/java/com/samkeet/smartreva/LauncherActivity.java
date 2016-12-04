package com.samkeet.smartreva;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.samkeet.smartreva.AlumniCell.AlumniCellActivity;
import com.samkeet.smartreva.Attendence.AttendenceMainActivity;
import com.samkeet.smartreva.Councling.CounclingMainActivity;
import com.samkeet.smartreva.Events.EventsMainActivity;
import com.samkeet.smartreva.Fees.FeesMainActivity;
import com.samkeet.smartreva.Library.LibraryMainActivity;
import com.samkeet.smartreva.Notes.NotesMainActivity;
import com.samkeet.smartreva.Notification.NotificationMainActivity;
import com.samkeet.smartreva.Results.ResultsMainActivity;
import com.samkeet.smartreva.Setings.SettingsMainActivity;
import com.samkeet.smartreva.Timetable.TimetableMainActivity;

public class LauncherActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);

        Constants.SharedPreferenceData.initSharedPreferenceData(getSharedPreferences(Constants.SharedPreferenceData.SHAREDPREFERENCES, MODE_PRIVATE));

        if (!Constants.SharedPreferenceData.getIsLoggedIn().equals("yes")) {
            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
            startActivityForResult(intent, 201);
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 201) {
            if (resultCode == RESULT_OK) {
                if (!Constants.SharedPreferenceData.getIsLoggedIn().equals("yes")) {
                    Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                    startActivityForResult(intent, 201);
                }
            }
        }
    }

    public void Attendence(View v) {
        Intent intent = new Intent(getApplicationContext(), AttendenceMainActivity.class);
        startActivity(intent);
    }

    public void Result(View v) {
        Intent intent = new Intent(getApplicationContext(), ResultsMainActivity.class);
        startActivity(intent);
    }

    public void Notes(View v) {
        Intent intent = new Intent(getApplicationContext(), NotesMainActivity.class);
        startActivity(intent);
    }

    public void Placements(View v) {
//        Intent intent = new Intent(getApplicationContext(), NotificationMainActivity.class);
//        startActivity(intent);
    }
    public void Notification(View v) {
        Intent intent = new Intent(getApplicationContext(), NotificationMainActivity.class);
        startActivity(intent);
    }

    public void Events(View v) {
        Intent intent = new Intent(getApplicationContext(), EventsMainActivity.class);
        startActivity(intent);
    }

    public void Councling(View v) {
        Intent intent = new Intent(getApplicationContext(), CounclingMainActivity.class);
        startActivity(intent);

    }

    public void Library(View v) {
        Intent intent = new Intent(getApplicationContext(), LibraryMainActivity.class);
        startActivity(intent);

    }

    public void Profile(View v) {
        Intent intent = new Intent(getApplicationContext(), ProfileStudentActivity.class);
        startActivity(intent);
    }

    public void Timetable(View v) {
        Intent intent = new Intent(getApplicationContext(), TimetableMainActivity.class);
        startActivity(intent);
    }

    public void Alumini(View v) {
        Intent intent = new Intent(getApplicationContext(), AlumniCellActivity.class);
        startActivity(intent);
    }

    public void Fees(View v) {
        Intent intent = new Intent(getApplicationContext(), FeesMainActivity.class);
        startActivity(intent);
    }

}
