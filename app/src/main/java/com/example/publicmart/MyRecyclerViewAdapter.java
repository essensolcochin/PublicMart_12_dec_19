package com.example.publicmart;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.ByteArrayOutputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public  class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder> {

private String[] mData;

private List<ProductModelClass>mylist;

//private final PipelineDraweeControllerBuilder mControllerBuilder =
//            Fresco.newDraweeControllerBuilder();

 private Context context;
        // data is passed into the constructor
        MyRecyclerViewAdapter(Context context, List<ProductModelClass> mylist) {
        this.context = context;
        this.mylist = mylist;
        }

@Override
@NonNull
public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_item,parent,false);
        return new ViewHolder(view);
        }

@Override
public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {

final ProductModelClass List = mylist.get(position);


//    Set<RequestListener> requestListeners = new HashSet<>();
//    requestListeners.add(new RequestLoggingListener());
//    ImagePipelineConfig config = ImagePipelineConfig.newBuilder(context)
//            // other setters
//            .setRequestListeners(requestListeners)
//            .build();
//    Fresco.initialize(context, config);
//    FLog.setMinimumLoggingLevel(FLog.VERBOSE);

//
//    try {
//
//
//
//        URL url = new URL("http://192.168.0.30:7899/images/product_category/saree.png");
//        ImageRequest imageRequest = ImageRequestBuilder.newBuilderWithSource(Uri.parse(url.toURI().toString()))
//                .setAutoRotateEnabled(true)
//                .setResizeOptions(new ResizeOptions(50, 50))
//                .build();
//        DraweeController draweeController = Fresco.newDraweeControllerBuilder()
//                .setImageRequest(imageRequest)
//                .build();
//        holder.product_image.setController(draweeController);
//    } catch (Exception e) {
//    }



    String base64 = List.getImagePath();
    Log.e("menugeterrrrrrrr","in"+base64);

    byte[] imageAsBytes = Base64.decode(base64.getBytes(), Base64.DEFAULT);
    holder.Image.setImageBitmap(BitmapFactory.decodeByteArray(imageAsBytes, 0, imageAsBytes.length));
    holder.ProductName.setText(List.ProductName);
    holder.desc.setText(List.getDescription());
    holder.Bv.setText(List.getBV());
    holder.Rs.setText(List.getMRP());




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
        return mylist.size();
        }


public class ViewHolder extends RecyclerView.ViewHolder  {
    TextView ProductName,desc,Bv,Rs;
    LinearLayout itemLayout;
   // SimpleDraweeView product_image;
    ImageView Image;
        ViewHolder(View itemView) {
        super(itemView);
            ProductName = itemView.findViewById(R.id.info_text);
            desc = itemView.findViewById(R.id.short_desc);
            Bv = itemView.findViewById(R.id.BV);
            Rs = itemView.findViewById(R.id.amount);
        itemLayout = itemView.findViewById(R.id.productLayout);
       // product_image = itemView.findViewById(R.id.image);
        Image = itemView.findViewById(R.id.image);


        }




}


}
