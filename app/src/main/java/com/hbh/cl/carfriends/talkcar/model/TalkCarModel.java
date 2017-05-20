package com.hbh.cl.carfriends.talkcar.model;


import com.google.gson.reflect.TypeToken;
import com.hbh.cl.carfriends.talkcar.bean.TalkCar;
import com.hbh.cl.carfriends.util.JsonHelper;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.lang.reflect.Type;

/**
 * Created by hbh on 2017/3/4.
 */

public class TalkCarModel implements ITalkCarModel {

    private static final String url = "http://msn.api.app.yiche.com/api.ashx";
    private TalkCar mTalkCar;

    @Override
    public void loadData(final ITalkCarCallBackListener mITalkCarCallBackListener) {
        RequestParams params = new RequestParams(url);
        params.addQueryStringParameter("method","topic.hottopictop");
        params.addQueryStringParameter("startindex","1");
        params.addQueryStringParameter("pagesize","20");
        params.addQueryStringParameter("ver","7.3");
        params.addQueryStringParameter("appid","17");
        params.addQueryStringParameter("sign","54519f2ad9fba468cee948d40243ac9c");

        x.http().get(params, new Callback.CommonCallback<String>() {

            @Override
            public void onSuccess(String result) {
                Type type = new TypeToken<TalkCar>(){}.getType();
                mTalkCar = JsonHelper.parseObject(result, type);
                mITalkCarCallBackListener.loadSuccess(mTalkCar);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                mITalkCarCallBackListener.loadFiled(ex.getMessage());
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });

    }
}
