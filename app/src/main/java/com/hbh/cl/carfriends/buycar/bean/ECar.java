package com.hbh.cl.carfriends.buycar.bean;

import com.google.gson.annotations.Expose;

import java.util.List;

/**
 * Created by hbh on 2017/3/28.
 */

public class ECar {

    /**
     * ID : 
     * Status : 2
     * Message : 
     * Method : bit.newenergycar
     * Data : [{"RowID":1,"SerialID":4269,"MasterId":138,"AliasName":"野马T70","FullSpelling":"yemat70","CoverPhoto":"http://img1.bitautoimg.com/autoalbum/files/20151021/241/0245182418_1.jpg","DealerPrice":"6.26-22.98万","CarNum":1,"CarIdList":"120936,","UV":7264,"CarType":1}]
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
     * SerialID : 4269
     * MasterId : 138
     * AliasName : 野马T70
     * FullSpelling : yemat70
     * CoverPhoto : http://img1.bitautoimg.com/autoalbum/files/20151021/241/0245182418_1.jpg
     * DealerPrice : 6.26-22.98万
     * CarNum : 1
     * CarIdList : 120936,
     * UV : 7264
     * CarType : 1
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
