package com.mansoul.hot.module.news.view;

import com.mansoul.hot.base.BaseIView;
import com.mansoul.hot.module.news.model.bean.NewsDetailBean;

/**
 * Created by Mansoul on 16/9/9.
 */
public interface INewsDetailView extends BaseIView<NewsDetailBean> {
    void loadSuccess(NewsDetailBean result);
}
