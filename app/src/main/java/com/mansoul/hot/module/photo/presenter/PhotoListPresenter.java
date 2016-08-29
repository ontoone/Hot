package com.mansoul.hot.module.photo.presenter;

import com.mansoul.hot.base.BasePresenter;
import com.mansoul.hot.module.photo.model.IPhotoListBean;
import com.mansoul.hot.module.photo.model.PhotoListBeanImp;
import com.mansoul.hot.module.photo.model.bean.PhotoListBean;
import com.mansoul.hot.module.photo.view.IPhotoListView;

/**
 * Created by Mansoul on 16/8/11.
 */
public class PhotoListPresenter extends BasePresenter<IPhotoListView, PhotoListBean> {

    private IPhotoListBean data;

    public PhotoListPresenter(IPhotoListView view) {
        super(view);
        data = new PhotoListBeanImp();

    }

    public void getPhotoList(String count, String page) {
        data.getPhotoList(count, page, this);
    }
}
