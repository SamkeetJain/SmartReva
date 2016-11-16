package com.samkeet.smartreva.Notes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.samkeet.smartreva.R;

public class UploadNotes extends AppCompatActivity {

    private static final int PICK_FILE_REQUEST = 101;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_notes);

    }
    public void BackButton(View v){
        finish();
    }

    public void NotesUploadSelectFile(View v){
        Intent intent = new Intent();
        intent.setType("*/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select a File"), PICK_FILE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==PICK_FILE_REQUEST){
            Intent intent=new Intent(getApplicationContext(),UploadToServer.class);
            startActivity(intent);
        }
    }
}
