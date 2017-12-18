package com.tian.wechatandqq.utils;

import android.content.Context;

import org.xutils.DbManager;
import org.xutils.db.sqlite.SqlInfo;
import org.xutils.ex.DbException;
import org.xutils.x;

/**
 * Created by tian on 2017/3/16.
 */

public class DbUtils {
	public static DbManager.DaoConfig getConfig(Context context){
		DbManager.DaoConfig daoConfig = new DbManager.DaoConfig()
				//设置数据库名，默认xutils.db
				.setDbName(Constant.DATABASE_NAME)
				.setDbVersion(3)
				//设置数据库路径，默认安装程序路径下
				.setDbDir(context.getDir("database", Context.MODE_PRIVATE))
				//设置数据库更新的监听
				.setDbUpgradeListener(new DbManager.DbUpgradeListener() {
					@Override
					public void onUpgrade(DbManager db, int oldVersion,
					                      int newVersion) {

					}
				})
				//设置数据库打开的监听
				.setDbOpenListener(new DbManager.DbOpenListener() {
					@Override
					public void onDbOpened(DbManager db) {
						//开启数据库支持多线程操作，提升性能
						db.getDatabase().enableWriteAheadLogging();
					}
				});
		return daoConfig;
	}
	public static void savemyDb(Context context,Object object){
		DbManager manager=x.getDb(getConfig(context));
		try {
			manager.save(object);
		} catch (DbException e) {
			e.printStackTrace();
		}
	}

}
