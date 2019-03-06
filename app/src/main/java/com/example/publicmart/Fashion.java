package com.example.publicmart;


import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import dmax.dialog.SpotsDialog;

public class Fashion extends BaseActivity {

    LinearLayout ll;

    MyRecyclerViewAdapter adapter;
    GridLayoutManager layoutManager;
    public int visibleItemCount,pastVisibleItemCount,totalItemCount,previousCount= 0;
    public boolean isloading =true;
    public  int view_threshold =10;
    TextView txtxmpny;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FrameLayout contentFrameLayout = (FrameLayout) findViewById(R.id.content_frame);
        getLayoutInflater().inflate(R.layout.activity_fashion, contentFrameLayout);


        final SpotsDialog progress = new SpotsDialog(Fashion.this,R.style.Custom);


        progress.show();

        Runnable progressRunnable = new Runnable() {

            @Override
            public void run() {


                progress.cancel();
            }
        };

        Handler pdCanceller = new Handler();
        pdCanceller.postDelayed(progressRunnable, 2000);



        String[] data = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48"};




        // set up the RecyclerView
        RecyclerView recyclerView = findViewById(R.id.rvNumbers);
        int numberOfColumns = 2;
        layoutManager =new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(layoutManager);


        adapter = new MyRecyclerViewAdapter(this, data);

        recyclerView.setAdapter(adapter);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                visibleItemCount = layoutManager.getChildCount();
                totalItemCount = layoutManager.getItemCount();
                pastVisibleItemCount =layoutManager.findFirstVisibleItemPosition();

                if(dy>0)
                {
                    if(isloading)
                    {
                        if(totalItemCount>previousCount)
                        {
                            isloading =false;
                            previousCount =totalItemCount;
                        }

                    }

                    if(isloading&&(totalItemCount-visibleItemCount)<=(pastVisibleItemCount+view_threshold))
                    {
                       // LoadItems();
                        Toast.makeText(Fashion.this,"Pagination",Toast.LENGTH_LONG).show();
                        isloading = true;

                    }


                }


            }
        });




        android.support.v7.widget.Toolbar tb=getToolBar();
        txtxmpny=(TextView)tb.findViewById(R.id.appname);
        txtxmpny.setText("Fashion");



    }
}
