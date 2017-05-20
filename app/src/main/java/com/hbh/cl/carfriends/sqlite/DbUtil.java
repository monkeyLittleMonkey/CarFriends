package com.hbh.cl.carfriends.sqlite;

import android.os.Environment;

import org.xutils.DbManager;
import org.xutils.x;

import java.io.File;

/**
 * Created by hbh on 2017/4/6.
 * 数据库操作工具类
 * 获取DbManager实例
 */

public class DbUtil {

    private static DbManager dbManager;
    private static DbManager.DaoConfig daoConfig;
    private final String dbName = "CarFriends.db";
    private final int dbVersion = 1;

    private DbUtil() {
        daoConfig = new DbManager.DaoConfig();
        daoConfig.setDbName(dbName)
                .setDbVersion(dbVersion)
                .setDbDir(new File(Environment.getExternalStorageDirectory().getAbsolutePath()))
                .setAllowTransaction(true)
                .setDbOpenListener(new DbManager.DbOpenListener() {
                    @Override
                    public void onDbOpened(DbManager db) {
                        db.getDatabase().enableWriteAheadLogging();
                    }
                })
                .setDbUpgradeListener(new DbManager.DbUpgradeListener() {
                    @Override
                    public void onUpgrade(DbManager db, int oldVersion, int newVersion) {

                    }
                });
        dbManager = x.getDb(daoConfig);
    }

    public static DbManager getInstence(){
        if (dbManager == null) {
            DbUtil dbUtil = new DbUtil();
        }
        return dbManager;
    }
}
