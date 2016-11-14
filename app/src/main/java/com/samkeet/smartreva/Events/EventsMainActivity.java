package com.samkeet.smartreva.Events;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.samkeet.smartreva.R;

public class EventsMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events_main);
    }

    public void BackButton(View v){
        finish();
    }

    public void AddEvent(View v){
        Intent intent =new Intent(getApplicationContext(),AddEventActivity.class);
        startActivity(intent);
    }


}
