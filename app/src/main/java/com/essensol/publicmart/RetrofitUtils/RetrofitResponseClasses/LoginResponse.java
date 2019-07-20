package com.essensol.publicmart.RetrofitUtils.RetrofitResponseClasses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class LoginResponse {

    @SerializedName("responseCode")
    @Expose
    private String code;

    @SerializedName("responseMessage")
    @Expose
    private String message;

    @SerializedName("result")
    @Expose
    private List<LoginJsonArray> loginresponse;

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public List<LoginJsonArray> getLoginresponse() {
        return loginresponse;
    }

    public  class LoginJsonArray{

        @SerializedName("UserKey")
        @Expose
        private String UserKey;

        @SerializedName("CustKey")
        @Expose
        private String CustKey;

        @SerializedName("UserName")
        @Expose
        private String UserName;

        @SerializedName("CustomerName")
        @Expose
        private String CustomerName;

        @SerializedName("CustCode")
        @Expose
        private String CustCode;

        @SerializedName("MemberShip")
        @Expose
        private String MemberShip;

        @SerializedName("Amount")
        @Expose
        private String Amount;

        @SerializedName("MobileNo")
        @Expose
        private String MobileNo;

        @SerializedName("Email")
        @Expose
        private String Email;

        @SerializedName("PaidStatus")
        @Expose
        private String PaidStatus;

        @SerializedName("Profile")
        @Expose
        private String Profile;


        @SerializedName("CartCount")
        @Expose
        private String CartCount;

        public String getUserKey() {
            return UserKey;
        }

        public String getCustKey() {
            return CustKey;
        }

        public String getUserName() {
            return UserName;
        }

        public String getCustomerName() {
            return CustomerName;
        }

        public String getCustCode() {
            return CustCode;
        }

        public String getMemberShip() {
            return MemberShip;
        }

        public String getAmount() {
            return Amount;
        }

        public String getMobileNo() {
            return MobileNo;
        }

        public String getEmail() {
            return Email;
        }

        public String getPaidStatus() {
            return PaidStatus;
        }

        public String getProfile() {
            return Profile;
        }

        public String getCartCount() {
            return CartCount;
        }
    }

}
