package com.example.publicmart;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ViewFlipper;

public class Products extends BaseActivity {

    ViewFlipper viewFlipper;

    LinearLayout fashion;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FrameLayout contentFrameLayout = (FrameLayout) findViewById(R.id.content_frame);
        getLayoutInflater().inflate(R.layout.activity_products, contentFrameLayout);


//        android.support.v7.widget.Toolbar tb=getToolBar();






        fashion = (LinearLayout)findViewById(R.id.deal1);
        fashion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Products.this, FashionCategory.class);
                startActivity(intent);

            }
        });
    }

}
