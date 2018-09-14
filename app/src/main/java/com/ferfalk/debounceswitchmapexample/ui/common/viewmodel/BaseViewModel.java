package com.ferfalk.debounceswitchmapexample.ui.common.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;

public class BaseViewModel extends ViewModel {
    protected SingleLiveEvent<Throwable> errorEvent = new SingleLiveEvent<>();

    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Override
    protected void onCleared() {
        super.onCleared();
        compositeDisposable.clear();
    }

    public void addDisposable(DisposableObserver observable) {
        compositeDisposable.add(observable);
    }

    public LiveData<Throwable> getErrorEvent() {
        return errorEvent;
    }
}
