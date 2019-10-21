package com.example.dagger2example.ui.history;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dagger2example.R;
import com.example.dagger2example.base.BaseFragment;
import com.example.dagger2example.model.history.TripPackage;
import com.example.dagger2example.model.history.TripSection;
import com.example.dagger2example.model.login.Results;
import com.example.dagger2example.ui.history.adapter.HistoryAdapter;
import com.example.dagger2example.ui.history.historydetail.HistoryDetailActivity;
import com.example.dagger2example.widget.LoadingDialog;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import butterknife.BindView;
import es.dmoral.toasty.Toasty;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;

public class HistoryFragment extends BaseFragment implements HistoryContract.View {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    private int page = 0;
    private int count = 5;

    private List<TripSection> tripSectionList = new ArrayList<>();
    private HistoryAdapter adapter;
    private boolean isLoadMore = false;
    private boolean isData = false;


    @Inject
    HistoryPresenter presenter;

    public static HistoryFragment newInstance() {
        Bundle args = new Bundle();
        HistoryFragment fragment = new HistoryFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onResume() {
        super.onResume();

        if (adapter.isLoadMoreEnable() == true && isData == true) {
            isLoadMore = true;
            presenter.getHistory(String.valueOf(page++), String.valueOf(count));
        }


    }

    @Override
    public void onPause() {
        super.onPause();
//        if (isLoadMore == true) {
//            page++;
//            presenter.getHistory(String.valueOf(page), String.valueOf(count));
//            Log.d("saddsasaddsad", "onPause: ");
//            return;
//


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
        appCompatActivity.setSupportActionBar(toolbar);
        if (appCompatActivity.getSupportActionBar() != null) {
            appCompatActivity.getSupportActionBar().setDisplayShowTitleEnabled(false);
        }
    }

    @Override
    protected void initDatas() {

    }

    @Override
    protected void configViews() {
        adapter = new HistoryAdapter();
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        adapter.setLoadMoreView(new CustomLoadMore());
        presenter.getHistory(String.valueOf(0), String.valueOf(count));

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
        adapter.setOnLoadMoreListener(() -> {
            page++;
            isData = true;
            addDisposable(Observable.timer(2, TimeUnit.SECONDS)
                    .compose(bindToLifecycle())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(avoid -> {
                        isLoadMore = true;
                        presenter.getHistory(String.valueOf(page), String.valueOf(count));
                    })

            );
        }, recyclerView);

        // send ID TripID sang màn hình HistoryDetail

        adapter.setOnItemClickListener((adapter, view, position) -> {
            TripPackage trip = tripSectionList.get(position).t;

            Log.d("sadsaasd", "addEvents: " + trip);
            if (trip == null) {

                return;
            }


            String tripId = trip.getTripPackageId();
            if (tripId == null) {
                return;
            }

            Log.d("sadsadsasad", "addEvents: " + tripId);
            HistoryDetailActivity.startActivity(context, tripId);
        });
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
        showProgress(false);
        if (adapter.isLoadMoreEnable()) {
            adapter.loadMoreFail();
        }
        Toasty.error(activity, R.string.error, Toasty.LENGTH_SHORT).show();
    }

    @Override
    public void showSuccessful() {

    }

    @Override
    public void onComplete(Results results) {

    }


    @Override
    public void showHistory(List<TripSection> tripSectionListr) {
        tripSectionList.addAll(tripSectionListr);
        int sizeTransactionList = tripSectionListr.size();
        if (!isLoadMore) {
            adapter.setNewData(tripSectionListr);
        } else {
            if (isLoadMore == true && sizeTransactionList > 0) {
                adapter.addData(tripSectionListr);
                adapter.loadMoreComplete();
                isData = false;
            }

        }

        if (adapter.getData().size() < count) {
            adapter.setEnableLoadMore(false);
        } else {
            adapter.setEnableLoadMore(true);
        }

    }
}
