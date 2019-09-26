package com.publicmart.essensol.RetrofitUtils.RetrofitResponseClasses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.publicmart.essensol.Utils.CONSTANTS;

import java.util.List;

public class OrderStatusResponse {

    @SerializedName("responseCode")
    @Expose
    private String code;

    @SerializedName("responseMessage")
    @Expose
    private String message;

    @SerializedName("result")
    @Expose
    private List<ResultArray> response;

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public List<ResultArray> getResponse() {
        return response;
    }

    public class ResultArray {

        @SerializedName(CONSTANTS.OrderKey)
        @Expose
        private String OrderKey;

        @SerializedName(CONSTANTS.ProductKey)
        @Expose
        private String ProductKey;

        @SerializedName(CONSTANTS.BrandName)
        @Expose
        private String BrandName;

        @SerializedName(CONSTANTS.ShortDesc)
        @Expose
        private String ShortDesc;

        @SerializedName(CONSTANTS.Amount)
        @Expose
        private String Amount;

        @SerializedName(CONSTANTS.BV)
        @Expose
        private String BV;

        @SerializedName(CONSTANTS.ImagePath)
        @Expose
        private String ImagePath;

        @SerializedName(CONSTANTS.OrderStatusKey)
        @Expose
        private String OrderStatusKey;

        @SerializedName(CONSTANTS.OrderStatusName)
        @Expose
        private String OrderStatusName;


        public String getOrderKey() {
            return OrderKey;
        }

        public String getProductKey() {
            return ProductKey;
        }

        public String getBrandName() {
            return BrandName;
        }

        public String getShortDesc() {
            return ShortDesc;
        }

        public String getAmount() {
            return Amount;
        }

        public String getBV() {
            return BV;
        }

        public String getImagePath() {
            return ImagePath;
        }

        public String getOrderStatusKey() {
            return OrderStatusKey;
        }

        public String getOrderStatusName() {
            return OrderStatusName;
        }
    }
}
