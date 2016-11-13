package com.samkeet.smartreva;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class ProfileStudentActivity extends AppCompatActivity {

    public Button EditProfileStudent, LogoutStudent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_student);

        EditProfileStudent = (Button) findViewById(R.id.EditProfileStudent_button);
        LogoutStudent = (Button) findViewById(R.id.LogoutStudent_button);
    }

    public void LogoutStudent(View v) {
        Constants.UserData.USER_ID=null;
        Constants.UserData.TOKEN=null;
        Constants.SharedPreferenceData.clearData();
        Toast.makeText(getApplicationContext(),"Loged Out",Toast.LENGTH_SHORT).show();
    }

    public void BackButton(View v) {
        finish();
    }
}
