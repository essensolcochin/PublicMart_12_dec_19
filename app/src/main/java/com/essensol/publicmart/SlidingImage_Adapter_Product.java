package com.essensol.publicmart;

import android.content.Context;
import android.graphics.PointF;
import android.net.Uri;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.core.ImagePipeline;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;

import java.net.URL;
import java.util.ArrayList;


public class SlidingImage_Adapter_Product extends PagerAdapter {


    private ArrayList<String> IMAGES;
    private LayoutInflater inflater;
    private Context context;


    public SlidingImage_Adapter_Product(Context context, ArrayList<String> IMAGES) {
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
    public Object instantiateItem(ViewGroup view, int position) {
        View imageLayout = inflater.inflate(R.layout.slidingimages_layout, view, false);

        assert imageLayout != null;
        final SimpleDraweeView imageView = (SimpleDraweeView) imageLayout
                .findViewById(R.id.image);





        try {



            URL url = new URL(context.getString(R.string.ImgUrl)+IMAGES.get(position));

            Uri _URI = Uri.parse(url.toURI().toString());



            ImageRequest imageRequest = ImageRequestBuilder.newBuilderWithSource(_URI)
                    .setAutoRotateEnabled(true)
//                    .setResizeOptions(new ResizeOptions(50, 50))
                    .build();
            DraweeController draweeController = Fresco.newDraweeControllerBuilder()
                    .setImageRequest(imageRequest)
                    .build();

//            imageView.getHierarchy()
//                     .setActualImageFocusPoint(focusPoint);

            imageView.setController(draweeController);


            ImagePipeline imagePipeline = Fresco.getImagePipeline();
            imagePipeline.evictFromCache(_URI);



        } catch (Exception e) {
        }




        Log.e("urlllllllllll"," "+IMAGES.get(position).toString());


        view.addView(imageLayout, 0);
        int width = view.getMeasuredWidth();
        imageLayout.setMinimumWidth(width);
        int height = view.getMeasuredWidth()/2;
        imageLayout.setMinimumHeight(height);

        return imageLayout;
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
