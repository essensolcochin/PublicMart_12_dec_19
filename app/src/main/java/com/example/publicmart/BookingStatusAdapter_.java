package com.example.publicmart;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.baoyachi.stepview.HorizontalStepView;
import com.baoyachi.stepview.bean.StepBean;

import java.util.ArrayList;
import java.util.List;

public class BookingStatusAdapter_ extends RecyclerView.Adapter<BookingStatusAdapter_.View_holder> {

    List<BookingstatusModel> items;
    private Context context;

    public BookingStatusAdapter_(Context context,List<BookingstatusModel>items) {

        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public View_holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_bookingstatus,viewGroup,false);
        return new BookingStatusAdapter_.View_holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull View_holder view_holder, int i) {
        final BookingstatusModel List = items.get(i);


        view_holder.passenger_name.setText(List.getPassengerName());
        view_holder.from_airport.setText(List.getFromAirportCode());
        view_holder.to_airport.setText(List.getToAirportCode());
        view_holder.date.setText(List.getTravelDate());
        view_holder.amount.setText(List.getAmount());
        view_holder.status.setText(List.getBookingStatusName());

        Log.e("checkinggg",""+List.getPassengerName());


        List<StepBean> stepsBeanList = new ArrayList<>();
        String state = List.getBookingStatusKey();


        view_holder.stepBean0 = new StepBean() ;
//        Log.e("step",""+stepBean0);

        view_holder. stepBean1 = new StepBean() ;

        view_holder.stepBean2 = new StepBean();

        view_holder.stepBean3 = new StepBean();

        view_holder.stepBean4 = new StepBean();

        view_holder.stepBean5 = new StepBean();

        stepsBeanList.add( view_holder.stepBean0);
        stepsBeanList.add( view_holder.stepBean1);
        stepsBeanList.add( view_holder.stepBean2);
        stepsBeanList.add( view_holder.stepBean3);
        stepsBeanList.add( view_holder.stepBean4);

        Log.e("step",""+ view_holder.stepBean2.getState());



        if(state =="Pending"){
            view_holder.status.setText("Pending");
            view_holder.payment.setVisibility(View.GONE);
            view_holder.stepBean0.setState(0);
            view_holder.stepBean0.setName("Pending");
            view_holder.stepBean1.setName("OrderConfirmed");
            view_holder. stepBean1.setState(-1);
            view_holder.stepBean2.setName("Payment");
            view_holder.stepBean2.setState(-1);
            view_holder.stepBean3.setName("ProductShipping");
            view_holder. stepBean3.setState(-1);
            view_holder.stepBean4.setName("Delivered");
            view_holder. stepBean4.setState(-1);



        }
        else  if(state=="Order Confirmed"){
            view_holder.status.setText("Order Confirmed");
            view_holder. payment.setVisibility(View.VISIBLE);
            view_holder.stepBean0.setState(1);
            view_holder.stepBean0.setName("Pending");
            view_holder. stepBean1.setName("OrderConfirmed");
            view_holder.stepBean1.setState(0);
            view_holder. stepBean2.setName("Payment");
            view_holder.stepBean2.setState(-1);
            view_holder.stepBean3.setName("ProductShipping");
            view_holder.stepBean3.setState(-1);
            view_holder.stepBean4.setName("Delivered");
            view_holder.stepBean4.setState(-1);
        }
        else  if(state=="Amount Paid"){
            view_holder.status.setText("Amount Paid");
            view_holder. payment.setVisibility(View.GONE);
            view_holder.stepBean0.setState(1);
            view_holder.stepBean0.setName("Pending");
            view_holder.stepBean1.setName("OrderConfirmed");
            view_holder.stepBean1.setState(1);
            view_holder.stepBean2.setName("Amount Paid");
            view_holder.stepBean2.setState(0);
            view_holder.stepBean3.setName("ProductShipping");
            view_holder.stepBean3.setState(-1);
            view_holder.stepBean4.setName("Delivered");
            view_holder.stepBean4.setState(-1);
        }
        else  if(state=="Product Shipping"){
            view_holder.status.setText("Shipping");
            view_holder. payment.setVisibility(View.GONE);
            view_holder.stepBean0.setState(1);
            view_holder.stepBean0.setName("Pending");
            view_holder. stepBean1.setName("OrderConfirmed");
            view_holder.stepBean1.setState(1);
            view_holder. stepBean2.setName("Payment");
            view_holder.stepBean2.setState(1);
            view_holder.stepBean3.setName("ProductShipping");
            view_holder.stepBean3.setState(0);
            view_holder.stepBean4.setName("Delivered");
            view_holder. stepBean4.setState(-1);
        }
        else  if(state=="Product Delivered"){
            view_holder.status.setText("Delivered");
            view_holder. payment.setVisibility(View.GONE);
            view_holder.stepBean0.setState(1);
            view_holder.stepBean0.setName("Pending");
            view_holder. stepBean1.setName("OrderConfirmed");
            view_holder.stepBean1.setState(1);
            view_holder. stepBean2.setName("Payment");
            view_holder.stepBean2.setState(1);
            view_holder.stepBean3.setName("ProductShipping");
            view_holder.stepBean3.setState(1);
            view_holder.stepBean4.setName("Delivered");
            view_holder. stepBean4.setState(0);
        }


        view_holder.horizontalStepView.setStepViewTexts(stepsBeanList)
                .setTextSize(16)//set textSize
                .setStepsViewIndicatorCompletedLineColor(ContextCompat.getColor(context, android.R.color.black))
                .setStepsViewIndicatorUnCompletedLineColor(ContextCompat.getColor(context, R.color.col5))
                .setStepViewComplectedTextColor(ContextCompat.getColor(context, R.color.col5))
                .setStepViewUnComplectedTextColor(ContextCompat.getColor(context, R.color.uncompleted_text_color))
                .setStepsViewIndicatorCompleteIcon(ContextCompat.getDrawable(context, R.drawable.complted))
                .setStepsViewIndicatorDefaultIcon(ContextCompat.getDrawable(context, R.drawable.default_icon))
                .setStepsViewIndicatorAttentionIcon(ContextCompat.getDrawable(context, R.drawable.attention));




    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class View_holder extends RecyclerView.ViewHolder

    {
        TextView passenger_name,from_airport,to_airport,amount,status,date;
        HorizontalStepView horizontalStepView;
        Button payment;
        StepBean stepBean0,stepBean1,stepBean2,stepBean3,stepBean4,stepBean5;

           View_holder(@NonNull View itemView) {
            super(itemView);
            horizontalStepView = (HorizontalStepView)itemView.findViewById(R.id.bstep_view) ;
            passenger_name = itemView.findViewById(R.id.pass);
            from_airport = itemView.findViewById(R.id.from_air);
            to_airport=itemView.findViewById(R.id.to_air);
            amount=itemView.findViewById(R.id.price);
            status=itemView.findViewById(R.id.status);
            date=itemView.findViewById(R.id.date);
            payment=itemView.findViewById(R.id.paymentbttn);


        }
    }


}
