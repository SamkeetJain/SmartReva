package com.samkeet.smartreva;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class LoginActivity extends AppCompatActivity {

    public EditText usn,password;
    public Button login_button;
    public String susn,spassword;

    public ProgressDialog pd;
    public Context progressDialogContext;

    public String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        progressDialogContext=this;
        usn=(EditText)findViewById(R.id.usn);
        password=(EditText)findViewById(R.id.password);
        login_button= (Button) findViewById(R.id.login_button);

        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                susn=usn.getText().toString();
                spassword=password.getText().toString();

                Login login=new Login();
                login.execute();

//                Intent intent =new Intent(getApplicationContext(),MainActivity.class);
//                startActivity(intent);
            }
        });
    }

    private class Login extends AsyncTask<Void, Void, Integer> {



        protected void onPreExecute() {
            pd =new ProgressDialog(progressDialogContext);
            pd.setTitle("Logging...");
            pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            pd.setMessage("Please wait.");
            pd.setCancelable(false);
            pd.setIndeterminate(true);
            pd.show();
        }

        protected Integer doInBackground(Void... params) {
            try {
                URL url = new URL(Constants.URLs.LOGIN);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setDoInput(true);
                connection.setDoOutput(true);
                connection.setRequestMethod("POST");
                Log.d("POST", "DATA ready to sent");

                Uri.Builder _data = new Uri.Builder().appendQueryParameter("usn",susn ).appendQueryParameter("password",spassword );
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(connection.getOutputStream(), "UTF-8"));
                writer.write(_data.build().getEncodedQuery());
                writer.flush();
                writer.close();
                Log.d("POST", "DATA SENT");

                InputStreamReader in = new InputStreamReader(connection.getInputStream());

                StringBuilder jsonResults = new StringBuilder();
                // Load the results into a StringBuilder
                int read;
                char[] buff = new char[1024];
                while ((read = in.read(buff)) != -1) {
                    jsonResults.append(buff, 0, read);
                }
                connection.disconnect();
                Log.d("return from server",jsonResults.toString());

                // Create a JSON object hierarchy from the results
                JSONObject jsonObj = new JSONObject(jsonResults.toString());
                token=jsonObj.getString("token");
                return 1;

            } catch (Exception ex) {
                ex.printStackTrace();
            }

            return 1;
        }
        protected void onPostExecute(Integer result) {
            if (pd!=null) {
                pd.dismiss();
            }
            if(token.equals("false")){
                Toast.makeText(getApplicationContext(),"Login Failed",Toast.LENGTH_SHORT).show();
            }else {

            }
        }
    }





}
