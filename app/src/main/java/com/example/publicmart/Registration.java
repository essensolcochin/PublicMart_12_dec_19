package com.example.publicmart;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class Registration extends AppCompatActivity {
    LinearLayout regist;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        regist = (LinearLayout)findViewById(R.id.submit);
        regist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(Registration.this,MainActivity.class);
                startActivity(intent);

            }
        });
    }
}
