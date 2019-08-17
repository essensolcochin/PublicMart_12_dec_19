package com.publicmart.android.Activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.baoyachi.stepview.HorizontalStepView;
import com.baoyachi.stepview.bean.StepBean;
import com.publicmart.android.Adapters_.OrderStatusAdapter_;
import com.publicmart.android.ModelClasses.OrderStatusModel;
import com.publicmart.android.R;
import com.publicmart.android.ModelClasses.RealmShopModel;
import com.publicmart.android.RetrofitUtils.ApiClient;
import com.publicmart.android.RetrofitUtils.ApiInterface;
import com.publicmart.android.RetrofitUtils.RetrofitResponseClasses.OrderStatusResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.realm.Realm;
import io.realm.RealmResults;
import retrofit2.Call;
import retrofit2.Callback;

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
    private Realm realm;
    private RealmResults<RealmShopModel> cartSIZE;
    ProgressDialog dialog;

    ApiInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FrameLayout contentFrameLayout = (FrameLayout) findViewById(R.id.content_frame);
        getLayoutInflater().inflate(R.layout.activity_order_status_ed, contentFrameLayout);

        realm = Realm.getDefaultInstance();


        cartSIZE = realm.where(RealmShopModel.class).findAll();
        cartSIZE.load();
        dialog =new ProgressDialog(OrderStatus.this);
        dialog.setTitle("Your Cart");
        dialog.setMessage("Loading Cart Items...");

        item_list=new ArrayList<>();



        // set up the RecyclerView
         recyclerView = findViewById(R.id.orderRecycler);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        apiInterface= ApiClient.getClient().create(ApiInterface.class);


        SharedPreferences sp = getSharedPreferences("UserLog",0);
        String CustKey =  sp.getString("CustKey",null);

        dialog.show();

        GetOrderStatus(CustKey);

//        try {
//
//            final JSONObject jsonString;
//            JSONObject values = new JSONObject();
//            values.put("CustKey", CustKey);
//
//
//
//            jsonString = new JSONObject();
//            jsonString.put("Token", "0001");
//            jsonString.put("call", "GetOrderDetailsByCustKey");
//            jsonString.put("values", values);
//
//
//
//            String URL = this.getString(R.string.Url)+"Select";
//
//
//            StringRequest stringRequest=new StringRequest(Request.Method.POST, URL,
//                    new Response.Listener<String>() {
//                        @Override
//                        public void onResponse(String response) {
//
//                            //progress.cancel();
//
//                            Log.e("OrderSatatussssss",""+response);
//
//
//                            try {
//
//
//                                JSONObject o     = new JSONObject(response);
//
//                                ////// Checking Json Response Is JSON Object Or Not ///////
//                                String data = response;
//                                Object json = new JSONTokener(data).nextValue();
//                                if (json instanceof JSONObject){
//
//                                    Log.e("objectttttt",""+json);
//                                }
//
//                                //you have an object
//                                else if (json instanceof JSONArray){
//                                    Log.e("Arrayyyyyyy",""+json);
//                                }
//
//                                ///////////////////////////////////////////
//
//                                Log.e("tryyyyyyyyy","in"+o);
//
//
//                                String  code = o.getString("responseCode");
//                                String  message=o.getString("responseMessage");
//
//
//                                Log.e("codeeeeeeeeee","in"+code);
//
//                                if (code.equalsIgnoreCase("0")) {
//                                    dialog.cancel();
//
//                                    //JSONArray json_array2 = o.getJSONArray("result");
//                                    JSONArray jsonObject;
//                                    jsonObject = o.getJSONArray("result");
//                                    int j;
//                                    for (j = 0; j < jsonObject.length(); j++) {
//                                        JSONObject JsonData;
//
//                                        JsonData = jsonObject.getJSONObject(j);
//
//
//                                        OrderStatusModel items = new OrderStatusModel(JsonData.getString("ProductKey"),
//                                                JsonData.getString("BrandName"),
//                                                JsonData.getString("ShortDesc"),
//                                                JsonData.getString("Amount"),
//                                                JsonData.getString("BV"),
//                                                JsonData.getString("ImagePath"),
//                                                JsonData.getString("OrderStatusKey"),
//                                                JsonData.getString("OrderStatusName"));
//
//                                        Log.e("itemssss", "ifffff" + items );
//
//
//                                        item_list.add(items);
//                                    }
//
//                                    adapter_ = new OrderStatusAdapter_(OrderStatus.this, item_list);
//                                    recyclerView.setAdapter(adapter_);
//
//
//                                }
//                                else {
//                                    dialog.cancel();
//                                    if(cartSIZE!=null){
//                                        realm.beginTransaction();
//                                        cartSIZE.deleteAllFromRealm();
//                                        realm.commitTransaction();
//                                        realm.close();
//                                    }
//
//                                }
//
//
//
//
//                            } catch (JSONException e) {
//                                e.printStackTrace();
//                            }
//                        }
//
//
//
//                    },
//                    new Response.ErrorListener() {
//                        @Override
//                        public void onErrorResponse(VolleyError error) {
//                             dialog.cancel();
////                            Toast.makeText(getApplicationContext(), "Some Error Occurred ", Toast.LENGTH_SHORT).show();
//
//                        }
//                    })
//
//
//            {
//
//                @Override
//                protected Map<String, String> getParams() throws AuthFailureError {
//                    Map<String, String> param = new HashMap<String, String>();
//                    param.put("jsonString",jsonString.toString() );
//                    Log.e("paramssss",""+param);
//                    return param;
//                }
//
//                @Override
//                public Map<String, String> getHeaders() throws AuthFailureError {
//                    Map<String,String> param = new HashMap<String, String>();
//                    param.put("Content-Type","application/x-www-form-urlencoded");
//                    return param;
//                }
//            }
//                    ;
//
//            RequestQueue requestQueue= Volley.newRequestQueue(this);
//            requestQueue.add(stringRequest);
//
//
//
//        } catch (Exception e) {
//            // JSONException
//        }



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main, menu);

        final MenuItem menuItem = menu.findItem(R.id.cart_action);

        View actionView = MenuItemCompat.getActionView(menuItem);
       TextView cartItem = (TextView) actionView.findViewById(R.id.cart_badge);


        FrameLayout click =(FrameLayout)actionView.findViewById(R.id.cartclick);

        click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent cartIntent = new Intent(OrderStatus.this, OrderStatus.class);

                startActivity(cartIntent);
                finish();
            }
        });



        if(cartSIZE.size()==0)
        {
            cartItem.setVisibility(View.GONE);
        }
        else
        {
            cartItem.setVisibility(View.VISIBLE);
            for(int i=0;i<cartSIZE.size();i++) {
                if (cartSIZE.get(i) != null) {
                    cartItem.setText(cartSIZE.get(i).getCount());
                }
            }
        }

        return  true;
    }



private void GetOrderStatus(String CustKey){

        apiInterface.GetOrderStatus(CustKey).enqueue(new Callback<OrderStatusResponse>() {
            @Override
            public void onResponse(Call<OrderStatusResponse> call, retrofit2.Response<OrderStatusResponse> response) {

                if (response.isSuccessful() && response.code() == 200) {
                    assert response.body() != null;
                    if (response.body().getCode().equalsIgnoreCase("0")) {
                        dialog.cancel();
                        List<OrderStatusResponse.ResultArray> result = response.body().getResponse();

                        for (int i = 0; i < result.size(); i++) {



                            OrderStatusModel items = new OrderStatusModel(result.get(i).getProductKey(),
                                    result.get(i).getBrandName(),
                                    result.get(i).getShortDesc(),
                                    result.get(i).getAmount(),
                                    result.get(i).getBV(),
                                    result.get(i).getImagePath(),
                                    result.get(i).getOrderStatusKey(),
                                    result.get(i).getOrderStatusName());

//                            Log.e("itemssss", "ifffff" + items );


                            item_list.add(items);
                        }

                        adapter_ = new OrderStatusAdapter_(OrderStatus.this, item_list);
                        recyclerView.setAdapter(adapter_);



                    }
                    else {
                        dialog.cancel();
                        if (cartSIZE != null) {
                            realm.beginTransaction();
                            cartSIZE.deleteAllFromRealm();
                            realm.commitTransaction();
                            realm.close();

                        }
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
            public void onFailure(Call<OrderStatusResponse> call, Throwable t) {

            }
        });

}


}
