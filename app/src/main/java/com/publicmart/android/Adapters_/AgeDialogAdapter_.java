package com.publicmart.android.Adapters_;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.publicmart.android.R;

import java.util.ArrayList;

public class AgeDialogAdapter_ extends RecyclerView.Adapter<AgeDialogAdapter_.AgeViewHolder> {

        private  ArrayList<String>ages;
        private Context context;

    public AgeDialogAdapter_(ArrayList<String> ages, Context context) {
        this.ages = ages;
        this.context = context;
    }

    @NonNull
    @Override
    public AgeViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.agelayout,viewGroup,false);
        return new AgeDialogAdapter_.AgeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AgeViewHolder ageViewHolder, int i) {

        ageViewHolder.age.setText(ages.get(i));

    }

    @Override
    public int getItemCount() {
        return ages.size();
    }

    public  class AgeViewHolder extends RecyclerView.ViewHolder{

        TextView age;

        public AgeViewHolder(@NonNull View itemView) {
            super(itemView);

            age =itemView.findViewById(R.id.age);
        }
    }
}
