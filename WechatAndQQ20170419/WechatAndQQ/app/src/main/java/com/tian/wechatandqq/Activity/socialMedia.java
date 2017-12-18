package com.tian.wechatandqq.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.github.mikephil.charting.charts.PieChart;
import com.tian.wechatandqq.R;
import com.tian.wechatandqq.chart.MPChartHelper;
import com.tian.wechatandqq.frame_init.Table.Statistics_record;
import com.tian.wechatandqq.utils.Constant;
import com.tian.wechatandqq.utils.DbUtils;

import org.xutils.DbManager;
import org.xutils.ex.DbException;
import org.xutils.x;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class socialMedia extends Activity implements AdapterView.OnItemClickListener {
	private DbManager database;
	private ListView listView;
	private PieChart pieChart;
	private Map<String, Float> pieValues;
	private String[] mediaApp = {
			Constant.PACKAGE_NAME_QQ,
			Constant.PACKAGE_NAME_WeChat,
			Constant.PACKAGE_NAME_WEIBO,
			Constant.PACKAGE_NAME_TIEBA};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_social_media);
		database = x.getDb(DbUtils.getConfig(this));

		listView = (ListView) findViewById(R.id.listview_socialMedia);
		listView.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, initListData()));
		listView.setOnItemClickListener(this);
		pieChart = (PieChart) findViewById(R.id.media_piechart);
		initData();
		MPChartHelper.setPieChart(pieChart, pieValues, "饼图", true);
		Log.e("数据初始成功", "数据初始成功");
	}

	private void initData() {
		pieValues = new LinkedHashMap<>();
		List<Statistics_record> records = new ArrayList<>();
		try {
			for (int i = 0; i < mediaApp.length; i++) {
				int time = 0;
				String name;
				records = database.selector(Statistics_record.class).where(Constant.PACKAGE_NAME, "=",
						mediaApp[i]).findAll();
				if (records==null){
					Log.e("dddd","dddddddddddd555");
					return;
				}
				if (records.size() == 1) {
					time = records.get(0).getDay_time();
					name = records.get(0).getApp_name();
					pieValues.put(name, Float.valueOf(time));
					Log.e("111122", "111111");
				}
				if (records.size() > 1) {
					for (int j = 0; j < records.size(); j++) {
						time = time + records.get(j).getDay_time();
						name = records.get(j).getApp_name();
						pieValues.put(name, Float.valueOf(time));
						Log.e("111122", "2222");
					}
				}
			}
		} catch (DbException e) {
			e.printStackTrace();
		}

	}

	private List<String> initListData() {
		List<String> data = new ArrayList<>();
		data.add("QQ");
		data.add("微信");
		data.add("微博");
		data.add("贴吧");
		return data;
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		Intent intent;
		switch (position) {
			case 0:
				intent = new Intent(socialMedia.this, Show_statistics.class);
				intent.putExtra(Constant.PACKAGE_NAME, Constant.PACKAGE_NAME_QQ);
				startActivity(intent);
				break;
			case 1:
				intent = new Intent(socialMedia.this, Show_statistics.class);
				intent.putExtra(Constant.PACKAGE_NAME, Constant.PACKAGE_NAME_WeChat);
				startActivity(intent);
				break;
			case 2:
				intent = new Intent(socialMedia.this, Show_statistics.class);
				intent.putExtra(Constant.PACKAGE_NAME, Constant.PACKAGE_NAME_WEIBO);
				startActivity(intent);
				break;
			case 3:
				intent = new Intent(socialMedia.this, Show_statistics.class);
				intent.putExtra(Constant.PACKAGE_NAME, Constant.PACKAGE_NAME_TIEBA);
				startActivity(intent);
				break;
		}
	}
}
