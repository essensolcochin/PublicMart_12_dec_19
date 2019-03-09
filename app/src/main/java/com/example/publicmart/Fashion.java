package com.example.publicmart;



import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dmax.dialog.SpotsDialog;

public class Fashion extends BaseActivity {

    LinearLayout ll;
    List<ProductModelClass> item_list;
    List<ProductModelClass>newlist;

    MyRecyclerViewAdapter adapter;
    GridLayoutManager layoutManager;

    private  int pageNo=1;
    private int ItemCount=4;
    ProgressBar Loader ;
    private int visibleItemCount,pastVisibleItemCount,totalItemCount,previousCount= 0;
    private  int view_threshold =10;
    private boolean isloading = true;
    private boolean isLastPage =false;

    TextView txtxmpny;
    RecyclerView recyclerView;
    String CategoryKey,CategoryName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FrameLayout contentFrameLayout = (FrameLayout) findViewById(R.id.content_frame);
        getLayoutInflater().inflate(R.layout.activity_fashion, contentFrameLayout);

        item_list= new ArrayList<>();

        final SpotsDialog progress = new SpotsDialog(Fashion.this,R.style.Custom);
        Loader = new ProgressBar(this);

        progress.show();




        String[] data = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48"};




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

        try {

            final JSONObject jsonString;
            JSONObject values = new JSONObject();
           // values.put("ImageType","Product");
            values.put("CategoryKey",CategoryKey);
//            values.put("PageSize",ItemCount);
//            values.put("PageNumber",pageNo);


            jsonString = new JSONObject();
            jsonString.put("Token", "0001");
            jsonString.put("call", "GetActiveProductListByCategoryId");
            jsonString.put("values", values);



            String URL = this.getString(R.string.Url)+"Select";


            StringRequest stringRequest=new StringRequest(Request.Method.POST, URL,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                           progress.cancel();

                            Log.e("Jsonnnn",""+response);


                            try {


                                JSONObject o     = new JSONObject(response);

                                ////// Checking Json Response Is JSON Object Or Not ///////
                                String data = response;
                                Object json = new JSONTokener(data).nextValue();
                                if (json instanceof JSONObject){

                                    Log.e("objectttttt",""+json);
                                }

                                //you have an object
                                else if (json instanceof JSONArray){
                                    Log.e("Arrayyyyyyy",""+json);
                                }

                                ///////////////////////////////////////////

                                Log.e("tryyyyyyyyy","in"+o);


                                String  code = o.getString("responseCode");
                                String  message=o.getString("responseMessage");




                                if (code.equalsIgnoreCase("0")) {

                                    JSONArray json_array2 = o.getJSONArray("result");
                                    JSONObject jsonObject;

                                    int j;
                                    for (j = 0; j < json_array2.length(); j++) {
                                        jsonObject = json_array2.getJSONObject(j);


                                        ProductModelClass items = new ProductModelClass(jsonObject.getString("ProductKey"),
                                                jsonObject.getString("BrandName"),
                                                jsonObject.getString("ShortDesc"),
                                                jsonObject.getString("MRP"),
                                                jsonObject.getString("BV"),
                                                jsonObject.getString("ImagePath"));

                                        Log.e("resppppppp", "ifffff" + code);


                                        item_list.add(items);


                                    }

                                    adapter = new MyRecyclerViewAdapter(getApplicationContext(), item_list);
                                    recyclerView.setAdapter(adapter);




                                }
                                else {
                                    Toast.makeText(Fashion.this,message,Toast.LENGTH_LONG).show();
                                }




                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }



                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            // progress.cancel();
                            Toast.makeText(getApplicationContext(), "Some Error Occurred ", Toast.LENGTH_LONG).show();

                        }
                    })


            {

                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> param = new HashMap<String, String>();
                    param.put("jsonString",jsonString.toString() );
                    Log.e("paramssss",""+param);
                    return param;
                }

                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    Map<String,String> param = new HashMap<String, String>();
                    param.put("Content-Type","application/x-www-form-urlencoded");
                    return param;
                }
            }
                    ;

            RequestQueue requestQueue= Volley.newRequestQueue(this);
            requestQueue.add(stringRequest);



        } catch (Exception e) {
           // JSONException
        }


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
//              //-----check this tomorrow  if(isloading&&(visibleItemCount+pastVisibleItemCount==totalItemCount)) -----//
//
//
//
////                if(dy>0)
////                {
//
//                    Log.e("checkinggg dy","   "+dy);
//
//                    if(isloading)
//                    {
//
//                        if(totalItemCount>previousCount)
//                        {
//                            isloading =false;
//                            previousCount = totalItemCount;
//                        }
//
//                    }
//
//                    if(!isloading&&(totalItemCount-visibleItemCount)<=(pastVisibleItemCount+view_threshold))//false&&(6-4)<=(2+10)
//                    {
//
//                            pageNo++;
//                            Log.e("____PageNo_insideee___", "" + pageNo);
//                            Perform_pagination();
//                            Toast.makeText(Fashion.this, "Pagination", Toast.LENGTH_LONG).show();
//                            isloading = true;
//
//                    }
//
//
////                }
//
//
//          }
//       });

   }

    private void Perform_pagination(){
        Loader.setVisibility(View.VISIBLE);

        try {

            final JSONObject jsonString;
            JSONObject values = new JSONObject();
            values.put("CategoryKey",CategoryKey);
            values.put("PageSize",ItemCount);
            values.put("PageNumber",pageNo);

            jsonString = new JSONObject();
            jsonString.put("Token", "0001");
            jsonString.put("call", "GetActiveProductListByCategoryId");
            jsonString.put("values", values);



            String URL = this.getString(R.string.Url)+"Select";


            StringRequest stringRequest=new StringRequest(Request.Method.POST, URL,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                            Loader.setVisibility(View.GONE);

                            Log.e("Jsonnnn",""+response);


                            try {


                                JSONObject o     = new JSONObject(response);



                                Log.e("tryyyyyyyyy","in"+o);


                                String  code = o.getString("responseCode");
                                String  message=o.getString("responseMessage");




                                if (code.equalsIgnoreCase("0")) {

                                    JSONArray json_array2 = o.getJSONArray("result");
                                    JSONObject jsonObject;

                                    int j;
                                    for (j = 0; j < json_array2.length(); j++) {
                                        jsonObject = json_array2.getJSONObject(j);


                                        ProductModelClass items = new ProductModelClass(jsonObject.getString("ProductKey"),
                                                jsonObject.getString("BrandName"),
                                                jsonObject.getString("ShortDesc"),
                                                jsonObject.getString("MRP"),
                                                jsonObject.getString("BV"),
                                                jsonObject.getString("ImagePath"));

                                        Log.e("resppppppp", "ifffff" + code);
                                        newlist =new ArrayList<>();
                                        newlist.add(items);


                                    }

                                   // Log.e("____PageNo____",""+pageNo);

                                    adapter.addData(newlist);




                                }
                                else {
                                    Toast.makeText(Fashion.this,message,Toast.LENGTH_SHORT).show();
                                }




                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }



                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            // progress.cancel();
                            Toast.makeText(getApplicationContext(), "Some Error Occurred ", Toast.LENGTH_LONG).show();

                        }
                    })


            {

                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> param = new HashMap<String, String>();
                    param.put("jsonString",jsonString.toString() );
                    Log.e("paramssss",""+param);
                    return param;
                }

                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    Map<String,String> param = new HashMap<String, String>();
                    param.put("Content-Type","application/x-www-form-urlencoded");
                    return param;
                }
            }
                    ;

            RequestQueue requestQueue= Volley.newRequestQueue(this);
            requestQueue.add(stringRequest);



        } catch (Exception e) {
            // JSONException
        }



    }

}
