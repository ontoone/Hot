package com.mansoul.hot.base;


/**
 * Created by Mansoul on 16/8/16.
 */
public interface BaseIView<T> {
    //正在加载
    void loading();

    //    //加载完成
//    void loadSuccess(T data);
//
//    void loadSuccess(List<T> data);

    //记载更多
    void loadMore();

    //加载错误
    void loadError();

    //刷新数据
    void refresh();
}
