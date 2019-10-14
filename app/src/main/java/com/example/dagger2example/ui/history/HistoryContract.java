package com.example.dagger2example.ui.history;

import android.widget.Button;

import com.example.dagger2example.base.BaseContract;
import com.example.dagger2example.model.history.History;
import com.example.dagger2example.model.history.TripSection;

import java.util.List;

public interface HistoryContract {

    interface View extends BaseContract.BaseView{

        void showHistory(List<TripSection> tripSectionListr);

    }

    interface Presenter<T> extends BaseContract.BasePresenter<T>{

        void getHistory(String page,String count);
    }
}
