package com.tian.wechatandqq.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by tian on 2017/2/21.
 */

public class MyDate {
	public static String getMyDate(){
		SimpleDateFormat formatter = new SimpleDateFormat("MM-dd");
		Date curDate    = new  Date(System.currentTimeMillis());//获取当前时间
		String date = formatter.format(curDate);
		return date;
	}
	public static String getMyTime(){
		SimpleDateFormat formatter=new SimpleDateFormat("HH");
		Date curTime=new Date(System.currentTimeMillis());
		String string = formatter.format(curTime);
		return string;
	}
	public static int getWeek(int last){
		GregorianCalendar cal = new GregorianCalendar();    //当前时间
		cal.setMinimalDaysInFirstWeek(7);   //第一周最少包含七天
		cal.setFirstDayOfWeek(Calendar.MONDAY);//周一为一周第一天
		cal.set(Calendar.DAY_OF_WEEK, cal.getFirstDayOfWeek());//取开始时间
		int weeks=cal.get(Calendar.WEEK_OF_YEAR);
		weeks=weeks-last;
		System.out.println(weeks);
		return weeks;
	}
	public static int getTime(){
		SimpleDateFormat formatter=new SimpleDateFormat("HH");
		Date curTime=new Date(System.currentTimeMillis());
		String tmp = formatter.format(curTime);
		int time=Integer.valueOf(tmp);
		if(time>=7&&time<=10){
			return 1;
		}
		if (time>=11&&time<=14){
			return 2;
		}
		if (time>=15&&time<=18){
			return 3;
		}
		if (time>=19&&time<=22){
			return 4;
		}else {
			return 5;
		}
	}
}
