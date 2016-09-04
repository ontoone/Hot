package com.mansoul.hot.base.mvp;

/**
 * Created by Mansoul on 16/9/4.
 */
public interface MvpPresenter<V extends MvpView> {
    void attachView(V mvpView);

    void detachView();

}
