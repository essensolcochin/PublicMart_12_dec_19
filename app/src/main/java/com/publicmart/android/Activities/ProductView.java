package com.publicmart.android.Activities;


import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.publicmart.android.R;
import com.publicmart.android.ModelClasses.RealmShopModel;
import com.publicmart.android.Adapters_.SlidingImage_Adapter_Product;
import com.publicmart.android.RetrofitUtils.ApiClient;
import com.publicmart.android.RetrofitUtils.ApiInterface;
import com.publicmart.android.RetrofitUtils.RetrofitResponseClasses.GetProductDetailsResponse;
import com.publicmart.android.RetrofitUtils.RetrofitResponseClasses.SaveOrderdetailsResponse;
import com.publicmart.android.Utility;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.realm.Realm;
import io.realm.RealmResults;
import retrofit2.Call;
import retrofit2.Callback;


public class ProductView extends BaseActivity {

    TextView Product_Name,Short_Desc,Product_Details,detailed_description,Material,material_care,price,BV,size,sizeTitle;

    private static ViewPager mPager;
    private static int currentPage = 0;
    private static int NUM_PAGES = 0;
    private static final String[] IMAGES={};
    TextView cartItem;

    private ArrayList<String> ImagesArray = new ArrayList<String>();
    LinearLayout wishlist,bg;
    Button placeorder;
    String CategoryKey,ProductKey,quantity;
    private Realm realm;
    private RealmResults<RealmShopModel> cartSIZE;
    private ProgressBar loading;
    private ImageView add,minus;
    Integer count =1;
    AnimatorSet set;
    ApiInterface apiInterface;
    View view;
    TextView Qty;


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
        bg=(LinearLayout) findViewById(R.id.bg);
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
        size= findViewById(R.id.size1);
        sizeTitle= findViewById(R.id.size);
        add= findViewById(R.id.plus);
        minus= findViewById(R.id.minus);
        Qty = findViewById(R.id.quantity);
        mPager = (ViewPager) findViewById(R.id.pager);
        view= findViewById(R.id.view);


        loading =new ProgressBar(this);
        Typeface custom_font = Typeface.createFromAsset(getAssets(),  "fonts/ralewayM.ttf");
        Typeface custom_font3 = Typeface.createFromAsset(getAssets(),  "fonts/CODEBold.otf");

        Typeface custom_font2 = Typeface.createFromAsset(getAssets(),  "fonts/OpenSansSemiBold.ttf");
        detailed_description.setTypeface(custom_font);
        Product_Details.setTypeface(custom_font2);
        Short_Desc.setTypeface(custom_font);
        Product_Name.setTypeface(custom_font2);
        Material.setTypeface(custom_font2);
        material_care.setTypeface(custom_font);
        size.setTypeface(custom_font3);
        sizeTitle.setTypeface(custom_font2);


        TabLayout tabLayout = (TabLayout) view.findViewById(R.id.tabselector);
        tabLayout.setupWithViewPager(mPager, true);


        apiInterface= ApiClient.getClient().create(ApiInterface.class);




        GetProductDetails(ProductKey);


        wishlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                Toast.makeText(getApplication(), "Product added to Wishlist",Toast.LENGTH_SHORT).show();
            }
        });


        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(count <=1)
                {
                 Qty.setText(String.valueOf(count));
                 Qty.setTextColor(Color.parseColor("#DC143C"));
                }
                else {
                    count--;
                    Qty.setTextColor(Color.parseColor("#475c7a"));
                    Qty.setText(String.valueOf(count));
                }
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(count >=10)
                {
                    Qty.setText(String.valueOf(count));
                    Qty.setTextColor(Color.parseColor("#DC143C"));
                }
                else {
                    count++;
                    Qty.setTextColor(Color.parseColor("#475c7a"));
                    Qty.setText(String.valueOf(count));
                }
            }
        });




        placeorder.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onClick(View v) {

                set = (AnimatorSet) AnimatorInflater.loadAnimator(ProductView.this, R.anim.flipping);
                set.setTarget(placeorder);

                set.start();

//                if(placeorder.getText().toString().equalsIgnoreCase("View Order"))
//                {
//                    SharedPreferences sp = getSharedPreferences("UserLog",0);
//                    String CustKey =  sp.getString("CustKey",null);
//
//                    Intent intent = new Intent(ProductView.this,OrderStatus.class);
//                    intent.putExtra("CustKey",CustKey);
//                    startActivity(intent);
//                }

                PlaceOrder();








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
            for(int i=0;i<cartSIZE.size();i++) {
                if (cartSIZE.get(i) != null) {
                    cartItem.setText(cartSIZE.get(i).getCount());
                }
            }
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






    public  void AddToCart(String Count)
   {

       realm.beginTransaction();

       RealmShopModel addToCart1 = new RealmShopModel();
       addToCart1.setCount(Count);
       realm.insertOrUpdate(addToCart1);

       realm.commitTransaction();

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



   }



   private void GetProductDetails(String ProductKey) {

        apiInterface.GetProductDetails(ProductKey).enqueue(new Callback<GetProductDetailsResponse>() {
            @Override
            public void onResponse(Call<GetProductDetailsResponse> call, retrofit2.Response<GetProductDetailsResponse> response) {


                if (response.isSuccessful() && response.code() == 200) {
                    assert response.body() != null;
                    if (response.body().getCode().equalsIgnoreCase("0")) {
                        List<GetProductDetailsResponse.Table> result = response.body().getResult().getTable();
                        for (int i = 0; i < result.size(); i++) {


                            Product_Name.setText(result.get(i).getBrandName());
                            Short_Desc.setText(result.get(i).getShortDesc());
                            price.setText(result.get(i).getMRP());
                            BV.setText(result.get(i).getBV());
                            detailed_description.setText(result.get(i).getProductDetails());
                            material_care.setText(result.get(i).getMaterial());
                            String size2 = result.get(i).getSize();
                            Log.e("sizeeeeeeeee", "inside      " + size2.toCharArray().length);

                            if (size2.equalsIgnoreCase("null")) {
                                size.setText("Not Available");
                                size.setTextSize(12);

                            } else if (size2.toCharArray().length - 1 > 1) {

                                size.setText(result.get(i).getSize());
                                size.setTextSize(12);
                            } else {
                                bg.setBackgroundDrawable(ContextCompat.getDrawable(ProductView.this, R.drawable.circularborder));
                                size.setText(result.get(i).getSize());
                            }


                        }

                        List<GetProductDetailsResponse.Table1> table1 = response.body().getResult().getTable1();
                        for (int i = 0; i < table1.size(); i++) {

                            ImagesArray.add(table1.get(i).getImagePath());


                        }


//                        Log.e("Imagearraayyyyyyy", "in" + ImagesArray);

                        PagerAdapter adapter = new SlidingImage_Adapter_Product(ProductView.this, ImagesArray);
                        mPager.setAdapter(adapter);

                    }




                    else
                    {

//                        Utility.ShowCustomToast("Coming Soon",Fashion.this); ToDo

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
            public void onFailure(Call<GetProductDetailsResponse> call, Throwable t) {

            }
        });


   }





    private void PlaceOrder() {

        SharedPreferences sp = getSharedPreferences("UserLog",0);
        String CustKey =  sp.getString("CustKey",null);
        String UserKey =  sp.getString("UserKey",null);
        String qty=Qty.getText().toString();
        String Amount= price.getText().toString();

        apiInterface.SaveOrderDetails(CustKey,ProductKey,qty,Amount,"false","1","true",UserKey).enqueue(new Callback<SaveOrderdetailsResponse>() {
            @Override
            public void onResponse(Call<SaveOrderdetailsResponse> call, retrofit2.Response<SaveOrderdetailsResponse> response) {

                if (response.isSuccessful() && response.code() == 200) {
                    assert response.body() != null;
                    if (response.body().getCode().equalsIgnoreCase("0")) {
                        List<SaveOrderdetailsResponse.ResultArray> result = response.body().getResponse();
                        for (int i = 0; i < result.size(); i++) {
                            if(result.get(i).getResult().equalsIgnoreCase("1"))
                            {

                                AddToCart(result.get(i).getCartCount());

                                Utility.ShowCustomToast("Your Order has been Placed",ProductView.this);
                                placeorder.setClickable(false);
//                                placeorder.setFocusable(false);
                            }


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
            public void onFailure(Call<SaveOrderdetailsResponse> call, Throwable t) {

            }
        });

    }

    }





