package com.publicmart.android.RetrofitUtils;



import com.publicmart.android.RetrofitUtils.RetrofitResponseClasses.CheckUsernameResponse;
import com.publicmart.android.RetrofitUtils.RetrofitResponseClasses.GetAirportCodesResponse;
import com.publicmart.android.RetrofitUtils.RetrofitResponseClasses.GetProductCategoryResponse;
import com.publicmart.android.RetrofitUtils.RetrofitResponseClasses.GetProductDetailsResponse;
import com.publicmart.android.RetrofitUtils.RetrofitResponseClasses.GetProductsByCategoryResponse;
import com.publicmart.android.RetrofitUtils.RetrofitResponseClasses.GetStationCodesResponse;
import com.publicmart.android.RetrofitUtils.RetrofitResponseClasses.HomeScreenResponse;
import com.publicmart.android.RetrofitUtils.RetrofitResponseClasses.LoginResponse;
import com.publicmart.android.RetrofitUtils.RetrofitResponseClasses.OrderStatusResponse;
import com.publicmart.android.RetrofitUtils.RetrofitResponseClasses.RegisterResponse;
import com.publicmart.android.RetrofitUtils.RetrofitResponseClasses.SaveOrderdetailsResponse;
import com.publicmart.android.RetrofitUtils.RetrofitResponseClasses.SearchResponse;
import com.publicmart.android.Utils.CONSTANTS;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiInterface {

    @POST("CommonApi/CheckLogin")
    @FormUrlEncoded
    Call<LoginResponse> login(@Field(CONSTANTS.UserName) String UserName,
                              @Field(CONSTANTS.UserPwd) String UserPwd,
                              @Field(CONSTANTS.AppToken) String AppToken,
                              @Field(CONSTANTS.Mode) String Mode);


    @POST("CommonApi/GetAdvImagesByType")
    @FormUrlEncoded
    Call<HomeScreenResponse> LoadHome(@Field("Type") String Type);


    @POST("CommonApi/SaveCustomerMaster")
    @FormUrlEncoded
    Call<RegisterResponse> Register(@Field(CONSTANTS.CustKey) String CustKey,
                                    @Field(CONSTANTS.CustomerName) String CustomerName,
                                    @Field(CONSTANTS.MobileNo) String MobileNo,
                                    @Field(CONSTANTS.Email) String Email,
                                    @Field(CONSTANTS.SponsorId) String SponsorId,
                                    @Field(CONSTANTS.MSTypeKey) String MSTypeKey,
                                    @Field(CONSTANTS.UserName) String UserName,
                                    @Field(CONSTANTS.UserPwd) String UserPwd);



    @POST("CommonApi/CheckUsernameExists")
    @FormUrlEncoded
    Call<CheckUsernameResponse> CheckUsername(@Field(CONSTANTS.UserName) String jsonString);



    @POST("ProductsApi/GetActiveProductCategoryList")

    Call<GetProductCategoryResponse> GetProductCategories();


    @POST("ProductsApi/GetActiveProductListByCategoryId")
    @FormUrlEncoded
    Call<GetProductsByCategoryResponse> GetProductCategory(@Field(CONSTANTS.PageNumber) int PageNumber,
                                                           @Field(CONSTANTS.PageSize) int PageSize,
                                                           @Field(CONSTANTS.CategoryKey) String CategoryKey);



    @POST("ProductsApi/GetProductDetailsById")
    @FormUrlEncoded
    Call<GetProductDetailsResponse> GetProductDetails(@Field(CONSTANTS.ProductKey) String ProductKey);



    @POST("ProductsApi/SaveOrderDetails")
    @FormUrlEncoded
    Call<SaveOrderdetailsResponse> SaveOrderDetails(@Field(CONSTANTS.CustKey) String CustKey,
                                                    @Field(CONSTANTS.ProductKey) String ProductKey,
                                                    @Field(CONSTANTS.Qty) String Qty,
                                                    @Field(CONSTANTS.Rate) String Rate,
                                                    @Field(CONSTANTS.IsCredit) String IsCredit,
                                                    @Field(CONSTANTS.OrderStatusKey) String OrderStatusKey,
                                                    @Field(CONSTANTS.Status) String Status,
                                                    @Field(CONSTANTS.CreatedBy) String CreatedBy);




    @POST("ProductsApi/GetOrderDetailsByCustKey")
    @FormUrlEncoded
    Call<OrderStatusResponse> GetOrderStatus(@Field(CONSTANTS.CustKey) String CustKey);


    @POST("ServicesApi/GetActiveAirports")
    Call<GetAirportCodesResponse> GetAirportCodes();


    @POST("ServicesApi/GetActiveStations")
    Call<GetStationCodesResponse> GetStationCodes();


    @POST("ProductsApi/GetActiveProductListForSearch")
    Call<SearchResponse> GetItemstoSearch();



}
