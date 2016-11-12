package com.samkeet.smartreva.Notes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.samkeet.smartreva.R;

public class NotesMainActivity extends AppCompatActivity {

    public Button UploadNotes,ViewNotes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes_main);

        UploadNotes = (Button) findViewById(R.id.upload_notes_button);
        ViewNotes = (Button) findViewById(R.id.view_notes_button);
    }

    public void UploadNotes(View v) {
        //TODO Connect to server for Authentication and then proceed.

        Intent intent = new Intent(getApplicationContext(),UploadNotes.class);
        startActivity(intent);
    }

    public void ViewNotes(View v) {
        //TODO Connect to server for Authentication and then proceed.

        Intent intent =new Intent(getApplicationContext(),ViewNotes.class);
        startActivity(intent);
    }
}
