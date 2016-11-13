package com.samkeet.smartreva;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import static android.R.attr.button;

public class ProfileFacultyActivity extends AppCompatActivity {


    public Button EditProfileFaculty, LogoutFaculty;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_faculty);

        EditProfileFaculty = (Button) findViewById(R.id.EditProfileFaculty_button);
        LogoutFaculty = (Button) findViewById(R.id.LogoutFaculty_button);
    }
    public void BackButton(View v){
        finish();
    }

}
