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
//
//
//    public CommonLoadCallback<B> callback = new CommonLoadCallback<B>() {
//        @Override
//        public void onSuccess(B resultBean) {
//            view.loadSuccess(resultBean);
//        }
//
//        @Override
//        public void onError() {
//            view.loadError();
//        }
//    };
//
//    public CommonLoadCallback<List<B>> listCallback = new CommonLoadCallback<List<B>>() {
//        @Override
//        public void onSuccess(List<B> resultBean) {
//            view.loadSuccess(resultBean);
//        }
//
//        @Override
//        public void onError() {
//            view.loadError();
//        }
//    };

//    public void getData(String count, String page) {
//        data.getData(count, page, callback);
//    }

//    public abstract void initData();
}
