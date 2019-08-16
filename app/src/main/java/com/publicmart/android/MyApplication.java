package com.publicmart.android;

import android.app.Application;



import io.realm.Realm;

public class MyApplication  extends Application {

    public static boolean activityVisible;

    @Override
    public void onCreate() {
        super.onCreate();

        Realm.init(this);

    }

    public static boolean isActivityVisible() {
        return activityVisible; // return true or false
    }

    public static void activityResumed() {
        activityVisible = true;// this will set true when activity resumed

    }

    public static void activityPaused() {
        activityVisible = false;// this will set false when activity paused

    }



}