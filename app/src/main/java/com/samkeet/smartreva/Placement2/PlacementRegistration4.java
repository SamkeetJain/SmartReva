package com.samkeet.smartreva.Placement2;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.text.InputType;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.samkeet.smartreva.Constants;
import com.samkeet.smartreva.R;

import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import dmax.dialog.SpotsDialog;

public class PlacementRegistration4 extends AppCompatActivity {

    public int count;
    public LinearLayout mainLayout;
    public EditText[] mFailedSemester, mSubjectName;
    public Switch[] mCleared;

    public SpotsDialog pd;
    public Context progressDialogContext;
    public boolean authenticationError;
    public String errorMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        progressDialogContext = this;
        setContentView(R.layout.activity_placement_registration4);
        mainLayout = (LinearLayout) findViewById(R.id.mainview);

        String temp = getIntent().getStringExtra("DATA");
        count = Integer.valueOf(temp);

        if (count > 0) {

            mFailedSemester = new EditText[count];
            mSubjectName = new EditText[count];
            mCleared = new Switch[count];

            for (int i = 0; i < count; i++) {

                LinearLayout componentView = new LinearLayout(this);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                params.setMargins(0, 0, 0, marginInDP(20));
                componentView.setLayoutParams(params);
                componentView.setBackgroundColor(Color.WHITE);
                componentView.setOrientation(LinearLayout.VERTICAL);

                TextView backlogTitle = new TextView(this);
                backlogTitle.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
                backlogTitle.setPadding(marginInDP(15), marginInDP(5), marginInDP(5), marginInDP(5));
                backlogTitle.setTextSize(18);
                String title = "Backlog " + (i + 1);
                backlogTitle.setText(title);

                View view = new View(this);
                view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, (marginInDP(1) / 2)));
                view.setBackgroundColor(Color.parseColor("#e0e0e0"));

                LinearLayout componentHolder = new LinearLayout(this);
                componentHolder.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                componentHolder.setOrientation(LinearLayout.VERTICAL);

                LinearLayout component1 = new LinearLayout(this);
                component1.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                component1.setGravity(Gravity.CENTER);
                component1.setOrientation(LinearLayout.HORIZONTAL);

                TextView failedSemester = new TextView(this);
                failedSemester.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT, 1));
                failedSemester.setGravity(Gravity.CENTER);
                failedSemester.setText("Failed semester");
                failedSemester.setTextSize(16);

                mFailedSemester[i] = new EditText(this);
                mFailedSemester[i].setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT, 1));
                mFailedSemester[i].setInputType(InputType.TYPE_CLASS_NUMBER);
                mFailedSemester[i].setTextSize(16);
                mFailedSemester[i].setGravity(Gravity.CENTER);
                mFailedSemester[i].setMaxLines(1);
                mFailedSemester[i].setSingleLine(true);
                mFailedSemester[i].setImeOptions(EditorInfo.IME_ACTION_NEXT);

                component1.addView(failedSemester);
                component1.addView(mFailedSemester[i]);

                LinearLayout component2 = new LinearLayout(this);
                component2.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                component2.setGravity(Gravity.CENTER);
                component2.setOrientation(LinearLayout.HORIZONTAL);

                TextView subjectName = new TextView(this);
                subjectName.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT, 1));
                subjectName.setGravity(Gravity.CENTER);
                subjectName.setText("Subject name");
                subjectName.setTextSize(16);

                mSubjectName[i] = new EditText(this);
                mSubjectName[i].setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT, 1));
                mSubjectName[i].setTextSize(16);
                mSubjectName[i].setGravity(Gravity.CENTER);
                mSubjectName[i].setMaxLines(1);
                mSubjectName[i].setSingleLine(true);
                mSubjectName[i].setInputType(InputType.TYPE_CLASS_TEXT);
                mSubjectName[i].setImeOptions(EditorInfo.IME_ACTION_NEXT);

                component2.addView(subjectName);
                component2.addView(mSubjectName[i]);

                LinearLayout component3 = new LinearLayout(this);
                component3.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                component3.setOrientation(LinearLayout.HORIZONTAL);

                TextView cleared = new TextView(this);
                cleared.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));
                cleared.setPadding(marginInDP(55), 0, 0, 0);
                cleared.setText("Cleared ?");
                cleared.setTextSize(16);

                mCleared[i] = new Switch(this);
                mCleared[i].setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                mCleared[i].setGravity(Gravity.RIGHT);
                mCleared[i].setPadding(0, 0, marginInDP(55), 0);

                component3.addView(cleared);
                component3.addView(mCleared[i]);

                componentHolder.addView(component1);
                componentHolder.addView(component2);
                componentHolder.addView(component3);

                componentView.addView(backlogTitle);
                componentView.addView(view);
                componentView.addView(componentHolder);

                mainLayout.addView(componentView);
            }

        } else {
            Intent intent = new Intent(getApplicationContext(), Placement2MainActivity.class);
            startActivity(intent);
        }
    }

    public int marginInDP(int n) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, n, getResources().getDisplayMetrics());
    }

    public void BackButton(View v) {
        finish();
    }

    public void Submit(View v) {
        for (int i = 0; i < count; i++) {
            String sub_name = mSubjectName[i].getText().toString().trim();
            String sub_sem = mFailedSemester[i].getText().toString().trim();
            String check;
            if (mCleared[i].isChecked()) {
                check = "YES";
            } else {
                check = "NO";
            }
            String data[] = new String[3];
            data[0] = sub_sem;
            data[1] = sub_name;
            data[2] = check;


        }

    }

    private class BacklogReg extends AsyncTask<Void, Void, Integer> {

        protected void onPreExecute() {
            authenticationError = true;
            errorMessage = "Data Courpted";
            pd = new SpotsDialog(progressDialogContext, R.style.CustomPD);
            pd.setTitle("Loading...");
            pd.setCancelable(false);
            pd.show();
        }

        protected Integer doInBackground(Void... params) {
            try {
                java.net.URL url = new URL(Constants.URLs.PLACEMENT_BASE + Constants.URLs.PLACEMENT_BACKLOG);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setDoInput(true);
                connection.setDoOutput(true);
                connection.setRequestMethod("POST");
                Uri.Builder _data = new Uri.Builder().appendQueryParameter("token", Constants.SharedPreferenceData.getTOKEN())
                        .appendQueryParameter("requestType", "put").appendQueryParameter("sub_name", "dfsd")
                        .appendQueryParameter("sub_sem", "sdfd").appendQueryParameter("cleared", "sdfnd");
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
                    JSONObject jsonObj = new JSONObject(jsonResults.toString());
                    String status = jsonObj.getString("status");
                    if (status.equals("success")) {
                        authenticationError = false;
                        errorMessage = status;
                    } else {
                        authenticationError = true;
                        errorMessage = status;
                    }
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
            }

        }
    }

}
