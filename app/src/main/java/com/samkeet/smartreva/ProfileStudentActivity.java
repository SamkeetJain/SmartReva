package com.samkeet.smartreva;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class ProfileStudentActivity extends AppCompatActivity {

    public Button EditProfileStudent, LogoutStudent;

    public boolean isLogedIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_student);

        if(Constants.SharedPreferenceData.getIsLoggedIn().equals("yes")){
            isLogedIn=true;
        }else {
            isLogedIn=false;
        }

        EditProfileStudent = (Button) findViewById(R.id.EditProfileStudent_button);
        LogoutStudent = (Button) findViewById(R.id.LogoutStudent_button);
        if(isLogedIn) {
            LogoutStudent.setText("Logout");
        }else {
            LogoutStudent.setText("Login");
        }
        LogoutStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isLogedIn){
                    Constants.UserData.USER_ID=null;
                    Constants.UserData.TOKEN=null;
                    Constants.SharedPreferenceData.clearData();
                    Toast.makeText(getApplicationContext(),"Loged Out",Toast.LENGTH_SHORT).show();
                    isLogedIn=false;
                    LogoutStudent.setText("Login");
                }
                else {
                    Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
                    intent.putExtra("TYPE","RETURN");
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
        if(Constants.SharedPreferenceData.getIsLoggedIn().equals("yes")){
            isLogedIn=true;
            LogoutStudent.setText("Logout");
        }else {
            isLogedIn=false;
            LogoutStudent.setText("Login");

        }
    }
}
