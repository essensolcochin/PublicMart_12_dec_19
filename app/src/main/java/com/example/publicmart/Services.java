package com.example.publicmart;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import dmax.dialog.SpotsDialog;

public class Services extends BaseActivity {

    ViewFlipper viewFlipper;
    int image[] = {R.drawable.ad, R.drawable.travelalert};
    LinearLayout flight, train, boat,tour,travel,gun,insure;
    TextView flightTXT, trainTXT, boatTXT, tourTXT, travelTXT, gunTXT, insureTXT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FrameLayout contentFrameLayout = (FrameLayout) findViewById(R.id.content_frame);
        getLayoutInflater().inflate(R.layout.activity_services_ed, contentFrameLayout);


        flightTXT = (TextView) findViewById(R.id.flight);
        trainTXT = (TextView) findViewById(R.id.train);
        boatTXT = (TextView) findViewById(R.id.boat);
        tourTXT = (TextView) findViewById(R.id.tour);
        travelTXT = (TextView) findViewById(R.id.travel);
        gunTXT = (TextView) findViewById(R.id.gun);
        insureTXT = (TextView) findViewById(R.id.insure);
        tour = (LinearLayout)findViewById(R.id.tour_package);
        travel =(LinearLayout)findViewById(R.id.travel_guide);
        gun=(LinearLayout)findViewById(R.id.gun_licence);
        insure=(LinearLayout)findViewById(R.id.insurance);



        Typeface custom_font = Typeface.createFromAsset(getAssets(), "fonts/curvy.ttf");

        flightTXT.setTypeface(custom_font);
        trainTXT.setTypeface(custom_font);
        boatTXT.setTypeface(custom_font);
        tourTXT.setTypeface(custom_font);
        travelTXT.setTypeface(custom_font);
        gunTXT.setTypeface(custom_font);
        insureTXT.setTypeface(custom_font);



        final SpotsDialog progress = new SpotsDialog(Services.this,R.style.Custom);


        progress.show();

        Runnable progressRunnable = new Runnable() {

            @Override
            public void run() {


                progress.cancel();
            }
        };

        Handler pdCanceller = new Handler();
        pdCanceller.postDelayed(progressRunnable, 2000);

//        android.support.v7.widget.Toolbar tb=getToolBar();
//        txtxmpny=(TextView)tb.findViewById(R.id.appname);
//        txtxmpny.setText("Services");


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
        tour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplication(), "Coming Soon",Toast.LENGTH_LONG).show();

            }
        });

        travel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplication(), "Coming Soon",Toast.LENGTH_LONG).show();
            }
        });
        gun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplication(), "Coming Soon",Toast.LENGTH_LONG).show();
            }
        });
        insure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplication(), "Coming Soon",Toast.LENGTH_LONG).show();
            }
        });


    }
}
