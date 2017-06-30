package com.hbh.cl.carfriends;

import android.app.Application;


import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

import org.xutils.x;

/**
 * Created by hbh on 2017/3/3.
 */

public class MyApplication extends Application {

    private static RefWatcher refWatcher;

    @Override
    public void onCreate() {
        super.onCreate();

        x.Ext.init(this);//��ʼ��
        x.Ext.setDebug(true);//���������־

        refWatcher = LeakCanary.install(this);
    }
    //��ȡ��ض���
    public static RefWatcher getRefWatcher(){
        return refWatcher;
    }
}
