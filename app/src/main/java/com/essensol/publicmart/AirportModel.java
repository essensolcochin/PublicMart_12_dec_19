package com.essensol.publicmart;

public class AirportModel {


    Integer AirportKey;
    String ShortCode;
    String AirportName;

    public AirportModel(Integer airportKey, String shortCode, String airportName) {
        AirportKey = airportKey;
        ShortCode = shortCode;
        AirportName = airportName;
    }

    public Integer getAirportKey() {
        return AirportKey;
    }

    public void setAirportKey(Integer airportKey) {
        AirportKey = airportKey;
    }

    public String getShortCode() {
        return ShortCode;
    }

    public void setShortCode(String shortCode) {
        ShortCode = shortCode;
    }

    public String getAirportName() {
        return AirportName;
    }

    public void setAirportName(String airportName) {
        AirportName = airportName;
    }


    @Override
    public String toString() {
        return AirportName;
    }
}