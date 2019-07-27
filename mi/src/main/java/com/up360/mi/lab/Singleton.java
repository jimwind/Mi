package com.up360.mi.lab;

import android.util.Log;

public class Singleton {
//    private static Singleton instance = new Singleton();
//
//    private Singleton() {
//        Log.e("jimwind", "Singleton()");
//    }
//
//    public static Singleton getInstance() {
//        return instance;
//    }


    private static class SingletonHolder {
        private static final Singleton INSTANCE = new Singleton();
    }

    private Singleton() {
        Log.e("jimwind", "Singleton()");
    }

    public static final Singleton getInstance() {
        return SingletonHolder.INSTANCE;
    }
}
