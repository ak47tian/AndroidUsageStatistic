package com.tian.wechatandqq.frame_init;

import android.app.Application;
import android.util.Log;

import org.xutils.x;

/**
 * Created by tian on 2017/3/16.
 */

public class application extends Application {
	@Override
	public void onCreate() {
		super.onCreate();
		x.Ext.init(this);
		Log.e("tag", "初始化框架");
		x.Ext.setDebug(true);
	}
}
