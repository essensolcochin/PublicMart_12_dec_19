package com.publicmart.android.TabFragments;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.publicmart.android.ModelClasses.BoatStausModel;
import com.publicmart.android.R;
import com.publicmart.android.RetrofitUtils.ApiClient;
import com.publicmart.android.RetrofitUtils.ApiInterface;
import com.publicmart.android.RetrofitUtils.RetrofitResponseClasses.GetBoatBookingStatusResponse;
import com.publicmart.android.Utility;
import com.publicmart.android.Adapters_._BookingStatusBoatAdapter_;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;


public class BoatTab extends Fragment {



    _BookingStatusBoatAdapter_ adapter_;
    List<BoatStausModel> item_list;
    RecyclerView recyclerView;
    ApiInterface apiInterface;

    public BoatTab() {
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setUserVisibleHint(false);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View RootView = inflater.inflate(R.layout.fragment_boat_tab, container, false);

        item_list =new ArrayList<>();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);


        recyclerView =  RootView.findViewById(R.id.BoatRecycle);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(linearLayoutManager);

        if(getUserVisibleHint()){

            getStatus();
        }
        return  RootView;

    }


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);

        if(isVisibleToUser&&isResumed()){

            getStatus();

        }



    }



    private  void LoadItems() {
        SharedPreferences sp = getActivity().getSharedPreferences("UserLog",0);
        String CustKey =  sp.getString("CustKey",null);


        try {

            final JSONObject jsonString;
            JSONObject values = new JSONObject();
            values.put("CustKey",CustKey);


            jsonString = new JSONObject();
            jsonString.put("Token", "0001");
            jsonString.put("call", "GetBookingDetailsByCustKey");
            jsonString.put("values", values);



            String URL = this.getString(R.string.Url)+"Select";


            StringRequest stringRequest=new StringRequest(Request.Method.POST, URL,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                            //progress.cancel();

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


                                Log.e("codeeeeeeeeee","in"+code);

                                if (code.equalsIgnoreCase("0")) {

                                    JSONObject jsonObject;
                                    jsonObject= o.getJSONObject("result");
                                    Log.e("Tableeeee","in"+jsonObject);
                                    JSONArray table;
                                    table = jsonObject.getJSONArray("Table2");

                                    int j;
                                    for (j = 0; j < table.length(); j++) {
                                        JSONObject tableObject = new JSONObject();

                                        tableObject = table.getJSONObject(j);


                                        Log.e("obj", "in" + tableObject);
                                        BoatStausModel items = new BoatStausModel(

                                                tableObject.getString("HBBookingKey"),
                                                tableObject.getString("PassengerName"),
                                                tableObject.getString("TravelDate"),
                                                tableObject.getString("GuestNos"),
                                                tableObject.getString("CruiseType"),
                                                tableObject.getString("Amount"),
                                                tableObject.getString("BookingStatusKey"),
                                                tableObject.getString("BookingStatusName"));

                                        item_list.add(items);

                                    }




                                    Log.e("newwwwwww","in"+item_list);
                                    adapter_ = new _BookingStatusBoatAdapter_(getContext(), item_list);
                                    recyclerView.setAdapter(adapter_);


                                }




//                                else {
//                                    Toast.makeText(getContext(),message,Toast.LENGTH_LONG).show();
//                                }




                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }



                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                            if (error instanceof TimeoutError || error instanceof NoConnectionError) {
                                Utility.ShowCustomToast(" No Network Connection",getContext());
                            } else if (error instanceof AuthFailureError) {
                                Utility.ShowCustomToast("Authentication Failed",getContext());
                            } else if (error instanceof ServerError) {

                                Utility.ShowCustomToast("Server Error Occurred",getContext());
                            } else if (error instanceof NetworkError) {

                                Utility.ShowCustomToast("Some Network Error Occurred",getContext());
                            } else if (error instanceof ParseError) {

                                Utility.ShowCustomToast("Some Error Occurred",getContext());
                            }

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

            RequestQueue requestQueue= Volley.newRequestQueue(getContext());
            requestQueue.add(stringRequest);



        } catch (Exception e) {
            // JSONException
        }

    }

    private  void getStatus(){


        SharedPreferences sp = getActivity().getSharedPreferences("UserLog",0);
        String CustKey =  sp.getString("CustKey",null);




        apiInterface= ApiClient.getClient().create(ApiInterface.class);


        apiInterface.GetBoatBookingStatus(Integer.parseInt(CustKey),"H").enqueue(new Callback<GetBoatBookingStatusResponse>() {
            @Override
            public void onResponse(Call<GetBoatBookingStatusResponse> call, retrofit2.Response<GetBoatBookingStatusResponse> response) {

                if (response.isSuccessful() && response.code() == 200) {
                    assert response.body() != null;
                    if (response.body().getCode().equalsIgnoreCase("0")) {

                        item_list.clear();;

                        List<GetBoatBookingStatusResponse.ResultArray> result = response.body().getResponse();



                        for (int i = 0; i < result.size(); i++) {

                            BoatStausModel items = new BoatStausModel(

                                    result.get(i).getHBBookingKey(),
                                    result.get(i).getPassengerName(),
                                    result.get(i).getTravelDate(),
                                    result.get(i).getGuestNos(),
                                    result.get(i).getCruiseType(),
                                    result.get(i).getAmount(),
                                    result.get(i).getBookingStatusKey(),
                                    result.get(i).getBookingStatusName());

                            item_list.add(items);

//                            Log.e("Error  CruiseType()-->","  "+result.get(i).getCruiseType());

                        }


                        adapter_ = new _BookingStatusBoatAdapter_(getContext(), item_list);
                        recyclerView.setAdapter(adapter_);
//



                    }
//                    else
//                    {
////                        progress.cancel();
////                        Utility.ShowCustomToast("Coming Soon",Products.this);
//
//                    }
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
            public void onFailure(Call<GetBoatBookingStatusResponse> call, Throwable t) {

            }
        });



    }

    @Override
    public void onResume() {
        super.onResume();

        getStatus();
    }
}
