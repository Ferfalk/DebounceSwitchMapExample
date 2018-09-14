package com.ferfalk.debounceswitchmapexample.app;

import android.app.Application;

import com.ferfalk.debounceswitchmapexample.BuildConfig;
import com.ferfalk.debounceswitchmapexample.data.Repository;
import com.ferfalk.debounceswitchmapexample.data.remote.RestApi;
import com.ferfalk.debounceswitchmapexample.data.remote.RetrofitConfig;

public class App extends Application {
    private static final String TAG = App.class.getSimpleName();
    private static App instance;

    private RestApi restApi;
    private Repository repository;

    public static synchronized App getInstance() {
        return instance;
    }

    private static RestApi getRestApi() {
        if (getInstance().restApi == null) {
            getInstance().restApi = RetrofitConfig.getRetrofitBuilder(BuildConfig.URL_LAUNCH_LIBRARY).create(RestApi.class);
        }
        return getInstance().restApi;
    }

    public static Repository getRepository() {
        if (getInstance().repository == null) {
            getInstance().repository = new Repository(getRestApi());
        }
        return getInstance().repository;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }
}
