package com.example.dagger2example.ui.splash;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.example.dagger2example.R;
import com.example.dagger2example.base.BaseActivity;
import com.example.dagger2example.constans.Constans;
import com.example.dagger2example.data.DataManager;
import com.example.dagger2example.model.ErrorParser;
import com.example.dagger2example.model.error.Error;
import com.example.dagger2example.model.history.TripPackage;
import com.example.dagger2example.model.login.EtrantJsonResult;
import com.example.dagger2example.model.login.Results;
import com.example.dagger2example.ui.account.AccountFragment;
import com.example.dagger2example.ui.login.LoginActivity;
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

import es.dmoral.toasty.Toasty;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class SplashActivity extends BaseActivity implements SplashContract.View, Connectable, Disconnectable, Bindable {

    @Inject
    SplashPresenter splashPresenter;


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
    protected void configViews() {

    }

    @Override
    protected void addEvens() {
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
    protected void attachView() {
        splashPresenter.attachView(this);
    }

    @Override
    protected void detachView() {
        splashPresenter.detachView();
    }

    @Override
    protected void setupComponent() {
        getActivityComponent().inject(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.splash_activity;
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
        showProgress(false);
    }

    @Override
    public void showSuccessful() {

    }

    @Override
    public void onComplete(Results results) {

    }

    @Override
    public void onBind(NetworkStatus networkStatus) {
        if (!networkStatus.isAvailable()) {
            onDisconnect();
        }

    }

    @Override
    public void onConnect() {
        showProgress(true);
        splashPresenter.CheckLoggin();

    }

    @Override
    public void onDisconnect() {
        showProgress(false);
        showToatDisconnect();


    }


    private void showToatDisconnect() {
        Toasty.warning(this, Constans.Disconnect, Toast.LENGTH_SHORT, true).show();
    }

    @Override
    public void finishCheckDeviceId() {
        showLogin();
    }

    @Override
    public void finishCheckLogin(boolean isLoggin) {
        if (isLoggin) {
            splashPresenter.CheckLoggin();
        }
    }

    @Override
    public void finishGetStatusUser(EtrantJsonResult etrantJsonResult) {

        showProgress(false);

        Results results = etrantJsonResult.getResults();
        if (results != null) {
            openMainScreen();
            return;
        }


    }

    @Override
    public void showErrorStatus(Error errorParser, DataManager dataManager) {
        showProgress(false);
        if (NetworkUntils.isOnline()) {
            if (errorParser != null && errorParser.getResults().getError() != null) {
                String mess = errorParser.getResults().getError().getMessage();
                if (mess.equalsIgnoreCase(Constans.user_login_other_device)) {
                    Toasty.error(this, getString(R.string.login_other_device), 200).show();
                    LoginActivity.startActivity(this);
                    dataManager.clearAllUser();
                }
            } else {
                Toasty.error(this, R.string.common_noti_error_message).show();
            }
        } else {
            showToatDisconnect();
        }
    }


    @Override
    public void showLogin() {
        addDisposable(Observable.just(0).delay(1000, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(aVoid -> {
                    openLoginScreen();
                }));
    }

    @Override
    public void showMain() {
        addDisposable(Observable.just(0).delay(1000, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(aVoid -> {
                    openMainScreen();
                }));
    }

    @Override
    public void showDeviceIdFirebase(String deviceId) {
        splashPresenter.saveDeviceIdSharedPreferences(deviceId);

    }

    private void openMainScreen() {
        showProgress(false);
        MainActivity.startActivity(this);
        finish();
    }

    private void openLoginScreen() {
        showProgress(false);
        LoginActivity.startActivity(this);
        finish();
    }
}
