package com.tian.wechatandqq.frame_init.Table;

import com.tian.wechatandqq.utils.Constant;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

/**
 * Created by tian on 2017/3/16.
 */

@Table(name = Constant.TABLE_NAME_APPS)
public class App_information {
	@Column(name = Constant.ID,isId = true)
	public int id;

	@Column(name = Constant.APP_NAME)
	public String app_name;//app名字

	@Column(name = Constant.PACKAGE_NAME)
	public String packagename;//包名

	@Column(name = Constant.TYPE)
	public int type;




}
