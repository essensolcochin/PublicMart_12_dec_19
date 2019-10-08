package com.publicmart.essensol.Activities;

import android.content.Intent;

import android.content.SharedPreferences;
import android.graphics.Typeface;

import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.CardView;

import android.util.Log;
import android.view.View;


import android.widget.FrameLayout;

import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.crashlytics.android.Crashlytics;
import com.publicmart.essensol.ModelClasses.HomeAdModel;
import com.publicmart.essensol.R;
import com.publicmart.essensol.Adapters_.SlidingImage_Adapter_Home;
import com.publicmart.essensol.RetrofitUtils.ApiClient;
import com.publicmart.essensol.RetrofitUtils.ApiInterface;
import com.publicmart.essensol.RetrofitUtils.RetrofitResponseClasses.CheckAccountStatusResponse;
import com.publicmart.essensol.RetrofitUtils.RetrofitResponseClasses.HomeScreenResponse;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import dmax.dialog.SpotsDialog;
import io.fabric.sdk.android.Fabric;
import retrofit2.Call;
import retrofit2.Callback;

public class Home extends BaseActivity {
    static int  i=0;
    private static ViewPager mPager;

    int currentPage = 0;
    Timer timer;
    final long DELAY_MS = 3000;//delay in milliseconds before task is to be executed
    final long PERIOD_MS = 5000;

    private static int NUM_PAGES = 0;
    LinearLayout services,product,orderstat,booking,credit_layout,shopping;

    TextView Product,sevices,order,shoplist,bookingstat,credit;
    TextView view;

    private static final Integer[] IMAGES= {R.drawable.ad,R.drawable.ad,R.drawable.ad2};
    private ArrayList<HomeAdModel> ImagesArray = new ArrayList<HomeAdModel>();
    private SpotsDialog progress;
    String code,message;

    JSONObject jsonString ;
    ApiInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FrameLayout contentFrameLayout = (FrameLayout) findViewById(R.id.content_frame);
        getLayoutInflater().inflate(R.layout.home_new_layout, contentFrameLayout);
        Fabric.with(this, new Crashlytics());

        progress = new SpotsDialog(Home.this,R.style.Custom);

        progress.show();

        product=(LinearLayout) findViewById(R.id.Product_layout);
        orderstat =(LinearLayout) findViewById(R.id.order);
        booking=(LinearLayout) findViewById(R.id.bookingstat);
        credit_layout=(LinearLayout)findViewById(R.id.credit_lay);
        mPager = (ViewPager) findViewById(R.id.viewflipper);
        shopping=(LinearLayout)findViewById(R.id.shoppinlist);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabselector);
        tabLayout.setupWithViewPager(mPager, true);


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

                Intent intent = new Intent(Home.this, HouseBoat.class);
                startActivity(intent);
            }
        });

        credit_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Home.this, Travel_guide.class);
                startActivity(intent);

            }
        });

        shopping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home.this, Tour_Packages.class);
                startActivity(intent);

            }
        });



        Typeface custom_font = Typeface.createFromAsset(getAssets(),  "fonts/OpenSansSemiBold.ttf");



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
                Intent intent = new Intent(Home.this, FlightTicket.class);
                startActivity(intent);

            }
        });


        product.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Home.this, TrainTicket.class);
                startActivity(intent);

            }
        });



        apiInterface= ApiClient.getClient().create(ApiInterface.class);

        CheckStatus();

    }




    private void LoadBanner () {

        apiInterface.LoadHome("H").enqueue(new Callback<HomeScreenResponse>() {
            @Override
            public void onResponse(Call<HomeScreenResponse> call, retrofit2.Response<HomeScreenResponse> response) {

                if (response.isSuccessful() && response.code() == 200) {
                    assert response.body() != null;
                    if (response.body().getCode().equalsIgnoreCase("0")) {
                        progress.cancel();
                        List<HomeScreenResponse.ResultArray> result = response.body().getResponse();
                        for (int i = 0; i < result.size(); i++) {


                            HomeAdModel images= new HomeAdModel(result.get(i).getAdvImageKey(),
                                    result.get(i).getImagePath(),result.get(i).getImageDesc());


                            ImagesArray.add(images);




                        }

                        PagerAdapter adapter = new SlidingImage_Adapter_Home(Home.this, ImagesArray);
                        mPager.setAdapter(adapter);

                        NUM_PAGES =ImagesArray.size();
                        // Auto start of viewpager
//                        final Handler handler = new Handler();
//                        final Runnable Update = new Runnable() {
//                            public void run() {
//                                if (currentPage == NUM_PAGES) {
//
//                                    currentPage= NUM_PAGES-currentPage;
//                                    mPager.setCurrentItem(currentPage--, true);
//                                }
//                                else{
//                                    mPager.setCurrentItem(currentPage++, true);
//                                }
//
//
//                            }
//                        };
//                        Timer swipeTimer = new Timer();
//                        swipeTimer.schedule(new TimerTask() {
//                            @Override
//                            public void run() {
//                                handler.post(Update);
//                            }
//                        }, 300, 3000);


                        final Handler handler = new Handler();
                        final Runnable Update = new Runnable() {
                            public void run() {
                                if (currentPage == NUM_PAGES) {
                                    currentPage = 0;
                                }
                                mPager.setCurrentItem(currentPage++, true);
                            }
                        };

                        timer = new Timer(); // This will create a new Thread
                        timer.schedule(new TimerTask() { // task to be scheduled
                            @Override
                            public void run() {
                                handler.post(Update);
                            }
                        }, DELAY_MS, PERIOD_MS);






                    }
                    else
                    {
                        progress.cancel();
                        //ToDo

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
            public void onFailure(Call<HomeScreenResponse> call, Throwable t) {
                progress.cancel();
            }
        });
    }


    private  void  CheckStatus(){

        SharedPreferences sp = getSharedPreferences("UserLog",0);
        String CustKey =  sp.getString("CustKey",null);

        apiInterface.CheckAccountStatus(CustKey).enqueue(new Callback<CheckAccountStatusResponse>() {
            @Override
            public void onResponse(Call<CheckAccountStatusResponse> call, retrofit2.Response<CheckAccountStatusResponse> response) {

                if (response.isSuccessful() && response.code() == 200) {
                    assert response.body() != null;
                    if (response.body().getCode().equalsIgnoreCase("0")) {

                        List<CheckAccountStatusResponse.ResultArray> result = response.body().getResponse();
                        for (int i = 0; i < result.size(); i++) {

                            if(result.get(i).getAccountStatus().equalsIgnoreCase("false"))
                            {
                                               Intent intent = new Intent(Home.this,AlertActivity.class);
                                               startActivity(intent);
                                               finish();
                            }
                            else {
                                LoadBanner ();
                            }



                        }



                    }
                    else
                    {
                        progress.cancel();
                        //ToDo

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
            public void onFailure(Call<CheckAccountStatusResponse> call, Throwable t) {
                progress.cancel();
            }
        });


    }


    public void forceCrash(View view) {
        throw new RuntimeException("This is a crash");
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        moveTaskToBack(true);
    }
}