package com.hbh.cl.carfriends.lookcar.view;

import com.hbh.cl.carfriends.lookcar.bean.LookCar;

/**
 * Created by hbh on 2017/3/9.
 */

public interface ILookCarView {
    void showLoadDialog();
    void hideLoadDialog();
    void loadData(LookCar lookCar);
    void showError(String message);
}
