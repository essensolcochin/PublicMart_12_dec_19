package com.publicmart.essensol.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.crashlytics.android.Crashlytics;
import com.publicmart.essensol.R;

import io.fabric.sdk.android.Fabric;

public class TestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        Fabric.with(this, new Crashlytics());
    }
    public void forceCrash(View view) {
        throw new RuntimeException("This is a crash");
    }

}