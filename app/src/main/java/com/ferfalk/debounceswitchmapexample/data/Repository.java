package com.ferfalk.debounceswitchmapexample.data;

import android.util.Log;

import com.ferfalk.debounceswitchmapexample.data.remote.RestApi;

import io.reactivex.Observable;

public class Repository {
    private final RestApi restApi;

    public Repository(RestApi restApi) {
        this.restApi = restApi;
    }

    public Observable<String> doSearch(String search) {
        return restApi.getNextLaunches(300, search.length(), "verbose")
                .map(nextLaunchesDTO -> nextLaunchesDTO.getLaunches().get(0).getRocket().getName())
                .doOnSubscribe(disposable -> Log.d("doSearch", "subscribed"))
                .doOnDispose(() -> Log.d("doSearch", "disposed"));
    }
}
