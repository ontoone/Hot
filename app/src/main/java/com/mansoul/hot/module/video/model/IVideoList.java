package com.mansoul.hot.module.video.model;

import com.mansoul.hot.common.CommonLoadCallback;
import com.mansoul.hot.module.video.model.bean.VideoListBean;

/**
 * Created by Mansoul on 16/8/29.
 */
public interface IVideoList {
    void getData(int Startage,
                 final CommonLoadCallback<VideoListBean> callback);
}
