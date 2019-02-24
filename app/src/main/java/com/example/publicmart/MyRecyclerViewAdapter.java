package com.example.publicmart;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.common.logging.FLog;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.backends.pipeline.PipelineDraweeControllerBuilder;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.common.ResizeOptions;
import com.facebook.imagepipeline.core.ImagePipelineConfig;
import com.facebook.imagepipeline.listener.RequestListener;
import com.facebook.imagepipeline.listener.RequestLoggingListener;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;

import java.net.URL;
import java.util.HashSet;
import java.util.Set;

public  class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder> {

private String[] mData;


private final PipelineDraweeControllerBuilder mControllerBuilder =
            Fresco.newDraweeControllerBuilder();

 private Context context;
        // data is passed into the constructor
        MyRecyclerViewAdapter(Context context, String[] data) {
        this.context = context;
        this.mData = data;
        }

@Override
@NonNull
public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_item,parent,false);
        return new ViewHolder(view);
        }

@Override
public void onBindViewHolder(@NonNull ViewHolder holder, int position) {



    Set<RequestListener> requestListeners = new HashSet<>();
    requestListeners.add(new RequestLoggingListener());
    ImagePipelineConfig config = ImagePipelineConfig.newBuilder(context)
            // other setters
            .setRequestListeners(requestListeners)
            .build();
    Fresco.initialize(context, config);
    FLog.setMinimumLoggingLevel(FLog.VERBOSE);


    try {
        URL url = new URL("http://192.168.0.30:7899/images/1.jpg");
        ImageRequest imageRequest = ImageRequestBuilder.newBuilderWithSource(Uri.parse(url.toURI().toString()))
                .setAutoRotateEnabled(true)
                .setResizeOptions(new ResizeOptions(50, 50))
                .build();
        DraweeController draweeController = Fresco.newDraweeControllerBuilder()
                .setImageRequest(imageRequest)
                .build();
        holder.product_image.setController(draweeController);
    } catch (Exception e) {
    }



//
//    Uri uri = Uri.parse("http://i.imgur.com/DvpvklR.png");
//    final ImageRequest imageRequest =
//            ImageRequestBuilder.newBuilderWithSource(uri)
//
//                    .build();
//    holder.product_image.setImageRequest(imageRequest);







   // holder.product_image.setImageURI(uri);


holder.itemLayout.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent intent = new Intent(context, ProductView.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.getApplicationContext().startActivity(intent);

    }
});


        }

@Override
public int getItemCount() {
        return mData.length;
        }


public class ViewHolder extends RecyclerView.ViewHolder  {
    TextView myTextView;
    LinearLayout itemLayout;
    SimpleDraweeView product_image;
        ViewHolder(View itemView) {
        super(itemView);
        myTextView = itemView.findViewById(R.id.info_text);
        itemLayout = itemView.findViewById(R.id.productLayout);
        product_image = itemView.findViewById(R.id.image);


        }







}


}
