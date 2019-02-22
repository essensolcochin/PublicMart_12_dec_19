package com.example.publicmart;


import android.graphics.Typeface;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;

import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.TextView;

import java.util.ArrayList;



public class ProductView extends BaseActivity {

    TextView Product_Name,Short_Desc,Product_Details,detailed_description,Material,material_care;

    private static ViewPager mPager;
    private static int currentPage = 0;
    private static int NUM_PAGES = 0;
    private static final Integer[] IMAGES= {R.drawable.sa1,R.drawable.sa2,R.drawable.sa3};
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



        Product_Name=(TextView)findViewById(R.id.product_name);
        Short_Desc=(TextView)findViewById(R.id.product_desc);
        Product_Details =(TextView)findViewById(R.id.product_detail);
         detailed_description =(TextView)findViewById(R.id.detail_desc);
        detailed_description =(TextView)findViewById(R.id.detail_desc);
        detailed_description =(TextView)findViewById(R.id.detail_desc);
        Material=(TextView)findViewById(R.id.material);
        material_care=(TextView)findViewById(R.id.materialcare);


        Typeface custom_font = Typeface.createFromAsset(getAssets(),  "fonts/CODEBold.otf");


        Typeface custom_font2 = Typeface.createFromAsset(getAssets(),  "fonts/GravityBold.otf");
        detailed_description.setTypeface(custom_font2);
        Product_Details.setTypeface(custom_font2);
        Short_Desc.setTypeface(custom_font2);
        Product_Name.setTypeface(custom_font2);
        Material.setTypeface(custom_font2);
        material_care.setTypeface(custom_font2);
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
