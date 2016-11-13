package com.samkeet.smartreva.Councling;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.samkeet.smartreva.R;

public class CounclingMyAppointment extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_councling_my_appointment);
        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        String mTitles[]= {"First","Secong","Thrid","Fourth","Fifth"};
        String mDesc[]={"gaffdfdbdkjnbfijknobdjkdnofbujvnd",
                        "bdijnfdobnlknifobjlkioubjnknobnkn",
                        "jdnojdlnknodfnlkaonbdknfnknknsifd",
                        "obnsdnobsnbofnbofnfbjldbndflbndfb",
                        "djfnnvadlfioewnlkftrjgnvsdnkffdnk"};
        mAdapter = new CounclingMyAppointmentAdapter(mTitles,mDesc);
        mRecyclerView.setAdapter(mAdapter);

    }

    public void GoBack(View v) {
        finish();
    }



}
