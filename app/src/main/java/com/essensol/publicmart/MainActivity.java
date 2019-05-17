package com.essensol.publicmart;


import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import android.widget.EditText;
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
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;


import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import io.realm.Realm;


public class MainActivity extends AppCompatActivity {
    JSONObject jsonString;
    TextView reg;
    LinearLayout log;
    EditText username,password;
    String code,message,request,token;
    SharedPreferences sp;
    Realm realm;
    private    ProgressDialog progressdialog;
    private static final Random random = new Random();
    private static final String CHARS = "abcdefghijkmnopqrstuvwxyzABCDEFGHJKLMNOPQRSTUVWXYZ234567890!@#$";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        reg = (TextView) findViewById(R.id.register);

        username= (EditText) findViewById(R.id.username);
        password= (EditText) findViewById(R.id.password);

        realm = Realm.getDefaultInstance();


        progressdialog = new ProgressDialog(MainActivity.this);

        getToken(5);
//
//        SharedPreferences SaveToken =   getSharedPreferences("GetToken",MODE_PRIVATE);
//        token =  SaveToken.getString("Token",null);
////        Log.e("newTokennnnnnnn  ",token);

        final ProgressDialog progress = new ProgressDialog(MainActivity.this);
        progress.setTitle("Publicmart");
        progress.setMessage("Gathering Information");
        progress.show();

        Runnable progressRunnable = new Runnable() {

            @Override
            public void run() {

                FirebaseInstanceId.getInstance().getInstanceId().addOnSuccessListener( MainActivity.this,  new OnSuccessListener<InstanceIdResult>() {
                    @Override
                    public void onSuccess(InstanceIdResult instanceIdResult) {
                        String newToken = instanceIdResult.getToken();
                        Log.e("newTokennnnnnnn  ",newToken);
                        token = newToken;
                    }
                });

                progress.cancel();
            }
        };

        Handler pdCanceller = new Handler();
        pdCanceller.postDelayed(progressRunnable, 500);




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


                progressdialog.setTitle("Publicmart");
                progressdialog.setMessage("Gathering Information");
                progressdialog.show();


                try {

                    JSONObject values = new JSONObject();
                    values.put("UserName", username.getText().toString());
                    values.put("UserPwd", password.getText().toString());
                    values.put("AppToken", token);
                    values.put("Mode", "M");

                    jsonString = new JSONObject();
                    jsonString.put("Token", "0001");
                    jsonString.put("call", "CheckLogin");
                    jsonString.put("values", values);
                    request = jsonString.toString();


                } catch (
                        JSONException e) {
                    e.printStackTrace();
                }

                Login(request);









            }

        });


    }
    public void forceCrash(View view) {
        throw new RuntimeException("This is a crash");
    }




    private void Login(final String request) {


        Log.e("gettttt","in"+request);



        String URL = this.getString(R.string.Url)+"Select";


        StringRequest stringRequest=new StringRequest(Request.Method.POST, URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {






                        Log.e("Jsonnnn",""+response);
                        progressdialog.cancel();
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


                             JSONArray json_array2 = o.getJSONArray("result");


                             JSONObject jsonObject = json_array2.getJSONObject(0);

                                Log.e("tryyyyyyyyy","  "+jsonObject.get("UserKey"));

                                String Count = jsonObject.get("CartCount").toString();

                                if(!Count.equalsIgnoreCase("0"))
                                {
                                    realm.beginTransaction();
                                    RealmShopModel addToCart1 = new RealmShopModel();
                                    addToCart1.setCount(Count);
                                    realm.insertOrUpdate(addToCart1);
                                    realm.commitTransaction();
                                }




                                sp = getSharedPreferences("UserLog",MODE_PRIVATE);
                                SharedPreferences.Editor editor =sp.edit();


                                editor.putString("UserKey",jsonObject.get("UserKey").toString());
                                editor.putString("CustKey",jsonObject.get("CustKey").toString());
                                editor.putString("Username",jsonObject.get("UserName").toString());
                                editor.putString("CustomerName",jsonObject.get("CustomerName").toString());
                                editor.putString("CustCode",jsonObject.get("CustCode").toString());
                                editor.putString("MemberShip",jsonObject.get("MemberShip").toString());
                                editor.putString("amnt",jsonObject.get("Amount").toString());
                                editor.putString("MobileNo",jsonObject.get("MobileNo").toString());
                                editor.putString("Email",jsonObject.get("Email").toString());

                                editor.apply();
                                Log.e("Log Bool","  "+sp.getBoolean("LoggedUser",false));
                                Log.e("Log keyyyy","  "+sp.getString("UserKey",null));


                                if(jsonObject.get("PaidStatus").toString().equalsIgnoreCase("True"))
                                {
                                    SharedPreferences sp = getSharedPreferences("UserLog",MODE_PRIVATE);
                                    SharedPreferences.Editor edit = sp.edit();
                                    edit.putBoolean("LoggedUser",true);
                                    edit.apply();
                                    Intent intent =new Intent(MainActivity.this,Home.class);
                                    startActivity(intent);
                                    finish();
                                }

                                else{

                                    Intent intent =new Intent(MainActivity.this,Payment.class);
//                                    intent.putExtra("amnt",jsonObject.get("Amount").toString());
//                                    intent.putExtra("CustomerName",jsonObject.get("CustomerName").toString());
//                                    intent.putExtra("MobileNo",jsonObject.get("MobileNo").toString());
//                                    intent.putExtra("Email",jsonObject.get("Email").toString());
//                                    intent.putExtra("MemberShip",jsonObject.get("MemberShip").toString());


                                    startActivity(intent);
                                    finish();
                                    }



                            }


                            else {
                                Toast.makeText(MainActivity.this,message,Toast.LENGTH_LONG).show();
                            }




                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }



                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressdialog.show();
                        Toast.makeText(getApplicationContext(), "Some Error Occurred", Toast.LENGTH_SHORT).show();

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

    public static String getToken(int length) {
        StringBuilder token = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            token.append(CHARS.charAt(random.nextInt(CHARS.length())));
        }
        Log.e("Mytokennnnn",""+token.toString());
        return token.toString();

    }



    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }



}
