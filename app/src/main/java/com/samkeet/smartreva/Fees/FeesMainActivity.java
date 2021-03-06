package com.samkeet.smartreva.Fees;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

import dmax.dialog.SpotsDialog;

public class FeesMainActivity extends AppCompatActivity {

    public TextView mTotal, mDue, mPaid;
    public SpotsDialog pd;
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

        if (Constants.Methods.networkState(getApplicationContext(), (ConnectivityManager) getSystemService(getApplicationContext().CONNECTIVITY_SERVICE))) {
            GetFees getFees = new GetFees();
            getFees.execute();
        }
    }

    public void BackButton(View v) {
        finish();
    }

    private class GetFees extends AsyncTask<Void, Void, Integer> {


        protected void onPreExecute() {
            pd = new SpotsDialog(progressDialogContext, R.style.CustomPD);
            pd.setTitle("Loading...");
            pd.setCancelable(false);
            pd.show();
        }

        protected Integer doInBackground(Void... params) {
            try {
                java.net.URL url = new URL(Constants.URLs.BASE + Constants.URLs.FEE);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setDoInput(true);
                connection.setDoOutput(true);
                connection.setRequestMethod("POST");
                Uri.Builder _data = new Uri.Builder().appendQueryParameter("token", Constants.SharedPreferenceData.getTOKEN());
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
                    JSONArray jsonArray = new JSONArray(jsonResults.toString());
                    if (jsonArray.length() == 1) {

                        JSONObject jsonObject = jsonArray.getJSONObject(0);
                        total =jsonObject.getString("totalFee");
                        paid = jsonObject.getString("paidFee");
                        int t = Integer.parseInt(total);
                        int p = Integer.parseInt(paid);
                        if (t - p > 0) {
                            int d = t - p;
                            due = getString(R.string.Rs) + " " +String.valueOf(d);
                        } else {
                            due = "No due";
                        }
                        total = getString(R.string.Rs) + " " +jsonObject.getString("totalFee");
                        paid = getString(R.string.Rs) + " " +jsonObject.getString("paidFee");
                    } else {
                        if (jsonArray.length() == 0)
                            errorMessage = "No Data for corresponding UserID ";
                        if (jsonArray.length() > 1)
                            errorMessage = "Corrupted or invalid Data\nContact Developer about this bug";
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
