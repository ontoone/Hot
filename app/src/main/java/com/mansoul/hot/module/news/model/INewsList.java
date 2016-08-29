package com.mansoul.hot.module.news.model;

import com.mansoul.hot.common.CommonLoadCallback;
import com.mansoul.hot.module.news.model.bean.NewsListBean;

import java.util.List;

/**
 * Created by Mansoul on 16/8/22.
 */
public interface INewsList {
    NewsListBean getData(String type, int page, CommonLoadCallback<NewsListBean> callback);
}
