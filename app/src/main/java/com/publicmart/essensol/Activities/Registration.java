package com.publicmart.essensol.Activities;

import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.crashlytics.android.Crashlytics;
import com.publicmart.essensol.R;
import com.publicmart.essensol.RetrofitUtils.ApiClient;
import com.publicmart.essensol.RetrofitUtils.ApiInterface;
import com.publicmart.essensol.RetrofitUtils.RetrofitResponseClasses.CheckUsernameResponse;
import com.publicmart.essensol.RetrofitUtils.RetrofitResponseClasses.RegisterResponse;
import com.publicmart.essensol.TabFragments.LoginTab;
import com.publicmart.essensol.Utility;

import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;

import java.util.List;

import io.fabric.sdk.android.Fabric;
import retrofit2.Call;
import retrofit2.Callback;

public class Registration extends AppCompatActivity {
    LinearLayout regist;
    EditText fname,mname,lname,house_no,tehsil,village,district,pincode,contact_no,email,sponsership_id,nominee,relationship,username,password,cnfmpassword;

    TextView terms,availability;






    JSONObject jsonString;
    String request,code,message;



    ApiInterface apiInterface;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_ed);

        Fabric.with(this, new Crashlytics());
        regist = (LinearLayout)findViewById(R.id.submit);

        fname = (EditText)findViewById(R.id.fname);

        availability= (TextView) findViewById(R.id.availability);


        tehsil = (EditText)findViewById(R.id.tehsil);
        village = (EditText)findViewById(R.id.village);
        district = (EditText)findViewById(R.id.district);
        pincode = (EditText)findViewById(R.id.pin);
        contact_no = (EditText)findViewById(R.id.contactno);
        email = (EditText)findViewById(R.id.email);
        sponsership_id = (EditText)findViewById(R.id.sponcerid);
        nominee = (EditText)findViewById(R.id.nominee);
        username = (EditText)findViewById(R.id.username);
        password = (EditText)findViewById(R.id.password);
        cnfmpassword = (EditText)findViewById(R.id.Confirmpasswd);

        terms = (TextView) findViewById(R.id.terms);

        apiInterface= ApiClient.getClient().create(ApiInterface.class);







        String stringFirst = "Our ";
        String stringSecond = "Terms&Conditions";

        SpannableString spannable = new SpannableString(stringFirst + stringSecond);
        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void updateDrawState(TextPaint ds) {
                super.updateDrawState(ds);
            }

            @Override
            public void onClick(@NotNull View widget) {
//                final FragmentManager fm = getSupportFragmentManager();
//            TermsConditions dialog = new TermsConditions();
//            dialog.show(fm,"TAG");

                Intent intent=new Intent(Registration.this,Terms.class);
                startActivity(intent);


            }
        };
        spannable.setSpan(clickableSpan, stringFirst.length(), stringFirst.length() + stringSecond.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        terms.setText(spannable);
        terms.setMovementMethod(LinkMovementMethod.getInstance());
        terms.setHighlightColor(ContextCompat.getColor(Registration.this, R.color.col10));



        username.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if(!username.getText().toString().equalsIgnoreCase("")){

                    IsUsernameAvailable(username.getText().toString());
                }


            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });






        regist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (TextUtils.isEmpty(fname.getText()))
                {
                    fname.requestFocus();
                    fname.setError("Field is Mandatory");
                }


                else if(TextUtils.isEmpty(contact_no.getText()))
                {
                    contact_no.requestFocus();
                    contact_no.setError("Field is Mandatory");
                }
                else if(TextUtils.isEmpty(email.getText()))
                {
                    email.requestFocus();
                    email.setError("Field is Mandatory");
                }

                else if(TextUtils.isEmpty  (username.getText()))
                {
                    username.requestFocus();
                    username.setError("Field is Mandatory");
                }
                else if(TextUtils.isEmpty  (password.getText()))
                {
                    password.requestFocus();
                    password.setError("Field is Mandatory");
                }
                else if(!password.getText().toString().equals(cnfmpassword.getText().toString()))
                {
                    cnfmpassword.requestFocus();
                    cnfmpassword.setError("Not Matching");
                }

                else {
                    if(Utility.isNetworkConnectionAvailable(Registration.this)){
                        RegisterUser();
                    }
                    else {
                        Utility.ShowCustomToast("No Network Available Check Your Internet Connectivity",Registration.this);
                    }

                }

            }
        });
    }
    public void forceCrash(View view) {
        throw new RuntimeException("This is a crash");
    }



    private void RegisterUser(){

        String custName =fname.getText().toString();
        String contactNO=contact_no.getText().toString();
        String Email=email.getText().toString();
        String SponsorId=sponsership_id.getText().toString();
        String UserName=username.getText().toString();
        String UserPwd=password.getText().toString();


        apiInterface.Register("0",custName,contactNO,Email,SponsorId,"3",UserName,UserPwd).enqueue(new Callback<RegisterResponse>() {
            @Override
            public void onResponse(Call<RegisterResponse> call, retrofit2.Response<RegisterResponse> response) {

                if (response.isSuccessful() && response.code() == 200) {
                    assert response.body() != null;
                    if (response.body().getCode().equalsIgnoreCase("0")) {
                        List<RegisterResponse.Result> result = response.body().getResults();
                        for (int i = 0; i < result.size(); i++) {

                            switch (result.get(i).getResult()) {
                                case "1":
                                    Utility.ShowCustomToast(" Registration Successful ",Registration.this);
                                    Intent intent = new Intent(Registration.this, Login.class);
                                    startActivity(intent);
                                    finish();
                                    break;

                                case "-1":
                                    Utility.ShowCustomToast(" Username Already Exist ",Registration.this);
                                    break;

                            }
                        }
                    }
                    else
                    {
                        Utility.ShowCustomToast(" Registration Failed ",Registration.this);

                    }
                }

                else if(response.code() == 401) {
                    Utility.ShowCustomToast("Authentication Failed ",Registration.this);
                    Log.e("Error  Codeeeeeeeeeeee","  "+response.code());
                }

                else if( response.code() == 500) {
                    Utility.ShowCustomToast("A Server Error has been Occurred",Registration.this);
                    Log.e("Error  Codeeeeeeeeeeee","  "+response.code());
                }

                else if(response.code() == 408) {
                    Utility.ShowCustomToast("A Network Error has been Occurred Check your Connectivity Settings",Registration.this);
                    Log.e("Error  Codeeeeeeeeeeee","  "+response.code());
                }

            }

            @Override
            public void onFailure(Call<RegisterResponse> call, Throwable t) {

                Utility.ShowCustomToast("A Network Error has been Occurred Check your Connectivity Settings",Registration.this);

            }
        });


    }



    private void IsUsernameAvailable(String Username){

        apiInterface.CheckUsername(Username).enqueue(new Callback<CheckUsernameResponse>() {
            @Override
            public void onResponse(Call<CheckUsernameResponse> call, retrofit2.Response<CheckUsernameResponse> response) {

                if (response.isSuccessful() && response.code() == 200) {
                    assert response.body() != null;
                    if (response.body().getCode().equalsIgnoreCase("0")) {

                        List<CheckUsernameResponse.ResultArray>result=response.body().getResponse();
                        for(int i=0;i<result.size();i++){

                            switch (result.get(i).getResult())
                            {
                                case "1":

                                    availability.setText("Not Available");
                                    availability.setTextColor(getResources().getColor(android.R.color.holo_red_light));
                                    regist.setClickable(false);
                                    regist.setFocusable(false);
                                    break;

                                case "2":
                                    availability.setText("Available");
                                    availability.setTextColor(getResources().getColor(android.R.color.holo_green_dark));
                                    regist.setClickable(true);
                                    regist.setFocusable(true);
                                    break;
                            }
                        }
                    }
                    else
                    {


                    }
                }

                else if(response.code() == 401) {

                    Log.e("Error  Codeeeeeeeeeeee","  "+response.code());
                }

                else if( response.code() == 500) {

                    Log.e("Error  Codeeeeeeeeeeee","  "+response.code());
                }

                else if(response.code() == 408) {

                    Log.e("Error  Codeeeeeeeeeeee","  "+response.code());
                }

            }

            @Override
            public void onFailure(Call<CheckUsernameResponse> call, Throwable t) {



            }
        });


    }



}