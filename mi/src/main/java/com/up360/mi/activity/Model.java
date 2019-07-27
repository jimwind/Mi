package com.up360.mi.activity;

import android.content.Context;
import android.graphics.ImageDecoder;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

import com.up360.mi.network.RequestMode;
import com.up360.mi.network.ResponseMode;

//我们可以把一个xml看作一个View, Activity看作一个Control, Model则是由相关的数据操作类来承担。
public class Model {

    public interface OnLoginListener {
        void onLogin();
    }
    private OnLoginListener mOnLoginListener;
    public void setOnLoginListener(OnLoginListener listener){
        mOnLoginListener = listener;
    }
    public Model(Context context){
        this.mContext = context;
        mRequestMode = new RequestMode(context, new ResponseMode() {
            @Override
            public void onLogin() {
                super.onLogin();
                if(mOnLoginListener != null){
                    mOnLoginListener.onLogin();
                }
            }
        });
    }


    public void login(String account, String password){
        mRequestMode.login(account, password);
    }

    private Context mContext;
    private RequestMode mRequestMode;
}
