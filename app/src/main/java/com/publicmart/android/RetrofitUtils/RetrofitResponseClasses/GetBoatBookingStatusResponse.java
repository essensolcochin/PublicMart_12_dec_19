package com.publicmart.android.RetrofitUtils.RetrofitResponseClasses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.publicmart.android.Utils.CONSTANTS;

import java.util.List;

public class GetBoatBookingStatusResponse {

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

        @SerializedName(CONSTANTS.HBBookingKey)
        @Expose
        private String HBBookingKey;

        @SerializedName(CONSTANTS.PassengerName)
        @Expose
        private String PassengerName;


        @SerializedName(CONSTANTS.TravelDate)
        @Expose
        private String TravelDate;


        @SerializedName(CONSTANTS.GuestNos)
        @Expose
        private String GuestNos;


        @SerializedName(CONSTANTS.CruiseType)
        @Expose
        private String CruiseType;

        @SerializedName(CONSTANTS.Amount)
        @Expose
        private String Amount;

        @SerializedName(CONSTANTS.BookingStatusKey)
        @Expose
        private String BookingStatusKey;

        @SerializedName(CONSTANTS.BookingStatusName)
        @Expose
        private String BookingStatusName;


        public String getHBBookingKey() {
            return HBBookingKey;
        }

        public String getPassengerName() {
            return PassengerName;
        }

        public String getTravelDate() {
            return TravelDate;
        }

        public String getGuestNos() {
            return GuestNos;
        }

        public String getCruiseType() {
            return CruiseType;
        }

        public String getAmount() {
            return Amount;
        }

        public String getBookingStatusKey() {
            return BookingStatusKey;
        }

        public String getBookingStatusName() {
            return BookingStatusName;
        }
    }
}
