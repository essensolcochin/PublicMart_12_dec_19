package com.publicmart.essensol.RetrofitUtils.RetrofitResponseClasses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.publicmart.essensol.Utils.CONSTANTS;

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

        @SerializedName("Type")
        @Expose
        private String Type;

        @SerializedName(CONSTANTS.PassengerName)
        @Expose
        private String PassengerName;


        @SerializedName(CONSTANTS.TravelDate)
        @Expose
        private String TravelDate;


        @SerializedName("Source")
        @Expose
        private String Source;


        @SerializedName("Destination")
        @Expose
        private String Destination;

        @SerializedName(CONSTANTS.Amount)
        @Expose
        private String Amount;

        @SerializedName("ContactEmail")
        @Expose
        private String ContactEmail;

        @SerializedName("ContactNo")
        @Expose
        private String ContactNo;

        @SerializedName("BookingStatusName")
        @Expose
        private String BookingStatusName;


        public String getType() {
            return Type;
        }

        public void setType(String type) {
            Type = type;
        }

        public String getPassengerName() {
            return PassengerName;
        }

        public void setPassengerName(String passengerName) {
            PassengerName = passengerName;
        }

        public String getTravelDate() {
            return TravelDate;
        }

        public void setTravelDate(String travelDate) {
            TravelDate = travelDate;
        }

        public String getSource() {
            return Source;
        }

        public void setSource(String source) {
            Source = source;
        }

        public String getDestination() {
            return Destination;
        }

        public void setDestination(String destination) {
            Destination = destination;
        }

        public String getAmount() {
            return Amount;
        }

        public void setAmount(String amount) {
            Amount = amount;
        }

        public String getContactEmail() {
            return ContactEmail;
        }

        public void setContactEmail(String contactEmail) {
            ContactEmail = contactEmail;
        }

        public String getContactNo() {
            return ContactNo;
        }

        public void setContactNo(String contactNo) {
            ContactNo = contactNo;
        }

        public String getBookingStatusName() {
            return BookingStatusName;
        }

        public void setBookingStatusName(String bookingStatusName) {
            BookingStatusName = bookingStatusName;
        }
    }
}
