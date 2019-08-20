package com.publicmart.android.RetrofitUtils.RetrofitResponseClasses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.publicmart.android.Utils.CONSTANTS;

import java.util.List;

public class GetFlightBookingStatusResponse {

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

        @SerializedName(CONSTANTS.FlightBookingKey)
        @Expose
        private String FlightBookingKey;

        @SerializedName(CONSTANTS.PassengerName)
        @Expose
        private String PassengerName;


        @SerializedName(CONSTANTS.FromAirportCode)
        @Expose
        private String FromAirportCode;


        @SerializedName(CONSTANTS.ToAirportCode)
        @Expose
        private String ToAirportCode;


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


        public String getFlightBookingKey() {
            return FlightBookingKey;
        }

        public String getPassengerName() {
            return PassengerName;
        }

        public String getFromAirportCode() {
            return FromAirportCode;
        }

        public String getToAirportCode() {
            return ToAirportCode;
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
