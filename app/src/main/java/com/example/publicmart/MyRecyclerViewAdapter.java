package com.example.publicmart;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

public  class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder> {

private String[] mData;
private LayoutInflater mInflater;

 private Context context;
        // data is passed into the constructor
        MyRecyclerViewAdapter(Context context, String[] data) {
        this.context = context;
        this.mData = data;
        }

@Override
@NonNull
public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_item,parent,false);
        return new ViewHolder(view);
        }

@Override
public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    Uri uri = Uri.parse("http://i.imgur.com/DvpvklR.png");
    holder.product_image.setImageURI(uri);


holder.itemLayout.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent intent = new Intent(context, ProductView.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.getApplicationContext().startActivity(intent);

    }
});


        }

@Override
public int getItemCount() {
        return mData.length;
        }


public class ViewHolder extends RecyclerView.ViewHolder  {
    TextView myTextView;
    LinearLayout itemLayout;
    SimpleDraweeView product_image;
        ViewHolder(View itemView) {
        super(itemView);
        myTextView = itemView.findViewById(R.id.info_text);
        itemLayout = itemView.findViewById(R.id.productLayout);
        product_image = itemView.findViewById(R.id.image);


        }







}


}
