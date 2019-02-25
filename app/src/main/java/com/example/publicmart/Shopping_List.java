package com.example.publicmart;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.FrameLayout;

public class Shopping_List extends BaseActivity {

    Shopping_List_Adapter shopping_list_adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FrameLayout contentFrameLayout = (FrameLayout) findViewById(R.id.content_frame);
        getLayoutInflater().inflate(R.layout.activity_shopping__list, contentFrameLayout);
        RecyclerView shoopingrecycler = findViewById(R.id.shoppingRecycler);

        String[] data = {"1","2","3"};

        shoopingrecycler.setLayoutManager(new LinearLayoutManager(this));
        shopping_list_adapter = new Shopping_List_Adapter(this, data);

        shoopingrecycler.setAdapter(shopping_list_adapter);



    }
}