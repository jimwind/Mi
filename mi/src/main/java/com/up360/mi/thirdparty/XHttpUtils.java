package com.up360.mi.thirdparty;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.alibaba.fastjson.TypeReference;
import com.up360.mi.network.INetwork;
import com.up360.mi.network.ResponseResult;

import org.xutils.common.Callback;
import org.xutils.http.HttpMethod;
import org.xutils.http.RequestParams;
import org.xutils.x;

public class XHttpUtils implements INetwork {

    @Override
    public void post(final Handler handler, final int id, String moJson, String url, TypeReference typeReference) {
        RequestParams params = new RequestParams(url);
        params.addQueryStringParameter("moJson", moJson);
        x.http().request(HttpMethod.POST, params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.e("jimwind", "[x] onSuccess result:"+result);
                Message message = new Message();
                message.what = id;
                message.obj = result;
                handler.sendMessage(message);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.e("jimwind", "[x] onError "+ ex.getMessage());
                Message message = new Message();
                message.what = id;
//                message.obj = result;
                handler.sendMessage(message);

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

    public void get(String moJson, String url){
        RequestParams params = new RequestParams(url);
        params.addQueryStringParameter("moJson", moJson);

        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.e("jimwind", "[x] onSuccess result:"+result);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });

    }


}
