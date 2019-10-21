
package com.example.dagger2example.model.typebike;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Result {

    @SerializedName("vehicleTypeId")
    @Expose
    private Integer vehicleTypeId;
    @SerializedName("estimatedPrice")
    @Expose
    private Double estimatedPrice;
    @SerializedName("estimatedDistance")
    @Expose
    private Integer estimatedDistance;
    @SerializedName("vehicleTypeLuxury")
    @Expose
    private Integer vehicleTypeLuxury;
    @SerializedName("estimatedDuration")
    @Expose
    private Integer estimatedDuration;
    @SerializedName("carTypeName")
    @Expose
    private String carTypeName;

    public Integer getVehicleTypeId() {
        return vehicleTypeId;
    }

    public void setVehicleTypeId(Integer vehicleTypeId) {
        this.vehicleTypeId = vehicleTypeId;
    }

    public Double getEstimatedPrice() {
        return estimatedPrice;
    }

    public void setEstimatedPrice(Double estimatedPrice) {
        this.estimatedPrice = estimatedPrice;
    }

    public Integer getEstimatedDistance() {
        return estimatedDistance;
    }

    public void setEstimatedDistance(Integer estimatedDistance) {
        this.estimatedDistance = estimatedDistance;
    }

    public Integer getVehicleTypeLuxury() {
        return vehicleTypeLuxury;
    }

    public void setVehicleTypeLuxury(Integer vehicleTypeLuxury) {
        this.vehicleTypeLuxury = vehicleTypeLuxury;
    }

    public Integer getEstimatedDuration() {
        return estimatedDuration;
    }

    public void setEstimatedDuration(Integer estimatedDuration) {
        this.estimatedDuration = estimatedDuration;
    }

    public String getCarTypeName() {
        return carTypeName;
    }

    public void setCarTypeName(String carTypeName) {
        this.carTypeName = carTypeName;
    }

}
