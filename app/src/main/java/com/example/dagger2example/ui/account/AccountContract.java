package com.example.dagger2example.ui.account;

import android.widget.Button;

import com.example.dagger2example.base.BaseContract;

public interface AccountContract {

    interface View extends BaseContract.BaseView{
        void showAvatar(String url);
        void showName(String name);

        void  nextMain();

    }

    interface Presenter<T> extends BaseContract.BasePresenter<T>{
        void btn_click(Button button);
        void logout();
        void getAvatar();
        void getName();
    }
}
