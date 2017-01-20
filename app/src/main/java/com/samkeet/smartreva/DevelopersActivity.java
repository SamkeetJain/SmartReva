package com.samkeet.smartreva;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class DevelopersActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_developers);
    }
    public void BackButton (View v){finish();}

    public void samcall(View v){
        try {
            Intent callIntent = new Intent(Intent.ACTION_DIAL);
            callIntent.setData(Uri.parse("tel:" + "+918147514179"));
            startActivity(callIntent);
        } catch (ActivityNotFoundException activityException) {
        }
    }
    public void samemail(View v){
        Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                "mailto","jain.sankeet2210@gmail.com", null));
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "From Smart Reva Android App");
        startActivity(Intent.createChooser(emailIntent, "Send email..."));
    }
    public void vaicall(View v){
        try {
            Intent callIntent = new Intent(Intent.ACTION_DIAL);
            callIntent.setData(Uri.parse("tel:" + "+919686488775"));
            startActivity(callIntent);
        } catch (ActivityNotFoundException activityException) {
        }
    }
    public void vaiemail(View v){
        Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                "mailto","vaibhavkrishna.bhosle@gmail.com", null));
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "From Smart Reva Android App");
        startActivity(Intent.createChooser(emailIntent, "Send email..."));
    }
    public void leecall(View v){
        try {
            Intent callIntent = new Intent(Intent.ACTION_DIAL);
            callIntent.setData(Uri.parse("tel:" + "+919740504214"));
            startActivity(callIntent);
        } catch (ActivityNotFoundException activityException) {
        }
    }
    public void leeemail(View v){
        Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                "mailto","lilashsah2012@gmail.com", null));
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "From Smart Reva Android App");
        startActivity(Intent.createChooser(emailIntent, "Send email..."));
    }
    public void radzcall(View v){
        try {
            Intent callIntent = new Intent(Intent.ACTION_DIAL);
            callIntent.setData(Uri.parse("tel:" + "+919902476427"));
            startActivity(callIntent);
        } catch (ActivityNotFoundException activityException) {
        }
    }
    public void radzemail(View v){
        Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                "mailto","gargradhika1996@gmail.com", null));
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "From Smart Reva Android App");
        startActivity(Intent.createChooser(emailIntent, "Send email..."));
    }
}
