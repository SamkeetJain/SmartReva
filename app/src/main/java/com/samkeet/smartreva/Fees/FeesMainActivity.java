package com.samkeet.smartreva.Fees;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
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

public class FeesMainActivity extends AppCompatActivity {

    public TextView mTotal, mDue, mPaid;
    public ProgressDialog pd;
    public Context progressDialogContext;

    public boolean authenticationError;
    public String errorMessage;

    public String total, paid, due;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fees_main);

        progressDialogContext = this;

        mTotal = (TextView) findViewById(R.id.tot);
        mDue = (TextView) findViewById(R.id.due);
        mPaid = (TextView) findViewById(R.id.paid);

        GetFees getFees=new GetFees();
        getFees.execute();

    }

    public void BackButton(View v) {
        finish();
    }

    private class GetFees extends AsyncTask<Void, Void, Integer> {


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
                java.net.URL url = new URL(Constants.URLs.BASE + Constants.URLs.FEE);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setDoInput(true);
                connection.setDoOutput(true);
                connection.setRequestMethod("POST");
                Log.d("POST", "DATA ready to sent");

                Uri.Builder _data = new Uri.Builder().appendQueryParameter("token", Constants.SharedPreferenceData.getTOKEN());
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
                    JSONArray jsonArray = new JSONArray(jsonResults.toString());
                    if (jsonArray.length() == 1) {

                        JSONObject jsonObject = jsonArray.getJSONObject(0);
                        total = jsonObject.getString("totalFee");
                        paid = jsonObject.getString("paidFee");
                        int t = Integer.parseInt(total);
                        int p = Integer.parseInt(paid);
                        if (t - p > 0) {
                            int d = t - p;
                            due = String.valueOf(d);
                        } else {
                            due = "No due";
                        }
                    }else {
                        if(jsonArray.length()==0) errorMessage="No Data for corresponding UserID ";
                        if(jsonArray.length()>1) errorMessage="Corrupted or invalid Data\nContact Developer about this bug";
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
                mTotal.setText(total);
                mPaid.setText(paid);
                mDue.setText(due);
            }
        }
    }

}
