package com.example.publicmart;


import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.ViewFlipper;

public class HouseBoat extends BaseActivity {
    String array_membrno[] = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};

    String array_day[] = {"DD", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10",
            "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30",
            "31"};
    String array_Mnth[] = {"MM", "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
    String array_Year[] = {"2019"};
    Spinner membersno, days, months, years;
    int image[] = {R.drawable.houseboatwall,R.drawable.houseboatwalll};
    ViewFlipper viewFlipper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FrameLayout contentFrameLayout = (FrameLayout) findViewById(R.id.content_frame);
        getLayoutInflater().inflate(R.layout.activity_house_boat_ed, contentFrameLayout);
        viewFlipper = (ViewFlipper)findViewById(R.id.viewflipper);

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


        for (int image: image) {
            flipperimage(image);
        }

    }
    void flipperimage(int image)
    {
        ImageView imageView = new ImageView(this);
        imageView.setBackgroundResource(image);
        viewFlipper.addView(imageView);
        viewFlipper.setFlipInterval(4000);
        viewFlipper.setAutoStart(true);

        viewFlipper.setOutAnimation(this,android.R.anim.slide_out_right);
        viewFlipper.setInAnimation(this,android.R.anim.slide_in_left);
    }
    }

