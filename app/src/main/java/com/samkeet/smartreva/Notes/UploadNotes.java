package com.samkeet.smartreva.Notes;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.samkeet.smartreva.R;

import java.io.File;

public class UploadNotes extends AppCompatActivity {

    private static final int PICK_FILE_REQUEST = 101;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_notes);

    }

    public void BackButton(View v) {
        finish();
    }


}
