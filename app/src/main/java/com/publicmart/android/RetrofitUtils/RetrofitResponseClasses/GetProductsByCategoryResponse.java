package com.publicmart.android.RetrofitUtils.RetrofitResponseClasses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.publicmart.android.Utils.CONSTANTS;

import java.util.List;

public class GetProductsByCategoryResponse {

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

        @SerializedName(CONSTANTS.NUMBER)
        @Expose
        private String NUMBER;

        @SerializedName(CONSTANTS.ProductKey)
        @Expose
        private String ProductKey;

        @SerializedName(CONSTANTS.BrandName)
        @Expose
        private String BrandName;

        @SerializedName(CONSTANTS.ShortDesc)
        @Expose
        private String ShortDesc;

        @SerializedName(CONSTANTS.MRP)
        @Expose
        private String MRP;

        @SerializedName(CONSTANTS.BV)
        @Expose
        private String BV;

        @SerializedName(CONSTANTS.ImagePath)
        @Expose
        private String ImagePath;

        public String getNUMBER() {
            return NUMBER;
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

        public String getMRP() {
            return MRP;
        }

        public String getBV() {
            return BV;
        }

        public String getImagePath() {
            return ImagePath;
        }
    }
}
