package com.hbh.cl.carfriends.buycar.bean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by hbh on 2017/3/24.
 */

public class OldCar {

    /**
     * AllCount : 6589
     * CarList : [{"UcarID":"14331967","CityID":"2101","CityName":"济南","ProvinceID":"21","ProvinceName":"山东","MainBrandId":"44","MainBrandName":"华普","BrandId":"1816","BrandName":"海尚","CarID":"2077","CarName":"06款 三厢1.5L舒适型","Exhaust":"1.5L","CarPublishTime":"2017-03-22 15:07:34","PictureCount":"6","BuyCarDate":"2006","DisPlayPrice":"0.4","DrivingMileage":"23000","CarSource1l":"2","UcarSerialNumber":"Dealer17032219023","2GImgs":"http://img5.taoche.cn/1f/021708j44n.jpg|http://img5.taoche.cn/1f/021708j455.jpg|http://img5.taoche.cn/1f/021708j45f.jpg|http://img5.taoche.cn/1f/021708j45t.jpg|http://img5.taoche.cn/1f/021708j468.jpg|http://img5.taoche.cn/1f/021708j46p.jpg","ImageURL":"http://img5.taoche.cn/1c/021708j44n.jpg|http://img5.taoche.cn/1c/021708j455.jpg|http://img5.taoche.cn/1c/021708j45f.jpg|http://img5.taoche.cn/1c/021708j45t.jpg|http://img5.taoche.cn/1c/021708j468.jpg|http://img5.taoche.cn/1c/021708j46p.jpg","Authenticated":"非品牌认证","IsDealerAuthorized":"非淘车认证","VideoId":"","VideoUnique":"","IsZhiBao":"非质保","cpc":"0","istop":"0","cpwt":"","CarShortPublishTime":"2天前","ShowPublisTime":"1","CarService":"0","CarDetailUrl":"http://ershouche.h5.yiche.com/car/car14331967.html?ref=4L_yc_app_bjdq","BuyCarDate_New":"2006年","CarServiceNew":"0000","NewCarPrice":"5.6888"}]
     */
    @Expose
    public String AllCount;
    /**
     * UcarID : 14331967
     * CityID : 2101
     * CityName : 济南
     * ProvinceID : 21
     * ProvinceName : 山东
     * MainBrandId : 44
     * MainBrandName : 华普
     * BrandId : 1816
     * BrandName : 海尚
     * CarID : 2077
     * CarName : 06款 三厢1.5L舒适型
     * Exhaust : 1.5L
     * CarPublishTime : 2017-03-22 15:07:34
     * PictureCount : 6
     * BuyCarDate : 2006
     * DisPlayPrice : 0.4
     * DrivingMileage : 23000
     * CarSource1l : 2
     * UcarSerialNumber : Dealer17032219023
     * 2GImgs : http://img5.taoche.cn/1f/021708j44n.jpg|http://img5.taoche.cn/1f/021708j455.jpg|http://img5.taoche.cn/1f/021708j45f.jpg|http://img5.taoche.cn/1f/021708j45t.jpg|http://img5.taoche.cn/1f/021708j468.jpg|http://img5.taoche.cn/1f/021708j46p.jpg
     * ImageURL : http://img5.taoche.cn/1c/021708j44n.jpg|http://img5.taoche.cn/1c/021708j455.jpg|http://img5.taoche.cn/1c/021708j45f.jpg|http://img5.taoche.cn/1c/021708j45t.jpg|http://img5.taoche.cn/1c/021708j468.jpg|http://img5.taoche.cn/1c/021708j46p.jpg
     * Authenticated : 非品牌认证
     * IsDealerAuthorized : 非淘车认证
     * VideoId : 
     * VideoUnique : 
     * IsZhiBao : 非质保
     * cpc : 0
     * istop : 0
     * cpwt : 
     * CarShortPublishTime : 2天前
     * ShowPublisTime : 1
     * CarService : 0
     * CarDetailUrl : http://ershouche.h5.yiche.com/car/car14331967.html?ref=4L_yc_app_bjdq
     * BuyCarDate_New : 2006年
     * CarServiceNew : 0000
     * NewCarPrice : 5.6888
     */
    @Expose
    public List<CarListBean> CarList;


    public static class CarListBean {

        public static final int ITEM_TYPE_PARENT = 0;
        public static final int ITEM_TYPE_CHILD = 1;

        private int type;// 显示类型
        private boolean expand;// 是否展开
        private CarListBean children;

        @Expose
        public String UcarID;
        @Expose
        public String CityID;
        @Expose
        public String CityName;
        @Expose
        public String ProvinceID;
        @Expose
        public String ProvinceName;
        @Expose
        public String MainBrandId;
        @Expose
        public String MainBrandName;
        @Expose
        public String BrandId;
        @Expose
        public String BrandName;
        @Expose
        public String CarID;
        @Expose
        public String CarName;
        @Expose
        public String Exhaust;
        @Expose
        public String CarPublishTime;
        @Expose
        public String PictureCount;
        @Expose
        public String BuyCarDate;
        @Expose
        public String DisPlayPrice;
        @Expose
        public String DrivingMileage;
        @Expose
        public String CarSource1l;
        @Expose
        public String UcarSerialNumber;
        @Expose
        @SerializedName("2GImgs")
        public String value2GImgs;
        @Expose
        public String ImageURL;
        @Expose
        public String Authenticated;
        @Expose
        public String IsDealerAuthorized;
        @Expose
        public String VideoId;
        @Expose
        public String VideoUnique;
        @Expose
        public String IsZhiBao;
        @Expose
        public String cpc;
        @Expose
        public String istop;
        @Expose
        public String cpwt;
        @Expose
        public String CarShortPublishTime;
        @Expose
        public String ShowPublisTime;
        @Expose
        public String CarService;
        @Expose
        public String CarDetailUrl;
        @Expose
        public String BuyCarDate_New;
        @Expose
        public String CarServiceNew;
        @Expose
        public String NewCarPrice;


        public boolean isExpand() {
            return expand;
        }

        public void setExpand(boolean expand) {
            this.expand = expand;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public CarListBean getChildren() {
            return children;
        }

        public void setChildren(CarListBean children) {
            this.children = children;
        }
    }

}
