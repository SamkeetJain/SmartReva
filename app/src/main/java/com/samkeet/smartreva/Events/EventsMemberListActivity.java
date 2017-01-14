package com.samkeet.smartreva.Events;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.samkeet.smartreva.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class EventsMemberListActivity extends AppCompatActivity {

    public TextView textView;
    public String data;
    public String[] members;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events_member_list);
        textView= (TextView) findViewById(R.id.textview);
        data=getIntent().getStringExtra("DATA");
        try {
            JSONArray jsonArray =new JSONArray(data);
            members=new String[jsonArray.length()];
            for(int i=0;i<jsonArray.length();i++){
                JSONObject jsonObject=jsonArray.getJSONObject(i);
                members[i]=jsonObject.getString("members");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String temp="";
        for(int i=0;i<members.length;i++){
            temp=temp+members[i]+"\n";
        }
        textView.setText(temp);

    }

    public void BackButton(View v) {
        finish();
    }

}
