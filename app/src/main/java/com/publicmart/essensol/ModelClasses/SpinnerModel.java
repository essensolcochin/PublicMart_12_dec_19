package com.publicmart.essensol.ModelClasses;

public class SpinnerModel {

    String StateName;
    Integer StateCode;

    public SpinnerModel() {
    }

    public SpinnerModel(Integer stateCode, String stateName ) {
        StateName = stateName;
        StateCode = stateCode;
    }


    public String getStateName() {
        return StateName;
    }

    public void setStateName(String stateName) {
        StateName = stateName;
    }

    public Integer getStateCode() {
        return StateCode;
    }

    public void setStateCode(Integer stateCode) {
        StateCode = stateCode;
    }

    @Override
    public String toString() {
        return StateName;
    }
}
