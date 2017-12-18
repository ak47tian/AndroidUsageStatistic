package com.tian.wechatandqq.utils;


/**
 * Created by tian on 2017/2/17.
 * 定义一些常量，方便统一修改
 */
public class Constant {
	//定义数据库的名称和版本
	public static final int SOCIALMEDIA = 1;
	public static final int VIDEO = 2;
	public static final int SHOP = 3;
	public static final int PROPERTY = 4;
	public static final int INTERATE = 5;
	public static final String TYPE = "type";
	public static final String WEEKNUMBER = "week";
	public static final int CALCULATOR_USE = 1;
	public static final int CALCULATOR_OPEN = 2;
	public static final int CALCULATOR_TIME = 1;
	public static final int CALCULATOR_FREQUENCY = 2;

	public static final int REFRESH_TIME = 5;
	public static final int ONCE = 1;
	public static final String ID = "id";
	public static final String DATABASE_NAME = "info.db";             //数据库名称
	public static final int DATABASE_VERSION = 1;                     //数据库版本
	//第一张表，用于存放APP的名称，包名，打开总次数，使用总时长，是否安装的标志，统计天数
	public static final String APP_NAME = "app_name";                 //app的名称
	public static final String PACKAGE_NAME = "package_name";         //应用程序包名
	public static final String LASTTIME_USED = "lasttime_used";       //最后使用时间
	public static final String APP_EXIST_FLAG = "flag";               //程序是否安装的标志
	public static final String TOTAL_TIME = "total_time";             //应用程序总共使用的时长
	public static final String TOTAL_FREQUENCY = "total_frequency";   //应用程序总共打开的次数
	public static final String STATISTICS_DAYS = "statistics_days";   //此应用使用（统计）的天数

	public static final String APP_NAME_QQ = "QQ";                  //QQ存储的应用名称
	public static final String APP_NAME_MM = "WeChat";              //微信存储的应用名称
	public static final String APP_NAME_PLAT = "plat";              //同花顺
	public static final String APP_NAME_WEIBO = "weibo";            //微博
	public static final String APP_NAME_TIEBA = "tieba";            //贴吧
	public static final String APP_NAME_TMALL = "tmall";            //天猫
	public static final String APP_NAME_TAOBAO = "taobao";          //淘宝
	public static final String APP_NAME_YOUKU = "youku";            //优酷
	public static final String APP_NAME_IQIYI = "iqiyi";            //爱奇艺
	public static final String APP_NAME_UC = "uc";                  //UC浏览器


	public static final String TABLE_NAME_APPS = "app_information";   //app信息表(表名)
	public static final String TABLE_NAME_STATISTICS = "Statistics_record";

	public static final String TABLE_NAME_QQ = "mobileqq";            //存放与QQ相关信息的的表名
	public static final String TABLE_NAME_MM = "mm";                  //存放与WeChat相关信息的表名
	public static final String TABLE_NAME_PLAT = "plat";              //保存的同花顺
	public static final String TABLE_NAME_WEIBO = "weibo";            //保存微博
	public static final String TABLE_NAME_TIEBA = "tieba";            //贴吧
	public static final String TABLE_NAME_TMALL = "tmall";            //保存天猫
	public static final String TABLE_NAME_TAOBAO = "taobao";          //保存淘宝
	public static final String TABLE_NAME_YOUKU = "youku";            //保存优酷
	public static final String TABLE_NAME_IQIYI = "iqiyi";            //保存爱奇艺
	public static final String TABLE_NAME_UC = "uc";                  //保存UC浏览器数据

	//应用程序包名
	public static final String PACKAGE_NAME_QQ = "com.tencent.mobileqq";      //QQ应用程序安装的包名
	public static final String PACKAGE_NAME_WeChat = "com.tencent.mm";        //微信应用程序安装的包名
	public static final String PACKAGE_NAME_PLAT = "com.hexin.plat.android";  //同花顺
	public static final String PACKAGE_NAME_WEIBO = "com.sina.weibo";         //新浪微博
	public static final String PACKAGE_NAME_TIEBA = "com.baidu.tieba";        //百度贴吧
	public static final String PACKAGE_NAME_TMALL = "com.tmall.wireless";     //天猫
	public static final String PACKAGE_NAME_TAOBAO = "com.taobao.taobao";     //淘宝
	public static final String PACKAGE_NAME_YOUKU = "com.youku.phone";        //优酷
	public static final String PACKAGE_NAME_IQIYI = "com.qiyi.video";         //爱奇艺
	public static final String PACKAGE_NAME_UC = "com.UCMobile";              //uc浏览器

	public static final String[] PACKAGENAME_SET = {PACKAGE_NAME_QQ, PACKAGE_NAME_WeChat, PACKAGE_NAME_PLAT,
			PACKAGE_NAME_WEIBO, PACKAGE_NAME_TIEBA, PACKAGE_NAME_TMALL, PACKAGE_NAME_TAOBAO, PACKAGE_NAME_YOUKU,
			PACKAGE_NAME_IQIYI, PACKAGE_NAME_UC};

	//第一张表的字段
	public static final String DATE_USE = "date_use";                 //存放使用日期
	public static final String FREQUENCY = "frequency";               //存放每日使用（打开APP）的频率
	public static final String DAY_TIME = "day_time";                 //存放每日使用APP的总时长
	public static final String RUN_FLAG = "run_flag";
	public static final String DAY_TIME_7_10 = "t7_10";                           //7-10
	public static final String DAY_TIME_11_14 = "t11_14";                         //11-14
	public static final String DAY_TIME_15_18 = "t15_18";                         //15-18
	public static final String DAY_TIME_19_22 = "t19_22";                         //19-22
	public static final String DAY_TIME_OTHER = "t_other";                        //other


	//每个app的统计信息表


	public static final String QQ_TAG = "QQ";                         //
	public static final String WeChcat_TAG = "WeChat";                //
	public static final String DB_TAG = "DB";                         //


	//定义存在（是否安装）标志1
	public static final int YES = 1;                            //APP 存在（安装） 标志1
	public static final int NO = 0;                             //APP 不存在（未安装） 标志0


	public static final int RUNNINT_FLAG = 1;
	public static final int NOTRUN_FLAG = 0;

	public static final String INIT_FLAG = "init.flag";
	public static final String QQ_INIT_FLAG = "qq_exist.flag";
	public static final String MM_INIT_FLAG = "wechat_exist.flag";


}
