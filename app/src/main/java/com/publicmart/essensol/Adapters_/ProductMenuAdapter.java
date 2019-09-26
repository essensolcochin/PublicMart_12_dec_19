package com.publicmart.essensol.Adapters_;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;


import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.publicmart.essensol.ModelClasses.$ProductMenuModel;
import com.publicmart.essensol.Activities.Fashion;
import com.publicmart.essensol.R;
import com.publicmart.essensol.Utils.CONSTANTS;


import java.util.List;

public class ProductMenuAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private List<$ProductMenuModel> menu_list;
    private static final int LAYOUT_HEADER= 0;
    private static final int LAYOUT_CHILD= 1;
    private LayoutInflater inflater;



    public ProductMenuAdapter(Context context, List<$ProductMenuModel> menu_list) {
        inflater = LayoutInflater.from(context);
        this.context = context;
        this.menu_list = menu_list;
    }



    @Override
    public int getItemCount() {
        return menu_list.size();
    }


    @Override
    public int getItemViewType(int position)
    {
        if(menu_list.get(position).isHeader()) {
            return LAYOUT_HEADER;
        }else
            return LAYOUT_CHILD;
    }




    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {


        RecyclerView.ViewHolder holder;
        if(i==LAYOUT_HEADER){
            View view = inflater.inflate(R.layout.rv_header, viewGroup, false);
            holder = new HeadViewHolder(view);
        }else {
            View view = inflater.inflate(R.layout.menuitems, viewGroup, false);
            holder = new MenuViewHolder(view);
        }

//        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.menuitems,viewGroup,false);
//        return new MenuViewHolder(view);
        return  holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder menuViewHolder,  int i) {



        Typeface custom_font2 = Typeface.createFromAsset(context.getAssets(),  "fonts/ralewayM.ttf");
        Typeface custom_font1 = Typeface.createFromAsset(context.getAssets(),  "fonts/ralewayM.ttf");
        Typeface custom_font = Typeface.createFromAsset(context.getAssets(),  "fonts/ralewayM.ttf");


        if(menuViewHolder.getItemViewType()== LAYOUT_HEADER)
        {
            IsHeader(i);

            HeadViewHolder ItemHolder = (HeadViewHolder) menuViewHolder;

            ItemHolder.Our.setTypeface(custom_font1);
            ItemHolder.Collection.setTypeface(custom_font2);

            ItemHolder.Our.setText(menu_list.get(i).getCategoryName());
            ItemHolder.Collection.setText(menu_list.get(i).getImagePath());
        }
        else {


            final $ProductMenuModel List = menu_list.get(i);
            final MenuViewHolder childHolder = (MenuViewHolder) menuViewHolder;



            Glide.with(context).load(CONSTANTS.ImgURL_ +List.getImagePath())
                    .thumbnail(0.5f)
                    .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                    .skipMemoryCache(true)
                    .into(childHolder.Logo);



            childHolder.MenuTitle.setText(menu_list.get(i).getCategoryName());

            childHolder.MenuTitle.setTypeface(custom_font);
            childHolder.itemLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, Fashion.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.putExtra("CategoryKey",List.getCategoryKey());
                    intent.putExtra("CategoryName",List.getCategoryName());
                    context.getApplicationContext().startActivity(intent);

                }
            });



            Animation expandIn = AnimationUtils.loadAnimation(context, R.anim.transmission_anim);
            childHolder.itemLayout.startAnimation(expandIn);

//            childHolder.itemLayout.setAnimation(scal);


        }





    }



     class HeadViewHolder extends RecyclerView.ViewHolder{

        TextView Our,Collection;

        public HeadViewHolder(@NonNull View itemView) {
            super(itemView);

            Our=itemView.findViewById(R.id.our);
            Collection=itemView.findViewById(R.id.collection);

        }
    }


     class MenuViewHolder extends RecyclerView.ViewHolder {

        TextView MenuTitle;
        ImageView Logo;
        CardView itemLayout;


        private MenuViewHolder(@NonNull View itemView) {
            super(itemView);

            MenuTitle=itemView.findViewById(R.id.menuTitle);
            itemLayout=itemView.findViewById(R.id.itemlayout);
            Logo=itemView.findViewById(R.id.menuIcon);

        }
    }

    public boolean IsHeader(int position){


        return position ==0;

    }

}
