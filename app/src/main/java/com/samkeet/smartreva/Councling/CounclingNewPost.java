package com.samkeet.smartreva.Councling;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.samkeet.smartreva.Constants;
import com.samkeet.smartreva.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CounclingNewPost extends AppCompatActivity {

    public EditText mTitle,mName,mDesc;
    public ProgressDialog pd;
    public Context progressDialogContext;

    public String title,name,desc,datetext;
    public String responce;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_councling_new_post);

        progressDialogContext=this;

        mTitle= (EditText) findViewById(R.id.title);
        mName= (EditText) findViewById(R.id.name);
        mDesc= (EditText) findViewById(R.id.message);


    }

    public void BackButton(View v) {
        finish();
    }

    public void Send(View v) {
        name=mName.getText().toString();
        desc=mDesc.getText().toString();
        title=mTitle.getText().toString();
        datetext = new SimpleDateFormat("dd-MM-yyyy").format(new Date());

        SendNewPost sendNewPost=new SendNewPost();
        sendNewPost.execute();
    }

    public class SendNewPost extends AsyncTask<Void, Void, Integer> {


        protected void onPreExecute() {
            pd = new ProgressDialog(progressDialogContext);
            pd.setTitle("Loading...");
            pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            pd.setMessage("Please wait.");
            pd.setCancelable(false);
            pd.setIndeterminate(true);
            pd.show();
        }

        protected Integer doInBackground(Void... params) {
            try {

                int random = (int )(Math.random() * 5000 + 1);
                String postID= String.valueOf(random);
                URL url = new URL(Constants.URLs.SEND_NEW_POST);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setDoInput(true);
                connection.setDoOutput(true);
                connection.setRequestMethod("POST");
                Log.d("POST", "DATA ready to sent");

                Uri.Builder _data = new Uri.Builder().appendQueryParameter("postID",postID).appendQueryParameter("name",name).appendQueryParameter("date",datetext).appendQueryParameter("message",desc).appendQueryParameter("title",title);
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
                Log.d("return from server", jsonResults.toString());
                responce=jsonResults.toString();

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
            responce=responce.replaceAll("\\t","");
            if(!responce.equals("Posted Succesfully")){
                Toast.makeText(getApplicationContext(),"Error: Post Unsuccessful !!!",Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(getApplicationContext(),responce ,Toast.LENGTH_SHORT).show();
            }
            finish();
        }
    }
}
