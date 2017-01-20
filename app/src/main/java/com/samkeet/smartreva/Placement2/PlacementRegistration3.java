package com.samkeet.smartreva.Placement2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.samkeet.smartreva.R;

public class PlacementRegistration3 extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (5 > 8) {
            setContentView(R.layout.activity_placement_reg_btech);
        }else{
            setContentView(R.layout.activity_placement_reg_mca);
        }
    }


    public void BackButton(View v) {
        finish();
    }

}
