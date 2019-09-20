package com.publicmart.android.Activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.publicmart.android.AvenuesParams;
import com.publicmart.android.R;
import com.publicmart.android.RetrofitUtils.ApiClient;
import com.publicmart.android.RetrofitUtils.ApiInterface;
import com.publicmart.android.RetrofitUtils.RetrofitResponseClasses.GetBookingPaymentDetailsResponse;
import com.publicmart.android.Utility;
import com.publicmart.android.Utils.CONSTANTS;
import com.publicmart.android.WebViewActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;

public class Payment extends AppCompatActivity {


    JSONObject jsonString;
    String code,message;
    Button confirmPay;
    TextView amount,curncy,name,email,contactno;
    String CustKey,BookingKey;
    Bundle amBundle;
    SharedPreferences sp;
    ApiInterface apiInterface;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        confirmPay = findViewById(R.id.submit_pay);
        amount = (TextView) findViewById(R.id.amount);
        name = (TextView) findViewById(R.id.name);
        email = (TextView) findViewById(R.id.emailId);
        contactno = (TextView) findViewById(R.id.contactno);


       apiInterface = ApiClient.getClient().create(ApiInterface.class);

        amBundle =getIntent().getExtras();
        assert amBundle != null;
        final String Type =amBundle.getString("Type");



        Log.e("Bundleeee","  "+Type);


        SharedPreferences sp = getSharedPreferences("UserLog",0);
        CustKey =  sp.getString("CustKey",null);


        GetPaymentDetails(CustKey,Type);



        confirmPay.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {

//        String vAmount = amount.getText().toString().trim();

        Intent intent = new Intent(Payment.this, WebViewActivity.class);
        intent.putExtra(AvenuesParams.ACCESS_CODE,"AVXL83GC76AB78LXBA");
        intent.putExtra(AvenuesParams.MERCHANT_ID, "210231");
        intent.putExtra(AvenuesParams.ORDER_ID, "#"+CustKey+"BK"+BookingKey+Type);
        intent.putExtra(AvenuesParams.CURRENCY, "INR");
        intent.putExtra(AvenuesParams.AMOUNT,amount.getText().toString());
        intent.putExtra(AvenuesParams.REDIRECT_URL, "http://publicmart.in/transaction/ccavResponseHandler.php");
        intent.putExtra(AvenuesParams.CANCEL_URL, "http://publicmart.in/transaction/ccavResponseHandler.php");
        intent.putExtra(AvenuesParams.RSA_KEY_URL, "http://publicmart.in/transaction/getRSA.php");
        intent.putExtra(AvenuesParams.BILLING_EMAIL, email.getText().toString());
        intent.putExtra(AvenuesParams.BILLING_TEL, contactno.getText().toString());
        intent.putExtra(AvenuesParams.BILLING_NAME, name.getText().toString());
        intent.putExtra(AvenuesParams.BILLING_COUNTRY, "India");



        startActivity(intent);
        finish();
        //AVXL83GC76AB78LXBA -accescode

        //https://secure.ccavenue.com/transaction/jsp/GetRSA.jsp

         }
    });


        Intent mainIntent = getIntent();
        String status = mainIntent.getStringExtra("transStatus");
        if(status!=null) {
            if (status.equals("Transaction Successful!")) {

//                sp = getSharedPreferences("UserLog",MODE_PRIVATE);
//                String custkey =sp.getString("CustKey",null);
//
//
//                try {
//
//                    JSONObject values = new JSONObject();
//                    values.put("CustKey", custkey);
//
//                    jsonString = new JSONObject();
//                    jsonString.put("Token", "0001");
//                    jsonString.put("call", "UpdatePaidStatus");
//                    jsonString.put("values", values);
//                    String req =jsonString.toString();
//
//                    updatePayment(req);
//
//                } catch (
//                        JSONException e) {
//                    e.printStackTrace();
//                }





            }







            }
        }

        public void updatePayment(final String params){



            final ProgressDialog progress = new ProgressDialog(Payment.this);
            progress.setTitle("Finalising");
            progress.setMessage("Gathering Information");
            progress.show();


            String URL = this.getString(R.string.Url)+"Save";


            StringRequest stringRequest=new StringRequest(Request.Method.POST, URL,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {






                            Log.e("Jsonnnn",""+response);
                            progress.cancel();
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


                                if (code.equals("0"))
                                {

                                   Utility.ShowCustomToast("Your Payment Was Successful",Payment.this);
                                   Intent intent = new Intent(Payment.this,Profile.class);
                                   startActivity(intent);
                                   finish();


                                }


                                else {
                                    Toast.makeText(Payment.this,message,Toast.LENGTH_SHORT).show();
                                }




                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }



                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            progress.cancel();
                            Toast.makeText(getApplicationContext(), "Some Error Occurred ", Toast.LENGTH_SHORT).show();

                        }
                    }) {

                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> param = new HashMap<String, String>();
                    param.put("jsonString",params );
                    Log.e("paramssss","payyyy "+param);
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




    private void GetPaymentDetails(String Custkey,String Type) {





        apiInterface.GetPaymentDetailsforBookings(Custkey,Type).enqueue(new Callback<GetBookingPaymentDetailsResponse>() {
            @Override
            public void onResponse(Call<GetBookingPaymentDetailsResponse> call, retrofit2.Response<GetBookingPaymentDetailsResponse> response) {

                if (response.isSuccessful() && response.code() == 200) {
                    assert response.body() != null;
                    if (response.body().getCode().equalsIgnoreCase("0")) {

                        List<GetBookingPaymentDetailsResponse.ResultArray> result = response.body().getResponse();
                        for (int i = 0; i < result.size(); i++) {

                            name.setText(result.get(i).getPassengerName());

                            contactno.setText(result.get(i).getContactNo());

                            email.setText(result.get(i).getContactEmail());

                            amount.setText(result.get(i).getAmount());

                            BookingKey=result.get(i).getBookingKey();

                            Log.e("Typeeeee","  "+result.get(i).getType());

                            SharedPreferences bookDetails = getSharedPreferences("BookDetails",0);
                            SharedPreferences.Editor bookEd=bookDetails.edit();
                            bookEd.putString(CONSTANTS.BookingKey,BookingKey);
                            bookEd.putString(CONSTANTS.Type,result.get(i).getType());
                            bookEd.apply();

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
            public void onFailure(Call<GetBookingPaymentDetailsResponse> call, Throwable t) {

            }
        });
    }




}

