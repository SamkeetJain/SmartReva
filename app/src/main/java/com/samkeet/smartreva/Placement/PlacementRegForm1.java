package com.samkeet.smartreva.Placement;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.samkeet.smartreva.R;

public class PlacementRegForm1 extends AppCompatActivity {

    public EditText mName, mDob, mGender, mNationality, mPaddress, mCaddress, mPhone, mMobile, mEmail;
    public EditText mFname, mFmobile, mFoccupation, mFemail;
    public EditText mMname, mMmobile, mMoccupation, mMemail;
    public EditText mTs, mTpy, mTb, mTn, mTm;
    public EditText mTWs, mTWpy, mTWb, mTWn, mTWm;
    public EditText mDs, mDpy, mDb, mDn, mDm;

    public String name, dob, gender, nationality, paddress, caddress, phone, mobile, email;
    public String fname, fmobile, foccupation, femail;
    public String mname, mmobile, moccupation, memail;
    public String ts, tpy, tb, tn, tm;
    public String tws, twpy, twb, twn, twm;
    public String ds, dpy, db, dn, dm;

    public CardView DiplomaCard;

    public String isDiploma;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_placement_regform1);

        setFindViewById();

        isDiploma = getIntent().getStringExtra("DIP");

        if (isDiploma.equals("no")) {
            DiplomaCard.setVisibility(View.GONE);
        }
    }

    public void sendData() {
        name = mName.getText().toString();
        dob = mDob.getText().toString();
        gender = mGender.getText().toString();
        nationality = mNationality.getText().toString();
        paddress = mPaddress.getText().toString();
        caddress = mCaddress.getText().toString();
        phone = mPhone.getText().toString();
        mobile = mMobile.getText().toString();
        email = mEmail.getText().toString();

        fname = mFname.getText().toString();
        fmobile = mFmobile.getText().toString();
        foccupation = mFoccupation.getText().toString();
        femail = mFemail.getText().toString();

        mname = mMname.getText().toString();
        mmobile = mMmobile.getText().toString();
        moccupation = mMoccupation.getText().toString();
        memail = mMemail.getText().toString();

        ts = mTs.getText().toString();
        tpy = mTpy.getText().toString();
        tb = mTb.getText().toString();
        tn = mTn.getText().toString();
        tm = mTm.getText().toString();

        tws = mTWs.getText().toString();
        twpy = mTWpy.getText().toString();
        twb = mTWb.getText().toString();
        twn = mTWn.getText().toString();
        twm = mTWm.getText().toString();

        if (isDiploma.equals("yes")) {
            ds = mDs.getText().toString();
            dpy = mDpy.getText().toString();
            db = mDb.getText().toString();
            dn = mDn.getText().toString();
            dm = mDm.getText().toString();
        }

    }

    public void setFindViewById() {

        mName = (EditText) findViewById(R.id.full_name);
        mDob = (EditText) findViewById(R.id.DOB);
        mGender = (EditText) findViewById(R.id.gender);
        mNationality = (EditText) findViewById(R.id.Nationality);
        mPaddress = (EditText) findViewById(R.id.paddress);
        mCaddress = (EditText) findViewById(R.id.caddress);
        mPhone = (EditText) findViewById(R.id.phone);
        mMobile = (EditText) findViewById(R.id.mobile);
        mEmail = (EditText) findViewById(R.id.email);

        mFname = (EditText) findViewById(R.id.f_full_name);
        mFoccupation = (EditText) findViewById(R.id.f_occupation);
        mFmobile = (EditText) findViewById(R.id.f_mobile);
        mFemail = (EditText) findViewById(R.id.f_email);

        mMname = (EditText) findViewById(R.id.m_full_name);
        mMoccupation = (EditText) findViewById(R.id.m_occupation);
        mMmobile = (EditText) findViewById(R.id.m_mobile);
        mMemail = (EditText) findViewById(R.id.m_email);

        mMname = (EditText) findViewById(R.id.m_full_name);
        mMoccupation = (EditText) findViewById(R.id.m_occupation);
        mMmobile = (EditText) findViewById(R.id.m_mobile);
        mMemail = (EditText) findViewById(R.id.m_email);

        DiplomaCard = (CardView) findViewById(R.id.diplomaCard);

        mTs = (EditText) findViewById(R.id.t_s);
        mTpy = (EditText) findViewById(R.id.t_py);
        mTb = (EditText) findViewById(R.id.t_b);
        mTn = (EditText) findViewById(R.id.t_n);
        mTm = (EditText) findViewById(R.id.t_m);

        mTWs = (EditText) findViewById(R.id.tw_s);
        mTWpy = (EditText) findViewById(R.id.tw_py);
        mTWb = (EditText) findViewById(R.id.tw_b);
        mTWn = (EditText) findViewById(R.id.tw_n);
        mTWm = (EditText) findViewById(R.id.tw_m);

        mDs = (EditText) findViewById(R.id.d_s);
        mDpy = (EditText) findViewById(R.id.d_py);
        mDb = (EditText) findViewById(R.id.d_b);
        mDn = (EditText) findViewById(R.id.d_n);
        mDm = (EditText) findViewById(R.id.d_m);

    }

    public void BackButton(View v) {
        finish();
    }

    public void submit(View v) {

    }
}
