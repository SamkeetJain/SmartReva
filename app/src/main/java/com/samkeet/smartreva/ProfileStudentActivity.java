package com.samkeet.smartreva;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v4.content.IntentCompat;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_student);

        EditProfileStudent = (Button) findViewById(R.id.EditProfileStudent_button);
        LogoutStudent = (Button) findViewById(R.id.LogoutStudent_button);
        LogoutStudent.setText("Logout");
        LogoutStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logout();
            }
        });
    }

    public void logout(){
        deleteToken deleteToken=new deleteToken();
        deleteToken.execute();
        Constants.SharedPreferenceData.clearData();
        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | IntentCompat.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }

    public void BackButton(View v) {
        finish();
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
