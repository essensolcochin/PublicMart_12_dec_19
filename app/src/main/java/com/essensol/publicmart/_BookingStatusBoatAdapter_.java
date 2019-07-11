package com.essensol.publicmart;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
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

public class _BookingStatusBoatAdapter_ extends RecyclerView.Adapter<_BookingStatusBoatAdapter_.View_holder> {


    List<BoatStausModel> boatItems;
    private Context context;

    public _BookingStatusBoatAdapter_(Context context, List<BoatStausModel> boatItems) {

        this.context = context;
        this.boatItems = boatItems;
    }

    @NonNull
    @Override
    public _BookingStatusBoatAdapter_.View_holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_bookingstatus_boat,viewGroup,false);
        return new _BookingStatusBoatAdapter_.View_holder(view);
    }



    @Override
    public void onBindViewHolder(@NonNull _BookingStatusBoatAdapter_.View_holder view_holder, int i) {
        final BoatStausModel List = boatItems.get(i);
        Typeface custom_font = Typeface.createFromAsset(context.getAssets(),  "fonts/ralewayM.ttf");

        Typeface custom_font2 = Typeface.createFromAsset(context.getAssets(),  "fonts/RalewayBold.ttf");

        view_holder.passenger_name.setText(List.getPassengerName());
        view_holder.passenger_name.setTypeface(custom_font2);
        view_holder.cruiseType.setTypeface(custom_font);
        view_holder.toc.setTypeface(custom_font);

        if(List.getCruiseType().equalsIgnoreCase("D"))
        {
            view_holder.cruiseType.setText("Day");

        }
       else if(List.getCruiseType().equalsIgnoreCase("N"))
        {
            view_holder.cruiseType.setText("Night");

        }
        else if(List.getCruiseType().equalsIgnoreCase("F"))
        {
            view_holder.cruiseType.setText("Full Day");

        }

        view_holder.guestCount.setText(List.getGuestNos());

        view_holder.date.setText(List.getTravelDate());

        if(List.getAmount().trim().equalsIgnoreCase("null"))
        {
            view_holder.amount.setText("N/A");
        }
        else{
            view_holder.amount.setText(List.getAmount());
        }

        view_holder.status.setText(List.getBookingStatusName());

        Log.e("checkinggg",""+List.getPassengerName());


        List<StepBean> stepsBeanList = new ArrayList<>();
        String state = List.getBookingStatusName();


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




        if(state.trim().equalsIgnoreCase("Pending")){
            view_holder.payment.setVisibility(View.GONE);
            view_holder.stepBean0.setState(0);
            view_holder.stepBean0.setName("Pending");
            view_holder.stepBean1.setName("Approved");
            view_holder. stepBean1.setState(-1);
            view_holder.stepBean2.setName("Payment");
            view_holder.stepBean2.setState(-1);
            view_holder.stepBean3.setName("Booked");
            view_holder. stepBean3.setState(-1);
            view_holder.stepBean4.setName("Delivered");
            view_holder. stepBean4.setState(-1);



        }
        else  if(state.trim().equalsIgnoreCase("Booking Confirmed")){
            view_holder. payment.setVisibility(View.VISIBLE);
            view_holder.stepBean0.setState(1);
            view_holder.stepBean0.setName("Pending");
            view_holder. stepBean1.setName("Approved");
            view_holder.stepBean1.setState(0);
            view_holder. stepBean2.setName("Payment");
            view_holder.stepBean2.setState(-1);
            view_holder.stepBean3.setName("Booked");
            view_holder.stepBean3.setState(-1);
            view_holder.stepBean4.setName("Delivered");
            view_holder.stepBean4.setState(-1);
        }
        else  if(state.trim().equalsIgnoreCase("Ticket Booked")){

            view_holder. payment.setVisibility(View.GONE);
            view_holder.stepBean0.setState(1);
            view_holder.stepBean0.setName("Pending");
            view_holder.stepBean1.setName("Approved");
            view_holder.stepBean1.setState(1);
            view_holder.stepBean2.setName("Payment");
            view_holder.stepBean2.setState(0);
            view_holder.stepBean3.setName("Booked");
            view_holder.stepBean3.setState(-1);
            view_holder.stepBean4.setName("Delivered");
            view_holder.stepBean4.setState(-1);
        }
        else  if(state.trim().equalsIgnoreCase("Ticket Delivered")){

            view_holder. payment.setVisibility(View.GONE);
            view_holder.stepBean0.setState(1);
            view_holder.stepBean0.setName("Pending");
            view_holder. stepBean1.setName("Approved");
            view_holder.stepBean1.setState(1);
            view_holder. stepBean2.setName("Payment");
            view_holder.stepBean2.setState(1);
            view_holder.stepBean3.setName("Booked");
            view_holder.stepBean3.setState(0);
            view_holder.stepBean4.setName("Delivered");
            view_holder. stepBean4.setState(-1);
        }
        else  if(state.trim().equalsIgnoreCase("Feedback")){

            view_holder. payment.setVisibility(View.GONE);
            view_holder.stepBean0.setState(1);
            view_holder.stepBean0.setName("Pending");
            view_holder. stepBean1.setName("Approved");
            view_holder.stepBean1.setState(1);
            view_holder. stepBean2.setName("Payment");
            view_holder.stepBean2.setState(1);
            view_holder.stepBean3.setName("Booked");
            view_holder.stepBean3.setState(1);
            view_holder.stepBean4.setName("Delivered");
            view_holder. stepBean4.setState(0);
        }


        view_holder.horizontalStepView.setStepViewTexts(stepsBeanList)
                .setTextSize(10)//set textSize

                .setStepsViewIndicatorCompletedLineColor(ContextCompat.getColor(context, android.R.color.black))
                .setStepsViewIndicatorUnCompletedLineColor(ContextCompat.getColor(context, R.color.col5))
                .setStepViewComplectedTextColor(ContextCompat.getColor(context, R.color.col5))
                .setStepViewUnComplectedTextColor(ContextCompat.getColor(context, R.color.col5))
                .setStepsViewIndicatorCompleteIcon(ContextCompat.getDrawable(context, R.drawable.radio_pressed))
                .setStepsViewIndicatorDefaultIcon(ContextCompat.getDrawable(context, R.drawable.radio_bg))
                .setStepsViewIndicatorAttentionIcon(ContextCompat.getDrawable(context, R.drawable.  checkmark));




        view_holder.payment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, Payment.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.getApplicationContext().startActivity(intent);
            }
        });




    }

    @Override
    public int getItemCount() {
        return boatItems.size();
    }

    public class View_holder extends RecyclerView.ViewHolder

    {
        TextView passenger_name,cruiseType,guestCount,amount,status,date,toc;
        HorizontalStepView horizontalStepView;
        Button payment;
        StepBean stepBean0,stepBean1,stepBean2,stepBean3,stepBean4,stepBean5;

        View_holder(@NonNull View itemView) {
            super(itemView);
            horizontalStepView = (HorizontalStepView)itemView.findViewById(R.id.bstep_view) ;
            passenger_name = itemView.findViewById(R.id.pass);
            cruiseType = itemView.findViewById(R.id.curiseType);
            guestCount=itemView.findViewById(R.id.count);
            amount=itemView.findViewById(R.id.price);
            status=itemView.findViewById(R.id.status);
            date=itemView.findViewById(R.id.date);
            payment=itemView.findViewById(R.id.paymentbttn);
            toc=itemView.findViewById(R.id.from_air);


        }
    }

}
