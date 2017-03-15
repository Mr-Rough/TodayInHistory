package com.example.todayinhistory.application;

import android.app.Application;

/**
 * Created by win7 on 2017/3/15.
 * 描述:
 * 作者:小智 win7
 */

public class MyApp extends Application {
    private static MyApp instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;

    }

    public static MyApp getInstance() {
        return instance;
    }


}
