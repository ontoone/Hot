package com.mansoul.hot.module.video.view;

import com.mansoul.hot.base.BaseIView;
import com.mansoul.hot.module.video.model.bean.VideoListBean;

/**
 * Created by Mansoul on 16/8/29.
 */
public interface IVideoListView extends BaseIView<VideoListBean> {
    void loadSuccess(VideoListBean data);
}
