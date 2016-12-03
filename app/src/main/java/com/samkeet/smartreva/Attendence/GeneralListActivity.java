package com.samkeet.smartreva.Attendence;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import com.samkeet.smartreva.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class GeneralListActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private Context progressDialogContext;
    public ProgressDialog pd;

    public String results;
    public String type;
    public String titles[]=null;
    public String returnValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_general_list);
        progressDialogContext=this;
        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);



        results=getIntent().getStringExtra("RESULTS");
        type=getIntent().getStringExtra("TYPE");


        try {
            JSONArray jsonArray = new JSONArray(results);
            titles = new String[jsonArray.length()];
            for(int i=0;i<jsonArray.length();i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String value = jsonObject.getString(type);
                titles[i]=value;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }


        final GestureDetector mGestureDetector = new GestureDetector(getApplicationContext(), new GestureDetector.SimpleOnGestureListener() {

            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                return true;
            }

        });

        mRecyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent motionEvent) {

                View child = mRecyclerView.findChildViewUnder(motionEvent.getX(), motionEvent.getY());
                if (child != null && mGestureDetector.onTouchEvent(motionEvent)) {
                    int temp = mRecyclerView.getChildPosition(child);
                    returnValue=titles[temp];

                    Intent returnIntent = new Intent();
                    returnIntent.putExtra("returnValue", returnValue);
                    setResult(Activity.RESULT_OK, returnIntent);
                    finish();


                }
                return false;
            }

            @Override
            public void onTouchEvent(RecyclerView rv, MotionEvent e) {

            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

            }
        });


        mAdapter = new GeneralListAdapter(titles);
        mRecyclerView.setAdapter(mAdapter);

    }






}
