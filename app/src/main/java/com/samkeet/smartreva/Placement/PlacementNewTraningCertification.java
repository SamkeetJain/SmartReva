package com.samkeet.smartreva.Placement;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.samkeet.smartreva.R;

public class PlacementNewTraningCertification extends AppCompatActivity {

    public EditText mTitle, mOrg, mSub, mDur, mFP, mTP;
    public String title, org, sub, dur, fp, tp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_placement_new_traning_certification);
    }

    public void BackButton(View v) {
        finish();
    }

    public void submit(View v) {

    }
}
