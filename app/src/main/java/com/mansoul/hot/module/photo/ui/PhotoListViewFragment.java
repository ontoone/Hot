package com.mansoul.hot.module.photo.ui;

import android.content.Intent;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.mansoul.hot.R;
import com.mansoul.hot.base.BaseFragment;
import com.mansoul.hot.module.photo.model.bean.PhotoListBean;
import com.mansoul.hot.module.photo.presenter.PhotoListPresenter;
import com.mansoul.hot.module.photo.ui.adapter.PhotoListAdapter;
import com.mansoul.hot.module.photo.view.IPhotoListView;
import com.mansoul.hot.util.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 照片
 * Created by Mansoul on 16/8/11.
 */
public class PhotoListViewFragment extends BaseFragment implements IPhotoListView, SwipeRefreshLayout.OnRefreshListener, PhotoListAdapter.OnClickListener {

    @BindView(R.id.progress_bar)
    ProgressBar mProgressBar;
    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout mRefreshLayout;
    @BindView(R.id.recycle_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.fl_main)
    FrameLayout mFrameLayout;
    @BindView(R.id.tv_error)
    TextView mTextView;

    private String count = "20";//每页显示20条数据
    private int page = 1; //页数,默认加载第一页

    private PhotoListPresenter presenter;
    private PhotoListAdapter mAdapter;

    private List<PhotoListBean.ResultsBean> mResults = new ArrayList<>();
    private String firstUrl;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_photo_list_view;
    }

    @Override
    public String getToolbarTitle() {
        return "妹纸";
    }

    @Override
    protected void initView() {
        loading();
        mRefreshLayout.setColorSchemeResources(R.color.colorAccent);
        mRefreshLayout.setOnRefreshListener(this);

        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                int lastPosition;

                StaggeredGridLayoutManager layoutManager = (StaggeredGridLayoutManager) recyclerView.getLayoutManager();
                int itemCount = layoutManager.getItemCount();

                //因为StaggeredGridLayoutManager的特殊性可能导致最后显示的item存在多个，
                //所以这里取到的是一个数组得到这个数组后再取到数组中position值最大的那个就是
                //最后显示的position值了
                int[] lastPositions = new int[layoutManager.getSpanCount()];
                layoutManager.findLastVisibleItemPositions(lastPositions);

                int max = lastPositions[0];
                for (int value : lastPositions) {
                    if (value > max) {
                        max = value;
                    }
                }
                lastPosition = max;

                if (lastPosition == itemCount - 1 && !isLoadMore) {
                    isLoadMore = true;
                    loadMore();
                    mRefreshLayout.setRefreshing(true);
                }
            }
        });
    }

    @Override
    protected void initData() {
        presenter = new PhotoListPresenter(this);
        presenter.getPhotoList(count, String.valueOf(page));
    }

    @Override
    public void loading() {
        mProgressBar.setVisibility(View.VISIBLE);
        mRefreshLayout.setVisibility(View.GONE);
        mRecyclerView.setVisibility(View.GONE);
    }

    private boolean isLoadMore = false;

    //加载更多
    @Override
    public void loadMore() {
//        if (mResults != null && mResults.size() > 0) {
//            mResults.add(null);
//            mAdapter.notifyItemInserted(mResults.size() - 1);
//        }
        page += 1;
        presenter.getPhotoList(count, String.valueOf(page));
    }

    @Override
    public void loadError() {
        if (isLoadMore) {   //加载更多失败
            isLoadMore = false;
            page -= 1;
            mRefreshLayout.setRefreshing(false);
        } else if (isRefresh) {  //下拉刷新失败
            isRefresh = false;
            mRefreshLayout.setRefreshing(false);
        } else if (isEmpty) {    //第一次进入页面加载失败
            mProgressBar.setVisibility(View.GONE);
            mTextView.setVisibility(View.VISIBLE);
            mFrameLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mTextView.setVisibility(View.GONE);
                    fetchData();
                }
            });
        }
        //页面不为空白，提示用户
        if (!isEmpty) {
            ToastUtil.showError(mContext);
        }
    }

    private boolean isRefresh = false;

    //下拉刷新，获取最新数据
    @Override
    public void refresh() {
        isRefresh = true;
        presenter.getPhotoList(count, "1");
    }

    @Override
    public void loadSuccess(PhotoListBean resultsBean) {
        if (isLoadMore) {            //加载更多成功
            mResults.addAll(resultsBean.results);
            mAdapter.notifyDataSetChanged();
            isLoadMore = false;
            mRefreshLayout.setRefreshing(false);
        } else if (isRefresh) {      //下拉刷新成功
            isRefresh = false;
            mRefreshLayout.setRefreshing(false);
            if (!resultsBean.results.get(1).url.equals(firstUrl)) {
                mResults.clear();
                mResults.addAll(resultsBean.results);
                mAdapter.notifyDataSetChanged();
            } else {
                ToastUtil.showShort(mContext, "当前为最新数据");
            }
        } else {                    //第一次进入页面加载数据成功
            mProgressBar.setVisibility(View.GONE);
            mRecyclerView.setVisibility(View.VISIBLE);
            mRefreshLayout.setVisibility(View.VISIBLE);

            mResults.addAll(resultsBean.results);
            mAdapter = new PhotoListAdapter(mResults, mContext);
            mRecyclerView.setAdapter(mAdapter);

            //获取第2条信息判断下拉刷新是否刷新数据
            firstUrl = resultsBean.results.get(1).url;

            isEmpty = false;
        }
        mAdapter.setOnClickListener(this);
    }

    @Override
    public void onRefresh() {
        refresh();
    }

    @Override
    public void onClick(View view, int position) {
        Intent intent = new Intent(mContext, PhotoViewActivity.class);
        intent.putExtra("imageUrl", mResults.get(position).url);
        ActivityOptionsCompat options = ActivityOptionsCompat
                .makeScaleUpAnimation(view, view.getWidth() / 2, view.getHeight() / 2, 0, 0);
        startActivity(intent, options.toBundle());
    }
}
