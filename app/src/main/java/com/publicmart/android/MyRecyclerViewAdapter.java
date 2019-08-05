package com.publicmart.android;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.backends.pipeline.PipelineDraweeControllerBuilder;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;

import java.net.URL;
import java.util.List;

public  class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder> {

private String[] mData;

private List<ProductModelClass>mylist;

private final PipelineDraweeControllerBuilder mControllerBuilder =
            Fresco.newDraweeControllerBuilder();

 private Context context;
        // data is passed into the constructor
        MyRecyclerViewAdapter(Context context, List<ProductModelClass> mylist) {
        this.context = context;
        this.mylist = mylist;
        }

@Override
@NonNull
public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_item,parent,false);
        int height = parent.getMeasuredHeight() / 4;
        view.setMinimumHeight(height);

        return new ViewHolder(view);
        }

@Override
public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {

    final ProductModelClass List = mylist.get(position);

    Typeface custom_font = Typeface.createFromAsset(context.getAssets(),  "fonts/ralewayM.ttf");



    try {



        URL url = new URL(context.getString(R.string.ImgUrl)+List.getImagePath());

        ImageRequest imageRequest = ImageRequestBuilder.newBuilderWithSource(Uri.parse(url.toURI().toString()))
                     .setAutoRotateEnabled(true)
                     //.setResizeOptions(new ResizeOptions(50, 50))
                     .build();

        DraweeController draweeController = Fresco.newDraweeControllerBuilder()
                         .setImageRequest(imageRequest)
                         .build();
        holder.product_image.setController(draweeController);
    } catch (Exception e) {
        //
    }



    holder.ProductName.setText(List.getBrandName());
    holder.ProductName.setTypeface(custom_font);
    holder.desc.setText(List.getShortDesc());
    holder.desc.setTypeface(custom_font);
    holder.Bv.setText(List.getBV());

    holder.Rs.setText(List.getMRP());

    Log.e("urlllllllllll"," "+"http://publicmart.in"+
    List.getImagePath());



        holder.itemLayout.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent intent = new Intent(context, ProductView.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("ProductKey",List.ProductKey);
        context.getApplicationContext().startActivity(intent);

    }
});


        }

@Override
public int getItemCount() {
        return mylist.size();
        }


public class ViewHolder extends RecyclerView.ViewHolder  {
    TextView ProductName,desc,Bv,Rs;
    LinearLayout itemLayout;
    SimpleDraweeView product_image;
    ImageView Image;


        ViewHolder(View itemView) {
        super(itemView);
            ProductName = itemView.findViewById(R.id.info_text);
            desc = itemView.findViewById(R.id.short_desc);
            Bv = itemView.findViewById(R.id.BV);
            Rs = itemView.findViewById(R.id.amount);
            itemLayout = itemView.findViewById(R.id.productLayout);
            product_image = itemView.findViewById(R.id.image);
//        Image = itemView.findViewById(R.id.image);


        }

}

public void addData(List<ProductModelClass>list)
{
            for (ProductModelClass Mclass:list)
            {

                mylist.add(Mclass);
            }
notifyDataSetChanged();
}


}
