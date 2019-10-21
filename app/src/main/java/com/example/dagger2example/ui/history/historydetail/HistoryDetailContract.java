package com.example.dagger2example.ui.history.historydetail;

import com.example.dagger2example.base.BaseContract;
import com.example.dagger2example.model.historydetail.HistoryDetail;
import com.example.dagger2example.model.historydetail.Results;
import com.example.dagger2example.model.historydetail.TripPackageDetail;

public interface HistoryDetailContract {

    interface View extends BaseContract.BaseView{

            void showHistory(Results results);





    }

    interface Presenter<T> extends BaseContract.BasePresenter<T>{

        void getHistoryDetail(String tripId);
        void getTripDetail(String tripId);
        void getRatingBar(String tripId,float ratingTrip);


    }
}
