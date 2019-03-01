package com.example.publicmart;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;

import com.crashlytics.android.Crashlytics;

import io.fabric.sdk.android.Fabric;

public class CreditActivity extends BaseActivity {

    CreditStatusAdapter credit_adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FrameLayout contentFrameLayout = (FrameLayout) findViewById(R.id.content_frame);
        getLayoutInflater().inflate(R.layout.activity_credit, contentFrameLayout);
        Fabric.with(this, new Crashlytics());
        String[] data = {"1"};

        // set up the RecyclerView
        RecyclerView recyclerView = findViewById(R.id.creditRecycler);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        credit_adapter = new CreditStatusAdapter(this, data);

        recyclerView.setAdapter(credit_adapter);


    }

    public void forceCrash(View view) {
        throw new RuntimeException("This is a crash");
    }
}
