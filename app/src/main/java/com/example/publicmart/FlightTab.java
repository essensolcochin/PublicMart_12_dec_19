package com.example.publicmart;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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


/**
 * A simple {@link Fragment} subclass.
 */
public class FlightTab extends Fragment {

    BookingStatusAdapter_ adapter_;
    List<BookingstatusModel> item_list;
    RecyclerView recyclerView;

    public FlightTab() {
        // Required empty public constructor
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
        LoadItems();

        return RootView;
    }

private  void LoadItems()
{
    SharedPreferences sp = getActivity().getSharedPreferences("UserLog",0);
    String CustKey =  sp.getString("UserKey",null);


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

                                //JSONArray json_array2 = o.getJSONArray("result");
                                JSONObject jsonObject;
                                jsonObject= o.getJSONObject("result");
                                Log.e("Tableeeee","in"+jsonObject);
                                JSONArray table,table1,table2;
                                table = jsonObject.getJSONArray("Table");
                                table1 = jsonObject.getJSONArray("Table1");
                                int j;
                                for (j = 0; j < table.length(); j++) {
                                    JSONObject tableObject = new JSONObject();

                                    tableObject = table.getJSONObject(j);


                                    Log.e("obj", "in" + tableObject);
                                    BookingstatusModel items = new BookingstatusModel(

                                            tableObject.getString("PassengerName"),
                                            tableObject.getString("FromAirportCode"),
                                            tableObject.getString("ToAirportCode"),
                                            tableObject.getString("TravelDate"),
                                            tableObject.getString("Timing"),
                                            tableObject.getString("Amount"),
                                            tableObject.getString("BookingStatusName"),
                                            tableObject.getString("FlightBookingKey"),
                                            tableObject.getString("BookingStatusKey"));

                                    item_list.add(items);

                                }

//                                for (j = 0; j < table.length(); j++) {
//                                    JSONObject tableObject = new JSONObject();
//
//                                    tableObject = table1.getJSONObject(j);
//
//
//                                    Log.e("obj", "in" + tableObject);
//                                    TrainBookingModel items = new TrainBookingModel(
//
//                                            tableObject.getString("PassengerName"),
//                                            tableObject.getString("FromStationCode"),
//                                            tableObject.getString("ToStationCode"),
//                                            tableObject.getString("TravelDate"),
//                                            tableObject.getString("Amount"),
//                                            tableObject.getString("BookingStatusKey"),
//                                            tableObject.getString("BookingStatusName"));
//
//                                    train_list.add(items);
//                                }


                                Log.e("newwwwwww","in"+item_list);
                                adapter_ = new BookingStatusAdapter_(getContext(), item_list);
                                recyclerView.setAdapter(adapter_);

//                                    table1 = jsonObject.getJSONArray("Table1");
//                                    Log.e("Tableeeee","inside table     "+table);
//                                    Log.e("Tableeeee","inside table1     "+table1);
//
//                                    JSONObject tableObject1;
//
//                                    adapter_ = new BookingStatusAdapter_(Bookingstatus.this, item_list);
//                                    recyclerView.setAdapter(adapter_);

                            }




                            else {
                                Toast.makeText(getContext(),message,Toast.LENGTH_LONG).show();
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
                        Toast.makeText(getContext(), "Some Error Occurred ", Toast.LENGTH_LONG).show();

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





}
