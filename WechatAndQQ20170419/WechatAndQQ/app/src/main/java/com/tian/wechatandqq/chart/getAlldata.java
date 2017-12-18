package com.tian.wechatandqq.chart;


import android.util.Log;

import com.github.mikephil.charting.data.PieEntry;
import com.tian.wechatandqq.frame_init.Table.App_information;
import com.tian.wechatandqq.frame_init.Table.Statistics_record;
import com.tian.wechatandqq.utils.Constant;

import org.xutils.DbManager;
import org.xutils.ex.DbException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tian on 2017/3/20.
 */

public class getAlldata {
	/**
	 * App information data list.
	 *
	 * @param database the database
	 * @return the list
	 */
	public static List<PieEntry> app_information_data(DbManager database) {
		//从数据库获取内容
		String string_name;
		int int_time;
		List<Statistics_record> app_informations = new ArrayList<>();
		try {

			List<PieEntry> pieEntries = new ArrayList<>();
			for (int i = 0; i < Constant.PACKAGENAME_SET.length; i++) {
				int time_total = 0;
				app_informations = database.selector(Statistics_record.class).where(Constant.PACKAGE_NAME,
						"=", Constant.PACKAGENAME_SET[i]).findAll();//找到所有记录
				if (app_informations != null) {
					for (int j = 0; j < app_informations.size(); j++) {
						time_total = time_total + app_informations.get(j).getDay_time();
					}
					if (time_total > 5) {
						pieEntries.add(new PieEntry(time_total, app_informations.get(0).getApp_name()));
					}
				}
			}
			return pieEntries;
		} catch (DbException e) {
			e.printStackTrace();
		}

		return null;
	}
//	public static List<PieEntry> app_information_data(DbManager database){
//		//从数据库获取内容
//		String string_name;
//		int int_time;
//		List<Statistics_record> app_informations = new ArrayList<>();
//
//		try {
//			app_informations = database.selector(Statistics_record.class).findAll();
//		} catch (DbException e) {
//			e.printStackTrace();
//		}
//		int dd=0;
//		List<PieEntry> pieEntries=new ArrayList<>();
//		for (int i=0;i<app_informations.size();i++){
//			string_name=app_informations.get(i).getPackage_name();
//			switch (string_name){
//				case Constant.PACKAGE_NAME_QQ:
//					Log.e("dddd",Constant.PACKAGE_NAME_QQ);
//					break;
//				case Constant.PACKAGE_NAME_WeChat:
//					Log.e("dddd",Constant.PACKAGE_NAME_WeChat);
//					break;
//				case Constant.PACKAGE_NAME_PLAT:
//					Log.e("dddd",Constant.PACKAGE_NAME_PLAT);
//					break;
//				case Constant.PACKAGE_NAME_WEIBO:
//					Log.e("dddd",Constant.PACKAGE_NAME_WEIBO);
//					break;
//				case Constant.PACKAGE_NAME_TIEBA:
//					Log.e("dddd",Constant.PACKAGE_NAME_TIEBA);
//					break;
//				case Constant.PACKAGE_NAME_TMALL:
//					Log.e("dddd",Constant.PACKAGE_NAME_TMALL);
//					break;
//				case Constant.PACKAGE_NAME_TAOBAO:
//					Log.e("dddd",Constant.PACKAGE_NAME_TAOBAO);
//					break;
//				case Constant.PACKAGE_NAME_YOUKU:
//					Log.e("dddd",Constant.PACKAGE_NAME_YOUKU);
//					break;
//				case Constant.PACKAGE_NAME_IQIYI:
//					Log.e("dddd",Constant.PACKAGE_NAME_IQIYI);
//					break;
//				case Constant.PACKAGE_NAME_UC:
//					Log.e("dddd",Constant.PACKAGE_NAME_UC);
//					break;
//
//			}
////			int_time=app_informations.get(i).getTotal_time();
////			pieEntries.add(new PieEntry(int_time,string_name));
//			dd++;
//			Log.e("ttt",String.valueOf(dd));
//		}
//		return pieEntries;
//	}
//	private PieEntry getTest(DbManager database,String packagename){
//		String string_name;
//		int int_time=0;
//		int int_frequency=0;
//		List<Statistics_record> app_informations = new ArrayList<>();
//		try {
//			app_informations = database.selector(Statistics_record.class).where(Constant.PACKAGE_NAME
//			,"=",packagename).findAll();
//			if (app_informations==null){
//				Log.e("error","error");
//			}else{
//				for (int i=0;i<app_informations.size();i++){
//					int_time=int_time+app_informations.get(i).getDay_time();
//					int_frequency=int_frequency+app_informations.get(i).getFrequency();
//				}
//			}
//		} catch (DbException e) {
//			e.printStackTrace();
//		}
//	}


}
