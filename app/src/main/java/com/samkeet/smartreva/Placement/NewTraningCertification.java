package com.samkeet.smartreva.Placement;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.samkeet.smartreva.R;

public class NewTraningCertification extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_traning_certification);
    }

    public void BackButton(View v){
        finish();
    }

    public void submit(View v){

    }
}
