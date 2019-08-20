package com.publicmart.android.RetrofitUtils.RetrofitResponseClasses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.publicmart.android.Utils.CONSTANTS;

import java.util.List;

public class GetTrainBookingStatusResponse {

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

        @SerializedName(CONSTANTS.TrainBookingKey)
        @Expose
        private String TrainBookingKey;

        @SerializedName(CONSTANTS.PassengerName)
        @Expose
        private String PassengerName;


        @SerializedName(CONSTANTS.FromStationCode)
        @Expose
        private String FromStationCode;


        @SerializedName(CONSTANTS.ToStationCode)
        @Expose
        private String ToStationCode;


        @SerializedName(CONSTANTS.TravelDate)
        @Expose
        private String TravelDate;

        @SerializedName(CONSTANTS.Amount)
        @Expose
        private String Amount;

        @SerializedName(CONSTANTS.BookingStatusKey)
        @Expose
        private String BookingStatusKey;

        @SerializedName(CONSTANTS.BookingStatusName)
        @Expose
        private String BookingStatusName;


        public String getTrainBookingKey() {
            return TrainBookingKey;
        }

        public String getPassengerName() {
            return PassengerName;
        }

        public String getFromStationCode() {
            return FromStationCode;
        }

        public String getToStationCode() {
            return ToStationCode;
        }

        public String getTravelDate() {
            return TravelDate;
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
