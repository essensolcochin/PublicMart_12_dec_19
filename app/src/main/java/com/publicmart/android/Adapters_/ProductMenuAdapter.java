package com.publicmart.android.Adapters_;

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
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;


import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.publicmart.android.ModelClasses.$ProductMenuModel;
import com.publicmart.android.Activities.Fashion;
import com.publicmart.android.R;
import com.publicmart.android.Utils.CONSTANTS;


import java.util.List;

public class ProductMenuAdapter extends RecyclerView.Adapter<ProductMenuAdapter.MenuViewHolder> {

    private Context context;
    private List<$ProductMenuModel> menu_list;


    public ProductMenuAdapter(Context context,  List<$ProductMenuModel> menu_list) {
        this.context = context;
        this.menu_list = menu_list;
    }

    @NonNull
    @Override
    public MenuViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.menuitems,viewGroup,false);
        return new MenuViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull MenuViewHolder menuViewHolder,  int i) {

        Typeface custom_font = Typeface.createFromAsset(context.getAssets(),  "fonts/ralewayM.ttf");
        final $ProductMenuModel List = menu_list.get(i);
        Log.e("size Adapter","in "+menu_list.size());




            Glide.with(context).load(CONSTANTS.ImgURL_ +List.getImagePath())
                    .thumbnail(0.5f)
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .skipMemoryCache(true)
                    .into(menuViewHolder.Logo);



        menuViewHolder.MenuTitle.setText(menu_list.get(i).getCategoryName());

        menuViewHolder.MenuTitle.setTypeface(custom_font);
        menuViewHolder.itemLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, Fashion.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("CategoryKey",List.getCategoryKey());
                intent.putExtra("CategoryName",List.getCategoryName());
                context.getApplicationContext().startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return menu_list.size();
    }

    public class MenuViewHolder extends RecyclerView.ViewHolder {

        TextView MenuTitle;
        ImageView Logo;
//        ImageView Logo;
        FrameLayout itemLayout;

        private MenuViewHolder(@NonNull View itemView) {
            super(itemView);

            MenuTitle=itemView.findViewById(R.id.menuTitle);
            itemLayout=itemView.findViewById(R.id.itemlayout);
            Logo=itemView.findViewById(R.id.menuIcon);
        }
    }

}
