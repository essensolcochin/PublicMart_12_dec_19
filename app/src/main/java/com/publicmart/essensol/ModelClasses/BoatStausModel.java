package com.publicmart.essensol.ModelClasses;

public class BoatStausModel {
  private   String Type,PassengerName,TravelDate,Source,Destination,Amount,ContactEmail,ContactNo,BookingStatusName;

    public BoatStausModel(String type, String passengerName, String travelDate, String source, String destination, String amount, String contactEmail, String contactNo, String bookingStatusName) {
        Type = type;
        PassengerName = passengerName;
        TravelDate = travelDate;
        Source = source;
        Destination = destination;
        Amount = amount;
        ContactEmail = contactEmail;
        ContactNo = contactNo;
        BookingStatusName = bookingStatusName;
    }

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
