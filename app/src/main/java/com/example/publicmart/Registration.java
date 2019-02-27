package com.example.publicmart;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Registration extends AppCompatActivity {
    LinearLayout regist;
    JSONObject values;
    JSONObject Register;
    String code,message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        regist = (LinearLayout)findViewById(R.id.submit);
        regist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(Registration.this,MainActivity.class);
                startActivity(intent);

              //  UserRegister();

            }
        });
    }

//    public void  UserRegister() {
//
//        try {
//
//
//
//            values= new JSONObject();
//            values.put("UserName","Test");
//            values.put("UserPwd","1234");
//
//
//            Register = new JSONObject();
//
//            Register.put("Token","0001");
//            Register.put("call","CheckLogin");
//            Register.put("values",values);
//
//
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//
//        String URL = "http://192.168.0.30:123/api/CommonApi/Invoke";
//
//
//        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL,
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
////                        asyncDialog.dismiss();
//
//                        Log.e("uttutut", "" + response);
//
//
//                        try {
//
//                            Log.e("uttutut", "" + response);
//                            JSONObject o     = new JSONObject(response);
//
//                            JSONObject person = new JSONObject(response);
//
//                            // JSONArray json_array2 =o.getJSONArray("returnResult");
//                            // JSONObject jsonObject = json_array2.getJSONObject(0);
//                            o.getJSONObject("returnResult");
//                            code = o.getString("responseCode");
//                            message=o.getString("responseMessage");
//
//
//                            Log.e("codeeeeeeee", "" + code);
//
//                            if (code.equals("0")) {
//
//                                Intent intent =new Intent(Registration.this,MainActivity.class);
//                                startActivity(intent);
//                                finish();
//
//                                JSONObject result =o.getJSONObject("result");
//
//                                Log.e("keyyyyyyyyy", "" + result.getString("UserKey"));
//
//
//
//
//                                Toast.makeText(Registration.this, "Registration Successful", Toast.LENGTH_LONG).show();
//                            } else {
//
//                                Toast.makeText(Registration.this,  "unsuccessful", Toast.LENGTH_LONG).show();
//
//                            }
//
//
//                        }catch (JSONException e){
//                            e.printStackTrace();
//                        }
//
//
//                    }
//
//                },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
////                        asyncDialog.dismiss();
//                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
//
//                    }
//                })
//        {
//
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//                Map<String, String> param = new HashMap<String, String>();
//                param.put("jsonString",Register.toString());
//
//                Log.e("posttttttttt",""+param);
//                return param;
//
//
//            }
//
//            @Override
//            public Map<String, String> getHeaders() throws AuthFailureError {
//                Map<String,String> param = new HashMap<String, String>();
//                param.put("Content-Type","application/x-www-form-urlencoded");
//                return param;
//            }
//        }
//                ;
//
//        // Volley.getInstance(this).addToRequestQueue(stringRequest);
//        RequestQueue requestQueue= Volley.newRequestQueue(Registration.this);
//        requestQueue.add(stringRequest);
//        Log.e("reqqqqqqqqqq",""+stringRequest.toString());
//
//
//
//
//
//
//
//    }
}
