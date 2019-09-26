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

import com.publicmart.essensol.Adapters_.BookingStatusAdapter_;
import com.publicmart.essensol.ModelClasses.BookingstatusModel;
import com.publicmart.essensol.R;
import com.publicmart.essensol.RetrofitUtils.ApiClient;
import com.publicmart.essensol.RetrofitUtils.ApiInterface;
import com.publicmart.essensol.RetrofitUtils.RetrofitResponseClasses.GetFlightBookingStatusResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;


/**
 * A simple {@link Fragment} subclass.
 */
public class FlightTab extends Fragment {

    BookingStatusAdapter_ adapter_;
    List<BookingstatusModel> item_list;
    RecyclerView recyclerView;

    ApiInterface apiInterface;
    public FlightTab() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setUserVisibleHint(false);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View RootView = inflater.inflate(R.layout.fragment_flight_tab, container, false);


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);


        recyclerView =  RootView.findViewById(R.id.flightRecycle);
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



//private  void LoadItems() {
//    SharedPreferences sp = getActivity().getSharedPreferences("UserLog",0);
//    String CustKey =  sp.getString("CustKey",null);
//
//
//       try {
//
//        final JSONObject jsonString;
//        JSONObject values = new JSONObject();
//        values.put("CustKey",CustKey);
//
//
//        jsonString = new JSONObject();
//        jsonString.put("Token", "0001");
//        jsonString.put("call", "GetBookingDetailsByCustKey");
//        jsonString.put("values", values);
//
//
//
//        String URL = this.getString(R.string.Url)+"Select";
//
//
//        StringRequest stringRequest=new StringRequest(Request.Method.POST, URL,
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//
//                        //progress.cancel();
//
//                        Log.e("Jsonnnn",""+response);
//
//
//                        try {
//
//
//                            JSONObject o     = new JSONObject(response);
//
//                            ////// Checking Json Response Is JSON Object Or Not ///////
//                            String data = response;
//                            Object json = new JSONTokener(data).nextValue();
//                            if (json instanceof JSONObject){
//
//                                Log.e("objectttttt",""+json);
//                            }
//
//                            //you have an object
//                            else if (json instanceof JSONArray){
//                                Log.e("Arrayyyyyyy",""+json);
//                            }
//
//                            ///////////////////////////////////////////
//
//                            Log.e("tryyyyyyyyy","in"+o);
//
//
//                            String  code = o.getString("responseCode");
//                            String  message=o.getString("responseMessage");
//
//
//                            Log.e("codeeeeeeeeee","in"+code);
//
//                            if (code.equalsIgnoreCase("0")) {
//
//                                //JSONArray json_array2 = o.getJSONArray("result");
//                                JSONObject jsonObject;
//                                jsonObject= o.getJSONObject("result");
//                                Log.e("Tableeeee","in"+jsonObject);
//                                JSONArray table,table1,table2;
//                                table = jsonObject.getJSONArray("Table");
//                                table1 = jsonObject.getJSONArray("Table1");
//                                int j;
//                                for (j = 0; j < table.length(); j++) {
//                                    JSONObject tableObject = new JSONObject();
//
//                                    tableObject = table.getJSONObject(j);
//
//
//                                    Log.e("obj", "in" + tableObject);
//                                    BookingstatusModel items = new BookingstatusModel(
//
//                                            tableObject.getString("PassengerName"),
//                                            tableObject.getString("FromAirportCode"),
//                                            tableObject.getString("ToAirportCode"),
//                                            tableObject.getString("TravelDate"),
//                                            tableObject.getString("Timing"),
//                                            tableObject.getString("Amount"),
//                                            tableObject.getString("BookingStatusName"),
//                                            tableObject.getString("FlightBookingKey"),
//                                            tableObject.getString("BookingStatusKey"));
//
//                                    item_list.add(items);
//
//                                }
//
////                                for (j = 0; j < table.length(); j++) {
////                                    JSONObject tableObject = new JSONObject();
////
////                                    tableObject = table1.getJSONObject(j);
////
////
////                                    Log.e("obj", "in" + tableObject);
////                                    TrainBookingModel items = new TrainBookingModel(
////
////                                            tableObject.getString("PassengerName"),
////                                            tableObject.getString("FromStationCode"),
////                                            tableObject.getString("ToStationCode"),
////                                            tableObject.getString("TravelDate"),
////                                            tableObject.getString("Amount"),
////                                            tableObject.getString("BookingStatusKey"),
////                                            tableObject.getString("BookingStatusName"));
////
////                                    train_list.add(items);
////                                }
//
//
//                                Log.e("newwwwwww","in"+item_list);
//                                adapter_ = new BookingStatusAdapter_(getContext(), item_list);
//                                recyclerView.setAdapter(adapter_);
//
////                                    table1 = jsonObject.getJSONArray("Table1");
////                                    Log.e("Tableeeee","inside table     "+table);
////                                    Log.e("Tableeeee","inside table1     "+table1);
////
////                                    JSONObject tableObject1;
////
////                                    adapter_ = new BookingStatusAdapter_(Bookingstatus.this, item_list);
////                                    recyclerView.setAdapter(adapter_);
//
//                            }
//
//
//
//
////                            else {
////                                Toast.makeText(getContext(),message,Toast.LENGTH_LONG).show();
////                            }
//
//
//
//
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//                    }
//
//
//
//                },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//
//                        if (error instanceof TimeoutError || error instanceof NoConnectionError) {
//                            Utility.ShowCustomToast(" No Network Connection",getContext());
//                        } else if (error instanceof AuthFailureError) {
//                            Utility.ShowCustomToast("Authentication Failed",getContext());
//                        } else if (error instanceof ServerError) {
//
//                            Utility.ShowCustomToast("Server Error Occurred",getContext());
//                        } else if (error instanceof NetworkError) {
//
//                            Utility.ShowCustomToast("Some Network Error Occurred",getContext());
//                        } else if (error instanceof ParseError) {
//
//                            Utility.ShowCustomToast("Some Error Occurred",getContext());
//                        }
//
//                    }
//                })
//
//
//        {
//
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//                Map<String, String> param = new HashMap<String, String>();
//                param.put("jsonString",jsonString.toString() );
//                Log.e("paramssss",""+param);
//                return param;
//            }
//
//            @Override
//            public Map<String, String> getHeaders() throws AuthFailureError {
//                Map<String,String> param = new HashMap<String, String>();
//                param.put("Content-Type","application/x-www-form-urlencoded");
//                return param;
//            }
//        }
//                ;
//
//        RequestQueue requestQueue= Volley.newRequestQueue(getContext());
//        requestQueue.add(stringRequest);
//
//
//
//    } catch (Exception e) {
//        // JSONException
//    }
//
//}


private  void getStatus(){


    SharedPreferences sp = getActivity().getSharedPreferences("UserLog",0);
    String CustKey =  sp.getString("CustKey",null);

        apiInterface= ApiClient.getClient().create(ApiInterface.class);


    apiInterface.GetFlightBookingStatus(Integer.parseInt(CustKey),"F").enqueue(new Callback<GetFlightBookingStatusResponse>() {
        @Override
        public void onResponse(Call<GetFlightBookingStatusResponse> call, retrofit2.Response<GetFlightBookingStatusResponse> response) {

            if (response.isSuccessful() && response.code() == 200) {
                assert response.body() != null;
                if (response.body().getCode().equalsIgnoreCase("0")) {

                    item_list.clear();
                    List<GetFlightBookingStatusResponse.ResultArray> result = response.body().getResponse();



                    for (int i = 0; i < result.size(); i++) {

                        BookingstatusModel items = new BookingstatusModel(

                                result.get(i).getPassengerName(),
                                result.get(i).getFromAirportCode(),
                                result.get(i).getToAirportCode(),
                                result.get(i).getTravelDate(),
                                result.get(i).getAmount(),
                                result.get(i).getBookingStatusName(),
                                result.get(i).getFlightBookingKey(),
                                result.get(i).getBookingStatusKey());

                        item_list.add(items);

                    }


                    adapter_ = new BookingStatusAdapter_(getContext(), item_list);
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
        public void onFailure(Call<GetFlightBookingStatusResponse> call, Throwable t) {

        }
    });



}




}
