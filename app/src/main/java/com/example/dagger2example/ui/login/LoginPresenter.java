package com.example.dagger2example.ui.login;

import android.util.Log;
import android.widget.Button;

import com.example.dagger2example.R;
import com.example.dagger2example.base.RxPresenter;
import com.example.dagger2example.constans.Constans;
import com.example.dagger2example.data.DataManager;
import com.example.dagger2example.model.login.Results;
import com.example.dagger2example.model.login.Token;
import com.google.firebase.iid.FirebaseInstanceId;
import com.jakewharton.rxbinding3.view.RxView;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.RequestBody;

public class LoginPresenter extends RxPresenter<LoginContract.View>
        implements LoginContract.Presenter<LoginContract.View> {


    DataManager dataManager;
    String deviceId ="";

    @Inject
    public LoginPresenter(DataManager dataManager) {
        this.dataManager = dataManager;

    }


    @Override
    public void checklogin(String phoneNumber) {
        mView.showProgress(true);


         deviceId = FirebaseInstanceId.getInstance().getToken();
        Log.d("asdsadasd1", "checklogin: "+deviceId);
        if (deviceId == null) {
            FirebaseInstanceId.getInstance().getInstanceId()
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            deviceId = Objects.requireNonNull(task.getResult()).getToken();
                            Log.d("asdsadasd2", "checklogin: "+deviceId);
                        }
                    })
                    .addOnFailureListener(e -> {
                        Log.d("easdasdsa", "error: "+e);

                    });
        }

        Map<String, String> httpBody = new HashMap<>();
        httpBody.put(Constans.KEY_LOGIN_STATE_CODE, "84");
        httpBody.put(Constans.KEY_LOGIN_DEVICE_ID,deviceId);
        httpBody.put(Constans.KEY_LOGIN_PHONE_NUMBER, phoneNumber);
        httpBody.put(Constans.KEY_LOGIN_DEVICE_TYPE, "1");

        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),
                (new JSONObject(httpBody)).toString());

        Disposable disposable = dataManager.LoginUser(body)
                .flatMap(loginJsonResult -> Observable.just(loginJsonResult.getResults()))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe((results) -> {

                            mView.onComplete(results);
                        },
                        (error) -> {
                            mView.showError();
                            mView.showProgress(false);
                        }
                );
        addSubscribe(disposable);
    }

    @Override
    public void saveUserInfoSharedPreference(Results results) {
        if (results != null) {
            dataManager.UpdateUserInfoSharePreference(results.getUserInfo(), results.getToken(), true);
            mView.finishSaveLoginSharedPreference();
        } else {
            mView.showError(R.string.error);
        }
    }

    @Override
    public void checkUserStatus() {
        Token token = dataManager.getToken();
        if (token == null) {
            mView.showLogin();
            return;
        }

        String tokenKey = token.getTokenKey();
        if (tokenKey == null & tokenKey.length() == 0) {
            mView.showLogin();
            return;
        }

    }

    @Override
    public void btn_click(Button button) {
        addSubscribe(RxView.clicks(button).subscribe(avoid -> {
            mView.nextMain();
        }));
    }


}
