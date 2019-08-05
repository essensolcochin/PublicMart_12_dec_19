package com.publicmart.android.RetrofitUtils;



import com.publicmart.android.RetrofitUtils.RetrofitResponseClasses.HomeScreenResponse;
import com.publicmart.android.RetrofitUtils.RetrofitResponseClasses.LoginResponse;
import com.publicmart.android.RetrofitUtils.RetrofitResponseClasses.RegisterResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiInterface {

    @POST("Select")
    @FormUrlEncoded
    Call<LoginResponse> login(@Field("jsonString") String jsonString);


    @POST("Select")
    @FormUrlEncoded
    Call<HomeScreenResponse> LoadHome(@Field("jsonString") String jsonString);


    @POST("Select")
    @FormUrlEncoded
    Call<RegisterResponse> Register(@Field("jsonString") String jsonString);


//
//    @POST("Select")
//    @FormUrlEncoded
//    Call<SearchResponse> ProductSearch(@Field("jsonString") String jsonString);


}
