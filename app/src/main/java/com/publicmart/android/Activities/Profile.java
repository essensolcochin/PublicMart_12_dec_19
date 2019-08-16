package com.publicmart.android.Activities;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

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
import com.publicmart.android.R;
import com.publicmart.android.Utility;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class Profile extends AppCompatActivity {

    TextView  Fname,Mname,Lname,contact_no,email_id,h_no,tehsil,village,dist,pinode,membership,proicon,proid,state_name;
    JSONObject jsonString;
    String request,code,message,Bday,Bmonth,Byear;
    TextView edit,save;
    EditText contact,email,name,address,accHname,accNo,ifscc,Bname,Branch;
    TextView Dob;
    LinearLayout submit;
    Calendar newCalendar;
    DatePickerDialog DateOfBirth;

    String array_day[] = {"DD", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10",
            "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30",
            "31"};

    String array_Mnth[] = {"MM", "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};

    String array_BYear[] = {"YY","1975","1976","1977","1978","1979","1980","1981","1982","1983","1984","1985","1986","1986","1987","1988","1989","1990","1991","1992",
            "1993","1994", "1995", "1996","1997","1998", "1999","2000","2001","2002","2003","2004","2005"} ;
//    Spinner state,Day,Month,Year;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
//        FrameLayout contentFrameLayout = (FrameLayout) findViewById(R.id.content_frame);
//        getLayoutInflater().inflate(R.layout.activity_profile, contentFrameLayout);

        proid=(TextView)findViewById(R.id.profileid);
        Fname=(TextView)findViewById(R.id.fname);

        contact=(EditText) findViewById(R.id.contact);
        email=(EditText) findViewById(R.id.email);
        name=(EditText) findViewById(R.id.name);
        address=(EditText) findViewById(R.id.address);
        accHname=(EditText) findViewById(R.id.AcchName);
        accNo=(EditText) findViewById(R.id.AccNo);
        ifscc=(EditText) findViewById(R.id.ifsc);
        Bname=(EditText) findViewById(R.id.BankName);
        Branch=(EditText) findViewById(R.id.Branch);
        submit= findViewById(R.id.submit);
        Dob= findViewById(R.id.Dob);



        proicon=(TextView)findViewById(R.id.proicon);

//        edit=findViewById(R.id.edit);



//        statecode();
//        names = new ArrayList<SpinnerModel>();
        profile_view();




        Dob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                newCalendar = Calendar.getInstance();
                DateOfBirth = new DatePickerDialog(Profile.this, new DatePickerDialog.OnDateSetListener() {

                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        Calendar newDate = Calendar.getInstance();
                        newDate.set(year, monthOfYear, dayOfMonth);


                            Dob.setText(dayOfMonth +"/" + (monthOfYear + 1) +"/"+  year);


                        DateOfBirth.getDatePicker().setSpinnersShown(true);
                        DateOfBirth.getDatePicker().setCalendarViewShown(false);
                    }

                }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

                DateOfBirth.getDatePicker().setCalendarViewShown(false);
                DateOfBirth.getDatePicker().setSpinnersShown(true);
                DateOfBirth.getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
//                    DateOfBirth.getDatePicker().setMaxDate(System.currentTimeMillis());
                DateOfBirth.show();

            }
        });





        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                SharedPreferences sp = getSharedPreferences("UserLog",0);
                String CustKey =  sp.getString("CustCode",null);
                String UserKey =  sp.getString("UserKey",null);

                try {
                    JSONObject values = new JSONObject();
                    values.put("CustDetKey",0);
                    values.put("CustCode",CustKey);
                    values.put("CustomerName",name.getText().toString());
                    values.put("MobileNo",contact.getText().toString());
                    values.put("Email",email.getText().toString());
                    values.put("DOB",Dob.getText().toString());
                    values.put("Address",address.getText().toString());
                    values.put("AcntHolderName",accHname.getText().toString());
                    values.put("AcntNo",accNo.getText().toString());
                    values.put("BankName",Bname.getText().toString());
                    values.put("BranchName",Branch.getText().toString());
                    values.put("IFSCCode",ifscc.getText().toString());
                    values.put("CreatedBy",UserKey);



                    jsonString = new JSONObject();
                    jsonString.put("Token", "0001");
                    jsonString.put("call", "SaveUpdateProfileDetails");
                    jsonString.put("values", values);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                if(Utility.isNetworkConnectionAvailable(Profile.this)) {
                    EditProfile(jsonString.toString());
                }
                else {
                    Utility.ShowCustomToast("No Network Connectivity",Profile.this);
                }


            }
        });



    }

    public void profile_view() {
        SharedPreferences sp = getSharedPreferences("UserLog",0);
        String CustKey =  sp.getString("CustCode",null);

        try {
            JSONObject values = new JSONObject();
            values.put("CustCode",CustKey);

            jsonString = new JSONObject();
            jsonString.put("Token", "0001");
            jsonString.put("call", "GetProfileDetailsByCustCode");
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
                                    if(!jsonObject.getString("CustomerName").equalsIgnoreCase("null"))
                                    {
                                        Fname.setText(jsonObject.getString("CustomerName"));
                                        proicon.setText(jsonObject.getString("CustomerName").substring(0,1));
                                        name.setText(jsonObject.getString("CustomerName"));


                                    }

                                    if(!jsonObject.getString("MobileNo").equalsIgnoreCase("null"))
                                    {
                                        contact.setText(jsonObject.getString("MobileNo"));

                                    }
                                    if(!jsonObject.getString("Email").equalsIgnoreCase("null"))
                                    {
                                        email.setText(jsonObject.getString("Email"));

                                    }
                                    if(!jsonObject.getString("Address").equalsIgnoreCase("null"))
                                    {
                                        address.setText(jsonObject.getString("Address"));

                                    }
                                    if(!jsonObject.getString("AcntHolderName").equalsIgnoreCase("null"))
                                    {
                                        accHname.setText(jsonObject.getString("AcntHolderName"));

                                    }
                                    if(!jsonObject.getString("AcntNo").equalsIgnoreCase("null"))
                                    {
                                        accNo.setText(jsonObject.getString("AcntNo"));

                                    }
                                    if(!jsonObject.getString("BankName").equalsIgnoreCase("null"))
                                    {
                                        Bname.setText(jsonObject.getString("BankName"));

                                    }
                                    if(!jsonObject.getString("BranchName").equalsIgnoreCase("null"))
                                    {
                                        Branch.setText(jsonObject.getString("BranchName"));

                                    }
                                    if(!jsonObject.getString("IFSCCode").equalsIgnoreCase("null"))
                                    {
                                        ifscc.setText(jsonObject.getString("IFSCCode"));

                                    }
                                    if(!jsonObject.getString("CustCode").equalsIgnoreCase("null"))
                                    {
                                        proid.setText(jsonObject.getString("CustCode"));

                                    }
                                    if (!jsonObject.getString("DOB").equalsIgnoreCase("null")) {
                                        Dob.setText(jsonObject.getString("DOB"));
                                    }



                                }
                            }
//                            else {
//                                Utility.ShowCustomToast(" No Network Connection",Profile.this);
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
                            Utility.ShowCustomToast(" No Network Connection",Profile.this);
                        } else if (error instanceof AuthFailureError) {
                            Utility.ShowCustomToast("Authentication Failed",Profile.this);
                        } else if (error instanceof ServerError) {

                            Utility.ShowCustomToast("Server Error Occurred",Profile.this);
                        } else if (error instanceof NetworkError) {

                            Utility.ShowCustomToast("Some Network Error Occurred",Profile.this);
                        } else if (error instanceof ParseError) {

                            Utility.ShowCustomToast("Some Error Occurred",Profile.this);
                        }
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
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }







    public void EditProfile(final  String request) {
        SharedPreferences sp = getSharedPreferences("UserLog",0);
        String CustKey =  sp.getString("CustCode",null);

        try {
            JSONObject values = new JSONObject();
            values.put("CustCode",CustKey);

            jsonString = new JSONObject();
            jsonString.put("Token", "0001");
            jsonString.put("call", "GetProfileDetailsByCustCode");
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


                            if (code.equalsIgnoreCase("-100"))
                            {

                                Utility.ShowCustomToast("Information Saved",Profile.this);

                                SharedPreferences sp = getSharedPreferences("UserLog",MODE_PRIVATE);
                                SharedPreferences.Editor edit = sp.edit();
                                edit.putBoolean("LoggedUser",true);
                                edit.apply();


                                Intent intent =new Intent(Profile.this,Home.class);
                                startActivity(intent);
                                finish();



                            }
//                            else {
//                                Utility.ShowCustomToast(" No Network Connection",Profile.this);
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
                            Utility.ShowCustomToast(" No Network Connection",Profile.this);
                        } else if (error instanceof AuthFailureError) {
                            Utility.ShowCustomToast("Authentication Failed",Profile.this);
                        } else if (error instanceof ServerError) {

                            Utility.ShowCustomToast("Server Error Occurred",Profile.this);
                        } else if (error instanceof NetworkError) {

                            Utility.ShowCustomToast("Some Network Error Occurred",Profile.this);
                        } else if (error instanceof ParseError) {

                            Utility.ShowCustomToast("Some Error Occurred",Profile.this);
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


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        moveTaskToBack(true);
    }
}
