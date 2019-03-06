package com.example.publicmart;




import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.FrameLayout;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import dmax.dialog.SpotsDialog;

public class Products extends BaseActivity  {


    JSONObject jsonString;

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




        progress = new SpotsDialog(Products.this,R.style.Custom);

//        android.support.v7.widget.Toolbar tb=getToolBar();

        recyclerView = findViewById(R.id.menu);


        layoutManager=new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(layoutManager);


        LoadMenu();

        progress.show();



    }


    private void LoadMenu() {



        try {

            JSONObject values = new JSONObject();
            values.put("ImageType","ProductCategory");

            jsonString = new JSONObject();
            jsonString.put("Token", "0001");
            jsonString.put("call", "GetActiveProductCategory");
            jsonString.put("values", values);

        } catch (
                JSONException e) {
            e.printStackTrace();
        }





        String URL = this.getString(R.string.Url)+"GetImage";


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

                            ///////////////////////////////////////////

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

                                  //
                                    menuModel.add(items);
                                    Log.e("fromjsonnnn", "  " + menuModel.size());

                                }
                                adapter = new ProductMenuAdapter(getApplicationContext(), menuModel);
                                recyclerView.setAdapter(adapter);
                          }


                            else {
                                Toast.makeText(Products.this,message,Toast.LENGTH_LONG).show();
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
                        Toast.makeText(getApplicationContext(), "Some Error Occurred ", Toast.LENGTH_LONG).show();

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
