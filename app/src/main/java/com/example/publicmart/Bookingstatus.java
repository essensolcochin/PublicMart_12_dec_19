package com.example.publicmart;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.crashlytics.android.Crashlytics;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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