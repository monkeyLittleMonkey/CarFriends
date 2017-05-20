package com.hbh.cl.carfriends.buycar.model;

import com.google.gson.reflect.TypeToken;
import com.hbh.cl.carfriends.buycar.bean.ECar;
import com.hbh.cl.carfriends.buycar.bean.HybridCar;
import com.hbh.cl.carfriends.buycar.bean.NewCar;
import com.hbh.cl.carfriends.buycar.bean.CarNews;
import com.hbh.cl.carfriends.buycar.bean.OldCar;
import com.hbh.cl.carfriends.util.JsonHelper;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.lang.reflect.Type;

/**
 * Created by hbh on 2017/3/22.
 */

public class BuyCarModel implements IBuyCarModel {

    private String newCarurl = "http://api.app.yiche.com/webapi/api.ashx";//上市新车
    private String newsUrl = "http://news.app.yiche.com/api.ashx";//新能源车  新闻
    private String ECarUrl = "http://api.app.yiche.com/WebAPI/Api.ashx";//新能源车 电动车
    private String HybridUrl = "http://api.app.yiche.com/WebAPI/Api.ashx";//新能源车 混动车
    private String oldCarUrl = "http://taoche.app.yiche.com/CarList.ashx";//优质二手车
    private NewCar mNewCar; //上市新车
    private CarNews mCarNews;  //新能源车 新闻
    private ECar mECar;//新能源车 电动车
    private HybridCar mHybridCar;//新能源车 混动车
    private OldCar mOldCar; //优质二手车

    @Override
    public void LoadNewCarData(final INewCarCallBackListener callBackListener) {
        RequestParams params = new RequestParams(newCarurl);
        params.addQueryStringParameter("method","bit.newcars");
        params.addQueryStringParameter("op","carlist");
        params.addQueryStringParameter("rid","0");
        params.addQueryStringParameter("pageIndex","1");
        params.addQueryStringParameter("pageSize","20");
        params.addQueryStringParameter("sign","3413579ac26011eddb0d659413b11e96");

        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Type type = new TypeToken<NewCar>(){}.getType();
                mNewCar = JsonHelper.parseObject(result, type);
                callBackListener.onNewCarSuccess(mNewCar);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                callBackListener.onError(ex.getMessage());
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
    public void LoadECarNewsData(final ICarNewsCallBackListener callBackListener) {
        RequestParams params = new RequestParams(newsUrl);
        params.addQueryStringParameter("method","news.list");
        params.addQueryStringParameter("cateid","28");
        params.addQueryStringParameter("serialid","");
        params.addQueryStringParameter("pageindex","1");
        params.addQueryStringParameter("pagesize","20");
        params.addQueryStringParameter("productid","17");

        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Type type = new TypeToken<CarNews>(){}.getType();
                mCarNews = JsonHelper.parseObject(result, type);

                callBackListener.onCarNewsSuccess(mCarNews);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                callBackListener.onError(ex.getMessage());
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
    public void LoadECarEData(final IECarCallBackListener callBackListener) {
        RequestParams params = new RequestParams(ECarUrl);
        params.addQueryStringParameter("op","1");
        params.addQueryStringParameter("method","bit.newenergycar");
        params.addQueryStringParameter("pageindex","1");
        params.addQueryStringParameter("pagesize","20");
        params.addQueryStringParameter("sign","75288c2dd54f781a7808b05af7ea2ecc");

        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Type type = new TypeToken<ECar>(){}.getType();
                mECar = JsonHelper.parseObject(result, type);
                callBackListener.loadSuccess(mECar);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                callBackListener.error(ex.getMessage());
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
    public void LoadECarHData(final IHybridCarCallBackListener callBackListener) {
        RequestParams params = new RequestParams(HybridUrl);
        params.addQueryStringParameter("op","2");
        params.addQueryStringParameter("method","bit.newenergycar");
        params.addQueryStringParameter("pageindex","1");
        params.addQueryStringParameter("pagesize","20");
        params.addQueryStringParameter("sign","11e10e92959c61404766f744f5cb3a58");
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Type type = new TypeToken<HybridCar>(){}.getType();
                mHybridCar = JsonHelper.parseObject(result, type);
                callBackListener.loadSuccess(mHybridCar);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                callBackListener.error(ex.getMessage());
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
    public void loadOldCarData(final IOldCarCallBackListener callBackListener) {
        RequestParams params = new RequestParams(oldCarUrl);
        params.addQueryStringParameter("cid","2101");
        params.addQueryStringParameter("pindex","1");
        params.addQueryStringParameter("psize","20");
        params.addQueryStringParameter("sign","bc2886e543f93a6dea4b0ced2eb82277");
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Type type = new TypeToken<OldCar>(){}.getType();
                mOldCar = JsonHelper.parseObject(result, type);
                callBackListener.loadDataSuccess(mOldCar);
                for (int i = 0; i < mOldCar.CarList.size(); i++) {
                    mOldCar.CarList.get(i).setType(0);
                    mOldCar.CarList.get(i).setChildren(mOldCar.CarList.get(i));
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                callBackListener.error(ex.getMessage());
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
