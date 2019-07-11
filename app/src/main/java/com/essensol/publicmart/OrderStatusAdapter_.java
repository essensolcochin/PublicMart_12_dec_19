package com.essensol.publicmart;

import android.content.Context;

import android.content.Intent;
import android.graphics.PointF;
import android.graphics.Typeface;
import android.net.Uri;
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
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class OrderStatusAdapter_ extends RecyclerView.Adapter<OrderStatusAdapter_.ViewHolder> {

    private String[] mData;
    private LayoutInflater mInflater;
    List<OrderStatusModel>items;
    private Context context;
    // data is passed into the constructor
    OrderStatusAdapter_(Context context, List<OrderStatusModel>items) {
        this.context = context;
        this.items = items;
    }

    @Override
    @NonNull
    public OrderStatusAdapter_.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_status_item,parent,false);
        return new OrderStatusAdapter_.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderStatusAdapter_.ViewHolder holder, int position) {

        final OrderStatusModel List = items.get(position);
        Typeface custom_font2 = Typeface.createFromAsset(context.getAssets(),  "fonts/GravityBold.otf");
        Typeface custom_font = Typeface.createFromAsset(context.getAssets(),  "fonts/ralewayM.ttf");

        Typeface custom_font3 = Typeface.createFromAsset(context.getAssets(),  "fonts/RalewayBold.ttf");

        holder.ItemName.setTypeface(custom_font3);
        holder.ItemDesc.setTypeface(custom_font);


        holder.ItemName.setText(List.getBrandName());
        holder.ItemDesc.setText(List.getShortDesc());

        holder.pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, Payment.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("ProductKey",List.ProductKey);
                context.getApplicationContext().startActivity(intent);
            }
        });







        List<StepBean> stepsBeanList = new ArrayList<>();
        String state = List.getOrderStatusName();


        holder.stepBean0 = new StepBean() ;

        holder. stepBean1 = new StepBean() ;

        holder.stepBean2 = new StepBean();

        holder.stepBean3 = new StepBean();

        holder.stepBean4 = new StepBean();

        holder.stepBean5 = new StepBean();

        stepsBeanList.add( holder.stepBean0);
        stepsBeanList.add( holder.stepBean1);
        stepsBeanList.add( holder.stepBean2);
        stepsBeanList.add( holder.stepBean3);
        stepsBeanList.add( holder.stepBean4);

        Log.e("stepinggggg",""+ state);





        switch (state.trim()){
            case "Pending":

                holder.status.setText("Pending");
                holder.pay.setVisibility(View.GONE);
                holder.stepBean0.setState(0);
                holder.stepBean0.setName("Pending");
                holder.stepBean1.setName("Approved");
                holder. stepBean1.setState(-1);
                holder.stepBean2.setName("Payment");
                holder.stepBean2.setState(-1);
                holder.stepBean3.setName("Shipping");
                holder. stepBean3.setState(-1);
                holder.stepBean4.setName("Delivered");
                holder. stepBean4.setState(-1);
                break;
            case "Order Confirmed":

                holder.status.setText("Approved");
                holder.pay.setVisibility(View.VISIBLE);
                holder.stepBean0.setState(1);
                holder.stepBean0.setName("Pending");
                holder.stepBean1.setName("Approved");
                holder.stepBean1.setState(0);
                holder. stepBean2.setName("Payment");
                holder.stepBean2.setState(-1);
                holder.stepBean3.setName("Shipping");
                holder.stepBean3.setState(-1);
                holder.stepBean4.setName("Delivered");
                holder. stepBean4.setState(-1);
                break;
            case "Amount Paid":
                holder.status.setText("Amount Paid");
                holder. pay.setVisibility(View.GONE);
                holder.stepBean0.setState(1);
                holder.stepBean0.setName("Pending");
                holder. stepBean1.setName("Approved");
                holder.stepBean1.setState(1);
                holder. stepBean2.setName("Amount Paid");
                holder.stepBean2.setState(0);
                holder.stepBean3.setName("Shipping");
                holder.stepBean3.setState(-1);
                holder.stepBean4.setName("Delivered");
                holder. stepBean4.setState(-1);
                break;
            case "Shipped":

                holder.status.setText("Shipped");
                holder. pay.setVisibility(View.GONE);
                holder.stepBean0.setState(1);
                holder.stepBean0.setName("Pending");
                holder. stepBean1.setName("Approved");
                holder.stepBean1.setState(1);
                holder. stepBean2.setName("Payment");
                holder.stepBean2.setState(1);
                holder.stepBean3.setName("Shipping");
                holder.stepBean3.setState(0);
                holder.stepBean4.setName("Delivered");
                holder. stepBean4.setState(-1);
                break;
            case "Order Cancelled":

                holder.horizontalStepView.setVisibility(View.GONE);
                break;

            case "Delivered":

                holder.status.setText("Delivered");
                holder. pay.setVisibility(View.GONE);
                holder.stepBean0.setState(1);
                holder.stepBean0.setName("Pending");
                holder. stepBean1.setName("Approved");
                holder.stepBean1.setState(1);
                holder. stepBean2.setName("Payment");
                holder.stepBean2.setState(1);
                holder.stepBean3.setName("Shipping");
                holder.stepBean3.setState(1);
                holder.stepBean4.setName("Delivered");
                holder. stepBean4.setState(0);
                break;
            case "Feedback":

                holder.status.setText("Delivered");
                holder. pay.setVisibility(View.GONE);
                holder.stepBean0.setState(1);
                holder.stepBean0.setName("Pending");
                holder. stepBean1.setName("Approved");
                holder.stepBean1.setState(1);
                holder. stepBean2.setName("Payment");
                holder.stepBean2.setState(1);
                holder.stepBean3.setName("Shipping");
                holder.stepBean3.setState(1);
                holder.stepBean4.setName("Delivered");
                holder. stepBean4.setState(0);
                break;
//                default:
//                    view_holder.horizontalStepView.setVisibility(View.GONE);
//                    break;

        }






        holder.horizontalStepView.setStepViewTexts(stepsBeanList)
                .setTextSize(10)//set textSize

                .setStepsViewIndicatorCompletedLineColor(ContextCompat.getColor(context, android.R.color.black))
                .setStepsViewIndicatorUnCompletedLineColor(ContextCompat.getColor(context, R.color.col5))
                .setStepViewComplectedTextColor(ContextCompat.getColor(context, R.color.col5))
                .setStepViewUnComplectedTextColor(ContextCompat.getColor(context, R.color.col5))
                .setStepsViewIndicatorCompleteIcon(ContextCompat.getDrawable(context, R.drawable.radio_pressed))
                .setStepsViewIndicatorDefaultIcon(ContextCompat.getDrawable(context, R.drawable.radio_bg))
                .setStepsViewIndicatorAttentionIcon(ContextCompat.getDrawable(context, R.drawable.checkmark));


        try {

            PointF focusPoint = new PointF(0f, 0.5f);


            URL url = new URL(context.getString(R.string.ImgUrl)+List.getImagePath());

            ImageRequest imageRequest = ImageRequestBuilder.newBuilderWithSource(Uri.parse(url.toURI().toString()))
                    .setAutoRotateEnabled(true)
                    .build();

            DraweeController draweeController = Fresco.newDraweeControllerBuilder()
                    .setImageRequest(imageRequest)
                    .build();
            holder.Icon
                    .getHierarchy()
                    .setActualImageFocusPoint(focusPoint);
            holder.Icon.setController(draweeController);
        } catch (Exception e) {
            //
        }

        holder.Amount.setText(List.getAmount());
        holder.BV.setText(List.getBV());




    }

    @Override
    public int getItemCount() {
        return items.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder  {
        TextView ItemName,ItemDesc;
        LinearLayout itemLayout;
        HorizontalStepView horizontalStepView;
        SimpleDraweeView Icon;
        Button pay;
        TextView status,BV,Amount;
        StepBean stepBean0,stepBean1,stepBean2,stepBean3,stepBean4,stepBean5;

        ViewHolder(View itemView) {
            super(itemView);
            horizontalStepView = (HorizontalStepView)itemView.findViewById(R.id.step_view) ;

            status  = itemView.findViewById(R.id.state);
            Amount  = itemView.findViewById(R.id.amount);
            BV  = itemView.findViewById(R.id.BV);
            pay = itemView.findViewById(R.id.payment);
            ItemName = itemView.findViewById(R.id.itemName);
            ItemDesc = itemView.findViewById(R.id.itemDesc);
            Icon = itemView.findViewById(R.id.image);

        }







    }


}
