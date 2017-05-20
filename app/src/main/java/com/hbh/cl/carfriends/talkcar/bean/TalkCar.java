package com.hbh.cl.carfriends.talkcar.bean;

import com.google.gson.annotations.Expose;

import java.util.List;

/**
 * Created by hbh on 2017/3/3.
 */

public class TalkCar {

    //∑√Œ µÿ÷∑
    // http://msn.api.app.yiche.com/api.ashx?method=topic.hottopictop&startindex=1&pagesize=20&ver=7.3&appid=17&sign=54519f2ad9fba468cee948d40243ac9c

    @Expose
    public String ID;
    @Expose
    public int Status;
    @Expose
    public String Message;
    @Expose
    public String Method;


    @Expose
    public DataBean Data;

    public static class DataBean {
        @Expose
        public int PageIndex;
        @Expose
        public int PageSize;
        @Expose
        public int RecordCount;
        @Expose
        public String LastUpdatetime;

        @Expose
        public java.util.List<ListBean> List;


        public static class ListBean {
            @Expose
            public int Row;
            @Expose
            public int TopicId;
            @Expose
            public int ForumId;
            @Expose
            public int UserId;
            @Expose
            public int TopicMode;
            @Expose
            public String Title;
            @Expose
            public Object VoteCare;
            @Expose
            public int TopicType;
            @Expose
            public String Summary;
            @Expose
            public String ImageList;
            @Expose
            public int ReplyCount;
            @Expose
            public int LikeCount;
            @Expose
            public String PlaceName;
            @Expose
            public String TopicCarName;
            @Expose
            public int AppId;
            @Expose
            public int CityId;
            @Expose
            public boolean IsGood;
            @Expose
            public boolean IsTop;
            @Expose
            public String CreatedOn;
            @Expose
            public String ReplyTime;
            @Expose
            public String SetGoodTime;
            @Expose
            public String Uptime;
            @Expose
            public int PositionLon;
            @Expose
            public int PositionLat;
            @Expose
            public Object QtIsDeleted;
            @Expose
            public boolean IsDeleted;
            @Expose
            public boolean IsMultipleMedia;
            @Expose
            public String ForumName;
            @Expose
            public String ForumIcon;
            @Expose
            public String UserName;
            @Expose
            public String UserAvatar;
            @Expose
            public int UserGender;
            @Expose
            public int identitytype;
            @Expose
            public Object QuoteType;
            @Expose
            public Object QuoteTopicId;
            @Expose
            public Object QuoteLink;
            @Expose
            public Object QuoteDesc;
            @Expose
            public Object QuoteLogo;
            @Expose
            public Object QuoteTitle;
            @Expose
            public String MasterName;
            @Expose
            public Object CityName;
            @Expose
            public Object CityFullName;
            @Expose
            public String AppName;
            @Expose
            public Object FullContent;
            @Expose
            public String UserIcons;
        }
    }
}
