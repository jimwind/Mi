package com.up360.mi.activity;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import com.up360.mi.R;

public class ProviderActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_provider);
        Log.e("jimwind", "ProviderActivity process id is "+android.os.Process.myPid());
        Uri uri = Uri.parse("content://com.up360.mi.provider");
        //每次都会创建新的BookProvider对象，运行在单独的线程中
        getContentResolver().query(uri, null, null, null, null);
        getContentResolver().query(uri, null, null, null, null);
        getContentResolver().query(uri, null, null, null, null);
    }
}
