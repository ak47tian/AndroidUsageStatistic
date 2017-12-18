package com.tian.wechatandqq.frame_init;

import com.tian.wechatandqq.utils.Constant;

/**
 * Created by tian on 2017/4/3.
 *
 */

public class get_AppName {
	public static String getName(String string){
		switch (string){
			case Constant.PACKAGE_NAME_IQIYI:
				return "爱奇艺";
			case Constant.PACKAGE_NAME_PLAT:
				return "同花顺";
			case Constant.PACKAGE_NAME_WEIBO:
				return "微博";
			case Constant.PACKAGE_NAME_QQ:
				return "QQ";
			case Constant.PACKAGE_NAME_WeChat:
				return "微信";
			case Constant.PACKAGE_NAME_TMALL:
				return "天猫";
			case Constant.PACKAGE_NAME_TAOBAO:
				return "淘宝";
			case Constant.PACKAGE_NAME_TIEBA:
				return "贴吧";
			case Constant.PACKAGE_NAME_UC:
				return "UC";
			case Constant.PACKAGE_NAME_YOUKU:
				return  "优酷";
			default:
				break;
		}


		return "xx";
	}
}
