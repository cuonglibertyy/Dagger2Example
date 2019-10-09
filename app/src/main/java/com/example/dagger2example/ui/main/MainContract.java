package com.example.dagger2example.ui.main;

import com.example.dagger2example.base.BaseContract;

public interface MainContract {
    interface View extends BaseContract.BaseView{



    }

    interface Presenter<T> extends BaseContract.BasePresenter<T>{



    }
}
