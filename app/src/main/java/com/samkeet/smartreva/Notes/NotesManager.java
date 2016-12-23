package com.samkeet.smartreva.Notes;

import android.app.DownloadManager;
import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.crash.FirebaseCrash;
import com.samkeet.smartreva.Constants;
import com.samkeet.smartreva.R;

import org.json.JSONException;
import org.json.JSONObject;

public class NotesManager extends AppCompatActivity {

    public JSONObject noteObjects;
    public TextView mTitle, mCourse, mDeptCode, mSem, mMessage;
    public String title, course, deptCode, sem, message, filename;
    public String path;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes_manager);

        mTitle = (TextView) findViewById(R.id.title);
        mCourse = (TextView) findViewById(R.id.course);
        mDeptCode = (TextView) findViewById(R.id.deptcode);
        mSem = (TextView) findViewById(R.id.sem);
        mMessage = (TextView) findViewById(R.id.message);

        try {
            noteObjects = new JSONObject(getIntent().getStringExtra("DATA"));
            title = noteObjects.getString("title");
            course = noteObjects.getString("course");
            deptCode = noteObjects.getString("deptCode");
            sem = noteObjects.getString("sem");
            message = noteObjects.getString("message");
            filename = noteObjects.getString("filename");

        } catch (JSONException e) {
            FirebaseCrash.report(new Exception(e));
            e.printStackTrace();
        }

        mTitle.setText(title);
        mCourse.setText(course);
        mDeptCode.setText(deptCode);
        mSem.setText(sem);
        mMessage.setText(message);

        path= Constants.URLs.BASE+"uploads/";

        String temp[] = filename.split("/");
        filename=temp[temp.length-1];
        path=path+temp[temp.length-1];
        path=path.replaceAll(" ","%20");
    }

    public void Download(View v) {
        String url = path;
        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));
        request.setTitle(filename);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            request.allowScanningByMediaScanner();
            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        }
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, filename);

        DownloadManager manager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
        manager.enqueue(request);
        Toast.makeText(getApplicationContext(),"Download Started...",Toast.LENGTH_SHORT).show();
    }

    public void BackButton(View v) {
        finish();
    }
}
