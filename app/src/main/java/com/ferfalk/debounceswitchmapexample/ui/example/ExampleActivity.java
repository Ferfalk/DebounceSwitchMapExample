package com.ferfalk.debounceswitchmapexample.ui.example;

import android.os.Bundle;
import android.util.Log;

import com.ferfalk.debounceswitchmapexample.R;
import com.ferfalk.debounceswitchmapexample.databinding.ActivityExampleBinding;
import com.ferfalk.debounceswitchmapexample.ui.common.BaseActivity;
import com.jakewharton.rxbinding2.widget.RxTextView;

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
        getViewModel().setSearchObservable(
                RxTextView.textChanges(getView().editText)
                        .map(CharSequence::toString)
        );
    }

    private void observeViewModel() {
        getViewModel().getDoSearchObservable().observe(this, result -> {
            Log.d("doSearch", "result");
            getView().textView.setText(result);
        });
    }

}
