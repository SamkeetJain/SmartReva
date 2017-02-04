package com.samkeet.smartreva.Placement2;

import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.samkeet.smartreva.Constants;
import com.samkeet.smartreva.R;
import com.satsuware.usefulviews.LabelledSpinner;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;

public class PlacementRegistration1 extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    public EditText mSrn, mPassword, mCPassword, mFullname, mPhone, mEmail, mCurrentAddress, mPermanentAddress;
    public TextInputLayout tSrn, tPassword, tCPassword, tFullname, tPhone, tEmail, tCurrentAddress, tPermanentAddress;
    public String srn, password, cpassword, fullname, dob, phone, email, currentAddress, permanentAddress;
    public CheckBox addressCheckBox;

    public LabelledSpinner genderSpin;
    String sGender;
    String[] genderList = {"Male", "Female"};

    public Button Next;
    public JSONObject jsonObject = new JSONObject();
    public Button mDob;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_placement2_registration1);

        //Edit text
        mSrn = (EditText) findViewById(R.id.srn_et);
        mCPassword = (EditText) findViewById(R.id.cpassword_et);
        mPassword = (EditText) findViewById(R.id.password_et);
        mFullname = (EditText) findViewById(R.id.fullname_et);
        mPhone = (EditText) findViewById(R.id.phoneno_et);
        mEmail = (EditText) findViewById(R.id.email_et);
        mCurrentAddress = (EditText) findViewById(R.id.caddress_et);
        mPermanentAddress = (EditText) findViewById(R.id.paddress_et);

        //Text input layout
        tSrn = (TextInputLayout) findViewById(R.id.tsrn);
        tPassword = (TextInputLayout) findViewById(R.id.tpassword);
        tCPassword = (TextInputLayout) findViewById(R.id.tcpassword);
        tFullname = (TextInputLayout) findViewById(R.id.tfull_name);
        tPhone = (TextInputLayout) findViewById(R.id.tphoneno);
        tEmail = (TextInputLayout) findViewById(R.id.temail);
        tCurrentAddress = (TextInputLayout) findViewById(R.id.tcaddress);
        tPermanentAddress = (TextInputLayout) findViewById(R.id.tpaddress);

        Next = (Button) findViewById(R.id.next_button);
        mDob = (Button) findViewById(R.id.dateButton);

        addressCheckBox = (CheckBox) findViewById(R.id.address_checkbox);
        addressCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (addressCheckBox.isChecked()) {
                    if (mCurrentAddress.getText().toString().trim().length() < 5) {
                        Toast.makeText(getApplicationContext(), "Invalid Current Address", Toast.LENGTH_SHORT).show();
                        addressCheckBox.setChecked(false);
                        return;
                    }
                    mPermanentAddress.setText(mCurrentAddress.getText().toString());
                } else {
                    mPermanentAddress.setText("");
                }
            }
        });

        genderSpin = (LabelledSpinner) findViewById(R.id.genderspiner);
        genderSpin.setItemsArray(genderList);
        genderSpin.setOnItemChosenListener(new LabelledSpinner.OnItemChosenListener() {
            @Override
            public void onItemChosen(View labelledSpinner, AdapterView<?> adapterView, View itemView, int position, long id) {
                sGender = genderList[position];
            }

            @Override
            public void onNothingChosen(View labelledSpinner, AdapterView<?> adapterView) {
                sGender = genderList[0];
            }
        });

        Next.setOnClickListener(new View.OnClickListener() {

                                    @Override
                                    public void onClick(View v) {
                                        srn = mSrn.getText().toString().trim();
                                        password = mPassword.getText().toString().trim();
                                        cpassword = mCPassword.getText().toString().trim();
                                        fullname = mFullname.getText().toString().trim();
                                        dob = mDob.getText().toString().trim();
                                        phone = mPhone.getText().toString().trim();
                                        email = mEmail.getText().toString().trim();
                                        currentAddress = mCurrentAddress.getText().toString().trim();
                                        permanentAddress = mPermanentAddress.getText().toString().trim();
                                        validation();
                                    }
                                }
        );
    }

    public void BackButton(View v) {
        finish();
    }

    public void validation() {
        if (!validSrn()) {
            return;
        }
        if (!validPassword()) {
            return;
        }
        if (!validCpassword()) {
            return;
        }
        if (!validFullname()) {
            return;
        }
        if (!validPhoneno()) {
            return;
        }
        if (!validEmail()) {
            return;
        }
        if (!validCurrentaddress()) {
            return;
        }
        if (!validPermanentaddress()) {
            return;
        }
        try {
            jsonObject.put("srn", srn);
            jsonObject.put("password", password);
            jsonObject.put("name", fullname);
            jsonObject.put("gender", sGender);
            jsonObject.put("dob", dob);
            jsonObject.put("phoneno", phone);
            jsonObject.put("email", email);
            jsonObject.put("ca", currentAddress);
            jsonObject.put("pa", permanentAddress);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Intent intent = new Intent(getApplicationContext(), PlacementRegistration2.class);
        intent.putExtra("OBJECT1", jsonObject.toString());
        startActivity(intent);
    }

    private boolean validSrn() {
        if (srn.isEmpty()) {
            tSrn.setError("Invalid srn");
            requestFocus(mSrn);
            return false;
        }
        if (Constants.Methods.checkForSpecial(srn)) {
            tSrn.setError("Remove Special characters");
            requestFocus(mSrn);
            return false;
        }
        if (srn.length() > 10) {
            tSrn.setError("Srn Should be less than 10 characters");
            requestFocus(mSrn);
            return false;
        } else {
            tSrn.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validPassword() {
        if (password.isEmpty()) {
            tPassword.setError("Invalid Password");
            requestFocus(mPassword);
            return false;
        }
        if (Constants.Methods.checkForSpecial(password)) {
            tSrn.setError("Remove Special characters");
            requestFocus(mPassword);
            return false;
        } else {
            tPassword.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validCpassword() {

        if (!cpassword.equals(password)) {
            tCPassword.setError("Password doesnt match");
            requestFocus(mCPassword);
            return false;
        } else {
            tCPassword.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validFullname() {
        if (fullname.isEmpty()) {
            tFullname.setError("Invalid Name");
            requestFocus(mFullname);
            return false;
        }
        if (Constants.Methods.checkForSpecial(fullname)) {
            tFullname.setError("Remove Special Characters");
            requestFocus(mFullname);
            return false;
        }
        if (fullname.length() > 35) {
            tFullname.setError("Name cannot be more than 35 characters");
            requestFocus(mFullname);
            return false;
        } else {
            tFullname.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validPhoneno() {

        if (phone.isEmpty() || !isValidMobilenumber(phone) || phone.length() < 5 || phone.length() > 10) {

            tPhone.setError("Invalid Phone Number");
            requestFocus(mPhone);
            return false;
        }
        if (Constants.Methods.checkForSpecial(phone)) {
            tPhone.setError("Remove Special Characters");
            requestFocus(mPhone);
            return false;
        } else {
            tPhone.setErrorEnabled(false);
        }
        return true;
    }

    private static boolean isValidMobilenumber(String mobileNo) {
        return !TextUtils.isEmpty(mobileNo) && Patterns.PHONE.matcher(mobileNo).matches();
    }

    private boolean validEmail() {
        if (email.isEmpty() || !isValidEmail(email)) {
            tEmail.setError("Invalid Email");
            requestFocus(mEmail);
            return false;
        }
        if (Constants.Methods.checkForSpecial(email)) {
            tEmail.setError("Remove Special Characters");
            requestFocus(mEmail);
            return false;
        }
        if (email.length() > 32) {
            tEmail.setError("Email should be less than 32 characters");
        } else {
            tEmail.setErrorEnabled(false);
        }
        return true;
    }

    private static boolean isValidEmail(String email) {
        return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private boolean validCurrentaddress() {
        if (currentAddress.isEmpty()) {
            tCurrentAddress.setError("Invalid Address");
            requestFocus(mCurrentAddress);
            return false;
        }
        if (Constants.Methods.checkForSpecial(currentAddress)) {
            tCurrentAddress.setError("Remove Special Characters");
            requestFocus(mCurrentAddress);
            return false;
        }
        if (currentAddress.length() > 256) {
            tCurrentAddress.setError("Address cannot bw more than 256 characters");
            requestFocus(mCurrentAddress);
            return false;
        } else {
            tCurrentAddress.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validPermanentaddress() {
        if (permanentAddress.isEmpty()) {
            tPermanentAddress.setError("Invalid Address");
            requestFocus(mPermanentAddress);
            return false;
        }
        if (Constants.Methods.checkForSpecial(permanentAddress)) {
            tPermanentAddress.setError("Remove Special Characters");
            requestFocus(mPermanentAddress);
            return false;
        }
        if (permanentAddress.length() > 256) {
            tPermanentAddress.setError("Address cannot bw more than 256 characters");
            requestFocus(mPermanentAddress);
            return false;
        } else {
            tPermanentAddress.setErrorEnabled(false);
        }
        return true;
    }

    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }

    public void DateButton(View v) {
        Calendar now = Calendar.getInstance();
        DatePickerDialog dpd = DatePickerDialog.newInstance(
                PlacementRegistration1.this,
                now.get(Calendar.YEAR),
                now.get(Calendar.MONTH),
                now.get(Calendar.DAY_OF_MONTH)
        );
        dpd.show(getFragmentManager(), "Datepickerdialog");
    }

    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        String fullDate;
        String dayofmonth = "" + dayOfMonth;
        String monthofyear = "" + monthOfYear;
        if (dayOfMonth < 10)
            dayofmonth = "0" + dayOfMonth;
        if (++monthOfYear < 10) {
            monthofyear = "0" + monthOfYear;
        }
        fullDate = year + "-" + (monthofyear) + "-" + dayofmonth;
        mDob.setText(fullDate);
    }
}
