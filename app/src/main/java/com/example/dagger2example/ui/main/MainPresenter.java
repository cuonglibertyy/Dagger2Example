package com.example.dagger2example.ui.main;

import com.example.dagger2example.base.RxPresenter;
import com.example.dagger2example.data.DataManager;

import javax.inject.Inject;

public class MainPresenter extends RxPresenter<MainContract.View>
        implements MainContract.Presenter<MainContract.View> {

    DataManager dataManager;

    @Inject
    public MainPresenter(DataManager dataManager) {
        this.dataManager = dataManager;
    }
}
