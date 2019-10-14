package com.example.dagger2example.data;

import com.example.dagger2example.data.remote.EtranServiceUrl;
import com.example.dagger2example.data.sharepreferences.PreferenHelper;
import com.example.dagger2example.model.history.History;
import com.example.dagger2example.model.historydetail.HistoryDetail;
import com.example.dagger2example.model.historydetail.TripPackageDetail;
import com.example.dagger2example.model.login.EtrantJsonResult;
import com.example.dagger2example.model.login.Token;
import com.example.dagger2example.model.login.UserInfo;

import io.reactivex.Observable;
import okhttp3.RequestBody;

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
    public Observable<EtrantJsonResult> getLastStatusDriver(String tokenKey) {
        return etranServiceUrl.getLastStatusDriver(tokenKey);
    }

    @Override
    public Observable<History> getHistory(String tokenKey, RequestBody body) {
        return etranServiceUrl.getHistory(tokenKey,body);
    }

    @Override
    public Observable<HistoryDetail> getHistoryDetail(String tokenKey, RequestBody body) {
        return etranServiceUrl.getHistoryDetail(tokenKey,body);
    }

    @Override
    public Observable<TripPackageDetail> postRatingBar(String tokenKey, RequestBody body) {
        return etranServiceUrl.postRatingBar(tokenKey,body);
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
