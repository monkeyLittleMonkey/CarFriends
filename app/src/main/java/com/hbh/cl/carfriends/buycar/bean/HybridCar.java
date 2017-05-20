package com.hbh.cl.carfriends.buycar.bean;

import com.google.gson.annotations.Expose;

import java.util.List;

/**
 * Created by hbh on 2017/3/28.
 */

public class HybridCar {

    /**
     * ID :
     * Status : 2
     * Message :
     * Method : bit.newenergycar
     * Data : [{"RowID":1,"SerialID":2406,"MasterId":26,"AliasName":"ÑÅ¸ó","FullSpelling":"dibadaiyage","CoverPhoto":"http://img1.bitautoimg.com/autoalbum/files/20160722/158/0531161580_1.jpg","DealerPrice":"13.60-26.56Íò","CarNum":3,"CarIdList":"121074,121254,121255,","UV":100325,"CarType":2}]
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
     * RowID : 1
     * SerialID : 2406
     * MasterId : 26
     * AliasName : ÑÅ¸ó
     * FullSpelling : dibadaiyage
     * CoverPhoto : http://img1.bitautoimg.com/autoalbum/files/20160722/158/0531161580_1.jpg
     * DealerPrice : 13.60-26.56Íò
     * CarNum : 3
     * CarIdList : 121074,121254,121255,
     * UV : 100325
     * CarType : 2
     */
    @Expose
    public List<DataBean> Data;

    public static class DataBean {
        @Expose
        public int RowID;
        @Expose
        public int SerialID;
        @Expose
        public int MasterId;
        @Expose
        public String AliasName;
        @Expose
        public String FullSpelling;
        @Expose
        public String CoverPhoto;
        @Expose
        public String DealerPrice;
        @Expose
        public int CarNum;
        @Expose
        public String CarIdList;
        @Expose
        public int UV;
        @Expose
        public int CarType;
        
    }
}
