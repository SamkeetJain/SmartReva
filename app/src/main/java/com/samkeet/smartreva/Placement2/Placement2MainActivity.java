package com.samkeet.smartreva.Placement2;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v4.content.IntentCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.crash.FirebaseCrash;
import com.google.firebase.iid.FirebaseInstanceId;
import com.mikepenz.crossfadedrawerlayout.view.CrossfadeDrawerLayout;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.MiniDrawer;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.samkeet.smartreva.Constants;
import com.samkeet.smartreva.DevelopersActivity;
import com.samkeet.smartreva.LoginActivity;
import com.samkeet.smartreva.R;

import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import dmax.dialog.SpotsDialog;

public class Placement2MainActivity extends AppCompatActivity {

    private AccountHeader headerResult = null;
    private Drawer result = null;
    private MiniDrawer miniResult = null;
    private CrossfadeDrawerLayout crossfadeDrawerLayout = null;
    private Toolbar toolbar;

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    public SwipeRefreshLayout swipeRefreshLayout;

    public boolean doubleBackToExitPressedOnce = false;

    public boolean authenticationError;
    public String errorMessage;

    public SpotsDialog pd;
    public Context progressDialogContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_placement2_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (Constants.SharedPreferenceData.isSharedPreferenceInited()) {
            Constants.SharedPreferenceData.initSharedPreferenceData(getSharedPreferences(Constants.SharedPreferenceData.SHAREDPREFERENCES, MODE_PRIVATE));
        }

        progressDialogContext = this;

        if (Constants.FireBase.token != null) {
            UpdateToken updateToken = new UpdateToken();
            updateToken.execute();
        }

        headerResult = new AccountHeaderBuilder()
                .withActivity(this)
                .withTranslucentStatusBar(true)
                .withHeaderBackground(R.drawable.reva_headerp)
//                .withHeaderBackground(R.drawable.header)
                /// TODO: 19-Oct-16
                .withSavedInstance(savedInstanceState)
                .build();

        result = new DrawerBuilder()
                .withActivity(this)
                .withToolbar(toolbar)
//                .withDrawerLayout(R.layout.crossfade_councling_material_drawer)
                .withHasStableIds(true)
//                .withDrawerWidthDp(72)
//                .withGenerateMiniDrawer(false)
                .withAccountHeader(headerResult) //set the AccountHeader we created earlier for the header
                .addDrawerItems(
                        new PrimaryDrawerItem().withName("Home").withIcon(R.drawable.ic_home_black_24dp).withIdentifier(1),
                        new PrimaryDrawerItem().withName("Academic Profile").withIcon(R.drawable.ic_event_black_24dp).withIdentifier(2),
                        new PrimaryDrawerItem().withName("Backlog Details").withIcon(R.drawable.ic_event_black_24dp).withIdentifier(3),
                        new PrimaryDrawerItem().withName("Registration Form").withIcon(R.drawable.ic_job_24dp).withIdentifier(4),
                        new DividerDrawerItem(),
                        new PrimaryDrawerItem().withName("Contact Us").withIcon(R.drawable.ic_contact_us_24dp).withIdentifier(5),
                        new PrimaryDrawerItem().withName("About Us").withIcon(R.drawable.ic_about_24dp).withIdentifier(6),
                        new DividerDrawerItem(),
                        new PrimaryDrawerItem().withName("Logout").withIcon(R.drawable.ic_logout_24dp).withIdentifier(7),
                        new PrimaryDrawerItem().withName("Developer").withIcon(R.drawable.ic_android_black_24dp).withIdentifier(8)
                )
                // add the items we want to use with our Drawer
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        if (drawerItem.getIdentifier() == 1) {

                        }
                        if (drawerItem.getIdentifier() == 2) {

                        }
                        if (drawerItem.getIdentifier() == 3) {
                            Intent intent = new Intent(getApplicationContext(), PlacementBackLogDetails.class);
                            startActivity(intent);
                        }
                        if (drawerItem.getIdentifier() == 4) {
                            CheckRegistration checkRegistration = new CheckRegistration();
                            checkRegistration.execute();
                        }
                        if (drawerItem.getIdentifier() == 5) {

                        }
                        if (drawerItem.getIdentifier() == 6) {

                        }
                        if (drawerItem.getIdentifier() == 7) {
                            Logout();
                        }
                        if (drawerItem.getIdentifier() == 8) {
                            Intent intent = new Intent(getApplicationContext(), DevelopersActivity.class);
                            startActivity(intent);
                        }
                        return false;
                    }
                })
                .withSavedInstance(savedInstanceState)
                .withShowDrawerOnFirstLaunch(false)
                .build();

    }

    public void Logout() {
        deleteToken deleteToken = new deleteToken();
        deleteToken.execute();
        Constants.SharedPreferenceData.clearData();
        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | IntentCompat.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }

    private class UpdateToken extends AsyncTask<Void, Void, Integer> {


        protected void onPreExecute() {
        }

        protected Integer doInBackground(Void... params) {
            try {
                java.net.URL url = new URL(Constants.URLs.PLACEMENT_BASE + Constants.URLs.FIREBASE);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setDoInput(true);
                connection.setDoOutput(true);
                connection.setRequestMethod("POST");

                Uri.Builder _data = new Uri.Builder().appendQueryParameter("token", Constants.SharedPreferenceData.getTOKEN()).appendQueryParameter("firebasetoken", Constants.FireBase.token);
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

                return 1;

            } catch (Exception ex) {
                ex.printStackTrace();
            }

            return 1;
        }

        protected void onPostExecute(Integer result) {
        }
    }

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce = false;
            }
        }, 2000);
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

    private class CheckRegistration extends AsyncTask<Void, Void, Integer> {


        protected void onPreExecute() {
            pd = new SpotsDialog(progressDialogContext, R.style.CustomPD);
            pd.setTitle("Loading...");
            pd.setCancelable(false);
            pd.show();
        }

        protected Integer doInBackground(Void... params) {
            try {
                java.net.URL url = new URL(Constants.URLs.PLACEMENT_BASE + Constants.URLs.PLACEMENT_CHECK);
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
                    JSONObject jsonObj = new JSONObject(jsonResults.toString());
                    String status = jsonObj.getString("status");
                    if (status.equals("PENDING")) {
                        authenticationError = false;
                    } else {
                        authenticationError = true;
                        if (status.equals("APPROVED") || status.equals("SUBMITTED")) {
                            errorMessage = "Already Registered";
                        } else {
                            errorMessage = status;
                        }
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
                AlertDialog.Builder builder = new AlertDialog.Builder(Placement2MainActivity.this);
                builder.setTitle("Oops!!! We cannot process your request at this time");
                builder.setMessage("Response: " + "\n" + errorMessage);
                String positiveText = "Okay";
                builder.setPositiveButton(positiveText,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                AlertDialog dialog = builder.create();
                dialog.show();
            } else {
                AlertDialog.Builder builder = new AlertDialog.Builder(Placement2MainActivity.this);
                builder.setTitle("Please note");
                builder.setMessage("You will have only ONE chance to submit your Registration Form.");
                String positiveText = "Proceed";
                builder.setPositiveButton(positiveText,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(getApplicationContext(), PlacementRegistration1.class);
                                startActivity(intent);
                            }
                        });
                String negativeText = "Not now";
                builder.setNegativeButton(negativeText,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        });

                AlertDialog dialog = builder.create();
                dialog.show();
            }
        }
    }

}
