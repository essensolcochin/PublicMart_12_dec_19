package com.publicmart.essensol.RetrofitUtils.RetrofitResponseClasses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.publicmart.essensol.Utils.CONSTANTS;

import java.util.List;

public class GetProductCategoryResponse {

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

        @SerializedName(CONSTANTS.CategoryKey)
        @Expose
        private String CategoryKey;

        @SerializedName(CONSTANTS.CategoryName)
        @Expose
        private String CategoryName;

        @SerializedName(CONSTANTS.ImagePath)
        @Expose
        private String ImagePath;

        public String getCategoryKey() {
            return CategoryKey;
        }

        public String getCategoryName() {
            return CategoryName;
        }

        public String getImagePath() {
            return ImagePath;
        }
    }
}
