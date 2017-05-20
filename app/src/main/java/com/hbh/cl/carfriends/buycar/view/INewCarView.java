package com.hbh.cl.carfriends.buycar.view;

import com.hbh.cl.carfriends.buycar.bean.NewCar;

/**
 * Created by hbh on 2017/3/22.
 */

public interface INewCarView {
    void showLoadDialog();
    void hideLoadDialog();
    void loadData(NewCar newCar);
    void showError(String message);
}
