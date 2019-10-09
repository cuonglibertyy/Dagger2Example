package com.example.dagger2example.dagger.qualifiers;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Qualifier;

@Qualifier
@Documented
@Retention(value = RetentionPolicy.RUNTIME)
public @interface EtranUrl {
}
