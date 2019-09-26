package com.publicmart.essensol.Activities;

import android.content.Intent;
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
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
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
import com.publicmart.essensol.ModelClasses.StationModel;
import com.publicmart.essensol.R;

import com.publicmart.essensol.RetrofitUtils.ApiClient;
import com.publicmart.essensol.RetrofitUtils.ApiInterface;
import com.publicmart.essensol.RetrofitUtils.RetrofitResponseClasses.GetStationCodesResponse;
import com.publicmart.essensol.RetrofitUtils.RetrofitResponseClasses.SaveTrainBookingResponse;
import com.publicmart.essensol.Utility;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.fabric.sdk.android.Fabric;
import retrofit2.Call;
import retrofit2.Callback;

public class TrainTicket extends BaseActivity {

    String array_day[] = {"DD", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10",
            "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30",
            "31"};
    String array_Mnth[] = {"MM", "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
    String[] array_Year = {"2019"};
    String array_BYear[] = {"YY", "1985", "1986", "1986", "1987", "1988", "1989", "1990", "1991", "1992",
            "1993", "1994", "1995", "1996", "1997", "1998", "1999", "2000", "2001", "2002"
            , "2003", "2004", "2005", "2006", "2007", "2008", "2009", "2010", "2011", "2012",
            "2011", "2012", "2013", "2014", "2015", "2016", "2017", "2018"};

    Spinner citycode,citycode2,days,months,years, bday,bmonth,byear;

    TextView header1,header2,header3,header4,header5,header6,header7;
    Button submit_ticket;
    EditText passengr_name,email_id,Contact_no,age;
    private static ViewPager mPager;
    private static int currentPage = 0;
    private static int NUM_PAGES = 0;
    private static final Integer[] IMAGES = {R.drawable.trainad};
    private ArrayList<Integer> TextArray = new ArrayList<Integer>();
    TextInputLayout trname,tremail,trcontact;
    Integer codecity,codecity2;
    String StationName,ShortCode;
    Integer StationKey;
    ArrayAdapter<StationModel> stationadapter;
    ArrayList <StationModel> names ;
    JSONObject jsonString;
    String request,code,message;
    String Bday,Bmonth,Byear,Day,Month,Year;

    ApiInterface apiInterface;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FrameLayout contentFrameLayout = (FrameLayout) findViewById(R.id.content_frame);
        getLayoutInflater().inflate(R.layout.activity_train_ticket, contentFrameLayout);
        Fabric.with(this, new Crashlytics());
        toolbar =  findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        passengr_name=findViewById(R.id.traveler_name);
        email_id=findViewById(R.id.email);
        Contact_no=findViewById(R.id.contact);
        citycode = findViewById(R.id.city);
        citycode2 = findViewById(R.id.city2);
        days = findViewById(R.id.day);
        months = findViewById(R.id.month);
        years = findViewById(R.id.year);
        age = findViewById(R.id.age);
//        bmonth = findViewById(R.id.bmonth);
//        byear = findViewById(R.id.byear);
        submit_ticket=(Button)findViewById(R.id.submitTrain);

        android.support.v7.widget.Toolbar tb=getToolBar();
        TextView txtxmpny=(TextView)tb.findViewById(R.id.appname);
        txtxmpny.setText("Train Booking");

        apiInterface= ApiClient.getClient().create(ApiInterface.class);


//        header2 = findViewById(R.id.header2);
//        header3 = findViewById(R.id.header3);
//        header4 = findViewById(R.id.header4);
//        header5 = findViewById(R.id.header5);
//        Typeface custom_font2 = Typeface.createFromAsset(getAssets(),  "fonts/GravityBold.otf");
//        header2.setTypeface(custom_font2);
//        header3.setTypeface(custom_font2);
//        header4.setTypeface(custom_font2);
//        header5.setTypeface(custom_font2);

        getStationCodesCodes();


//        age.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                ageDialog ageDialog=new ageDialog();
//                ageDialog.show(getSupportFragmentManager(),"TAG");
//            }
//        });



        citycode.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                codecity = names.get(i).getStationKey();



            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        citycode2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                codecity2=names.get(i).getStationKey();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

//        bday.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                Bday = adapterView.getItemAtPosition(i).toString();
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> adapterView) {
//
//            }
//        });
//
//        bmonth.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                Bmonth = adapterView.getItemAtPosition(i).toString();
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> adapterView) {
//
//            }
//        });
//
//        byear.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                Byear = adapterView.getItemAtPosition(i).toString();
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> adapterView) {
//
//            }
//        });



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



        trname = (TextInputLayout) findViewById(R.id.name_input_layout);
        trname.setHint("Enter Passenger Name");

        tremail = (TextInputLayout) findViewById(R.id.input_layout_email);
        tremail.setHint("Enter Passenger Email ID");

        trcontact = (TextInputLayout) findViewById(R.id.input_layout_contact);
        trcontact.setHint("Enter Passenger Contact No");

//        init();



        submit_ticket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (TextUtils.isEmpty(passengr_name.getText())) {
                    passengr_name.setError("Field is Mandatory");
                } else if (TextUtils.isEmpty(email_id.getText())) {
                    email_id.setError("Field is Mandatory");
                } else if (TextUtils.isEmpty(Contact_no.getText())) {
                    Contact_no.setError("Field is Mandatory");

                }
                else if(codecity.equals(codecity2))
                {
                    Utility.ShowCustomToast("From Station and To Station Cannot be the same",TrainTicket.this);
                }

                else if(Day.equals("DD")||Month.equals("MM")||Year.equals("YYYY"))
                {
                    Utility.ShowCustomToast("Please Specify the Dates",TrainTicket.this);
                }

                else {

//                    SharedPreferences sp = getSharedPreferences("UserLog",0);
//                    String CustKey =  sp.getString("CustKey",null);
//                    String UserKey =  sp.getString("UserKey",null);
//                    ////////////////////////////////////////////////////////////////////
//
//                    try {
//
//
//                        JSONObject values = new JSONObject();
//                        values.put("CustKey",Integer.parseInt(CustKey));
//                        values.put("PassengerName", passengr_name.getText().toString());
//                        values.put("Age", age.getText().toString());
//                        values.put("FromStationKey", codecity);
//                        values.put("ToStationKey", codecity2);
//                        values.put("TravelDate", Day + "-" + Month + "-" + Year);
//                        values.put("ContactEmail", email_id.getText().toString());
//                        values.put("ContactNo", Contact_no.getText().toString());
//                        values.put("BookingStatusKey", 1);
//                        values.put("Status", true);
//                        values.put("CreatedBy", Integer.parseInt(UserKey));
//                        jsonString = new JSONObject();
//                        jsonString.put("Token", "0001");
//                        jsonString.put("call", "SaveTrainBooking");
//                        jsonString.put("values", values);
//                        request = jsonString.toString();
//
//                    } catch (
//                            JSONException e) {
//                        e.printStackTrace();
//                    }


                    BookTickets();


                }



            }



        });
    }


    public void forceCrash(View view) {
        throw new RuntimeException("This is a crash");
    }


    ///////////////////////////////////Posting/////////////////////////////////////////


    private void train_ticket(final String request) {

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
                                Toast.makeText(TrainTicket.this,"Booking Successful",Toast.LENGTH_LONG).show();
                                Intent intent=new Intent(TrainTicket.this,Home.class);
                                startActivity(intent);
                                finish();
                            }

//                            else {
//                                Toast.makeText(TrainTicket.this,message,Toast.LENGTH_LONG).show();
//                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        if (error instanceof TimeoutError || error instanceof NoConnectionError) {
                            Utility.ShowCustomToast(" No Network Connection",TrainTicket.this);
                        } else if (error instanceof AuthFailureError) {
                            Utility.ShowCustomToast("Authentication Failed",TrainTicket.this);
                        } else if (error instanceof ServerError) {

                            Utility.ShowCustomToast("Server Error Occurred",TrainTicket.this);
                        } else if (error instanceof NetworkError) {

                            Utility.ShowCustomToast("Some Network Error Occurred",TrainTicket.this);
                        } else if (error instanceof ParseError) {

                            Utility.ShowCustomToast("Some Error Occurred",TrainTicket.this);
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
        };

        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                0,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }


    private  void  BookTickets() {




        SharedPreferences sp = getSharedPreferences("UserLog",0);
        String CustKey =  sp.getString("CustKey",null);
        String UserKey =  sp.getString("UserKey",null);

        String Pname  =passengr_name.getText().toString();

        String Age  =age.getText().toString();

        String TravelDate  =Day+"-"+Month+"-"+Year;

        String email  =email_id.getText().toString();

        String contact  =Contact_no.getText().toString();


        apiInterface.SaveTrainBooking(Integer.parseInt(CustKey),Pname,Age,codecity,codecity2,TravelDate,email,contact,1,true,Integer.parseInt(UserKey))
                .enqueue(new Callback<SaveTrainBookingResponse>() {
            @Override
            public void onResponse(Call<SaveTrainBookingResponse> call, retrofit2.Response<SaveTrainBookingResponse> response) {

                if (response.isSuccessful() && response.code() == 200) {
                    assert response.body() != null;
                    if (response.body().getCode().equalsIgnoreCase("0")) {

                        List<SaveTrainBookingResponse.ResultArray> result = response.body().getResponse();



                        for (int i = 0; i < result.size(); i++) {

                            if(result.get(i).getResult().equalsIgnoreCase("1"))
                            {
                                passengr_name .getText().clear();
                                email_id .getText().clear();
                                Contact_no.getText().clear();
                                citycode.setSelection(0);
                                citycode2.setSelection(0);
                                days.setSelection(0);
                                months.setSelection(0);
                                years.setSelection(0);


                                Utility.ShowCustomToast("Booking Successful",TrainTicket.this);
                            }
                            else
                            {
                                Utility.ShowCustomToast("Booking Failed",TrainTicket.this);

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
            public void onFailure(Call<SaveTrainBookingResponse> call, Throwable t) {

            }
        });


    }



    /////////////////////////////////spinner loading//////////////////////////////////







    private  void  getStationCodesCodes() {

        names =new ArrayList<StationModel>();

        apiInterface.GetStationCodes().enqueue(new Callback<GetStationCodesResponse>() {
            @Override
            public void onResponse(Call<GetStationCodesResponse> call, retrofit2.Response<GetStationCodesResponse> response) {

                if (response.isSuccessful() && response.code() == 200) {
                    assert response.body() != null;
                    if (response.body().getCode().equalsIgnoreCase("0")) {

                        List<GetStationCodesResponse.ResultArray> result = response.body().getResponse();



                        for (int i = 0; i < result.size(); i++) {

                            StationModel items  =new StationModel(result.get(i).getStationKey(),result.get(i).getShortCode(),result.get(i).getStationName());

                            names.add(items);

                        }

                        stationadapter = new ArrayAdapter<StationModel>(TrainTicket.this,android.R.layout.simple_spinner_dropdown_item,names);
                        stationadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        citycode.setAdapter(stationadapter);
                        citycode2.setAdapter(stationadapter);




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
            public void onFailure(Call<GetStationCodesResponse> call, Throwable t) {

            }
        });


    }

}
