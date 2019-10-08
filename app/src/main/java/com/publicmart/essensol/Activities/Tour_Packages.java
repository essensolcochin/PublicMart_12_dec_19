package com.publicmart.essensol.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.publicmart.essensol.Adapters_.MenuItemAdapter;
import com.publicmart.essensol.Adapters_.Tourpackage_Adapter;
import com.publicmart.essensol.R;

import java.util.ArrayList;

public class Tour_Packages extends BaseActivity {

    RecyclerView tour_package_recycle;
    Tourpackage_Adapter tourpackage_adapter;
    ArrayList<Integer> tourItems=new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FrameLayout contentFrameLayout = (FrameLayout) findViewById(R.id.content_frame);
        getLayoutInflater().inflate(R.layout.activity_tour__packages, contentFrameLayout);

        tour_package_recycle=(RecyclerView)findViewById(R.id.tour_package_recycle);

        android.support.v7.widget.Toolbar tb=getToolBar();
        TextView txtxmpny=(TextView)tb.findViewById(R.id.appname);
        txtxmpny.setText("Tour Packages");


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        tour_package_recycle.setLayoutManager(linearLayoutManager);


        tourItems.add(R.drawable.lehladak);
        tourItems.add(R.drawable.munnar);
        tourItems.add(R.drawable.kerala);
        tourItems.add(R.drawable.goa);

        tourpackage_adapter = new Tourpackage_Adapter(getApplicationContext(), tourItems);
        tour_package_recycle.setAdapter(tourpackage_adapter);
    }
}
