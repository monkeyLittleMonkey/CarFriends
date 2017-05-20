package com.hbh.cl.carfriends.buycar.model;

import com.hbh.cl.carfriends.buycar.bean.CarNews;

/**
 * Created by hbh on 2017/3/23.
 */

public interface ICarNewsCallBackListener {

    /**
     * 新能源车 新闻资讯数据加载成功
     * @param carNews
     */
    void onCarNewsSuccess(CarNews carNews);

    /**
     * 数据加载失败
     * @param message
     */
    void onError(String message);
}
