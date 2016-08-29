package com.mansoul.hot.module.photo.model;

import com.mansoul.hot.common.CommonLoadCallback;
import com.mansoul.hot.http.API;
import com.mansoul.hot.http.manager.HttpManager;
import com.mansoul.hot.http.service.NetService;
import com.mansoul.hot.module.photo.model.bean.PhotoListBean;
import com.orhanobut.logger.Logger;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Mansoul on 16/8/11.
 */
public class PhotoListBeanImp implements IPhotoListBean {

    @Override
    public PhotoListBean getPhotoList(String count, String page, final CommonLoadCallback<PhotoListBean> callback) {
        String cacheControl = HttpManager.getInstance().getCacheControl();

        NetService service = HttpManager.getInstance().getRetrofit(API.PHOTO_BASE_URL);


        service.getPhotoList(cacheControl, count, page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<PhotoListBean>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        Logger.d(e.getMessage());
                        callback.onError();
                    }

                    @Override
                    public void onNext(PhotoListBean photoListBean) {
                        callback.onSuccess(photoListBean);
                    }
                });
        return null;
    }

}
