package com.tian.wechatandqq.chart;

import java.text.NumberFormat;

/**
 * Created by tian on 2017/3/23.
 */

public class StringUtils {
	public static String double2String(double d, int num) {
		NumberFormat nf = NumberFormat.getNumberInstance();
		nf.setMaximumFractionDigits(num);//保留两位小数
		nf.setGroupingUsed(false);//去掉数值中的千位分隔符

		String temp = nf.format(d);
		if (temp.contains(".")) {
			String s1 = temp.split("\\.")[0];
			String s2 = temp.split("\\.")[1];
			for (int i = s2.length(); i > 0; --i) {
				if (!s2.substring(i - 1, i).equals("0")) {
					return s1 + "." + s2.substring(0, i);
				}
			}
			return s1;
		}
		return temp;
	}
}
