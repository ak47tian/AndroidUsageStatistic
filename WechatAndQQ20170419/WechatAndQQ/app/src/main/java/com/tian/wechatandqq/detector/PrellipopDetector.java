package com.tian.wechatandqq.detector;

import android.app.ActivityManager;
import android.app.Service;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

/**
 * Created by tian on 2017/2/26.
 * 用来获取5.0之前（即为安卓4.4）的应用程序包名
 */

public class PrellipopDetector implements Detector {
	public String getForegroundApp(Context context) {
		ActivityManager activityManager = (ActivityManager) context.getSystemService(Service.ACTIVITY_SERVICE);
		ActivityManager.RunningTaskInfo foregroundTaskInfo = activityManager.getRunningTasks(1).get(0);
		String foregroundTaskPackageName = foregroundTaskInfo.topActivity.getPackageName();
		PackageManager packageManager = context.getPackageManager();
		PackageInfo foregroundAppPackageInfo = null;
		try {
			foregroundAppPackageInfo = packageManager.getPackageInfo(foregroundTaskPackageName, 0);
		} catch (PackageManager.NameNotFoundException e) {
			e.printStackTrace();
		}
		String foregroundApp = null;
		if (foregroundAppPackageInfo != null) {
			foregroundApp = foregroundAppPackageInfo.applicationInfo.packageName;
		}
		return foregroundApp;
	}
}
