package com.up360.mi.application;


import android.app.Application;
import android.util.Log;

import com.up360.mi.UPCrashHandler;

import org.xutils.x;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Log.e("jimwind", "application create");
        x.Ext.init(this);
        x.Ext.setDebug(true);
        UPCrashHandler.getInstance().init(this);
    }
}
