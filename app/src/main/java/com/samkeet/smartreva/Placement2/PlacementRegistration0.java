package com.samkeet.smartreva.Placement2;

import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import com.samkeet.smartreva.Constants;
import com.samkeet.smartreva.Placement.PlacementDriveManager;
import com.samkeet.smartreva.R;

import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import dmax.dialog.SpotsDialog;

public class PlacementRegistration0 extends AppCompatActivity {

    public EditText mSrn, mPassword, mCPassword;
    public TextInputLayout tSrn, tPassword, tCPassword;
    public String srn, password, cpassword;

    public SpotsDialog pd;
    public Context progressDialogContext;
    public boolean authenticationError = true;
    public String errorMessage = "Data corrupt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_placement_regitration0);
        progressDialogContext = this;

        tSrn = (TextInputLayout) findViewById(R.id.tsrn);
        tPassword = (TextInputLayout) findViewById(R.id.tpassword);
        tCPassword = (TextInputLayout) findViewById(R.id.tcpassword);

        mSrn = (EditText) findViewById(R.id.srn_et);
        mPassword = (EditText) findViewById(R.id.password_et);
        mCPassword = (EditText) findViewById(R.id.cpassword_et);
    }

    public void BackButton(View v) {
        finish();
    }

    public void Submit(View v) {
        srn = mSrn.getText().toString().trim();
        password = mPassword.getText().toString().trim();
        cpassword = mCPassword.getText().toString().trim();
        validation();
    }

    private void validation() {
        if (!validSrn()) {
            return;
        }
        if (!validPassword()) {
            return;
        }
        if (!validCpassword()) {
            return;
        }

        if (Constants.Methods.networkState(getApplicationContext(), (ConnectivityManager) getSystemService(getApplicationContext().CONNECTIVITY_SERVICE))) {
            Registration0 registration0 = new Registration0();
            registration0.execute();
        }
    }

    private boolean validSrn() {
        if (srn.isEmpty()) {
            tSrn.setError("Invalid srn");
            requestFocus(mSrn);
            return false;
        }
        if (Constants.Methods.checkForSpecial(srn)) {
            tSrn.setError("Remove Special characters");
            requestFocus(mSrn);
            return false;
        }
        if (srn.length() > 10) {
            tSrn.setError("Srn Should be less than 10 characters");
            requestFocus(mSrn);
            return false;
        } else {
            tSrn.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validPassword() {
        if (password.isEmpty()) {
            tPassword.setError("Invalid Password");
            requestFocus(mPassword);
            return false;
        }
        if (Constants.Methods.checkForSpecial(password)) {
            tSrn.setError("Remove Special characters");
            requestFocus(mPassword);
            return false;
        } else {
            tPassword.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validCpassword() {

        if (!cpassword.equals(password)) {
            tCPassword.setError("Password doesnt match");
            requestFocus(mCPassword);
            return false;
        } else {
            tCPassword.setErrorEnabled(false);
        }
        return true;
    }

    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }

    private class Registration0 extends AsyncTask<Void, Void, Integer> {

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
                java.net.URL url = new URL(Constants.URLs.PLACEMENT_BASE + Constants.URLs.PLACEMENT_REGISTRATION);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setDoInput(true);
                connection.setDoOutput(true);
                connection.setRequestMethod("POST");
                Uri.Builder _data = new Uri.Builder().appendQueryParameter("UserID", srn)
                        .appendQueryParameter("password", password);
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
                Log.d("return from server", jsonResults.toString());
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
                AlertDialog.Builder builder = new AlertDialog.Builder(PlacementRegistration0.this);
                builder.setTitle("Oops!!! We cannot process your request at this time");
                builder.setMessage("Response: " + "\n" + errorMessage);
                String positiveText = "Retry";
                builder.setPositiveButton(positiveText,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                String negativeText = "Cancel";
                builder.setNegativeButton(negativeText,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                finish();
                            }
                        });

                AlertDialog dialog = builder.create();
                dialog.show();
            } else {
                AlertDialog.Builder builder = new AlertDialog.Builder(PlacementRegistration0.this);
                builder.setTitle("Registration Successful");
                builder.setMessage("Your registration request is received, Please login with your account and completed the Registration Form.");
                String positiveText = "Login now";
                builder.setPositiveButton(positiveText,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                finish();
                            }
                        });

                builder.setOnCancelListener(new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialogInterface) {
                        finish();
                    }
                });

                AlertDialog dialog = builder.create();
                // display dialog
                dialog.show();
            }

        }
    }

}
