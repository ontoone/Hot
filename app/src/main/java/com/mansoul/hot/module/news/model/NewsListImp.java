package com.mansoul.hot.module.news.model;

import com.mansoul.hot.http.API;
import com.mansoul.hot.http.manager.HttpManager;
import com.mansoul.hot.http.service.NetService;
import com.mansoul.hot.module.news.model.bean.NewsListBean;

import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.functions.Func2;
import rx.schedulers.Schedulers;

/**
 * Created by Mansoul on 16/8/22.
 */
public class NewsListImp implements INewsList {

    @Override
    public NewsListBean getData(String type, int page, final CallBack callback) {
        String cacheControl = HttpManager.getInstance().getCacheControl();

        NetService service = HttpManager.getInstance().getRetrofit(API.NEWS_BASE_URL);

        service.getNewsList(cacheControl, type, page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap(new Func1<NewsListBean, Observable<NewsListBean.bean>>() {
                    @Override
                    public Observable<NewsListBean.bean> call(NewsListBean bean) {
                        return Observable.from(bean.T1348647909107);
                    }
                })
                .toSortedList(new Func2<NewsListBean.bean, NewsListBean.bean, Integer>() {
                    @Override
                    public Integer call(NewsListBean.bean bean, NewsListBean.bean bean2) {
                        return bean2.lmodify.compareTo(bean.lmodify);

                    }
                })
                .subscribe(new Subscriber<List<NewsListBean.bean>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onError();
                    }

                    @Override
                    public void onNext(List<NewsListBean.bean> been) {
                        callback.onSuccess(been);
                    }
                });
        return null;
    }
}
