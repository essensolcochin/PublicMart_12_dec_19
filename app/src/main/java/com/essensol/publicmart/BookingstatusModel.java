package com.essensol.publicmart;

public class BookingstatusModel {

    String PassengerName,FromAirportCode,ToAirportCode,TravelDate,Timing,Amount,BookingStatusName,FlightBookingKey,BookingStatusKey;

    public BookingstatusModel(String passengerName, String fromAirportCode, String toAirportCode, String travelDate, String timing, String amount, String bookingStatusName, String flightBookingKey, String bookingStatusKey) {
        PassengerName = passengerName;
        FromAirportCode = fromAirportCode;
        ToAirportCode = toAirportCode;
        TravelDate = travelDate;
        Timing = timing;
        Amount = amount;
        BookingStatusName = bookingStatusName;
        FlightBookingKey = flightBookingKey;
        BookingStatusKey = bookingStatusKey;
    }

    public String getPassengerName() {
        return PassengerName;
    }

    public void setPassengerName(String passengerName) {
        PassengerName = passengerName;
    }

    public String getFromAirportCode() {
        return FromAirportCode;
    }

    public void setFromAirportCode(String fromAirportCode) {
        FromAirportCode = fromAirportCode;
    }

    public String getToAirportCode() {
        return ToAirportCode;
    }

    public void setToAirportCode(String toAirportCode) {
        ToAirportCode = toAirportCode;
    }

    public String getTravelDate() {
        return TravelDate;
    }

    public void setTravelDate(String travelDate) {
        TravelDate = travelDate;
    }

    public String getTiming() {
        return Timing;
    }

    public void setTiming(String timing) {
        Timing = timing;
    }

    public String getAmount() {
        return Amount;
    }

    public void setAmount(String amount) {
        Amount = amount;
    }

    public String getBookingStatusName() {
        return BookingStatusName;
    }

    public void setBookingStatusName(String bookingStatusName) {
        BookingStatusName = bookingStatusName;
    }

    public String getFlightBookingKey() {
        return FlightBookingKey;
    }

    public void setFlightBookingKey(String flightBookingKey) {
        FlightBookingKey = flightBookingKey;
    }

    public String getBookingStatusKey() {
        return BookingStatusKey;
    }

    public void setBookingStatusKey(String bookingStatusKey) {
        BookingStatusKey = bookingStatusKey;
    }


}
