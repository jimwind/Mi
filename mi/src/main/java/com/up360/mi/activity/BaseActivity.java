package com.up360.mi.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Window;

import com.up360.mi.BuildConfig;

public abstract class BaseActivity extends FragmentActivity {
    protected Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        if (BuildConfig.DEBUG) {
            Log.e("jimwind", getClass().getName());
        }
        context = this;
    }
}
