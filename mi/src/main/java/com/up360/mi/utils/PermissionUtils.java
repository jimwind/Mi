package com.up360.mi.utils;

import android.Manifest;
import android.os.Build;

public class PermissionUtils {

    public static final String PERMISSION_AUTHORITY = "com.up360.mi.fileprovider";

    public static final String[] CALENDAR;//日历
    public static final String[] CAMERA;//相机
    public static final String[] CONTACTS;//联系人
    public static final String[] LOCATION;//定位
    public static final String[] MICROPHONE;//麦克风
    public static final String[] PHONE;//手机相关
    public static final String[] SENSORS;//传感器
    public static final String[] SMS;//短信
    public static final String[] STORAGE;//存储

    public static final int PERMISSION_STATUS_GRANTED = 1;
    public static final int PERMISSION_STATUS_DENY_REQUEST = 2;
    public static final int PERMISSION_STATUS_DENY_SETTING = 3;
    public static final int PERMISSION_STATUS_DENY_CANCEL = 4;
    public static final int PERMISSION_STATUS_DENY_RATIONAL = 5;

    static {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            CALENDAR = new String[]{};
            CAMERA = new String[]{};
            CONTACTS = new String[]{};
            LOCATION = new String[]{};
            MICROPHONE = new String[]{};
            PHONE = new String[]{};
            SENSORS = new String[]{};
            SMS = new String[]{};
            STORAGE = new String[]{};
        } else {
            CALENDAR = new String[]{
                    Manifest.permission.READ_CALENDAR,
                    Manifest.permission.WRITE_CALENDAR};

            CAMERA = new String[]{
                    Manifest.permission.CAMERA};

            CONTACTS = new String[]{
                    Manifest.permission.READ_CONTACTS,
                    Manifest.permission.WRITE_CONTACTS,
                    Manifest.permission.GET_ACCOUNTS};

            LOCATION = new String[]{
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION};

            MICROPHONE = new String[]{
                    Manifest.permission.RECORD_AUDIO};

            PHONE = new String[]{
                    Manifest.permission.READ_PHONE_STATE
//                    ,
//                    Manifest.permission.CALL_PHONE,
//                    Manifest.permission.READ_CALL_LOG,
//                    Manifest.permission.WRITE_CALL_LOG,
//                    Manifest.permission.USE_SIP,
//                    Manifest.permission.PROCESS_OUTGOING_CALLS
            };

            SENSORS = new String[]{
                    Manifest.permission.BODY_SENSORS};

            SMS = new String[]{
                    Manifest.permission.SEND_SMS,
                    Manifest.permission.RECEIVE_SMS,
                    Manifest.permission.READ_SMS,
                    Manifest.permission.RECEIVE_WAP_PUSH,
                    Manifest.permission.RECEIVE_MMS};

            STORAGE = new String[]{
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE};
        }
    }



}
