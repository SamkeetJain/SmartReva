package com.samkeet.smartreva.Placement;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.samkeet.smartreva.Constants;
import com.samkeet.smartreva.R;

import org.w3c.dom.Text;

public class PlacementRegForm2 extends AppCompatActivity {

    public Spinner passingyear[];
    public String course, semister, py[];
    public LinearLayout passingLayout[];
    public String passingYears[];
    public TextView courseTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_placement_reg_form2);

        course = getIntent().getStringExtra("COURSE");
        semister = getIntent().getStringExtra("SEMESTER");

        passingyear = new Spinner[10];
        py = new String[10];
        passingLayout=new LinearLayout[10];

        setFindViewById();

        courseTitle.setText(course.toUpperCase());

        passingYears=Constants.passingYears;
        for(int i=0;i<passingYears.length;i++){
            passingYears[i]=passingYears[i].toUpperCase();
        }

        initSpinner();

        int counter=Integer.valueOf(semister);
        for(int i=counter;i<10;i++){
            passingLayout[i].setVisibility(View.GONE);
        }




    }

    public void setFindViewById(){

        courseTitle = (TextView) findViewById(R.id.coursetitle);

        passingyear[0] = (Spinner) findViewById(R.id.spinner0);
        passingyear[1] = (Spinner) findViewById(R.id.spinner1);
        passingyear[2] = (Spinner) findViewById(R.id.spinner2);
        passingyear[3] = (Spinner) findViewById(R.id.spinner3);
        passingyear[4] = (Spinner) findViewById(R.id.spinner4);
        passingyear[5] = (Spinner) findViewById(R.id.spinner5);
        passingyear[6] = (Spinner) findViewById(R.id.spinner6);
        passingyear[7] = (Spinner) findViewById(R.id.spinner7);
        passingyear[8] = (Spinner) findViewById(R.id.spinner8);
        passingyear[9] = (Spinner) findViewById(R.id.spinner9);

        passingLayout[0] = (LinearLayout) findViewById(R.id.slab0);
        passingLayout[1] = (LinearLayout) findViewById(R.id.slab1);
        passingLayout[2] = (LinearLayout) findViewById(R.id.slab2);
        passingLayout[3] = (LinearLayout) findViewById(R.id.slab3);
        passingLayout[4] = (LinearLayout) findViewById(R.id.slab4);
        passingLayout[5] = (LinearLayout) findViewById(R.id.slab5);
        passingLayout[6] = (LinearLayout) findViewById(R.id.slab6);
        passingLayout[7] = (LinearLayout) findViewById(R.id.slab7);
        passingLayout[8] = (LinearLayout) findViewById(R.id.slab8);
        passingLayout[9] = (LinearLayout) findViewById(R.id.slab9);
    }

    public void initSpinner() {

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, passingYears);

        passingyear[0].setPrompt("Select one");

        passingyear[0].setAdapter(adapter);
        passingyear[0].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                py[0] = Constants.passingYears[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        passingyear[1].setAdapter(adapter);
        passingyear[1].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                py[1] = Constants.passingYears[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        passingyear[2].setAdapter(adapter);
        passingyear[2].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                py[2] = Constants.passingYears[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        passingyear[3].setAdapter(adapter);
        passingyear[3].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                py[3] = Constants.passingYears[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        passingyear[4].setAdapter(adapter);
        passingyear[4].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                py[4] = Constants.passingYears[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        passingyear[5].setAdapter(adapter);
        passingyear[5].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                py[5] = Constants.passingYears[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        passingyear[6].setAdapter(adapter);
        passingyear[6].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                py[6] = Constants.passingYears[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        passingyear[7].setAdapter(adapter);
        passingyear[7].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                py[7] = Constants.passingYears[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        passingyear[8].setAdapter(adapter);
        passingyear[8].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                py[8] = Constants.passingYears[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        passingyear[9].setAdapter(adapter);
        passingyear[9].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                py[9] = Constants.passingYears[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


    }

    public void BackButton(View v) {
        finish();
    }
    public void submit(View v) {

    }
}
