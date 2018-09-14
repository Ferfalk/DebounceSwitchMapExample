package com.ferfalk.debounceswitchmapexample.data;

import android.util.Log;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;

public class Repository {

    public Observable<String> doSearch(String search) {
        return Observable.fromCallable(() -> "You searched for: " + search)
                .map(s -> {
                    Log.d("doSearch", "Thread: " + Thread.currentThread().getName());
                    return s;
                })
                .doOnSubscribe(disposable -> Log.d("doSearch", "subscribed"))
                .doOnDispose(() -> Log.d("doSearch", "disposed"))
                .delay(3, TimeUnit.SECONDS);
    }
}
