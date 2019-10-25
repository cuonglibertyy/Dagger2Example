package com.example.dagger2example.dagger.modules;


import android.app.Application;
import android.content.Context;

import com.example.dagger2example.constans.Constans;
import com.example.dagger2example.dagger.qualifiers.ApplicationContext;
import com.example.dagger2example.dagger.qualifiers.EtranUrl;
import com.example.dagger2example.dagger.qualifiers.GoogleUrl;
import com.example.dagger2example.dagger.qualifiers.PreferenceInfo;
import com.example.dagger2example.data.DataManager;
import com.example.dagger2example.data.remote.EtranServiceUrl;
import com.example.dagger2example.data.remote.GoogleService;
import com.example.dagger2example.data.sharepreferences.AppPreferenHelper;
import com.example.dagger2example.data.sharepreferences.PreferenHelper;
import com.facebook.stetho.okhttp3.StethoInterceptor;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class ApplicationModule {

    private Application application;


    public ApplicationModule(Application application) {
        this.application = application;

    }

    @Provides
    @Singleton
    Application provideApplication() {
        return application;
    }

    @Provides
    @ApplicationContext
    Context provideContext() {
        return application;
    }

    @Provides
    @PreferenceInfo
    String providePreferenceName() {
        return Constans.COMMON_PREF_NAME;
    }

    @Provides
    @Singleton
    PreferenHelper providePreferenHelper(AppPreferenHelper appPreferenHelper){
        return appPreferenHelper;
    }



    @Provides
    @Singleton
    HttpLoggingInterceptor httpLoggingInterceptor() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return interceptor;
    }


    @Provides
    @Singleton
    OkHttpClient okHttpClientManager(HttpLoggingInterceptor interceptor) {
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .addNetworkInterceptor(new StethoInterceptor())
                .connectTimeout(50, TimeUnit.SECONDS)
                .readTimeout(50, TimeUnit.SECONDS)
                .writeTimeout(50, TimeUnit.SECONDS)
                .build();
        return client;
    }

    @Provides
    @Singleton
    DataManager provideDataManager(EtranServiceUrl etranServiceUrl, PreferenHelper preferenHelper, GoogleService googleService) {
        return new DataManager(etranServiceUrl, preferenHelper,googleService);
    }

    @Provides
    @Singleton
    GoogleService provideGetGoogleService(@GoogleUrl Retrofit retrofit){
        return retrofit.create(GoogleService.class);
    }

    @Provides
    @Singleton
    EtranServiceUrl provideGetEtranJsonResult(@EtranUrl Retrofit retrofit) {
        return retrofit.create(EtranServiceUrl.class);
    }

    @Provides
    @Singleton
    Retrofit.Builder providerRetrofitBuilder() {
        return new Retrofit.Builder();
    }

    @Provides
    @Singleton
    @EtranUrl
    Retrofit provideGet42Retrofit(Retrofit.Builder builder, OkHttpClient client) {
        return createRetrofit(builder, client, Constans.ETRAN_URL);
    }

    @Provides
    @Singleton
    @GoogleUrl
    Retrofit provieGoogleRetrofit(Retrofit.Builder builder,OkHttpClient client){
        return createRetrofit(builder,client,Constans.GOOGLE_SERVICE_URL);
    }


    private Retrofit createRetrofit(Retrofit.Builder builder, OkHttpClient client, String etranUrl) {
        return builder.client(client)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(etranUrl)
                .build();
    }

}
