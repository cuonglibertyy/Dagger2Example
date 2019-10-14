package com.example.dagger2example.ui.main;

import android.util.Log;

import com.example.dagger2example.base.RxPresenter;
import com.example.dagger2example.data.DataManager;
import com.example.dagger2example.model.login.Token;
import com.example.dagger2example.untils.ErrorHandle;
import com.novoda.merlin.Logger;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainPresenter extends RxPresenter<MainContract.View>
        implements MainContract.Presenter<MainContract.View> {

    DataManager dataManager;

    @Inject
    public MainPresenter(DataManager dataManager) {
        this.dataManager = dataManager;
    }


    @Override
    public void checkUser() {

        Token token = dataManager.getToken();
        if (token == null){
            mView.showError();
            mView.showlogin();
            return;
        }

        String tokenKey = dataManager.getToken().getTokenKey();
        if (tokenKey == null){
            mView.showError();
            mView.showlogin();
            return;
        }

    }
}
