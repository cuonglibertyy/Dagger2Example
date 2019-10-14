package com.example.dagger2example.model;

import com.example.dagger2example.model.login.Results;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import lombok.Data;

@Data
public class ErrorParser {
    @SerializedName("code")
    @Expose
    public Integer code;
    @SerializedName("count")
    @Expose
    public Integer count;
    @SerializedName("status")
    @Expose
    public String status;
    @SerializedName("type")
    @Expose
    public String type;
    @SerializedName("results")
    @Expose
    public Results results;
}
