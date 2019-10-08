package com.publicmart.essensol.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.publicmart.essensol.Adapters_.Tourpackage_Adapter;
import com.publicmart.essensol.Adapters_.TravelGuide_Adapter;
import com.publicmart.essensol.R;

import java.util.ArrayList;

public class Travel_guide extends BaseActivity {

    RecyclerView travel_recycle;
    TravelGuide_Adapter travelGuide_adapter;
    ArrayList<Integer> Items=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FrameLayout contentFrameLayout = (FrameLayout) findViewById(R.id.content_frame);
        getLayoutInflater().inflate(R.layout.activity_travel_guide, contentFrameLayout);

        travel_recycle=(RecyclerView)findViewById(R.id.travel_recycle);

        android.support.v7.widget.Toolbar tb=getToolBar();
        TextView txtxmpny=(TextView)tb.findViewById(R.id.appname);
        txtxmpny.setText("Travel Guide");

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        travel_recycle.setLayoutManager(linearLayoutManager);


        Items.add(R.drawable.goatour);
        Items.add(R.drawable.tour);


        travelGuide_adapter = new TravelGuide_Adapter(getApplicationContext(), Items);
        travel_recycle.setAdapter(travelGuide_adapter);
    }
}
