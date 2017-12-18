package com.tian.wechatandqq.frame_init.Table;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import java.util.List;

/**
 * Created by tian on 2017/3/29.
 */

public class init_app_information {
	public static boolean check_init(Context context){
		PackageManager packageManager = context.getPackageManager();
		List<PackageInfo> packageInfos = packageManager.getInstalledPackages(0);

		return false;
	}
}
