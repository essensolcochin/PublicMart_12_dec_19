package com.example.publicmart;


import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Handler;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dmax.dialog.SpotsDialog;
import io.realm.Realm;
import io.realm.RealmResults;


public class ProductView extends BaseActivity {

    TextView Product_Name,Short_Desc,Product_Details,detailed_description,Material,material_care,price,BV;

    private static ViewPager mPager;
    private static int currentPage = 0;
    private static int NUM_PAGES = 0;
    private static final String[] IMAGES={};
    TextView cartItem;

    private ArrayList<String> ImagesArray = new ArrayList<String>();
    LinearLayout wishlist;
    Button placeorder;
    String CategoryKey,ProductKey;
    private Realm realm;
    private RealmResults<RealmShopModel> cartSIZE;
    private ProgressBar loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FrameLayout contentFrameLayout = (FrameLayout) findViewById(R.id.content_frame);
        getLayoutInflater().inflate(R.layout.activity_product_view, contentFrameLayout);


//        android.support.v7.widget.Toolbar tb=getToolBar();
//        txtxmpny=(TextView)tb.findViewById(R.id.appname);
//        txtxmpny.setText("Products");

        realm = Realm.getDefaultInstance();


        cartSIZE = realm.where(RealmShopModel.class).findAll();
        cartSIZE.load();






        Bundle abBundle= getIntent().getExtras();
        ProductKey = abBundle.getString("ProductKey");

        Product_Name=(TextView)findViewById(R.id.product_name);
        Short_Desc=(TextView)findViewById(R.id.product_desc);
        Product_Details =(TextView)findViewById(R.id.product_detail);
        detailed_description =(TextView)findViewById(R.id.detail_desc);
        Material=(TextView)findViewById(R.id.material);
        material_care=(TextView)findViewById(R.id.materialcare);
        wishlist = findViewById(R.id.bottomframe);
        placeorder = findViewById(R.id.place_order);
        price = findViewById(R.id.Price);
        BV = findViewById(R.id.BV);
        loading= findViewById(R.id.loading);

        loading =new ProgressBar(this);
        Typeface custom_font = Typeface.createFromAsset(getAssets(),  "fonts/ralewayM.ttf");

        Typeface custom_font2 = Typeface.createFromAsset(getAssets(),  "fonts/RalewayBold.ttf");
        detailed_description.setTypeface(custom_font);
        Product_Details.setTypeface(custom_font2);
        Short_Desc.setTypeface(custom_font);
        Product_Name.setTypeface(custom_font2);
        Material.setTypeface(custom_font2);
        material_care.setTypeface(custom_font);


        wishlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(getApplication(), "Product added to Wishlist",Toast.LENGTH_SHORT).show();
            }
        });

        placeorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplication(), "Your Order Has Been Placed",Toast.LENGTH_LONG).show();
            }
        });





        try {

            final JSONObject jsonString;
            JSONObject values = new JSONObject();
            values.put("ProductKey",ProductKey);
//            values.put("PageSize",ItemCount);
//            values.put("PageNumber",pageNo);


            jsonString = new JSONObject();
            jsonString.put("Token", "0001");
            jsonString.put("call", "GetProductDetailsById");
            jsonString.put("values", values);



            String URL = this.getString(R.string.Url)+"Select";


            StringRequest stringRequest=new StringRequest(Request.Method.POST, URL,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                            //progress.cancel();

                            Log.e("Jsonnnn",""+response);


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
                                    JSONObject jsonObject;
                                    jsonObject= o.getJSONObject("result");
                                    Log.e("Tableeeee","in"+jsonObject);
                                    JSONArray table,table1;
                                    table = jsonObject.getJSONArray("Table");
                                    JSONObject tableObject = new JSONObject();
                                    tableObject =table.getJSONObject(0);

                                    Product_Name.setText(tableObject.getString("BrandName"));
                                    Short_Desc.setText(tableObject.getString("ShortDesc"));
                                    price.setText(tableObject.getString("MRP"));
                                    BV.setText(tableObject.getString("BV"));
                                    detailed_description.setText(tableObject.getString("ProductDetails"));

                                    Log.e("Tableeeee","in"+Product_Name);

                                    table1 = jsonObject.getJSONArray("Table1");
                                    Log.e("Tableeeee","inside table     "+table);
                                    Log.e("Tableeeee","inside table1     "+table1);

                                    JSONObject tableObject1;


                                    int j;
                                    for (j = 0; j < table1.length(); j++) {

                                        tableObject1 =table1.getJSONObject(j);
                                        ImagesArray.add(tableObject1.getString("ImagePath"));


                                    }
                                    mPager = (ViewPager) findViewById(R.id.pager);


                                    Log.e("Imagearraayyyyyyy","in"+ImagesArray);

                                    PagerAdapter adapter = new SlidingImage_Adapter_Product(ProductView.this, ImagesArray);
                                    mPager.setAdapter(adapter);

                                }
                                else {
                                    Toast.makeText(ProductView.this,message,Toast.LENGTH_LONG).show();
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

        placeorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AddToCart();

//                ItemCountSelectionDialog cdd = new ItemCountSelectionDialog(ProductView.this);
//                cdd.show();


//                final SpotsDialog progress = new SpotsDialog(ProductView.this,R.style.Custom);
//
//
//                progress.show();







            }
        });


    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main, menu);

        final MenuItem menuItem = menu.findItem(R.id.cart_action);

        View actionView = MenuItemCompat.getActionView(menuItem);
        cartItem = (TextView) actionView.findViewById(R.id.cart_badge);


        FrameLayout click =(FrameLayout)actionView.findViewById(R.id.cartclick);

        click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent cartIntent = new Intent(ProductView.this, OrderStatus.class);

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
            cartItem.setText(Integer.toString(cartSIZE.size()));
        }

        return  true;
    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//
//        switch (item.getItemId())
//        {
//            case R.id.cart_action:
//                Intent cartIntent = new Intent(ProductView.this, OrderStatus.class);
//                startActivity(cartIntent);
//                return true;
//        }
//
//
//        return super.onOptionsItemSelected(item);
//    }






    public  void AddToCart()
   {

       realm.beginTransaction();

       RealmShopModel addToCart1 = new RealmShopModel();
       addToCart1.setProductKey(ProductKey);
       realm.insertOrUpdate(addToCart1);

       realm.commitTransaction();

       if(cartSIZE.size()==0)
       {
           cartItem.setVisibility(View.GONE);
       }
       else
       {
           cartItem.setVisibility(View.VISIBLE);
           cartItem.setText(Integer.toString(cartSIZE.size()));
       }

       PostOrderDetails();

   }

public  void PostOrderDetails()
{
 SharedPreferences sp = getSharedPreferences("UserLog",0);
  String CustKey =  sp.getString("UserKey",null);

    Log.e("sharedddddddd",""+CustKey);
           try {

           final JSONObject jsonString;
           JSONObject values = new JSONObject();
               values.put("OrderDate","10-2-2019");
               values.put("CustKey",CustKey);
               values.put("ProductKey",ProductKey);
               values.put("Qty",1);
               values.put("Rate",price.getText().toString());
               values.put("IsCredit",false);
               values.put("OrderStatusKey",1);
               values.put("Status",true);
               values.put("CreatedBy",CustKey);



           jsonString = new JSONObject();
           jsonString.put("Token", "0001");
           jsonString.put("call", "SaveOrderDetails");
           jsonString.put("values", values);



           String URL = this.getString(R.string.Url)+"Save";


           StringRequest stringRequest=new StringRequest(Request.Method.POST, URL,
                   new Response.Listener<String>() {
                       @Override
                       public void onResponse(String response) {

                           //progress.cancel();

                           Log.e("Jsonnnn",""+response);


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

                               if (code.equalsIgnoreCase("-100")) {

                                   Toast.makeText(ProductView.this,"Your Order Has Been Placed",Toast.LENGTH_LONG).show();

                               }
                               else {
                                   Toast.makeText(ProductView.this,message,Toast.LENGTH_LONG).show();
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





