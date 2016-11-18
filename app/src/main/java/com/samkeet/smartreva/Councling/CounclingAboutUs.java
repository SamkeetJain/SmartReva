package com.samkeet.smartreva.Councling;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.SyncStateContract;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.Toast;

import com.samkeet.smartreva.AlumniCell.UploadActivity;
import com.samkeet.smartreva.Constants;

public class CounclingAboutUs extends AppCompatActivity {

    public Button uploadDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WebView webview = new WebView(this);
        getWindow().requestFeature(Window.FEATURE_PROGRESS);
        setContentView(webview);

        webview.getSettings().setJavaScriptEnabled(true);

        final Activity activity = this;
        webview.setWebChromeClient(new WebChromeClient() {
            public void onProgressChanged(WebView view, int progress) {
                // Activities and WebViews measure progress with different scales.
                // The progress meter will automatically disappear when we reach 100%
                activity.setProgress(progress * 1000);
            }
        });
        webview.setWebViewClient(new WebViewClient() {
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                Toast.makeText(activity, "Oh no! " + description, Toast.LENGTH_SHORT).show();
            }
        });

        webview.loadUrl(Constants.URLs.ABOUTUS);
    }

    public void UploadDetails(View v){
        Intent intent=new Intent(getApplicationContext(), UploadActivity.class);
        startActivity(intent);
    }
}
