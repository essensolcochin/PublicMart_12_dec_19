package com.example.publicmart;

import android.content.Context;

import android.graphics.Color;
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
import com.facebook.imagepipeline.common.ResizeOptions;
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

        holder.ItemName.setTypeface(custom_font2);
        holder.ItemDesc.setTypeface(custom_font2);

        holder.ItemName.setText(List.getBrandName());
        holder.ItemDesc.setText(List.getShortDesc());



        List<StepBean> stepsBeanList = new ArrayList<>();
        String state = List.getOrderStatusKey();


        holder.stepBean0 = new StepBean() ;
//        Log.e("step",""+stepBean0);

        holder. stepBean1 = new StepBean() ;


//

        holder.stepBean2 = new StepBean();





        holder.stepBean3 = new StepBean();

        holder.stepBean4 = new StepBean();

        stepsBeanList.add( holder.stepBean0);
        stepsBeanList.add( holder.stepBean1);
        stepsBeanList.add( holder.stepBean2);
        stepsBeanList.add( holder.stepBean3);

        Log.e("step",""+ holder.stepBean2.getState());

        if(state =="approved"){
            holder.status.setText("Approved");
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




        try {



            URL url = new URL(context.getString(R.string.ImgUrl)+List.getImagePath());

            ImageRequest imageRequest = ImageRequestBuilder.newBuilderWithSource(Uri.parse(url.toURI().toString()))
                    .setAutoRotateEnabled(true)
                    .setResizeOptions(new ResizeOptions(50, 50))
                    .build();

            DraweeController draweeController = Fresco.newDraweeControllerBuilder()
                    .setImageRequest(imageRequest)
                    .build();
            holder.Icon.setController(draweeController);
        } catch (Exception e) {
            //
        }
holder.status.setText(List.OrderStatusName);





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
        TextView status;
        StepBean stepBean0,stepBean1,stepBean2,stepBean3,stepBean4;

        ViewHolder(View itemView) {
            super(itemView);
            horizontalStepView = (HorizontalStepView)itemView.findViewById(R.id.step_view) ;

            status  = itemView.findViewById(R.id.state);
            pay = itemView.findViewById(R.id.payment);
            ItemName = itemView.findViewById(R.id.itemName);
            ItemDesc = itemView.findViewById(R.id.itemDesc);
            Icon = itemView.findViewById(R.id.image);

        }







    }


}
