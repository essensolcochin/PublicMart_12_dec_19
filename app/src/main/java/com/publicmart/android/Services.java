package com.publicmart.android;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.crashlytics.android.Crashlytics;

import java.util.ArrayList;

import io.fabric.sdk.android.Fabric;

public class Services extends BaseActivity {

    ViewFlipper viewFlipper;
    int image[] = {R.drawable.ad, R.drawable.travelalert};
    LinearLayout flight, train, boat,tour,travel,gun,insure;
    private static ViewPager mPager;
    int NUM_PAGES,currentPage=0;
    TextView flightTXT, trainTXT, boatTXT, tourTXT, travelTXT, gunTXT, insureTXT,our,services;
//    private static final Integer[] IMAGES = {R.drawable.servicebanner};
    private ArrayList<Integer> TextArray = new ArrayList<Integer>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FrameLayout contentFrameLayout = (FrameLayout) findViewById(R.id.content_frame);
        getLayoutInflater().inflate(R.layout.activity_services_ed, contentFrameLayout);
        Fabric.with(this, new Crashlytics());

        flightTXT = (TextView) findViewById(R.id.flight);

        our = (TextView) findViewById(R.id.our);

        services = (TextView) findViewById(R.id.services);

        trainTXT = (TextView) findViewById(R.id.train);
        boatTXT = (TextView) findViewById(R.id.boat);
        tourTXT = (TextView) findViewById(R.id.tour);
        travelTXT = (TextView) findViewById(R.id.travel);
        gunTXT = (TextView) findViewById(R.id.gun);
        insureTXT = (TextView) findViewById(R.id.insure);
        tour = (LinearLayout)findViewById(R.id.tour_package);
        travel =(LinearLayout)findViewById(R.id.travel_guide);
        gun=(LinearLayout)findViewById(R.id.gun_licence);
        insure=(LinearLayout)findViewById(R.id.insurance);
//        mPager=(ViewPager) findViewById(R.id.viewflipper1);



        Typeface custom_font = Typeface.createFromAsset(getAssets(), "fonts/curvy.ttf");
        Typeface custom_font1 = Typeface.createFromAsset(getAssets(), "fonts/collection.ttf");
        Typeface custom_font2 = Typeface.createFromAsset(getAssets(),  "fonts/fresh.ttf");

        our.setTypeface(custom_font2);
        services.setTypeface(custom_font1);

        flightTXT.setTypeface(custom_font);
        trainTXT.setTypeface(custom_font);
        boatTXT.setTypeface(custom_font);
        tourTXT.setTypeface(custom_font);
        travelTXT.setTypeface(custom_font);
        gunTXT.setTypeface(custom_font);
        insureTXT.setTypeface(custom_font);

//        init();

//        final SpotsDialog progress = new SpotsDialog(Services.this,R.style.Custom);
//
//
//        progress.show();
//
//        Runnable progressRunnable = new Runnable() {
//
//            @Override
//            public void run() {
//
//
//                progress.cancel();
//            }
//        };
//
//        Handler pdCanceller = new Handler();
//        pdCanceller.postDelayed(progressRunnable, 2000);

//        android.support.v7.widget.Toolbar tb=getToolBar();
//        txtxmpny=(TextView)tb.findViewById(R.id.appname);
//        txtxmpny.setText("Services");


        flight = findViewById(R.id.flightticket);
        flight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Services.this, FlightTicket.class);
                startActivity(intent);
            }
        });

        train = findViewById(R.id.trainticket);
        train.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Services.this, TrainTicket.class);
                startActivity(intent);
            }
        });
        boat = findViewById(R.id.houseboat);
        boat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Services.this, HouseBoat.class);
                startActivity(intent);
            }
        });
        tour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Coming Soon",Toast.LENGTH_SHORT).show();
            }
        });

        travel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Coming Soon",Toast.LENGTH_SHORT).show();
            }
        });
        gun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Coming Soon",Toast.LENGTH_SHORT).show();
            }
        });
        insure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Coming Soon",Toast.LENGTH_SHORT).show();
            }
        });


    }


    public void forceCrash(View view) {
        throw new RuntimeException("This is a crash");
    }

    public void showToast()
    {
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.toast_layout,(ViewGroup) findViewById(R.id.toast_root));
        Toast toast =new Toast(getApplicationContext());
        toast.setGravity(Gravity.BOTTOM,0,0);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(layout);
        toast.show();

    }



//    private void init() {
//
//        for (int i = 0; i < IMAGES.length; i++)
//
//
//        TextArray.add(IMAGES[i]);
//
//        mPager = findViewById(R.id.viewflipper1);
//
//        PagerAdapter adapter = new SlidingText_Adapter_(Services.this, TextArray);
//        mPager.setAdapter(adapter);
//        Log.e("textsizeeeee", "flight " + TextArray.size());
//
//
////        NUM_PAGES = TextArray.size();
////        // Auto start of viewpager
////        final Handler handler = new Handler();
////        final Runnable Update = new Runnable() {
////            public void run() {
////                if (currentPage == NUM_PAGES) {
////                    currentPage = 0;
////                }
////                mPager.setCurrentItem(currentPage++, true);
////            }
////        };
////        Timer swipeTimer = new Timer();
////        swipeTimer.schedule(new TimerTask() {
////            @Override
////            public void run() {
////                handler.post(Update);
////            }
////        }, 1500, 5000);
//
//    }





}
