package com.mansoul.hot.module.video.presenter;

import com.mansoul.hot.common.CommonLoadCallback;
import com.mansoul.hot.module.video.model.VideoListImp;
import com.mansoul.hot.module.video.model.bean.VideoListBean;
import com.mansoul.hot.module.video.view.IVideoListView;

/**
 * Created by Mansoul on 16/8/29.
 */
public class VideoListPresenter implements CommonLoadCallback<VideoListBean> {
    private VideoListImp data;
    protected IVideoListView view;

    public VideoListPresenter(IVideoListView view) {
        this.view = view;
        data = new VideoListImp();
    }

    public void getVideoList(int starePage) {
        data.getData(starePage, this);
    }

    @Override
    public void onSuccess(VideoListBean resultBean) {
        view.loadSuccess(resultBean);
    }

    @Override
    public void onError() {
        view.loadError();
    }
}
