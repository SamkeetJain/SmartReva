package com.samkeet.smartreva.Notes;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.samkeet.smartreva.R;

public class NotesUploadNotes extends AppCompatActivity {

    private static final int PICK_FILE_REQUEST = 101;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes_upload_notes);

    }

    public void BackButton(View v) {
        finish();
    }


}
