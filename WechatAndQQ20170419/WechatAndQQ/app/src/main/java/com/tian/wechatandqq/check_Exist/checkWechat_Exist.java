package com.tian.wechatandqq.check_Exist;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.Log;

import java.util.List;


/**
 * 此类用于检测手机上是否安装手机QQ
 */
public class checkWechat_Exist {

    /**
     * Is wechat exist boolean.
     *
     * @param context 上下文，提供了关于应用环境全局信息的接口
     * @return boolean 返回一个布尔值，用于判断安装的状态
     */
    public static boolean isWechat_Exist(Context context){

        PackageManager packageManager = context.getPackageManager();
        List<PackageInfo> packageInfos = packageManager.getInstalledPackages(0);
        if (packageInfos != null){

            for (int i = 0; i < packageInfos.size(); i++){
                String pn = packageInfos.get(i).packageName;
                if (pn.equals("com.tencent.mm")){
                    return  true;
                    //找到目标APK包，微信已安装
                }
            }
        }
        Log.e("WeChat","WeChat未安装");
        return  false;
    }
}
