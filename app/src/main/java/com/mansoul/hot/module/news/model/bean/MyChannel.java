package com.mansoul.hot.module.news.model.bean;

import com.trs.channellib.channel.channel.ChannelEntity;

/**
 * 新闻频道
 * Created by Mansoul on 16/8/18.
 */
public class MyChannel implements ChannelEntity.ChannelEntityCreater {
    private String title;

    private int channelType;

    private int isFix;

    private String url;

    private int isSubscrible;

    public int getIsSubscrible() {
        return isSubscrible;
    }

    public void setIsSubscrible(int isSubscrible) {
        this.isSubscrible = isSubscrible;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getChannelType() {
        return channelType;
    }

    public void setChannelType(int channelType) {
        this.channelType = channelType;
    }

    public int getIsFix() {
        return isFix;
    }

    public void setIsFix(int isFix) {
        this.isFix = isFix;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public ChannelEntity createChannelEntity() {
        ChannelEntity entity = new ChannelEntity();
        entity.setFixed(isFix == 1);
        entity.setName(title);
        return entity;
    }
}
