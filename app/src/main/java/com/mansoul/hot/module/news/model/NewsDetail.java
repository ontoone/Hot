package com.mansoul.hot.module.news.model;

import com.mansoul.hot.common.CommonLoadCallback;
import com.mansoul.hot.http.API;
import com.mansoul.hot.http.manager.HttpManager;
import com.mansoul.hot.http.service.NetService;
import com.mansoul.hot.module.news.model.bean.NewsDetailBean;
import com.orhanobut.logger.Logger;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Mansoul on 16/9/9.
 */
public class NewsDetail {

    public void getData(String newsId, final CommonLoadCallback<NewsDetailBean> callback) {
        Logger.d(newsId);
        String cacheControl = HttpManager.getInstance().getCacheControl();

        NetService service = HttpManager.getInstance().getRetrofit(API.NEWS_BASE_URL);

        service.getNewsDetail(cacheControl, newsId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<NewsDetailBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Logger.d("请求失败");
                        callback.onError();
                    }

                    @Override
                    public void onNext(NewsDetailBean bean) {
                        if (bean != null) {
                            callback.onSuccess(bean);
                        } else {
                            Logger.d("数据为空");
                            callback.onError();
                        }
                    }
                });
    }
}
