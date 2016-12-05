package com.samkeet.smartreva.Placement;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.samkeet.smartreva.R;

public class DriveManagement extends AppCompatActivity {

    public String TYPE;
    public TextView mTitle,mDetails;
    public ImageView mImage;
    public Button mRegister;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drive_management);
        TYPE=getIntent().getStringExtra("TYPE");

        mImage= (ImageView) findViewById(R.id.logo);
        mTitle = (TextView) findViewById(R.id.title);
        mDetails = (TextView) findViewById(R.id.details);
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
            String details = "";

            mTitle.setText(title);
            mDetails.setText(Html.fromHtml(details));
            mImage.setImageResource(R.drawable.tata);

        }else if (TYPE.equals("TWO")){

            String title="IBM";
            String details = "";

            mTitle.setText(title);
            mDetails.setText(Html.fromHtml(details));
            mImage.setImageResource(R.drawable.tata);

        }
    }

    public void BackButton(View v){
        finish();
    }

}
