package com.mansoul.hot.module.news.presenter;

import com.mansoul.hot.common.CommonLoadCallback;
import com.mansoul.hot.module.news.model.NewsDetail;
import com.mansoul.hot.module.news.model.bean.NewsDetailBean;
import com.mansoul.hot.module.news.view.INewsDetailView;

/**
 * Created by Mansoul on 16/9/9.
 */
public class NewsDetailPresenter {
    private NewsDetail data;
    private INewsDetailView view;

    public NewsDetailPresenter(INewsDetailView view) {
        this.data = new NewsDetail();
        this.view = view;
    }

    private CommonLoadCallback<NewsDetailBean> callback = new CommonLoadCallback<NewsDetailBean>() {
        @Override
        public void onSuccess(NewsDetailBean resultBean) {
            view.loadSuccess(resultBean);
        }

        @Override
        public void onError() {
            view.loadError();
        }
    };

    public void getData(String newsId) {
        data.getData(newsId, callback);
    }
}
