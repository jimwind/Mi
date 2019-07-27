package com.up360.mi.network;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

import org.json.JSONException;
import org.json.JSONObject;


public class JsonParseUtils {
	public static <T> ResponseResult<T> jsonToObject(String json, TypeReference<ResponseResult<T>> type){
		ResponseResult<T> result = null;
		try{
			 result = JSON.parseObject(json,type);
		}catch (Exception e) {
			result  = new ResponseResult<T>();
			result.setResult(HttpResponseStatus.ERR_JSON);
            e.printStackTrace();
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 
	 * @Method: getDataObject 
	 * @Description: 解析获取data对象
	 * @param @param json 返回json字符串
	 * @return JSONObject 返回data jsonobject
	 * @throws
	 */
	private static JSONObject getDataObject(String json){
		JSONObject dataObject = null;
		try {
			JSONObject jsonObject = new JSONObject(json);
			dataObject = jsonObject.getJSONObject("data");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dataObject;
	}
}
