package com.hbh.cl.carfriends.lookcar.bean;

import com.google.gson.annotations.Expose;

import java.util.List;

/**
 * Created by hbh on 2017/3/9.
 */

public class LookCar {

    /**
     * ID :
     * Status : 2
     * Message :
     * Method : bit.videocatlist
     * Data : [{"CatId":"15","CatName":"��չ��Ƶ","CatList":[{"Mp4Link":"http://flv.bitauto.com/2014/2016/11/19/81eb55598e75689b-sd.mp4","VideoId":"439741","Author":"�Ŀ�˶","Title":"2016���ݳ�չ �������4008����ȫ���","CreatedOn":"2016-11-19","VideoUnique":"","LetvVideoId":"0","Duration":"07:13","CategoryName":"��չ��Ƶ","CategoryId":"102","ImageLink":"http://img4.bitautoimg.com/wapimg-240-0/Video/2016/11/21/201611219613866.jpg","Row":"1","SwfLink":"http://v.bitauto.com/player/439741/v.swf","TotalVisit":"357086","Summary":"�¿Ƽ� ������\u2014\u20142016��ʮ�Ľ���ݹ��ʳ�չʢ��Ļ���׳���Ƶ���ʳ��֣��������4008����ȫ���","PublishTime":"2016-11-18","PlayLink":"http://v.m.yiche.com/vplay/439741.html","RelativeVideoId":null}]}]
     */
    @Expose
    public String ID;
    @Expose
    public String Status;
    @Expose
    public String Message;
    @Expose
    public String Method;
    /**
     * CatId : 15
     * CatName : ��չ��Ƶ
     * CatList : [{"Mp4Link":"http://flv.bitauto.com/2014/2016/11/19/81eb55598e75689b-sd.mp4","VideoId":"439741","Author":"�Ŀ�˶","Title":"2016���ݳ�չ �������4008����ȫ���","CreatedOn":"2016-11-19","VideoUnique":"","LetvVideoId":"0","Duration":"07:13","CategoryName":"��չ��Ƶ","CategoryId":"102","ImageLink":"http://img4.bitautoimg.com/wapimg-240-0/Video/2016/11/21/201611219613866.jpg","Row":"1","SwfLink":"http://v.bitauto.com/player/439741/v.swf","TotalVisit":"357086","Summary":"�¿Ƽ� ������\u2014\u20142016��ʮ�Ľ���ݹ��ʳ�չʢ��Ļ���׳���Ƶ���ʳ��֣��������4008����ȫ���","PublishTime":"2016-11-18","PlayLink":"http://v.m.yiche.com/vplay/439741.html","RelativeVideoId":null}]
     */

    @Expose
    public List<DataBean> Data;

    public static class DataBean {
        @Expose
        public String CatId;
        @Expose
        public String CatName;
        /**
         * Mp4Link : http://flv.bitauto.com/2014/2016/11/19/81eb55598e75689b-sd.mp4
         * VideoId : 439741
         * Author : �Ŀ�˶
         * Title : 2016���ݳ�չ �������4008����ȫ���
         * CreatedOn : 2016-11-19
         * VideoUnique :
         * LetvVideoId : 0
         * Duration : 07:13
         * CategoryName : ��չ��Ƶ
         * CategoryId : 102
         * ImageLink : http://img4.bitautoimg.com/wapimg-240-0/Video/2016/11/21/201611219613866.jpg
         * Row : 1
         * SwfLink : http://v.bitauto.com/player/439741/v.swf
         * TotalVisit : 357086
         * Summary : �¿Ƽ� �������2016��ʮ�Ľ���ݹ��ʳ�չʢ��Ļ���׳���Ƶ���ʳ��֣��������4008����ȫ���
         * PublishTime : 2016-11-18
         * PlayLink : http://v.m.yiche.com/vplay/439741.html
         * RelativeVideoId : null
         */

        @Expose
        public List<CatListBean> CatList;


        public static class CatListBean {
            @Expose
            public String Mp4Link;
            @Expose
            public String VideoId;
            @Expose
            public String Author;
            @Expose
            public String Title;
            @Expose
            public String CreatedOn;
            @Expose
            public String VideoUnique;
            @Expose
            public String LetvVideoId;
            @Expose
            public String Duration;
            @Expose
            public String CategoryName;
            @Expose
            public String CategoryId;
            @Expose
            public String ImageLink;
            @Expose
            public String Row;
            @Expose
            public String SwfLink;
            @Expose
            public String TotalVisit;
            @Expose
            public String Summary;
            @Expose
            public String PublishTime;
            @Expose
            public String PlayLink;
            @Expose
            public Object RelativeVideoId;
        }
    }
}
