package com.hbh.cl.carfriends.lookcar.model;

/**
 * Created by hbh on 2017/3/9.
 */

public interface ILookCarModel {
    void LoadHeaderData(ILookCarCallBackListener listener);
    void LoadBottomData(ILookCarCallBackListener listener, String carID);
}
