package com.mansoul.hot.module.news.model;

import com.mansoul.hot.common.CommonLoadCallback;
import com.mansoul.hot.http.API;
import com.mansoul.hot.http.manager.HttpManager;
import com.mansoul.hot.http.service.NetService;
import com.mansoul.hot.module.news.model.bean.NewsListBean;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Mansoul on 16/8/22.
 */
public class NewsListImp implements INewsList {
    @Override
    public NewsListBean getData(String type, int page,
                                final CommonLoadCallback<NewsListBean> callback) {

        String cacheControl = HttpManager.getInstance().getCacheControl();

        NetService service = HttpManager.getInstance().getRetrofit(API.NEWS_BASE_URL);

        service.getNewsList(cacheControl, type, page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<NewsListBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onError();
                    }

                    @Override
                    public void onNext(NewsListBean newsListBean) {
                        callback.onSuccess(newsListBean);
                    }
                });

        return null;
    }
}
