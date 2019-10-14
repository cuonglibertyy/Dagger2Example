package com.example.dagger2example.ui.history.historydetail;

import android.util.Log;
import android.widget.Toast;

import com.example.dagger2example.base.RxPresenter;
import com.example.dagger2example.constans.Constans;
import com.example.dagger2example.data.DataManager;
import com.example.dagger2example.model.historydetail.Results;
import com.example.dagger2example.model.historydetail.TripPackageDetail;
import com.example.dagger2example.model.login.Token;
import com.example.dagger2example.ui.history.HistoryContract;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import es.dmoral.toasty.Toasty;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.RequestBody;

public class HistoryDetailPresenter extends RxPresenter<HistoryDetailContract.View>
        implements HistoryDetailContract.Presenter<HistoryDetailContract.View> {

    DataManager dataManager;
    @Inject
     HistoryDetailPresenter(DataManager dataManager) {
        this.dataManager = dataManager;
    }




    @Override
    public void getHistoryDetail(String tripId) {
        if (tripId == null){
            mView.showError();
            Log.d("sadasasd", "lỗi tripID: ");
            return;
        }
        Token token = dataManager.getToken();
        if (token== null){
            mView.showError();
            Log.d("sadasasd", "lỗi token: ");
            return;
        }

        String tokenKey = dataManager.getToken().getTokenKey();
        if (tokenKey == null){
            mView.showError();

            return;
        }
        Map<String, String> httpBody = new HashMap<>();
        httpBody.put(Constans.KEY_HISTORY_DETAIL_TRIP_ID,tripId);

        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),
                (new JSONObject(httpBody)).toString());

        Disposable disposable = dataManager.getHistoryDetail(tokenKey,body)
            .flatMap(historyDetail -> Observable.just(historyDetail.getResults()))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((results -> {
                    mView.showHistory(results);
                    Log.d("sdsaddsa", "getHistoryDetail: "+results);
                }),(error ->{
                    mView.showError();
                    Log.d("dsadsadsa", "getHistoryDetail: "+error);
                }));

    addSubscribe(disposable);

    }

    @Override
    public void getTripDetail(String tripId) {

    }




    @Override
    public void getRatingBar(String tripId, String ratingTrip) {

            if (tripId == null){
                Log.d("sadasasd", "lỗi k có tripID: ");

                return;
            }

        Token token = dataManager.getToken();
        if (token== null){
            Log.d("sadasasd", "lỗi tokenkey: ");
            return;
        }

        String tokenKey = dataManager.getToken().getTokenKey();
        if (tokenKey == null){

            Log.d("sadasasd", "lỗi tokenkey: ");
            return;
        }

        Map<String, String> httpBody = new HashMap<>();
        httpBody.put(Constans.KEY_HISTORY_DETAIL_TRIP_ID,tripId);
        httpBody.put(Constans.KEY_RATING,ratingTrip);

        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),
                (new JSONObject(httpBody)).toString());

       Disposable disposable = dataManager.postRatingBar(tokenKey,body)
               .flatMap(tripPackageDetail -> Observable.just(tripPackageDetail))
               .subscribeOn(Schedulers.io())
               .observeOn(AndroidSchedulers.mainThread())
               .subscribe((hisResults ->{
                   mView.showSuccessful();
               }),(error) ->{

                   mView.showError();
                   Log.d("sadsasdad", "getRatingBar: "+error);
               });



                addSubscribe(disposable);
    }





}
