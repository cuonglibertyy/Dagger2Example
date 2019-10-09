package com.example.dagger2example.data.sharepreferences;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.dagger2example.dagger.qualifiers.ApplicationContext;
import com.example.dagger2example.dagger.qualifiers.PreferenceInfo;
import com.example.dagger2example.model.login.Token;
import com.example.dagger2example.model.login.UserInfo;
import com.google.gson.Gson;

import javax.inject.Inject;
import javax.inject.Singleton;


@Singleton
public class AppPreferenHelper implements PreferenHelper {

    private static final String PREF_KEY_DEVICE_ID = "PREF_KEY_DEVICE_ID";
    private static final String PREF_KEY_USER_INFO = "PREF_KEY_USER_INFO";
    private static final String PREF_KEY_TOKEN_INFO = "PREF_KEY_TOKEN_INFO";
    private static final String PREF_KEY_IS_LOGGED_IN = "PREF_KEY_IS_LOGGED_IN ";

    private SharedPreferences mPrefs;

    @Inject
    AppPreferenHelper(@ApplicationContext Context context, @PreferenceInfo String prefFileName) {
        mPrefs = context.getSharedPreferences(prefFileName, Context.MODE_PRIVATE);
    }

    @Override
    public void setDeviceId(String deviceId) {
        mPrefs.edit().putString(PREF_KEY_DEVICE_ID,deviceId).apply();
    }

    @Override
    public String getDeviceId() {
        return mPrefs.getString(PREF_KEY_DEVICE_ID,"");
    }

    @Override
    public void setLoggedIn(boolean isLoggedIn) {
        mPrefs.edit().putBoolean(PREF_KEY_IS_LOGGED_IN,isLoggedIn).apply();
    }

    @Override
    public boolean IsLoggedIn() {
        return mPrefs.getBoolean(PREF_KEY_IS_LOGGED_IN,false);
    }

    @Override
    public void setUserInfo(UserInfo userInfo) {
        mPrefs.edit().putString(PREF_KEY_USER_INFO,new Gson().toJson(userInfo)).apply();
    }

    @Override
    public UserInfo getUserInfo() {
        return new Gson().fromJson(mPrefs.getString(PREF_KEY_USER_INFO,""),UserInfo.class);
    }

    @Override
    public void setToken(Token token) {
        mPrefs.edit().putString(PREF_KEY_TOKEN_INFO,new Gson().toJson(token)).apply();
    }

    @Override
    public Token getToken() {
        return new Gson().fromJson(mPrefs.getString(PREF_KEY_TOKEN_INFO,""),Token.class);
    }

    @Override
    public void clearDeviceId() {
        mPrefs.edit().remove(PREF_KEY_DEVICE_ID).apply();
    }

    @Override
    public void clearUserInfo() {
            mPrefs.edit().remove(PREF_KEY_USER_INFO).apply();
    }

    @Override
    public void clearToken() {
        mPrefs.edit().remove(PREF_KEY_TOKEN_INFO).apply();
    }
}
