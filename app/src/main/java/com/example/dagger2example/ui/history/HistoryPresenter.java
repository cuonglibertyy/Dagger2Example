package com.example.dagger2example.ui.history;

import android.util.Log;
import android.widget.Button;

import com.example.dagger2example.base.RxPresenter;
import com.example.dagger2example.constans.Constans;
import com.example.dagger2example.data.DataManager;
import com.example.dagger2example.model.history.TripPackage;
import com.example.dagger2example.model.history.TripSection;
import com.example.dagger2example.model.login.Token;
import com.example.dagger2example.untils.DateUtils;
import com.jakewharton.rxbinding3.view.RxView;
import com.novoda.merlin.Logger;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import es.dmoral.toasty.Toasty;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.RequestBody;

public class HistoryPresenter extends RxPresenter<HistoryContract.View>
        implements HistoryContract.Presenter<HistoryContract.View> {


    DataManager dataManager;

    List<TripSection> tripSectionList = new ArrayList<>();

    @Inject
    public HistoryPresenter(DataManager dataManager) {
        this.dataManager = dataManager;

    }


    @Override
    public void getHistory(String page, String count) {
        if (page == null && count == null) {
            Log.d("sadsdsa", "lỗi 1: ");
            mView.showError();
            return;
        }

        Token token = dataManager.getToken();
        if (token == null) {
            Log.d("sadsdsa", "lỗi 2: ");
            mView.showError();
            return;
        }
        String tokenKey = dataManager.getToken().getTokenKey();
        if (tokenKey == null){
            Log.d("sadsdsa", "lỗi 3: ");
            mView.showError();
            return;
        }

        Map<String, String> httpBody = new HashMap<>();
        httpBody.put(Constans.KEY_HISTORY_PAGE, page);
        httpBody.put(Constans.KEY_HISTORY_COUNT, count);

        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),
                (new JSONObject(httpBody)).toString());

        Disposable disposable = dataManager.getHistory(tokenKey, body)
        .flatMap(history -> Observable.just(history.getResults().getTripPackage()))
                .map(tripList -> {
                    List<TripSection> tripSectionChildList = new ArrayList<>();

                    for (TripPackage trip : tripList) {
                        if (!tripSectionList.contains(new TripSection(true, DateUtils.convertMiliToDate(trip.getCreatedDate())))) {
                            tripSectionList.add(new TripSection(true,
                                    DateUtils.convertMiliToDate(trip.getCreatedDate())));
                            tripSectionChildList.add(new TripSection(true,
                                    DateUtils.convertMiliToDate(trip.getCreatedDate())));
                        }
                        tripSectionList.add(new TripSection(trip));
                        tripSectionChildList.add(new TripSection(trip));
                    }
                    return tripSectionChildList;
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        (beans) ->{
                            mView.showHistory(beans);
                            Log.d("đâsasdsad", "getHistory: "+beans);
                },
                        (error) ->{
                            mView.showError();
                            Log.d("sadasdasdasdas", "getHistory: "+error);

                }
                );

        addSubscribe(disposable);
    }



}

