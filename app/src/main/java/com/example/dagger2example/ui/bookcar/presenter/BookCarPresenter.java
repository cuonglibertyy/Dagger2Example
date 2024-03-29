package com.example.dagger2example.ui.bookcar.presenter;

import android.util.Log;

import com.example.dagger2example.R;
import com.example.dagger2example.base.RxPresenter;
import com.example.dagger2example.constans.Constans;
import com.example.dagger2example.data.DataManager;
import com.example.dagger2example.model.body.LocationBody;
import com.example.dagger2example.model.login.Token;
import com.example.dagger2example.model.typebike.Result;
import com.example.dagger2example.ui.bookcar.contract.BookCarContract;
import com.example.dagger2example.untils.ErrorHandle;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.RequestBody;

import static com.facebook.stetho.inspector.network.PrettyPrinterDisplayType.JSON;

public class BookCarPresenter extends RxPresenter<BookCarContract.View>
        implements BookCarContract.Presenter<BookCarContract.View> {

    LocationBody locationBody;
    DataManager dataManager;

//    List<Results> resultsList = new ArrayList<>();
    @Inject
    public BookCarPresenter(DataManager dataManager) {
        this.dataManager = dataManager;
    }


    @Override
    public void postTypeBike(String latitudemylocation, String longitudemylocation, String latiudedroffone, String longitudedroffone, String latdropofftwo, String longdropofftwo) {
        mView.showProgress(true);
        Token token = dataManager.getToken();
        if (token == null) {
            Log.d("ádsaasđấ", "lỗi token: ");
            mView.showError();
            return;
        }

        String tokenKey = token.getTokenKey();
        if (tokenKey == null & tokenKey.length() == 0) {
            Log.d("ádsaasđấ", "lỗi tokenkey: ");
            mView.showError();
            return;
        }


        Map<String, String> httpBody = new HashMap<>();
        httpBody.put("latitude",latitudemylocation);
        httpBody.put("longitude",longitudemylocation);

        Map<String, String> httpBodydroffone = new HashMap<>();
        httpBodydroffone.put("latitude",latiudedroffone);
        httpBodydroffone.put("longitude",longitudedroffone);

        Map<String, String> httpBodydrofftwo = new HashMap<>();
        httpBodydrofftwo.put("latitude", latdropofftwo);
        httpBodydrofftwo.put("longitude", longdropofftwo);

        Map<String, Object> httpBodyobject = new HashMap<>();
        httpBodyobject.put(Constans.KEY_START_LOCATION,httpBody);
        httpBodyobject.put(Constans.KEY_DROFF_ONE,httpBodydroffone);
        httpBodyobject.put(Constans.KEY_DROFF_TWO,httpBodydrofftwo);



        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),
                (new JSONObject(httpBodyobject)).toString());
        Log.d("dsadsasdsassa", "data: "+body.toString());



        Disposable disposable = dataManager.postTypeBike(tokenKey,body)
                .flatMap(typebike -> Observable.just(typebike.getResults().getResult()))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((beans ->{

                    mView.getTypeBike(beans);
                    Log.d("ádasdasds", "list: "+beans);


                }),(error ->{
                    Log.d("ádaafvvv", "error: "+error.toString());
                }));

        addSubscribe(disposable);
    }

    @Override
    public void PostBookBike(String jsonString) {
            mView.showProgress(true);

        Token token = dataManager.getToken();
        if (token == null) {
            Log.d("ádsaasđấ", "lỗi token: ");
            mView.showError();
            return;
        }

        String tokenKey = token.getTokenKey();
        if (tokenKey == null & tokenKey.length() == 0) {
            Log.d("ádsaasđấ", "lỗi tokenkey: ");
            mView.showError();
            return;
        }


//        Map<String, String> httpBody = new HashMap<>();
//        httpBody.put("latitude",latitudemylocation);
//        httpBody.put("longitude",longitudemylocation);
//
//        Map<String, String> httpBodydroffone = new HashMap<>();
//        httpBodydroffone.put("latitude",latiudedroffone);
//        httpBodydroffone.put("longitude",longitudedropoffone);

//        Map<String, String> httpBodydrofftwo = new HashMap<>();
//        httpBodydrofftwo.put("latitude",latdropofftwo);
//        httpBodydrofftwo.put("longitude", longdropofftwo);
//
//        Map<String,String> typebike = new HashMap<>();
//        typebike.put("estimatedDistance",estimatedDistance);
//        typebike.put("estimatedDuration",estimatedDuration);
//        typebike.put("estimatedPrice",estimatedPrice);
//        typebike.put("vehicleTypeLuxury",vehicleTypeLuxury);
//        typebike.put("vehicleTypeId",vehicleTypeId);


//        Map<String, Object> httpBodyobject = new HashMap<>();
////        httpBodyobject.put(Constans.KEY_START_LOCATION,httpBody);
//        httpBodyobject.put(Constans.KEY_START_LOCATION,httpBodydroffone);
////        httpBodyobject.put(Constans.KEY_DROFF_TWO,httpBodydrofftwo);




        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),
                (jsonString));

        Disposable disposable = dataManager.postMyLocation(tokenKey,body)
              .flatMap(error -> Observable.just(error))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((result ->{
                    mView.showProgress(false);
                    Log.d("wqewa", "result: "+result);
                }),(error ->{
                mView.getResult(ErrorHandle.errorParser(error));
                    Log.d("ádsadads", "PostBookBike: "+error);
                }));

        addSubscribe(disposable);


    }

    @Override
    public void getMyLocationName(String latlng) {

        Token token = dataManager.getToken();
        if (token == null) {
            Log.d("ádsaasđấ", "lỗi token: ");
            mView.showError();
            return;
        }

        String tokenKey = token.getTokenKey();
        if (tokenKey == null & tokenKey.length() == 0) {
            Log.d("ádsaasđấ", "lỗi tokenkey: ");
            mView.showError();
            return;
        }


        Disposable disposable = dataManager.getChooseLocation(latlng,Constans.KEY_GOOGLE_DIRECTION)
                .flatMap(geocode -> Observable.just(geocode.getResults()))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe((result ->{
                    mView.showName(result);
                    Log.d("sadaddasasd", "getMyLocationName: "+result);
                }),(error ->{
                    Log.d("sdsadasdsadadssdaasdasd", "getMyLocationName: "+error);
                    mView.showError(R.string.error);
                }));
        addSubscribe(disposable);


    }

    @Override
    public void getLastStatus() {

        Token token = dataManager.getToken();
        if (token == null) {
            Log.d("ádsaasđấ", "lỗi token: ");
            mView.showError();
            return;
        }

        String tokenKey = token.getTokenKey();
        if (tokenKey == null & tokenKey.length() == 0) {
            Log.d("ádsaasđấ", "lỗi tokenkey: ");
            mView.showError();
            return;
        }

        Disposable disposable = dataManager.getLastStatus(tokenKey)
                .flatMap(historyDetail -> Observable.just(historyDetail.getResults()))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe((result ->{
                    mView.showInformation(result);
                }),(error ->{
                    Log.d("sadasdsasda", "getLastStatus: "+error);
                    mView.showError(R.string.error);

                }));

        addSubscribe(disposable);
    }

    @Override
    public void getLocationDriver() {

        Token token = dataManager.getToken();
        if (token == null) {
            Log.d("ádsaasđấ", "lỗi token: ");
            mView.showError();
            return;
        }

        String tokenKey = token.getTokenKey();
        if (tokenKey == null & tokenKey.length() == 0) {
            Log.d("ádsaasđấ", "lỗi tokenkey: ");
            mView.showError();
            return;
        }

        Disposable disposable = dataManager.getLastStatus(tokenKey)
                .flatMap(historyDetail -> Observable.just(historyDetail.getResults()))
                .observeOn(AndroidSchedulers.mainThread())
                .observeOn(Schedulers.io())
                .subscribe((results -> {
                    mView.showLocationDriver(results);
                }),(error ->{
                    mView.showError(R.string.error);
                }));
        addSubscribe(disposable);
    }
}
