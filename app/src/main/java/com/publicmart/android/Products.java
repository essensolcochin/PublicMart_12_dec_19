package com.publicmart.android;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import android.view.View;
import android.widget.FrameLayout;
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
import java.util.List;
import java.util.Map;
import dmax.dialog.SpotsDialog;
import io.fabric.sdk.android.Fabric;

public class Products extends BaseActivity  {


    JSONObject jsonString;
    TextView fresh,fashion;
    String code,message;
    ProductMenuAdapter adapter;
    GridLayoutManager layoutManager;
    RecyclerView recyclerView;
    List<$ProductMenuModel>menuModel=new ArrayList<>();
    SpotsDialog progress ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FrameLayout contentFrameLayout = (FrameLayout) findViewById(R.id.content_frame);
        getLayoutInflater().inflate(R.layout.activity_products_recycle, contentFrameLayout);

        Fabric.with(this, new Crashlytics());


        progress = new SpotsDialog(Products.this,R.style.Custom);

//        android.support.v7.widget.Toolbar tb=getToolBar();

        recyclerView = findViewById(R.id.menu);


        layoutManager=new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(layoutManager);

        fresh= (TextView) findViewById(R.id.fresh);
        fashion= (TextView) findViewById(R.id.fashion);

        Typeface custom_font = Typeface.createFromAsset(getAssets(),  "fonts/collection.ttf");
        Typeface custom_font1 = Typeface.createFromAsset(getAssets(),  "fonts/fresh.ttf");

        fresh.setTypeface(custom_font1);
        fashion.setTypeface(custom_font);

        LoadMenu();

        progress.show();



    }

    public void forceCrash(View view) {
        throw new RuntimeException("This is a crash");
    }


    private void LoadMenu() {



        try {

            JSONObject values = new JSONObject();
           // values.put("ImageType","ProductCategory");

            jsonString = new JSONObject();
            jsonString.put("Token", "0001");
            jsonString.put("call", "GetActiveProductCategoryList");
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

                       ////// Checking Json Response Is JSON Object Or Not ///////
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

////////////////////////////////////////////////////////////////////////////////////////////////////

                            Log.e("tryyyyyyyyy","in"+o);


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

                                   $ProductMenuModel items = new $ProductMenuModel(jsonObject.getString("CategoryKey"),
                                           jsonObject.getString("CategoryName"),
                                           jsonObject.getString("ImagePath"));

                                    menuModel.add(items);
                                    Log.e("fromjsonnnn", "  " + menuModel.size());

                                }
                                adapter = new ProductMenuAdapter(getApplicationContext(), menuModel);
                                recyclerView.setAdapter(adapter);
                          }


                            else {
                                Toast.makeText(Products.this,"Coming Soon",Toast.LENGTH_LONG).show();
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
                })


        {

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

}
