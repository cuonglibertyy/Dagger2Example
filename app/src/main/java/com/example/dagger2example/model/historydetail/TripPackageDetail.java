
package com.example.dagger2example.model.historydetail;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TripPackageDetail implements Parcelable {

    @SerializedName("tripPackageId")
    @Expose
    private String tripPackageId;
    @SerializedName("ownerUserId")
    @Expose
    private String ownerUserId;
    @SerializedName("listPickUpPoint")
    @Expose
    private List<ListPickUpPoint_> listPickUpPoint = null;
    @SerializedName("tripPackageStatus")
    @Expose
    private Long tripPackageStatus;
    @SerializedName("tripPackageType")
    @Expose
    private Long tripPackageType;
    @SerializedName("tripPackageName")
    @Expose
    private String tripPackageName;
    @SerializedName("createdDate")
    @Expose
    private Long createdDate;
    @SerializedName("censoredDate")
    @Expose
    private Long censoredDate;
    @SerializedName("overTime")
    @Expose
    private Long overTime;
    @SerializedName("startDate")
    @Expose
    private Long startDate;
    @SerializedName("startType")
    @Expose
    private Long startType;
    @SerializedName("endDate")
    @Expose
    private Long endDate;
    @SerializedName("vehicleTypeId")
    @Expose
    private String vehicleTypeId;
    @SerializedName("isRepeat")
    @Expose
    private Boolean isRepeat;
    @SerializedName("numberOfSeats")
    @Expose
    private Long numberOfSeats;
    @SerializedName("numberOfVehicles")
    @Expose
    private Long numberOfVehicles;
    @SerializedName("numberOfTripPerWeek")
    @Expose
    private Long numberOfTripPerWeek;
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
    private Long routeType;
    @SerializedName("paidAmount")
    @Expose
    private Long paidAmount;
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
    private Long ratingTrip;
    @SerializedName("ownerStateCode")
    @Expose
    private Long ownerStateCode;

    protected TripPackageDetail(Parcel in) {
        this.tripPackageId = in.readString();
        this.ownerUserId = in.readString();
        this.listPickUpPoint = in.createTypedArrayList(ListPickUpPoint_.CREATOR);
        this.tripPackageStatus = in.readLong();
        this.tripPackageType = in.readLong();
        this.tripPackageName = in.readString();
        this.createdDate = in.readLong();
        this.censoredDate = in.readLong();
        this.overTime = in.readLong();
        this.startDate = in.readLong();
        this.startType = in.readLong();
        this.endDate = in.readLong();
        this.vehicleTypeId = in.readString();
        this.numberOfSeats = in.readLong();
        this.numberOfVehicles = in.readLong();
        this.numberOfTripPerWeek = in.readLong();
        this.rate = in.readDouble();
        this.tripPackageDescription = in.readString();
        this.estimatedDistance = in.readDouble();
        this.estimatedDuration = in.readDouble();
        this.distance = in.readDouble();
        this.estimatedPrice = in.readDouble();
        this.price = in.readDouble();
        this.routeType = in.readLong();
        this.paidAmount = in.readLong();
        this.listDriverId = in.createStringArrayList();
        this.ownerPhoneNumber = in.readString();
        this.ownerFullName = in.readString();
        this.ratingTrip = in.readLong();
        this.ownerStateCode = in.readLong();
    }

    public static final Creator<TripPackageDetail> CREATOR = new Creator<TripPackageDetail>() {
        @Override
        public TripPackageDetail createFromParcel(Parcel in) {
            return new TripPackageDetail(in);
        }

        @Override
        public TripPackageDetail[] newArray(int size) {
            return new TripPackageDetail[size];
        }
    };

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

    public List<ListPickUpPoint_> getListPickUpPoint() {
        return listPickUpPoint;
    }

    public void setListPickUpPoint(List<ListPickUpPoint_> listPickUpPoint) {
        this.listPickUpPoint = listPickUpPoint;
    }

    public Long getTripPackageStatus() {
        return tripPackageStatus;
    }

    public void setTripPackageStatus(Long tripPackageStatus) {
        this.tripPackageStatus = tripPackageStatus;
    }

    public Long getTripPackageType() {
        return tripPackageType;
    }

    public void setTripPackageType(Long tripPackageType) {
        this.tripPackageType = tripPackageType;
    }

    public String getTripPackageName() {
        return tripPackageName;
    }

    public void setTripPackageName(String tripPackageName) {
        this.tripPackageName = tripPackageName;
    }

    public Long getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Long createdDate) {
        this.createdDate = createdDate;
    }

    public Long getCensoredDate() {
        return censoredDate;
    }

    public void setCensoredDate(Long censoredDate) {
        this.censoredDate = censoredDate;
    }

    public Long getOverTime() {
        return overTime;
    }

    public void setOverTime(Long overTime) {
        this.overTime = overTime;
    }

    public Long getStartDate() {
        return startDate;
    }

    public void setStartDate(Long startDate) {
        this.startDate = startDate;
    }

    public Long getStartType() {
        return startType;
    }

    public void setStartType(Long startType) {
        this.startType = startType;
    }

    public Long getEndDate() {
        return endDate;
    }

    public void setEndDate(Long endDate) {
        this.endDate = endDate;
    }

    public String getVehicleTypeId() {
        return vehicleTypeId;
    }

    public void setVehicleTypeId(String vehicleTypeId) {
        this.vehicleTypeId = vehicleTypeId;
    }

    public Boolean getRepeat() {
        return isRepeat;
    }

    public void setRepeat(Boolean repeat) {
        isRepeat = repeat;
    }

    public Long getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(Long numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    public Long getNumberOfVehicles() {
        return numberOfVehicles;
    }

    public void setNumberOfVehicles(Long numberOfVehicles) {
        this.numberOfVehicles = numberOfVehicles;
    }

    public Long getNumberOfTripPerWeek() {
        return numberOfTripPerWeek;
    }

    public void setNumberOfTripPerWeek(Long numberOfTripPerWeek) {
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

    public Long getRouteType() {
        return routeType;
    }

    public void setRouteType(Long routeType) {
        this.routeType = routeType;
    }

    public Long getPaidAmount() {
        return paidAmount;
    }

    public void setPaidAmount(Long paidAmount) {
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

    public Boolean getFavoriteTrip() {
        return isFavoriteTrip;
    }

    public void setFavoriteTrip(Boolean favoriteTrip) {
        isFavoriteTrip = favoriteTrip;
    }

    public Long getRatingTrip() {
        return ratingTrip;
    }

    public void setRatingTrip(Long ratingTrip) {
        this.ratingTrip = ratingTrip;
    }

    public Long getOwnerStateCode() {
        return ownerStateCode;
    }

    public void setOwnerStateCode(Long ownerStateCode) {
        this.ownerStateCode = ownerStateCode;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(tripPackageId);
        parcel.writeString(ownerUserId);
        parcel.writeTypedList(listPickUpPoint);
        parcel.writeLong(tripPackageStatus);
        parcel.writeLong(tripPackageType);
        parcel.writeString(tripPackageName);
        parcel.writeLong(createdDate);
        parcel.writeLong(censoredDate);
        parcel.writeLong(overTime);
        parcel.writeLong(startDate);
        parcel.writeLong(startType);
        parcel.writeLong(endDate);
        parcel.writeString(vehicleTypeId);
        parcel.writeLong(numberOfSeats);
        parcel.writeLong(numberOfVehicles);
        parcel.writeLong(numberOfTripPerWeek);
        parcel.writeDouble(rate);
        parcel.writeString(tripPackageDescription);
        parcel.writeDouble(estimatedDistance);
        parcel.writeDouble(estimatedDuration);
        parcel.writeDouble(distance);
        parcel.writeDouble(estimatedPrice);
        parcel.writeDouble(price);
        parcel.writeLong(routeType);
        parcel.writeLong(paidAmount);
        parcel.writeStringList(listDriverId);
        parcel.writeString(ownerPhoneNumber);
        parcel.writeString(ownerFullName);
        parcel.writeLong(ratingTrip);
        parcel.writeLong(ownerStateCode);
    }
}
