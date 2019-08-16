package com.up360.mi.activity.hencoder;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;

import com.up360.mi.R;
import com.up360.mi.activity.BaseActivity;

public class ui_1_1_activity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hencoder_ui_1_1);
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        Log.e("jimwind", "mi am "+am);
    }
}
