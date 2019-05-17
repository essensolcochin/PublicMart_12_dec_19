package com.essensol.publicmart;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;

import com.crashlytics.android.Crashlytics;

import io.fabric.sdk.android.Fabric;

public class Shopping_List extends BaseActivity {

    Shopping_List_Adapter shopping_list_adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FrameLayout contentFrameLayout = (FrameLayout) findViewById(R.id.content_frame);
        getLayoutInflater().inflate(R.layout.activity_shopping__list, contentFrameLayout);

        Fabric.with(this, new Crashlytics());

        RecyclerView shoopingrecycler = findViewById(R.id.shoppingRecycler);

        String[] data = {"1","2","3"};

        shoopingrecycler.setLayoutManager(new LinearLayoutManager(this));
        shopping_list_adapter = new Shopping_List_Adapter(this, data);

        shoopingrecycler.setAdapter(shopping_list_adapter);



    }
    public void forceCrash(View view) {
        throw new RuntimeException("This is a crash");
    }

}