package com.ferfalk.debounceswitchmapexample.ui.example;

import android.os.Bundle;
import android.util.Log;

import com.ferfalk.debounceswitchmapexample.R;
import com.ferfalk.debounceswitchmapexample.databinding.ActivityExampleBinding;
import com.ferfalk.debounceswitchmapexample.ui.common.BaseActivity;
import com.jakewharton.rxbinding2.view.RxView;
import com.jakewharton.rxbinding2.widget.RxTextView;

import java.util.concurrent.TimeUnit;

import io.reactivex.android.schedulers.AndroidSchedulers;

public class ExampleActivity extends BaseActivity<ActivityExampleBinding, ExampleViewModel> {

    @Override
    public int getLayoutId() {
        return R.layout.activity_example;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        observeViewModel();

        setupSearch();
    }

    private void setupSearch() {
        getViewModel().setSearchObservable(RxTextView.textChanges(getView().editText)
                .debounce(500, TimeUnit.MILLISECONDS)
                .map(CharSequence::toString)
                .doOnNext(s -> Log.d("doSearch", "debounced")));
    }

    private void observeViewModel() {
        getViewModel().getSearchObservable().observe(this, result -> {
            Log.d("doSearch", "result: " + result);
            getView().textView.setText(result);
        });
    }

}
