package com.publicmart.essensol.TabFragments;


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
import com.publicmart.essensol.ModelClasses.BoatStausModel;
import com.publicmart.essensol.R;
import com.publicmart.essensol.RetrofitUtils.ApiClient;
import com.publicmart.essensol.RetrofitUtils.ApiInterface;
import com.publicmart.essensol.RetrofitUtils.RetrofitResponseClasses.GetBoatBookingStatusResponse;
import com.publicmart.essensol.Utility;
import com.publicmart.essensol.Adapters_._BookingStatusBoatAdapter_;

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





    private  void getStatus(){


        SharedPreferences sp = getActivity().getSharedPreferences("UserLog",0);
        String CustKey =  sp.getString("CustKey",null);




        apiInterface= ApiClient.getClient().create(ApiInterface.class);


        apiInterface.GetBoatBookingStatus(Integer.parseInt(CustKey)).enqueue(new Callback<GetBoatBookingStatusResponse>() {
            @Override
            public void onResponse(Call<GetBoatBookingStatusResponse> call, retrofit2.Response<GetBoatBookingStatusResponse> response) {

                if (response.isSuccessful() && response.code() == 200) {
                    assert response.body() != null;
                    if (response.body().getCode().equalsIgnoreCase("0")) {

                        item_list.clear();

                        List<GetBoatBookingStatusResponse.ResultArray> result = response.body().getResponse();



                        for (int i = 0; i < result.size(); i++) {

                            BoatStausModel items = new BoatStausModel(

                                    result.get(i).getType(),
                                    result.get(i).getPassengerName(),
                                    result.get(i).getTravelDate(),
                                    result.get(i).getSource(),
                                    result.get(i).getDestination(),
                                    result.get(i).getAmount(),
                                    result.get(i).getContactEmail(),
                                    result.get(i).getContactNo(),
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

                Log.e("Error  Codeeeeeeeeeeee","  "+t.getMessage());

            }
        });



    }

    @Override
    public void onResume() {
        super.onResume();

        getStatus();
    }
}
