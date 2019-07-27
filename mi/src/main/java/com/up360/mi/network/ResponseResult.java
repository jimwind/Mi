package com.up360.mi.network;


public class ResponseResult<T> {
	/**
	 * 返回状态 1 成功 0 失败
	 */
	private int result;
	
	/***
	 * 当info不为0时，描述具体错误原因
	 */
	private String msg = "";
	
	private String testMsg;
	
	/**
	 * 返回信息
	 */
	private T data;

	public int getResult() {
		return result;
	}

	public void setResult(int result) {
		this.result = result;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getTestMsg() {
		return testMsg;
	}

	public void setTestMsg(String testMsg) {
		this.testMsg = testMsg;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
}
