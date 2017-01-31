package com.samkeet.smartreva.Placement2;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.Toast;

import com.google.firebase.crash.FirebaseCrash;
import com.samkeet.smartreva.Constants;
import com.samkeet.smartreva.R;
import com.satsuware.usefulviews.LabelledSpinner;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import dmax.dialog.SpotsDialog;

public class PlacementRegistration2 extends AppCompatActivity {

    public TextInputLayout tBoard10, tBoard12, tSchoolName10, tSchoolName12, tPercentage10, tpercentage12, tYop10, tYop12;
    public EditText mBoard10, mBoard12, mSchoolName10, mSchoolName12, mPercentage10, mpercentage12, mYop10, mYop12;
    public String board10, board12, schoolname10, schoolname12, percentage10, percentage12, yop10, yop12;

    public LabelledSpinner courseSpin, branchSpin;
    public String[] courseList = {"btech", "mtech", "mba_mcom", "degree", "mca"};
    public String[] branchList = {"Branch"};
    public String scourse, sbranch;

    public Button Next;

    public Switch twelthSwitcher;
    public LinearLayout twelthLayout;

    public String object1;
    public JSONObject jsonObject = new JSONObject();

    public SpotsDialog pd;
    public Context progressDialogContext;
    public boolean authenticationError;
    public String errorMessage;

    public ArrayList<String> cList = new ArrayList<String>();
    public ArrayList<String> bList = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_placement2_registration2);
        progressDialogContext = this;

        object1 = getIntent().getStringExtra("OBJECT1");

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

        twelthLayout = (LinearLayout) findViewById(R.id.twelthlayout);
        twelthSwitcher = (Switch) findViewById(R.id.twelthSwitcher);
        twelthSwitcher.setChecked(true);
        twelthSwitcher.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    twelthLayout.setVisibility(View.VISIBLE);
                } else {
                    twelthLayout.setVisibility(View.GONE);
                }
            }
        });

        //spinner
        courseSpin = (LabelledSpinner) findViewById(R.id.course_spin);
        branchSpin = (LabelledSpinner) findViewById(R.id.branch_spin);

        if (Constants.Methods.networkState(getApplicationContext(), (ConnectivityManager) getSystemService(getApplicationContext().CONNECTIVITY_SERVICE))) {
            GetCourseDeptDetails getCourseDeptDetails = new GetCourseDeptDetails();
            getCourseDeptDetails.execute();
        }

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
        if (twelthSwitcher.isChecked()) {
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
            try {
                jsonObject.put("tenthb", board10);
                jsonObject.put("tenthsn", schoolname10);
                jsonObject.put("tenths", percentage10);
                jsonObject.put("tenthpy", yop10);
                jsonObject.put("twelthb", board12);
                jsonObject.put("twelthsn", schoolname12);
                jsonObject.put("twelths", percentage12);
                jsonObject.put("twelthpy", yop12);

            } catch (JSONException e) {
                e.printStackTrace();
            }
            Intent intent = new Intent(getApplicationContext(), PlacementRegistration3.class);
            intent.putExtra("OBJECT1", object1);
            intent.putExtra("OBJECT2", jsonObject.toString());
            intent.putExtra("DATA", scourse);
            startActivity(intent);
        } else {
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
            try {
                jsonObject.put("tenthb", board10);
                jsonObject.put("tenthsn", schoolname10);
                jsonObject.put("tenths", percentage10);
                jsonObject.put("tenthpy", yop10);
                jsonObject.put("twelthb", "NA");
                jsonObject.put("twelthsn", "NA");
                jsonObject.put("twelths", "NA");
                jsonObject.put("twelthpy", "NA");
                jsonObject.put("branch", sbranch);

            } catch (JSONException e) {
                e.printStackTrace();
            }
            Intent intent = new Intent(getApplicationContext(), PlacementRegistration3.class);
            intent.putExtra("OBJECT1", object1);
            intent.putExtra("OBJECT2", jsonObject.toString());
            intent.putExtra("DATA", scourse);
            startActivity(intent);
        }
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

    private class GetCourseDeptDetails extends AsyncTask<Void, Void, Integer> {

        protected void onPreExecute() {
            pd = new SpotsDialog(progressDialogContext, R.style.CustomPD);
            pd.setTitle("Loading...");
            pd.setCancelable(false);
            pd.show();
        }

        protected Integer doInBackground(Void... params) {
            try {
                java.net.URL url = new URL(Constants.URLs.PLACEMENT_BASE + Constants.URLs.PLACEMENT_COURSE_DEPT);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setDoInput(true);
                connection.setDoOutput(true);
                connection.setRequestMethod("POST");
                Uri.Builder _data = new Uri.Builder().appendQueryParameter("token", Constants.SharedPreferenceData.getTOKEN());
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(connection.getOutputStream(), "UTF-8"));
                writer.write(_data.build().getEncodedQuery());
                writer.flush();
                writer.close();

                InputStreamReader in = new InputStreamReader(connection.getInputStream());

                StringBuilder jsonResults = new StringBuilder();
                // Load the results into a StringBuilder
                int read;
                char[] buff = new char[1024];
                while ((read = in.read(buff)) != -1) {
                    jsonResults.append(buff, 0, read);
                }
                connection.disconnect();

                authenticationError = jsonResults.toString().contains("Authentication Error");

                if (authenticationError) {
                    errorMessage = jsonResults.toString();
                } else {
                    JSONArray jsonArray = new JSONArray(jsonResults.toString());
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        String coursetemp = jsonObject.getString("course");
                        String depttemp = jsonObject.getString("dept");
                        cList.add(coursetemp);
                        bList.add(depttemp);
                    }
                    authenticationError = false;
                }
                return 1;
            } catch (Exception ex) {
                ex.printStackTrace();
            }

            return 1;
        }

        protected void onPostExecute(Integer result) {
            if (pd != null) {
                pd.dismiss();
            }
            if (authenticationError) {
                Toast.makeText(getApplicationContext(), errorMessage, Toast.LENGTH_SHORT).show();
            } else {
                initCourseList();
            }

        }
    }

    public void initCourseList() {

        ArrayList<String> temp = new ArrayList<String>();
        for (int i = 0; i < cList.size(); i++) {
            boolean flag = false;
            for (int j = 0; j < temp.size(); j++) {
                if (temp.get(j).equals(cList.get(i))) {
                    flag = true;
                    break;
                }
            }
            if (!flag) {
                temp.add(cList.get(i));
            }
        }
        courseList = null;
        courseList = temp.toArray(new String[temp.size()]);
        courseSpin.setItemsArray(courseList);
        courseSpin.setOnItemChosenListener(new LabelledSpinner.OnItemChosenListener() {
            @Override
            public void onItemChosen(View labelledSpinner, AdapterView<?> adapterView, View itemView, int position, long id) {
                scourse = courseList[position];
                initDeptList();
            }

            @Override
            public void onNothingChosen(View labelledSpinner, AdapterView<?> adapterView) {
                scourse = courseList[0];
            }
        });
    }

    public void initDeptList() {

        ArrayList<String> temp = new ArrayList<String>();
        for (int i = 0; i < cList.size(); i++) {
            if (cList.get(i).equals(scourse)) {
                temp.add(bList.get(i));
            }
        }

        branchList = null;
        branchList = temp.toArray(new String[temp.size()]);

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
    }


}
