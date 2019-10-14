package com.example.dagger2example.ui.main;

import android.content.Context;
import android.content.Intent;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.dagger2example.R;
import com.example.dagger2example.base.BaseActivity;
import com.example.dagger2example.model.login.Results;
import com.example.dagger2example.ui.account.AccountFragment;
import com.example.dagger2example.ui.history.HistoryFragment;
import com.example.dagger2example.ui.home.HomeFragment;
import com.example.dagger2example.ui.login.LoginActivity;
import com.example.dagger2example.ui.notification.NotificationFragment;
import com.example.dagger2example.ui.wallet.WalletFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.novoda.merlin.Merlin;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import butterknife.BindView;
import es.dmoral.toasty.Toasty;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends BaseActivity implements MainContract.View {

    @BindView(R.id.main_status_network)
    TextView mainStatusNetwork;

    @BindView(R.id.bottom_navigation_view)
    BottomNavigationView bottomNavigationView;

    @BindView(R.id.main_container)
    FrameLayout main_container;

    private HomeFragment homeFragment = HomeFragment.newInstance();
    //    private WalletFragment walletFragment = WalletFragment.newInstance();
    private HistoryFragment historyFragment = HistoryFragment.newInstance();
    private NotificationFragment notificationFragment = NotificationFragment.newInstance();
    private AccountFragment accountFragment = AccountFragment.newInstance();
    private WalletFragment walletFragment = WalletFragment.newInstance();


    private Fragment activeFragment;

    @Inject
    MainPresenter mainPresenter;

    public static void startActivity(Context context) {
        context.startActivity(new Intent(context, MainActivity.class));
    }

    @Override
    protected void configViews() {
        getSupportFragmentManager().beginTransaction().replace(R.id.main_container,new HomeFragment()).commit();
        bottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    @Override
    protected void addEvens() {
        mainPresenter.checkUser();
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
        mainPresenter.attachView(this);
    }

    @Override
    protected void detachView() {

        mainPresenter.detachView();

    }

    @Override
    protected void setupComponent() {
        getActivityComponent().inject(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.main_activity1;
    }

    private BottomNavigationView.OnNavigationItemSelectedListener
            mOnNavigationItemSelectedListener = menuItem -> {

        Fragment fragment = null;
        switch (menuItem.getItemId()) {
            case R.id.menu_navigation_home:
                fragment = new HomeFragment();
//                fakeStatusBar.setBackgroundColor(getResources().getColor(R.color.toolBar));
              break;
            case R.id.menu_navigation_wallet:
                fragment = new WalletFragment();
//                fakeStatusBar.setBackgroundColor(getResources().getColor(R.color.toolBar));
                break;
            case R.id.menu_navigation_history:
                fragment = new HistoryFragment();
//                fakeStatusBar.setBackgroundColor(getResources().getColor(R.color.toolBar));
                break;
            case R.id.menu_navigation_notification:
                fragment = new NotificationFragment();
//                fakeStatusBar.setBackgroundColor(getResources().getColor(R.color.toolBar));
                break;
            case R.id.menu_navigation_account:
                fragment = new AccountFragment();
//                fakeStatusBar.setBackgroundColor(getResources().getColor(R.color.blue));
                break;
        }
        getSupportFragmentManager().beginTransaction().replace(R.id.main_container,fragment).commit();
        return true;
    };





    @Override
    public void showProgress(boolean show) {

    }

    @Override
    public void showError(int stringResId) {

    }

    @Override
    public void showError() {
        Toasty.error(this,"Tài khoản bạn có người đăng nhập").show();
    }

    @Override
    public void showSuccessful() {

    }

    @Override
    public void onComplete(Results results) {

    }

    @Override
    public void showlogin() {
        addDisposable(Observable.just(0).delay(1000, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(aVoid -> {
                    openLoginScreen();
                }));
    }

    private void openLoginScreen() {
        showProgress(false);
        LoginActivity.startActivity(this);
        finish();
    }
}
