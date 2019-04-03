package com.example.publicmart;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;

import android.graphics.Typeface;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.CardView;

import android.util.Log;
import android.view.View;


import android.view.animation.Interpolator;
import android.webkit.WebView;
import android.widget.FrameLayout;

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
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import dmax.dialog.SpotsDialog;
import io.fabric.sdk.android.Fabric;

public class Home extends BaseActivity {
    static int  i=0;
    private static ViewPager mPager;
    private static int currentPage = 0;
    private static int NUM_PAGES = 0;
    LinearLayout services,product,orderstat,booking,credit_layout,shopping;
    CardView profile;
    TextView txtxmpny,Product,sevices,order,shoplist,bookingstat,credit,report,prof;
    TextView view;

    private static final Integer[] IMAGES= {R.drawable.ad,R.drawable.ad,R.drawable.ad2};
    private ArrayList<HomeAdModel> ImagesArray = new ArrayList<HomeAdModel>();
    private SpotsDialog progress;
String code,message;
    int image[] = {R.drawable.ad};
    JSONObject jsonString ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FrameLayout contentFrameLayout = (FrameLayout) findViewById(R.id.content_frame);
        getLayoutInflater().inflate(R.layout.activity_home_test, contentFrameLayout);
        Fabric.with(this, new Crashlytics());

         progress = new SpotsDialog(Home.this,R.style.Custom);


        progress.show();





               // init();

//        FirebaseInstanceId.getInstance().getInstanceId().addOnSuccessListener( Home.this,  new OnSuccessListener<InstanceIdResult>() {
//            @Override
//            public void onSuccess(InstanceIdResult instanceIdResult) {
//                String newToken = instanceIdResult.getToken();
//                Log.e("newTokennnnnnnn  ",newToken);
//
//            }
//        });









//        android.support.v7.widget.Toolbar tb=getToolBar();
//        txtxmpny=(TextView)tb.findViewById(R.id.appname);
//        txtxmpny.setText("Home");

        product=(LinearLayout) findViewById(R.id.Product_layout);
        orderstat =(LinearLayout) findViewById(R.id.order);
        booking=(LinearLayout) findViewById(R.id.bookingstat);
        credit_layout=(LinearLayout)findViewById(R.id.credit_lay);

        shopping=(LinearLayout)findViewById(R.id.shoppinlist);
        booking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Home.this, TabActivity.class);
                startActivity(intent);
            }
        });


        orderstat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Home.this, OrderStatus.class);
                startActivity(intent);
            }
        });

        credit_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home.this, CreditActivity.class);
                startActivity(intent);
            }
        });

        shopping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home.this, Shopping_List.class);
                startActivity(intent);
            }
        });



        Typeface custom_font = Typeface.createFromAsset(getAssets(),  "fonts/ralewayM.ttf");



        Product=(TextView)findViewById(R.id.product);
        sevices=(TextView)findViewById(R.id.services);
        order=(TextView)findViewById(R.id.Order);
        shoplist=(TextView)findViewById(R.id.shop);
        bookingstat=(TextView)findViewById(R.id.book);
        credit=(TextView)findViewById(R.id.credit);

//        view=findViewById(R.id.textContent);
//
//        String text;
//        text =   "ACCT NAME   : PUBLIC MART\n"+
//                "ACCT NO     : 50200034752049\n"+
//                "IFSC CODE   : HDFC0000628\n"+
//                "BRANCH NAME : ALAPPUZHA\n";
//
//        view.setText(text);


        Product.setTypeface(custom_font);
        sevices.setTypeface(custom_font);
        order.setTypeface(custom_font);
        shoplist.setTypeface(custom_font);
        bookingstat.setTypeface(custom_font);
        credit.setTypeface(custom_font);



        services = findViewById(R.id.service);
        services.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home.this, Services.class);
                startActivity(intent);

            }
        });


        product.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home.this, Products.class);
                startActivity(intent);
            }
        });


        LoadAdBanner();

    }


    private void LoadAdBanner() {



        jsonString =new JSONObject();




        try {

            JSONObject values = new JSONObject();
            values.put("Type","H");


            jsonString = new JSONObject();
            jsonString.put("Token", "0001");
            jsonString.put("call", "GetAdvImagesByType");
            jsonString.put("values", values);



        } catch (
                JSONException e) {
            e.printStackTrace();
        }





        String URL = this.getString(R.string.Url)+"Select";


        StringRequest stringRequest=new StringRequest(Request.Method.POST, URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {



                        progress.cancel();


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

//                            JSONArray json_array2 = o.getJSONArray("result");
//                            Log.e("tryyyyyyyyy",""+json_array2);
//
//                            JSONObject jsonObject = json_array2.getJSONObject(0);
                            code = o.getString("responseCode");
                            message=o.getString("responseMessage");

                            Log.e("resppppppp",""+code);


                            if (code.equals("0"))
                            {


                                JSONArray json_array2 = o.getJSONArray("result");


                                JSONObject jsonObject;


                                int j;
                                for (j = 0; j < json_array2.length(); j++) {

                                    jsonObject = json_array2.getJSONObject(j);

                                    HomeAdModel images= new HomeAdModel(jsonObject.getString("AdvImageKey"),
                                            jsonObject.getString("ImageDesc"),
                                            jsonObject.getString("ImagePath"));


                                    ImagesArray.add(images);


                                }
                                mPager = (ViewPager) findViewById(R.id.viewflipper);


                                Log.e("Imagearraayyyyyyy","in"+ImagesArray);

                                PagerAdapter adapter = new SlidingImage_Adapter_Home(Home.this, ImagesArray);
                                mPager.setAdapter(adapter);

                                 NUM_PAGES =ImagesArray.size();
                                // Auto start of viewpager
                                final Handler handler = new Handler();
                                final Runnable Update = new Runnable() {
                                    public void run() {
                                        if (currentPage == NUM_PAGES) {

                                            currentPage= NUM_PAGES-currentPage;
                                            Log.e("pageeeee","in -- "+currentPage);
                                            mPager.setCurrentItem(currentPage--, true);
                                        }
                                        else{
                                            mPager.setCurrentItem(currentPage++, true);
                                            Log.e("pageeeee","in ++ "+currentPage);
                                        }


                                    }
                                };
                                Timer swipeTimer = new Timer();
                                swipeTimer.schedule(new TimerTask() {
                                    @Override
                                    public void run() {
                                        handler.post(Update);
                                    }
                                }, 300, 3000);








                            }


//                            else {
//                                Toast.makeText(Home.this,message,Toast.LENGTH_LONG).show();
//                            }




                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }



                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progress.cancel();
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

    }













    public void forceCrash(View view) {
        throw new RuntimeException("This is a crash");
    }










}