package com.publicmart.android.Activities;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.crashlytics.android.Crashlytics;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.gson.Gson;
import com.publicmart.android.ModelClasses.$ProductMenuModel;
import com.publicmart.android.Adapters_.ProductMenuAdapter;
import com.publicmart.android.R;
import com.publicmart.android.RetrofitUtils.ApiClient;
import com.publicmart.android.RetrofitUtils.ApiInterface;
import com.publicmart.android.RetrofitUtils.RetrofitResponseClasses.GetProductCategoryResponse;
import com.publicmart.android.Utility;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import dmax.dialog.SpotsDialog;
import io.fabric.sdk.android.Fabric;
import retrofit2.Call;
import retrofit2.Callback;

public class Products extends BaseActivity {


    JSONObject jsonString;
    TextView fresh,fashion;
    String code,message;
    ProductMenuAdapter adapter;
    GridLayoutManager layoutManager;
    RecyclerView recyclerView;
    List<$ProductMenuModel>menuModel=new ArrayList<>();
    List<$ProductMenuModel>headermodel=new ArrayList<>();
    List<$ProductMenuModel>lisitem=new ArrayList<>();

    SpotsDialog progress ;
    ApiInterface apiInterface;
    ShimmerFrameLayout shimmer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FrameLayout contentFrameLayout = (FrameLayout) findViewById(R.id.content_frame);
        getLayoutInflater().inflate(R.layout.activity_products_recycle, contentFrameLayout);

        Fabric.with(this, new Crashlytics());


        progress = new SpotsDialog(Products.this,R.style.Custom);

//        android.support.v7.widget.Toolbar tb=getToolBar();

        recyclerView = findViewById(R.id.menu);
        shimmer= findViewById(R.id.shimmer);

        layoutManager=new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(layoutManager);
        layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int i) {
                return adapter.IsHeader(i) ? layoutManager.getSpanCount() : 1;


            }
        });

//        fresh= (TextView) findViewById(R.id.fresh);
//        fashion= (TextView) findViewById(R.id.fashion);

//        Typeface custom_font = Typeface.createFromAsset(getAssets(),  "fonts/collection.ttf");
//        Typeface custom_font1 = Typeface.createFromAsset(getAssets(),  "fonts/fresh.ttf");
//
//        fresh.setTypeface(custom_font1);
//        fashion.setTypeface(custom_font);


        apiInterface = ApiClient.getClient().create(ApiInterface.class);

        shimmer.startShimmer();
        ShowProducts();


//        progress.show();




    }

    public void forceCrash(View view) {
        throw new RuntimeException("This is a crash");
    }




    private  void ShowProducts() {
        apiInterface.GetProductCategories().enqueue(new Callback<GetProductCategoryResponse>() {
            @Override
            public void onResponse(Call<GetProductCategoryResponse> call, retrofit2.Response<GetProductCategoryResponse> response) {
                if (response.isSuccessful() && response.code() == 200) {
                    assert response.body() != null;
                    if (response.body().getCode().equalsIgnoreCase("0")) {
//                        progress.cancel();
                        List<GetProductCategoryResponse.ResultArray> result = response.body().getResponse();

                        $ProductMenuModel header = new $ProductMenuModel("0",
                                "Our",
                                "Collections",true);

                        headermodel.add(header);

                        for (int i = 0; i < result.size(); i++) {


                            $ProductMenuModel items = new $ProductMenuModel(result.get(i).getCategoryKey(),
                                    result.get(i).getCategoryName(),
                                    result.get(i).getImagePath(),false);

                            menuModel.add(items);

                          }


                        populateList();




                    }
                    else
                    {
//                        progress.cancel();
                        Utility.ShowCustomToast("Coming Soon",Products.this);

                    }
                }

                else if(response.code() == 401) {

                    Log.e("Error  Codeeeeeeeeeeee","  "+response.code());
                }

                else if( response.code() == 500) {

                    Log.e("Error  Codeeeeeeeeeeee","  "+response.code());
                }

                else if(response.code() == 408) {

                    Log.e("Error  Codeeeeeeeeeeee","  "+response.code());
                }




            }

            @Override
            public void onFailure(Call<GetProductCategoryResponse> call, Throwable t) {

            }
        });

    }





    private void populateList() {

        for (int i = 0; i < headermodel.size(); i++) {

            $ProductMenuModel head = new $ProductMenuModel(headermodel.get(i).getCategoryKey(),headermodel.get(i).getCategoryName(),headermodel.get(i).getImagePath(),true);

            lisitem.add(head);

            for (int j = i; j < menuModel.size(); j++) {



                $ProductMenuModel childModel = new $ProductMenuModel(menuModel.get(j).getCategoryKey(),menuModel.get(j).getCategoryName(),menuModel.get(j).getImagePath(),false);
                lisitem.add(childModel);


            }

        }
        String json = new Gson().toJson(lisitem);

        Log.e("finalArrayyyyy ","Loop In Fn  "+json);

        adapter = new ProductMenuAdapter(getApplicationContext(), lisitem);
        recyclerView.setAdapter(adapter);
        shimmer.stopShimmer();
        shimmer.setVisibility(View.GONE);

    }



}
