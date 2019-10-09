
package com.example.dagger2example.model.history;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TripPackage {

    @SerializedName("tripPackageId")
    @Expose
    private String tripPackageId;
    @SerializedName("ownerUserId")
    @Expose
    private String ownerUserId;
    @SerializedName("listPickUpPoint")
    @Expose
    private List<ListPickUpPoint> listPickUpPoint = null;
    @SerializedName("tripPackageStatus")
    @Expose
    private Integer tripPackageStatus;
    @SerializedName("tripPackageType")
    @Expose
    private Integer tripPackageType;
    @SerializedName("tripPackageName")
    @Expose
    private String tripPackageName;
    @SerializedName("createdDate")
    @Expose
    private Integer createdDate;
    @SerializedName("censoredDate")
    @Expose
    private Integer censoredDate;
    @SerializedName("overTime")
    @Expose
    private Integer overTime;
    @SerializedName("startDate")
    @Expose
    private Integer startDate;
    @SerializedName("startType")
    @Expose
    private Integer startType;
    @SerializedName("endDate")
    @Expose
    private Integer endDate;
    @SerializedName("vehicleTypeId")
    @Expose
    private String vehicleTypeId;
    @SerializedName("isRepeat")
    @Expose
    private Boolean isRepeat;
    @SerializedName("numberOfSeats")
    @Expose
    private Integer numberOfSeats;
    @SerializedName("numberOfVehicles")
    @Expose
    private Integer numberOfVehicles;
    @SerializedName("numberOfTripPerWeek")
    @Expose
    private Integer numberOfTripPerWeek;
    @SerializedName("rate")
    @Expose
    private Double rate;
    @SerializedName("tripPackageDescription")
    @Expose
    private String tripPackageDescription;
    @SerializedName("schedule")
    @Expose
    private List<Object> schedule = null;
    @SerializedName("estimatedDistance")
    @Expose
    private Double estimatedDistance;
    @SerializedName("estimatedDuration")
    @Expose
    private Double estimatedDuration;
    @SerializedName("distance")
    @Expose
    private Double distance;
    @SerializedName("estimatedPrice")
    @Expose
    private Double estimatedPrice;
    @SerializedName("price")
    @Expose
    private Double price;
    @SerializedName("routeType")
    @Expose
    private Integer routeType;
    @SerializedName("paidAmount")
    @Expose
    private Integer paidAmount;
    @SerializedName("listBidPackage")
    @Expose
    private List<Object> listBidPackage = null;
    @SerializedName("listVehicleId")
    @Expose
    private List<Object> listVehicleId = null;
    @SerializedName("listDriverId")
    @Expose
    private List<String> listDriverId = null;
    @SerializedName("listCompanyId")
    @Expose
    private List<Object> listCompanyId = null;
    @SerializedName("ownerPhoneNumber")
    @Expose
    private String ownerPhoneNumber;
    @SerializedName("ownerFullName")
    @Expose
    private String ownerFullName;
    @SerializedName("isFavoriteTrip")
    @Expose
    private Boolean isFavoriteTrip;
    @SerializedName("ratingTrip")
    @Expose
    private Integer ratingTrip;
    @SerializedName("ownerStateCode")
    @Expose
    private Integer ownerStateCode;

    public String getTripPackageId() {
        return tripPackageId;
    }

    public void setTripPackageId(String tripPackageId) {
        this.tripPackageId = tripPackageId;
    }

    public String getOwnerUserId() {
        return ownerUserId;
    }

    public void setOwnerUserId(String ownerUserId) {
        this.ownerUserId = ownerUserId;
    }

    public List<ListPickUpPoint> getListPickUpPoint() {
        return listPickUpPoint;
    }

    public void setListPickUpPoint(List<ListPickUpPoint> listPickUpPoint) {
        this.listPickUpPoint = listPickUpPoint;
    }

    public Integer getTripPackageStatus() {
        return tripPackageStatus;
    }

    public void setTripPackageStatus(Integer tripPackageStatus) {
        this.tripPackageStatus = tripPackageStatus;
    }

    public Integer getTripPackageType() {
        return tripPackageType;
    }

    public void setTripPackageType(Integer tripPackageType) {
        this.tripPackageType = tripPackageType;
    }

    public String getTripPackageName() {
        return tripPackageName;
    }

    public void setTripPackageName(String tripPackageName) {
        this.tripPackageName = tripPackageName;
    }

    public Integer getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Integer createdDate) {
        this.createdDate = createdDate;
    }

    public Integer getCensoredDate() {
        return censoredDate;
    }

    public void setCensoredDate(Integer censoredDate) {
        this.censoredDate = censoredDate;
    }

    public Integer getOverTime() {
        return overTime;
    }

    public void setOverTime(Integer overTime) {
        this.overTime = overTime;
    }

    public Integer getStartDate() {
        return startDate;
    }

    public void setStartDate(Integer startDate) {
        this.startDate = startDate;
    }

    public Integer getStartType() {
        return startType;
    }

    public void setStartType(Integer startType) {
        this.startType = startType;
    }

    public Integer getEndDate() {
        return endDate;
    }

    public void setEndDate(Integer endDate) {
        this.endDate = endDate;
    }

    public String getVehicleTypeId() {
        return vehicleTypeId;
    }

    public void setVehicleTypeId(String vehicleTypeId) {
        this.vehicleTypeId = vehicleTypeId;
    }

    public Boolean getIsRepeat() {
        return isRepeat;
    }

    public void setIsRepeat(Boolean isRepeat) {
        this.isRepeat = isRepeat;
    }

    public Integer getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(Integer numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    public Integer getNumberOfVehicles() {
        return numberOfVehicles;
    }

    public void setNumberOfVehicles(Integer numberOfVehicles) {
        this.numberOfVehicles = numberOfVehicles;
    }

    public Integer getNumberOfTripPerWeek() {
        return numberOfTripPerWeek;
    }

    public void setNumberOfTripPerWeek(Integer numberOfTripPerWeek) {
        this.numberOfTripPerWeek = numberOfTripPerWeek;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public String getTripPackageDescription() {
        return tripPackageDescription;
    }

    public void setTripPackageDescription(String tripPackageDescription) {
        this.tripPackageDescription = tripPackageDescription;
    }

    public List<Object> getSchedule() {
        return schedule;
    }

    public void setSchedule(List<Object> schedule) {
        this.schedule = schedule;
    }

    public Double getEstimatedDistance() {
        return estimatedDistance;
    }

    public void setEstimatedDistance(Double estimatedDistance) {
        this.estimatedDistance = estimatedDistance;
    }

    public Double getEstimatedDuration() {
        return estimatedDuration;
    }

    public void setEstimatedDuration(Double estimatedDuration) {
        this.estimatedDuration = estimatedDuration;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    public Double getEstimatedPrice() {
        return estimatedPrice;
    }

    public void setEstimatedPrice(Double estimatedPrice) {
        this.estimatedPrice = estimatedPrice;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getRouteType() {
        return routeType;
    }

    public void setRouteType(Integer routeType) {
        this.routeType = routeType;
    }

    public Integer getPaidAmount() {
        return paidAmount;
    }

    public void setPaidAmount(Integer paidAmount) {
        this.paidAmount = paidAmount;
    }

    public List<Object> getListBidPackage() {
        return listBidPackage;
    }

    public void setListBidPackage(List<Object> listBidPackage) {
        this.listBidPackage = listBidPackage;
    }

    public List<Object> getListVehicleId() {
        return listVehicleId;
    }

    public void setListVehicleId(List<Object> listVehicleId) {
        this.listVehicleId = listVehicleId;
    }

    public List<String> getListDriverId() {
        return listDriverId;
    }

    public void setListDriverId(List<String> listDriverId) {
        this.listDriverId = listDriverId;
    }

    public List<Object> getListCompanyId() {
        return listCompanyId;
    }

    public void setListCompanyId(List<Object> listCompanyId) {
        this.listCompanyId = listCompanyId;
    }

    public String getOwnerPhoneNumber() {
        return ownerPhoneNumber;
    }

    public void setOwnerPhoneNumber(String ownerPhoneNumber) {
        this.ownerPhoneNumber = ownerPhoneNumber;
    }

    public String getOwnerFullName() {
        return ownerFullName;
    }

    public void setOwnerFullName(String ownerFullName) {
        this.ownerFullName = ownerFullName;
    }

    public Boolean getIsFavoriteTrip() {
        return isFavoriteTrip;
    }

    public void setIsFavoriteTrip(Boolean isFavoriteTrip) {
        this.isFavoriteTrip = isFavoriteTrip;
    }

    public Integer getRatingTrip() {
        return ratingTrip;
    }

    public void setRatingTrip(Integer ratingTrip) {
        this.ratingTrip = ratingTrip;
    }

    public Integer getOwnerStateCode() {
        return ownerStateCode;
    }

    public void setOwnerStateCode(Integer ownerStateCode) {
        this.ownerStateCode = ownerStateCode;
    }

}
