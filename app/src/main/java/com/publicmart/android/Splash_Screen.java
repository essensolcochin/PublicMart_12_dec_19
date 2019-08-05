package com.publicmart.android;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class Splash_Screen extends AppCompatActivity {
    public ImageView splash;
    Boolean isLogged;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash__screen);
        splash = (ImageView) findViewById(R.id.pm_logo);
        Animation myanim = AnimationUtils.loadAnimation(this, R.anim.mytransmission);
        SharedPreferences settings1 = getSharedPreferences("UserLog", 0);
        isLogged = settings1.getBoolean("LoggedUser", false);
        Log.e("checking boolllllll",""+isLogged);

        if (isLogged) {
            final Intent i = new Intent(this, Home.class);
            Thread timer = new Thread() {
                public void run() {
                    try {
                        sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        startActivity(i);
                        finish();
                    }
                }
            };
            timer.start();
        } else {
            final Intent i = new Intent(this, MainActivity.class);
            Thread timer = new Thread() {
                public void run() {
                    try {
                        sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        startActivity(i);
                        finish();
                    }
                }
            };
            timer.start();
        }


    }
}
