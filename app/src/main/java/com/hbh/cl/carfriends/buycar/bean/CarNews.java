package com.hbh.cl.carfriends.buycar.bean;

import com.google.gson.annotations.Expose;

import java.util.List;

/**
 * Created by hbh on 2017/3/23.
 * ??????? ?????????
 */

public class CarNews {


    /**
     * ID :
     * Status : 2
     * Message : ???????
     * Method : news.list
     * Data : [{"facetitle":"????A70E?????????????","author":"","serialid":[4705],"filepath":"http://news.bitauto.com/xinche/20170323/1106790910.html","PicCover":"http://img1.bitautoimg.com/bitauto/2017/03/23/d25a648e-feca-4889-87e5-dafb7d386e20.jpg","PicTemplet":"http://img1.bitautoimg.com/wapimg-{0}-{1}/bitauto/2017/03/23/d25a648e-feca-4889-87e5-dafb7d386e20.jpg","publishtime":"2017-03-23","CategoryID":[],"newsid":"6790910","CommentCount":71,"Source":0,"SourceName":"","title":"????A70E????????????? \u201c?????\u201d??????14?????","Type":"1","FocusImg":"","ImageListInfo":[]}]
     */
    @Expose
    public String ID;
    @Expose
    public int Status;
    @Expose
    public String Message;
    @Expose
    public String Method;
    /**
     * facetitle : ????A70E?????????????
     * author :
     * serialid : [4705]
     * filepath : http://news.bitauto.com/xinche/20170323/1106790910.html
     * PicCover : http://img1.bitautoimg.com/bitauto/2017/03/23/d25a648e-feca-4889-87e5-dafb7d386e20.jpg
     * PicTemplet : http://img1.bitautoimg.com/wapimg-{0}-{1}/bitauto/2017/03/23/d25a648e-feca-4889-87e5-dafb7d386e20.jpg
     * publishtime : 2017-03-23
     * CategoryID : []
     * newsid : 6790910
     * CommentCount : 71
     * Source : 0
     * SourceName :
     * title : ????A70E????????????? ???????i??????14?????
     * Type : 1
     * FocusImg :
     * ImageListInfo : []
     */
    @Expose
    public List<DataBean> Data;

    public static class DataBean {
        @Expose
        public String facetitle;
        @Expose
        public String author;
        @Expose
        public String filepath;
        @Expose
        public String PicCover;
        @Expose
        public String PicTemplet;
        @Expose
        public String publishtime;
        @Expose
        public String newsid;
        @Expose
        public int CommentCount;
        @Expose
        public int Source;
        @Expose
        public String SourceName;
        @Expose
        public String title;
        @Expose
        public String Type;
        @Expose
        public String FocusImg;
        @Expose
        public List<Integer> serialid;
        @Expose
        public List<?> CategoryID;
        @Expose
        public List<?> ImageListInfo;

    }
}
