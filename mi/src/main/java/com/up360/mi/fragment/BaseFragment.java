package com.up360.mi.fragment;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * @ClassName BaseFragment
 * @Description 用于fragemnt的友盟访问信息的统计
 * @author jinyb
 * @date 2014-8-6
 */
public abstract class BaseFragment extends Fragment implements Handler.Callback  {
	public Handler handler;
	protected Context context;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		context = getActivity();
		handler = new Handler(this);
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
		return super.onCreateView(inflater, container, savedInstanceState);
	}
	@Override
	public void onResume() {
		super.onResume();
	}

	@Override
	public void onPause() {
		super.onPause();
	}
	

	protected void init(){
		loadViewLayout();
		setListener();	
		initData();
	}
	/**
	 * 功能描述：<加载页面View>
	 */
	protected abstract void loadViewLayout();

	/**
	 * 功能描述：<初始化数据>
	 */
	protected abstract void initData();

	/**
	 * 功能描述：<设置监听>
	 */
	protected abstract void setListener();	
	
}
