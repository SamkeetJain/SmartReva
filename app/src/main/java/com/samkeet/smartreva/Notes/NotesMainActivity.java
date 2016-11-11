package com.samkeet.smartreva.Notes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.samkeet.smartreva.R;

public class NotesMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes_main);

        UploadNotes = findViewById(R.id.upload_notes_button);
        ViewNotes = findViewById(R.id.view_notes_button);
    }

    public void UploadNotes(View v) {
        Intent intent = new Intent(getApplicationContext(),UploadNotes.class);
        startActivity(intent);
    }

    public void ViewNotes(View v) {
        Intent intent =new Intent(getApplicationContext(),ViewNotes.class);
        startActivity(intent);
    }
}
