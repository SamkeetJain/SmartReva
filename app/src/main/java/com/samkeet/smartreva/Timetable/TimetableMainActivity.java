
package com.samkeet.smartreva.Timetable;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.samkeet.smartreva.R;

public class TimetableMainActivity extends AppCompatActivity {

    public Spinner departmentDrop,semesterDrop,classDrop,dayDrop;
    final String semesterItems[] = new String[]{"First", "Second", "Third", "Fourth", "Fifth", "Sixth", "Seventh", "Eigth"};
    final String departmentsItems[] = new String[]{"CSE", "CVS", "ECE","EEE","MC"};
    final String classItems[] = new String[]{"A", "B", "C","D","E"};
    final String dayItems[] = new String[]{"Monday", "Tuesday", "Wednesday","Thusday","Friday","Saturday"};



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timetable_main);
    }



    public void BackButton(View v){
        finish();
    }
}
