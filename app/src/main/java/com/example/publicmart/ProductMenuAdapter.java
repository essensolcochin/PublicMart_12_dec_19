package com.example.publicmart;

import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.common.ResizeOptions;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;

import java.net.URL;
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

        Typeface custom_font = Typeface.createFromAsset(context.getAssets(),  "fonts/curvy.ttf");
        final $ProductMenuModel List = menu_list.get(i);

        try {
            URL url = new URL(context.getString(R.string.ImgUrl)+menu_list.get(i).getImagePath());

            Log.e("Imageurllllllll","in "+context.getString(R.string.ImgUrl)+menu_list.get(i).getImagePath());

            ImageRequest imageRequest = ImageRequestBuilder.newBuilderWithSource(Uri.parse(url.toURI().toString()))
                    .setAutoRotateEnabled(true)
//                    .setResizeOptions(new ResizeOptions(50, 50))
                    .build();
            DraweeController draweeController = Fresco.newDraweeControllerBuilder()
                    .setImageRequest(imageRequest)
//                    .setLowResImageRequest()
                    .build();
            menuViewHolder.Logo.setController(draweeController);
        } catch (Exception e) {
        }



//        String base64 = menu_list.get(i).getImagePath();
//        Log.e("getterrrrrrrrrMenuiCON","in"+base64);
//        byte[] imageAsBytes = Base64.decode(base64.getBytes(), Base64.DEFAULT);
//
//        menuViewHolder.Logo.setImageBitmap(BitmapFactory.decodeByteArray(imageAsBytes, 0, imageAsBytes.length));

        menuViewHolder.MenuTitle.setText(menu_list.get(i).CategoryName);

        menuViewHolder.MenuTitle.setTypeface(custom_font);
        menuViewHolder.itemLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, Fashion.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("CategoryKey",List.CategoryKey);
                intent.putExtra("CategoryName",List.CategoryName);
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
        SimpleDraweeView Logo;
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
