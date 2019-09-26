package com.publicmart.essensol.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.google.firebase.iid.FirebaseInstanceId;
import com.publicmart.essensol.ModelClasses.RealmShopModel;
import com.publicmart.essensol.R;

import java.io.IOException;

import io.realm.Realm;
import io.realm.RealmResults;

public class AlertActivity extends AppCompatActivity {

    LinearLayout logout;
    private RealmResults<RealmShopModel> cartSIZE;
    private Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alert);

        logout=findViewById(R.id.logout);

        realm = Realm.getDefaultInstance();


        cartSIZE = realm.where(RealmShopModel.class).findAll();
        cartSIZE.load();



        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            FirebaseInstanceId.getInstance().deleteInstanceId();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
//                        SharedPreferences SaveToken =   getSharedPreferences("GetToken",MODE_PRIVATE);
//                        SharedPreferences.Editor edit =SaveToken.edit();
//                        edit.putString("Token","");
//                        edit.apply();


                SharedPreferences sp = getSharedPreferences("UserLog",MODE_PRIVATE);
                SharedPreferences.Editor editor = sp.edit();
                editor.putBoolean("LoggedUser",false);
                editor.apply();
                realm.beginTransaction();
                cartSIZE.deleteAllFromRealm();
                realm.commitTransaction();
                realm.close();
                Intent intent = new Intent(AlertActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        moveTaskToBack(true);
    }
}
