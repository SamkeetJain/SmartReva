package com.samkeet.smartreva.AlumniCell;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.Toast;

import com.samkeet.smartreva.Constants;
import com.samkeet.smartreva.R;

public class AlumniCellActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alumini_cell);

//
    }

    public void Registeration(View view) {
        Intent intent = new Intent(getApplicationContext(), AlunmiRegistration.class);
        startActivity(intent);
    }

    public void BackButton(View v) {
        finish();
    }
//    public void UploadDetails(View v){
//        Intent intent=new Intent(getApplicationContext(), UploadActivity.class);
//        startActivity(intent);
}

