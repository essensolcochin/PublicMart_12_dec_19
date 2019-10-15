package com.publicmart.essensol.RetrofitUtils;



import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    private static Retrofit retrofit = null;

   public static Retrofit getClient() {



        OkHttpClient client = new OkHttpClient.Builder().build();


        retrofit = new Retrofit.Builder()
//                .baseUrl("http://192.168.1.16:1111/api/")
                .baseUrl("http://Service.publicmart.in/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();



        return retrofit;
    }


}