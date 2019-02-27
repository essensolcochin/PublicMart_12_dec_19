package com.example.publicmart;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
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
import com.crashlytics.android.ndk.CrashlyticsNdk;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import io.fabric.sdk.android.Fabric;


public class MainActivity extends AppCompatActivity {

    TextView reg;
    LinearLayout log;
  String code,message;
    JSONObject values;
    JSONObject Login;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Fabric.with(this, new Crashlytics(), new CrashlyticsNdk());
        reg = (TextView) findViewById(R.id.register);


        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(MainActivity.this,Registration.class);
                startActivity(intent);

            }
        });
        log = findViewById(R.id.login);
        log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                UserLogin();

            }

        });


    }
    public void forceCrash(View view) {
        throw new RuntimeException("This is a crash");
    }

    public void  UserLogin() {

        try {



            values= new JSONObject();
            values.put("UserName","Test");
            values.put("UserPwd","1234");


            Login = new JSONObject();

            Login.put("Token","0001");
            Login.put("call","CheckLogin");
            Login.put("values",values);


        } catch (JSONException e) {
            e.printStackTrace();
        }

        String URL = "http://192.168.0.30:123/api/CommonApi/Invoke";


        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
//                        asyncDialog.dismiss();

                        Log.e("uttutut", "" + response);


                        try {

                            Log.e("uttutut", "" + response);
                            JSONObject o     = new JSONObject(response);

                            JSONObject person = new JSONObject(response);

                           // JSONArray json_array2 =o.getJSONArray("returnResult");
                           // JSONObject jsonObject = json_array2.getJSONObject(0);
                            o.getJSONObject("returnResult");
                            code = o.getString("responseCode");
                            message=o.getString("responseMessage");


                            Log.e("codeeeeeeee", "" + code);

                            if (code.equals("0")) {

                                Intent intent =new Intent(MainActivity.this,Home.class);
                                startActivity(intent);
                                finish();

                                JSONObject result =o.getJSONObject("result");

                                Log.e("keyyyyyyyyy", "" + result.getString("UserKey"));




                                Toast.makeText(MainActivity.this, "Login Success", Toast.LENGTH_LONG).show();
                            } else {

                                Toast.makeText(MainActivity.this,  "unsuccessful", Toast.LENGTH_LONG).show();

                            }


                        }catch (JSONException e){
                            e.printStackTrace();
                        }


                    }

                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
//                        asyncDialog.dismiss();
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();

                    }
                })
        {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> param = new HashMap<String, String>();
                param.put("jsonString",Login.toString());

                Log.e("posttttttttt",""+param);
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
        RequestQueue requestQueue= Volley.newRequestQueue(MainActivity.this);
        requestQueue.add(stringRequest);
        Log.e("reqqqqqqqqqq",""+stringRequest.toString());







    }


}

