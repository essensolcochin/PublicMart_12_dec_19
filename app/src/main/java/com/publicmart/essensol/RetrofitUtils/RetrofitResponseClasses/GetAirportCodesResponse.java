package com.publicmart.essensol.RetrofitUtils.RetrofitResponseClasses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.publicmart.essensol.Utils.CONSTANTS;

import java.util.List;

public class GetAirportCodesResponse {

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

        @SerializedName(CONSTANTS.AirportKey)
        @Expose
        private Integer AirportKey;

        @SerializedName(CONSTANTS.ShortCode)
        @Expose
        private String ShortCode;

        @SerializedName(CONSTANTS.AirportName)
        @Expose
        private String AirportName;

        public Integer getAirportKey() {
            return AirportKey;
        }

        public String getShortCode() {
            return ShortCode;
        }

        public String getAirportName() {
            return AirportName;
        }
    }
}
