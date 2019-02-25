package com.example.publicmart;

import android.graphics.Typeface;
import android.os.Handler;
import android.support.design.widget.TextInputLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class TrainTicket extends BaseActivity {

    String array_city[] = {"Select City","Thiruvananthapuram Central","Ernakulam Junction (South)","Kozhikode Railway Station","Kollam Junction"};
    String array_day[] = {"DD", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10",
            "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30",
            "31"};
    String array_Mnth[] = {"MM", "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
    String array_Year[] = {"2019"};
    String array_BYear[] = {"YY", "1985", "1986", "1986", "1987", "1988", "1989", "1990", "1991", "1992",
            "1993", "1994", "1995", "1996", "1997", "1998", "1999", "2000", "2001", "2002"
            , "2003", "2004", "2005", "2006", "2007", "2008", "2009", "2010", "2011", "2012",
            "2011", "2012", "2013", "2014", "2015", "2016", "2017", "2018"};

    Spinner citycode,citycode2,days,months,years, bday,bmonth,byear;

    TextView header1,header2,header3,header4,header5,header6,header7;

    private static ViewPager mPager;
    private static int currentPage = 0;
    private static int NUM_PAGES = 0;
    private static final String[] Text= {"Easy Cancellation Process","Convenience charge 50₹"};

    private ArrayList<String> TextArray = new ArrayList<String>();
    TextInputLayout trname,tremail,trcontact;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FrameLayout contentFrameLayout = (FrameLayout) findViewById(R.id.content_frame);
        getLayoutInflater().inflate(R.layout.activity_train_ticket_ed, contentFrameLayout);

        toolbar =  findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);





        citycode = findViewById(R.id.city);
        citycode2 = findViewById(R.id.city2);
        days = findViewById(R.id.day);
        months = findViewById(R.id.month);
        years = findViewById(R.id.year);
        bday = findViewById(R.id.bday);
        bmonth = findViewById(R.id.bmonth);
        byear = findViewById(R.id.byear);





        header2 = findViewById(R.id.header2);
        header3 = findViewById(R.id.header3);
        header4 = findViewById(R.id.header4);
        header5 = findViewById(R.id.header5);


        Typeface custom_font2 = Typeface.createFromAsset(getAssets(),  "fonts/GravityBold.otf");

        header2.setTypeface(custom_font2);
        header3.setTypeface(custom_font2);
        header4.setTypeface(custom_font2);
        header5.setTypeface(custom_font2);







        final ArrayAdapter<String> spinner_adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, array_city);
        spinner_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        citycode.setAdapter(spinner_adapter);
        citycode2.setAdapter(spinner_adapter);
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

        final ArrayAdapter<String> spinner_adapter_bday = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, array_day);
        spinner_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        bday.setAdapter(spinner_adapter_bday);
        final ArrayAdapter<String> spinner_adapter_mbday = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, array_Mnth);
        spinner_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        bmonth.setAdapter(spinner_adapter_mbday);

        final ArrayAdapter<String> spinner_adapter_yday = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, array_BYear);
        spinner_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        byear.setAdapter(spinner_adapter_yday);

        trname = (TextInputLayout) findViewById(R.id.name_input_layout);
        trname.setHint("Enter Your Name");

        tremail = (TextInputLayout) findViewById(R.id.input_layout_email);
        tremail.setHint("Enter Your Email ID");

        trcontact = (TextInputLayout) findViewById(R.id.input_layout_contact);
        trcontact.setHint("Enter Your Contact No");



        init();
    }

    private void init() {


        for (int i = 0; i < Text.length; i++)
            TextArray.add(Text[i]);



        mPager = findViewById(R.id.viewflipper1);

        PagerAdapter adapter = new SlidingText_Adapter_Train(TrainTicket.this, TextArray);
        mPager.setAdapter(adapter);
        Log.e("textsizeeeee","flight "+TextArray.size());






        NUM_PAGES = Text.length;


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
        }, 1500, 5000);




    }
}
