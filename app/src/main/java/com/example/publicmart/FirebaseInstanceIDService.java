package com.example.publicmart;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;

import android.graphics.Color;


import android.os.Build;

import android.support.v4.app.NotificationCompat;
import android.util.Log;


import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Random;


public class FirebaseInstanceIDService  extends FirebaseMessagingService {

    NotificationManager notificationManager;
    String CHANNEL_ID = "Notification_Channel";
    @Override
    public void onNewToken(String s) {
        super.onNewToken(s);
        Log.e("NEW_TOKEN",s);

//        SharedPreferences SaveToken =   getSharedPreferences("GetToken",MODE_PRIVATE);
//        SharedPreferences.Editor editor =SaveToken.edit();
//        editor.putString("Token",s);
//        editor.apply();
    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);



        notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        int notificationId = new Random().nextInt(60000);
        //Setting up Notification channels for android O and above

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {


            NotificationCompat.Builder notification = new NotificationCompat.Builder(this, CHANNEL_ID)
                    .setContentTitle(remoteMessage.getNotification().getTitle())
                    .setContentText(remoteMessage.getNotification().getBody())
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setChannelId(CHANNEL_ID)
//                    .setContentIntent(pendingIntent);
                    .setVibrate(new long[]{0,400,200,400});
            notificationManager.notify(notificationId, notification.build());


            NotificationChannel channel = new NotificationChannel(
                    CHANNEL_ID,
                    "Publicmart",
                    NotificationManager.IMPORTANCE_DEFAULT);
            notificationManager.createNotificationChannel(channel);
        }




        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle(remoteMessage.getNotification().getTitle())
                        .setContentText(remoteMessage.getNotification().getBody())
//                        .setContentIntent(pendingIntent)
                        .setVibrate(new long[]{0,400,200,400});


        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mBuilder.setSmallIcon(R.drawable.publicmartlogo);
            mBuilder.setColor(Color.parseColor("#FFFFFF"));
        } else {
            mBuilder.setSmallIcon(R.mipmap.ic_launcher);
        }


        NotificationManager mNotificationManager = (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(notificationId, mBuilder.build());









//        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
//        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this, CHANNEL_ID)
//                .setSmallIcon(R.mipmap.ic_launcher)  //a resource for your custom small icon
//                .setContentTitle(remoteMessage.getNotification().getTitle())
//                .setContentText(remoteMessage.getNotification().getBody())
//                .setAutoCancel(true)
//                .setSound(defaultSoundUri);
//
//        NotificationManager notificationManager =
//                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
//
//        notificationManager.notify(notificationId /* ID of notification */, notificationBuilder.build());



    }
//    @RequiresApi(api = Build.VERSION_CODES.O)
//    private void setupChannels(){
//        CharSequence adminChannelName = "Publicmart";
//        String adminChannelDescription = "Shoppingapp";
//
//        NotificationChannel adminChannel;
//        adminChannel = new NotificationChannel(CHANNEL_ID, adminChannelName, NotificationManager.IMPORTANCE_LOW);
//        adminChannel.setDescription(adminChannelDescription);
//        adminChannel.enableLights(true);
//        adminChannel.setLightColor(Color.RED);
//        adminChannel.enableVibration(true);
//        if (notificationManager != null) {
//            notificationManager.createNotificationChannel(adminChannel);
//        }
//    }

    }



