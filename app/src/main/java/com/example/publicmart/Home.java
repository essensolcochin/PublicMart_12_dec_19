package com.example.publicmart;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.CardView;

import android.view.View;


import android.webkit.WebView;
import android.widget.FrameLayout;

import android.widget.LinearLayout;
import android.widget.TextView;



import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import dmax.dialog.SpotsDialog;

public class Home extends BaseActivity {

    private static ViewPager mPager;
    private static int currentPage = 0;
    private static int NUM_PAGES = 0;
    LinearLayout services,product,orderstat,booking;
    CardView profile;
    TextView txtxmpny,Product,sevices,order,shoplist,bookingstat,credit,report,prof;
    TextView view;

    private static final Integer[] IMAGES= {R.drawable.ad,R.drawable.ad,R.drawable.ad2};
    private ArrayList<Integer> ImagesArray = new ArrayList<Integer>();


    int image[] = {R.drawable.ad};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FrameLayout contentFrameLayout = (FrameLayout) findViewById(R.id.content_frame);
        getLayoutInflater().inflate(R.layout.activity_home_test, contentFrameLayout);


        final SpotsDialog progress = new SpotsDialog(Home.this,R.style.Custom);


        progress.show();

        Runnable progressRunnable = new Runnable() {

            @Override
            public void run() {


                progress.cancel();
                init();

            }
        };

        Handler pdCanceller = new Handler();
        pdCanceller.postDelayed(progressRunnable, 2000);





//        android.support.v7.widget.Toolbar tb=getToolBar();
//        txtxmpny=(TextView)tb.findViewById(R.id.appname);
//        txtxmpny.setText("Home");

        product=(LinearLayout) findViewById(R.id.Product_layout);
        orderstat =(LinearLayout) findViewById(R.id.order);
        booking=(LinearLayout) findViewById(R.id.bookingstat);

        booking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Home.this, Bookingstatus.class);
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



        Typeface custom_font = Typeface.createFromAsset(getAssets(),  "fonts/curvy.ttf");



        Product=(TextView)findViewById(R.id.product);
        sevices=(TextView)findViewById(R.id.services);
        order=(TextView)findViewById(R.id.Order);
        shoplist=(TextView)findViewById(R.id.shop);
        bookingstat=(TextView)findViewById(R.id.book);
        credit=(TextView)findViewById(R.id.credit);

        view=findViewById(R.id.textContent);

        String text;
        text =   "ACCT NAME   : PUBLIC MART\n"+
                "ACCT NO     : 50200034752049\n"+
                "IFSC CODE   : HDFC0000628\n"+
                "BRANCH NAME : ALAPPUZHA\n";

        view.setText(text);


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
//

        product.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home.this, Products.class);
                startActivity(intent);
            }
        });
//
//        booking = findViewById(R.id.bookstat);
//        booking.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(Home.this, Bookingstatus.class);
//                startActivity(intent);
//            }
//        });

//        for (int image: image) {
//            flipperimage(image);
//        }

//        orderstat = (LinearLayout)findViewById(R.id.orderstatus);
//        orderstat.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(Home.this, OrderStatus.class);
//                startActivity(intent);
//            }
//        });

    }

    private void init() {


        for (int i = 0; i < IMAGES.length; i++)
            ImagesArray.add(IMAGES[i]);

        mPager = findViewById(R.id.viewflipper);

        PagerAdapter adapter = new SlidingImage_Adapter_Home(Home.this, ImagesArray);
        mPager.setAdapter(adapter);


        // mPager.setAdapter(new SlidingImage_Adapter(ProductView.this, ImagesArray));


        final float density = getResources().getDisplayMetrics().density;

        NUM_PAGES = IMAGES.length;


        // Auto start of viewpager
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage == NUM_PAGES) {
                    currentPage = 0;
                }
                mPager.setCurrentItem(currentPage++, true);
            }
        };
        Timer swipeTimer = new Timer();
        swipeTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(Update);
            }
        }, 5000, 5000);




    }

}