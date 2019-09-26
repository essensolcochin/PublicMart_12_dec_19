package com.publicmart.essensol.Utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import com.publicmart.essensol.MyApplication;
import com.publicmart.essensol.Utility;

public class ConnectivityManagerClass extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {


        boolean isVisible = MyApplication.isActivityVisible();
        if(isVisible){

            try {
                ConnectivityManager connectivityManager = (ConnectivityManager)context
                        .getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

                // Check internet connection and accrding to state change the
                // text of activity by calling method
                if (networkInfo != null && networkInfo.isConnected()) {
                    Log.e("Connected","  ");
//                    new MainActivity().getFirebaseToken();
                } else {

                    Log.e("Not Connected","  ");
                    Utility.ShowCustomToast("You are offline Check your Connectivity Settings",context);
//                    new MainActivity().changeNetworkStatus(false);

                }
            } catch (Exception e) {
                e.printStackTrace();
            }



        }


    }


}
