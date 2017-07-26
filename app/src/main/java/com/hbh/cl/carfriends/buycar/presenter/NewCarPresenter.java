package com.hbh.cl.carfriends.buycar.presenter;

import com.hbh.cl.carfriends.buycar.bean.NewCar;
import com.hbh.cl.carfriends.buycar.model.BuyCarModel;
import com.hbh.cl.carfriends.buycar.model.IBuyCarModel;
import com.hbh.cl.carfriends.buycar.model.INewCarCallBackListener;
import com.hbh.cl.carfriends.buycar.view.INewCarView;

/**
 * Created by hbh on 2017/3/22.
 * 上市新车
 */

public class NewCarPresenter {

    private IBuyCarModel mModel;
    private INewCarView mView;

    public NewCarPresenter(INewCarView buyCarView) {
        this.mView = buyCarView;
        this.mModel = new BuyCarModel();
    }

    /**
     * 加载上市新车数据
     */
    public void loadNewCarData(){
        mView.showLoadDialog();
        mModel.LoadNewCarData(new INewCarCallBackListener() {
            @Override
            public void onNewCarSuccess(NewCar newCar) {
                mView.loadData(newCar);
                mView.hideLoadDialog();
            }

            @Override
            public void onError(String message) {
                mView.showError(message);
            }
        });
    }
}
