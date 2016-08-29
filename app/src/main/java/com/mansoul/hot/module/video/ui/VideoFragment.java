package com.mansoul.hot.module.video.ui;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.mansoul.hot.R;
import com.mansoul.hot.base.BaseFragment;
import com.mansoul.hot.module.video.model.bean.VideoListBean;
import com.mansoul.hot.module.video.presenter.VideoListPresenter;
import com.mansoul.hot.module.video.ui.adapter.VideoAdapter;
import com.mansoul.hot.module.video.view.IVideoListView;
import com.mansoul.hot.util.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


/**
 * Created by Mansoul on 16/8/13.
 */
public class VideoFragment extends BaseFragment implements XRecyclerView.LoadingListener, IVideoListView {
    @BindView(R.id.xRecycleView)
    XRecyclerView mRecyclerView;
    @BindView(R.id.progress_bar)
    ProgressBar mProgressBar;
    @BindView(R.id.tv_error)
    TextView mTvError;
    @BindView(R.id.fl_main)
    FrameLayout mFlMain;

    private boolean isLoadMore = false;
    private boolean isRefresh = false;

    private List<VideoListBean.V9LG4B3A0Bean> mResults;
    private VideoAdapter mAdapter;
    private String first;

    private VideoListPresenter presenter;


    private int startPage = 0;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_video;
    }

    @Override
    public String getToolbarTitle() {
        return "视频";
    }

    @Override
    protected void initView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(mActivity);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);

        mRecyclerView.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        mRecyclerView.setLoadingMoreProgressStyle(ProgressStyle.BallRotate);
        mRecyclerView.setArrowImageView(R.drawable.iconfont_downgrey);

        mRecyclerView.setLoadingListener(this);
    }

    @Override
    protected void initData() {
        presenter = new VideoListPresenter(this);

        presenter.getVideoList(startPage);

        mResults = new ArrayList<>();
    }

    @Override
    public void loading() {
        mProgressBar.setVisibility(View.VISIBLE);
        mRecyclerView.setVisibility(View.GONE);
    }


    @Override
    public void loadSuccess(VideoListBean data) {
        if (isRefresh) {
            isRefresh = false;
            mRecyclerView.refreshComplete();

            if (!data.V9LG4B3A0.get(0).title.equals(first)) {
                mResults.clear();
                mResults.addAll(data.V9LG4B3A0);
                mAdapter.notifyDataSetChanged();
            } else {
                ToastUtil.showShort(mContext, "当前为最新数据");
            }

        } else if (isLoadMore) {
            isLoadMore = false;
            mRecyclerView.loadMoreComplete();

            mResults.addAll(data.V9LG4B3A0);
            mAdapter.notifyDataSetChanged();
        } else {
            mProgressBar.setVisibility(View.GONE);
            mRecyclerView.setVisibility(View.VISIBLE);

            mResults.addAll(data.V9LG4B3A0);
            mAdapter = new VideoAdapter(mResults, this);
            mRecyclerView.setAdapter(mAdapter);

            first = data.V9LG4B3A0.get(0).title;
            isEmpty = false;

        }
    }

    @Override
    public void onRefresh() {
        isRefresh = true;
        presenter.getVideoList(0);
    }

    @Override
    public void onLoadMore() {
        isLoadMore = true;
        startPage += 1;
        presenter.getVideoList(startPage);
    }

    @Override
    public void loadError() {
        if (isLoadMore) {   //加载更多失败
            isLoadMore = false;
            mRecyclerView.loadMoreComplete();
        } else if (isRefresh) {  //下拉刷新失败
            isRefresh = false;
            mRecyclerView.refreshComplete();
        } else if (isEmpty) {    //第一次进入页面加载失败
            mProgressBar.setVisibility(View.GONE);
            mTvError.setVisibility(View.VISIBLE);
            mFlMain.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mTvError.setVisibility(View.GONE);
                    fetchData();
                }
            });
        }
        //页面不为空白，提示用户
        if (!isEmpty) {
            ToastUtil.showError(mContext);
        }
    }


    //下面用不到
    ////////////////////////////////////////////////////////////////////////////
    @Override
    public void loadMore() {

    }

    @Override
    public void refresh() {

    }
}
