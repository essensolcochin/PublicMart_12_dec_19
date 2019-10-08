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

public class TravelGuide_Adapter extends RecyclerView.Adapter<TravelGuide_Adapter.TravelGuideViewholder>{

    private Context context;
    private ArrayList<Integer> mData ;

    public TravelGuide_Adapter(Context context, ArrayList<Integer> mData) {
        this.context = context;
        this.mData = mData;
    }

    @NonNull
    @Override
    public TravelGuideViewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.tour_items,viewGroup,false);
        return new TravelGuide_Adapter.TravelGuideViewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TravelGuideViewholder viewholder, int i) {

        Glide
                .with(context)
                .load(mData.get(i))
                .into(viewholder.image);

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class TravelGuideViewholder extends RecyclerView.ViewHolder{

        ImageView image;

        public TravelGuideViewholder(@NonNull View itemView) {
            super(itemView);

            image=itemView.findViewById(R.id.image);
        }
    }
}
