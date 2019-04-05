package com.example.publicmart;

import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Handler;
import android.support.design.widget.TextInputLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
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
import java.util.Timer;
import java.util.TimerTask;

import io.fabric.sdk.android.Fabric;

import static io.fabric.sdk.android.services.concurrency.AsyncTask.init;

public class TrainTicket extends BaseActivity {

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

    TextView header1,header2,header3,header4,header5,header6,header7;
    Button submit_ticket;
    EditText passengr_name,email_id,Contact_no;
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
        bday = findViewById(R.id.bday);
        bmonth = findViewById(R.id.bmonth);
        byear = findViewById(R.id.byear);
        submit_ticket=(Button)findViewById(R.id.submitTrain);

        android.support.v7.widget.Toolbar tb=getToolBar();
        TextView txtxmpny=(TextView)tb.findViewById(R.id.appname);
        txtxmpny.setText("Train Booking");


//        header2 = findViewById(R.id.header2);
//        header3 = findViewById(R.id.header3);
//        header4 = findViewById(R.id.header4);
//        header5 = findViewById(R.id.header5);
//        Typeface custom_font2 = Typeface.createFromAsset(getAssets(),  "fonts/GravityBold.otf");
//        header2.setTypeface(custom_font2);
//        header3.setTypeface(custom_font2);
//        header4.setTypeface(custom_font2);
//        header5.setTypeface(custom_font2);

        traincode();






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

        trname = (TextInputLayout) findViewById(R.id.name_input_layout);
        trname.setHint("Enter Your Name");

        tremail = (TextInputLayout) findViewById(R.id.input_layout_email);
        tremail.setHint("Enter Your Email ID");

        trcontact = (TextInputLayout) findViewById(R.id.input_layout_contact);
        trcontact.setHint("Enter Your Contact No");

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
                } else {

                    SharedPreferences sp = getSharedPreferences("UserLog",0);
                    String CustKey =  sp.getString("CustKey",null);
                    String UserKey =  sp.getString("UserKey",null);
                    ////////////////////////////////////////////////////////////////////

                    try {


                        JSONObject values = new JSONObject();
                        values.put("CustKey",Integer.parseInt(CustKey));
                        values.put("PassengerName", passengr_name.getText().toString());
                        values.put("DOB", Bday + "-" + Bmonth + "-" + Byear);
                        values.put("FromStationKey", codecity);
                        values.put("ToStationKey", codecity2);
                        values.put("TravelDate", Day + "-" + Month + "-" + Year);
                        values.put("ContactEmail", email_id.getText().toString());
                        values.put("ContactNo", Contact_no.getText().toString());
                        values.put("BookingStatusKey", 1);
                        values.put("Status", true);
                        values.put("CreatedBy", Integer.parseInt(UserKey));
                        jsonString = new JSONObject();
                        jsonString.put("Token", "0001");
                        jsonString.put("call", "SaveTrainBooking");
                        jsonString.put("values", values);
                        request = jsonString.toString();

                    } catch (
                            JSONException e) {
                        e.printStackTrace();
                    }


                    train_ticket(request);


                }

                passengr_name.getText().clear();
                email_id.getText().clear();
                Contact_no.getText().clear();
                citycode.setSelection(0);
                citycode2.setSelection(0);
                days.setSelection(0);
                months.setSelection(0);
                years.setSelection(0);
                bday.setSelection(0);
                bmonth.setSelection(0);
                byear.setSelection(0);
            }



        });
    }


    public void forceCrash(View view) {
        throw new RuntimeException("This is a crash");
    }

    private void init() {
        for (int i = 0; i < IMAGES.length; i++)
        TextArray.add(IMAGES[i]);
        mPager = findViewById(R.id.viewflipper1);
        PagerAdapter adapter = new SlidingText_Adapter_Train(TrainTicket.this, TextArray);
        mPager.setAdapter(adapter);
        Log.e("textsizeeeee","flight "+TextArray.size());
        NUM_PAGES = IMAGES.length;
        // Auto start of viewpager
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage == NUM_PAGES) {
                    currentPage = 0;
                }
                mPager.setCurrentItem(currentPage++, true);
            }
        };
        Timer swipeTimer = new Timer();
        swipeTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(Update);
            }
        }, 1500, 5000);
    }

    ///////////////////////////////////Posting/////////////////////////////////////////


    private void train_ticket(final String request)
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
                                Toast.makeText(TrainTicket.this,"Success",Toast.LENGTH_LONG).show();
                            }

                            else {
                                Toast.makeText(TrainTicket.this,message,Toast.LENGTH_LONG).show();
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






    /////////////////////////////////spinner loading//////////////////////////////////



    private void traincode() {
        names =new ArrayList<StationModel>();

        try {
            JSONObject values = new JSONObject();

            jsonString = new JSONObject();
            jsonString.put("Token", "0001");
            jsonString.put("call", "GetActiveStations");
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

                                    StationKey= jsonObject.getInt("StationKey");
                                    ShortCode =jsonObject.getString("ShortCode");
                                    StationName=jsonObject.getString("StationName");
                                    Log.e("teeessst","ifffff  "+StationKey);

                                    StationModel items  = new StationModel(StationKey,ShortCode,StationName);

                                    names.add(items);
                                    Log.e("from jsonnnn", "  " + jsonObject.getString("ShortCode"));


                                }
                                Log.e("namessssss", "  " + names);

                                stationadapter = new ArrayAdapter<StationModel>(TrainTicket.this,android.R.layout.simple_spinner_dropdown_item,names);
                                stationadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                citycode.setAdapter(stationadapter);
                                citycode2.setAdapter(stationadapter);





                            }


                            else {
                                Toast.makeText(TrainTicket.this,message,Toast.LENGTH_LONG).show();
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


        Log.e("statecodeeeeeee",""+ StationKey);



    }
}
