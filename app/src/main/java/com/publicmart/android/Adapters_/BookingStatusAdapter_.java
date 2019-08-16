package com.publicmart.android.Adapters_;

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
import com.publicmart.android.Activities.Payment;
import com.publicmart.android.ModelClasses.BookingstatusModel;
import com.publicmart.android.R;


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
        return new View_holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull View_holder view_holder, int i) {
        final BookingstatusModel List = items.get(i);

        Typeface custom_font = Typeface.createFromAsset(context.getAssets(),  "fonts/ralewayM.ttf");

        Typeface custom_font2 = Typeface.createFromAsset(context.getAssets(),  "fonts/RalewayBold.ttf");

        view_holder.passenger_name.setText(List.getPassengerName());
        view_holder.passenger_name.setTypeface(custom_font2);


        view_holder.from_airport.setText(List.getFromAirportCode());
        view_holder.from_airport.setTypeface(custom_font2);
        view_holder.to_airport.setText(List.getToAirportCode());
        view_holder.to_airport.setTypeface(custom_font2);


        view_holder.date.setText(List.getTravelDate().substring(0, 10));
        if(List.getAmount().equalsIgnoreCase("null"))
        {
            view_holder.amount.setText("N/A");
        }
        else{
            view_holder.amount.setText(List.getAmount());
        }


        view_holder.status.setText(List.getBookingStatusName());
        view_holder.status.setTypeface(custom_font);


        Log.e("checkinggg",""+List.getBookingStatusName());


        List<StepBean> stepsBeanList = new ArrayList<>();
        String state = List.getBookingStatusName();


        view_holder.stepBean0 = new StepBean() ;
//        Log.e("step",""+stepBean0);

        view_holder. stepBean1 = new StepBean() ;

        view_holder.stepBean2 = new StepBean();

        view_holder.stepBean3 = new StepBean();

        view_holder.stepBean4 = new StepBean();





        stepsBeanList.add( view_holder.stepBean0);
        stepsBeanList.add( view_holder.stepBean1);
        stepsBeanList.add( view_holder.stepBean2);
        stepsBeanList.add( view_holder.stepBean3);
        stepsBeanList.add( view_holder.stepBean4);

        Log.e("steppppppppp"," "+state);

        switch (state.trim()){
            case "Pending":

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
                break;
            case "Booking Confirmed":

                view_holder.payment.setVisibility(View.VISIBLE);
                view_holder.stepBean0.setState(1);
                view_holder.stepBean0.setName("Pending");
                view_holder.stepBean1.setName("Approved");
                view_holder.stepBean1.setState(0);
                view_holder.stepBean2.setName("Payment");
                view_holder.stepBean2.setState(-1);
                view_holder.stepBean3.setName("Booked");
                view_holder.stepBean3.setState(-1);
                view_holder.stepBean4.setName("Delivered");
                view_holder.stepBean4.setState(-1);
                break;
            case "Amount Paid":
                Log.e("steppppppppp","Inside Case Amount "+state);
                view_holder.payment.setVisibility(View.GONE);
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
                break;
            case "Ticket Booked":

                view_holder. payment.setVisibility(View.GONE);
                view_holder.stepBean0.setState(1);
                view_holder.stepBean0.setName("Pending");
                view_holder.stepBean1.setName("Approved");
                view_holder.stepBean1.setState(1);
                view_holder.stepBean2.setName("Payment");
                view_holder.stepBean2.setState(1);
                view_holder.stepBean3.setName("Booked");
                view_holder.stepBean3.setState(0);
                view_holder.stepBean4.setName("Delivered");
                view_holder.stepBean4.setState(-1);
                break;
            case "Booking Cancelled":

                view_holder.horizontalStepView.setVisibility(View.GONE);
                break;

            case "Ticket Delivered":

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
                break;
            case "Feedback":

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
                break;
//                default:
//                    view_holder.horizontalStepView.setVisibility(View.GONE);
//                    break;

        }



//        if(state.equalsIgnoreCase("Pending")){
//            view_holder.payment.setVisibility(View.GONE);
//            view_holder.stepBean0.setState(0);
//            view_holder.stepBean0.setName("Pending");
//            view_holder.stepBean1.setName("Approved");
//            view_holder. stepBean1.setState(-1);
//            view_holder.stepBean2.setName("Payment");
//            view_holder.stepBean2.setState(-1);
//            view_holder.stepBean3.setName("Booked");
//            view_holder. stepBean3.setState(-1);
//            view_holder.stepBean4.setName("Delivered");
//            view_holder. stepBean4.setState(-1);
//
//            //0 completed
//            //1 ongoing
//            //-1 pending
//
//
//        }
//        else  if(state.equalsIgnoreCase("Booking Confirmed")){
//            view_holder. payment.setVisibility(View.VISIBLE);
//            view_holder.stepBean0.setState(1);
//            view_holder.stepBean0.setName("Pending");
//            view_holder. stepBean1.setName("Approved");
//            view_holder.stepBean1.setState(0);
//            view_holder. stepBean2.setName("Payment");
//            view_holder.stepBean2.setState(-1);
//            view_holder.stepBean3.setName("Booked");
//            view_holder.stepBean3.setState(-1);
//            view_holder.stepBean4.setName("Delivered");
//            view_holder.stepBean4.setState(-1);
//        }

//        else  if(state.equalsIgnoreCase("Amount Paid")){
//            view_holder. payment.setVisibility(View.VISIBLE);
//            view_holder.stepBean0.setState(1);
//            view_holder.stepBean0.setName("Pending");
//            view_holder. stepBean1.setName("Approved");
//            view_holder.stepBean1.setState(1);
//            view_holder. stepBean2.setName("Payment");
//            view_holder.stepBean2.setState(0);
//            view_holder.stepBean3.setName("Booked");
//            view_holder.stepBean3.setState(-1);
//            view_holder.stepBean4.setName("Delivered");
//            view_holder.stepBean4.setState(-1);
//        }


//        else  if(state.equalsIgnoreCase("Ticket Booked")){
//
//            view_holder. payment.setVisibility(View.GONE);
//            view_holder.stepBean0.setState(1);
//            view_holder.stepBean0.setName("Pending");
//            view_holder.stepBean1.setName("Approved");
//            view_holder.stepBean1.setState(1);
//            view_holder.stepBean2.setName("Payment");
//            view_holder.stepBean2.setState(1);
//            view_holder.stepBean3.setName("Booked");
//            view_holder.stepBean3.setState(0);
//            view_holder.stepBean4.setName("Delivered");
//            view_holder.stepBean4.setState(-1);
//        }

//        else  if(state.equalsIgnoreCase("Booking Cancelled")){
//
//          view_holder.horizontalStepView.setVisibility(View.GONE);
//        }
//
//
//        else  if(state.equalsIgnoreCase("Ticket Delivered")){
//
//            view_holder. payment.setVisibility(View.GONE);
//            view_holder.stepBean0.setState(1);
//            view_holder.stepBean0.setName("Pending");
//            view_holder. stepBean1.setName("Approved");
//            view_holder.stepBean1.setState(1);
//            view_holder. stepBean2.setName("Payment");
//            view_holder.stepBean2.setState(1);
//            view_holder.stepBean3.setName("Booked");
//            view_holder.stepBean3.setState(1);
//            view_holder.stepBean4.setName("Delivered");
//            view_holder. stepBean4.setState(0);
//        }
//        else  if(state.equalsIgnoreCase("Feedback")){
//
//            view_holder. payment.setVisibility(View.GONE);
//            view_holder.stepBean0.setState(1);
//            view_holder.stepBean0.setName("Pending");
//            view_holder. stepBean1.setName("Approved");
//            view_holder.stepBean1.setState(1);
//            view_holder. stepBean2.setName("Payment");
//            view_holder.stepBean2.setState(1);
//            view_holder.stepBean3.setName("Booked");
//            view_holder.stepBean3.setState(1);
//            view_holder.stepBean4.setName("Delivered");
//            view_holder. stepBean4.setState(0);
//        }


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
