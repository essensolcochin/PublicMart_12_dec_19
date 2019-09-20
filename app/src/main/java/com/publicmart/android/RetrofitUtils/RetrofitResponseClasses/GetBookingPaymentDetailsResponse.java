package com.publicmart.android.RetrofitUtils.RetrofitResponseClasses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.publicmart.android.Utils.CONSTANTS;

import java.util.List;

public class GetBookingPaymentDetailsResponse {

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

        @SerializedName(CONSTANTS.PassengerName)
        @Expose
        private String PassengerName;

        @SerializedName(CONSTANTS.ContactEmail)
        @Expose
        private String ContactEmail;

        @SerializedName(CONSTANTS.ContactNo)
        @Expose
        private String ContactNo;

        @SerializedName(CONSTANTS.Amount)
        @Expose
        private String Amount;

        @SerializedName(CONSTANTS.BookingKey)
        @Expose
        private String BookingKey;

        @SerializedName(CONSTANTS.Type)
        @Expose
        private String Type;

        public String getPassengerName() {
            return PassengerName;
        }

        public String getContactEmail() {
            return ContactEmail;
        }

        public String getContactNo() {
            return ContactNo;
        }

        public String getAmount() {
            return Amount;
        }

        public String getBookingKey() {
            return BookingKey;
        }

        public String getType() {
            return Type;
        }
    }
}
