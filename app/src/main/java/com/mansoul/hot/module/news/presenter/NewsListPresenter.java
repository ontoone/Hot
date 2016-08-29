package com.mansoul.hot.module.news.presenter;

import com.mansoul.hot.common.CommonLoadCallback;
import com.mansoul.hot.module.news.model.INewsList;
import com.mansoul.hot.module.news.model.NewsListImp;
import com.mansoul.hot.module.news.model.bean.NewsListBean;
import com.mansoul.hot.module.news.view.INewsView;

import java.util.List;

/**
 * Created by Mansoul on 16/8/22.
 */
public class NewsListPresenter {

    private INewsList data;
    private INewsView view;

    public NewsListPresenter(INewsView view) {
        this.data = new NewsListImp();
        this.view = view;
    }

    private CommonLoadCallback<NewsListBean> callback = new CommonLoadCallback<NewsListBean>() {
        @Override
        public void onSuccess(NewsListBean resultBean) {
            view.loadSuccess(resultBean);
        }

        @Override
        public void onError() {
            view.loadError();
        }
    };

    public void getData(String type, int page) {
        data.getData(type, page, callback);
    }
}
