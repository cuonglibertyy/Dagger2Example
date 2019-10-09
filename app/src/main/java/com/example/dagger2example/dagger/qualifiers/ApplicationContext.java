package com.example.dagger2example.dagger.qualifiers;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Qualifier;
import javax.inject.Singleton;

import dagger.Provides;

@Qualifier
@Retention(RetentionPolicy.RUNTIME)
public @interface ApplicationContext {

}
