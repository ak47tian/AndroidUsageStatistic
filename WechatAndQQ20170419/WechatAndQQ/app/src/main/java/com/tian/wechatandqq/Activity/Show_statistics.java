package com.tian.wechatandqq.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.github.mikephil.charting.charts.CombinedChart;
import com.tian.wechatandqq.R;
import com.tian.wechatandqq.chart.MPChartHelper;
import com.tian.wechatandqq.frame_init.Table.Statistics_record;
import com.tian.wechatandqq.utils.Constant;
import com.tian.wechatandqq.utils.DbUtils;
import com.tian.wechatandqq.utils.MyDate;

import org.xutils.DbManager;
import org.xutils.ex.DbException;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

public class Show_statistics extends Activity {

	DbManager database;

	private CombinedChart combineChart;//合成图
	private List<String> xAxisValues;//x轴线
	private List<Integer> lineValues;//直线
	private List<Float> barValues;//柱状图值


	List<String> date_x = new ArrayList<>();//横坐标
	List<Integer> frequency = new ArrayList<>();//频率
	List<Float> usetime = new ArrayList<>();//每日使用时长


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.show_qq_statistics_info);
		Intent intent = getIntent();
		String string = intent.getStringExtra(Constant.PACKAGE_NAME);
		Log.e(string, string + "intent" + "获取数据");
		database = x.getDb(DbUtils.getConfig(this));
		combineChart = (CombinedChart) findViewById(R.id.qq_com_chart);
		initData(string);
		MPChartHelper.setCombineChart(combineChart, xAxisValues, lineValues, barValues, "每日打开次数（右）", "每日使用时长（左）");



		TextView textView_average_use;
		textView_average_use = (TextView) findViewById(R.id.average_use);
		TextView textView_average_use_set;
		textView_average_use_set = (TextView) findViewById(R.id.average_use_set);
		TextView textView_average_open;
		textView_average_open = (TextView) findViewById(R.id.average_open);
		TextView textView_average_open_set;
		textView_average_open_set = (TextView) findViewById(R.id.average_open_set);
		TextView textView_today_usetime;
		textView_today_usetime = (TextView) findViewById(R.id.today_usetime);
		TextView textView_today_usetime_set;
		textView_today_usetime_set = (TextView) findViewById(R.id.today_usetime_set);
		TextView textView_today_open;
		textView_average_open = (TextView) findViewById(R.id.today_open);
		TextView textView_today_open_set;
		textView_today_open_set = (TextView) findViewById(R.id.today_open_set);
		TextView textView_package= (TextView) findViewById(R.id.package_name);



		textView_average_use_set.setText(String.valueOf(getAverageCal(string,Constant.CALCULATOR_USE)));
		textView_average_open_set.setText(String.valueOf(getAverageCal(string,Constant.CALCULATOR_OPEN)));
		textView_today_usetime_set.setText(String.valueOf(getTodayCal(string,Constant.CALCULATOR_FREQUENCY)));
		textView_today_open_set.setText(String.valueOf(getTodayCal(string,Constant.CALCULATOR_TIME)));
		textView_package.setText(app_name(string));
	}
	private String app_name(String string){
		if (string!=null){
			Log.e("teee",string);
			Statistics_record record=new Statistics_record();
			try {
				record=database.selector(Statistics_record.class).where(Constant.PACKAGE_NAME,"=",string).findFirst();
				if (record!=null){
					return record.app_name;
				}
			} catch (DbException e) {
				e.printStackTrace();
			}

		}
		return "数据为空";
	}

	private float getAverageCal(String string, int mode) {
		List<Statistics_record> records = new ArrayList<>();
		float cal = 0;
		try {
			records = database.selector(Statistics_record.class).where(Constant.PACKAGE_NAME, "=", string).findAll();//找到所有记录
			if (mode == Constant.CALCULATOR_USE && records != null) {
				for (int i = 0; i < records.size(); i++) {
					cal = cal + records.get(i).day_time;
				}
				cal = cal / records.size();
				return cal;

			}
			if (mode == Constant.CALCULATOR_OPEN && records != null) {

				for (int j = 0; j < records.size(); j++) {
					cal=cal+records.get(j).frequency;
				}
				cal=cal/records.size();
				return cal;
			}
		} catch (DbException e) {
			e.printStackTrace();
		}
		return 0;
	}
	private float getTodayCal(String string, int mode){
		Statistics_record record=new Statistics_record();
		try {
			record=database.selector(Statistics_record.class).where(Constant.DATE_USE,"=", MyDate.getMyDate()).and(
					Constant.PACKAGE_NAME,"=",string).findFirst();
			if (record!=null&mode==Constant.CALCULATOR_TIME){
				return (float)record.day_time;
			}
			if (record!=null&mode==Constant.CALCULATOR_FREQUENCY){
				return record.frequency;
			}
		} catch (DbException e) {
			e.printStackTrace();
		}
		return 0;
	}

	private void initData(String string) {
		List<Statistics_record> records = new ArrayList<>();
		try {
			records = database.selector(Statistics_record.class).where(Constant.PACKAGE_NAME, "=",
					string).findAll();
			if (records != null) {
				for (int i = 0; i < records.size(); i++) {
					date_x.add(records.get(i).date_use);//横坐标
					frequency.add(records.get(i).getFrequency());
					usetime.add((float) (records.get(i).day_time / 60));//得到分钟数zuo
				}
			}
		} catch (DbException e) {
			e.printStackTrace();
		}
		xAxisValues = new ArrayList<>();//对应x轴的显示个数
		lineValues = new ArrayList<>();//保存这折线图的数据
		barValues = new ArrayList<>();//保存柱状图的数据
		if (date_x.size()>=1&&frequency.size()>=1&&usetime.size()>=1){
			for (int i = 0; i < date_x.size(); i++) {
				xAxisValues.add(date_x.get(i));
				lineValues.add(frequency.get(i));
				barValues.add(usetime.get(i));
			}
		}else {
			xAxisValues.add("0");
			lineValues.add(0);
			barValues.add((float)0);
		}


	}
}
