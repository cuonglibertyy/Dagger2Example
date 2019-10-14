package com.example.dagger2example.ui.splash;

import android.util.Log;

import com.example.dagger2example.base.RxPresenter;
import com.example.dagger2example.data.DataManager;
import com.example.dagger2example.model.login.Token;
import com.example.dagger2example.model.login.UserInfo;
import com.example.dagger2example.untils.ErrorHandle;
import com.google.gson.Gson;
import com.novoda.merlin.Logger;

import org.xml.sax.ErrorHandler;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

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
//        UserInfo userInfo = dataManager.getUserInfo();
//        if (userInfo == null){
//            mView.showLogin();
//        }else {
//            mView.showMain();
//        }
        Token token = dataManager.getToken();
        if (token == null) {
            mView.showLogin();
            return;
        }

        String tokenKey = dataManager.getToken().getTokenKey();
        if (tokenKey == null) {
            mView.showLogin();
            return;
        }

        Disposable disposable = dataManager.getLastStatusDriver(tokenKey)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        (beans) -> {
//                            Log.d("dsasadsad", "CheckLoggin: "+beans);
                            mView.finishGetStatusUser(beans);
                        },
                        (error) -> {
                            Logger.w("GET LAST STATUS ERROR: %s", error);
//                            mView.showError(R.string.common_noti_error_message);
                            mView.showErrorStatus(ErrorHandle.errorParser(error),dataManager);
                        }
                );
        addSubscribe(disposable);



    }
}
