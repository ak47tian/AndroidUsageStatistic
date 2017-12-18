package com.tian.wechatandqq.detector;

import android.annotation.TargetApi;
import android.app.AppOpsManager;
import android.content.Context;
import android.os.Build;

/**
 * Created by tian on 2017/2/26.
 */
public class versionCheck {

	public versionCheck() {
	}

	public static boolean postLollipop() {
		return Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP;
	}
	//返回一个boolean值，来判断当前系统的版本来执行不同的检测好函数，5.0一下的使用一种，
	//5.0及其之后，是哟个另一种
	//返回值
	@TargetApi(Build.VERSION_CODES.KITKAT)
	/**
	 * 通过这个来取得Usage相关的权限，来取得当前手机使用应用情况统计的权限
	 * 返回值为真则表示拥有权限，反之则无
	 * @param context the context
	 * @return the boolean
	 */
	public static boolean hasUsageStatsPermission(Context context) {
		AppOpsManager appOps = (AppOpsManager) context.getSystemService(Context.APP_OPS_SERVICE);
		int mode = appOps.checkOpNoThrow("android:get_usage_stats",
				android.os.Process.myUid(), context.getPackageName());
		boolean granted = mode == AppOpsManager.MODE_ALLOWED;
		return granted;
	}
}
