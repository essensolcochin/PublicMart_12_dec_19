package com.publicmart.essensol.Adapters_;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.publicmart.essensol.R;

import java.util.ArrayList;

public class FoodImageAdapter extends RecyclerView.Adapter<FoodImageAdapter.FoodImageViewHolder>{

    private Context context;
    private ArrayList<Integer>mItems;

    public FoodImageAdapter(Context context, ArrayList<Integer>mItems) {
        this.context = context;
        this.mItems = mItems;
    }

    @NonNull
    @Override
    public FoodImageViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.food_image,viewGroup,false);
        return new FoodImageAdapter.FoodImageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodImageViewHolder viewHolder, int i) {



              Glide
                .with(context)
                .load(mItems.get(i))
                .into(viewHolder.image);

    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public class FoodImageViewHolder extends RecyclerView.ViewHolder{

        ImageView image;
        public FoodImageViewHolder(@NonNull View itemView) {
            super(itemView);

            image=itemView.findViewById(R.id.image);
        }
    }
}
