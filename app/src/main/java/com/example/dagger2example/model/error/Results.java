
package com.example.dagger2example.model.error;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.dagger2example.untils.ErrorHandle;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Results extends ErrorHandle implements Parcelable {

    @SerializedName("error")
    @Expose
    private Error_ error;

    @SerializedName("message")
    @Expose
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    protected Results(Parcel in) {
    }

    public static final Creator<Results> CREATOR = new Creator<Results>() {
        @Override
        public Results createFromParcel(Parcel in) {
            return new Results(in);
        }

        @Override
        public Results[] newArray(int size) {
            return new Results[size];
        }
    };

    public Error_ getError() {
        return error;
    }

    public void setError(Error_ error) {
        this.error = error;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }
}
