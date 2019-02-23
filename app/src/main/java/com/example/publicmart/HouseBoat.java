package com.example.publicmart;


import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.ViewFlipper;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class HouseBoat extends BaseActivity {
    String array_membrno[] = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};

    String array_day[] = {"DD", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10",
            "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30",
            "31"};
    String array_Mnth[] = {"MM", "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
    String array_Year[] = {"2019"};
    Spinner membersno, days, months, years;


    private static final Integer[] IMAGES = {R.drawable.houseboatwall,R.drawable.houseboatwalll};
    private static ViewPager mPager;
    private ArrayList<Integer> ImagesArray = new ArrayList<Integer>();
    private static int NUM_PAGES = 0;
    private static int currentPage = 0;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FrameLayout contentFrameLayout = (FrameLayout) findViewById(R.id.content_frame);
        getLayoutInflater().inflate(R.layout.activity_house_boat_ed, contentFrameLayout);
        mPager = (ViewPager) findViewById(R.id.viewflipper);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        membersno = findViewById(R.id.noofmembers);
        days = findViewById(R.id.day);
        months = findViewById(R.id.month);
        years = findViewById(R.id.year);

        final ArrayAdapter<String> spinner_adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, array_membrno);
        spinner_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        membersno.setAdapter(spinner_adapter);

        final ArrayAdapter<String> spinner_adapter_day = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, array_day);
        spinner_adapter_day.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        days.setAdapter(spinner_adapter_day);

        final ArrayAdapter<String> spinner_adapter_month = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, array_Mnth);
        spinner_adapter_month.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        months.setAdapter(spinner_adapter_month);

        final ArrayAdapter<String> spinner_adapter_year = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, array_Year);
        spinner_adapter_year.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        years.setAdapter(spinner_adapter_year);

        init();

    }
    private void init() {


        for (int i = 0; i < IMAGES.length; i++)
            ImagesArray.add(IMAGES[i]);




        PagerAdapter adapter = new SlidingImage_Adapter(HouseBoat.this, ImagesArray);
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

