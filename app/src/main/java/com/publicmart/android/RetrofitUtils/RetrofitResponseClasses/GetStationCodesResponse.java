package com.publicmart.android.RetrofitUtils.RetrofitResponseClasses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.publicmart.android.Utils.CONSTANTS;

import java.util.List;

public class GetStationCodesResponse {

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

        @SerializedName(CONSTANTS.StationKey)
        @Expose
        private Integer StationKey;

        @SerializedName(CONSTANTS.ShortCode)
        @Expose
        private String ShortCode;

        @SerializedName(CONSTANTS.StationName)
        @Expose
        private String StationName;

        public Integer getStationKey() {
            return StationKey;
        }

        public String getShortCode() {
            return ShortCode;
        }

        public String getStationName() {
            return StationName;
        }
    }
}
