package com.example.publicmart;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.TextInputLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.ViewFlipper;

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
import java.util.Timer;
import java.util.TimerTask;

import io.fabric.sdk.android.Fabric;

import static io.fabric.sdk.android.services.concurrency.AsyncTask.init;

public class HouseBoat extends BaseActivity {
    String array_membrno[] = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};

    String array_day[] = {"DD", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10",
            "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30",
            "31"};
    String array_Mnth[] = {"MM", "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
    String array_Year[] = {"2019"};
    Spinner membersno, days, months, years;
///gggggg


    private static ViewPager mPager;
    private ArrayList<Integer> ImagesArray = new ArrayList<Integer>();
    private static int NUM_PAGES = 0;
    private static int currentPage = 0;

    TextInputLayout guest,bemail,bcontact;
    Button submit_boat;
    EditText passengr_name,email_id,Contact_no;
    String members,Day,Month,Year;
    JSONObject jsonString;
    RadioGroup radioGroup;
    RadioButton cruise;
    String request,code,message, radio;
   // DayCruise,NightCruise,FullDay
   String radiovalue;
    String Cruise_Type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FrameLayout contentFrameLayout = (FrameLayout) findViewById(R.id.content_frame);
        getLayoutInflater().inflate(R.layout.activity_house_boat_ed, contentFrameLayout);

        Fabric.with(this, new Crashlytics());
        mPager = (ViewPager) findViewById(R.id.viewflipper);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        membersno = findViewById(R.id.noofmembers);
        days = findViewById(R.id.day);
        months = findViewById(R.id.month);
        years = findViewById(R.id.year);
        submit_boat=(Button)findViewById(R.id.boat_submit);
        passengr_name=(EditText)findViewById(R.id.traveler_name);
        email_id=(EditText)findViewById(R.id.email);
        Contact_no=(EditText)findViewById(R.id.contact);
        radioGroup = findViewById(R.id.radiogrp);








//




        final ArrayAdapter<String> spinner_adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, array_membrno);
        spinner_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        membersno.setAdapter(spinner_adapter);

        final ArrayAdapter<String> spinner_adapter_day = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, array_day);
        spinner_adapter_day.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        days.setAdapter(spinner_adapter_day);

        final ArrayAdapter<String> spinner_adapter_month = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, array_Mnth);
        spinner_adapter_month.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        months.setAdapter(spinner_adapter_month);

        final ArrayAdapter<String> spinner_adapter_year = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, array_Year);
        spinner_adapter_year.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        years.setAdapter(spinner_adapter_year);


        membersno.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                members= adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        days.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Day= adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        months.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Month= adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        years.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Year = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

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

                    Cruise_Type = "D";

                }
                else  if (position==2)
                {
                    Cruise_Type = "N";
                }
                else if(position==2)
                {
                    Cruise_Type = "F";

                }

            }
        });


        init();


        guest = (TextInputLayout) findViewById(R.id.guest_input_layout);
        guest.setHint("Guest Name");
        bemail = (TextInputLayout) findViewById(R.id.bemail_input_layout);
        bemail.setHint("Enter Your Email ID");
        bcontact = (TextInputLayout) findViewById(R.id.bcontact_input_layout);
        bcontact.setHint("Enter Your Contact No");

        submit_boat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



        if (TextUtils.isEmpty(passengr_name.getText()))
        {
            passengr_name.setError("Field is Mandatory");
        }
        else if(TextUtils.isEmpty(email_id.getText()))
        {
            email_id.setError("Field is Mandatory");
        }
        else if(TextUtils.isEmpty(Contact_no.getText()))
        {
            Contact_no.setError("Field is Mandatory");
        }
        else
        {
            SharedPreferences sp = getSharedPreferences("UserLog",0);
            String CustKey =  sp.getString("UserKey",null);
            ////////////////////////////////////////////////////////////////////

            try {


                JSONObject values = new JSONObject();
                values.put("CustKey",Integer.parseInt(CustKey));
                values.put("PassengerName", passengr_name.getText().toString());
                values.put("TravelDate", Day + "-" + Month + "-" + Year);
                values.put("GuestNos", members);
              values.put("CruiseType", Cruise_Type);

                values.put("ContactEmail", email_id.getText().toString());
                values.put("ContactNo", Contact_no.getText().toString());
                values.put("BookingStatusKey", 1);
                values.put("Status", true);
                values.put("CreatedBy", Integer.parseInt(CustKey));
                jsonString = new JSONObject();
                jsonString.put("Token", "0001");
                jsonString.put("call", "SaveHouseboatBooking");
                jsonString.put("values", values);
                request = jsonString.toString();

            } catch (
                    JSONException e) {
                e.printStackTrace();
            }


            boat_ticket(request);


        }
                passengr_name.getText().clear();
                email_id.getText().clear();
                Contact_no.getText().clear();
                days.setSelection(0);
                months.setSelection(0);
                years.setSelection(0);
                membersno.setSelection(0);
        }


        });
    }



    public void forceCrash(View view) {
        throw new RuntimeException("This is a crash");
    }

//    private void init() {
//        for (int i = 0; i < IMAGES.length; i++)
//            ImagesArray.add(IMAGES[i]);
//        PagerAdapter adapter = new SlidingImage_Adapter(HouseBoat.this, ImagesArray);
//        mPager.setAdapter(adapter);
//        // mPager.setAdapter(new SlidingImage_Adapter(ProductView.this, ImagesArray));
//        final float density = getResources().getDisplayMetrics().xdpi;
//        NUM_PAGES = IMAGES.length;
//        // Auto start of viewpager
//        final Handler handler = new Handler();
//        final Runnable Update = new Runnable() {
//            public void run() {
//                if (currentPage == NUM_PAGES) {
//                    currentPage = 0;
//                }
//                mPager.setCurrentItem(currentPage++, true);
//            }
//        };
//        Timer swipeTimer = new Timer();
//        swipeTimer.schedule(new TimerTask() {
//            @Override
//            public void run() {
//                handler.post(Update);
//            }
//        }, 5000, 5000);
//    }

    private void boat_ticket(final String request)
    {

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
                                Toast.makeText(HouseBoat.this,"Success",Toast.LENGTH_LONG).show();
                            }

                            else {
                                Toast.makeText(HouseBoat.this,message,Toast.LENGTH_LONG).show();
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
        };

        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }




}

