package com.ferfalk.debounceswitchmapexample.data.remote;

import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.ferfalk.debounceswitchmapexample.BuildConfig;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitConfig {

    private RetrofitConfig() {
    }

    private static OkHttpClient getOkHttpClient() {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder()
                .addInterceptor(RetrofitConfig::requestInterceptor);

        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            httpClient.addInterceptor(loggingInterceptor);

            httpClient.addNetworkInterceptor(new StethoInterceptor());
        }
        return httpClient.build();
    }


    private static Response requestInterceptor(Interceptor.Chain chain) throws IOException {
        final String CONTENT_TYPE = "Content-Type";
        final String CONTENT_TYPE_JSON = "application/json";

        Request.Builder requestBuilder = chain.request().newBuilder();
        requestBuilder.header(CONTENT_TYPE, CONTENT_TYPE_JSON);

        return chain.proceed(requestBuilder.build());
    }


    public static Retrofit getRetrofitBuilder(String url) {
        return new Retrofit.Builder()
                .baseUrl(url)
                .client(getOkHttpClient())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}
