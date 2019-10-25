package com.example.dagger2example.base;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.dagger2example.EtrantInitComponent;
import com.example.dagger2example.R;
import com.example.dagger2example.constans.Constans;
import com.example.dagger2example.dagger.components.ActivityComponent;
import com.example.dagger2example.dagger.components.DaggerActivityComponent;
import com.example.dagger2example.dagger.modules.ActivityModule;
import com.example.dagger2example.untils.DateUtils;
import com.example.dagger2example.untils.FormatUtils;
import com.novoda.merlin.Bindable;
import com.novoda.merlin.Connectable;
import com.novoda.merlin.Disconnectable;
import com.novoda.merlin.Merlin;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import org.greenrobot.eventbus.EventBus;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import es.dmoral.toasty.Toasty;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public abstract class BaseActivity extends RxAppCompatActivity {


    CompositeDisposable compositeDisposable;
    private Unbinder unbinder;
    ActivityComponent activityComponent;
    private Merlin merlin;
    protected Context context;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());

        unbinder = ButterKnife.bind(this);
        context = this;

        setupComponent();
        addEvens();
        attachView();
        configViews();
        merlin = initMerlin();



    }

    protected abstract void configViews();


    @Override
    protected void onStart() {
        super.onStart();
        merlin.bind();
    }

    @Override
    public void onStop() {
        if (merlin != null) {
            merlin.unbind();
        }
        super.onStop();
    }
    protected abstract void addEvens();


    protected abstract Merlin initMerlin();

    protected abstract void attachView();

    protected ActivityComponent getActivityComponent(){
        if (activityComponent == null){
            activityComponent = DaggerActivityComponent.builder()
                    .activityModule(new ActivityModule(this))
                    .applicationComponent(EtrantInitComponent.getInstance(this).getComponent())
                    .build();
        }
        return activityComponent;
    }
    protected void addDisposable(Disposable disposable) {
        if (compositeDisposable == null) {
            compositeDisposable = new CompositeDisposable();
        }
        compositeDisposable.add(disposable);
    }
    protected void registerConnectable(Connectable connectable) {
        merlin.registerConnectable(connectable);
    }

    protected void registerDisconnectable(Disconnectable disconnectable) {
        merlin.registerDisconnectable(disconnectable);
    }

    protected void registerBindable(Bindable bindable) {
        merlin.registerBindable(bindable);
    }
    @Override
    protected void onDestroy() {
        if (compositeDisposable != null) {
            compositeDisposable.clear();
        }
        if (unbinder != null) {
            unbinder.unbind();
        }
        detachView();
        super.onDestroy();
    }

    protected abstract void detachView();

    protected abstract void setupComponent();





    protected abstract int getLayoutId();
    public void showErrorToasty(){
        Toasty.error(this,"That bai").show();
    };
    protected void loadAvatar(String url, ImageView ivAvatar) {
        if (url == null || ivAvatar == null) {
            return;
        }
        Glide.with(context)
                .load(url)
                .placeholder(R.drawable.ic_info_outline_white_24dp)
                .error(R.drawable.ic_error_outline_white_24dp)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .dontTransform()
                .dontAnimate()
                .into(ivAvatar);
    }
    protected void loadFullname(String fullName, TextView textView){
        if (textView == null){
            return;
        }
        textView.setText(fullName);
    }
    protected void loadDateTime(long createDated, TextView tvCreateDate) {
        if (tvCreateDate == null) {
            return;
        }
        tvCreateDate.setText(DateUtils.convertMiliToDateTime(createDated));
    }
    protected void loadCreateTime(long createTimed, TextView tvCreateTime) {
        if (tvCreateTime == null) {
            return;
        }
        tvCreateTime.setText(getString(R.string.history_label_create_time, DateUtils.convertMiliToTime(createTimed)));
    }
    protected void loadEstimatedPrice(Double estimatedPrice, TextView tvPrice) {
        if (tvPrice == null) {
            return;
        }
        String estimatedPriceFormatted = FormatUtils.convertEstimatedPrice(estimatedPrice);
        tvPrice.setText(getString(R.string.common_label_estimated_price, estimatedPriceFormatted));
    }
    protected void loadEstimatedDistance(long estimatedDistance,TextView tv_distance){
        if (tv_distance == null){
            return;
        }
        if (estimatedDistance<1000){
            String estimatedDistanceFormattedMetter = FormatUtils.convertEstimatedDistance(estimatedDistance);
            tv_distance.setText(getString(R.string.common_label_estimated_distance_metter,estimatedDistanceFormattedMetter));
        }else{
            Long estimateDistanceKilometter = estimatedDistance/1000;
            String estimateKilomet = FormatUtils.convertEstimatedDistance(estimateDistanceKilometter);
            tv_distance.setText(getString(R.string.common_label_estimated_distance_kilo_metter,estimateKilomet));
        }

    }
    protected void loadEstimatedDuration(long estimatedDuration,TextView tv_duration){
        if (tv_duration == null){
            return;
        }
        if (estimatedDuration>60){
            Long durationminute = estimatedDuration/60;
            String estimatedDurationFormatted = FormatUtils.convertEstimatedDuration(durationminute);
            tv_duration.setText(getString(R.string.common_label_estimated_duration_minute,estimatedDurationFormatted));
        }else if (estimatedDuration>3600){
            Long durationhour = estimatedDuration/3600;
            String estimateHour = FormatUtils.convertEstimatedDuration(durationhour);
            tv_duration.setText(getString(R.string.common_label_estimated_duration_hour,estimateHour));
        }else if (estimatedDuration<60){
            String estimateSecond = FormatUtils.convertEstimatedDuration(estimatedDuration);
            tv_duration.setText(getString(R.string.common_label_estimated_duration_second,estimateSecond));
        }

    }
    protected void loadTripStatus(int tripStatus, TextView tvTripStatus) {
        if (tvTripStatus == null) {
            return;
        }

        if (tripStatus == Constans.TRIP_STATUS_69) {
            tvTripStatus.setBackgroundResource(R.color.history_color_bg_trip_status_ok);
            tvTripStatus.setText(R.string.history_label_trip_status_success);
        } else {
            tvTripStatus.setBackgroundResource(R.color.history_color_bg_trip_status_cancel);
            tvTripStatus.setText(R.string.history_label_trip_status_cancel);
        }
    }
    protected void loadListPickUpPoins(String address,TextView textView){
        if (address == null){
            return;
        }
        textView.setText(address);
    }
    protected void visible(final View... views) {
        if (views != null && views.length > 0) {
            for (View view : views) {
                if (view != null) {
                    view.setVisibility(View.VISIBLE);
                }
            }
        }

    }
}
