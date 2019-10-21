package com.example.dagger2example.ui.bookcar.contract;

import com.example.dagger2example.base.BaseContract;
import com.example.dagger2example.model.error.Error;
import com.example.dagger2example.model.typebike.Result;
import com.example.dagger2example.model.typebike.Results;
import com.example.dagger2example.model.typebike.Typebike;

import java.util.List;

public interface BookCarContract {

    interface View extends BaseContract.BaseView{



        void getResult(Error error);
        void getTypeBike(List<Result> results);

    }

    interface Presenter<T> extends BaseContract.BasePresenter<T>{

        void postTypeBike(String latitudemylocation,String longitudemylocation,String latiudedroffone,String longitudedroffone,String latdropofftwo,String longdropofftwo);
        void PostBookBike(String latitudemylocation,String longitudemylocation,String latiudedroffone,String longitudedroffone,String latdropofftwo,String longdropofftwo);
    }
}
