package com.hbh.cl.carfriends.buycar.model;

import com.hbh.cl.carfriends.buycar.bean.ECar;

/**
 * Created by hbh on 2017/3/28.
 */

public interface IECarCallBackListener {
    void loadSuccess(ECar eCar);
    void error(String message);
}
