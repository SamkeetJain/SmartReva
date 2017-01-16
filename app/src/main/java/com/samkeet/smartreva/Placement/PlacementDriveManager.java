package com.samkeet.smartreva.Placement;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.samkeet.smartreva.Constants;
import com.samkeet.smartreva.R;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import dmax.dialog.SpotsDialog;

public class PlacementDriveManager extends AppCompatActivity {

    public String data;
    public TextView mTitle,mDetails,mEligibility;
    public ImageView mImage;
    public Button mRegister;
    public JSONObject object;

    public SpotsDialog pd;
    public Context progressDialogContext;
    public boolean authenticationError = true;
    public String errorMessage = "Data Corrupted";

    public String title,details,id,ddate,role,dept,el_tenth,el_twelth,el_ug,el_cb,el_nb;
    public String message,status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_placement_drive_management);
        progressDialogContext=this;

        mImage= (ImageView) findViewById(R.id.logo);
        mTitle = (TextView) findViewById(R.id.title);
        mDetails = (TextView) findViewById(R.id.details);
        mRegister = (Button) findViewById(R.id.register);
        mEligibility= (TextView) findViewById(R.id.eligiblity);

        data=getIntent().getStringExtra("DATA");
        try {
            object=new JSONObject(data);
            title = object.getString("comp_name");
            details = object.getString("details");
            id = object.getString("ID");
            el_tenth=object.getString("el_tenth");
            el_twelth=object.getString("el_twelth");
            el_ug = object.getString("el_ug");
            el_cb=object.getString("el_cb");
            el_nb=object.getString("el_nb");
            mTitle.setText(title);
            mDetails.setText(details);
            mEligibility.setText("Eligibility Criteria\nMin Tenth Score = "+el_tenth+"\nMin Twelfth Score = "+el_twelth+"\nMin UG Score = "+el_ug+"\nBacklog history = "+el_nb.substring(0,3)+"\nCurrent Backlog = "+el_cb.substring(0,3));
            Picasso.with(getApplicationContext()).load(Constants.URLs.BASE+"uploads/"+object.getString("logoFileName")).into(mImage);

        } catch (JSONException e) {
            e.printStackTrace();
        }


        mRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Registration registration = new Registration();
                registration.execute();
            }
        });
    }

    public void BackButton(View v){
        finish();
    }

    public class Registration extends AsyncTask<Void, Void, Integer> {


        protected void onPreExecute() {
            pd = new SpotsDialog(progressDialogContext,R.style.CustomPD);
            pd.setTitle("Loading...");
            pd.setCancelable(false);
            pd.show();
        }

        protected Integer doInBackground(Void... params) {
            try {


                URL url = new URL(Constants.URLs.BASE + Constants.URLs.PLACEMENT_DRIVE_REGISTRATION);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setDoInput(true);
                connection.setDoOutput(true);
                connection.setRequestMethod("POST");
                Log.d("POST", "DATA ready to sent");

                Uri.Builder _data = new Uri.Builder().appendQueryParameter("token", Constants.SharedPreferenceData.getTOKEN()).appendQueryParameter("ID", id);
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

                authenticationError = jsonResults.toString().contains("Authentication Error");

                if (authenticationError) {
                    errorMessage = jsonResults.toString();
                } else {
                    JSONObject jsonObject = new JSONObject(jsonResults.toString());
                    status = jsonObject.getString("status");
                    message = jsonObject.getString("message");
                    authenticationError = false;

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
                if(status .equals("ELIGIBLE")){
                    Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
                }else if(status.equals("NOT ELIGIBLE")){
                    Toast.makeText(getApplicationContext(), status+"\n"+message, Toast.LENGTH_SHORT).show();
                }
            }
        }

    }


}
