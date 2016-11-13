package com.samkeet.smartreva.Attendence;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.samkeet.smartreva.Constants;
import com.samkeet.smartreva.R;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;

public class TakeAttendence extends AppCompatActivity implements DatePickerDialog.OnDateSetListener{

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private Context progressDialogContext;
    public ProgressDialog pd;

    public String table;
    public String studentsList[];

    public String datetext;
    public TextView dateView;
    public String finalValues,finalFields;

    String res;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_attendence);

        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        progressDialogContext = this;
        dateView= (TextView) findViewById(R.id.date);

        table = getIntent().getStringExtra("TABLE");

        GetStudentsList getStudentsList=new GetStudentsList();
        getStudentsList.execute();

    }

    public void BackButton(View v) {
        finish();
    }

    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        String date=""+dayOfMonth+"/"+(++monthOfYear)+"/"+year;
        dateView.setText(date);
    }

    private class GetStudentsList extends AsyncTask<Void, Void, Integer> {


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
                java.net.URL url = new URL(Constants.URLs.GETSTUDENTLIST);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setDoInput(true);
                connection.setDoOutput(true);
                connection.setRequestMethod("POST");
                Log.d("POST", "DATA ready to sent");

                Uri.Builder _data = new Uri.Builder().appendQueryParameter("table", table);
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

                String temp=jsonResults.toString();
                studentsList=temp.split("\\|");
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
            ArrayList<String> arrayList=new ArrayList<String>();
            for(int i=0;i<studentsList.length;i++){
                arrayList.add(studentsList[i]);
            }

            arrayList.remove(arrayList.size()-1);
            arrayList.remove(0);

            String[] newList=arrayList.toArray(new String[arrayList.size()]);

            mAdapter = new TakeAttendenceAdapter(newList);
            mRecyclerView.setAdapter(mAdapter);


        }
    }

    public void Submit(View v){
        datetext=dateView.getText().toString();
        boolean check[]=((TakeAttendenceAdapter) mAdapter).getCheckBoxes();
        String values[]=new String[check.length];
        for(int i=0;i<check.length;i++){
            if(check[i]==true){
                values[i]="p";
            }else if(check[i]==false){
                values[i]="a";
            }
        }
        String []fields= ((TakeAttendenceAdapter)mAdapter).getTitles();
        String temp=fields[0];
        for(int i=1;i<fields.length;i++){
            temp=temp.concat("|"+fields[i]);
        }
        finalFields=temp;
        temp=values[0];
        for(int i=1;i<values.length;i++){
            temp=temp.concat("|"+values[i]);
        }
        finalValues=temp;

        InsertAttendence insertAttendence=new InsertAttendence();
        insertAttendence.execute();

    }

    public void Date(View v){
        Calendar now = Calendar.getInstance();
        DatePickerDialog dpd = DatePickerDialog.newInstance(
                TakeAttendence.this,
                now.get(Calendar.YEAR),
                now.get(Calendar.MONTH),
                now.get(Calendar.DAY_OF_MONTH)
        );
        Calendar minDate = Calendar.getInstance();
        minDate.set(now.get(Calendar.YEAR), now.get(Calendar.MONTH), now.get(Calendar.DAY_OF_MONTH));
        dpd.setMinDate(minDate);
        dpd.show(getFragmentManager(), "Datepickerdialog");
    }

    private class InsertAttendence extends AsyncTask<Void, Void, Integer> {


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
                java.net.URL url = new URL(Constants.URLs.INSERTINTOATTENDENCE);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setDoInput(true);
                connection.setDoOutput(true);
                connection.setRequestMethod("POST");
                Log.d("POST", "DATA ready to sent");

                Uri.Builder _data = new Uri.Builder().appendQueryParameter("table", table).appendQueryParameter("date",datetext).appendQueryParameter("value",finalValues).appendQueryParameter("field",finalFields);
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
                res=jsonResults.toString();


            } catch (Exception ex) {
                ex.printStackTrace();
            }

            return 1;
        }

        protected void onPostExecute(Integer result) {
            if (pd != null) {
                pd.dismiss();
            }
            res=res.replaceAll("\\t","");
            if(res.equals("")){
                Toast.makeText(getApplicationContext(),"Attendence Uploaded",Toast.LENGTH_SHORT).show();
                finish();
            }

        }
    }


}
