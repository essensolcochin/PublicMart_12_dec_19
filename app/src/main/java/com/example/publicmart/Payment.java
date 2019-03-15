package com.example.publicmart;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class Payment extends AppCompatActivity {


    String array_Mnth[] = {"MM", "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
    String array_Year[] = {"YY", "2019", "2020", "2021", "2022", "2023", "2024", "2025", "2026", "2027", "2028", "2029"};

    Spinner months,years;
    Button confirmPay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        months = findViewById(R.id.month);
        years = findViewById(R.id.year);
        confirmPay = findViewById(R.id.submit_pay);
        final ArrayAdapter<String> spinner_adapter_month = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, array_Mnth);
        spinner_adapter_month.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        months.setAdapter(spinner_adapter_month);

        final ArrayAdapter<String> spinner_adapter_year = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, array_Year);
        spinner_adapter_year.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        years.setAdapter(spinner_adapter_year);

    confirmPay.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {

        SharedPreferences sp = getSharedPreferences("UserLog",MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean("LoggedUser",true);
        editor.apply();
        Intent intent =new Intent(Payment.this,Home.class);
        startActivity(intent);
        finish();

         }
    });


    }
}
