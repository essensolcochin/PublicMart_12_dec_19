package com.essensol.publicmart;

import android.content.SharedPreferences;
import android.support.design.widget.TextInputLayout;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
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

public class FlightTicket extends BaseActivity{

    android.support.v7.widget.Toolbar toolbar;

    String array_day[] = {"DD", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10",
            "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30",
            "31"};
    String array_Mnth[] = {"MM", "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
    String array_Year[] = {"2019"};
    String array_BYear[] = {"YY", "1985", "1986", "1986", "1987", "1988", "1989", "1990", "1991", "1992",
            "1993", "1994", "1995", "1996", "1997", "1998", "1999", "2000", "2001", "2002"
            , "2003", "2004", "2005", "2006", "2007", "2008", "2009", "2010", "2011", "2012",
            "2011", "2012", "2013", "2014", "2015", "2016", "2017", "2018"};

    Spinner citycode,citycode2,days,months,years, bday,bmonth,byear;
    String Bday,Bmonth,Byear,Day,Month,Year;
    Integer codecity,codecity2;
//    RadioGroup rg;
    RadioButton radioButton;
    Button submit_flight;
    EditText traveler_name,email_id,contact_no;
    String AirportName,ShortCode;
    Integer AirportKey;
    ArrayAdapter<AirportModel> airport_adapter;
    String Timing;
    int selectedRadioGroupId = 0;

    ArrayList <AirportModel> names ;

    private static ViewPager mPager;
    private static int currentPage = 0;
    private static int NUM_PAGES = 0;
    private static final Integer[] IMAGES = {R.drawable.flightbanner};


    ArrayList<Integer> TextArray = new ArrayList<Integer>();
    TextInputLayout name,email,contact;
    TextView txtxmpny;

    JSONObject jsonString;
    String request,code,message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FrameLayout contentFrameLayout = (FrameLayout) findViewById(R.id.content_frame);
        getLayoutInflater().inflate(R.layout.activity_flight_ticket, contentFrameLayout);
        Fabric.with(this, new Crashlytics());
        name = (TextInputLayout) findViewById(R.id.text_input_layout);
        name.setHint("Enter Passenger Name");

        email = (TextInputLayout) findViewById(R.id.email_input_layout);
        email.setHint("Enter Passenger Email ID");

        contact = (TextInputLayout) findViewById(R.id.contact_input_layout);
        contact.setHint("Enter Passenger Contact No");


        toolbar =  findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        android.support.v7.widget.Toolbar tb=getToolBar();
        txtxmpny=(TextView)tb.findViewById(R.id.appname);
        txtxmpny.setText("Flight Booking");

//        rg = (RadioGroup)findViewById(R.id.radiogrp);


        citycode = findViewById(R.id.city);
        citycode2 = findViewById(R.id.city2);
        days = findViewById(R.id.day);
        months = findViewById(R.id.month);
        years = findViewById(R.id.year);
        bday = findViewById(R.id.bday);
        bmonth = findViewById(R.id.bmonth);
        byear = findViewById(R.id.byear);
        traveler_name=(EditText)findViewById(R.id.traveler_name);
        email_id=(EditText)findViewById(R.id.email);
        contact_no=(EditText)findViewById(R.id.contact);



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

        final ArrayAdapter<String> spinner_adapter_bday = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, array_day);
        spinner_adapter_bday.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        bday.setAdapter(spinner_adapter_bday);
        final ArrayAdapter<String> spinner_adapter_mbday = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, array_Mnth);
        spinner_adapter_mbday.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        bmonth.setAdapter(spinner_adapter_mbday);

        final ArrayAdapter<String> spinner_adapter_yday = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, array_BYear);
        spinner_adapter_yday.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        byear.setAdapter(spinner_adapter_yday);


        citycode.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                codecity = names.get(i).getAirportKey();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        citycode2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                codecity2=names.get(i).getAirportKey();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        bday.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Bday = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        bmonth.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Bmonth = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        byear.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Byear = adapterView.getItemAtPosition(i).toString();
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

//        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(RadioGroup radioGroup, int i) {
//                int radioButtonID = radioGroup.getCheckedRadioButtonId();
//                View radioButton = radioGroup.findViewById(radioButtonID);
//                int position = radioGroup.indexOfChild(radioButton);
//                Log.e("radio",""+position);
//                Log.e("radioid",""+radioButtonID);
//
//                if (position==0)
//
//                    {
//
//                     Timing = "M";
//
//                     }
//                     else  if (position==1)
//                       {
//                      Timing = "E";
//                        }
//                     else if(position==2)
//                         {
//                                   Timing = "N";
//
//                         }
//
//            }
//        });


        airportcode();



//       init();

       submit_flight = (Button)findViewById(R.id.fsubmit);
       submit_flight.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {


               if (TextUtils.isEmpty(traveler_name.getText()))
               {
                   traveler_name.setError("Field is Mandatory");
               }
               else if(TextUtils.isEmpty(email_id.getText()))
               {
                   email_id.setError("Field is Mandatory");
               }
               else if(TextUtils.isEmpty(contact_no.getText()))
               {
                   contact_no.setError("Field is Mandatory");
               }
        else
               {


                   SharedPreferences sp = getSharedPreferences("UserLog",0);
                   String CustKey =  sp.getString("CustKey",null);
                   String UserKey =  sp.getString("UserKey",null);


//hhhhhhhh

               try {


               JSONObject values = new JSONObject();
               values.put("CustKey",Integer.parseInt(CustKey));
               values.put("PassengerName", traveler_name.getText().toString());
                   values.put("DOB", Bday+"-"+Bmonth+"-"+Byear);
               values.put("FromAirportKey", codecity);
               values.put("ToAirportKey", codecity2);
               values.put("TravelDate", Day+"-"+Month+"-"+Year);
               values.put("Timing", Timing);

               values.put("ContactEmail", email_id.getText().toString());
               values.put("ContactNo", contact_no.getText().toString());
               values.put("BookingStatusKey", 1);
               values.put("Status", true);
               values.put("CreatedBy",Integer.parseInt(UserKey));

               jsonString = new JSONObject();
               jsonString.put("Token", "0001");
               jsonString.put("call", "SaveFlightBooking");
               jsonString.put("values", values);
               request = jsonString.toString();
                   Log.e("timinggggg",""+Timing);
           } catch (
           JSONException e) {
               e.printStackTrace();
           }


                   if(Utility.isNetworkConnectionAvailable(FlightTicket.this)){
                       book_ticket(request);
                   }
                   else {
                       Utility.ShowCustomToast("No Network Available Check Your Internet Connectivity",FlightTicket.this);
                   }







               }


               }
//
       });



    }

    public void forceCrash(View view) {
        throw new RuntimeException("This is a crash");
    }




//    private void init() {
//
//
//        for (int i = 0; i < IMAGES.length; i++)
//            TextArray.add(IMAGES[i]);
//        mPager = findViewById(R.id.viewflipper1);
//
//        PagerAdapter adapter = new SlidingText_Adapter_Flight(FlightTicket.this, TextArray);
//        mPager.setAdapter(adapter);
//        Log.e("textsizeeeee","flight "+TextArray.size());
//        NUM_PAGES = IMAGES.length;
//        // Auto start of viewpager
//        final Handler handler = new Handler();
//        final Runnable Update = new Runnable() {
//            public void run() {
//                if (currentPage == NUM_PAGES) {
//
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
//        }, 2000, 5000);
//
//    }





    private void book_ticket(final String request)
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

                                traveler_name .getText().clear();
                                email_id .getText().clear();
                                contact_no.getText().clear();
                                citycode.setSelection(0);
                                citycode2.setSelection(0);
                                days.setSelection(0);
                                months.setSelection(0);
                                years.setSelection(0);
                                bday.setSelection(0);
                                bmonth.setSelection(0);
                                byear.setSelection(0);

                                Utility.ShowCustomToast("Booking Successful",FlightTicket.this);
                            }


                            else {
                                Utility.ShowCustomToast("Booking Failed",FlightTicket.this);
                            }




                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }



                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), "Some Error Occurred ", Toast.LENGTH_SHORT).show();

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


    ////// Adding Airport Code To Spinner /////////

    private void airportcode() {
        names =new ArrayList<AirportModel>();

        try {
            JSONObject values = new JSONObject();

            jsonString = new JSONObject();
            jsonString.put("Token", "0001");
            jsonString.put("call", "GetActiveAirports");
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

                                    AirportKey=jsonObject.getInt("AirportKey");
                                    ShortCode=jsonObject.getString("ShortCode");
                                    AirportName=jsonObject.getString("AirportName");
                                    Log.e("teeessst","ifffff  "+AirportKey);

                                    AirportModel items  =new AirportModel(AirportKey,ShortCode,AirportName);

                                    names.add(items);
                                    Log.e("from jsonnnn", "  " + jsonObject.getString("AirportName"));
                                    Log.e("namessssss", "  " + names);

                                }


                                airport_adapter = new ArrayAdapter<AirportModel>(FlightTicket.this,android.R.layout.simple_spinner_dropdown_item,names);
                                airport_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                citycode.setAdapter(airport_adapter);
                                citycode2.setAdapter(airport_adapter);


                            }


                            else {
                                Toast.makeText(FlightTicket.this,message,Toast.LENGTH_LONG).show();
                            }




                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), "Some Error Occurred ", Toast.LENGTH_SHORT).show();

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
        } ;
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                0,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        // Volley.getInstance(this).addToRequestQueue(stringRequest);
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);


        Log.e("statecodeeeeeee",""+ AirportKey);



    }


}
