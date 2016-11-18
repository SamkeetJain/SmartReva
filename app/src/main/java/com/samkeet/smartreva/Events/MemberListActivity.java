package com.samkeet.smartreva.Events;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.samkeet.smartreva.R;

public class MemberListActivity extends AppCompatActivity {

    public TextView textView;
    public String data;
    public String[] members;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_list);
        textView= (TextView) findViewById(R.id.textview);
        data=getIntent().getStringExtra("DATA");
        members=data.split("\\|");
        String temp="";
        for(int i=0;i<members.length;i++){
            members[i]=members[i].trim();
            members[i]=members[i].replaceAll("\\t","");
            members[i]=members[i].replaceAll("\\|","");
            temp=temp+members[i]+"\n";
        }
        textView.setText(temp);

    }
}
