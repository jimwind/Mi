package com.up360.mi.network;

import android.os.Handler;

import com.alibaba.fastjson.TypeReference;

public interface INetwork<T> {
    void post(Handler handler, int id, String moJson, String url, TypeReference<ResponseResult<T>> typeReference);
}
