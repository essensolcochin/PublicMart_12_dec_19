package com.essensol.publicmart;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

class Shopping_List_Adapter extends RecyclerView.Adapter<Shopping_List_Adapter.Shopping_ViewHolder> {

    private Context context;
    private List<String> menu_list;

    private String[] mData;

    public Shopping_List_Adapter(Context context, String[] mData) {
        this.context = context;
        this.mData = mData;
    }

    @NonNull
    @Override
    public Shopping_ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.shopping_items,viewGroup,false);
        return new Shopping_List_Adapter.Shopping_ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Shopping_ViewHolder shopping_viewHolder, int i) {

    }

    @Override
    public int getItemCount() {
       return mData.length;
    }

    class Shopping_ViewHolder extends RecyclerView.ViewHolder{

        public Shopping_ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
