package com.mansoul.hot.module.video.model;


import com.mansoul.hot.common.CommonLoadCallback;
import com.mansoul.hot.http.API;
import com.mansoul.hot.http.manager.HttpManager;
import com.mansoul.hot.http.service.NetService;
import com.mansoul.hot.module.news.model.bean.NewsListBean;
import com.mansoul.hot.module.video.model.bean.VideoListBean;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Mansoul on 16/8/29.
 */
public class VideoListImp implements IVideoList {
    @Override
    public void getData(int startPage, final CommonLoadCallback<VideoListBean> callback) {
        String cacheControl = HttpManager.getInstance().getCacheControl();

        NetService service = HttpManager.getInstance().getRetrofit(API.VIDEO_BASE_URL);

        service.getVideoList(cacheControl, startPage)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<VideoListBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onError();
                    }

                    @Override
                    public void onNext(VideoListBean videoListBean) {
                        callback.onSuccess(videoListBean);
                    }
                });
    }
}
