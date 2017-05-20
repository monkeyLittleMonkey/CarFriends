package com.hbh.cl.carfriends.buycar.model;

import com.hbh.cl.carfriends.buycar.bean.OldCar;

/**
 * Created by hbh on 2017/3/27.
 */

public interface IOldCarCallBackListener {

    void loadDataSuccess(OldCar oldCar);
    void error(String message);
}
