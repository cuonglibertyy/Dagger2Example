package com.example.dagger2example.ui.login;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import com.example.dagger2example.R;
import com.example.dagger2example.base.BaseActivity;
import com.example.dagger2example.constans.Constans;
import com.example.dagger2example.model.login.Results;
import com.example.dagger2example.ui.main.MainActivity;
import com.example.dagger2example.untils.NetworkUntils;
import com.example.dagger2example.widget.LoadingDialog;
import com.novoda.merlin.Bindable;
import com.novoda.merlin.Connectable;
import com.novoda.merlin.Disconnectable;
import com.novoda.merlin.Merlin;
import com.novoda.merlin.NetworkStatus;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import butterknife.BindView;
import es.dmoral.toasty.Toasty;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class LoginActivity extends BaseActivity implements LoginContract.View, Connectable, Bindable, Disconnectable {

    @BindView(R.id.ed_phoneNumber)
    EditText ed_phoneNumber;

    @BindView(R.id.btn_login)
    Button btn_Login;

    @Inject
    LoginPresenter loginPresenter;


    public static void startActivity(Context context) {
        context.startActivity(new Intent(context, LoginActivity.class));
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerConnectable(this);
        registerDisconnectable(this);
        registerBindable(this);
    }

    @Override
    public void onBind(NetworkStatus networkStatus) {
        if (!networkStatus.isAvailable()) {
            onDisconnect();
        }
    }

    @Override
    public void onConnect() {
        showProgress(false);
    }

    @Override
    public void onDisconnect() {
        showProgress(true);
        showErrorToasty();
    }

    @Override
    protected Merlin initMerlin() {
        return new Merlin.Builder()
                .withConnectableCallbacks()
                .withDisconnectableCallbacks()
                .withBindableCallbacks()
                .build(this);
    }

    @Override
    protected void configViews() {

    }

    @Override
    protected void addEvens() {
        loginPresenter.btn_click(btn_Login);


    }


    @Override
    protected void attachView() {
        loginPresenter.attachView(this);
    }

    @Override
    protected void detachView() {
        loginPresenter.detachView();
    }

    @Override
    protected void setupComponent() {
        getActivityComponent().inject(this);
    }


    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public void showLogin() {

    }

    @Override
    public void finishSaveLoginSharedPreference() {
        if (NetworkUntils.isOnline()) {

        }
    }

    @Override
    public void nextMain() {
        String phoneNumber = ed_phoneNumber.getText().toString().trim();
        if (phoneNumber.length()< 0 && phoneNumber.length() > 11){

            showError();
        }
        loginPresenter.checklogin(phoneNumber);
    }


    @Override
    public void showProgress(boolean show) {
        if (show) {
            LoadingDialog.getInstance().showLoading(this);
        } else {
            LoadingDialog.getInstance().hideLoading();
        }
    }

    @Override
    public void showError(int stringResId) {

    }

    @Override
    public void showError() {
        Toasty.error(this,"Số điện thoại không đúng !",Toasty.LENGTH_SHORT).show();
    }

    @Override
    public void onComplete(Results results) {
//        Log.d("dadsaa", "onComplete: " + results.getToken().toString());
        if (results != null) {
            loginPresenter.saveUserInfoSharedPreference(results);
            addDisposable(Observable.just(0).delay(1000, TimeUnit.MILLISECONDS)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(aVoid -> {
                        MainActivity.startActivity(this);
                        finish();
                        showProgress(false);
                    }));
        }


    }


}
