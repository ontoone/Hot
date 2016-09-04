package com.mansoul.hot.base.mvp;

/**
 * Created by Mansoul on 16/9/4.
 */
public interface MvpView {
    /**
     * 发生错误
     *
     * @param e
     */
    void onFailure(Throwable e);
}
