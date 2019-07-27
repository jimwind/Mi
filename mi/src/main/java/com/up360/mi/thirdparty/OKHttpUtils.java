package com.up360.mi.thirdparty;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.alibaba.fastjson.TypeReference;
import com.up360.mi.network.INetwork;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class OKHttpUtils implements INetwork {
    private OkHttpClient okHttpClient = new OkHttpClient();

    @Override
    public void post(final Handler handler, final int id, String moJson, String url, TypeReference typeReference) {
        RequestBody requestBody = new FormBody.Builder()
                .add("moJson", moJson)
                .build();
        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .addHeader("token", "up360.com")
                .build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("jimwind", "[okhttp]onFailure "+e.getMessage());
                Message message = new Message();
                message.what = id;
//            message.obj = result;
                handler.sendMessage(message);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.e("jimwind", "[okhttp]onResponse "+response.body().string());
                Message message = new Message();
                message.what = id;
                message.obj = response.body().string();
                handler.sendMessage(message);
            }
        });
        Log.e("jimwind", "[okhttp]post");
    }
}
