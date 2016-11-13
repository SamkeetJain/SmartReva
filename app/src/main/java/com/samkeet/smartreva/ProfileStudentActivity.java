package com.samkeet.smartreva;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ProfileStudentActivity extends AppCompatActivity {

    public Button EditProfileStudent, LogoutStudent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_student);

        EditProfileStudent = (Button) findViewById(R.id.EditProfileStudent_button);
        LogoutStudent = (Button) findViewById(R.id.LogoutStudent_button);
    }

    public void BackButton(View v){
        finish();
    }
}
