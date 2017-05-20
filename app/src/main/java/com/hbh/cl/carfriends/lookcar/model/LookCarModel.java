package com.hbh.cl.carfriends.lookcar.model;

import com.google.gson.reflect.TypeToken;
import com.hbh.cl.carfriends.lookcar.bean.LookCar;
import com.hbh.cl.carfriends.util.JsonHelper;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.lang.reflect.Type;

/**
 * Created by hbh on 2017/3/9.
 */

public class LookCarModel implements ILookCarModel {

    private String url = "http://api.app.yiche.com/webapi/api.ashx";
    private LookCar mLookCar;

    @Override
    public void LoadHeaderData(final ILookCarCallBackListener listener) {

        RequestParams params = new RequestParams(url);
        params.addQueryStringParameter("method","bit.videocatlist");
        params.addQueryStringParameter("catids","9");
        params.addQueryStringParameter("sign","22fdf38331d801f19015e09f5f6a0b83");

        x.http().get(params, new Callback.CommonCallback<String>(){

            @Override
            public void onSuccess(String result) {
                Type type = new TypeToken<LookCar>(){}.getType();
                mLookCar = JsonHelper.parseObject(result, type);
                listener.onSuccess(mLookCar);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                listener.onError(ex.getMessage());
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

    @Override
    public void LoadBottomData(final ILookCarCallBackListener listener, String carID) {
        RequestParams params = new RequestParams(url);
        params.addQueryStringParameter("method","bit.videocatlist");
        params.addQueryStringParameter("catids",carID);
        params.addQueryStringParameter("sign","22fdf38331d801f19015e09f5f6a0b83");

        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Type type = new TypeToken<LookCar>(){}.getType();
                mLookCar = JsonHelper.parseObject(result,type);
                listener.onSuccess(mLookCar);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                listener.onError(ex.getMessage());
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
