package com.example.publicmart;

import android.content.Intent;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.baoyachi.stepview.HorizontalStepView;
import com.baoyachi.stepview.bean.StepBean;

import java.util.ArrayList;
import java.util.List;

public class OrderStatus extends BaseActivity {
    HorizontalStepView horizontalStepView;
    LinearLayout ll;
    TextView status,status2, status1;
    StepBean stepBean0,stepBean1,stepBean2,stepBean3;
    Button pay;
    TextView txtxmpny;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FrameLayout contentFrameLayout = (FrameLayout) findViewById(R.id.content_frame);
        getLayoutInflater().inflate(R.layout.activity_order_status, contentFrameLayout);



        status = findViewById(R.id.state1);
        status2 = findViewById(R.id.state2);
        status1  = findViewById(R.id.state0);



        pay = findViewById(R.id.payment);

        pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(OrderStatus.this,Payment.class);
                startActivity(intent);
            }
        });

        horizontalStepView = (HorizontalStepView)findViewById(R.id.step_view) ;
        List<StepBean> stepsBeanList = new ArrayList<>();
        String state = "payment";


       stepBean0 = new StepBean() ;
//        Log.e("step",""+stepBean0);

        stepBean1 = new StepBean() ;


//

       stepBean2 = new StepBean();





     stepBean3 = new StepBean();



        stepsBeanList.add(stepBean0);
        stepsBeanList.add(stepBean1);
        stepsBeanList.add(stepBean2);
        stepsBeanList.add(stepBean3);

        Log.e("step",""+stepBean2.getState());
        if(state=="approved"){
            status.setVisibility(View.GONE);
            status2.setVisibility(View.GONE);
            status1.setVisibility(View.VISIBLE);
            pay.setVisibility(View.GONE);
            stepBean0.setState(0);
            stepBean0.setName("Approved");
            stepBean1.setName("Payment");
             stepBean1.setState(-1);

            stepBean2.setName("Shipping");
            stepBean2.setState(-1);
            stepBean3.setName("Delivered");
           stepBean3.setState(-1);


        }
        else  if(state=="payment"){
            status.setVisibility(View.VISIBLE);
            status2.setVisibility(View.GONE);
            status1.setVisibility(View.GONE);
            pay.setVisibility(View.VISIBLE);
            stepBean0.setState(1);
            stepBean0.setName("Approved");
            stepBean1.setName("Payment");
            stepBean1.setState(0);

            stepBean2.setName("Shipping");
            stepBean2.setState(-1);
            stepBean3.setName("Delivered");
            stepBean3.setState(-1);


        }

        horizontalStepView

                .setStepViewTexts(stepsBeanList)
                .setTextSize(12)
                .setStepsViewIndicatorCompletedLineColor(Color.parseColor("#1b2f42"))
                .setStepsViewIndicatorUnCompletedLineColor(Color.parseColor("#1b2f42"))
                .setStepViewComplectedTextColor(Color.parseColor("#1b2f42"))
                .setStepViewUnComplectedTextColor(Color.parseColor("#1b2f42"))

                .setStepsViewIndicatorCompleteIcon(ContextCompat.getDrawable(this,R.drawable.checkmark))
                .setStepsViewIndicatorDefaultIcon(ContextCompat.getDrawable(this,R.drawable.radio_normal))
                .setStepsViewIndicatorAttentionIcon(ContextCompat.getDrawable(this,R.drawable.radio_pressed))
        ;


        horizontalStepView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(OrderStatus.this, orderstatusdet.class);
                startActivity(intent);

            }
        });
    }
}
