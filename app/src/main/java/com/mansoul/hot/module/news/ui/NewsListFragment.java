package com.mansoul.hot.module.news.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.mansoul.hot.R;
import com.mansoul.hot.base.BaseFragment;
import com.mansoul.hot.module.news.model.bean.NewsListBean;
import com.mansoul.hot.module.news.presenter.NewsListPresenter;
import com.mansoul.hot.module.news.ui.adapter.NewsListAdapter;
import com.mansoul.hot.module.news.view.INewsView;
import com.mansoul.hot.util.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


/**
 * Created by Mansoul on 16/8/18.
 */
public class NewsListFragment extends BaseFragment implements INewsView, XRecyclerView.LoadingListener {

    @BindView(R.id.progress_bar)
    ProgressBar mProgressBar;
    @BindView(R.id.tv_error)
    TextView mTextView;
    @BindView(R.id.fl_main)
    FrameLayout mFrameLayout;
    @BindView(R.id.recycle_view)
    XRecyclerView mRecyclerView;

    public static final java.lang.String KEY_URL = "key_url";
    private String url;

    private int page = 0;

    private NewsListPresenter presenter;
    private ArrayList<NewsListBean.bean> mResult;
    private NewsListAdapter mAdapter;
    private String isUpdate;
    private boolean isRefresh = false;
    private boolean isLoadMore = false;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        url = bundle.getString(KEY_URL, "");
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_news_list;
    }

    @Override
    public String getToolbarTitle() {
        return "新闻";
    }

    public String getUrl() {
        return url;
    }

    @Override
    protected void initView() {
        loading();

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
        mResult = new ArrayList<>();
        presenter = new NewsListPresenter(this);
        presenter.getData(url, page);
    }


    @Override
    public void loading() {
        mProgressBar.setVisibility(View.VISIBLE);
        mTextView.setVisibility(View.GONE);
        mRecyclerView.setVisibility(View.GONE);

    }

//    @Override
//    public void loadSuccess(NewsListBean data) {
//        if (isRefresh) {
//            if (data.T1348647909107.get(2).postid.equals(isUpdate)) {
//                ToastUtil.showShort(mActivity, "当前为最新数据");
//            } else {
//                mResult.clear();
//                mResult.addAll(data.T1348647909107);
//                mAdapter.notifyDataSetChanged();
//
//            }
//            isRefresh = false;
//            mRecyclerView.refreshComplete();
//        } else if (isLoadMore) {
//            System.out.println("加载更多成功");
//
//            mResult.addAll(data.T1348647909107);
//            mAdapter.notifyDataSetChanged();
//            isLoadMore = false;
//            mRecyclerView.loadMoreComplete();
//
//        } else {
//            mProgressBar.setVisibility(View.GONE);
//            mRecyclerView.setVisibility(View.VISIBLE);
//
//            mResult.addAll(data.T1348647909107);
//            mAdapter = new NewsListAdapter(mActivity, mResult);
//            mRecyclerView.setAdapter(mAdapter);
//
//            isUpdate = data.T1348647909107.get(2).postid;
//
//            isEmpty = false;
//        }
//    }

    @Override
    public void loadError() {
        System.out.println("loadError");
        if (isLoadMore) {   //加载更多失败
            isLoadMore = false;
            page -= 1;
            mRecyclerView.loadMoreComplete();
        } else if (isRefresh) {  //下拉刷新失败
            isRefresh = false;
            mRecyclerView.refreshComplete();
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

    @Override
    public void onRefresh() {
        isRefresh = true;
        presenter.getData(url, 0);
    }

    @Override
    public void onLoadMore() {
        System.out.println("onLoadMore");

        isLoadMore = true;
        page += 1;
        presenter.getData(url, page);

    }

    //////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public void loadMore() {

    }

    @Override
    public void refresh() {

    }

    @Override
    public void loadSuccess(List<NewsListBean.bean> result) {
        if (isRefresh) {
            if (result.get(2).postid.equals(isUpdate)) {
                ToastUtil.showShort(mActivity, "当前为最新数据");
            } else {
                mResult.clear();
                mResult.addAll(result);
                mAdapter.notifyDataSetChanged();

            }
            isRefresh = false;
            mRecyclerView.refreshComplete();
        } else if (isLoadMore) {
            System.out.println("加载更多成功");

            mResult.addAll(result);
            mRecyclerView.loadMoreComplete();
            mAdapter.notifyDataSetChanged();
            isLoadMore = false;

        } else {
            mProgressBar.setVisibility(View.GONE);
            mRecyclerView.setVisibility(View.VISIBLE);

            mResult.addAll(result);
            mAdapter = new NewsListAdapter(mActivity, mResult);
            mRecyclerView.setAdapter(mAdapter);

            isUpdate = result.get(2).postid;

            isEmpty = false;
        }
    }
}
