package com.tian.wechatandqq.detector;

import android.content.Context;

/**
 * Created by tian on 2017/2/26.
 *
 */

public interface Detector {
	String getForegroundApp(Context context);
	//定义一个获取前台运行程序的app的接口
}
