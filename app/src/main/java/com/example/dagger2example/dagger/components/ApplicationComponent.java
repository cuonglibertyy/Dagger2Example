package com.example.dagger2example.dagger.components;


import android.app.Application;
import android.content.Context;

import com.example.dagger2example.EtrantInitComponent;
import com.example.dagger2example.dagger.modules.ApplicationModule;
import com.example.dagger2example.dagger.qualifiers.ApplicationContext;
import com.example.dagger2example.data.DataManager;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ApplicationModule.class})

public interface ApplicationComponent {

    void inject(EtrantInitComponent application);


    @ApplicationContext
    Context context();


    Application application();

    DataManager getDataManager();
}
