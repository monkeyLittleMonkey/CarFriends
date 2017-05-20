package com.hbh.cl.carfriends.buycar.view;

import com.hbh.cl.carfriends.buycar.bean.CarNews;
import com.hbh.cl.carfriends.buycar.bean.ECar;
import com.hbh.cl.carfriends.buycar.bean.HybridCar;

/**
 * Created by hbh on 2017/3/23.
 */

public interface IECarView {
    void showLoadDialog();
    void hideLoadDialog();
    void loadCarNewsData(CarNews carNews);
    void loadHybridCarData(HybridCar hybridCar);
    void loadECarData(ECar eCar);
    void showError(String message);
}
