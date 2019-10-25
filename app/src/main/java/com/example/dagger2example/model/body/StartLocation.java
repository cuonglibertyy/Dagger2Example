package com.example.dagger2example.model.body;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class StartLocation {
    @SerializedName("latitude")
    @Expose
    double latitude;
    @SerializedName("longitude")
    @Expose
    double longitude;

    public StartLocation(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }





    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}
