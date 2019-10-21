package com.example.dagger2example.ui.home;

import android.os.Bundle;
import android.view.View;

import com.example.dagger2example.R;
import com.example.dagger2example.base.BaseFragment;
import com.example.dagger2example.ui.bookcar.activity.BookCarActivity;

import butterknife.BindView;
import de.hdodenhof.circleimageview.CircleImageView;

public class HomeFragment extends BaseFragment  {

    @BindView(R.id.img_bookbike)
    CircleImageView img_bookbike;


    public static HomeFragment newInstance() {
        Bundle args = new Bundle();
        HomeFragment fragment = new HomeFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    protected void setupComponent() {
        getActivityComponent().inject(this);
    }

    @Override
    protected void attachView() {

    }

    @Override
    protected void detachView() {

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
    protected int getLayoutId() {
        return R.layout.home_fragment;
    }

    @Override
    protected void addEvents() {
            img_bookbike.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                BookCarActivity.startActivity(context);
                }
            });
    }
}
