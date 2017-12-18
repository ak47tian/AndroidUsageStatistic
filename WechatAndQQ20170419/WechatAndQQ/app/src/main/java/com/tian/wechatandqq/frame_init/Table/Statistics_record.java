package com.tian.wechatandqq.frame_init.Table;

import com.tian.wechatandqq.utils.Constant;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

/**
 * Created by tian on 2017/3/29.
 */
@Table(name = Constant.TABLE_NAME_STATISTICS)
public class Statistics_record {

	@Column(name = Constant.ID, isId = true)
	public int id;

	@Column(name = Constant.PACKAGE_NAME)//1
	public String package_name;

	@Column(name = Constant.APP_NAME)
	public String app_name;

	@Column(name = Constant.FREQUENCY)//2
	public int frequency;

	@Column(name = Constant.DATE_USE)//3
	public String date_use;

	@Column(name = Constant.WEEKNUMBER)
	public int weeknumber;

	@Column(name = Constant.TYPE)
	public int type;

	public int getType() {
		return type;
	}

	@Column(name = Constant.DAY_TIME)//4
	public int day_time;

	public String getApp_name() {
		return app_name;
	}

	public void setApp_name(String app_name) {
		this.app_name = app_name;
	}

	@Column(name = Constant.DAY_TIME_7_10)//5
	public int day_time_7_10;

	@Column(name = Constant.DAY_TIME_11_14)//6
	public int day_time_11_14;

	@Column(name = Constant.DAY_TIME_15_18)//7
	public int day_time_15_18;

	@Column(name = Constant.DAY_TIME_19_22)//8
	public int day_time_19_22;

	@Column(name = Constant.DAY_TIME_OTHER)//9
	public int day_time_other;

	@Column(name = Constant.RUN_FLAG)//10
	public int run_flag;

	public int getWeeknumber() {
		return weeknumber;
	}

	public void setWeeknumber(int weeknumber) {
		this.weeknumber = weeknumber;
	}

	public int getId() {
		return id;
	}

	public void setType(int type) {
		this.type = type;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPackage_name() {
		return package_name;
	}

	public void setPackage_name(String package_name) {
		this.package_name = package_name;
	}

	public int getFrequency() {
		return frequency;
	}

	public void setFrequency(int frequency) {
		this.frequency = frequency;
	}

	public String getDate_use() {
		return date_use;
	}

	public void setDate_use(String date_use) {
		this.date_use = date_use;
	}

	public int getDay_time() {
		return day_time;
	}

	public void setDay_time(int day_time) {
		this.day_time = day_time;
	}

	public int getDay_time_7_10() {
		return day_time_7_10;
	}

	public void setDay_time_7_10(int day_time_7_10) {
		this.day_time_7_10 = day_time_7_10;
	}

	public int getDay_time_11_14() {
		return day_time_11_14;
	}

	public void setDay_time_11_14(int day_time_11_14) {
		this.day_time_11_14 = day_time_11_14;
	}

	public int getDay_time_15_18() {
		return day_time_15_18;
	}

	public void setDay_time_15_18(int day_time_15_18) {
		this.day_time_15_18 = day_time_15_18;
	}

	public int getDay_time_19_22() {
		return day_time_19_22;
	}

	public void setDay_time_19_22(int day_time_19_22) {
		this.day_time_19_22 = day_time_19_22;
	}

	public int getDay_time_other() {
		return day_time_other;
	}

	public void setDay_time_other(int day_time_other) {
		this.day_time_other = day_time_other;
	}

	public int getRun_flag() {
		return run_flag;
	}

	public void setRun_flag(int run_flag) {
		this.run_flag = run_flag;
	}
}
