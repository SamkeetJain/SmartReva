package com.samkeet.smartreva.Results;

import android.app.ActionBar;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutCompat;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.samkeet.smartreva.Constants;
import com.samkeet.smartreva.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import dmax.dialog.SpotsDialog;

public class ResultsMainActivity extends AppCompatActivity {

    public Spinner semesterDrop, componetDrop;
    final String semesterItems[] = new String[]{"First", "Second", "Third", "Fourth", "Fifth", "Sixth", "Seventh", "Eigth"};
    final String componetsItems[] = new String[]{"C1", "C2", "C3"};

    private SpotsDialog pd;
    private Context progressDialogContext;

    public TextView mUsn;
    public String usn;
    public String comp;

    public boolean authenticationError;
    public String errorMessage;
    public LinearLayout mResultsLayout;

    public JSONArray jsonArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results_main);

        progressDialogContext = this;

        mUsn = (TextView) findViewById(R.id.usnText);
        mResultsLayout = (LinearLayout) findViewById(R.id.resultLayout);

        semesterDrop = (Spinner) findViewById(R.id.spinner1);
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, semesterItems);
        semesterDrop.setAdapter(adapter1);
        semesterDrop.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {

//                Toast.makeText(getApplicationContext(),semesterItems[position],Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        componetDrop = (Spinner) findViewById(R.id.spinner2);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, componetsItems);
        componetDrop.setAdapter(adapter2);
        componetDrop.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                comp = componetsItems[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    public void Send(View v) {
        mResultsLayout.removeAllViews();
        usn = mUsn.getText().toString();
        if (Constants.Methods.networkState(getApplicationContext(), (ConnectivityManager) getSystemService(getApplicationContext().CONNECTIVITY_SERVICE))) {
            GetResults getResults = new GetResults();
            getResults.execute();
        }
    }

    public void BackButton(View v) {
        finish();
    }

    private class GetResults extends AsyncTask<Void, Void, Integer> {


        protected void onPreExecute() {
            pd = new SpotsDialog(progressDialogContext, R.style.CustomPD);
            pd.setTitle("Loading...");
            pd.setCancelable(false);
            pd.show();
        }

        protected Integer doInBackground(Void... params) {
            try {
                java.net.URL url = new URL(Constants.URLs.BASE + Constants.URLs.GET_RESULTS);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setDoInput(true);
                connection.setDoOutput(true);
                connection.setRequestMethod("POST");
                Log.d("POST", "DATA ready to sent");

                Uri.Builder _data = new Uri.Builder().appendQueryParameter("token", Constants.SharedPreferenceData.getTOKEN()).appendQueryParameter("usn", usn).appendQueryParameter("Component", comp);
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(connection.getOutputStream(), "UTF-8"));
                writer.write(_data.build().getEncodedQuery());
                writer.flush();
                writer.close();
                Log.d("POST", "Data Sent");

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
                    jsonArray = new JSONArray(jsonResults.toString());
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
                try {
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        String sub = jsonObject.getString("subject_code");
                        String res = jsonObject.getString(comp);
                        CardView cardView = new CardView(getApplicationContext());
                        CardView.LayoutParams cardParam = new CardView.LayoutParams(CardView.LayoutParams.MATCH_PARENT, CardView.LayoutParams.WRAP_CONTENT);
                        cardView.setLayoutParams(cardParam);
                        cardView.setCardElevation(4);
                        cardView.setRadius(2);
                        cardView.setCardBackgroundColor(Color.WHITE);
                        cardView.setUseCompatPadding(true);
                        LinearLayout L1 = new LinearLayout(getApplicationContext());
                        LinearLayout.LayoutParams L1Params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                        L1.setLayoutParams(L1Params);
                        L1.setPadding(15, 15, 15, 15);
                        L1.setOrientation(LinearLayout.HORIZONTAL);

                        TextView subView = new TextView(getApplicationContext());
                        subView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1));
                        subView.setText(sub.toUpperCase());
                        subView.setTextColor(Color.BLACK);

                        TextView resView = new TextView(getApplicationContext());
                        resView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1));
                        resView.setText(res.toUpperCase());
                        resView.setTextColor(Color.BLACK);

                        L1.addView(subView);
                        L1.addView(resView);

                        cardView.addView(L1);

                        mResultsLayout.addView(cardView);

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

        }
    }


}
