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

public class shop extends Activity implements AdapterView.OnItemClickListener{
	private ListView listView;
	private DbManager database;
	private PieChart pieChart;
	private Map<String,Float> pieValues;
	private String[] shopApp={
			Constant.PACKAGE_NAME_TMALL,
			Constant.PACKAGE_NAME_TAOBAO
	};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_shop);
		database = x.getDb(DbUtils.getConfig(this));

		listView= (ListView) findViewById(R.id.listview_shop);
		listView.setAdapter(new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,initListData()));
		listView.setOnItemClickListener(this);
		pieChart= (PieChart) findViewById(R.id.shop_piechart);

		initData();
		MPChartHelper.setPieChart(pieChart,pieValues,"饼图",true);

	}

	private void initData() {
		pieValues = new LinkedHashMap<>();
		List<Statistics_record> records = new ArrayList<>();
		try {
			for (int i = 0; i < shopApp.length; i++) {
				int time = 0;
				String name = null;
				records = database.selector(Statistics_record.class).where(Constant.PACKAGE_NAME, "=",
						shopApp[i]).findAll();
//				Log.e("wwwww",String.valueOf(records.get(0).type));
				if (records.size() == 1) {
					time = records.get(0).getDay_time();
					name = records.get(0).getApp_name();
					pieValues.put(name, Float.valueOf(time));
					Log.e("111122", "111111");
				}
				if (records.size() > 1) {
					Log.e("dddd",String.valueOf(records.size()));
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
		data.add("天猫");
		data.add("淘宝");
		return data;
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		Intent intent;
		switch (position){
			case 0:
				intent = new Intent(shop.this, Show_statistics.class);
				intent.putExtra(Constant.PACKAGE_NAME, Constant.PACKAGE_NAME_TMALL);
				startActivity(intent);
				break;
			case 1:
				intent = new Intent(shop.this, Show_statistics.class);
				intent.putExtra(Constant.PACKAGE_NAME, Constant.PACKAGE_NAME_TAOBAO);
				startActivity(intent);
				break;
		}
	}
}
