package com.samkeet.smartreva.Placement2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.samkeet.smartreva.Placement.PlacementMainActivity;
import com.samkeet.smartreva.R;

public class Placement2MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_placement_main2);
    }
    public void BackButton (View v){finish();}

    public void register(View v){
        //Toast.makeText(getApplicationContext(),"Once you register, it has to be approved by the mentor to log in",Toast.LENGTH_LONG).show();
        Intent intent=new Intent(getApplicationContext(),PlacementRegistration1.class);
        startActivity(intent);
    }

    public  void placementlogin (View v){
        Intent intent=new Intent(getApplicationContext(), PlacementMainActivity.class);
        startActivity(intent);
    }

}
