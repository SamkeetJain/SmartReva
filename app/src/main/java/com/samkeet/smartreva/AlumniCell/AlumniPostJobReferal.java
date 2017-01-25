package com.samkeet.smartreva.AlumniCell;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.samkeet.smartreva.Constants;
import com.samkeet.smartreva.R;
import com.satsuware.usefulviews.LabelledSpinner;

import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import dmax.dialog.SpotsDialog;

public class AlumniPostJobReferal extends AppCompatActivity {

    public LabelledSpinner mJobType;
    public EditText mCompany, mRole, mDesc;
    public String company, role, desc, jobType;
    public String[] types = {"Internship", "Full-Time Job"};

    public TextInputLayout ip_company;
    public TextInputLayout ip_role;

    public SpotsDialog pd;
    public Context progressDialogContext;

    public ImageView submit;

    public boolean authenticationError = true;
    public String errorMessage = "Data Corrupted";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alumni_post_job_referal);

        progressDialogContext = this;

        mJobType = (LabelledSpinner) findViewById(R.id.type);
        mCompany = (EditText) findViewById(R.id.company);
        mRole = (EditText) findViewById(R.id.role);
        mDesc = (EditText) findViewById(R.id.desc);

        ip_company = (TextInputLayout) findViewById(R.id.input_layout_company);
        ip_role = (TextInputLayout) findViewById(R.id.input_layout_role);

        mJobType.setItemsArray(types);
        mJobType.setOnItemChosenListener(new LabelledSpinner.OnItemChosenListener() {
            @Override
            public void onItemChosen(View labelledSpinner, AdapterView<?> adapterView, View itemView, int position, long id) {
                jobType = types[position];
            }

            @Override
            public void onNothingChosen(View labelledSpinner, AdapterView<?> adapterView) {
                jobType = types[1];
            }
        });

        submit = (ImageView) findViewById(R.id.send_button);

        submit.setOnClickListener(new View.OnClickListener() {

                                      @Override
                                      public void onClick(View v) {
                                          appLogin();
                                      }
                                  }
        );

    }

    public void appLogin() {
        if (!validateCompany()) {
            return;
        }
        if (!validateRole()) {
            return;
        }
        if (!validateDesc()) {
            return;
        }
        company = mCompany.getText().toString();
        role = mRole.getText().toString();
        desc = mDesc.getText().toString();

        NewPost newPost = new NewPost();
        newPost.execute();
    }

    private boolean validateCompany() {

        if (company.isEmpty()) {
            ip_company.setError("Company Name is not valid");
            requestFocus(mCompany);
            return false;
        }
        if (company.length() > 33) {
            ip_company.setError("Company Name should be less than 32 charecters");
            requestFocus(mCompany);
            return false;
        }if (Constants.Methods.checkForSpecial(company)){
            ip_company.setError("Remove Special charecters");
            requestFocus(mCompany);
            return false;
        }
        else {
            ip_company.setErrorEnabled(false);
        }
        return true;

    }

    private boolean validateRole() {
        if (role.isEmpty()) {
            ip_role.setError("Role is not valid");
            requestFocus(mRole);
            return false;
        }
        if (role.length() > 33) {
            ip_role.setError("Role should be less than 32 charesters");
            requestFocus(mRole);
            return false;

        }if (Constants.Methods.checkForSpecial(role)){
            ip_role.setError("Remove Special charecters");
            requestFocus(mRole);
            return false;
        }
        else {
            ip_role.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validateDesc() {
        if (desc.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Decription cant be left empty", Toast.LENGTH_LONG).show();
            return false;
        }
        if (desc.length() < 1000) {
            Toast.makeText(getApplicationContext(), "Description must be less than 1000 charesters", Toast.LENGTH_LONG).show();
            return false;
        }if (Constants.Methods.checkForSpecial(desc)){
            Toast.makeText(getApplicationContext(),"Remove Special charecter",Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }

    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }

    public void BackButton(View v) {
        finish();
    }


    private class NewPost extends AsyncTask<Void, Void, Integer> {

        protected void onPreExecute() {
            pd = new SpotsDialog(progressDialogContext, R.style.CustomPD);
            pd.setTitle("Loading...");
            pd.setCancelable(false);
            pd.show();
        }

        protected Integer doInBackground(Void... params) {
            try {
                java.net.URL url = new URL(Constants.URLs.ALUMNI_BASE + Constants.URLs.ALUMNI_REFER_JOB);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setDoInput(true);
                connection.setDoOutput(true);
                connection.setRequestMethod("POST");

                Uri.Builder _data = new Uri.Builder()
                        .appendQueryParameter("token", Constants.SharedPreferenceData.getTOKEN())
                        .appendQueryParameter("type", "new")
                        .appendQueryParameter("company", company)
                        .appendQueryParameter("role", role)
                        .appendQueryParameter("jobType", jobType)
                        .appendQueryParameter("desc", desc);

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
                finish();
            } else {
                Toast.makeText(getApplicationContext(), "Your Referal Request is Recieved", Toast.LENGTH_SHORT).show();
                finish();
            }

        }
    }

}
