package com.hbh.cl.carfriends.buycar.view;

import com.hbh.cl.carfriends.buycar.bean.OldCar;

/**
 * Created by hbh on 2017/3/24.
 */

public interface IOldCarView {
    void LoadOldCarData(OldCar oldCar);
    void LoadError(String message);
    void showLoadDialog();
    void HideLoadDialog();
}
