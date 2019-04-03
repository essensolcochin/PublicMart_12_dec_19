package com.example.publicmart;

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
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
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

    String array_day[] = {"DD", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10",
            "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30",
            "31"};

    String array_Mnth[] = {"MM", "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};

    String array_BYear[] = {"YY","1985","1986","1986","1987","1988","1989","1990","1991","1992",
            "1993","1994", "1995", "1996","1997","1998", "1999","2000","2001","2002"
            ,"2003","2004","2005","2006","2007","2008","2009","2010","2011","2012",
            "2011","2012", "2013","2014","2015","2016","2017","2018"} ;

    String Bday,Bmonth,Byear,StateName;
    Integer StateCode, StateKey;

    ArrayAdapter<SpinnerModel> spinner_adapter;
    ArrayList <SpinnerModel> names ;//add names in this list

    Spinner state,Day,Month,Year;
    JSONObject jsonString;
    String request,code,message;
    RadioGroup radioGroup;
    Integer Membership_Type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        Fabric.with(this, new Crashlytics());
        regist = (LinearLayout)findViewById(R.id.submit);

        fname = (EditText)findViewById(R.id.fname);
        mname = (EditText)findViewById(R.id.Mname);
        lname = (EditText)findViewById(R.id.lname);


        Day = (Spinner) findViewById(R.id.day);
        Month = (Spinner) findViewById(R.id.Bmonth);
        Year = (Spinner) findViewById(R.id.Byear);
        radioGroup = findViewById(R.id.membershipgrp);
        house_no = (EditText)findViewById(R.id.Hno);
        tehsil = (EditText)findViewById(R.id.tehsil);
        village = (EditText)findViewById(R.id.village);
        district = (EditText)findViewById(R.id.district);   //
        pincode = (EditText)findViewById(R.id.pin);
        contact_no = (EditText)findViewById(R.id.contactno);
        email = (EditText)findViewById(R.id.email);
        sponsership_id = (EditText)findViewById(R.id.sponcerid);
        nominee = (EditText)findViewById(R.id.nominee);
        relationship = (EditText)findViewById(R.id.Relation);
        username = (EditText)findViewById(R.id.username);
        password = (EditText)findViewById(R.id.password);
        cnfmpassword = (EditText)findViewById(R.id.Confirmpasswd);
        state = (Spinner)findViewById(R.id.state);




        getCodes();
        names = new ArrayList<SpinnerModel>();

        Log.e("getcodeeeeee","test   "+names.size());

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                int radioButtonID = radioGroup.getCheckedRadioButtonId();
                View radioButton = radioGroup.findViewById(radioButtonID);
                int position = radioGroup.indexOfChild(radioButton);
                Log.e("radio",""+position);
                Log.e("radioid",""+radioButtonID);

                if (position==1)

                {

                    Membership_Type = 1;

                }
                else  if (position==2)
                {
                    Membership_Type = 2;
                }
                else if(position==3)
                {
                    Membership_Type = 3;

                }

            }
        });







        ArrayAdapter<String> spinner_adapterDay = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, array_day);
        spinner_adapterDay.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Day.setAdapter(spinner_adapterDay);

        ArrayAdapter<String> spinner_adapterMonth = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, array_Mnth);
        spinner_adapterMonth.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Month.setAdapter(spinner_adapterMonth);

        ArrayAdapter<String> spinner_adapterYear = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, array_BYear);
        spinner_adapterYear.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Year.setAdapter(spinner_adapterYear);



        Day.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Bday = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        Month.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Bmonth = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        Year.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Byear = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

//        ArrayAdapter<SpinnerModel> spinner_adapter = new ArrayAdapter<SpinnerModel>(this,
//                android.R.layout.simple_spinner_item, names);
//        spinner_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        state.setAdapter(spinner_adapter);

        state.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


               // Toast.makeText(getApplication(), ""+names.get(position).getStateCode(),Toast.LENGTH_LONG).show();
                StateKey = names.get(position).getStateCode();
                Log.e("codeeeeestaeeeeeee","test   "+ StateKey);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



        regist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(fname.getText()))
                {
                    fname.setFocusable(true);
                    fname.setError("Field is Mandatory");
                }

                 if(TextUtils.isEmpty(lname.getText()))
                {
                    lname.setError("Field is Mandatory");
                }
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
                 if(TextUtils.isEmpty(pincode.getText()))
                {
                    pincode.setError("Field is Mandatory");
                }
                 if(TextUtils.isEmpty(contact_no.getText()))
                {
                    contact_no.setError("Field is Mandatory");
                }
                 if(TextUtils.isEmpty(email.getText()))
                {
                    ////dddd
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
                 if(TextUtils.isEmpty  (username.getText()))
                {
                    username.setError("Field is Mandatory");
                }
                 if(TextUtils.isEmpty  (password.getText()))
                {
                    password.setError("Field is Mandatory");
                }
                 if(!password.getText().toString().equals(cnfmpassword.getText().toString()))
                {
                    cnfmpassword.setError("Not Matching");
                }

                else {

                try {

                    JSONObject values = new JSONObject();
                    values.put("CustKey", 0);
                    values.put("FName", fname.getText().toString());
                    values.put("MName", mname.getText().toString());
                    values.put("LName", lname.getText().toString());
                    values.put("DOB", Bday+"-"+Bmonth+"-"+Byear);
                    values.put("HouseNo", house_no.getText().toString());
                    values.put("Tehsil", tehsil.getText().toString());
                    values.put("Village", village.getText().toString());
                    values.put("District", district.getText().toString());
                    values.put("StateKey", StateKey);
                    values.put("PinNo", pincode.getText().toString());
                    values.put("MobileNo", contact_no.getText().toString());
                    values.put("AlterMobileNo","");
                    values.put("Email", email.getText().toString());
                    values.put("BankKey", 0);
                    values.put("BranchName", "");
                    values.put("AccountNo", "");
                    values.put("IFSCCode", "");
                    values.put("PanNo", "");
                    values.put("Nominee", nominee.getText().toString());
                    values.put("Relationship", relationship.getText().toString());
                    values.put("SponsorId", sponsership_id.getText());
                    values.put("MSTypeKey", Membership_Type);
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





                    Register(request);
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
                        Toast.makeText(getApplicationContext(), "No Response From Server ", Toast.LENGTH_LONG).show();

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



    private void getCodes() {


        try {
            JSONObject values = new JSONObject();

            jsonString = new JSONObject();
            jsonString.put("Token", "0001");
            jsonString.put("call", "GetActiveStates");
            jsonString.put("values", values);
        } catch (JSONException e) {
            e.printStackTrace();
        }


        Log.e("gettttt","in"+request);



        String URL = this.getString(R.string.Url)+"Select";


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

                            ;
                            code = o.getString("responseCode");
                            message=o.getString("responseMessage");

                            Log.e("resppppppp",""+code);


                            if (code.equalsIgnoreCase("0"))
                            {


                                Log.e("resppppppp","ifffff"+code);

                                JSONArray json_array2 = o.getJSONArray("result");


                                JSONObject jsonObject;


                                int j;
                                for (j = 0; j < json_array2.length(); j++) {
                                    jsonObject = json_array2.getJSONObject(j);

                                    StateName=jsonObject.getString("StateName");
                                    StateCode=jsonObject.getInt("StateKey");
                                    SpinnerModel items  =new SpinnerModel(StateCode, StateName);
                                    names.add(items);
                                    Log.e("from jsonnnn", "  " + jsonObject.getString("StateName"));
                                    Log.e("namessssss", "  " + names);


                                }


                                spinner_adapter = new ArrayAdapter<SpinnerModel>(Registration.this,android.R.layout.simple_spinner_dropdown_item,names);
                                spinner_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                state.setAdapter(spinner_adapter);





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
                        Toast.makeText(getApplicationContext(), "No Response From Server ", Toast.LENGTH_LONG).show();

                    }
                }) {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> param = new HashMap<String, String>();
                param.put("jsonString",jsonString.toString() );
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

        // Volley.getInstance(this).addToRequestQueue(stringRequest);
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);


        Log.e("statecodeeeeeee",""+ StateKey);



    }






}