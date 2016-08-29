package com.mansoul.hot.module.news.model.bean;

import java.util.List;

/**
 * 网易新闻列表
 * Created by Mansoul on 16/8/19.
 */
public class NewsListBean {

    /**
     * postid : PHOT22SKK000100A
     * hasCover : false
     * hasHead : 1
     * replyCount : 39906
     * hasImg : 1
     * digest :
     * hasIcon : false
     * docid : 9IG74V5H00963VRO_BV2EAJS4bjlishidaiupdateDoc
     * title : 五楼邻居养一米长鳄鱼 半夜掉一楼花园
     * order : 1
     * priority : 340
     * lmodify : 2016-08-22 14:15:57
     * boardid : photoview_bbs
     * ads : [{"title":"南京村庄建\u201c楼叠楼\u201d 为多拿拆迁补偿","tag":"photoset","imgsrc":"http://cms-bucket.nosdn.127.net/4693747579184988ad6e903e3e07793620160822095553.jpeg","subtitle":"","url":"00AP0001|2192041"},{"title":"安倍晋三扮马里奥亮相奥运会闭幕式","tag":"photoset","imgsrc":"http://cms-bucket.nosdn.127.net/14c874ae5e8c41818a1d754de935830920160822094144.jpeg","subtitle":"","url":"6TSU0005|147568"},{"title":"法国华人团体游行呼吁保障华人安全","tag":"photoset","imgsrc":"http://cms-bucket.nosdn.127.net/9c221db80bc54e068b84f6833701df0d20160822075828.jpeg","subtitle":"","url":"00AO0001|2192019"},{"title":"\"长征五号\"运抵天津码头 \"出征\"首飞","tag":"photoset","imgsrc":"http://cms-bucket.nosdn.127.net/9374e441b9134ebba5189aaaa96ff67220160822163244.jpeg","subtitle":"","url":"00AN0001|2192093"},{"title":"济南家长自戴头灯 领小孩黑虎泉边捕鱼","tag":"photoset","imgsrc":"http://cms-bucket.nosdn.127.net/1a12be5fb7f3495ab7626a99dbd75ace20160822101953.jpeg","subtitle":"","url":"00AP0001|2192051"}]
     * photosetID : 00AP0001|2192020
     * template : normal1
     * votecount : 38357
     * skipID : 00AP0001|2192020
     * alias : Top News
     * skipType : photoset
     * cid : C1348646712614
     * hasAD : 1
     * imgextra : [{"imgsrc":"http://cms-bucket.nosdn.127.net/796f95bf84c24797b536f5457cbf3ee820160822083406.jpeg"},{"imgsrc":"http://cms-bucket.nosdn.127.net/f7226a14d6764c29a6fe95af2837cd2a20160822083406.jpeg"}]
     * source : 网易原创
     * ename : androidnews
     * imgsrc : http://cms-bucket.nosdn.127.net/43a9e0011a934640b0ee140224afaa0820160822083404.jpeg
     * tname : 头条
     * ptime : 2016-08-22 08:20:55
     */

    public  List<bean> T1348647909107;

    public static class bean {
        public String postid;
        public boolean hasCover;
        public int hasHead;
        public int replyCount;
        public int hasImg;
        public String digest;
        public boolean hasIcon;
        public String docid;
        public String title;
        public int order;
        public int priority;
        public String lmodify;
        public String boardid;
        public String photosetID;
        public String template;
        public int votecount;
        public String skipID;
        public String alias;
        public String skipType;
        public String cid;
        public int hasAD;
        public String source;
        public String ename;
        public String imgsrc;
        public String tname;
        public String ptime;
        /**
         * title : 南京村庄建“楼叠楼” 为多拿拆迁补偿
         * tag : photoset
         * imgsrc : http://cms-bucket.nosdn.127.net/4693747579184988ad6e903e3e07793620160822095553.jpeg
         * subtitle :
         * url : 00AP0001|2192041
         */

        public List<AdsBean> ads;
        /**
         * imgsrc : http://cms-bucket.nosdn.127.net/796f95bf84c24797b536f5457cbf3ee820160822083406.jpeg
         */

        public List<ImgextraBean> imgextra;

        public static class AdsBean {
            public String title;
            public String tag;
            public String imgsrc;
            public String subtitle;
            public String url;
        }

        public static class ImgextraBean {
            public String imgsrc;
        }
    }
}
