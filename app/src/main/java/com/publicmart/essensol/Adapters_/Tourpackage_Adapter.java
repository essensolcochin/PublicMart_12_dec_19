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

public class Tourpackage_Adapter extends RecyclerView.Adapter<Tourpackage_Adapter.Tourpackage_ViewHolder>{

    private Context context;
    private ArrayList<Integer> mData;

    public Tourpackage_Adapter(Context context, ArrayList<Integer> mData) {
        this.context = context;
        this.mData = mData;
    }

    @NonNull
    @Override
    public Tourpackage_ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.tour_items,viewGroup,false);
        return new Tourpackage_Adapter.Tourpackage_ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Tourpackage_ViewHolder viewHolder, int i) {

        Glide
                .with(context)
                .load(mData.get(i))
                .into(viewHolder.image);

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class Tourpackage_ViewHolder extends RecyclerView.ViewHolder{

        ImageView image;

        public Tourpackage_ViewHolder(@NonNull View itemView) {
            super(itemView);
            image=itemView.findViewById(R.id.image);

        }
    }
}
