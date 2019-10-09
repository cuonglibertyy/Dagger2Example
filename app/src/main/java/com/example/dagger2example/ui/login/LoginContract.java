package com.example.dagger2example.ui.login;

import android.widget.Button;

import com.example.dagger2example.base.BaseContract;
import com.example.dagger2example.model.login.Results;

public interface LoginContract {

    interface View extends BaseContract.BaseView{

        void showLogin();
        void finishSaveLoginSharedPreference();
        void nextMain();



    }

    interface Presenter<T> extends BaseContract.BasePresenter<T>{

        void   checklogin(String phoneNumber);

        void saveUserInfoSharedPreference(Results results);
        void checkUserStatus();
        void btn_click(Button button);

    }


}
