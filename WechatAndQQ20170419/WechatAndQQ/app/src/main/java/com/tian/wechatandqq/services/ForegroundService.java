package com.tian.wechatandqq.services;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v7.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;

import com.tian.wechatandqq.R;
import com.tian.wechatandqq.detector.AppChecker;
import com.tian.wechatandqq.frame_init.Table.Statistics_record;
import com.tian.wechatandqq.frame_init.get_AppName;
import com.tian.wechatandqq.utils.Constant;
import com.tian.wechatandqq.utils.DbUtils;
import com.tian.wechatandqq.utils.MyDate;
import com.tian.wechatandqq.utils.getType;

import org.xutils.DbManager;
import org.xutils.ex.DbException;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

public class ForegroundService extends Service {


	DbManager database;
	int YES = 1;
	int NO = 0;

	private final static int NOTIFICATION_ID = 1234;
	private final static String STOP_SERVICE = ForegroundService.class.getPackage() + ".stop";
	private BroadcastReceiver stopServiceReceiver;
	private AppChecker appChecker;

	public static void start(Context context) {
		context.startService(new Intent(context, ForegroundService.class));
	}

	public static void stop(Context context) {
		context.stopService(new Intent(context, ForegroundService.class));
	}


	@Nullable
	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	@Override
	public void onCreate() {
		super.onCreate();
		database = x.getDb(DbUtils.getConfig(this));
		registerReceivers();
		startChecker();
		createStickyNotification();
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		stopChecker();
		removeNotification();
		unregisterReceivers();
		stopSelf();
	}

	private void startChecker() {
		appChecker = new AppChecker();
		appChecker
				//监控程序自己的包
				.when(getPackageName(), new AppChecker.Listener() {
					@Override
					public void onForeground(String packageName) {
						Toast.makeText(getBaseContext(), "分析软件运行于前台", Toast.LENGTH_SHORT).show();
						Log.e("tag", "自己的程序");
						//仅弹出提示，用于判断程序是否运行
					}
				})
				//检测其他的包名，用于辅助改变微信和qq的运行状态
				.other(new AppChecker.Listener() {
					@Override
					public void onForeground(String packageName) {
						switch (packageName) {
							case Constant.PACKAGE_NAME_QQ: {
								refreshStatistics(Constant.PACKAGE_NAME_QQ);
								changeOtherFlag(Constant.PACKAGE_NAME_QQ);
								Log.e("QQ", packageName);
							}
							break;
							case Constant.PACKAGE_NAME_WeChat: {
								refreshStatistics(Constant.PACKAGE_NAME_WeChat);
								changeOtherFlag(Constant.PACKAGE_NAME_WeChat);
								Log.e("WeChat", packageName);
							}
							break;
							case Constant.PACKAGE_NAME_PLAT: {
								refreshStatistics(Constant.PACKAGE_NAME_PLAT);
								changeOtherFlag(Constant.PACKAGE_NAME_PLAT);
								Log.e("同花顺", packageName);
							}
							break;
							case Constant.PACKAGE_NAME_WEIBO: {
								refreshStatistics(Constant.PACKAGE_NAME_WEIBO);
								changeOtherFlag(Constant.PACKAGE_NAME_WEIBO);
								Log.e("微博", packageName);
							}
							break;
							case Constant.PACKAGE_NAME_TIEBA: {
								refreshStatistics(Constant.PACKAGE_NAME_TIEBA);
								changeOtherFlag(Constant.PACKAGE_NAME_TIEBA);
								Log.e("贴吧", packageName);
							}
							break;
							case Constant.PACKAGE_NAME_TMALL: {
								refreshStatistics(Constant.PACKAGE_NAME_TMALL);
								changeOtherFlag(Constant.PACKAGE_NAME_TMALL);
								Log.e("天猫", packageName);
							}
							break;
							case Constant.PACKAGE_NAME_TAOBAO: {
								refreshStatistics(Constant.PACKAGE_NAME_TAOBAO);
								changeOtherFlag(Constant.PACKAGE_NAME_TAOBAO);
								Log.e("淘宝", packageName);
							}
							break;
							case Constant.PACKAGE_NAME_YOUKU: {
								refreshStatistics(Constant.PACKAGE_NAME_YOUKU);
								changeOtherFlag(Constant.PACKAGE_NAME_YOUKU);
								Log.e("优酷", packageName);
							}
							break;
							case Constant.PACKAGE_NAME_IQIYI: {
								refreshStatistics(Constant.PACKAGE_NAME_IQIYI);
								changeOtherFlag(Constant.PACKAGE_NAME_IQIYI);
								Log.e("爱奇艺", packageName);
							}
							break;
							case Constant.PACKAGE_NAME_UC: {
								refreshStatistics(Constant.PACKAGE_NAME_UC);
								changeOtherFlag(Constant.PACKAGE_NAME_UC);
								Log.e("UCWEB", packageName);
							}
							break;
							default: {
								changeOtherFlag("aaaaa");
								Log.e("default", "default");
							}
							break;
						}

					}
				})
				.timeout(5000)
				.start(this);
	}

	private void changeOtherFlag(String packageName) {
		try {
			List<Statistics_record> list = new ArrayList<>();
			list = database.selector(Statistics_record.class).where(Constant.DATE_USE,
					"=", MyDate.getMyDate()).findAll();
			if (list==null){
				return;
			}
			for (int i = 0; i < list.size(); i++) {
				if (!list.get(i).getPackage_name().equals(packageName)) {
					Log.e("test2",packageName);
					list.get(i).run_flag=NO;
					database.saveOrUpdate(list.get(i));
				}
			}
		} catch (DbException e) {
			e.printStackTrace();
		}

	}


	private void refreshStatistics(String packageName) {
		try {

			//首先查找今日记录
			Statistics_record record_today = database.selector(Statistics_record.class)
					.where(Constant.DATE_USE, "=", MyDate.getMyDate())
					.and(Constant.PACKAGE_NAME, "=", packageName).findFirst();

			if (record_today == null) {
				//如果今日记录为空，则初始化某条的今日记录
				refresh_today(packageName);

			} else {
				String tmp=String.valueOf(record_today.run_flag);
				Log.e("test",tmp);
				Log.e("1111111111","11111111111");
				System.out.println(record_today.date_use+" "+record_today.day_time+" "+record_today.day_time_7_10+" "
				+record_today.day_time_11_14+" "+record_today.day_time_15_18+" "+record_today.day_time_19_22+" " +
				record_today.day_time_other+" "+record_today.frequency+" " +record_today.package_name+" "+
				record_today.run_flag+" " +record_today.app_name+" "+record_today.type);
				Log.e("222222222","222222222");
				//如果正在运行状态中
				if (record_today.run_flag == YES) {
					Log.e("test","flag==yes");
					switch (MyDate.getTime()) {
						case 1: {
							record_today.day_time_7_10 = record_today.day_time_7_10 + 5;
							record_today.day_time = record_today.day_time + 5;
							database.saveOrUpdate(record_today);
						}
						break;
						case 2: {
							record_today.day_time_11_14 = record_today.day_time_11_14 + 5;
							record_today.day_time = record_today.day_time + 5;
							database.saveOrUpdate(record_today);
						}
						break;
						case 3: {
							record_today.day_time_15_18 = record_today.day_time_15_18 + 5;
							record_today.day_time = record_today.day_time + 5;
							database.saveOrUpdate(record_today);
						}
						break;
						case 4: {
							record_today.day_time_19_22 = record_today.day_time_19_22 + 5;
							record_today.day_time = record_today.day_time + 5;
							database.saveOrUpdate(record_today);
						}
						break;
						case 5: {
							record_today.day_time_other = record_today.day_time_other + 5;
							record_today.day_time = record_today.day_time + 5;
							database.saveOrUpdate(record_today);
						}
						break;
					}

				} else if (record_today.run_flag == NO) {
					Log.e("test","flag==no");
					switch (MyDate.getTime()) {
						case 1: {
							record_today.run_flag = Constant.YES;
							record_today.frequency = record_today.frequency + 1;
							record_today.day_time_7_10 = record_today.day_time_7_10 + 5;
							record_today.day_time = record_today.day_time + 5;
							database.saveOrUpdate(record_today);
							Log.e("test","flag==111111");

						}
						break;
						case 2: {
							record_today.run_flag = Constant.YES;
							record_today.frequency = record_today.frequency + 1;
							record_today.day_time_11_14 = record_today.day_time_11_14 + 5;
							record_today.day_time = record_today.day_time + 5;
							database.saveOrUpdate(record_today);
							System.out.print(record_today);
							Log.e("test","flag==22222");

						}
						break;
						case 3: {
							record_today.run_flag = Constant.YES;
							record_today.frequency = record_today.frequency + 1;
							record_today.day_time_15_18 = record_today.day_time_15_18 + 5;
							record_today.day_time = record_today.day_time + 5;
							database.saveOrUpdate(record_today);
							System.out.print(record_today);
							Log.e("test","flag==33333");

						}
						break;
						case 4: {
							record_today.run_flag = Constant.YES;
							record_today.frequency = record_today.frequency + 1;
							record_today.day_time_19_22 = record_today.day_time_19_22 + 5;
							record_today.day_time = record_today.day_time + 5;
							database.saveOrUpdate(record_today);
							System.out.print(record_today);
							Log.e("test","flag==44444");

						}
						break;
						case 5: {
							record_today.run_flag = Constant.YES;
							record_today.frequency = record_today.frequency + 1;
							record_today.day_time_other = record_today.day_time_other + 5;
							record_today.day_time = record_today.day_time + 5;
							database.saveOrUpdate(record_today);
							System.out.print(record_today);
							Log.e("test","flag==55555");
						}
						break;
					}
				}
			}
		} catch (DbException e) {
			e.printStackTrace();
		}
		Log.e("今日数据", "今日数据更新");
	}

	private void refresh_today(String packageName) {
		Statistics_record record = new Statistics_record();
		record.package_name = packageName;
		record.type= getType.appType(packageName);
		record.date_use = MyDate.getMyDate();
		record.app_name= get_AppName.getName(packageName);
		record.frequency = 1;
		record.weeknumber=MyDate.getWeek(0);
		record.run_flag = Constant.RUNNINT_FLAG;
		record.day_time = 5;
		switch (MyDate.getTime()) {
			case 1: {
				record.day_time_7_10 = 5;
				record.day_time_11_14 = 0;
				record.day_time_15_18 = 0;
				record.day_time_19_22 = 0;
				record.day_time_other = 0;
			}
			break;
			case 2: {
				record.day_time_7_10 = 0;
				record.day_time_11_14 = 5;
				record.day_time_15_18 = 0;
				record.day_time_19_22 = 0;
				record.day_time_other = 0;

			}
			break;
			case 3: {
				record.day_time_7_10 = 0;
				record.day_time_11_14 = 0;
				record.day_time_15_18 = 5;
				record.day_time_19_22 = 0;
				record.day_time_other = 0;

			}
			break;
			case 4: {
				record.day_time_7_10 = 0;
				record.day_time_11_14 = 0;
				record.day_time_15_18 = 0;
				record.day_time_19_22 = 5;
				record.day_time_other = 0;

			}
			break;
			case 5: {
				record.day_time_7_10 = 0;
				record.day_time_11_14 = 0;
				record.day_time_15_18 = 0;
				record.day_time_19_22 = 0;
				record.day_time_other = 5;
			}
			break;

		}
		try {
			database.save(record);
		} catch (DbException e) {
			e.printStackTrace();
		}
	}

	private void stopChecker() {
		appChecker.stop();
	}

	private void registerReceivers() {
		stopServiceReceiver = new BroadcastReceiver() {
			@Override
			public void onReceive(Context context, Intent intent) {
				stopSelf();
			}
		};
		registerReceiver(stopServiceReceiver, new IntentFilter(STOP_SERVICE));
	}

	private void unregisterReceivers() {
		unregisterReceiver(stopServiceReceiver);
	}

	private Notification createStickyNotification() {
		NotificationManager manager = ((NotificationManager) getSystemService(NOTIFICATION_SERVICE));
		Notification notification = new NotificationCompat.Builder(this)
				.setSmallIcon(R.mipmap.statistics)
				.setOngoing(true)
				.setOnlyAlertOnce(true)
				.setAutoCancel(false)
				.setContentTitle(getString(R.string.app_name))
				.setContentText(getString(R.string.stop_services))
				.setContentIntent(PendingIntent.getBroadcast(this, 0, new Intent(STOP_SERVICE), PendingIntent.FLAG_UPDATE_CURRENT))
				.setWhen(0)
				.build();
		manager.notify(NOTIFICATION_ID, notification);
		return notification;
	}

	private void removeNotification() {
		NotificationManager manager = ((NotificationManager) getSystemService(NOTIFICATION_SERVICE));
		manager.cancel(NOTIFICATION_ID);
	}


	/*private void update_table_app_information(int run_status, String packagename) {
		try {
			App_information app_information = database.selector(App_information.class).where(Constant.PACKAGE_NAME, "=", packagename).findFirst();
			if (app_information != null) {
				if (run_status == Constant.RUNNINT_FLAG) {
					app_information.total_time = app_information.total_time + Constant.REFRESH_TIME;
					database.saveOrUpdate(app_information);
				}
				if (run_status == Constant.NOTRUN_FLAG) {
					app_information.total_time = app_information.total_time + Constant.REFRESH_TIME;
					app_information.total_frequency = app_information.total_frequency + Constant.ONCE;
					database.saveOrUpdate(app_information);
				}
			} else {
				Log.e("error", "error");
			}


		} catch (DbException e) {
			e.printStackTrace();
		}
	}
	*/


}
