
package com.example.dagger2example.model.history;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ListPickUpPoint {

    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("longitude")
    @Expose
    private Double longitude;
    @SerializedName("latitude")
    @Expose
    private Double latitude;
    @SerializedName("order")
    @Expose
    private String order;
    @SerializedName("type")
    @Expose
    private Integer type;

    public ListPickUpPoint(String address, Double longitude, Double latitude, String order, Integer type) {
        this.address = address;
        this.longitude = longitude;
        this.latitude = latitude;
        this.order = order;
        this.type = type;
    }


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

}
