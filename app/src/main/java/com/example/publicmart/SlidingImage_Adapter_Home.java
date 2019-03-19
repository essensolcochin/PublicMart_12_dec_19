package com.example.publicmart;

import android.content.Context;
import android.graphics.PointF;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;


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
        final SimpleDraweeView imageView = (SimpleDraweeView) imageLayout
                .findViewById(R.id.image);
        final TextView Title = (TextView) imageLayout
                .findViewById(R.id.ad);

        try {
            PointF focusPoint = new PointF(0f, 0.5f);

            URL url = new URL(context.getString(R.string.ImgUrl)+IMAGES.get(position).getImagePath());
            ImageRequest imageRequest = ImageRequestBuilder.newBuilderWithSource(Uri.parse(url.toURI().toString()))
                    .setAutoRotateEnabled(true)
                    .build();
            DraweeController draweeController = Fresco.newDraweeControllerBuilder()
                    .setImageRequest(imageRequest)
                    .build();



            imageView.setController(draweeController);
        } catch (Exception e) {
        }

        custom_font = Typeface.createFromAsset(context.getAssets(),  "fonts/CODEBold.otf");
        Title.setTypeface(custom_font);

        Title.setText(IMAGES.get(position).getImageDesc());

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
