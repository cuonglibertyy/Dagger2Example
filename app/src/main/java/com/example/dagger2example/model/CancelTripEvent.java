package com.example.dagger2example.model;

public class CancelTripEvent {
    private String tripCode;

    public CancelTripEvent(String tripCode) {
        this.tripCode = tripCode;
    }

    public String getTripCode() {
        return tripCode;
    }

    public void setTripCode(String tripCode) {
        this.tripCode = tripCode;
    }
}
