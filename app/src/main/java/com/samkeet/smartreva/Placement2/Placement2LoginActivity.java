package com.samkeet.smartreva.Placement2;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.design.widget.TextInputLayout;
import android.support.v4.content.IntentCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
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

public class Placement2LoginActivity extends AppCompatActivity {

    public EditText srn, password;
    public Button login_button;
    public String ssrn, spassword;

    public SpotsDialog pd;
    public Context progressDialogContext;

    public TextInputLayout ip_srn;
    public TextInputLayout ip_password;


    public String token;
    public boolean authenticationError = true;
    public String errorMessage = "Data Corrupted";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_placement2_login);
        progressDialogContext = this;

        FirebaseMessaging.getInstance().subscribeToTopic("global");
        FirebaseInstanceId.getInstance().getToken();

        Constants.SharedPreferenceData.initSharedPreferenceData(getSharedPreferences(Constants.SharedPreferenceData.SHAREDPREFERENCES, MODE_PRIVATE));


        srn = (EditText) findViewById(R.id.srn);
        password = (EditText) findViewById(R.id.password);

        login_button = (Button) findViewById(R.id.login_button);

        ip_srn = (TextInputLayout) findViewById(R.id.input_layout_srn);
        ip_password = (TextInputLayout) findViewById(R.id.input_layout_password);

        srn.addTextChangedListener(new MyTextWatcher(srn));
        password.addTextChangedListener(new MyTextWatcher(password));

        if (Constants.SharedPreferenceData.getIsLoggedIn().equals("yes")) {
            if (Constants.SharedPreferenceData.getIsPlacement().equals("yes")) {
                Intent intent = new Intent(getApplicationContext(), Placement2MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | IntentCompat.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();
            }
        }
    }

    public void BackButton(View v) {
        finish();
    }

    public void register(View v) {
        Intent intent = new Intent(getApplicationContext(), PlacementRegistration1.class);
        startActivity(intent);
    }

    public void placementlogin(View v) {
        ssrn = srn.getText().toString().trim();
        spassword = password.getText().toString().trim();

        appLogin();

    }

    public void appLogin() {
        if (!validateSrn()) {
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

    private boolean validateSrn() {
        if (srn.getText().toString().trim().isEmpty()) {
            ip_srn.setError("Srn you entered is not valid");
            requestFocus(srn);
            return false;
        } else {
            ip_srn.setErrorEnabled(false);
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

    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
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
                    validateSrn();
                    break;

                case R.id.input_layout_password:
                    validatePassword();
                    break;
            }
        }
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
                URL url = new URL(Constants.URLs.PLACEMENT_BASE + Constants.URLs.LOGIN);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setDoInput(true);
                connection.setDoOutput(true);
                connection.setRequestMethod("POST");

                Uri.Builder _data = new Uri.Builder().appendQueryParameter("UserID", ssrn).appendQueryParameter("Password", spassword);
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
                Toast.makeText(getApplicationContext(), errorMessage, Toast.LENGTH_LONG).show();
            } else {

                Constants.SharedPreferenceData.setIsLoggedIn(token);
                Constants.SharedPreferenceData.setTOKEN(token);
                Constants.SharedPreferenceData.setUserId(ssrn);
                Constants.SharedPreferenceData.setIsPlacement("yes");

                if (Constants.FireBase.token != null) {
                    UpdateToken updateToken = new UpdateToken();
                    updateToken.execute();
                } else {
                    FirebaseInstanceId.getInstance().getToken();
                    Intent intent = new Intent(getApplicationContext(), Placement2MainActivity.class);
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
                java.net.URL url = new URL(Constants.URLs.PLACEMENT_BASE + Constants.URLs.FIREBASE);
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

                Intent intent = new Intent(getApplicationContext(), Placement2MainActivity.class);
                startActivity(intent);
                finish();
            }
        }
    }

    public void ForgetPassword(View v) {
        Toast.makeText(getApplicationContext(), "To Reset your Password, Please Contact Administrator", Toast.LENGTH_SHORT).show();
    }


}
