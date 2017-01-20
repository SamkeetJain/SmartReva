package com.samkeet.smartreva.Placement2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.samkeet.smartreva.R;

public class PlacementRegistration1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_placement_registration1);
    }
    public void BackButton (View v){finish();}

    public void NextButton (View v){
        Intent intent=new Intent(getApplicationContext(),PlacementRegistration2.class);
        startActivity(intent);
    }
}
