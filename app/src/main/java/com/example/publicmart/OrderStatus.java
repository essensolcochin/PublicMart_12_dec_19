package com.example.publicmart;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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
import com.baoyachi.stepview.HorizontalStepView;
import com.baoyachi.stepview.bean.StepBean;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderStatus extends BaseActivity {
    HorizontalStepView horizontalStepView;
    LinearLayout ll;
    TextView status,status2, status1;
    StepBean stepBean0,stepBean1,stepBean2,stepBean3;
    Button pay;
    TextView txtxmpny;
    OrderStatusAdapter_ adapter_;
    List<OrderStatusModel>item_list;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FrameLayout contentFrameLayout = (FrameLayout) findViewById(R.id.content_frame);
        getLayoutInflater().inflate(R.layout.activity_order_status_ed, contentFrameLayout);


item_list=new ArrayList<>();


        String[] data = {"1", "2", "3", "4", "5", "6", "7", "8", "9"};

        // set up the RecyclerView
         recyclerView = findViewById(R.id.orderRecycler);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));




        SharedPreferences sp = getSharedPreferences("UserLog",0);
        String CustKey =  sp.getString("UserKey",null);


        try {

            final JSONObject jsonString;
            JSONObject values = new JSONObject();
            values.put("CustKey",CustKey);



            jsonString = new JSONObject();
            jsonString.put("Token", "0001");
            jsonString.put("call", "GetOrderDetailsByCustKey");
            jsonString.put("values", values);



            String URL = this.getString(R.string.Url)+"Select";


            StringRequest stringRequest=new StringRequest(Request.Method.POST, URL,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                            //progress.cancel();

                            Log.e("OrderSatatussssss",""+response);


                            try {


                                JSONObject o     = new JSONObject(response);

                                ////// Checking Json Response Is JSON Object Or Not ///////
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


                                String  code = o.getString("responseCode");
                                String  message=o.getString("responseMessage");


                                Log.e("codeeeeeeeeee","in"+code);

                                if (code.equalsIgnoreCase("0")) {

                                    //JSONArray json_array2 = o.getJSONArray("result");
                                    JSONArray jsonObject;
                                    jsonObject = o.getJSONArray("result");
                                    int j;
                                    for (j = 0; j < jsonObject.length(); j++) {
                                        JSONObject JsonData;

                                        JsonData = jsonObject.getJSONObject(j);


                                        OrderStatusModel items = new OrderStatusModel(JsonData.getString("ProductKey"),
                                                JsonData.getString("BrandName"),
                                                JsonData.getString("ShortDesc"),
                                                JsonData.getString("Amount"),
                                                JsonData.getString("BV"),
                                                JsonData.getString("ImagePath"),
                                                JsonData.getString("OrderStatusKey"),
                                                JsonData.getString("OrderStatusName"));

                                        Log.e("resppppppp", "ifffff" + code);


                                        item_list.add(items);
                                    }

                                    adapter_ = new OrderStatusAdapter_(OrderStatus.this, item_list);
                                    recyclerView.setAdapter(adapter_);


                                }
                                else {
                                    Toast.makeText(OrderStatus.this,message,Toast.LENGTH_LONG).show();
                                }




                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }



                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            // progress.cancel();
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



        } catch (Exception e) {
            // JSONException
        }



    }







}
