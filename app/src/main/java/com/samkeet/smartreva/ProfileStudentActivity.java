package com.samkeet.smartreva;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.crash.FirebaseCrash;
import com.google.firebase.iid.FirebaseInstanceId;

import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import dmax.dialog.SpotsDialog;

public class ProfileStudentActivity extends AppCompatActivity {

    public Button EditProfileStudent, LogoutStudent;

    public boolean isLogedIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_student);

        if (Constants.SharedPreferenceData.getIsLoggedIn().equals("yes")) {
            isLogedIn = true;
        } else {
            isLogedIn = false;
        }

        EditProfileStudent = (Button) findViewById(R.id.EditProfileStudent_button);
        LogoutStudent = (Button) findViewById(R.id.LogoutStudent_button);
        if (isLogedIn) {
            LogoutStudent.setText("Logout");
        } else {
            LogoutStudent.setText("Login");
        }
        LogoutStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isLogedIn) {
                    Toast.makeText(getApplicationContext(), "Loged Out", Toast.LENGTH_SHORT).show();
                    deleteToken deleteToken=new deleteToken();
                    deleteToken.execute();
                    Constants.SharedPreferenceData.clearData();
                    isLogedIn = false;
                    LogoutStudent.setText("Login");
                } else {
                    Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                    intent.putExtra("TYPE", "RETURN");
                    startActivity(intent);
                }
            }
        });
    }


    public void BackButton(View v) {
        finish();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (Constants.SharedPreferenceData.getIsLoggedIn().equals("yes")) {
            isLogedIn = true;
            LogoutStudent.setText("Logout");
        } else {
            isLogedIn = false;
            LogoutStudent.setText("Login");

        }
    }

    private class deleteToken extends AsyncTask<Void, Void, Integer> {


        protected void onPreExecute() {
        }

        protected Integer doInBackground(Void... params) {
            try {
                FirebaseInstanceId.getInstance().deleteInstanceId();
                FirebaseInstanceId.getInstance().getToken();
                return 1;

            } catch (Exception ex) {
                ex.printStackTrace();
                FirebaseCrash.report(new Exception(ex));
            }

            return 1;
        }

        protected void onPostExecute(Integer result) {

        }
    }

}
