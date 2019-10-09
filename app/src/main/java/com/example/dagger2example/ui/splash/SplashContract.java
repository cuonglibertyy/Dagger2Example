package com.example.dagger2example.ui.splash;

import com.example.dagger2example.base.BaseContract;
import com.example.dagger2example.model.login.EtrantJsonResult;

public interface SplashContract {

    interface View extends BaseContract.BaseView{

        void finishCheckLogin(boolean isLoggin);
        void finishGetStatusUser(EtrantJsonResult etrantJsonResult);
        void showLogin();
        void showMain();

    }

    interface Presenter<T> extends BaseContract.BasePresenter<T>{

        void CheckLoginUser();
        void CheckLoggin();

    }
}
