package com.samkeet.smartreva.AlumniCell;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.samkeet.smartreva.R;

import org.json.JSONException;
import org.json.JSONObject;

public class AlumniReferJobManager extends AppCompatActivity {

    public TextView mName, mCompany, mRole, mType, mDate, mDesc;
    public String name, company, role, type, date, desc;

    public JSONObject object;
    public String data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alumni_refer_job_manager);

        data = getIntent().getStringExtra("DATA");

        try {
            object = new JSONObject(data);
            name = object.getString("name");
            company = object.getString("company_name");
            role = object.getString("job_role");
            type = object.getString("job_type");
            date = object.getString("ddate");
            desc = object.getString("description");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        mName = (TextView) findViewById(R.id.name);
        mCompany = (TextView) findViewById(R.id.company);
        mRole = (TextView) findViewById(R.id.role);
        mType = (TextView) findViewById(R.id.type);
        mDate = (TextView) findViewById(R.id.date);
        mDesc = (TextView) findViewById(R.id.desc);

        mName.setText(name);
        mCompany.setText(company);
        mRole.setText(role);
        mType.setText(type);
        mDate.setText(date);
        mDesc.setText(desc);


    }

    public void BackButton(View v) {
        finish();
    }
}
