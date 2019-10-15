package com.publicmart.essensol.RetrofitUtils;



import com.publicmart.essensol.RetrofitUtils.RetrofitResponseClasses.CheckAccountStatusResponse;
import com.publicmart.essensol.RetrofitUtils.RetrofitResponseClasses.CheckUsernameResponse;
import com.publicmart.essensol.RetrofitUtils.RetrofitResponseClasses.GetAirportCodesResponse;
import com.publicmart.essensol.RetrofitUtils.RetrofitResponseClasses.GetBoatBookingStatusResponse;
import com.publicmart.essensol.RetrofitUtils.RetrofitResponseClasses.GetBookingPaymentDetailsResponse;
import com.publicmart.essensol.RetrofitUtils.RetrofitResponseClasses.GetFlightBookingStatusResponse;
import com.publicmart.essensol.RetrofitUtils.RetrofitResponseClasses.GetProductCategoryResponse;
import com.publicmart.essensol.RetrofitUtils.RetrofitResponseClasses.GetProductDetailsResponse;
import com.publicmart.essensol.RetrofitUtils.RetrofitResponseClasses.GetProductsByCategoryResponse;
import com.publicmart.essensol.RetrofitUtils.RetrofitResponseClasses.GetProfileDetailsResponse;
import com.publicmart.essensol.RetrofitUtils.RetrofitResponseClasses.GetStationCodesResponse;
import com.publicmart.essensol.RetrofitUtils.RetrofitResponseClasses.GetTrainBookingStatusResponse;
import com.publicmart.essensol.RetrofitUtils.RetrofitResponseClasses.HomeScreenResponse;
import com.publicmart.essensol.RetrofitUtils.RetrofitResponseClasses.LoginResponse;
import com.publicmart.essensol.RetrofitUtils.RetrofitResponseClasses.OrderStatusResponse;
import com.publicmart.essensol.RetrofitUtils.RetrofitResponseClasses.RegisterResponse;
import com.publicmart.essensol.RetrofitUtils.RetrofitResponseClasses.SaveFlightBookingResponse;
import com.publicmart.essensol.RetrofitUtils.RetrofitResponseClasses.SaveHouseBoatBookingResponse;
import com.publicmart.essensol.RetrofitUtils.RetrofitResponseClasses.SaveOrderdetailsResponse;
import com.publicmart.essensol.RetrofitUtils.RetrofitResponseClasses.SaveTrainBookingResponse;
import com.publicmart.essensol.RetrofitUtils.RetrofitResponseClasses.SaveUpdateProfileDetailsResponse;
import com.publicmart.essensol.RetrofitUtils.RetrofitResponseClasses.SearchResponse;
import com.publicmart.essensol.RetrofitUtils.RetrofitResponseClasses.UpdatePaymentStatusResponse;
import com.publicmart.essensol.Utils.CONSTANTS;

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



    @POST("ServicesApi/SaveFlightBooking")
    @FormUrlEncoded
    Call<SaveFlightBookingResponse> SaveFlightBooking(@Field(CONSTANTS.CustKey) int CustKey,
                                                      @Field(CONSTANTS.PassengerName) String PassengerName,
                                                      @Field(CONSTANTS.DOB) String DOB,
                                                      @Field(CONSTANTS.FromAirportKey) int FromAirportKey,
                                                      @Field(CONSTANTS.ToAirportKey) int ToAirportKey,
                                                      @Field(CONSTANTS.TravelDate) String TravelDate,
                                                      @Field(CONSTANTS.ContactEmail) String ContactEmail,
                                                      @Field(CONSTANTS.ContactNo) String ContactNo,
                                                      @Field(CONSTANTS.BookingStatusKey) int BookingStatusKey,
                                                      @Field(CONSTANTS.Status) boolean Status,
                                                      @Field(CONSTANTS.CreatedBy) int CreatedBy);


    @POST("ServicesApi/SaveTrainBooking")
    @FormUrlEncoded
    Call<SaveTrainBookingResponse> SaveTrainBooking(@Field(CONSTANTS.CustKey) int CustKey,
                                                    @Field(CONSTANTS.PassengerName) String PassengerName,
                                                    @Field(CONSTANTS.Age) String Age,
                                                    @Field(CONSTANTS.FromStationKey) int FromAirportKey,
                                                    @Field(CONSTANTS.ToStationKey) int ToAirportKey,
                                                    @Field(CONSTANTS.TravelDate) String TravelDate,
                                                    @Field(CONSTANTS.ContactEmail) String ContactEmail,
                                                    @Field(CONSTANTS.ContactNo) String ContactNo,
                                                    @Field(CONSTANTS.BookingStatusKey) int BookingStatusKey,
                                                    @Field(CONSTANTS.Status) boolean Status,
                                                    @Field(CONSTANTS.CreatedBy) int CreatedBy);
//    CustKey:5
//    PassengerName:xyz898
//    TravelDate:05-11-2019
//    GuestNos:5
//    CruiseType:D
//    ContactEmail:xyz@test.com
//    ContactNo:8075249241
//    BookingStatusKey:1
//    Status:true
//    CreatedBy:1



    @POST("ServicesApi/SaveHouseboatBooking")
    @FormUrlEncoded
    Call<SaveHouseBoatBookingResponse> SaveHouseBoatBooking(@Field(CONSTANTS.CustKey) String CustKey,
                                                            @Field(CONSTANTS.PassengerName) String PassengerName,
                                                            @Field(CONSTANTS.TravelDate) String TravelDate,
                                                            @Field(CONSTANTS.CruiseType) String CruiseType,
                                                            @Field(CONSTANTS.ContactEmail) String ContactEmail,
                                                            @Field(CONSTANTS.GuestNos) String GuestNos,
                                                            @Field(CONSTANTS.ContactNo) String ContactNo,
                                                            @Field(CONSTANTS.BookingStatusKey) int BookingStatusKey,
                                                            @Field(CONSTANTS.Status) boolean Status,
                                                            @Field(CONSTANTS.CreatedBy) String CreatedBy);



    @POST("ServicesApi/GetBookingDetailsByCustKeyAndType")
    @FormUrlEncoded
    Call<GetFlightBookingStatusResponse> GetFlightBookingStatus(@Field(CONSTANTS.CustKey) int CustKey,@Field(CONSTANTS.Type) String Type);


    @POST("ServicesApi/GetBookingDetailsByCustKeyAndType")
    @FormUrlEncoded
    Call<GetTrainBookingStatusResponse> GetTrainBookingStatus(@Field(CONSTANTS.CustKey) int CustKey,@Field(CONSTANTS.Type) String Type);


    @POST("CommonApi/GetBookingDetailsByCustomerId")
    @FormUrlEncoded
    Call<GetBoatBookingStatusResponse> GetBoatBookingStatus(@Field(CONSTANTS.CustKey) int CustKey);




    @POST("ProductsApi/GetOrderDetailsByCustKey")
    @FormUrlEncoded
    Call<OrderStatusResponse> GetOrderStatus(@Field(CONSTANTS.CustKey) String CustKey);


    @POST("ServicesApi/GetActiveAirports")
    Call<GetAirportCodesResponse> GetAirportCodes();


    @POST("ServicesApi/GetActiveStations")
    Call<GetStationCodesResponse> GetStationCodes();


    @POST("ProductsApi/GetActiveProductListForSearch")
    Call<SearchResponse> GetItemstoSearch();


    @POST("CommonApi/CheckAccountStatus")
    @FormUrlEncoded
    Call<CheckAccountStatusResponse> CheckAccountStatus(@Field(CONSTANTS.CustKey) String CustKey);



    @POST("ServicesApi/GetPaymentDetailsByBookingKeyAndType")
    @FormUrlEncoded
    Call<GetBookingPaymentDetailsResponse> GetPaymentDetailsforBookings(@Field(CONSTANTS.BookingKey) String BookingKey,@Field(CONSTANTS.Type) String Type);


    @POST("ServicesApi/UpdateBookingPaymentByType")
    @FormUrlEncoded
    Call<UpdatePaymentStatusResponse> UpdatebookingStatus(@Field(CONSTANTS.BookingKey) String BookingKey,
                                                          @Field(CONSTANTS.Type) String Type,
                                                          @Field(CONSTANTS.CustKey) String CustKey);



    @POST("CommonApi/GetProfileDetailsByCustcode")
    @FormUrlEncoded
    Call<GetProfileDetailsResponse> GetProfileDetails(@Field("CustCode") String CustCode);

    @POST("CommonApi/SaveUpdateProfileDetails")
    @FormUrlEncoded
    Call<SaveUpdateProfileDetailsResponse> SaveUpdateProfileDetails(@Field("Address") String Address,
                                                                    @Field("DOB") String DOB,
                                                                    @Field("Email") String Email,
                                                                    @Field("CreatedBy") String CreatedBy,
                                                                    @Field("CustCode") String CustCode,
                                                                    @Field("CustDetKey") int CustDetKey,
                                                                    @Field("CustomerName") String CustomerName,
                                                                    @Field("MobileNo") String MobileNo,
                                                                    @Field("AcntHolderName") String AcntHolderName,
                                                                    @Field("AcntNo") String AcntNo,
                                                                    @Field("BankName") String BankName,
                                                                    @Field("BranchName") String BranchName,
                                                                    @Field("IFSCCode") String IFSCCode);




}
