package com.essensol.publicmart;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class ServiceAdapter extends RecyclerView.Adapter<ServiceAdapter.ServiceViewHolder>{
private List<String>list;
private Context context;


    public ServiceAdapter(List<String> list, Context context) {

    }

    @NonNull
    @Override
    public ServiceViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ServiceViewHolder serviceViewHolder, int i) {
serviceViewHolder.title.setText(list.size());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public  class ServiceViewHolder extends RecyclerView.ViewHolder{
TextView title;
        public ServiceViewHolder(@NonNull View itemView) {
            super(itemView);
      // title=itemView.findViewById(R.id)
        }

    }

}
