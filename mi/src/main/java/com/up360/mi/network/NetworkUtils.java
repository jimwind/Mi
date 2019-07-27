package com.up360.mi.network;

import android.util.Log;

import com.alibaba.fastjson.JSON;

import java.security.MessageDigest;
import java.util.HashMap;

public class NetworkUtils {
    public static String getMoJson(String requestUrl, Object paramsObject){
        return "见笔记";
    }
    public static String stringToMD5(String str){
        try {
            byte[] strTemp=str.getBytes();
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(strTemp);
            return toHexString(md.digest());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    private static String toHexString(byte[] md) {
        char[] hexDigits={'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f',};
        int j = hexDigits.length;
        char[] str = new char[j*2];
        for (int i = 0; i < j; i++) {
            byte byteo = md[i];
            str[2*i]=hexDigits[byteo>>>4 & 0xf];
            str[2*i+1]=hexDigits[byteo & 0xf];
        }
        return new String(str);
    }
}
