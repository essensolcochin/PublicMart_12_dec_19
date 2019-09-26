package com.publicmart.essensol.ModelClasses;

public class TrainBookingModel {

    String TrainBookingKey,PassengerName,FromStationCode,ToStationCode,TravelDate,Amount,BookingStatusKey,BookingStatusName;



    public TrainBookingModel(String trainBookingKey,String passengerName, String fromStationCode, String toStationCode, String travelDate, String amount, String bookingStatusKey, String bookingStatusName) {
        TrainBookingKey = trainBookingKey;
        PassengerName = passengerName;
        FromStationCode = fromStationCode;
        ToStationCode = toStationCode;
        TravelDate = travelDate;
        Amount = amount;
        BookingStatusKey = bookingStatusKey;
        BookingStatusName = bookingStatusName;
    }


    public String getTrainBookingKey() {
        return TrainBookingKey;
    }

    public void setTrainBookingKey(String trainBookingKey) {
        TrainBookingKey = trainBookingKey;
    }

    public String getPassengerName() {
        return PassengerName;
    }

    public void setPassengerName(String passengerName) {
        PassengerName = passengerName;
    }

    public String getFromStationCode() {
        return FromStationCode;
    }

    public void setFromStationCode(String fromStationCode) {
        FromStationCode = fromStationCode;
    }

    public String getToStationCode() {
        return ToStationCode;
    }

    public void setToStationCode(String toStationCode) {
        ToStationCode = toStationCode;
    }

    public String getTravelDate() {
        return TravelDate;
    }

    public void setTravelDate(String travelDate) {
        TravelDate = travelDate;
    }

    public String getAmount() {
        return Amount;
    }

    public void setAmount(String amount) {
        Amount = amount;
    }

    public String getBookingStatusKey() {
        return BookingStatusKey;
    }

    public void setBookingStatusKey(String bookingStatusKey) {
        BookingStatusKey = bookingStatusKey;
    }

    public String getBookingStatusName() {
        return BookingStatusName;
    }

    public void setBookingStatusName(String bookingStatusName) {
        BookingStatusName = bookingStatusName;
    }
}
