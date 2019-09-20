package com.publicmart.android.Adapters_;

import android.app.Activity;
import android.content.Context;
import android.graphics.PointF;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.publicmart.android.ModelClasses.HomeAdModel;
import com.publicmart.android.R;
import com.publicmart.android.Utils.CONSTANTS;


import org.jetbrains.annotations.NotNull;

import java.net.URL;
import java.util.ArrayList;


public class SlidingImage_Adapter_Home extends PagerAdapter {


    private ArrayList<HomeAdModel> IMAGES;
    private LayoutInflater inflater;
    private Context context;
    Typeface custom_font;


    public SlidingImage_Adapter_Home(Context context, ArrayList<HomeAdModel> IMAGES) {
        this.context = context;
        this.IMAGES=IMAGES;
        inflater = LayoutInflater.from(context);



    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        return IMAGES.size();
    }

    @Override
    public Object instantiateItem(@NotNull ViewGroup view, int position) {
        View imageLayout = inflater.inflate(R.layout.slidingimages_home_layout, view, false);

        assert imageLayout != null;
        final ImageView imageView = (ImageView) imageLayout
                .findViewById(R.id.image);


        DisplayMetrics displaymetrics = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);


        int devicewidth = displaymetrics.widthPixels/2;

        int deviceheight = displaymetrics.heightPixels /4;
//        matchesAdapter_viewHolder.activityImage.getLayoutParams().width = devicewidth;

        imageView.getLayoutParams().height = deviceheight;


        Glide.with(context).load(CONSTANTS.ImgURL_ +IMAGES.get(position).getImagePath())
                .thumbnail(0.5f)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .skipMemoryCache(true)
                .into(imageView);



        final TextView Title = (TextView) imageLayout
                .findViewById(R.id.ad);



        custom_font = Typeface.createFromAsset(context.getAssets(),  "fonts/NoticiaText-Regular.ttf");
        Title.setTypeface(custom_font);
//
        Title.setText(IMAGES.get(position).getDesc());

        view.addView(imageLayout, 0);


        return imageLayout;
    }

    @Override
    public boolean isViewFromObject(@NotNull View view, @NotNull Object object) {
        return view.equals(object);
    }

    @Override
    public void restoreState(Parcelable state, ClassLoader loader) {
    }

    @Override
    public Parcelable saveState() {
        return null;
    }

    @Override
    public void destroyItem(@NonNull View container, int position, @NonNull Object object) {
        ((ViewPager) container).removeView((View)object);
    }
}
