package com.example.dagger2example.model;

public class NewTripEvent {
    private String tripId;

    public NewTripEvent(String tripId) {
        this.tripId = tripId;
    }

    public String getTripId() {
        return tripId;
    }

    public void setTripId(String tripId) {
        this.tripId = tripId;
    }
}
