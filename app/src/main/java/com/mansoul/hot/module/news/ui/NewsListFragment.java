package com.mansoul.hot.module.news.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.mansoul.hot.R;
import com.mansoul.hot.base.BaseFragment;
import com.mansoul.hot.module.news.model.bean.NewsListBean;
import com.mansoul.hot.module.news.presenter.NewsListPresenter;
import com.mansoul.hot.module.news.view.INewsView;
import com.mansoul.hot.util.ToastUtil;
import com.orhanobut.logger.Logger;

import butterknife.BindView;

/**
 * Created by Mansoul on 16/8/18.
 */
public class NewsListFragment extends BaseFragment implements INewsView {

    public static final java.lang.String KEY_URL = "key_url";
    private String url;

    @BindView(R.id.text_view)
    TextView textView;
    private NewsListPresenter presenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        url = bundle.getString(KEY_URL, "");
    }

    @Override
    protected void initView() {
        textView.setText(url);
    }

    @Override
    protected void initData() {
        presenter = new NewsListPresenter(this);
        presenter.getData(url, 0);
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
    public void loading() {

    }

    @Override
    public void loadSuccess(NewsListBean data) {
        String title = data.T1348647909107.get(1).title;
        textView.setText(title);
    }

    @Override
    public void loadMore() {

    }

    @Override
    public void loadError() {
        ToastUtil.showShort(mActivity, "加载失败");
    }

    @Override
    public void refresh() {

    }
}
