package com.example.dagger2example;

import android.app.Application;
import android.content.Context;

import androidx.multidex.MultiDex;

import com.example.dagger2example.dagger.components.ApplicationComponent;
import com.example.dagger2example.dagger.components.DaggerApplicationComponent;
import com.example.dagger2example.dagger.modules.ApplicationModule;

public class EtrantInitComponent extends Application {

    ApplicationComponent applicationComponent;


    public static EtrantInitComponent getInstance(Context context) {
        return (EtrantInitComponent) context.getApplicationContext();
    }


    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }


    @Override
    public void onCreate() {
        super.onCreate();
        setupComponent();
    }

    private void setupComponent() {
        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
        applicationComponent.inject(this);
    }

    public ApplicationComponent getComponent() {
        return applicationComponent;
    }
}
