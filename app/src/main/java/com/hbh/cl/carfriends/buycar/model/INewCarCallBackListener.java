package com.hbh.cl.carfriends.buycar.model;

import com.hbh.cl.carfriends.buycar.bean.NewCar;
import com.hbh.cl.carfriends.buycar.bean.CarNews;

/**
 * Created by hbh on 2017/3/22.
 */

public interface INewCarCallBackListener {
    /**
     * 上市新车数据加载成功
     * @param newCar
     */
    void onNewCarSuccess(NewCar newCar);
    /**
     * 数据加载失败
     * @param message
     */
    void onError(String message);
}
