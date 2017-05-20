package com.hbh.cl.carfriends;

import android.app.Application;


import com.squareup.leakcanary.LeakCanary;

import org.xutils.x;

/**
 * Created by hbh on 2017/3/3.
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        x.Ext.init(this);//初始化
        x.Ext.setDebug(true);//设置输出日志

        LeakCanary.install(this);
    }
}
