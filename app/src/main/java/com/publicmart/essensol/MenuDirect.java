package com.publicmart.essensol;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.publicmart.essensol.Activities.Payment;

public class MenuDirect extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_direct);





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
