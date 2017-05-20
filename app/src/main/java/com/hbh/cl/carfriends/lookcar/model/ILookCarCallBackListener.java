package com.hbh.cl.carfriends.lookcar.model;

import com.hbh.cl.carfriends.lookcar.bean.LookCar;

/**
 * Created by hbh on 2017/3/9.
 */

public interface ILookCarCallBackListener {
    void onSuccess(LookCar lookCar);
    void onError(String message);
}
