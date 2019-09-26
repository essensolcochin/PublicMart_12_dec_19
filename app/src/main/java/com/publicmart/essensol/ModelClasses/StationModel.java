package com.publicmart.essensol.ModelClasses;

public  class StationModel {

    Integer StationKey;

    String ShortCode,StationName;

    public StationModel(Integer stationKey, String shortCode, String stationName) {
        StationKey = stationKey;
        ShortCode = shortCode;
        StationName = stationName;
    }

    public Integer getStationKey() {
        return StationKey;
    }

    public void setStationKey(Integer stationKey) {
        StationKey = stationKey;
    }

    public String getShortCode() {
        return ShortCode;
    }

    public void setShortCode(String shortCode) {
        ShortCode = shortCode;
    }

    public String getStationName() {
        return StationName;
    }

    public void setStationName(String stationName) {
        StationName = stationName;
    }

    @Override
    public String toString() {
        return StationName;
    }
}
