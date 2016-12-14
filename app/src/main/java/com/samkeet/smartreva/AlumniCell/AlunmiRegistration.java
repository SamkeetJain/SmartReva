package com.samkeet.smartreva.AlumniCell;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Spinner;

import com.samkeet.smartreva.R;

public class AlunmiRegistration extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alunmi_registration);
    }

    public void BackButton (View v){
        finish();
    }

}
