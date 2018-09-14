package com.ferfalk.debounceswitchmapexample.ui.common;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;

import com.ferfalk.debounceswitchmapexample.R;
import com.ferfalk.debounceswitchmapexample.ui.common.viewmodel.BaseViewModel;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public abstract class BaseActivity<T extends ViewDataBinding, V extends BaseViewModel> extends AppCompatActivity {
    static {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }

    private T mViewDataBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        performDataBinding();
        observeForErrors();
    }

    @Override
    public void onBackPressed() {
        supportFinishAfterTransition();
    }

    private void observeForErrors() {
        getViewModel().getErrorEvent().observe(this, this::onError);
    }

    private void performDataBinding() {
        mViewDataBinding = DataBindingUtil.setContentView(this, getLayoutId());
        mViewDataBinding.executePendingBindings();
    }

    /**
     * @return layout resource id
     */
    public abstract
    @LayoutRes
    int getLayoutId();

    public V getViewModel() {
        final Type[] types = ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments();
        return ViewModelProviders.of(this).get((Class<V>) types[1]);
    }

    public T getView() {
        return mViewDataBinding;
    }

    public void showSnack(@NonNull String message) {
        Snackbar.make(mViewDataBinding.getRoot(), message, Snackbar.LENGTH_LONG)
                .show();
    }

    public String getActivityTag() {
        return this.getClass().getSimpleName();
    }

    public void onError(Throwable throwable) {
        showSnack(getString(R.string.error_unexpected));
    }
}
