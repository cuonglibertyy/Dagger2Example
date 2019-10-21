package com.example.dagger2example.ui.splash;

import com.example.dagger2example.base.BaseContract;
import com.example.dagger2example.data.DataManager;
import com.example.dagger2example.model.ErrorParser;
import com.example.dagger2example.model.error.Error;
import com.example.dagger2example.model.login.EtrantJsonResult;

import org.xml.sax.ErrorHandler;

public interface SplashContract {

    interface View extends BaseContract.BaseView{
        void finishCheckDeviceId();
        void finishCheckLogin(boolean isLoggin);
        void finishGetStatusUser(EtrantJsonResult etrantJsonResult);
        void showErrorStatus(Error errorParser, DataManager dataManager);
        void showLogin();
        void showMain();
        void showDeviceIdFirebase(String deviceId);

    }

    interface Presenter<T> extends BaseContract.BasePresenter<T>{
        void getDeviceIdFirebase();

        void saveDeviceIdSharedPreferences(String deviceId);
        void CheckLoginUser();
        void CheckLoggin();


    }
}
