package com.mansoul.hot.http.service;

import com.mansoul.hot.module.news.model.bean.NewsListBean;
import com.mansoul.hot.module.photo.model.bean.PhotoListBean;
import com.mansoul.hot.module.video.model.bean.VideoListBean;

import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by Mansoul on 16/8/11.
 */
public interface NetService {
    //获取图片
    @GET("{count}/{page}")
    Observable<PhotoListBean> getPhotoList(
            @Header("Cache-Control") String cacheControl,
            @Path("count") String count,
            @Path("page") String page);

    //获取新闻列表
    @GET("{type}/{page}-20.html")
    Observable<NewsListBean> getNewsList(
            @Header("Cache-Control") String cacheControl,
            @Path("type") String type,
            @Path("page") int page);

    //list/V9LG4B3A0/n/0-10.html
    //获取视频列表
    @GET("list/V9LG4B3A0/n/{startPage}-10.html")
    Observable<VideoListBean> getVideoList(
            @Header("Cache-Control") String cacheControl,
            @Path("startPage") int startPage);

}
