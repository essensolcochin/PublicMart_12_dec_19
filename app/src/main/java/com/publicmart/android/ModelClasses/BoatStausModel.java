package com.publicmart.android.ModelClasses;

public class BoatStausModel {
  private   String HBBookingKey,PassengerName,TravelDate,GuestNos,CruiseType,Amount,BookingStatusKey,BookingStatusName;


    public BoatStausModel(String HBBookingKey, String passengerName, String travelDate, String guestNos, String cruiseType, String amount, String bookingStatusKey, String bookingStatusName) {
        this.HBBookingKey = HBBookingKey;
        PassengerName = passengerName;
        TravelDate = travelDate;
        GuestNos = guestNos;
        CruiseType = cruiseType;
        Amount = amount;
        BookingStatusKey = bookingStatusKey;
        BookingStatusName = bookingStatusName;
    }


    public String getHBBookingKey() {
        return HBBookingKey;
    }

    public void setHBBookingKey(String HBBookingKey) {
        this.HBBookingKey = HBBookingKey;
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

    public String getGuestNos() {
        return GuestNos;
    }

    public void setGuestNos(String guestNos) {
        GuestNos = guestNos;
    }

    public String getCruiseType() {
        return CruiseType;
    }

    public void setCruiseType(String cruiseType) {
        CruiseType = cruiseType;
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
