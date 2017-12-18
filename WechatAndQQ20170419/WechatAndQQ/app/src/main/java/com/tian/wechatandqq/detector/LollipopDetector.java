package com.tian.wechatandqq.detector;

import android.annotation.TargetApi;
import android.app.Service;
import android.app.usage.UsageEvents;
import android.app.usage.UsageStatsManager;
import android.content.Context;
import android.os.Build;

/**
 * Created by tian on 2017/2/26.
 * 一个用来检测5.0及其之后系统版本前台应用 程序的包名
 */

public class LollipopDetector implements Detector {
	@TargetApi(Build.VERSION_CODES.LOLLIPOP)

	public String getForegroundApp(Context context) {
		if (!versionCheck.hasUsageStatsPermission(context))
			return null;
		String foregroundApp = null;
		UsageStatsManager usageStatsManager = (UsageStatsManager) context.getSystemService(Service.USAGE_STATS_SERVICE);
		long time = System.currentTimeMillis();
		UsageEvents usageEvents = usageStatsManager.queryEvents(time - 1000 * 3600, time);
		UsageEvents.Event event = new UsageEvents.Event();
		while (usageEvents.hasNextEvent()) {
			usageEvents.getNextEvent(event);
			if (event.getEventType() == UsageEvents.Event.MOVE_TO_FOREGROUND) {
				foregroundApp = event.getPackageName();
			}
		}
		return foregroundApp;
	}
}
