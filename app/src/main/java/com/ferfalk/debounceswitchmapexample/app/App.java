package com.ferfalk.debounceswitchmapexample.app;

import android.app.Application;

import com.ferfalk.debounceswitchmapexample.data.Repository;

public class App extends Application {
    private static final String TAG = App.class.getSimpleName();
    private static App instance;

    private Repository repository;

    public static synchronized App getInstance() {
        return instance;
    }

    public static Repository getRepository() {
        if (getInstance().repository == null) {
            getInstance().repository = new Repository();
        }
        return getInstance().repository;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }
}
