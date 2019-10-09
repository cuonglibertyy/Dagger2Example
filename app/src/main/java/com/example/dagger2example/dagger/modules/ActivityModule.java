package com.example.dagger2example.dagger.modules;

import android.app.Activity;

import com.example.dagger2example.dagger.qualifiers.ActivityContext;

import dagger.Module;
import dagger.Provides;

@Module
public class ActivityModule {

    private Activity activity;

    public ActivityModule(Activity activity) {
        this.activity = activity;
    }

    @Provides
    @ActivityContext
    Activity provideActivity() {
        return activity;
    }
}
