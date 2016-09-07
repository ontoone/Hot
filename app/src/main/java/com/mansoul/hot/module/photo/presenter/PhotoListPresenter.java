package com.mansoul.hot.module.photo.presenter;

import com.mansoul.hot.common.CommonLoadCallback;
import com.mansoul.hot.module.photo.model.IPhotoListBean;
import com.mansoul.hot.module.photo.model.PhotoListBeanImp;
import com.mansoul.hot.module.photo.model.bean.PhotoListBean;
import com.mansoul.hot.module.photo.view.IPhotoListView;

/**
 * Created by Mansoul on 16/8/11.
 */
public class PhotoListPresenter implements CommonLoadCallback<PhotoListBean> {

    private IPhotoListBean data;
    private IPhotoListView mView;

    public PhotoListPresenter(IPhotoListView view) {
        mView = view;
        data = new PhotoListBeanImp();

    }

    public void getPhotoList(String count, String page) {
        data.getPhotoList(count, page, this);
    }

    @Override
    public void onSuccess(PhotoListBean resultBean) {
        mView.loadSuccess(resultBean);
    }

    @Override
    public void onError() {
        mView.loadError();
    }
}
