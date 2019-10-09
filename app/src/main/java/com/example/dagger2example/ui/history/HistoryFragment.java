package com.example.dagger2example.ui.history;

import android.os.Bundle;
import android.widget.Button;

import com.example.dagger2example.R;
import com.example.dagger2example.base.BaseFragment;
import com.example.dagger2example.model.login.Results;
import com.example.dagger2example.ui.login.LoginActivity;
import com.example.dagger2example.widget.LoadingDialog;
import com.novoda.merlin.Bindable;
import com.novoda.merlin.Connectable;
import com.novoda.merlin.Disconnectable;
import com.novoda.merlin.NetworkStatus;

import javax.inject.Inject;

import butterknife.BindView;

public class HistoryFragment extends BaseFragment implements Connectable, Disconnectable, Bindable, HistoryContract.View {


    @Inject
    HistoryPresenter presenter;

    public static HistoryFragment newInstance() {
        Bundle args = new Bundle();
        HistoryFragment fragment = new HistoryFragment();
        fragment.setArguments(args);
        return fragment;
    }



    @Override
    protected void attachView() {
        presenter.attachView(this);
    }

    @Override
    protected void detachView() {
        presenter.detachView();
    }

    @Override
    protected void initToolbar() {

    }

    @Override
    protected void initDatas() {

    }

    @Override
    protected void configViews() {

    }

    @Override
    protected void setupComponent() {
        getActivityComponent().inject(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_history;
    }

    @Override
    protected void addEvents() {

    }


    @Override
    public void onBind(NetworkStatus networkStatus) {

    }

    @Override
    public void onConnect() {

    }

    @Override
    public void onDisconnect() {

    }

    @Override
    public void showProgress(boolean show) {
        if (show) {
            LoadingDialog.getInstance().showLoading(activity);
        } else {
            LoadingDialog.getInstance().hideLoading();
        }
    }

    @Override
    public void showError(int stringResId) {

    }

    @Override
    public void showError() {

    }

    @Override
    public void onComplete(Results results) {

    }


    @Override
    public void nextMain() {
        LoginActivity.startActivity(activity);
        activity.finish();
        showProgress(false);
    }
}
