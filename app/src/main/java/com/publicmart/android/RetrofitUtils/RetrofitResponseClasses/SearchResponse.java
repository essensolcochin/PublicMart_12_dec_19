package com.publicmart.android.RetrofitUtils.RetrofitResponseClasses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.publicmart.android.Utils.CONSTANTS;

import java.util.List;

public class SearchResponse {

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

        @SerializedName(CONSTANTS.ProductKey)
        @Expose
        private String ProductKey;

        @SerializedName(CONSTANTS.CategoryKey)
        @Expose
        private String CategoryKey;

        @SerializedName(CONSTANTS.CategoryName)
        @Expose
        private String CategoryName;

        @SerializedName(CONSTANTS.BrandName)
        @Expose
        private String BrandName;

        @SerializedName(CONSTANTS.ShortDesc)
        @Expose
        private String ShortDesc;

        public String getProductKey() {
            return ProductKey;
        }

        public String getCategoryKey() {
            return CategoryKey;
        }

        public String getCategoryName() {
            return CategoryName;
        }

        public String getBrandName() {
            return BrandName;
        }

        public String getShortDesc() {
            return ShortDesc;
        }
    }
}
