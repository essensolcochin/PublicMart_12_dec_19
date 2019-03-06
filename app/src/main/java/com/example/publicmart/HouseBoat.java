package com.example.publicmart;


import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.TextInputLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.ViewFlipper;

import com.crashlytics.android.Crashlytics;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import io.fabric.sdk.android.Fabric;

import static io.fabric.sdk.android.services.concurrency.AsyncTask.init;

public class HouseBoat extends BaseActivity {
    String array_membrno[] = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};

    String array_day[] = {"DD", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10",
            "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30",
            "31"};
    String array_Mnth[] = {"MM", "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
    String array_Year[] = {"2019"};
    Spinner membersno, days, months, years;
///gggggg

    private static final Integer[] IMAGES = {R.drawable.houseboatwall,R.drawable.houseboatwalll};
    private static ViewPager mPager;
    private ArrayList<Integer> ImagesArray = new ArrayList<Integer>();
    private static int NUM_PAGES = 0;
    private static int currentPage = 0;

    TextInputLayout guest,bemail,bcontact;
    Button submit_boat;
    EditText passengr_name,email_id,Contact_no;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FrameLayout contentFrameLayout = (FrameLayout) findViewById(R.id.content_frame);
        getLayoutInflater().inflate(R.layout.activity_house_boat_ed, contentFrameLayout);

        Fabric.with(this, new Crashlytics());
        mPager = (ViewPager) findViewById(R.id.viewflipper);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        membersno = findViewById(R.id.noofmembers);
        days = findViewById(R.id.day);
        months = findViewById(R.id.month);
        years = findViewById(R.id.year);
        submit_boat=(Button)findViewById(R.id.boat_submit);

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

        guest = (TextInputLayout) findViewById(R.id.guest_input_layout);
        guest.setHint("Guest Name");

        bemail = (TextInputLayout) findViewById(R.id.bemail_input_layout);
        bemail.setHint("Enter Your Email ID");

        bcontact = (TextInputLayout) findViewById(R.id.bcontact_input_layout);
        bcontact.setHint("Enter Your Contact No");


        submit_boat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



        if (TextUtils.isEmpty(passengr_name.getText()))
        {
            passengr_name.setError("Field is Mandatory");
        }
        else if(TextUtils.isEmpty(email_id.getText()))
        {
            email_id.setError("Field is Mandatory");
        }
        else if(TextUtils.isEmpty(Contact_no.getText()))
        {
            Contact_no.setError("Field is Mandatory");
        }
        else
        {


        }

            }
        });



    }
    public void forceCrash(View view) {
        throw new RuntimeException("This is a crash");
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

