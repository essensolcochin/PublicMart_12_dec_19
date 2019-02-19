package com.example.publicmart;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toolbar;
import android.widget.ViewFlipper;

public class FlightTicket extends BaseActivity{

    android.support.v7.widget.Toolbar toolbar;

    String array_city[] = {"Select City","Agatti Island(AGX)","Ahmedabad(AMD)","Aizawl(AJL)","Akola(AKD)","Along(IXV)","Lucknow(LKO)","Ludhiana(LUH)","Bangalore(BLR)","Delhi(DEL)","Ernakulam(COK)"};
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

    RadioGroup rg;
    RadioButton radioButton;


    TextView txtxmpny;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FrameLayout contentFrameLayout = (FrameLayout) findViewById(R.id.content_frame);
        getLayoutInflater().inflate(R.layout.activity_flight_ticket_ed, contentFrameLayout);



        toolbar =  findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        rg = (RadioGroup)findViewById(R.id.radiogrp);


        citycode = findViewById(R.id.city);
        citycode2 = findViewById(R.id.city2);
        days = findViewById(R.id.day);
        months = findViewById(R.id.month);
        years = findViewById(R.id.year);
        bday = findViewById(R.id.bday);
        bmonth = findViewById(R.id.bmonth);
        byear = findViewById(R.id.byear);


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






    }




    public void rbclick(View v)
    {
        int radiobuttonid = rg.getCheckedRadioButtonId();
        radioButton = findViewById(radiobuttonid);

    }


}
