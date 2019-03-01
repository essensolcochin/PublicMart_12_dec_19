package com.example.publicmart;


import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.view.View;
import android.widget.FrameLayout;

import com.crashlytics.android.Crashlytics;

import io.fabric.sdk.android.Fabric;


public class Bookingstatus extends BaseActivity {


    BookingStatusAdapter_ adapter_;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FrameLayout contentFrameLayout = (FrameLayout) findViewById(R.id.content_frame);
        getLayoutInflater().inflate(R.layout.activity_bookingstatus_ed, contentFrameLayout);
        Fabric.with(this, new Crashlytics());

        String[] data = {"1", "2", "3", "4", "5", "6", "7", "8", "9"};

        // set up the RecyclerView
        RecyclerView recyclerView = findViewById(R.id.bookingRecycler);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter_ = new BookingStatusAdapter_(this, data);

        recyclerView.setAdapter(adapter_);


    }
    public void forceCrash(View view) {
        throw new RuntimeException("This is a crash");
    }

}