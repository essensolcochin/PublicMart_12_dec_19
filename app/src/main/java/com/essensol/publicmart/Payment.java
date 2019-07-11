package com.essensol.publicmart;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.util.HashMap;
import java.util.Map;

public class Payment extends AppCompatActivity {


    JSONObject jsonString;
    String code,message;
    Button confirmPay;
    TextView amount,curncy,name,plan;
    String CustKey;
    Bundle amBundle;
    SharedPreferences sp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        confirmPay = findViewById(R.id.submit_pay);
        amount = (TextView) findViewById(R.id.amount);
//        curncy = (TextView) findViewById(R.id.currency);
        name = (TextView) findViewById(R.id.name);
//        plan = (TextView) findViewById(R.id.planname);

         sp = getSharedPreferences("UserLog",0);
        CustKey =  sp.getString("CustKey",null);


        amount.setText(sp.getString("amnt",null));
//        curncy.setText("INR");
//        name.setText(sp.getString("CustomerName",null));
//        plan.setText(sp.getString("MemberShip",null));



        confirmPay.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {

        String vAmount = amount.getText().toString().trim();

        Intent intent = new Intent(Payment.this,WebViewActivity.class);
        intent.putExtra(AvenuesParams.ACCESS_CODE,"AVXL83GC76AB78LXBA");
        intent.putExtra(AvenuesParams.MERCHANT_ID, "210231");
        intent.putExtra(AvenuesParams.ORDER_ID, CustKey);
        intent.putExtra(AvenuesParams.CURRENCY, "INR");
        intent.putExtra(AvenuesParams.AMOUNT,vAmount);
        intent.putExtra(AvenuesParams.REDIRECT_URL, "http://publicmart.in/transaction/ccavResponseHandler.php");
        intent.putExtra(AvenuesParams.CANCEL_URL, "http://publicmart.in/transaction/ccavResponseHandler.php");
        intent.putExtra(AvenuesParams.RSA_KEY_URL, "http://publicmart.in/transaction/getRSA.php");
        intent.putExtra(AvenuesParams.BILLING_EMAIL, sp.getString("Email",null));
        intent.putExtra(AvenuesParams.BILLING_TEL, sp.getString("MobileNo",null));
        intent.putExtra(AvenuesParams.BILLING_NAME, sp.getString("CustomerName",null));
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

                sp = getSharedPreferences("UserLog",MODE_PRIVATE);
                String custkey =sp.getString("CustKey",null);


                try {

                    JSONObject values = new JSONObject();
                    values.put("CustKey", custkey);

                    jsonString = new JSONObject();
                    jsonString.put("Token", "0001");
                    jsonString.put("call", "UpdatePaidStatus");
                    jsonString.put("values", values);
                    String req =jsonString.toString();

                    updatePayment(req);

                } catch (
                        JSONException e) {
                    e.printStackTrace();
                }





            }





//                SharedPreferences log = getSharedPreferences("UserLog", MODE_PRIVATE);
//                SharedPreferences.Editor editor = log.edit();
//                editor.putBoolean("LoggedUser", true);
//                editor.apply();
//                mainIntent = new Intent(Payment.this, Home.class);
//                startActivity(mainIntent);
//                finish();

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

                                   SharedPreferences sp = getSharedPreferences("UserLog",MODE_PRIVATE);
                                   SharedPreferences.Editor edit = sp.edit();
                                   edit.putBoolean("LoggedUser",true);
                                   edit.apply();
                                   Intent intent = new Intent(Payment.this,Home.class);
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
            }
                    ;

            RequestQueue requestQueue= Volley.newRequestQueue(this);
            requestQueue.add(stringRequest);


        }


    }

