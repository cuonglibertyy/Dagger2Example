package com.example.dagger2example.data.sharepreferences;

import com.example.dagger2example.model.login.Token;
import com.example.dagger2example.model.login.UserInfo;

public interface PreferenHelper {

    void setDeviceId(String deviceId);

    String getDeviceId();

    void setLoggedIn(boolean isLoggedIn);

    boolean IsLoggedIn();

    void setUserInfo(UserInfo userInfo);

    UserInfo getUserInfo();

    void setToken(Token token);

    Token getToken();

    void clearDeviceId();

    void clearUserInfo();

    void clearToken();
}
