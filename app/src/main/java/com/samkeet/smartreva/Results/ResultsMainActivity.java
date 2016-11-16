package com.samkeet.smartreva.Results;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.samkeet.smartreva.R;

public class ResultsMainActivity extends AppCompatActivity {

    public Spinner semesterDrop,componetDrop;
    final String semesterItems[]=new String[]{"First", "Second", "Third", "Fourth", "Fifth", "Sixth", "Seventh", "Eigth"};
    final String componetsItems[]=new String[]{"C1","C2","C3"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results_main);

        semesterDrop = (Spinner) findViewById(R.id.spinner1);
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, semesterItems);
        semesterDrop.setAdapter(adapter1);
        semesterDrop.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
//                Toast.makeText(getApplicationContext(),semesterItems[position],Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        componetDrop = (Spinner) findViewById(R.id.spinner2);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, componetsItems);
        componetDrop.setAdapter(adapter2);
        componetDrop.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
//                Toast.makeText(getApplicationContext(),componetsItems[position],Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    public void BackButton(View v) {
        finish();
    }


}
