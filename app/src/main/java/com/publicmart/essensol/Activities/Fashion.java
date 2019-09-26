package com.publicmart.essensol.Activities;



import android.os.Bundle;

import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.publicmart.essensol.Adapters_.MyRecyclerViewAdapter;
import com.publicmart.essensol.ModelClasses.ProductModelClass;
import com.publicmart.essensol.R;
import com.publicmart.essensol.RetrofitUtils.ApiClient;
import com.publicmart.essensol.RetrofitUtils.ApiInterface;
import com.publicmart.essensol.RetrofitUtils.RetrofitResponseClasses.GetProductsByCategoryResponse;
import com.publicmart.essensol.Utility;


import java.util.ArrayList;
import java.util.List;

import dmax.dialog.SpotsDialog;
import retrofit2.Call;
import retrofit2.Callback;

public class Fashion extends BaseActivity {

    LinearLayout ll;
    List<ProductModelClass> item_list;
    ArrayList<ProductModelClass>newlist =new ArrayList<>();

    private MyRecyclerViewAdapter adapter;
    GridLayoutManager layoutManager;

    int pageNo= 1;
    private int ItemCount= 6;
    ProgressBar Loader ;
    private int visibleItemCount,pastVisibleItemCount,totalItemCount,previousCount= 0;
    private  int view_threshold =5;
    private boolean isloading=true;
    private boolean isLastPage =false;

    TextView txtxmpny;
    RecyclerView recyclerView;
    String CategoryKey,CategoryName;
    ApiInterface apiInterface;
    SpotsDialog progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FrameLayout contentFrameLayout = (FrameLayout) findViewById(R.id.content_frame);
        getLayoutInflater().inflate(R.layout.activity_fashion, contentFrameLayout);

        item_list = new ArrayList<>();

        progress = new SpotsDialog(Fashion.this,R.style.Custom);
        Loader = new ProgressBar(this);


        apiInterface= ApiClient.getClient().create(ApiInterface.class);









        Bundle abBundle= getIntent().getExtras();
        CategoryKey = abBundle.getString("CategoryKey");
        CategoryName = abBundle.getString("CategoryName");

        // set up the RecyclerView
        recyclerView = findViewById(R.id.rvNumbers);
        layoutManager =new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(layoutManager);

        android.support.v7.widget.Toolbar tb=getToolBar();
        txtxmpny=(TextView)tb.findViewById(R.id.appname);
        txtxmpny.setText(CategoryName);

        progress.show();




//        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
//            @Override
//            public void onScrolled( RecyclerView recyclerView, int dx, int dy) {
//                super.onScrolled(recyclerView, dx, dy);
//
//                visibleItemCount = layoutManager.getChildCount();
//                totalItemCount = layoutManager.getItemCount();
//                pastVisibleItemCount =layoutManager.findFirstVisibleItemPosition();
//
//                Log.e("pastVisibleItemCount", " " + pastVisibleItemCount);
//                Log.e("total item",""+totalItemCount);
//
//                Log.e("visibleCount", " " + visibleItemCount);
//
//
//
////                if(totalItemCount>previousCount)
////                {
////                    isloading = false;
////                    previousCount=totalItemCount;
////                }
////
//
//                if(isloading && (visibleItemCount+pastVisibleItemCount == totalItemCount))
//                {
//
////                    pageNo = pageNo++;
//
//                    Loader.setVisibility(View.VISIBLE);
//                    PerformPaging(pageNo+1);
//                    isloading = false;
//
//
//
//
//
//                    Log.e("insideIFFFFF", " " + pageNo );
//
//                }
//
//
//
//
//
//
//
//
//
//
//
//          }
//       });



        LoadProducts();

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);


                if (dy > 0) {

                    visibleItemCount = layoutManager.getChildCount();
                    totalItemCount = layoutManager.getItemCount();
                    pastVisibleItemCount = layoutManager.findFirstVisibleItemPosition();

//                    Log.e("pastVisibleItemCount", " " + pastVisibleItemCount);
//                    Log.e("total item", "" + totalItemCount);
//
//                    Log.e("visibleCount", " " + visibleItemCount);


                    if (isloading && (visibleItemCount + pastVisibleItemCount == totalItemCount)) {


                        pageNo++;
//                        Loader.setVisibility(View.VISIBLE);
                        PerformPaging(pageNo);
                        isloading = false;


                        Log.e("insideIFFFFF", " " + pageNo);

                    }


                }


            }
        });







    }



    private void LoadProducts() {

        apiInterface.GetProductCategory(pageNo,ItemCount,CategoryKey).enqueue(new Callback<GetProductsByCategoryResponse>() {
            @Override
            public void onResponse(Call<GetProductsByCategoryResponse> call, retrofit2.Response<GetProductsByCategoryResponse> response) {

                if (response.isSuccessful() && response.code() == 200) {
                    assert response.body() != null;
                    if (response.body().getCode().equalsIgnoreCase("0")) {
                        progress.cancel();
                        List<GetProductsByCategoryResponse.ResultArray> result = response.body().getResponse();
                        for (int i = 0; i < result.size(); i++) {


                            ProductModelClass items = new ProductModelClass(result.get(i).getProductKey(),
                                    result.get(i).getBrandName(),
                                    result.get(i).getShortDesc(),
                                    result.get(i).getMRP(),
                                    result.get(i).getBV(),
                                    result.get(i).getImagePath());

                            item_list.add(items);


                        }

                        adapter = new MyRecyclerViewAdapter(getApplicationContext(), item_list);
                        recyclerView.setAdapter(adapter);

                    }
                    else
                    {
                        progress.cancel();
                        Utility.ShowCustomToast("Coming Soon",Fashion.this);

                    }
                }

                else if(response.code() == 401) {
                    progress.cancel();
                    Log.e("Error  Codeeeeeeeeeeee","  "+response.code());
                }

                else if( response.code() == 500) {
                    progress.cancel();
                    Log.e("Error  Codeeeeeeeeeeee","  "+response.code());
                }

                else if(response.code() == 408) {
                    progress.cancel();
                    Log.e("Error  Codeeeeeeeeeeee","  "+response.code());
                }



            }

            @Override
            public void onFailure(Call<GetProductsByCategoryResponse> call, Throwable t) {
                progress.cancel();
            }
        });
    }

//    private void Perform_pagination(int Pageno){
//
//        Log.e("insideFUNCTION", " " + Pageno );
//
//
//        Loader.setVisibility(View.VISIBLE);
//
//        try {
//
//            final JSONObject jsonString;
//            JSONObject values = new JSONObject();
//            values.put("CategoryKey",CategoryKey);
//            values.put("PageSize",ItemCount);
//            values.put("PageNumber",Pageno);
//
//            jsonString = new JSONObject();
//            jsonString.put("Token", "0001");
//            jsonString.put("call", "GetActiveProductListByCategoryId");
//            jsonString.put("values", values);
//
//
//
//            String URL = this.getString(R.string.Url)+"Select";
//
//
//            StringRequest stringRequest=new StringRequest(Request.Method.POST, URL,
//                    new Response.Listener<String>() {
//                        @Override
//                        public void onResponse(String response) {
//
//                            Loader.setVisibility(View.GONE);
//
//                            Log.e("Jsonnnn",""+response);
//
//
//                            try {
//
//
//                                JSONObject o     = new JSONObject(response);
//
//
//
//                                Log.e("tryyyyyyyyy","in"+o);
//
//
//                                String  code = o.getString("responseCode");
//                                String  message=o.getString("responseMessage");
//
//
//
//
//                                if (code.equalsIgnoreCase("0")) {
//
//                                    newlist = new ArrayList<>();
//
//                                    JSONArray json_array2 = o.getJSONArray("result");
//                                    JSONObject jsonObject;
//
//                                    int j;
//                                    for (j = 0; j < json_array2.length(); j++) {
//                                        jsonObject = json_array2.getJSONObject(j);
//
//
//                                        ProductModelClass items = new ProductModelClass(jsonObject.getString("ProductKey"),
//                                                jsonObject.getString("BrandName"),
//                                                jsonObject.getString("ShortDesc"),
//                                                jsonObject.getString("MRP"),
//                                                jsonObject.getString("BV"),
//                                                jsonObject.getString("ImagePath"));
//
//
//                                        newlist.add(items);
//
//
//                                    }
//
//                                    Log.e("listttttt ", "LIST " + newlist.size());
//
//                                    adapter.addData(newlist);
//
////                                    isloading = true;
//
//
//                                }
////                                else {
////                                    Toast.makeText(Fashion.this,message,Toast.LENGTH_SHORT).show();
////                                }
//
//
//
//
//                            } catch (JSONException e) {
//                                e.printStackTrace();
//                            }
//                        }
//
//
//
//                    },
//                    new Response.ErrorListener() {
//                        @Override
//                        public void onErrorResponse(VolleyError error) {
//                            // progress.cancel();
//                            Toast.makeText(getApplicationContext(), "Some Error Occurred ", Toast.LENGTH_SHORT).show();
//
//                        }
//                    })
//
//
//            {
//
//                @Override
//                protected Map<String, String> getParams() throws AuthFailureError {
//                    Map<String, String> param = new HashMap<String, String>();
//                    param.put("jsonString",jsonString.toString() );
//                    Log.e("paramssss",""+param);
//                    return param;
//                }
//
//                @Override
//                public Map<String, String> getHeaders() throws AuthFailureError {
//                    Map<String,String> param = new HashMap<String, String>();
//                    param.put("Content-Type","application/x-www-form-urlencoded");
//                    return param;
//                }
//            }
//                    ;
//
//            RequestQueue requestQueue= Volley.newRequestQueue(this);
//            requestQueue.add(stringRequest);
//
//
//
//        } catch (Exception e) {
//            // JSONException
//        }
//
//
//
//    }


    private void PerformPaging(int Pageno) {

        Log.e("insideIFFFFF", "PerformPaging " + Pageno);

        apiInterface.GetProductCategory(Pageno,ItemCount,CategoryKey).enqueue(new Callback<GetProductsByCategoryResponse>() {
            @Override
            public void onResponse(Call<GetProductsByCategoryResponse> call, retrofit2.Response<GetProductsByCategoryResponse> response) {

                if (response.isSuccessful() && response.code() == 200) {
                    assert response.body() != null;
                    if (response.body().getCode().equalsIgnoreCase("0")) {
                        newlist.clear();
                        List<GetProductsByCategoryResponse.ResultArray> result = response.body().getResponse();
                        for (int i = 0; i < result.size(); i++) {


                            ProductModelClass items = new ProductModelClass(result.get(i).getProductKey(),
                                    result.get(i).getBrandName(),
                                    result.get(i).getShortDesc(),
                                    result.get(i).getMRP(),
                                    result.get(i).getBV(),
                                    result.get(i).getImagePath());

                            newlist.add(items);


                        }

                        adapter.addData(newlist);
                        isloading=true;
                    }
                    else
                    {

//                        Utility.ShowCustomToast("Coming Soon",Fashion.this);

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
            public void onFailure(Call<GetProductsByCategoryResponse> call, Throwable t) {

            }
        });
    }


}


