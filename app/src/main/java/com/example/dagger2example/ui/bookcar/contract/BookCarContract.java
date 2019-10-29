package com.example.dagger2example.ui.bookcar.contract;

import com.example.dagger2example.base.BaseContract;
import com.example.dagger2example.model.error.Error;
import com.example.dagger2example.model.historydetail.Results;
import com.example.dagger2example.model.historydetail.User;
import com.example.dagger2example.model.typebike.Result;

import java.util.List;

public interface BookCarContract {

    interface View extends BaseContract.BaseView {


        void getResult(Error error);

        void getTypeBike(List<Result> results);

        void showName(List<com.example.dagger2example.model.geocode.Result> result);
        void showInformation(Results results);
        void showLocationDriver(Results results);

    }

    interface Presenter<T> extends BaseContract.BasePresenter<T> {

        void postTypeBike(String latitudemylocation, String longitudemylocation, String latiudedroffone, String longitudedroffone, String latdropofftwo, String longdropofftwo);

        void PostBookBike(String jsonString);

        //        ,String longitudedroffone,String latdropofftwo,String longdropofftwo,String estimatedDistance,String estimatedDuration,String estimatedPrice,String vehicleTypeLuxury,String vehicleTypeId
        void getMyLocationName(String latlng);

        void getLastStatus();
        void getLocationDriver();
    }

}
