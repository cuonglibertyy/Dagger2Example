
package com.example.dagger2example.model.typebike;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Result {

    @SerializedName("vehicleTypeId")
    @Expose
    private Long vehicleTypeId;
    @SerializedName("estimatedPrice")
    @Expose
    private Double estimatedPrice;
    @SerializedName("estimatedDistance")
    @Expose
    private Long estimatedDistance;
    @SerializedName("vehicleTypeLuxury")
    @Expose
    private Long vehicleTypeLuxury;
    @SerializedName("estimatedDuration")
    @Expose
    private Long estimatedDuration;
    @SerializedName("carTypeName")
    @Expose
    private String carTypeName;

    public Long getVehicleTypeId() {
        return vehicleTypeId;
    }

    public void setVehicleTypeId(Long vehicleTypeId) {
        this.vehicleTypeId = vehicleTypeId;
    }

    public Double getEstimatedPrice() {
        return estimatedPrice;
    }

    public void setEstimatedPrice(Double estimatedPrice) {
        this.estimatedPrice = estimatedPrice;
    }

    public Long getEstimatedDistance() {
        return estimatedDistance;
    }

    public void setEstimatedDistance(Long estimatedDistance) {
        this.estimatedDistance = estimatedDistance;
    }

    public Long getVehicleTypeLuxury() {
        return vehicleTypeLuxury;
    }

    public void setVehicleTypeLuxury(Long vehicleTypeLuxury) {
        this.vehicleTypeLuxury = vehicleTypeLuxury;
    }

    public Long getEstimatedDuration() {
        return estimatedDuration;
    }

    public void setEstimatedDuration(Long estimatedDuration) {
        this.estimatedDuration = estimatedDuration;
    }

    public String getCarTypeName() {
        return carTypeName;
    }

    public void setCarTypeName(String carTypeName) {
        this.carTypeName = carTypeName;
    }
}
