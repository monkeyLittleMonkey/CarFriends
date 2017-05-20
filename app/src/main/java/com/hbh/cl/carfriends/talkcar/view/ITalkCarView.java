package com.hbh.cl.carfriends.talkcar.view;

import com.hbh.cl.carfriends.talkcar.bean.TalkCar;

/**
 * Created by hbh on 2017/3/4.
 */

public interface ITalkCarView {
    void showLoadDialog();
    void hideLoadDialog();
    void loadData(TalkCar mTalkCar);
    void showError(String message);
}
