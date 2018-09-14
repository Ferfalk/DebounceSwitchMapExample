package com.ferfalk.debounceswitchmapexample.ui.common.viewmodel;

import android.util.Log;

import io.reactivex.observers.DisposableObserver;

/**
 * Default {@link DisposableObserver} base class to be used whenever you want default error handling.
 */
public class DefaultObserver<T> extends DisposableObserver<T> {
    private final String TAG = DefaultObserver.class.getSimpleName();

    @Override
    public void onNext(T t) {
        // no-op by default.
    }

    @Override
    public void onComplete() {
        // no-op by default.
    }

    @Override
    public void onError(Throwable throwable) {
        Log.e(TAG, throwable.getMessage(), throwable);
        // no-op by default.
    }
}