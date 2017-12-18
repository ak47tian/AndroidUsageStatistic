package com.tian.wechatandqq.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.tian.wechatandqq.R;
import com.tian.wechatandqq.frame_init.Table.Statistics_record;
import com.tian.wechatandqq.utils.Constant;
import com.tian.wechatandqq.utils.DbUtils;
import com.tian.wechatandqq.utils.MyDate;

import org.xutils.DbManager;
import org.xutils.ex.DbException;
import org.xutils.x;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class WeeklyReport extends Activity {
	DbManager database;
	float min=0;
	String name="";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_weekly_report);
		database = x.getDb(DbUtils.getConfig(this));
		TextView textView_lastweek_total_time;
		TextView textView_lastweek_longest;
		TextView textView_lasteek_influence;
		TextView textView_total_time;
		TextView textView_longest;
		TextView textView_influence;

		textView_lastweek_total_time=(TextView)findViewById(R.id.lastWeek_total_time_used__set);
		textView_lastweek_longest=(TextView)findViewById(R.id.lastWeek_lonngest_use_app_set);
		textView_lasteek_influence=(TextView)findViewById(R.id.lastWeek_after_ten_usd_set);

		textView_total_time=(TextView)findViewById(R.id.thisWeek_total_time_used_set);
		textView_longest=(TextView)findViewById(R.id.thisWeek_lonngest_use_app_set);
		textView_influence=(TextView)findViewById(R.id.thisWeek_after_ten_usd_set);


		DecimalFormat decimalFormat=new DecimalFormat(".00");
		List<Statistics_record> records_last=new ArrayList<>();
		try {
			records_last=database.selector(Statistics_record.class).where(Constant.WEEKNUMBER,"=", MyDate.getWeek(1)).findAll();
			Log.e("text1",String.valueOf(records_last.size()));
			if (records_last.size()==0){
				textView_lastweek_total_time.setText("无数据");
				textView_lastweek_longest.setText("无数据");
				textView_lasteek_influence.setText("无数据");
				Log.e("text2",String.valueOf(records_last.size()));

			}else {
				for (int i=0;i<records_last.size();i++){
					min=min+records_last.get(i).getDay_time();
					if (records_last.get(i).getDay_time_other()>0){
						name=name+" "+records_last.get(i).getApp_name();
					}
				}

				textView_lastweek_total_time.setText(decimalFormat.format(min)+"分钟");
				textView_lastweek_longest.setText("加入分类后完成");
				textView_lasteek_influence.setText(name);
				Log.e("text3",String.valueOf(records_last.size()));

			}

		} catch (DbException e) {
			e.printStackTrace();
		}

		List<Statistics_record> records=new ArrayList<>();
		try {
			records=database.selector(Statistics_record.class).where(Constant.WEEKNUMBER,"=", MyDate.getWeek(0)).findAll();
			Log.e("text1",String.valueOf(records.size()));
			if (records.size()==0){
				textView_lastweek_total_time.setText("无数据");
				textView_lastweek_longest.setText("无数据");
				textView_lasteek_influence.setText("无数据");
				Log.e("text2",String.valueOf(records.size()));

			}else {
				for (int i=0;i<records.size();i++){
					min=min+records.get(i).getDay_time();
					if (records.get(i).getDay_time_other()>0){
						name=name+" "+records.get(i).getApp_name();
					}
				}
				textView_total_time.setText(decimalFormat.format(min/60)+"分钟");
				textView_longest.setText("加入分类后完成");
				textView_influence.setText(name);
				Log.e("text3",String.valueOf(records.size()));

			}

		} catch (DbException e) {
			e.printStackTrace();
		}

	}

}
