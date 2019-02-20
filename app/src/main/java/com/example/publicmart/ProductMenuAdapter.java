package com.example.publicmart;

import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class ProductMenuAdapter extends RecyclerView.Adapter<ProductMenuAdapter.MenuViewHolder> {

    private Context context;
    private List<String> menu_list;
    private String[] mData;
    public ProductMenuAdapter(Context context, String[] Data) {
        this.context = context;
        this.mData = Data;
    }

    @NonNull
    @Override
    public MenuViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.menuitems,viewGroup,false);
        return new MenuViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull MenuViewHolder menuViewHolder, int i) {

        Typeface custom_font = Typeface.createFromAsset(context.getAssets(),  "fonts/curvy.ttf");

        menuViewHolder.MenuTitle.setTypeface(custom_font);

    }

    @Override
    public int getItemCount() {
        return mData.length;
    }

    public class MenuViewHolder extends RecyclerView.ViewHolder {

        TextView MenuTitle;
        ImageView Logo;

        public MenuViewHolder(@NonNull View itemView) {
            super(itemView);

            MenuTitle=itemView.findViewById(R.id.menuTitle);
        }
    }

}
