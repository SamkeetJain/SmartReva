package com.samkeet.smartreva.AlumniCell;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.samkeet.smartreva.R;

public class AlumniLoginActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alumini_login);


    }

    public void Login(View v) {
        Intent intent =new Intent(getApplicationContext(),AlumniMainActivity.class);
        startActivity(intent);
        finish();
    }

    public void register(View v) {
        Intent intent = new Intent(getApplicationContext(), AlunmiRegistrationActivity.class);
        startActivity(intent);
    }

    public void BackButton(View v) {
        finish();
    }
}

