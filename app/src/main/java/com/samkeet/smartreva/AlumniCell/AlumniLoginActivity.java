package com.samkeet.smartreva.AlumniCell;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.design.widget.TextInputLayout;
import android.support.v4.content.IntentCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessaging;
import com.samkeet.smartreva.Constants;
import com.samkeet.smartreva.R;

import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import dmax.dialog.SpotsDialog;

public class AlumniLoginActivity extends AppCompatActivity {

    public EditText mobileno, password;
    public Button login_button;
    public String smobileno, spassword;

    public TextInputLayout ip_mobileno;
    public TextInputLayout ip_password;

    public SpotsDialog pd;
    public Context progressDialogContext;

    public String token;
    public boolean authenticationError = true;
    public String errorMessage = "Data Corrupted";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alumini_login);

        FirebaseMessaging.getInstance().subscribeToTopic("global");
        FirebaseInstanceId.getInstance().getToken();

        Constants.SharedPreferenceData.initSharedPreferenceData(getSharedPreferences(Constants.SharedPreferenceData.SHAREDPREFERENCES, MODE_PRIVATE));

        progressDialogContext = this;
        mobileno = (EditText) findViewById(R.id.mobileno);
        password = (EditText) findViewById(R.id.password);
        login_button = (Button) findViewById(R.id.login_button);

        ip_mobileno = (TextInputLayout) findViewById(R.id.input_layout_mobilemo);
        ip_password = (TextInputLayout) findViewById(R.id.input_layout_password);

        mobileno.addTextChangedListener(new MyTextWatcher(mobileno));
        password.addTextChangedListener(new MyTextWatcher(password));


        if (Constants.SharedPreferenceData.getIsLoggedIn().equals("yes")) {
            if (Constants.SharedPreferenceData.getIsAlumni().equals("yes")) {
                Intent intent = new Intent(getApplicationContext(), AlumniMainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | IntentCompat.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();
            }
        }

        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                smobileno = mobileno.getText().toString().trim();
                spassword = password.getText().toString().trim();

                appLogin();

            }
        });

    }

    public void appLogin() {
        if (!validateMobilenumber()) {
            return;
        }

        if (!validatePassword()) {
            return;
        }


        if (Constants.Methods.networkState(getApplicationContext(), (ConnectivityManager) getSystemService(getApplicationContext().CONNECTIVITY_SERVICE))) {
            Login login = new Login();
            login.execute();
        }
    }

    private boolean validateMobilenumber() {
        String mobile = mobileno.getText().toString().trim();

        if (mobile.isEmpty() || !isValidMobilenumber(mobile) || mobile.length() > 15) {
            ip_mobileno.setError("Invalid Phone Number");
            requestFocus(mobileno);
            return false;
        } else {
            ip_mobileno.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validatePassword() {
        if (password.getText().toString().trim().isEmpty()) {
            ip_password.setError("Password you entered is not valid");
            requestFocus(password);
            return false;
        } else {
            ip_password.setErrorEnabled(false);
        }
        return true;
    }

    private static boolean isValidMobilenumber(String email) {
        return !TextUtils.isEmpty(email) && Patterns.PHONE.matcher(email).matches();
    }

    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }


    public void register(View v) {
        AlertDialog.Builder builder = new AlertDialog.Builder(AlumniLoginActivity.this);
        builder.setTitle("Valid Registration only");
        builder.setMessage("Registration is allowed only if you are a graduate of REVA University or REVA Group of Educational Institutions, Invalid registration will be rejected. As you are part of our global alumni community, we encourage you to stay in touch with us, so that we can provide you with support and benefits for life.");
        String positiveText = "Agree";
        builder.setPositiveButton(positiveText,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // positive button logic
                        Intent intent = new Intent(getApplicationContext(), AlumniRegistrationActivity.class);
                        startActivity(intent);
                    }
                });
        String negativeText = "Disagree";
        builder.setNegativeButton(negativeText,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // negative button logic
                    }
                });

        AlertDialog dialog = builder.create();
        // display dialog
        dialog.show();

    }

    public void BackButton(View v) {
        finish();
    }

    private class Login extends AsyncTask<Void, Void, Integer> {


        protected void onPreExecute() {
            pd = new SpotsDialog(progressDialogContext, R.style.CustomPD);
            pd.setTitle("Loading...");
            pd.setCancelable(false);
            pd.show();
        }

        protected Integer doInBackground(Void... params) {
            try {
                URL url = new URL(Constants.URLs.ALUMNI_BASE + Constants.URLs.ALUMNI_LOGIN);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setDoInput(true);
                connection.setDoOutput(true);
                connection.setRequestMethod("POST");

                Uri.Builder _data = new Uri.Builder().appendQueryParameter("UserID", smobileno).appendQueryParameter("Password", spassword);
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
                    // Create a JSON object hierarchy from the results
                    JSONObject jsonObj = new JSONObject(jsonResults.toString());
                    String status = jsonObj.getString("status");
                    if (status.equals("success")) {
                        token = jsonObj.getString("token");
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
                AlertDialog.Builder builder = new AlertDialog.Builder(AlumniLoginActivity.this);
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
                // display dialog
                dialog.show();
            } else {

                Constants.SharedPreferenceData.setIsLoggedIn(token);
                Constants.SharedPreferenceData.setTOKEN(token);
                Constants.SharedPreferenceData.setUserId(smobileno);
                Constants.SharedPreferenceData.setIsAlumni("yes");

                if (Constants.FireBase.token != null) {
                    UpdateToken updateToken = new UpdateToken();
                    updateToken.execute();
                } else {
                    FirebaseInstanceId.getInstance().getToken();
                    Intent intent = new Intent(getApplicationContext(), AlumniMainActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | IntentCompat.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    finish();
                }
            }
        }
    }

    private class UpdateToken extends AsyncTask<Void, Void, Integer> {


        protected void onPreExecute() {
            pd = new SpotsDialog(progressDialogContext, R.style.CustomPD);
            pd.setTitle("Loading...");
            pd.setCancelable(false);
            pd.show();
        }

        protected Integer doInBackground(Void... params) {
            try {
                URL url = new URL(Constants.URLs.ALUMNI_BASE + Constants.URLs.FIREBASE);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setDoInput(true);
                connection.setDoOutput(true);
                connection.setRequestMethod("POST");

                Uri.Builder _data = new Uri.Builder().appendQueryParameter("token", Constants.SharedPreferenceData.getTOKEN()).appendQueryParameter("firebasetoken", Constants.FireBase.token);
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
                    // Create a JSON object hierarchy from the results
                    JSONObject jsonObj = new JSONObject(jsonResults.toString());
                    String status = jsonObj.getString("status");
                    if (status.equals("success")) {

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

                Intent intent = new Intent(getApplicationContext(), AlumniMainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | IntentCompat.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();

            }

        }
    }

    public void ForgetPassword(View v) {
        Toast.makeText(getApplicationContext(), "To Reset your Password, Please Contact Administrator", Toast.LENGTH_SHORT).show();
    }

    public class MyTextWatcher implements TextWatcher {

        private View view;

        private MyTextWatcher(View view) {
            this.view = view;
        }

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        public void afterTextChanged(Editable editable) {
            switch (view.getId()) {
                case R.id.input_layout_usn:
                    validateMobilenumber();
                    break;

                case R.id.input_layout_password:
                    validatePassword();
                    break;
            }
        }
    }


}

