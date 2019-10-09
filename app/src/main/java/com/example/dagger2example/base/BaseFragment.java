package com.example.dagger2example.base;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.dagger2example.EtrantInitComponent;
import com.example.dagger2example.R;
import com.example.dagger2example.dagger.components.ActivityComponent;
import com.example.dagger2example.dagger.components.DaggerActivityComponent;
import com.example.dagger2example.dagger.modules.ActivityModule;
import com.novoda.merlin.Bindable;
import com.novoda.merlin.Connectable;
import com.novoda.merlin.Disconnectable;
import com.novoda.merlin.Merlin;
import com.trello.rxlifecycle2.components.support.RxFragment;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import es.dmoral.toasty.Toasty;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public abstract class BaseFragment extends RxFragment {

    protected CompositeDisposable compositeDisposable = new CompositeDisposable();
    protected Context context;
    protected FragmentActivity activity;
    protected AppCompatActivity appCompatActivity;

    private Unbinder unbinder;
    private ActivityComponent activityComponent;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutId(),container,false);
        activity = getSupportActivity();
        context = activity;
        appCompatActivity = (AppCompatActivity) activity;
        return view;
    }



    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        unbinder = ButterKnife.bind(this, view);

        setupComponent();
        attachView();
        initToolbar();
        initDatas();
        configViews();
        addEvents();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.activity = (FragmentActivity) context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        this.activity = null;
    }

    @Override
    public void onDestroyView() {
        unbinder.unbind();
        if (compositeDisposable != null) {
            compositeDisposable.dispose();
        }
        detachView();
        super.onDestroyView();
    }

    protected abstract void setupComponent();

    protected abstract void attachView();

    protected abstract void detachView();

    protected abstract void initToolbar();

    protected abstract void initDatas();

    protected abstract void configViews();

    protected abstract int getLayoutId();

    protected abstract void addEvents();

    protected ActivityComponent getActivityComponent() {
        if (activityComponent == null) {
            activityComponent = DaggerActivityComponent.builder()
                    .activityModule(new ActivityModule(activity))
                    .applicationComponent(EtrantInitComponent.getInstance(context).getComponent())
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


    protected void gone(final View... views) {
        if (views != null && views.length > 0) {
            for (View view : views) {
                if (view != null) {
                    view.setVisibility(View.GONE);
                }
            }
        }
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

    protected boolean isVisible(View view) {
        return view.getVisibility() == View.VISIBLE;
    }

    protected void loadAvatar(String url, ImageView ivAvatar) {
        if (url == null || ivAvatar == null) {
            return;
        }
        Glide.with(context)
                .load(url)
                .placeholder(R.drawable.ic_info_outline_white_24dp)
                .error(R.drawable.ic_info_outline_white_24dp)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .dontTransform()
                .dontAnimate()
                .into(ivAvatar);
    }

    protected void loadFullName(String fullName, TextView tvFullName) {
        if (fullName == null && tvFullName == null) {
            return;
        }
        tvFullName.setText(fullName);
    }

    private FragmentActivity getSupportActivity() {
        return super.getActivity();
    }
}
