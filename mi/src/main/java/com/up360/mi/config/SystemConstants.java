package com.up360.mi.config;

import android.os.Environment;

public class SystemConstants {
    public static final String APP_SDCARD_PATH =
            Environment.getExternalStorageDirectory().getAbsolutePath()
                    + "/mi.gao/";
    public static final String LOG_DIR = "log/";
}
