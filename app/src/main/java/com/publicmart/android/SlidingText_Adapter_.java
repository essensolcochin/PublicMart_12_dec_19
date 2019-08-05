package com.publicmart.android;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;

public class SlidingText_Adapter_ extends PagerAdapter {
    private ArrayList<Integer> IMAGES;
    private LayoutInflater inflater;
    private Context context;
    Typeface custom_font;

    public SlidingText_Adapter_(Context context, ArrayList<Integer> IMAGES) {
        this.context = context;
        this.IMAGES=IMAGES;
        inflater = LayoutInflater.from(context);
        Log.e("textsizeeeee",""+IMAGES.size());


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
    public Object instantiateItem(ViewGroup view, int position) {
        View texlayout = inflater.inflate(R.layout.sliding_text_services, view, false);

        assert texlayout != null;
        final SimpleDraweeView imageView = (SimpleDraweeView) texlayout
                .findViewById(R.id.adTXT);

//        custom_font = Typeface.createFromAsset(context.getAssets(),  "fonts/north.ttf");
//        imageView.setTypeface(custom_font);



        imageView.setActualImageResource(IMAGES.get(position));


        view.addView(texlayout, 0);


        return texlayout;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view.equals(object);
    }

    @Override
    public void restoreState(Parcelable state, ClassLoader loader) {
    }

    @Override
    public Parcelable saveState() {
        return null;
    }


}

