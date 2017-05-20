package com.hbh.cl.carfriends.buycar.model;

import com.hbh.cl.carfriends.buycar.bean.HybridCar;

/**
 * Created by hbh on 2017/3/28.
 */

public interface IHybridCarCallBackListener {

    void loadSuccess(HybridCar hybridCar);
    void error(String message);
}
