package com.example.dagger2example.ui.splash;

import com.example.dagger2example.base.RxPresenter;
import com.example.dagger2example.data.DataManager;
import com.example.dagger2example.model.login.Token;
import com.example.dagger2example.model.login.UserInfo;

import javax.inject.Inject;

public class SplashPresenter extends RxPresenter<SplashContract.View>
        implements SplashContract.Presenter<SplashContract.View> {

    DataManager dataManager;

    @Inject
    public SplashPresenter(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    @Override
    public void CheckLoginUser() {
        boolean Isloggin = dataManager.IsLoggedIn();
        mView.finishCheckLogin(Isloggin);
    }

    @Override
    public void CheckLoggin() {
        UserInfo userInfo = dataManager.getUserInfo();
        if (userInfo == null){
            mView.showLogin();
        }else {
            mView.showMain();
        }
//        Token token = dataManager.getToken();
//        String tokenKey = token.getTokenKey();
//        if (token != null) {
//            mView.showMain();
//            return;
//        } else if (tokenKey != null) {
//            mView.showMain();
//            return;
//        } else {
//            mView.showLogin();
//
//
//        }



    }
}
