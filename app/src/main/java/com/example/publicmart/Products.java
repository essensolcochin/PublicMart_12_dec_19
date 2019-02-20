package com.example.publicmart;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ViewFlipper;

public class Products extends BaseActivity {

    ViewFlipper viewFlipper;

    LinearLayout fashion;

TextView fash,spice,nut,weight;
    ProductMenuAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FrameLayout contentFrameLayout = (FrameLayout) findViewById(R.id.content_frame);
        getLayoutInflater().inflate(R.layout.activity_products_recycle, contentFrameLayout);


//        android.support.v7.widget.Toolbar tb=getToolBar();



//        fash =findViewById(R.id.fashion);
//        spice =findViewById(R.id.spice);
//        nut =findViewById(R.id.nutri);
//        weight =findViewById(R.id.weight);
//        Typeface custom_font = Typeface.createFromAsset(getAssets(),  "fonts/curvy.ttf");
//
//        fash.setTypeface(custom_font);
//        spice.setTypeface(custom_font);
//        nut.setTypeface(custom_font);
//        weight.setTypeface(custom_font);
//
//        fashion = (LinearLayout)findViewById(R.id.deal1);
//        fashion.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(Products.this, FashionCategory.class);
//                startActivity(intent);
//
//            }
//        });

        String[] data = {"1", "2", "3", "4", "5", "6", "7", "8", "9"};

        // set up the RecyclerView
        RecyclerView recyclerView = findViewById(R.id.menu);
        int numberOfColumns = 2;
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        adapter = new ProductMenuAdapter(this, data);

        recyclerView.setAdapter(adapter);


    }

}
