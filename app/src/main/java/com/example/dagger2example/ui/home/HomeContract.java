package com.example.dagger2example.ui.home;

import android.widget.Button;

import com.example.dagger2example.base.BaseContract;

public interface HomeContract {

    interface View extends BaseContract.BaseView{

        void  nextMain();

    }

    interface Presenter<T> extends BaseContract.BasePresenter<T>{
        void btn_click(Button button);
        void logout();
    }
}
