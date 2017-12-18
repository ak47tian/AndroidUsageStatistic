package com.tian.wechatandqq.utils;

/**
 * Created by tian on 2017/4/6.
 */

public class getType {
	public static int appType(String packageName){
		switch (packageName){
			//////////////1111
			case Constant.PACKAGE_NAME_QQ:
				return 1;
			case Constant.PACKAGE_NAME_WeChat:
				return 1;
			case Constant.PACKAGE_NAME_WEIBO:
				return 1;
			case Constant.PACKAGE_NAME_TIEBA:
				return 1;

			case Constant.PACKAGE_NAME_IQIYI:
				return 2;
			case Constant.PACKAGE_NAME_YOUKU:
				return 2;

			case Constant.PACKAGE_NAME_TAOBAO:
				return 3;
			case Constant.PACKAGE_NAME_TMALL:
				return 3;

			case Constant.PACKAGE_NAME_PLAT:
				return 4;

			case Constant.PACKAGE_NAME_UC:
				return 5;

		}
		return 0;
	}
}
