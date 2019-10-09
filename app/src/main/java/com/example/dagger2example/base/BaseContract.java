package com.example.dagger2example.base;

import androidx.annotation.StringRes;

import com.example.dagger2example.model.login.Results;

public interface BaseContract {
    interface BaseView {

        void showProgress(boolean show);

        void showError(@StringRes int stringResId);

        void showError();

        void onComplete(Results results);
    }

    interface BasePresenter<T> {
        void attachView(T view);

        void detachView();
    }
}
