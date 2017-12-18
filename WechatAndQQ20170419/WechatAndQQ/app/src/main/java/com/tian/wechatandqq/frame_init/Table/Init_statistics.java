package com.tian.wechatandqq.frame_init.Table;

import android.util.Log;

import com.tian.wechatandqq.utils.Constant;
import com.tian.wechatandqq.utils.MyDate;

import org.xutils.DbManager;
import org.xutils.ex.DbException;

/**
 * Created by tian on 2017/3/29.
 */

public class Init_statistics {
	public static void initStatistics(DbManager dbManager) {
		Statistics_record record = new Statistics_record();
		for (int i = 0; i < Constant.PACKAGENAME_SET.length; i++) {
			Log.e(Constant.PACKAGENAME_SET[i]+"555555",Constant.PACKAGENAME_SET[i]);
			record.package_name = Constant.PACKAGENAME_SET[i];
			record.date_use = MyDate.getMyDate();
			record.frequency = 0;
			record.day_time_7_10 = 0;
			record.day_time_11_14 = 0;
			record.day_time_15_18 = 0;
			record.day_time_19_22 = 0;
			record.day_time_other = 0;
			record.day_time = 0;
			record.run_flag = Constant.NOTRUN_FLAG;
			try {
				dbManager.save(record);
			} catch (DbException e) {
				e.printStackTrace();
			}
		}
	}
}
