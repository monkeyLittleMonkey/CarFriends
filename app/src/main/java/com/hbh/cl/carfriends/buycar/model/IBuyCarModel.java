package com.hbh.cl.carfriends.buycar.model;

/**
 * Created by hbh on 2017/3/22.
 */

public interface IBuyCarModel {
    /**
     * 加载上市新车数据
     * @param callBackListener
     */
    void LoadNewCarData(INewCarCallBackListener callBackListener);

    /**
     * 加载新能源车 新闻资讯数据
     * @param callBackListener
     */
    void LoadECarNewsData(ICarNewsCallBackListener callBackListener);

    /**
     * 加载新能源车 电动车数据
     * @param callBackListener
     */
    void LoadECarEData(IECarCallBackListener callBackListener);

    /**
     * 加载新能源车 混动车数据
     * @param callBackListener
     */
    void LoadECarHData(IHybridCarCallBackListener callBackListener);

    /**
     * 加载优质二手车数据
     * @param callBackListener
     */
    void loadOldCarData(IOldCarCallBackListener callBackListener);
}
