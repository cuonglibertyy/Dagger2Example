package com.example.dagger2example.ui.history.historydetail;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.LayerDrawable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import com.example.dagger2example.R;
import com.example.dagger2example.base.BaseActivity;
import com.example.dagger2example.constans.Constans;
import com.example.dagger2example.model.history.TripPackage;
import com.example.dagger2example.model.historydetail.HistoryDetail;
import com.example.dagger2example.model.historydetail.ListPickUpPoint;
import com.example.dagger2example.model.historydetail.Trip;
import com.example.dagger2example.model.historydetail.TripPackageDetail;
import com.example.dagger2example.model.historydetail.User;
import com.example.dagger2example.model.login.Results;
import com.example.dagger2example.widget.LoadingDialog;
import com.jakewharton.rxbinding3.view.RxView;
import com.novoda.merlin.Merlin;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import butterknife.BindView;
import es.dmoral.toasty.Toasty;

public class HistoryDetailActivity extends BaseActivity implements HistoryDetailContract.View {

    @BindView(R.id.activity_history_detail_toolbar)
    Toolbar toolbar;
    @BindView(R.id.activity_history_detail_iv_back)
    ImageView ivBack;
    @BindView(R.id.activity_history_detail_tv_create_date)
    TextView tvCreateDate;
    @BindView(R.id.history_detail_iv_avatar_user)
    ImageView ivAvatarDriver;
    @BindView(R.id.history_detail_tv_full_name_user)
    TextView tvFullNameDriver;
    @BindView(R.id.activity_history_detail_tv_estimated_price)
    TextView tvEstimatedPrice;
    @BindView(R.id.activity_history_detail_tv_create_time)
    TextView tvCreateTime;
    @BindView(R.id.activity_history_detail_tv_trip_status)
    TextView tvTripStatus;
    @BindView(R.id.activity_history_detail_tv_pickup_point)
    TextView tvPickupPoint;
    @BindView(R.id.activity_history_detail_tv_drof_off_one)
    TextView tvDrofOffOne;
    @BindView(R.id.activity_history_detail_tv_drof_off_two)
    TextView tvDrofOffTwo;
    @BindView(R.id.activity_history_detail_iv_marker_drof_off_one)
    ImageView ivMarkerDrofOffOne;
    @BindView(R.id.activity_history_detail_iv_marker_drof_off_two)
    ImageView ivMarkerDrofOffTwo;
    @BindView(R.id.activity_history_detail_view_divider_one)
    View viewDividerOne;
    @BindView(R.id.activity_history_detail_view_divider_two)
    View viewDividerTwo;
    @BindView(R.id.activity_history_detail_iv_dot_one)
    ImageView ivDotOne;
    @BindView(R.id.activity_history_detail_iv_dot_two)
    ImageView ivDotTwo;
    @BindView(R.id.tv_rating)
    TextView tv_rating;
    @BindView(R.id.ratingBar)
    RatingBar ratingBar;
    @BindView(R.id.btn_vote)
    Button btn_vote;

    private float rating  ;

    @Inject
    HistoryDetailPresenter historyDetailPresenter;

    private String tripId;
    private Results results;


    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }


    public static void startActivity(Context context, String tripId) {
        context.startActivity(new Intent(context, HistoryDetailActivity.class)
                .putExtra("EXTRA_TRIP_ID", tripId));
    }

    @Override
    protected void configViews() {
        if (getIntent() != null) {
            tripId = getIntent().getStringExtra("EXTRA_TRIP_ID");
            Log.d("âfwafafa", "configViews: " + tripId);
        }
        historyDetailPresenter.getHistoryDetail(tripId);

    }

    @Override
    protected void addEvens() {

        showRating();
        addDisposable(RxView.clicks(ivBack)
                .throttleFirst(2, TimeUnit.SECONDS)
                .compose(bindToLifecycle())
                .subscribe(aVoid -> {
                    onBackPressed();
                    finish();
                }));

        addDisposable(RxView.clicks(btn_vote)
                .throttleFirst(2, TimeUnit.SECONDS)
                .compose(bindToLifecycle())
                .subscribe(avoid -> {
                    rating = ratingBar.getRating();
                    Log.d("đâssads", "addEvens: "+rating);
                    historyDetailPresenter.getRatingBar(tripId, rating);
//            Toast.makeText(context, rating, Toast.LENGTH_SHORT).show();
                    onBackPressed();
                    finish();

                }));
    }

    private void showRating() {
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                if (rating == 0.0) {
                    tv_rating.setText("Chưa đánh giá");
                    btn_vote.setClickable(false);
                    btn_vote.setVisibility(View.GONE);
                    return;

                } else if (rating == 1.0) {
                    btn_vote.setClickable(true);
                    btn_vote.setVisibility(View.VISIBLE);
                    tv_rating.setText("Rất tệ");
                    return;
                } else if (rating == 2.0) {
                    tv_rating.setText("Chưa tốt");
                    btn_vote.setVisibility(View.VISIBLE);
                    btn_vote.setClickable(true);
                    return;
                } else if (rating == 3.0) {
                    tv_rating.setText("Tiêu chuẩn");
                    btn_vote.setVisibility(View.VISIBLE);
                    btn_vote.setClickable(true);
                    return;
                } else if (rating == 4.0) {
                    tv_rating.setText("Tốt");
                    btn_vote.setVisibility(View.VISIBLE);
                    btn_vote.setClickable(true);
                    return;
                } else if (rating == 5.0) {
                    tv_rating.setText("Rất tốt");
                    btn_vote.setVisibility(View.VISIBLE);
                    btn_vote.setClickable(true);
                    return;
                }
            }
        });
    }


    @Override
    protected Merlin initMerlin() {
        return new Merlin.Builder()
                .withConnectableCallbacks()
                .withBindableCallbacks()
                .withDisconnectableCallbacks()
                .build(this);
    }

    @Override
    protected void attachView() {
        historyDetailPresenter.attachView(this);
    }

    @Override
    protected void detachView() {
        historyDetailPresenter.detachView();
    }

    @Override
    protected void setupComponent() {
        getActivityComponent().inject(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_history_detail;
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
        Toast.makeText(context, "Mời bạn đánh giá tài xế ", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showSuccessful() {
        Toasty.success(this, Constans.RATING_SUCCESSFUL, Toasty.LENGTH_SHORT).show();
    }

    @Override
    public void onComplete(Results results) {

    }

    @Override
    public void showHistory(com.example.dagger2example.model.historydetail.Results results) {
        showProgress(false);
        ratingBar.setRating((float) results.getTripPackageDetail().getRatingTrip());
        if (results == null) {
            showError();
            return;
        }
        User user = results.getUser();
        Log.d("sadsasad", "showHistory: " + user);
        if (user == null) {
            showError();
            return;
        }
        loadAvatar(user.getAvatar(), ivAvatarDriver);
        loadFullname(user.getFullName(), tvFullNameDriver);

        Trip trip = results.getTrip();
        if (trip == null) {
            showError();
            return;
        }
        loadCreateTime(trip.getCreatedDate(), tvCreateTime);
        loadDateTime(trip.getCreatedDate(), tvCreateDate);
        loadEstimatedPrice(trip.getEstimatedPrice(), tvEstimatedPrice);
        loadTripStatus(trip.getTripStatus(), tvTripStatus);

        List<ListPickUpPoint> listPickUpPoints = trip.getListPickUpPoint();
        int sizeListPickUpPoins = listPickUpPoints.size();
        if (sizeListPickUpPoins == 1) {
            loadListPickUpPoins(trip.getListPickUpPoint().get(0).getAddress(), tvPickupPoint);
        } else if (sizeListPickUpPoins == 2) {
            loadListPickUpPoins(trip.getListPickUpPoint().get(0).getAddress(), tvPickupPoint);
            loadListPickUpPoins(trip.getListPickUpPoint().get(1).getAddress(), tvDrofOffOne);
            visible(ivDotOne, ivMarkerDrofOffOne, tvDrofOffOne, viewDividerOne);
        } else if (sizeListPickUpPoins == 3) {
            loadListPickUpPoins(trip.getListPickUpPoint().get(0).getAddress(), tvPickupPoint);
            loadListPickUpPoins(trip.getListPickUpPoint().get(1).getAddress(), tvDrofOffOne);
            loadListPickUpPoins(trip.getListPickUpPoint().get(2).getAddress(), tvDrofOffTwo);
            visible(ivDotOne, ivMarkerDrofOffOne, tvDrofOffOne, viewDividerOne, tvDrofOffTwo, ivDotTwo, ivMarkerDrofOffTwo, viewDividerTwo);
        }

    }


}
