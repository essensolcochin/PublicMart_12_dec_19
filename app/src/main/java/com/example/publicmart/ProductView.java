package com.example.publicmart;


import android.graphics.Typeface;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.crashlytics.android.Crashlytics;

import java.util.ArrayList;

import io.fabric.sdk.android.Fabric;


public class ProductView extends BaseActivity {

    TextView Product_Name,Short_Desc,Product_Details,detailed_description,Material,material_care;

    private static ViewPager mPager;
    private static int currentPage = 0;
    private static int NUM_PAGES = 0;
    private static final String[] IMAGES= {"http://192.168.0.30:7899/images/1.jpg",
            "https://assets.myntassets.com/h_1440,q_100,w_1080/v1/assets/images/7715805/2018/10/30/b7e47f34-55dc-4c8e-986d-7e9a8166a9e41540883421915-Mitera-Magenta-Silk-Blend-Solid-Kanjeevaram-Saree-9711540883421780-3.jpg",
            "https://assets.myntassets.com/h_1440,q_100,w_1080/v1/assets/images/7715805/2018/10/30/6492f428-e370-4736-8fd2-f97a48f4912f1540883421894-Mitera-Magenta-Silk-Blend-Solid-Kanjeevaram-Saree-9711540883421780-4.jpg"};


    private ArrayList<String> ImagesArray = new ArrayList<String>();
    LinearLayout wishlist;
    Button placeorder;
    String CategoryKey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FrameLayout contentFrameLayout = (FrameLayout) findViewById(R.id.content_frame);
        getLayoutInflater().inflate(R.layout.activity_product_view, contentFrameLayout);

        Fabric.with(this, new Crashlytics());
        init();
//        android.support.v7.widget.Toolbar tb=getToolBar();
//        txtxmpny=(TextView)tb.findViewById(R.id.appname);
//        txtxmpny.setText("Products");





        Product_Name=(TextView)findViewById(R.id.product_name);
        Short_Desc=(TextView)findViewById(R.id.product_desc);
        Product_Details =(TextView)findViewById(R.id.product_detail);
        detailed_description =(TextView)findViewById(R.id.detail_desc);
        Material=(TextView)findViewById(R.id.material);
        material_care=(TextView)findViewById(R.id.materialcare);
        wishlist = findViewById(R.id.bottomframe);
        placeorder = findViewById(R.id.place_order);

        Typeface custom_font = Typeface.createFromAsset(getAssets(),  "fonts/CODEBold.otf");


        Typeface custom_font2 = Typeface.createFromAsset(getAssets(),  "fonts/GravityBold.otf");
        detailed_description.setTypeface(custom_font2);
        Product_Details.setTypeface(custom_font2);
        Short_Desc.setTypeface(custom_font2);
        Product_Name.setTypeface(custom_font2);
        Material.setTypeface(custom_font2);
        material_care.setTypeface(custom_font2);


        wishlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(getApplication(), "Product added to Wishlist",Toast.LENGTH_LONG).show();
            }
        });

        placeorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplication(), "Your Order Has Been Placed",Toast.LENGTH_LONG).show();
            }
        });

    }
    public void forceCrash(View view) {
        throw new RuntimeException("This is a crash");
    }
    private void init() {


        for (String IMAGE : IMAGES)

            ImagesArray.add(IMAGE);

        mPager = (ViewPager) findViewById(R.id.pager);


        PagerAdapter adapter = new SlidingImage_Adapter_Product(ProductView.this, ImagesArray);
        mPager.setAdapter(adapter);

        NUM_PAGES = IMAGES.length;




    }




}
