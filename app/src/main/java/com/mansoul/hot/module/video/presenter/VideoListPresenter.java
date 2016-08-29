package com.mansoul.hot.module.video.presenter;

import com.mansoul.hot.base.BasePresenter;
import com.mansoul.hot.module.video.model.VideoListImp;
import com.mansoul.hot.module.video.model.bean.VideoListBean;
import com.mansoul.hot.module.video.view.IVideoListView;

/**
 * Created by Mansoul on 16/8/29.
 */
public class VideoListPresenter extends BasePresenter<IVideoListView, VideoListBean> {
    private VideoListImp data;

    public VideoListPresenter(IVideoListView view) {
        super(view);
        data = new VideoListImp();
    }

    public void getVideoList(int starePage) {
        data.getData(starePage, this);
    }
}
