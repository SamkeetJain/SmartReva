package com.samkeet.smartreva.Placement2;

import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.crash.FirebaseCrash;
import com.samkeet.smartreva.Constants;
import com.samkeet.smartreva.R;
import com.satsuware.usefulviews.LabelledSpinner;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class PlacementRegistration2 extends AppCompatActivity {

    public TextInputLayout tBoard10, tBoard12, tSchoolName10, tSchoolName12, tPercentage10, tpercentage12, tYop10, tYop12;
    public EditText mBoard10, mBoard12, mSchoolName10, mSchoolName12, mPercentage10, mpercentage12, mYop10, mYop12;
    public String board10, board12, schoolname10, schoolname12, percentage10, percentage12, yop10, yop12;

    public LabelledSpinner courseSpin, branchSpin;
    public String[] courseList = {"B Tech.", "M Tech.", "M.B.A./M Com.", "Degree", "M.C.A."};
    public String[] branchList = {"Branch"};
    public String scourse, sbranch;

    public Button Next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_placement2_registration2);

        //Text input layout
        tBoard10 = (TextInputLayout) findViewById(R.id.board_10);
        tBoard12 = (TextInputLayout) findViewById(R.id.board_12);
        tPercentage10 = (TextInputLayout) findViewById(R.id.percentage_10);
        tpercentage12 = (TextInputLayout) findViewById(R.id.percentage_12);
        tSchoolName10 = (TextInputLayout) findViewById(R.id.school_name_10);
        tSchoolName12 = (TextInputLayout) findViewById(R.id.school_name_12);
        tYop10 = (TextInputLayout) findViewById(R.id.yop_10);
        tYop12 = (TextInputLayout) findViewById(R.id.yop_12);

        //edit text
        mBoard10 = (EditText) findViewById(R.id.board10_et);
        mBoard12 = (EditText) findViewById(R.id.board_12_et);
        mPercentage10 = (EditText) findViewById(R.id.percentage_10_et);
        mpercentage12 = (EditText) findViewById(R.id.percentage_12_et);
        mSchoolName10 = (EditText) findViewById(R.id.school_name_10_et);
        mSchoolName12 = (EditText) findViewById(R.id.school_name_12_et);
        mYop10 = (EditText) findViewById(R.id.yop_10_et);
        mYop12 = (EditText) findViewById(R.id.yop_12_et);

        Next = (Button) findViewById(R.id.next_button);

        //spinner
        courseSpin = (LabelledSpinner) findViewById(R.id.course_spin);
        branchSpin = (LabelledSpinner) findViewById(R.id.branch_spin);

        courseSpin.setItemsArray(courseList);
        courseSpin.setOnItemChosenListener(new LabelledSpinner.OnItemChosenListener() {
            @Override
            public void onItemChosen(View labelledSpinner, AdapterView<?> adapterView, View itemView, int position, long id) {
                scourse = courseList[position];
            }

            @Override
            public void onNothingChosen(View labelledSpinner, AdapterView<?> adapterView) {
                scourse = courseList[0];
            }
        });
        branchSpin.setItemsArray(branchList);
        branchSpin.setOnItemChosenListener(new LabelledSpinner.OnItemChosenListener() {
            @Override
            public void onItemChosen(View labelledSpinner, AdapterView<?> adapterView, View itemView, int position, long id) {
                sbranch = branchList[position];
            }

            @Override
            public void onNothingChosen(View labelledSpinner, AdapterView<?> adapterView) {
                sbranch = branchList[0];
            }
        });


        Next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                board10 = mBoard10.getText().toString().trim();
                board12 = mBoard12.getText().toString().trim();
                percentage10 = mPercentage10.getText().toString().trim();
                percentage12 = mpercentage12.getText().toString().trim();
                yop10 = mYop10.getText().toString().trim();
                yop12 = mYop12.getText().toString().trim();
                schoolname10 = mSchoolName10.getText().toString().trim();
                schoolname12 = mSchoolName12.getText().toString().trim();

                validation();
            }
        });
    }

    public void validation() {
        if (!validBoard10()) {
            return;
        }
        if (!validSchoolname10()) {
            return;
        }
        if (!validPercentage10()) {
            return;
        }
        if (!validYop10()) {
            return;
        }
        if (!validBoard12()) {
            return;
        }
        if (!validSchoolname12()) {
            return;
        }
        if (!validPercentage12()) {
            return;
        }
        if (!validYop12()) {
            return;
        }
        Intent intent = new Intent(getApplicationContext(), PlacementRegistration3.class);
        intent.putExtra("DATA", scourse);
        startActivity(intent);
    }

    public void BackButton(View v) {
        finish();
    }

    private boolean validBoard10() {
        if (board10.isEmpty()) {
            tBoard10.setError("Invalid Board");
            requestFocus(mBoard10);
            return false;
        }
        if (Constants.Methods.checkForSpecial(board10)) {
            tBoard10.setError("Remove Special characters");
            requestFocus(mBoard10);
            return false;
        }
        if (board10.length() > 32) {
            tBoard10.setError("Board Should be less than 32 characters");
            requestFocus(mBoard10);
            return false;
        } else {
            tBoard10.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validSchoolname10() {
        if (schoolname10.isEmpty()) {
            tSchoolName10.setError("Invalid School Name ");
            requestFocus(mSchoolName10);
            return false;
        }
        if (Constants.Methods.checkForSpecial(schoolname10)) {
            tSchoolName10.setError("Remove Special characters");
            requestFocus(mSchoolName10);
            return false;
        }
        if (schoolname10.length() > 128) {
            tSchoolName10.setError("School Name Should be less than 128 characters");
            requestFocus(mSchoolName10);
            return false;
        } else {
            tSchoolName10.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validPercentage10() {
        if (percentage10.isEmpty() || percentage10.length() < 2) {
            tPercentage10.setError("Invalid Percentage");
            requestFocus(mPercentage10);
            return false;
        }
        if (Constants.Methods.checkForSpecial(percentage10)) {
            tPercentage10.setError("Remove Special characters");
            requestFocus(mPercentage10);
            return false;
        }
        if (percentage10.length() > 5) {
            tPercentage10.setError("Percentage Should be less than 5 characters");
            requestFocus(mPercentage10);
            return false;
        } else {
            tPercentage10.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validYop10() {
        if (yop10.isEmpty()) {
            tYop10.setError("Invalid YOP");
            requestFocus(mYop10);
            return false;
        }
        if (Constants.Methods.checkForSpecial(yop10)) {
            tYop10.setError("Remove Special characters");
            requestFocus(mYop10);
            return false;
        }
        if (!(yop10.length() == 4)) {
            tYop10.setError(" Should be of 4 characters");
            requestFocus(mYop10);
            return false;
        } else {
            tYop10.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validBoard12() {
        if (board12.isEmpty()) {
            tBoard12.setError("Invalid Board");
            requestFocus(mBoard12);
            return false;
        }
        if (Constants.Methods.checkForSpecial(board12)) {
            tBoard12.setError("Remove Special characters");
            requestFocus(mBoard12);
            return false;
        }
        if (board12.length() > 32) {
            tBoard12.setError("Board Should be less than 32 characters");
            requestFocus(mBoard12);
            return false;
        } else {
            tBoard12.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validSchoolname12() {
        if (schoolname12.isEmpty()) {
            tSchoolName12.setError("Invalid School Name ");
            requestFocus(mSchoolName12);
            return false;
        }
        if (Constants.Methods.checkForSpecial(schoolname12)) {
            tSchoolName12.setError("Remove Special characters");
            requestFocus(mSchoolName12);
            return false;
        }
        if (schoolname12.length() > 128) {
            tSchoolName12.setError("School Name Should be less than 128 characters");
            requestFocus(mSchoolName12);
            return false;
        } else {
            tSchoolName12.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validPercentage12() {
        if (percentage12.isEmpty() || percentage12.length() < 2) {
            tpercentage12.setError("Invalid Percentage");
            requestFocus(mpercentage12);
            return false;
        }
        if (Constants.Methods.checkForSpecial(percentage12)) {
            tpercentage12.setError("Remove Special characters");
            requestFocus(mpercentage12);
            return false;
        }
        if (percentage12.length() > 5) {
            tpercentage12.setError("Percentage Should be less than 5 characters");
            requestFocus(mpercentage12);
            return false;
        } else {
            tpercentage12.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validYop12() {
        if (yop12.isEmpty()) {
            tYop12.setError("Invalid YOP");
            requestFocus(mYop12);
            return false;
        }
        if (Constants.Methods.checkForSpecial(yop12)) {
            tYop12.setError("Remove Special characters");
            requestFocus(mYop12);
            return false;
        }
        if (!(yop12.length() == 4)) {
            tYop12.setError(" Should be of 4  characters");
            requestFocus(mYop12);
            return false;
        } else {
            tYop12.setErrorEnabled(false);
        }
        return true;
    }

    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }

}
