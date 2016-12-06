package com.samkeet.smartreva.Placement;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.samkeet.smartreva.R;

public class DriveManagement extends AppCompatActivity {

    public String TYPE;
    public TextView mTitle;
    public ImageView mImage;
    public Button mRegister;
    public WebView mDetails;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drive_management);
        TYPE=getIntent().getStringExtra("TYPE");

        mImage= (ImageView) findViewById(R.id.logo);
        mTitle = (TextView) findViewById(R.id.title);
        mDetails = (WebView) findViewById(R.id.details);
        mRegister = (Button) findViewById(R.id.register);
        mRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TYPE.equals("ONE")){
                    mRegister.setText("Registered Successfully");
                    mRegister.setBackgroundColor(Color.GREEN);
                }else if (TYPE.equals("TWO")){
                    mRegister.setText("Not Eligible ");
                    mRegister.setBackgroundColor(Color.RED);
                }
            }
        });

        if(TYPE.equals("ONE")){
            String title="Tata Consultancy Services";
            String details = "<html>\n" +
                    "<title>\n" +
                    "</title>\n" +
                    "<head>\n" +
                    "</head>\n" +
                    "<body>\n" +
                    "<p><h4>Job description</h4>\n" +
                    "\n" +
                    "<h5>Core responsiblities:</h5>\n" +
                    "<div >\n" +
                    "<ul>\n" +
                    "    <li>You will translate designs and wireframes into high-quality code</li>\n" +
                    "    <li>You will be responsible for building and maintaining high-performance, reusable, and reliable code</li>\n" +
                    "    <li>Ensure the best possible performance, quality, and responsiveness of the application</li>\n" +
                    "    <li>Identify and correct bottlenecks and fix bugs</li>\n" +
                    "    <li>Help maintain code quality, organization, and automation</li>\n" +
                    "\t</ul>\n" +
                    "</div>\n" +
                    "\n" +
                    "<h5>kills required:</h5>\n" +
                    "<div>\n" +
                    "<ul>\n" +
                    "    <li>1-3 years of experience with Android development </li>\n" +
                    "    <li>Experience building and maintaining large-scale apps, or have a portfolio of impressive apps that you’ve built personally </li>\n" +
                    "    <li>Being a technical expert of the Android platform and competent with industry standard libraries </li>\n" +
                    "    <li>Passion for Mobile UI & UX to make apps exciting for the user</li>\n" +
                    "    <li>Excellent debugging and optimization skills </li>\n" +
                    "    <li>Proficient in Java language and related frameworks </li>\n" +
                    "    <li>Effective communication skills (both written and verbal)</li> \n" +
                    "    <li>Comfort and delight in a small, fast-paced startup environment</li>\n" +
                    "\t</ul>\n" +
                    "</div>\n" +
                    "</p>\n" +
                    "</body>\n" +
                    "\n" +
                    "</html>";

            mTitle.setText(title);
            mDetails.loadDataWithBaseURL(null,details,"text/html","utf-8",null);
            mImage.setImageResource(R.drawable.tata);

        }else if (TYPE.equals("TWO")){

            String title="IBM";
            String details = "<html>\n" +
                    "<title>\n" +
                    "</title>\n" +
                    "<head>\n" +
                    "</head>\n" +
                    "<body>\n" +
                    "<p><h4>Job description</h4>\n" +
                    "\n" +
                    "<h5>Core responsiblities:</h5>\n" +
                    "<div >\n" +
                    "<ul>\n" +
                    "    <li>You will translate designs and wireframes into high-quality code</li>\n" +
                    "    <li>You will be responsible for building and maintaining high-performance, reusable, and reliable code</li>\n" +
                    "    <li>Ensure the best possible performance, quality, and responsiveness of the application</li>\n" +
                    "    <li>Identify and correct bottlenecks and fix bugs</li>\n" +
                    "    <li>Help maintain code quality, organization, and automation</li>\n" +
                    "\t</ul>\n" +
                    "</div>\n" +
                    "\n" +
                    "<h5>kills required:</h5>\n" +
                    "<div>\n" +
                    "<ul>\n" +
                    "    <li>1-3 years of experience with Android development </li>\n" +
                    "    <li>Experience building and maintaining large-scale apps, or have a portfolio of impressive apps that you’ve built personally </li>\n" +
                    "    <li>Being a technical expert of the Android platform and competent with industry standard libraries </li>\n" +
                    "    <li>Passion for Mobile UI & UX to make apps exciting for the user</li>\n" +
                    "    <li>Excellent debugging and optimization skills </li>\n" +
                    "    <li>Proficient in Java language and related frameworks </li>\n" +
                    "    <li>Effective communication skills (both written and verbal)</li> \n" +
                    "    <li>Comfort and delight in a small, fast-paced startup environment</li>\n" +
                    "\t</ul>\n" +
                    "</div>\n" +
                    "</p>\n" +
                    "</body>\n" +
                    "\n" +
                    "</html>";
            mTitle.setText(title);
            mDetails.loadDataWithBaseURL(null,details,"text/html","utf-8",null);
            mImage.setImageResource(R.drawable.ibmlogo);

        }
    }

    public void BackButton(View v){
        finish();
    }

}
