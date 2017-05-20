package com.hbh.cl.carfriends.lookcar.presenter;

import com.hbh.cl.carfriends.lookcar.bean.LookCar;
import com.hbh.cl.carfriends.lookcar.model.ILookCarModel;
import com.hbh.cl.carfriends.lookcar.model.ILookCarCallBackListener;
import com.hbh.cl.carfriends.lookcar.model.LookCarModel;
import com.hbh.cl.carfriends.lookcar.view.ILookCarView;

/**
 * Created by hbh on 2017/3/9.
 */

public class LookCarPresenter {

    private ILookCarView mLookCarView;
    private ILookCarModel mLookCarModel;

    public LookCarPresenter(ILookCarView lookCarView) {
        this.mLookCarView = lookCarView;
        mLookCarModel = new LookCarModel();
    }

    public void loadHeaderData(){
        mLookCarView.showLoadDialog();
        mLookCarModel.LoadHeaderData(new ILookCarCallBackListener() {
            @Override
            public void onSuccess(LookCar lookCar) {
                mLookCarView.loadData(lookCar);
                mLookCarView.hideLoadDialog();
            }

            @Override
            public void onError(String message) {
                mLookCarView.showError(message);
            }
        });
    }

    public void loadBottomData(String carID){
        mLookCarView.showLoadDialog();
        mLookCarModel.LoadBottomData(new ILookCarCallBackListener() {
            @Override
            public void onSuccess(LookCar lookCar) {
                mLookCarView.loadData(lookCar);
                mLookCarView.hideLoadDialog();
            }

            @Override
            public void onError(String message) {
                mLookCarView.showError(message);
            }
        }, carID);
    }
}
