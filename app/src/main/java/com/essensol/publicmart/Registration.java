package com.essensol.publicmart;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.crashlytics.android.Crashlytics;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import io.fabric.sdk.android.Fabric;

public class Registration extends AppCompatActivity {
    LinearLayout regist;
    EditText fname,mname,lname,house_no,tehsil,village,district,pincode,contact_no,email,sponsership_id,nominee,relationship,username,password,cnfmpassword;

    String statecode[] = {"-Select State Code-","01", "02", "03", "04", "05", "06", "07", "08", "09", "10"};



    String Bday,Bmonth,Byear,StateName;
    Integer StateCode, StateKey;

    ArrayAdapter<SpinnerModel> spinner_adapter;
    ArrayList <SpinnerModel> names ;//add names in this list

//    Spinner state,Day,Month,Year;
    JSONObject jsonString;
    String request,code,message;
    RadioGroup radioGroup;
    Integer Membership_Type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_ed);

        Fabric.with(this, new Crashlytics());
        regist = (LinearLayout)findViewById(R.id.submit);

        fname = (EditText)findViewById(R.id.fname);
//        mname = (EditText)findViewById(R.id.Mname);
//        lname = (EditText)findViewById(R.id.lname);


//        Day = (Spinner) findViewById(R.id.day);
//        Month = (Spinner) findViewById(R.id.Bmonth);
//        Year = (Spinner) findViewById(R.id.Byear);
//        radioGroup = findViewById(R.id.membershipgrp);
//        house_no = (EditText)findViewById(R.id.Hno);
        tehsil = (EditText)findViewById(R.id.tehsil);
        village = (EditText)findViewById(R.id.village);
        district = (EditText)findViewById(R.id.district);   //
        pincode = (EditText)findViewById(R.id.pin);
        contact_no = (EditText)findViewById(R.id.contactno);
        email = (EditText)findViewById(R.id.email);
        sponsership_id = (EditText)findViewById(R.id.sponcerid);
        nominee = (EditText)findViewById(R.id.nominee);
//        relationship = (EditText)findViewById(R.id.Relation);
        username = (EditText)findViewById(R.id.username);
        password = (EditText)findViewById(R.id.password);
        cnfmpassword = (EditText)findViewById(R.id.Confirmpasswd);
//        state = (Spinner)findViewById(R.id.state);




       // getCodes();
        names = new ArrayList<SpinnerModel>();

        Log.e("getcodeeeeee","test   "+names.size());

//        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(RadioGroup radioGroup, int i) {
//                int radioButtonID = radioGroup.getCheckedRadioButtonId();
//                View radioButton = radioGroup.findViewById(radioButtonID);
//                int position = radioGroup.indexOfChild(radioButton);
//                Log.e("radio",""+position);
//                Log.e("radioid",""+radioButtonID);
//
//                switch (position) {
//
//                    case 0:
//                        Log.e("positionnnn", " " + position);
//                        Membership_Type = 1;
//                        Log.e("positionnnn", "Membership_Type" + Membership_Type);
//
//                        break;
//
//                    case 1:
//
//                        Log.e("positionnnn", " " + position);
//                        Membership_Type = 2;
//                        Log.e("positionnnn", "Membership_Type" + Membership_Type);
//                        break;
//                    case 2:
//
//                        Log.e("positionnnn", " " + position);
//                        Membership_Type = 3;
//                        Log.e("positionnnn", "Membership_Type" + Membership_Type);
//                        break;
//                        default:
//                            break;
//                }
//
//            }
//        });







//
////        ArrayAdapter<SpinnerModel> spinner_adapter = new ArrayAdapter<SpinnerModel>(this,
////                android.R.layout.simple_spinner_item, names);
////        spinner_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
////        state.setAdapter(spinner_adapter);
//
//        state.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//
//
//               // Toast.makeText(getApplication(), ""+names.get(position).getStateCode(),Toast.LENGTH_LONG).show();
//                StateKey = names.get(position).getStateCode();
//                Log.e("codeeeeestaeeeeeee","test   "+ StateKey);
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });



        regist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (TextUtils.isEmpty(fname.getText()))
                {
                    fname.requestFocus();
                    fname.setError("Field is Mandatory");
                }

//                else if(TextUtils.isEmpty(lname.getText()))
//                {
//                    lname.requestFocus();
//                    lname.setError("Field is Mandatory");
//                }
//                 if(TextUtils.isEmpty(house_no.getText()))
//                {
//                    house_no.setError("Field is Mandatory");
//                }
//                 if(TextUtils.isEmpty(tehsil.getText()))
//                {
//                    tehsil.setError("Field is Mandatory");
//                }

//                 if(TextUtils.isEmpty(district.getText()))
//                {
//                    district.setError("Field is Mandatory");
//                }
//                else  if(TextUtils.isEmpty(pincode.getText()))
//                {
//                    pincode.requestFocus();
//                    pincode.setError("Field is Mandatory");
//                }
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
//                 if(TextUtils.isEmpty(sponsership_id.getText()))
//                {
//                    sponsership_id.setError("Field is Mandatory");
//                }
//                 if(TextUtils.isEmpty  (nominee.getText()))
//                {
//                    nominee.setError("Field is Mandatory");
//
//                }
//                 if(TextUtils.isEmpty  (relationship.getText()))
//                {
//                    relationship.setError("Field is Mandatory");
//                }
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

                try {

                    JSONObject values = new JSONObject();
                    values.put("CustKey", 0);
                    values.put("CustomerName", fname.getText().toString());

                    values.put("MobileNo", contact_no.getText().toString());

                    values.put("Email", email.getText().toString());

                    values.put("SponsorId", sponsership_id.getText());
                    values.put("MSTypeKey", 3);
                    values.put("Status", true);
                    values.put("CreatedBy", 0);
//                    values.put("ReturnValue", 0);
                    values.put("UserName", username.getText().toString());
                    values.put("UserPwd", password.getText().toString());
                    Log.e("testtttttt","in"+password.getText().toString());
                    jsonString = new JSONObject();
                    jsonString.put("Token", "0001");
                    jsonString.put("call", "SaveUpdateCustomerMaster");
                    jsonString.put("values", values);
                    request = jsonString.toString();

                } catch (
                        JSONException e) {
                    e.printStackTrace();
                }



                    if(Utility.isNetworkConnectionAvailable(Registration.this)){
                        Register(request);
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



    private void Register(final String request) {


        Log.e("gettttt","in"+request);



        String URL = this.getString(R.string.Url)+"Save";


        StringRequest stringRequest=new StringRequest(Request.Method.POST, URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Log.e("Jsonnnn",""+response);
                        // p1.dismiss();

                        try {


                            JSONObject o     = new JSONObject(response);


                            String data = response;
                            Object json = new JSONTokener(data).nextValue();
                            if (json instanceof JSONObject){
                                Log.e("objectttttt",""+json);
                            }
                            //you have an object
                            else if (json instanceof JSONArray){
                                Log.e("Arrayyyyyyy",""+json);
                            }


                            Log.e("tryyyyyyyyy","in"+o);


                            code = o.getString("responseCode");
                            message=o.getString("responseMessage");

                            Log.e("resppppppp",""+code);


                            if (code.equalsIgnoreCase("-100"))
                            {

                                Log.e("resppppppp","ifffff"+code);

                                Intent intent =new Intent(Registration.this,MainActivity.class);
                                startActivity(intent);
                                finish();

                            }


                            else {
                                Toast.makeText(Registration.this,message,Toast.LENGTH_LONG).show();
                            }




                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }



                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        if (error instanceof TimeoutError || error instanceof NoConnectionError) {
                            Utility.ShowCustomToast(" No Network Connection",Registration.this);
                        } else if (error instanceof AuthFailureError) {
                            Utility.ShowCustomToast("Authentication Failed",Registration.this);
                        } else if (error instanceof ServerError) {

                            Utility.ShowCustomToast("Server Error Occurred",Registration.this);
                        } else if (error instanceof NetworkError) {

                            Utility.ShowCustomToast("Some Network Error Occurred",Registration.this);
                        } else if (error instanceof ParseError) {

                            Utility.ShowCustomToast("Some Error Occurred",Registration.this);
                        }

                    }
                }) {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> param = new HashMap<String, String>();
                param.put("jsonString",request );
                Log.e("paramssss",""+param);
                return param;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String,String> param = new HashMap<String, String>();
                param.put("Content-Type","application/x-www-form-urlencoded");
                return param;
            }
        }
                ;

        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }



//    private void getCodes() {
//
//
//        try {
//            JSONObject values = new JSONObject();
//
//            jsonString = new JSONObject();
//            jsonString.put("Token", "0001");
//            jsonString.put("call", "GetActiveStates");
//            jsonString.put("values", values);
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//
//
//        Log.e("gettttt","in"+request);
//
//
//
//        String URL = this.getString(R.string.Url)+"Select";
//
//
//        StringRequest stringRequest=new StringRequest(Request.Method.POST, URL,
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//
//                        Log.e("Jsonnnn",""+response);
//                        // p1.dismiss();
//
//                        try {
//
//
//                            JSONObject o     = new JSONObject(response);
//
//
//                            String data = response;
//                            Object json = new JSONTokener(data).nextValue();
//                            if (json instanceof JSONObject){
//                                Log.e("objectttttt",""+json);
//                            }
//                            //you have an object
//                            else if (json instanceof JSONArray){
//                                Log.e("Arrayyyyyyy",""+json);
//                            }
//
//
//                            Log.e("tryyyyyyyyy","in"+o);
//
//                            ;
//                            code = o.getString("responseCode");
//                            message=o.getString("responseMessage");
//
//                            Log.e("resppppppp",""+code);
//
//
//                            if (code.equalsIgnoreCase("0"))
//                            {
//
//
//                                Log.e("resppppppp","ifffff"+code);
//
//                                JSONArray json_array2 = o.getJSONArray("result");
//
//
//                                JSONObject jsonObject;
//
//
//                                int j;
//                                for (j = 0; j < json_array2.length(); j++) {
//                                    jsonObject = json_array2.getJSONObject(j);
//
//                                    StateName=jsonObject.getString("StateName");
//                                    StateCode=jsonObject.getInt("StateKey");
//                                    SpinnerModel items  =new SpinnerModel(StateCode, StateName);
//                                    names.add(items);
//                                    Log.e("from jsonnnn", "  " + jsonObject.getString("StateName"));
//                                    Log.e("namessssss", "  " + names);
//
//
//                                }
//
//
//                                spinner_adapter = new ArrayAdapter<SpinnerModel>(Registration.this,android.R.layout.simple_spinner_dropdown_item,names);
//                                spinner_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//                                state.setAdapter(spinner_adapter);
//
//
//
//
//
//                            }
//
//
//                            else {
//                                Toast.makeText(Registration.this,message,Toast.LENGTH_LONG).show();
//                            }
//
//
//
//
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//                    }
//
//                },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        Toast.makeText(getApplicationContext(), "Some Error Occurred ", Toast.LENGTH_SHORT).show();
//
//                    }
//                }) {
//
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//                Map<String, String> param = new HashMap<String, String>();
//                param.put("jsonString",jsonString.toString() );
//                Log.e("paramssss",""+param);
//                return param;
//            }
//
//            @Override
//            public Map<String, String> getHeaders() throws AuthFailureError {
//                Map<String,String> param = new HashMap<String, String>();
//                param.put("Content-Type","application/x-www-form-urlencoded");
//                return param;
//            }
//        }
//                ;
//
//        // Volley.getInstance(this).addToRequestQueue(stringRequest);
//        RequestQueue requestQueue= Volley.newRequestQueue(this);
//        requestQueue.add(stringRequest);
//
//
//        Log.e("statecodeeeeeee",""+ StateKey);
//
//
//
//    }






}