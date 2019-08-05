package com.publicmart.android;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.facebook.drawee.view.SimpleDraweeView;

public class MenuDirect extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_direct);

        SimpleDraweeView logo1 = findViewById(R.id.dirLogo1);
        SimpleDraweeView logo2 = findViewById(R.id.dirLogo2);

        logo1.setImageResource(R.drawable.cartlogo);
        logo2.setImageResource(R.drawable.birthdaycake);

        LinearLayout ll =findViewById(R.id.publicmart);
        ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent intent = new Intent(MenuDirect.this, Payment.class);
               startActivity(intent);
            }
        });

    }
}
