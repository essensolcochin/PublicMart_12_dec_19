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
import com.publicmart.android.Adapters_.BookingStatusAdapter_;
import com.publicmart.android.ModelClasses.BookingstatusModel;
import com.publicmart.android.R;
import com.publicmart.android.Adapters_.TrainBookStatusAdapter_;
import com.publicmart.android.ModelClasses.TrainBookingModel;
import com.publicmart.android.RetrofitUtils.ApiClient;
import com.publicmart.android.RetrofitUtils.ApiInterface;
import com.publicmart.android.RetrofitUtils.RetrofitResponseClasses.GetTrainBookingStatusResponse;
import com.publicmart.android.Utility;

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


/**
 * A simple {@link Fragment} subclass.
 */
public class TrainTab extends Fragment {


    TrainBookStatusAdapter_ adapter_;
    List<TrainBookingModel> item_list;
    RecyclerView recyclerView;

    ApiInterface apiInterface;

    public TrainTab() {
        // Required empty public constructortr
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setUserVisibleHint(false);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View RootView = inflater.inflate(R.layout.fragment_train_tab, container, false);


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);


        recyclerView =  RootView.findViewById(R.id.trainRecycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(linearLayoutManager);




        item_list = new ArrayList<>();

        if(getUserVisibleHint()){

            getStatus();
        }





        return RootView;
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


        apiInterface.GetTrainBookingStatus(Integer.parseInt(CustKey),"T").enqueue(new Callback<GetTrainBookingStatusResponse>() {
            @Override
            public void onResponse(Call<GetTrainBookingStatusResponse> call, retrofit2.Response<GetTrainBookingStatusResponse> response) {

                if (response.isSuccessful() && response.code() == 200) {
                    assert response.body() != null;
                    if (response.body().getCode().equalsIgnoreCase("0")) {

                        item_list.clear();

                        List<GetTrainBookingStatusResponse.ResultArray> result = response.body().getResponse();



                        for (int i = 0; i < result.size(); i++) {

                            TrainBookingModel items = new TrainBookingModel(

                                    result.get(i).getTrainBookingKey(),
                                    result.get(i).getPassengerName(),
                                    result.get(i).getFromStationCode(),
                                    result.get(i).getToStationCode(),
                                    result.get(i).getTravelDate(),
                                    result.get(i).getAmount(),
                                    result.get(i).getBookingStatusKey(),
                                    result.get(i).getBookingStatusName());

                            item_list.add(items);

                        }


                        adapter_ = new TrainBookStatusAdapter_(getContext(), item_list);
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
            public void onFailure(Call<GetTrainBookingStatusResponse> call, Throwable t) {

            }
        });



    }

}
