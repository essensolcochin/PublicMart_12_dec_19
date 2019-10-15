package com.publicmart.essensol.Activities;

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
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.publicmart.essensol.R;
import com.publicmart.essensol.RetrofitUtils.ApiClient;
import com.publicmart.essensol.RetrofitUtils.ApiInterface;
import com.publicmart.essensol.RetrofitUtils.RetrofitResponseClasses.GetProfileDetailsResponse;
import com.publicmart.essensol.RetrofitUtils.RetrofitResponseClasses.SaveUpdateProfileDetailsResponse;
import com.publicmart.essensol.Utility;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Profile extends AppCompatActivity {

    TextView  Fname,Mname,Lname,contact_no,email_id,h_no,tehsil,village,dist,pinode,membership,proicon,proid,state_name;
    JSONObject jsonString;
    String request,code,message,cusname,Bmonth,Byear;
    TextView edit,save;
    EditText contact,email,name,address,accHname,accNo,ifscc,Bname,Branch;
    TextView Dob;
    LinearLayout submit;
    Calendar newCalendar;
    DatePickerDialog DateOfBirth;

    ApiInterface apiInterface;


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
        address=(EditText) findViewById(R.id.address);

        submit= findViewById(R.id.submit);
        Dob= findViewById(R.id.Dob);


        apiInterface = ApiClient.getClient().create(ApiInterface.class);

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


                EditProfile();

                if(Utility.isNetworkConnectionAvailable(Profile.this)) {
//                    EditProfile(jsonString.toString());
                }
                else {
                    Utility.ShowCustomToast("No Network Connectivity",Profile.this);
                }


            }
        });



    }

    private void profile_view() {

        SharedPreferences sp = getSharedPreferences("UserLog",0);
        String CustKey =  sp.getString("CustCode",null);


        apiInterface.GetProfileDetails(CustKey).enqueue(new Callback<GetProfileDetailsResponse>() {
            @Override
            public void onResponse(Call<GetProfileDetailsResponse> call, retrofit2.Response<GetProfileDetailsResponse> response) {

                if (response.isSuccessful() && response.code() == 200) {
                    assert response.body() != null;
                    if (response.body().getCode().equalsIgnoreCase("0")) {

                        List<GetProfileDetailsResponse.ResultArray> result = response.body().getResponse();



                        for (int i = 0; i < result.size(); i++) {

                            Fname.setText(result.get(i).getCustomerName());
                            proid.setText(result.get(i).getCustCode());
                            contact.setText(result.get(i).getMobileNo());
                            email.setText(result.get(i).getEmail());
                            address.setText(result.get(i).getAddress());
                            proicon.setText(result.get(i).getCustomerName().substring(0,1));
                            Dob.setText(result.get(i).getDOB());
                            cusname=result.get(i).getCustomerName();
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
            public void onFailure(Call<GetProfileDetailsResponse> call, Throwable t) {

            }
        });

    }


    public void EditProfile() {

        Log.e("Clickeddd"," "+Dob.toString());
        Log.e("Date",""+Dob.getText().toString());

        SharedPreferences sp = getSharedPreferences("UserLog",0);
        String CustKey =  sp.getString("CustCode",null);
        String UserKey =  sp.getString("UserKey",null);

        apiInterface.SaveUpdateProfileDetails(address.getText().toString(),Dob.getText().toString(),
                email.getText().toString(),UserKey,CustKey,0,cusname,contact.getText().toString(),
                "N/A","N/A","N/A","N/A","N/A").enqueue(new Callback<SaveUpdateProfileDetailsResponse>() {

            @Override
            public void onResponse(Call<SaveUpdateProfileDetailsResponse> call, Response<SaveUpdateProfileDetailsResponse> response) {
                Log.e("Clickeddd"," "+response.code());
                if (response.isSuccessful() && response.code() == 200) {
                    assert response.body() != null;
                    if (response.body().getCode().equalsIgnoreCase("0")) {



                            Intent intent =new Intent(Profile.this,Home.class);
                            startActivity(intent);
                            finish();

                    }
                    else
                    {
                        Log.e("Failed"," ");
                    }
                }
            }

            @Override
            public void onFailure(Call<SaveUpdateProfileDetailsResponse> call, Throwable t) {
                Log.e("Failed"," ");
            }
        });

    }


    @Override
    public void onBackPressed() {

        Intent intent =new Intent(Profile.this,Home.class);
        startActivity(intent);
        finish();

    }
}
