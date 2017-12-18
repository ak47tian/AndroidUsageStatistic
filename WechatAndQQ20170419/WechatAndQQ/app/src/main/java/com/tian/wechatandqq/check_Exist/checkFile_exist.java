package com.tian.wechatandqq.check_Exist;

import android.content.Context;
import android.util.Log;

import com.tian.wechatandqq.frame_init.Table.Statistics_record;
import com.tian.wechatandqq.utils.Constant;

import org.xutils.DbManager;
import org.xutils.ex.DbException;

import java.io.File;
import java.io.IOException;

/**
 * Created by tian on 2017/3/4.
 */

public class checkFile_exist {
	public static boolean init_flag(DbManager dbManager){
		Statistics_record record;
		try {
			record=dbManager.selector(Statistics_record.class).findFirst();
			if (record!=null){
				return true;
			}
		} catch (DbException e) {
			e.printStackTrace();
		}

		return false;
	}

	public static boolean Init_flag_all(Context context){
		File folderpath=context.getFilesDir();
		File file=new File(folderpath,Constant.INIT_FLAG);
		if (file.exists()){
			Log.e(Constant.INIT_FLAG,"flag存在");
			return true;
		}else {
			Log.e(Constant.INIT_FLAG,"flag不存在");
			return false;
		}
	}
	public static boolean Init_flag_qq(Context context){
		File folderpath=context.getFilesDir();
		File file=new File(folderpath,Constant.QQ_INIT_FLAG);
		if (file.exists()){
			Log.e(Constant.QQ_TAG,"flag存在");
			return true;
		}else {
			Log.e(Constant.QQ_TAG,"flag不存在");
			return false;
		}
	}
	public static boolean Init_flag_wechat(Context context){
		File folderpath=context.getFilesDir();
		File file=new File(folderpath,Constant.MM_INIT_FLAG);
		if (file.exists()){
			Log.e(Constant.WeChcat_TAG,"flag存在");
			return true;
		}else {
			Log.e(Constant.WeChcat_TAG,"flag不存在");
			return false;
		}
	}
}
