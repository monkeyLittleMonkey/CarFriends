package com.hbh.cl.carfriends.buycar.presenter;

import com.hbh.cl.carfriends.buycar.bean.OldCar;
import com.hbh.cl.carfriends.buycar.model.BuyCarModel;
import com.hbh.cl.carfriends.buycar.model.IBuyCarModel;
import com.hbh.cl.carfriends.buycar.model.IOldCarCallBackListener;
import com.hbh.cl.carfriends.buycar.view.IOldCarView;

/**
 * Created by hbh on 2017/3/27.
 * 优质二手车
 */

public class OldCarPresenter {

    private IBuyCarModel mModel;
    private IOldCarView mView;

    public OldCarPresenter(IOldCarView view) {
        this.mView = view;
        mModel = new BuyCarModel();
    }

    public void loadOldCarData(){
        mView.showLoadDialog();
        mModel.loadOldCarData(new IOldCarCallBackListener() {
            @Override
            public void loadDataSuccess(OldCar oldCar) {
                mView.HideLoadDialog();
                mView.LoadOldCarData(oldCar);
            }

            @Override
            public void error(String message) {
                mView.LoadError(message);
            }
        });
    }
}
