package com.example.dagger2example.data.remote;

import com.example.dagger2example.model.geocode.Geocode;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GoogleService {

@GET("geocode/json")
    Observable<Geocode> getChooseLocation(@Query("latlng")String latlng,
                                          @Query("key")String googleApiKey);

    @GET("geocode/json?")
    Observable<Geocode> getChooseLocationPlace_id(@Query("place_id")String place_id,
                                          @Query("key")String googleApiKey);

}
