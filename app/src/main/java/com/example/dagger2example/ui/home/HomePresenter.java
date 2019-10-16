package com.example.dagger2example.ui.home;

import android.util.Log;
import android.widget.Button;

import com.example.dagger2example.base.RxPresenter;
import com.example.dagger2example.constans.Constans;
import com.example.dagger2example.data.DataManager;
import com.example.dagger2example.model.error.Results;
import com.example.dagger2example.model.login.Token;
import com.example.dagger2example.untils.ErrorHandle;
import com.jakewharton.rxbinding3.view.RxView;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.RequestBody;

public class HomePresenter extends RxPresenter<HomeContract.View>
        implements HomeContract.Presenter<HomeContract.View> {


    DataManager dataManager;

    @Inject
    public HomePresenter(DataManager dataManager) {
        this.dataManager = dataManager;
    }


    @Override
    public void PostBookBike(String latitudemylocation, String longitudemylocation, String latiudedroffone, String longitudedroffone) {
            mView.showProgress(true);

        Token token = dataManager.getToken();
        if (token == null) {
            mView.showError();
            return;
        }

        String tokenKey = token.getTokenKey();
        if (tokenKey == null & tokenKey.length() == 0) {
            mView.showError();
            return;
        }


        Map<String, String> httpBody = new HashMap<>();
        httpBody.put("latitude",latitudemylocation);
        httpBody.put("longitude",longitudemylocation);

        Map<String, String> httpBodydroffone = new HashMap<>();
        httpBody.put("latitude",latiudedroffone);
        httpBody.put("longitude",longitudedroffone);

        Map<String, Object> httpBodyobject = new HashMap<>();
        httpBodyobject.put(Constans.KEY_START_LOCATION,httpBody);
        httpBodyobject.put(Constans.KEY_DROFF_ONE,httpBodydroffone);



        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),
                (new JSONObject(httpBodyobject)).toString());

        Disposable disposable = dataManager.postMyLocation(tokenKey,body)
              .flatMap(error -> Observable.just(error))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((result ->{
                    mView.showSuccessful();

                    Log.d("wqewa", "result: "+result);
                }),(error ->{
                mView.getResult(ErrorHandle.errorParser(error));

                }));

        addSubscribe(disposable);


    }
}
