package com.example.dagger2example.data.remote;

import com.example.dagger2example.constans.Constans;
import com.example.dagger2example.model.error.Error;
import com.example.dagger2example.model.history.History;
import com.example.dagger2example.model.historydetail.HistoryDetail;
import com.example.dagger2example.model.historydetail.TripPackageDetail;
import com.example.dagger2example.model.login.EtrantJsonResult;
import com.example.dagger2example.model.typebike.Typebike;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface EtranServiceUrl {

    @POST("user/checkuserexist")
    Observable<EtrantJsonResult> LoginUser(@Body RequestBody responseBody);

    @POST("user/getlaststatus")
    Observable<EtrantJsonResult> getLastStatusDriver(@Header(Constans.VALUE_NAME_TOKEN) String tokenKey);

    @POST("user/getlaststatus")
    Observable<HistoryDetail> getLastStatus(@Header(Constans.VALUE_NAME_TOKEN) String tokenKey);


    @POST("user/getHistoryTrip/v2")
    Observable<History> getHistory(@Header(Constans.VALUE_NAME_TOKEN) String tokenKey,
                                 @Body RequestBody body);

    @POST("user/getHistoryTripDetail/v2")
    Observable<HistoryDetail> getHistoryDetail (@Header(Constans.VALUE_NAME_TOKEN) String tokenKey,
                                                @Body RequestBody body);

    @POST("api-driver/ratingtrippackage")
    Observable<TripPackageDetail> postRatingBar(@Header(Constans.VALUE_NAME_TOKEN) String tokenKey,
                                                @Body RequestBody body);
    @POST("api-trip/findTripByLocation")
    Observable<Error> postMyLocation(@Header(Constans.VALUE_NAME_TOKEN) String tokenKey,
                                     @Body RequestBody body);

    @POST("config/getLstCarType")
    Observable<Typebike> postTypeBike(@Header(Constans.VALUE_NAME_TOKEN)String tokenKey,
                                          @Body RequestBody body);
}
