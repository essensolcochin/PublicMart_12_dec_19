package com.example.publicmart;

import android.content.Context;

import android.graphics.Color;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.baoyachi.stepview.HorizontalStepView;
import com.baoyachi.stepview.bean.StepBean;

import java.util.ArrayList;
import java.util.List;

public class OrderStatusAdapter_ extends RecyclerView.Adapter<OrderStatusAdapter_.ViewHolder> {

    private String[] mData;
    private LayoutInflater mInflater;

    private Context context;
    // data is passed into the constructor
    OrderStatusAdapter_(Context context, String[] data) {
        this.context = context;
        this.mData = data;
    }

    @Override
    @NonNull
    public OrderStatusAdapter_.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_status_item,parent,false);
        return new OrderStatusAdapter_.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderStatusAdapter_.ViewHolder holder, int position) {

        Typeface custom_font2 = Typeface.createFromAsset(context.getAssets(),  "fonts/GravityBold.otf");

        holder.ItemName.setTypeface(custom_font2);
        holder.ItemDesc.setTypeface(custom_font2);


        List<StepBean> stepsBeanList = new ArrayList<>();
        String state = "payment";


        holder.stepBean0 = new StepBean() ;
//        Log.e("step",""+stepBean0);

        holder. stepBean1 = new StepBean() ;


//

        holder.stepBean2 = new StepBean();





        holder.stepBean3 = new StepBean();



        stepsBeanList.add( holder.stepBean0);
        stepsBeanList.add( holder.stepBean1);
        stepsBeanList.add( holder.stepBean2);
        stepsBeanList.add( holder.stepBean3);

        Log.e("step",""+ holder.stepBean2.getState());
        if(state=="approved"){
            holder. status.setVisibility(View.GONE);
            holder.status2.setVisibility(View.GONE);
            holder.status1.setVisibility(View.VISIBLE);
            holder.pay.setVisibility(View.GONE);
            holder.stepBean0.setState(0);
            holder.stepBean0.setName("Approved");
            holder.stepBean1.setName("Payment");
            holder. stepBean1.setState(-1);

            holder.stepBean2.setName("Shipping");
            holder.stepBean2.setState(-1);
            holder.stepBean3.setName("Delivered");
            holder. stepBean3.setState(-1);


        }
        else  if(state=="payment"){
            holder.status.setVisibility(View.VISIBLE);
            holder. status2.setVisibility(View.GONE);
            holder. status1.setVisibility(View.GONE);
            holder. pay.setVisibility(View.VISIBLE);
            holder.stepBean0.setState(1);
            holder.stepBean0.setName("Approved");
            holder. stepBean1.setName("Payment");
            holder.stepBean1.setState(0);

            holder. stepBean2.setName("Shipping");
            holder.stepBean2.setState(-1);
            holder.stepBean3.setName("Delivered");
            holder.stepBean3.setState(-1);


        }

        holder. horizontalStepView.setStepViewTexts(stepsBeanList)
                .setTextSize(12)
                .setStepsViewIndicatorCompletedLineColor(Color.parseColor("#1b2f42"))
                .setStepsViewIndicatorUnCompletedLineColor(Color.parseColor("#1b2f42"))
                .setStepViewComplectedTextColor(Color.parseColor("#1b2f42"))
                .setStepViewUnComplectedTextColor(Color.parseColor("#1b2f42"))

                .setStepsViewIndicatorCompleteIcon(ContextCompat.getDrawable(context,R.drawable.checkmark))
                .setStepsViewIndicatorDefaultIcon(ContextCompat.getDrawable(context,R.drawable.radio_normal))
                .setStepsViewIndicatorAttentionIcon(ContextCompat.getDrawable(context,R.drawable.radio_pressed))
        ;







    }

    @Override
    public int getItemCount() {
        return mData.length;
    }


    public class ViewHolder extends RecyclerView.ViewHolder  {
        TextView ItemName,ItemDesc;
        LinearLayout itemLayout;
        HorizontalStepView horizontalStepView;

        Button pay;
        TextView status,status2, status1;
        StepBean stepBean0,stepBean1,stepBean2,stepBean3;

        ViewHolder(View itemView) {
            super(itemView);
            horizontalStepView = (HorizontalStepView)itemView.findViewById(R.id.step_view) ;
            status = itemView.findViewById(R.id.state1);
            status2 = itemView.findViewById(R.id.state2);
            status1  = itemView.findViewById(R.id.state0);
            pay = itemView.findViewById(R.id.payment);
            ItemName = itemView.findViewById(R.id.itemName);
            ItemDesc = itemView.findViewById(R.id.itemDesc);

        }







    }


}
