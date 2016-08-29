package com.mansoul.hot.module.video.model.bean;

import java.util.List;

/**
 * Created by Mansoul on 16/8/29.
 */
public class VideoListBean {

    /**
     * topicImg : http://vimg1.ws.126.net/image/snapshot/2016/8/P/7/VBU7HVMP7.jpg
     * videosource : 新媒体
     * mp4Hd_url : null
     * topicDesc : 这是一个个人自媒体账号，用于发布一些娱乐性文章，希望能为贵平台带来收益，同时让本号能分到一杯羹，我们的口号是：大千世界，博学深知，轻松求知
     * topicSid : VBU7HVMP2
     * cover : http://vimg2.ws.126.net/image/snapshot/2016/8/B/T/VBUMT35BT.jpg
     * title : 永远不要跟二逼一起吃饭 他会把你智商拉低
     * playCount : 0
     * replyBoard : video_bbs
     * videoTopic : {"alias":"大千世界，博学深知，轻松求知","tname":"世界知最","ename":"T1471681823371","tid":"T1471681823371"}
     * sectiontitle :
     * replyid : BUMSOFVO008535RB
     * description : 永远不要跟二逼一起吃饭，他会把你的智商拉低到一定程度.
     * mp4_url : http://flv2.bn.netease.com/videolib3/1608/29/SVDBN7676/SD/SVDBN7676-mobile.mp4
     * length : 53
     * playersize : 1
     * m3u8Hd_url : null
     * vid : VBUMSOFVO
     * m3u8_url : http://flv2.bn.netease.com/videolib3/1608/29/SVDBN7676/SD/movie_index.m3u8
     * ptime : 2016-08-29 10:35:07
     * topicName : 世界知最
     */

    public List<V9LG4B3A0Bean> V9LG4B3A0;

    public static class V9LG4B3A0Bean {
        public String topicImg;
        public String videosource;
        public String mp4Hd_url;
        public String topicDesc;
        public String topicSid;
        public String cover;
        public String title;
        public int playCount;
        public String replyBoard;
        /**
         * alias : 大千世界，博学深知，轻松求知
         * tname : 世界知最
         * ename : T1471681823371
         * tid : T1471681823371
         */

        public VideoTopicBean videoTopic;
        public String sectiontitle;
        public String replyid;
        public String description;
        public String mp4_url;
        public int length;
        public int playersize;
        public Object m3u8Hd_url;
        public String vid;
        public String m3u8_url;
        public String ptime;
        public String topicName;

        public static class VideoTopicBean {
            public String alias;
            public String tname;
            public String ename;
            public String tid;
        }
    }
}
