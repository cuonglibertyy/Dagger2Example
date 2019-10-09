package com.example.dagger2example.base;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;

import com.example.dagger2example.EtrantInitComponent;
import com.example.dagger2example.dagger.components.ActivityComponent;
import com.example.dagger2example.dagger.components.DaggerActivityComponent;
import com.example.dagger2example.dagger.modules.ActivityModule;
import com.novoda.merlin.Bindable;
import com.novoda.merlin.Connectable;
import com.novoda.merlin.Disconnectable;
import com.novoda.merlin.Merlin;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

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

}
