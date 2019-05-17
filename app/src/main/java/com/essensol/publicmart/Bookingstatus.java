package com.essensol.publicmart;


import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.crashlytics.android.Crashlytics;

import java.util.ArrayList;
import java.util.List;

import io.fabric.sdk.android.Fabric;


public class Bookingstatus extends BaseActivity {

    TextView passenger_name,from,to,date,price,status;
    BookingStatusAdapter_ adapter_;

    List<BookingstatusModel> item_list;
    List<TrainBookingModel> train_list;
//    List<BookingstatusModel> item_list;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FrameLayout contentFrameLayout = (FrameLayout) findViewById(R.id.content_frame);
        getLayoutInflater().inflate(R.layout.activity_bookingstatus_ed, contentFrameLayout);
        Fabric.with(this, new Crashlytics());

        item_list = new ArrayList<>();
        train_list = new ArrayList<>();

        // set up the RecyclerView
        recyclerView = findViewById(R.id.bookingRecycler);


        recyclerView.setLayoutManager(new LinearLayoutManager(this));


//


    }






    public void forceCrash(View view) {
        throw new RuntimeException("This is a crash");
    }

}