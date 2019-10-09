package com.example.dagger2example.data.remote;

import com.example.dagger2example.constans.Constans;
import com.example.dagger2example.model.history.History;
import com.example.dagger2example.model.login.EtrantJsonResult;

import io.reactivex.Observable;
import io.reactivex.Observer;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface EtranServiceUrl {

    @POST("user/checkuserexist")
    Observable<EtrantJsonResult> LoginUser(@Body RequestBody responseBody);


    @POST("user/getHistoryTrip/v2")
    Observer<History> GetHistory(@Header(Constans.VALUE_NAME_TOKEN) String tokenKey,
                                 @Body ResponseBody body);


}
