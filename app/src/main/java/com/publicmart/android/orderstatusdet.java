package com.publicmart.android;

import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.baoyachi.stepview.VerticalStepView;
import com.publicmart.android.Activities.BaseActivity;

import java.util.ArrayList;
import java.util.List;

public class orderstatusdet extends BaseActivity {

    VerticalStepView verticalStepView;

    TextView txtxmpny;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FrameLayout contentFrameLayout = (FrameLayout) findViewById(R.id.content_frame);
        getLayoutInflater().inflate(R.layout.activity_orderstatusdet, contentFrameLayout);


//        android.support.v7.widget.Toolbar tb=getToolBar();
//        txtxmpny=(TextView)tb.findViewById(R.id.appname);
//        txtxmpny.setText("Order Status");

        verticalStepView = (VerticalStepView)findViewById(R.id.step_view) ;
        List<String> sources = new ArrayList<>();


        sources.add("Approved " +" 18-2-2018"+
                        " 10.00 AM"+"   "+"Your order has been placed by the dealer." );
        sources.add("Packed "+" Expected by, Tue 19-2-2018"
                +  " 10.00 AM"  + "Item waiting to be picked up by the courier partner."+" ");
        sources.add("Shipping"+ "Expected By Wed,"+
                " 19-2-2018" +  " 10.00 AM");
        sources.add("Delivery Expected By Sat," +
                " 25-2-2018" + " 10.00 AM" );



        verticalStepView.setStepsViewIndicatorComplectingPosition(sources.size()-3)
                .reverseDraw(false)
                .setStepViewTexts(sources)
                .setTextSize(14)
                .setLinePaddingProportion(2)
                .setStepsViewIndicatorCompletedLineColor(Color.parseColor("#FF504F4F"))
                .setStepsViewIndicatorUnCompletedLineColor(Color.parseColor("#1b2f42"))
                .setStepViewComplectedTextColor(Color.parseColor("#1b2f42"))
                .setStepViewUnComplectedTextColor(Color.parseColor("#1b2f42"))
                .setStepsViewIndicatorCompleteIcon(ContextCompat.getDrawable(this,R.drawable.radio_bg))
                .setStepsViewIndicatorDefaultIcon(ContextCompat.getDrawable(this,R.drawable.radio_normal))
                .setStepsViewIndicatorAttentionIcon(ContextCompat.getDrawable(this,R.drawable.radio_pressed))

        ;







    }
    }

