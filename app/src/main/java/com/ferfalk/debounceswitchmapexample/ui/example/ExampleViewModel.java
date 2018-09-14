package com.ferfalk.debounceswitchmapexample.ui.example;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import com.ferfalk.debounceswitchmapexample.app.App;
import com.ferfalk.debounceswitchmapexample.ui.common.viewmodel.BaseViewModel;
import com.ferfalk.debounceswitchmapexample.ui.common.viewmodel.DefaultObserver;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class ExampleViewModel extends BaseViewModel {
    private MutableLiveData<String> searchObservable = new MutableLiveData<>();

    public LiveData<String> getDoSearchObservable() {
        return searchObservable;
    }

    public void setSearchObservable(Observable<String> searchO) {
        addDisposable(
                searchO
                        .distinctUntilChanged()
                        .debounce(300, TimeUnit.MILLISECONDS)
                        .doOnNext(s -> Log.d("doSearch", "debounced"))
                        .switchMap(search ->
                                App.getRepository().doSearch(search)
                                        .subscribeOn(Schedulers.io()))
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(new DefaultObserver<String>() {
                            @Override
                            public void onNext(String response) {
                                searchObservable.setValue(response);
                            }

                            @Override
                            public void onError(Throwable throwable) {
                                super.onError(throwable);
                                errorEvent.setValue(throwable);
                            }
                        })
        );
    }
}
