package com.hbh.cl.carfriends.talkcar.presenter;

import com.hbh.cl.carfriends.talkcar.bean.TalkCar;
import com.hbh.cl.carfriends.talkcar.model.ITalkCarModel;
import com.hbh.cl.carfriends.talkcar.model.ITalkCarCallBackListener;
import com.hbh.cl.carfriends.talkcar.model.TalkCarModel;
import com.hbh.cl.carfriends.talkcar.view.ITalkCarView;

/**
 * Created by hbh on 2017/3/4.
 */

public class TalkCarPresenter {

    private ITalkCarModel mITalkCarModel;
    private ITalkCarView mITalkCarView;

    public TalkCarPresenter(ITalkCarView iTalkCarView) {
        this.mITalkCarView = iTalkCarView;
        this.mITalkCarModel = new TalkCarModel();
    }

    public void loadData(){
        mITalkCarView.showLoadDialog();
        mITalkCarModel.loadData(new ITalkCarCallBackListener() {
            @Override
            public void loadSuccess(TalkCar mTalkCar) {
                mITalkCarView.loadData(mTalkCar);
                mITalkCarView.hideLoadDialog();
            }

            @Override
            public void loadFiled(String message) {
                mITalkCarView.showError(message);
            }
        });
    }
}
