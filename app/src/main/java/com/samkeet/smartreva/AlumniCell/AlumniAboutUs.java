package com.samkeet.smartreva.AlumniCell;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.crash.FirebaseCrash;
import com.google.firebase.iid.FirebaseInstanceId;
import com.samkeet.smartreva.Constants;
import com.samkeet.smartreva.R;

public class AlumniAboutUs extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alumni_about_us);


    }

    public void BackButton(View v){
        finish();
    }


}
