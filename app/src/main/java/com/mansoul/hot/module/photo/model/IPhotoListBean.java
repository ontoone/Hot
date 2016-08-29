package com.mansoul.hot.module.photo.model;

import com.mansoul.hot.base.BaseBean;
import com.mansoul.hot.common.CommonLoadCallback;
import com.mansoul.hot.module.photo.model.bean.PhotoListBean;

/**
 * Created by Mansoul on 16/8/11.
 */
public interface IPhotoListBean extends BaseBean {
    PhotoListBean getPhotoList(String count, String page,
                               CommonLoadCallback<PhotoListBean> callback);
}
