package com.example.publicmart;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ViewFlipper;

public class Services extends BaseActivity {

//    ViewFlipper viewFlipper;
    int image[] = {R.drawable.ad,R.drawable.travelalert};
    LinearLayout flight,train,boat;
    TextView flightTXT,trainTXT,boatTXT,tourTXT,travelTXT,gunTXT,insureTXT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FrameLayout contentFrameLayout = (FrameLayout) findViewById(R.id.content_frame);
        getLayoutInflater().inflate(R.layout.activity_services_ed, contentFrameLayout);


        flightTXT=(TextView)findViewById(R.id.flight);
        trainTXT=(TextView)findViewById(R.id.train);
        boatTXT=(TextView)findViewById(R.id.boat);
        tourTXT=(TextView)findViewById(R.id.tour);
        travelTXT=(TextView)findViewById(R.id.travel);
        gunTXT=(TextView)findViewById(R.id.gun);
        insureTXT=(TextView)findViewById(R.id.insure);


        Typeface custom_font = Typeface.createFromAsset(getAssets(),  "fonts/curvy.ttf");

        flightTXT.setTypeface(custom_font);
        trainTXT.setTypeface(custom_font);
        boatTXT.setTypeface(custom_font);
        tourTXT.setTypeface(custom_font);
        travelTXT.setTypeface(custom_font);
        gunTXT.setTypeface(custom_font);
        insureTXT.setTypeface(custom_font);


//        android.support.v7.widget.Toolbar tb=getToolBar();
//        txtxmpny=(TextView)tb.findViewById(R.id.appname);
//        txtxmpny.setText("Services");

//        viewFlipper = (ViewFlipper)findViewById(R.id.viewflipper);
        flight = findViewById(R.id.flightticket);
        flight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Services.this, FlightTicket.class);
                startActivity(intent);
            }
        });

        train = findViewById(R.id.trainticket);
        train.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Services.this, TrainTicket.class);
                startActivity(intent);
            }
        });
        boat = findViewById(R.id.houseboat);
        boat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Services.this, HouseBoat.class);
                startActivity(intent);
            }
        });




//        for (int image: image) {
//            flipperimage(image);
//        }
    }
//    void flipperimage(int image)
//    {
//        ImageView imageView = new ImageView(this);
//        imageView.setBackgroundResource(image);
//        viewFlipper.addView(imageView);
//        viewFlipper.setFlipInterval(4000);
//        viewFlipper.setAutoStart(true);
//
//        viewFlipper.setOutAnimation(this,android.R.anim.slide_out_right);
//        viewFlipper.setInAnimation(this,android.R.anim.slide_in_left);
//    }
}
