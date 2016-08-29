package com.mansoul.hot.common;


/**
 * 通用请求callback
 * Created by Mansoul on 16/8/19.
 */
public interface CommonLoadCallback<T> {
    void onSuccess(T resultBean);

    void onError();
}
