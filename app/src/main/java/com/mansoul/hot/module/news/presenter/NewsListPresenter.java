package com.mansoul.hot.module.news.presenter;

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

    private INewsList.CallBack callback = new INewsList.CallBack() {
        @Override
        public void onSuccess(List<NewsListBean.bean> result) {
            view.loadSuccess(result);
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
