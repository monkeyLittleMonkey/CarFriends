package com.hbh.cl.carfriends.talkcar.model;

import com.hbh.cl.carfriends.talkcar.bean.TalkCar;

/**
 * Created by hbh on 2017/3/4.
 */

public interface ITalkCarCallBackListener {
    void loadSuccess(TalkCar mTalkCar);
    void loadFiled(String message);
}
