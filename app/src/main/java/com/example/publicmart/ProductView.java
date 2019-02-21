package com.example.publicmart;


import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;

import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.TextView;

import java.util.ArrayList;



public class ProductView extends BaseActivity {

    TextView txtxmpny;

    private static ViewPager mPager;
    private static int currentPage = 0;
    private static int NUM_PAGES = 0;
    private static final Integer[] IMAGES= {R.drawable.ad,R.drawable.ad,R.drawable.ad2};
    private ArrayList<Integer> ImagesArray = new ArrayList<Integer>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FrameLayout contentFrameLayout = (FrameLayout) findViewById(R.id.content_frame);
        getLayoutInflater().inflate(R.layout.activity_product_view, contentFrameLayout);

        init();
//        android.support.v7.widget.Toolbar tb=getToolBar();
//        txtxmpny=(TextView)tb.findViewById(R.id.appname);
//        txtxmpny.setText("Products");
    }

    private void init() {


        for (int i = 0; i < IMAGES.length; i++)
            ImagesArray.add(IMAGES[i]);

        mPager = (ViewPager) findViewById(R.id.pager);


        PagerAdapter adapter = new SlidingImage_Adapter(ProductView.this, ImagesArray);
        mPager.setAdapter(adapter);


       // mPager.setAdapter(new SlidingImage_Adapter(ProductView.this, ImagesArray));


        final float density = getResources().getDisplayMetrics().density;


        NUM_PAGES = IMAGES.length;




    }




}
