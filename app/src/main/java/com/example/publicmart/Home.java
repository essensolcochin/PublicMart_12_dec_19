package com.example.publicmart;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toolbar;
import android.widget.ViewFlipper;

public class Home extends BaseActivity {


    LinearLayout services,product,orderstat,booking,profile;
    ViewFlipper viewFlipper;
    TextView txtxmpny,Product,sevices,order,shoplist,bookingstat;



    int image[] = {R.drawable.logopm,R.drawable.ad2};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FrameLayout contentFrameLayout = (FrameLayout) findViewById(R.id.content_frame);
        getLayoutInflater().inflate(R.layout.activity_home_test, contentFrameLayout);


//        android.support.v7.widget.Toolbar tb=getToolBar();
//        txtxmpny=(TextView)tb.findViewById(R.id.appname);
//        txtxmpny.setText("Home");

        product=(LinearLayout) findViewById(R.id.Product_layout);


        Product=(TextView)findViewById(R.id.product);
        sevices=(TextView)findViewById(R.id.services);
        order=(TextView)findViewById(R.id.Order);
        shoplist=(TextView)findViewById(R.id.shop);
        bookingstat=(TextView)findViewById(R.id.book);


        Typeface custom_font = Typeface.createFromAsset(getAssets(),  "fonts/curvy.ttf");

        Product.setTypeface(custom_font);
        sevices.setTypeface(custom_font);
        order.setTypeface(custom_font);
        shoplist.setTypeface(custom_font);
        bookingstat.setTypeface(custom_font);




        profile = findViewById(R.id.viewprofile);
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home.this, Profile.class);
                startActivity(intent);

            }
        });


        viewFlipper = (ViewFlipper)findViewById(R.id.viewflipper);

//        services = findViewById(R.id.service);
//        services.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(Home.this, Services.class);
//                startActivity(intent);
//
//            }
//        });
//

        product.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home.this, Products.class);
                startActivity(intent);
            }
        });
//
//        booking = findViewById(R.id.bookstat);
//        booking.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(Home.this, Bookingstatus.class);
//                startActivity(intent);
//            }
//        });

        for (int image: image) {
            flipperimage(image);
        }

//        orderstat = (LinearLayout)findViewById(R.id.orderstatus);
//        orderstat.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(Home.this, OrderStatus.class);
//                startActivity(intent);
//            }
//        });

    }

    void flipperimage(int image)
    {
        ImageView imageView = new ImageView(this);
        imageView.setBackgroundResource(image);
        viewFlipper.addView(imageView);
        viewFlipper.setFlipInterval(4000);
        viewFlipper.setAutoStart(true);

        viewFlipper.setOutAnimation(this,android.R.anim.slide_out_right);
        viewFlipper.setInAnimation(this,android.R.anim.slide_in_left);
    }
}
