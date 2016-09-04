package com.mansoul.hot.base;

import com.mansoul.hot.common.CommonLoadCallback;

import java.util.List;

/**
 * Created by Mansoul on 16/8/20.
 */
public class BasePresenter<T extends BaseIView, V> implements CommonLoadCallback<V> {
    protected T view;

    public BasePresenter(T view) {
        this.view = view;
    }

    @Override
    public void onSuccess(V resultBean) {
        view.loadSuccess(resultBean);
    }

    @Override
    public void onError() {
        view.loadError();
    }

}
