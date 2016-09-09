package com.mansoul.hot.module.news.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.mansoul.hot.R;
import com.mansoul.hot.common.Constant;
import com.mansoul.hot.module.news.model.bean.NewsDetailBean;
import com.mansoul.hot.module.news.presenter.NewsDetailPresenter;
import com.mansoul.hot.module.news.view.INewsDetailView;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.majiajie.swipeback.SwipeBackActivity;

public class NewsDetailActivity extends SwipeBackActivity implements INewsDetailView {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    private String newsId;

    private NewsDetailPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);
        ButterKnife.bind(this);

        initView();
        initData();

    }

    private void initView() {
        setSupportActionBar(mToolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void initData() {
        Intent intent = getIntent();
        newsId = intent.getStringExtra(Constant.NEWS_DETAIL_ID);
        mPresenter = new NewsDetailPresenter(this);

        mPresenter.getData(newsId);
    }

    @Override
    public void loadSuccess(NewsDetailBean result) {

    }

    @Override
    public void loading() {

    }

    @Override
    public void loadMore() {

    }

    @Override
    public void loadError() {

    }

    @Override
    public void refresh() {

    }
}
