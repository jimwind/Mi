package com.up360.mi.network;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.alibaba.fastjson.TypeReference;
import com.up360.mi.R;
import com.up360.mi.character.bean.CharacterSingleDetailBean;
import com.up360.mi.thirdparty.OKHttpUtils;
import com.up360.mi.thirdparty.XHttpUtils;

import java.util.HashMap;
import java.util.Map;

public class RequestMode implements Handler.Callback{
    private Handler handler = new Handler(this);
    private ResponseMode mResponseMode;

    public RequestMode(Context context, ResponseMode responseMode){
        mResponseMode = responseMode;
    }

    private INetwork getInstance(){
//        return new OKHttpUtils();
        return new XHttpUtils();
    }

    public void login(String account, String password){
        HashMap<String, Object> paramsMap = new HashMap<>();
        paramsMap.put("account", account);
        paramsMap.put("password", NetworkUtils.stringToMD5(password));
        String moJson = NetworkUtils.getMoJson(HttpConstant.HTTP_LOGIN, paramsMap);

        String url = "https://mobile.up360.com" + HttpConstant.HTTP_LOGIN;
        INetwork iNetwork = getInstance();
        iNetwork.post(handler, R.id.login, moJson, url, new TypeReference<ResponseResult<String>>(){});
    }

    public void getSingleCharacter(long studentUserId, long lessonWordId){
        Map<String, Object> paramsMap = new HashMap<String, Object>();
        paramsMap.put("lessonWordId", lessonWordId);
        paramsMap.put("studentUserId", studentUserId);
        String moJson = NetworkUtils.getMoJson(HttpConstant.HTTP_GET_CHARACTER_GINGLE_DETAIL_AUTO, paramsMap);

        String url = "https://mobile.up360.com" + HttpConstant.HTTP_GET_CHARACTER_GINGLE_DETAIL_AUTO;
        INetwork iNetwork = getInstance();
        iNetwork.post(handler, R.id.getAutoSingleCharacterDetail, moJson, url, new TypeReference<ResponseResult<CharacterSingleDetailBean>>(){});
    }


    @Override
    public boolean handleMessage(Message msg) {
        switch (msg.what){
            case R.id.login:{
                String response = (String) msg.obj;
                Log.e("jimwind", "login response: "+response);
                mResponseMode.onLogin();
                break;
            }
            case R.id.getAutoSingleCharacterDetail:{
                String response = (String) msg.obj;
                ResponseResult<CharacterSingleDetailBean> result
                        = JsonParseUtils.jsonToObject(response, new TypeReference<ResponseResult<CharacterSingleDetailBean>>(){});
                mResponseMode.onGetCharacter(result.getData());
                Log.e("jimwind", "character "+response);
                break;
            }
        }
        return false;
    }
}

