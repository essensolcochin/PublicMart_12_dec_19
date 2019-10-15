package com.publicmart.essensol.Activities;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
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

import com.crashlytics.android.Crashlytics;

import com.publicmart.essensol.R;
import com.publicmart.essensol.RetrofitUtils.ApiClient;
import com.publicmart.essensol.RetrofitUtils.ApiInterface;
import com.publicmart.essensol.RetrofitUtils.RetrofitResponseClasses.SaveHouseBoatBookingResponse;
import com.publicmart.essensol.Utility;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import io.fabric.sdk.android.Fabric;
import retrofit2.Call;
import retrofit2.Callback;

public class HouseBoat extends BaseActivity {
    String array_membrno[] = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};

    String array_day[] = {"DD", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10",
            "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30",
            "31"};
    String array_Mnth[] = {"MM", "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
    String array_Year[] = {"2019"};
    Spinner membersno, days, months, years;


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
    ImageView image;
    String request,code,message, radio;
   // DayCruise,NightCruise,FullDay
    String radiovalue;
    String Cruise_Type ="D";
    ApiInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FrameLayout contentFrameLayout = (FrameLayout) findViewById(R.id.content_frame);
        getLayoutInflater().inflate(R.layout.activity_house_boat_ed, contentFrameLayout);

        Fabric.with(this, new Crashlytics());
       // mPager = (ViewPager) findViewById(R.id.viewflipper);
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

        apiInterface = ApiClient.getClient().create(ApiInterface.class);





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

                if (position==0)

                {

                    Cruise_Type = "D";

                }

                else if(position==1)
                {
                    Cruise_Type = "F";

                }

            }
        });


//        init();


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


        else if(! Patterns.EMAIL_ADDRESS.matcher(email_id.getText().toString()).matches())

        {
            email_id.requestFocus();
            email_id.setError("Invalid Email ID");
        }


        else if(TextUtils.isEmpty(Contact_no.getText()))
        {
            Contact_no.setError("Field is Mandatory");
        }
        else if (Contact_no.getText().toString().trim().length()<10||Contact_no.getText().toString().trim().length()>12) {
            Contact_no.setError("Invalid Mobile no");

        }

//        else if(TextUtils.isEmpty(Contact_no.getText()))
//        {
//            Contact_no.setError("Field is Mandatory");
//        }

        else if(Day.equals("DD")||Month.equals("MM")||Year.equals("YYYY"))
        {
            Utility.ShowCustomToast("Please Specify the Dates",HouseBoat.this);
        }

        else if(members.equals("0"))
        {
            Utility.ShowCustomToast("Please Specify Guest number",HouseBoat.this);
        }

        else
        {
//            SharedPreferences sp = getSharedPreferences("UserLog",0);
//            String CustKey =  sp.getString("CustKey",null);
//            String UserKey =  sp.getString("UserKey",null);
            ////////////////////////////////////////////////////////////////////

//            try {
//
//
//                JSONObject values = new JSONObject();
//                values.put("CustKey",Integer.parseInt(CustKey));
//                values.put("PassengerName", passengr_name.getText().toString());
//                values.put("TravelDate", Day + "-" + Month + "-" + Year);
//                values.put("GuestNos", members);
//              values.put("CruiseType", Cruise_Type);
//
//                values.put("ContactEmail", email_id.getText().toString());
//                values.put("ContactNo", Contact_no.getText().toString());
//                values.put("BookingStatusKey", 1);
//                values.put("Status", true);
//                values.put("CreatedBy", Integer.parseInt(UserKey));
//                jsonString = new JSONObject();
//                jsonString.put("Token", "0001");
//                jsonString.put("call", "SaveHouseboatBooking");
//                jsonString.put("values", values);
//                request = jsonString.toString();
//
//            } catch (
//                    JSONException e) {
//                e.printStackTrace();
//            }


            BookTickets();


        }

        }


        });
    }



    public void forceCrash(View view) {
        throw new RuntimeException("This is a crash");
    }






    private  void  BookTickets() {




        SharedPreferences sp = getSharedPreferences("UserLog",0);
        String CustKey =  sp.getString("CustKey",null);
        String UserKey =  sp.getString("UserKey",null);

        String Pname  =passengr_name.getText().toString();

        Log.e("Error  Pname","  "+Pname);

        Log.e("Error  CustKey","  "+CustKey);

        Log.e("Error  Cruise_Type","  "+Cruise_Type);

        Log.e("Error  members","  "+members);

        Log.e("Error  UserKey","  "+UserKey);

        String TravelDate  =Day+"-"+Month+"-"+Year;

        Log.e("Error  UserKey","  "+TravelDate);

        String email  =email_id.getText().toString();

        String contact  =Contact_no.getText().toString();

        Log.e("Error  email","  "+email);

        Log.e("Error  contact","  "+contact);


        apiInterface.SaveHouseBoatBooking(CustKey,Pname,TravelDate,Cruise_Type,email,contact,members,1,true,UserKey)
                .enqueue(new Callback<SaveHouseBoatBookingResponse>() {
                    @Override
                    public void onResponse(Call<SaveHouseBoatBookingResponse> call, retrofit2.Response<SaveHouseBoatBookingResponse> response) {

                        if (response.isSuccessful() && response.code() == 200) {
                            assert response.body() != null;
                            if (response.body().getCode().equalsIgnoreCase("0")) {

                                List<SaveHouseBoatBookingResponse.ResultArray> result = response.body().getResponse();



                                for (int i = 0; i < result.size(); i++) {

                                    if(result.get(i).getResult().equalsIgnoreCase("1"))
                                    {
                                        passengr_name .getText().clear();
                                        email_id .getText().clear();
                                        Contact_no.getText().clear();

                                        days.setSelection(0);
                                        months.setSelection(0);
                                        years.setSelection(0);


                                        Utility.ShowCustomToast("Booking Successful",HouseBoat.this);
                                    }
                                    else
                                    {
                                        Utility.ShowCustomToast("Booking Failed",HouseBoat.this);

                                    }


                                }






                            }
//                    else
//                    {
////                        progress.cancel();
////                        Utility.ShowCustomToast("Coming Soon",Products.this);
//
//                    }
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
                    public void onFailure(Call<SaveHouseBoatBookingResponse> call, Throwable t) {

                    }
                });


    }



}

