package com.tian.wechatandqq.Activity;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.tian.wechatandqq.R;
import com.tian.wechatandqq.chart.MPChartHelper;
import com.tian.wechatandqq.chart.MyMarkerView;
import com.tian.wechatandqq.chart.getAlldata;
import com.tian.wechatandqq.check_Exist.*;
import com.tian.wechatandqq.frame_init.Table.Statistics_record;
import com.tian.wechatandqq.services.ForegroundService;
import com.tian.wechatandqq.utils.Constant;
import com.tian.wechatandqq.utils.DbUtils;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AppOpsManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.xutils.DbManager;
import org.xutils.ex.DbException;
import org.xutils.x;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


public class MainActivity extends Activity implements AdapterView.OnItemClickListener {
	DbManager database;
	private int[] appType = {Constant.SOCIALMEDIA,
			Constant.VIDEO,
			Constant.SHOP,
			Constant.PROPERTY,
			Constant.INTERATE
	};
	private String[] typeName = {"社交类", "视频类", "购物类", "理财类", "网络类"};
	private PieChart pieChart;
	private Map<String, Float> pieValues;
	private ListView listView;


	//主页面的需要修改为按类型，从头到尾进行计算占比。信息类每个app的本周和上周的使用数据记录
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);//将xml文件引入到Activity中
		database = x.getDb(DbUtils.getConfig(this));
		listView = (ListView) findViewById(R.id.listview_main);
		listView.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, initListData()));
		listView.setOnItemClickListener(this);




		TextView permission_notice;

		permission_notice = (TextView) findViewById(R.id.noticedText);

		if (!checkFile_exist.init_flag(database)) {
			Log.e("执行初始化", "执行初始化");
		}

//		statisticsDya_flash();
//		setupPieChart();
		pieChart = (PieChart) findViewById(R.id.chart);
		initData();
		MPChartHelper.setPieChart(pieChart, pieValues, "饼图", true);
		//服务已经开始监控，其 他界面跳转
//		if (!needsUsageStatsPermission()) {
//			//此处先行判断已经获取到权限
//			button_get_permission.setVisibility(View.GONE);//设置控件隐藏
//			permission_notice.setText(R.string.normal_notice);
//		} else {
//			button_get_permission.setOnClickListener(new View.OnClickListener() {
//				@Override
//				public void onClick(View v) {
//					requestUsageStatsPermission();
//				}
//			});
//		}
		if (!needsUsageStatsPermission()) {
			//此处先行判断已经获取到权限
			permission_notice.setText(R.string.normal_notice);
		} else {
			requestUsageStatsPermission();
		}
	}

	private List<String> initListData() {
		List<String> data = new ArrayList<>();
		data.add("开始统计");
		data.add("社交类");
		data.add("视频类");
		data.add("购物类");
		data.add("网络类");
		data.add("财富类");
		data.add("周报");
		return data;
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		Intent intent;
		switch (position) {
			case 0:
				ForegroundService.start(getBaseContext());
				Toast.makeText(getBaseContext(), getString(R.string.service_started), Toast.LENGTH_SHORT).show();
				finish();
				break;
			case 1:
				intent = new Intent(MainActivity.this, socialMedia.class);
				startActivity(intent);
				Log.e("11", "socialMedia");

				break;
			case 2:
				intent = new Intent(MainActivity.this, video.class);
				startActivity(intent);
				Log.e("14", "video");
				break;
			case 3:
				intent = new Intent(MainActivity.this, shop.class);
				startActivity(intent);
				Log.e("15", "shop");
				break;
			case 4:
				intent = new Intent(MainActivity.this, internet.class);
				startActivity(intent);
				Log.e("13", "internet");
				break;
			case 5:
				intent = new Intent(MainActivity.this, property.class);
				startActivity(intent);
				Log.e("12", "property");
				break;
			case 6:
				intent = new Intent(MainActivity.this, WeeklyReport.class);
				startActivity(intent);
				Log.e("weekReport", "周报");
				break;
		}
	}

	private void initData() {
		pieValues = new LinkedHashMap<>();
		List<Statistics_record> initRecord = new ArrayList<>();
		try {
			for (int i = 0; i < appType.length; i++) {
				float time = 0;
				String name;
				initRecord = database.selector(Statistics_record.class).where(Constant.TYPE, "=",
						appType[i]).findAll();
				if (initRecord == null) {
					return;
				} else {
					for (int j = 0; j < initRecord.size(); j++) {
						time = time + initRecord.get(j).getDay_time();
						pieValues.put(typeName[i], time);
						Log.e("Main", "2222");
					}
				}
			}
		} catch (DbException e) {
			e.printStackTrace();
		}
	}




	private boolean needsUsageStatsPermission() {
		return postLollipop() && !hasUsageStatsPermission(this);
	}

	@TargetApi(Build.VERSION_CODES.LOLLIPOP)
	private void requestUsageStatsPermission() {
		if (!hasUsageStatsPermission(this)) {
			startActivity(new Intent(Settings.ACTION_USAGE_ACCESS_SETTINGS));
		}
	}

	private boolean postLollipop() {
		return android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP;
	}

	@TargetApi(Build.VERSION_CODES.KITKAT)
	private boolean hasUsageStatsPermission(Context context) {
		AppOpsManager appOps = (AppOpsManager) context.getSystemService(Context.APP_OPS_SERVICE);
		int mode = appOps.checkOpNoThrow("android:get_usage_stats",
				android.os.Process.myUid(), context.getPackageName());
		boolean granted = mode == AppOpsManager.MODE_ALLOWED;
		return granted;
	}


}
