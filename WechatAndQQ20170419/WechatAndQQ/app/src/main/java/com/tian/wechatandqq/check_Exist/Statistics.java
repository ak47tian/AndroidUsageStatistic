package com.tian.wechatandqq.check_Exist;

import com.tian.wechatandqq.frame_init.Table.App_information;
import com.tian.wechatandqq.utils.Constant;
import com.tian.wechatandqq.utils.MyDate;

import org.xutils.DbManager;
import org.xutils.ex.DbException;

/**
 * Created by tian on 2017/3/17.
 */

public class Statistics {
//	public static boolean check_date_qq(DbManager database){
//		try {
//			Mobileqq mobileqq= database.selector(Mobileqq.class).where(Constant.DATE_USE,"=", MyDate.getMyDate()).findFirst();
//			if (mobileqq!=null){
//				App_information app_information=database.selector(App_information.class).where(Constant.PACKAGE_NAME,"=",Constant.PACKAGE_NAME_QQ).findFirst();
//				if (app_information!=null){
//					app_information.statistics_days=app_information.statistics_days+Constant.ONCE;
//					database.saveOrUpdate(app_information);
//				}
//			}
//		} catch (DbException e) {
//			e.printStackTrace();
//		}
//
//		return false;
//	}
//	public static boolean check_date_mm(DbManager database){
//		try {
//			MM mm= database.selector(MM.class).where(Constant.DATE_USE,"=", MyDate.getMyDate()).findFirst();
//			if (mm!=null){
//				App_information app_information=database.selector(App_information.class).where(Constant.PACKAGE_NAME,"=",Constant.PACKAGE_NAME_QQ).findFirst();
//				if (app_information!=null){
//					app_information.statistics_days=app_information.statistics_days+Constant.ONCE;
//					database.saveOrUpdate(app_information);
//				}
//			}
//		} catch (DbException e) {
//			e.printStackTrace();
//		}
//		return false;
//	}

}
