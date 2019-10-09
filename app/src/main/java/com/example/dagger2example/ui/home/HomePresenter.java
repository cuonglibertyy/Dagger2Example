package com.example.dagger2example.ui.home;

import android.widget.Button;

import com.example.dagger2example.base.RxPresenter;
import com.example.dagger2example.data.DataManager;
import com.jakewharton.rxbinding3.view.RxView;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class HomePresenter extends RxPresenter<HomeContract.View>
        implements HomeContract.Presenter<HomeContract.View> {


    DataManager dataManager;

    @Inject
    public HomePresenter(DataManager dataManager) {
        this.dataManager = dataManager;
    }


    @Override
    public void btn_click(Button button) {
      addSubscribe(RxView.clicks(button).subscribe(
              avoid ->{
                  mView.showProgress(true);
                  logout();
                  addSubscribe(Observable.just(0).delay(1000, TimeUnit.MILLISECONDS)
                          .subscribeOn(Schedulers.io())
                          .observeOn(AndroidSchedulers.mainThread())
                          .subscribe(aVoid -> {

                            mView.nextMain();

                          }));
              }
      ));
    }

    @Override
    public void logout() {
        dataManager.clearAllUser();
    }
}
