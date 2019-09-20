package com.publicmart.android.RetrofitUtils.RetrofitResponseClasses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class UpdatePaymentStatusResponse {

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

        @SerializedName("result")
        @Expose
        private String result;

        @SerializedName("errorcode")
        @Expose
        private String errorcode;

        @SerializedName("msg")
        @Expose
        private String msg;

        public String getResult() {
            return result;
        }

        public String getErrorcode() {
            return errorcode;
        }

        public String getMsg() {
            return msg;
        }
    }
}
