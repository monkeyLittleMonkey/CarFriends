package com.hbh.cl.carfriends.buycar.bean;

import com.google.gson.annotations.Expose;

import java.util.List;

/**
 * Created by hbh on 2017/3/22.
 * 上市新车类
 */

public class NewCar {

    /**
     * ID :
     * Status : 2
     * Message :
     * Method : bit.newcars
     * Data : [{"RowNumber":1,"ID":2334,"Name":"全新悦动","ShowName":"全新悦动","Level":"紧凑型车","AllSpell":"yuedong","Country":"国产","MakeDay":"2017-03-20","Pic":"http://img1.bitautoimg.com/autoalbum/files/20170320/420/0948294201_1.jpg","Price":"7.99-11.59万","cyear":"2017","isdelete":false}]
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
     * RowNumber : 1
     * ID : 2334
     * Name : 全新悦动
     * ShowName : 全新悦动
     * Level : 紧凑型车
     * AllSpell : yuedong
     * Country : 国产
     * MakeDay : 2017-03-20
     * Pic : http://img1.bitautoimg.com/autoalbum/files/20170320/420/0948294201_1.jpg
     * Price : 7.99-11.59万
     * cyear : 2017
     * isdelete : false
     */
    @Expose
    public List<DataBean> Data;


    public static class DataBean {
        @Expose
        public int RowNumber;
        @Expose
        public int ID;
        @Expose
        public String Name;
        @Expose
        public String ShowName;
        @Expose
        public String Level;
        @Expose
        public String AllSpell;
        @Expose
        public String Country;
        @Expose
        public String MakeDay;
        @Expose
        public String Pic;
        @Expose
        public String Price;
        @Expose
        public String cyear;
        @Expose
        public boolean isdelete;

    }
}
