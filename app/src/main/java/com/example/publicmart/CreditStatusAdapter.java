package com.example.publicmart;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

class CreditStatusAdapter extends RecyclerView.Adapter<CreditStatusAdapter.CreditViewHolder> {
    private Context context;
    private List<String> menu_list;
    private String[] mData;

    public CreditStatusAdapter(Context context, String[] mData) {
        this.context = context;
        this.mData = mData;
    }

    @NonNull
    @Override
    public CreditViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.credit_items,viewGroup,false);
        return new CreditStatusAdapter.CreditViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CreditViewHolder creditViewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return  mData.length;
    }

    class CreditViewHolder extends RecyclerView.ViewHolder{

        public CreditViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
