package com.example.publicmart;

import android.content.Intent;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.baoyachi.stepview.HorizontalStepView;
import com.baoyachi.stepview.bean.StepBean;
import com.crashlytics.android.Crashlytics;

import java.util.ArrayList;
import java.util.List;

import io.fabric.sdk.android.Fabric;

public class OrderStatus extends BaseActivity {
    HorizontalStepView horizontalStepView;
    LinearLayout ll;
    TextView status,status2, status1;
    StepBean stepBean0,stepBean1,stepBean2,stepBean3;
    Button pay;
    TextView txtxmpny;
    OrderStatusAdapter_ adapter_;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FrameLayout contentFrameLayout = (FrameLayout) findViewById(R.id.content_frame);
        getLayoutInflater().inflate(R.layout.activity_order_status_ed, contentFrameLayout);

        Fabric.with(this, new Crashlytics());



        String[] data = {"1", "2", "3", "4", "5", "6", "7", "8", "9"};

        // set up the RecyclerView
        RecyclerView recyclerView = findViewById(R.id.orderRecycler);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter_ = new OrderStatusAdapter_(this, data);

        recyclerView.setAdapter(adapter_);


    }

    public void forceCrash(View view) {
        throw new RuntimeException("This is a crash");
    }
}
