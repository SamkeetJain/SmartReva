package com.samkeet.smartreva.AlumniCell;

import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.samkeet.smartreva.R;

import org.json.JSONException;
import org.json.JSONObject;

public class AlumniNotificationManager extends AppCompatActivity {

    public JSONObject object;
    public String title,message,ddate;
    public TextView mTitle,mMessage,mDdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alumni_notification_manager);

        String temp = getIntent().getStringExtra("DATA");

        try {
            object= new JSONObject(temp);
            title = object.getString("title");
            message=object.getString("message");
            ddate=object.getString("ddate");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        mTitle= (TextView) findViewById(R.id.title);
        mMessage= (TextView) findViewById(R.id.message);
        mDdate  = (TextView) findViewById(R.id.time);

        mTitle.setText(title);
        mDdate.setText(ddate);
        mMessage.setText(message);


    }
    public void BackButton (View v){
        finish();
    }
}
