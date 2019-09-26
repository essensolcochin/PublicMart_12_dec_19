package com.publicmart.essensol.RetrofitUtils.RetrofitResponseClasses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.publicmart.essensol.Utils.CONSTANTS;

import java.util.List;

public class HomeScreenResponse {

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

        @SerializedName(CONSTANTS.AdvImageKey)
        @Expose
        private String AdvImageKey;

        @SerializedName(CONSTANTS.ImageDesc)
        @Expose
        private String ImageDesc;

        @SerializedName(CONSTANTS.ImagePath)
        @Expose
        private String ImagePath;

        public String getAdvImageKey() {
            return AdvImageKey;
        }

        public String getImageDesc() {
            return ImageDesc;
        }

        public String getImagePath() {
            return ImagePath;
        }
    }
}
