package com.example.dagger2example.ui.home;

import android.widget.Button;

import com.example.dagger2example.base.BaseContract;
import com.example.dagger2example.model.error.Error;
import com.example.dagger2example.model.error.Results;
import com.example.dagger2example.untils.ErrorHandle;

public interface HomeContract {

    interface View extends BaseContract.BaseView{



        void getResult(Error error);

    }

    interface Presenter<T> extends BaseContract.BasePresenter<T>{

        void PostBookBike(String latitudemylocation,String longitudemylocation,String latiudedroffone,String longitudedroffone);
    }
}
