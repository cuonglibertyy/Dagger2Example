package com.example.dagger2example.ui.account;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

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
import de.hdodenhof.circleimageview.CircleImageView;

public class AccountFragment extends BaseFragment implements AccountContract.View {

    @BindView(R.id.btn_logout)
    Button btn_logout;

    @BindView(R.id.img_avatar)
    CircleImageView img_avatar;

    @BindView(R.id.tv_name)
    TextView tv_name;

    @Inject
    AccountPresenter presenter;

    public static AccountFragment newInstance() {
        Bundle args = new Bundle();
        AccountFragment fragment = new AccountFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.getAvatar();
        presenter.getName();
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
        return R.layout.fragment_account;
    }

    @Override
    protected void addEvents() {
        presenter.btn_click(btn_logout);
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
    public void showSuccessful() {

    }

    @Override
    public void onComplete(Results results) {

    }


    @Override
    public void showAvatar(String url) {
        loadAvatar(url,img_avatar);
    }

    @Override
    public void showName(String name) {
        loadFullName(name,tv_name);
    }

    @Override
    public void nextMain() {
        LoginActivity.startActivity(activity);
        activity.finish();
        showProgress(false);
    }


}
