package com.example.dagger2example.data;

import com.example.dagger2example.data.remote.EtranServiceUrl;
import com.example.dagger2example.data.sharepreferences.PreferenHelper;
import com.example.dagger2example.model.history.History;
import com.example.dagger2example.model.login.EtrantJsonResult;
import com.example.dagger2example.model.login.Token;
import com.example.dagger2example.model.login.UserInfo;

import io.reactivex.Observable;
import io.reactivex.Observer;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;

public class DataManager implements EtranServiceUrl, PreferenHelper {

    private EtranServiceUrl etranServiceUrl;
    private PreferenHelper preferenHelper;


    public DataManager(EtranServiceUrl etranServiceUrl, PreferenHelper preferenHelper) {
        this.etranServiceUrl = etranServiceUrl;
        this.preferenHelper = preferenHelper;
    }


    @Override
    public Observable<EtrantJsonResult> LoginUser(RequestBody responseBody) {
        return etranServiceUrl.LoginUser(responseBody);
    }

    @Override
    public Observer<History> GetHistory(String tokenKey, ResponseBody body) {
        return etranServiceUrl.GetHistory(tokenKey, body);
    }

    /////---------------------PreferencesHelper-------------------------/////


    @Override
    public void setDeviceId(String deviceId) {
        preferenHelper.setDeviceId(deviceId);
    }

    @Override
    public String getDeviceId() {
        return preferenHelper.getDeviceId();
    }

    @Override
    public void setLoggedIn(boolean isLoggedIn) {
        preferenHelper.setLoggedIn(isLoggedIn);
    }

    @Override
    public boolean IsLoggedIn() {
        return preferenHelper.IsLoggedIn();
    }

    @Override
    public void setUserInfo(UserInfo userInfo) {
        preferenHelper.setUserInfo(userInfo);
    }

    @Override
    public UserInfo getUserInfo() {
        return preferenHelper.getUserInfo();
    }

    @Override
    public void setToken(Token token) {
        preferenHelper.setToken(token);
    }

    @Override
    public Token getToken() {
        return preferenHelper.getToken();
    }

    @Override
    public void clearDeviceId() {
        preferenHelper.clearDeviceId();
    }

    @Override
    public void clearUserInfo() {
        preferenHelper.clearUserInfo();
    }

    @Override
    public void clearToken() {
        preferenHelper.clearToken();
    }

    public void UpdateUserInfoSharePreference(UserInfo userInfo, Token token, boolean IsLoggin) {
        setUserInfo(userInfo);
        setLoggedIn(IsLoggin);
        setToken(token);
    }

    public void clearAllUser() {
        clearUserInfo();
        clearToken();

    }
}
