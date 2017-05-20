package com.hbh.cl.carfriends.buycar.presenter;

import com.hbh.cl.carfriends.buycar.bean.CarNews;
import com.hbh.cl.carfriends.buycar.bean.ECar;
import com.hbh.cl.carfriends.buycar.bean.HybridCar;
import com.hbh.cl.carfriends.buycar.model.BuyCarModel;
import com.hbh.cl.carfriends.buycar.model.IBuyCarModel;
import com.hbh.cl.carfriends.buycar.model.ICarNewsCallBackListener;
import com.hbh.cl.carfriends.buycar.model.IECarCallBackListener;
import com.hbh.cl.carfriends.buycar.model.IHybridCarCallBackListener;
import com.hbh.cl.carfriends.buycar.view.IECarView;

/**
 * Created by hbh on 2017/3/23.
 * 新能源车
 */

public class NewEnergyCarPresenter {

    private IECarView mView;
    private IBuyCarModel mModel;

    public NewEnergyCarPresenter(IECarView view) {
        this.mView = view;
        mModel = new BuyCarModel();
    }

    /**
     * 加载新闻资讯
     */
    public void loadCarNewsData(){
        mView.showLoadDialog();
        mModel.LoadECarNewsData(new ICarNewsCallBackListener() {
            @Override
            public void onCarNewsSuccess(CarNews carNews) {
                mView.hideLoadDialog();
                mView.loadCarNewsData(carNews);
            }

            @Override
            public void onError(String message) {
                mView.showError(message);
            }
        });
    }

    /**
     * 加载新能源 电动车
     */
    public void loadECarData(){
        mView.showLoadDialog();
        mModel.LoadECarEData(new IECarCallBackListener() {
            @Override
            public void loadSuccess(ECar eCar) {
                mView.hideLoadDialog();
                mView.loadECarData(eCar);
            }

            @Override
            public void error(String message) {
                mView.showError(message);
            }
        });
    }

    /**
     * 加载新能源 混动车
     */
    public void loadHCarData(){
        mView.showLoadDialog();
        mModel.LoadECarHData(new IHybridCarCallBackListener() {
            @Override
            public void loadSuccess(HybridCar hybridCar) {
                mView.hideLoadDialog();
                mView.loadHybridCarData(hybridCar);
            }

            @Override
            public void error(String message) {
                mView.showError(message);
            }
        });
    }
}
