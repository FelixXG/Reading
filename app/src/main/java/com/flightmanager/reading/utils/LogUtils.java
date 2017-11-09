package com.flightmanager.reading.utils;

import android.content.Context;

import java.text.SimpleDateFormat;

/**
 *log工具类，可控制Log输出开关、保存Log到文件、过滤输出等级
 */
public class LogUtils {

    private static  Boolean LOG_SWITCH=true;//日志文件总开关
    private static Boolean  LOG_TO_FILE=false;//日志写入文件开关
    private static String LOG_TAG="READING";//默认的TAG;
    private static char LOG_TYPE='v';//日志输出类型
    private static int LOG_SAVE_DAYS=7;//sd卡中日志文件的最多保存天数

    private final static SimpleDateFormat LOG_FORMAT=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//日志的输出格式
    private final static SimpleDateFormat FILE_SUFFIX=new SimpleDateFormat("yyyy-MM-dd");//日志文件格式
    private static String LOG_FILE_PATH;//日志文件保存路径
    private static String LOG_FILE_NAME;//日志文件保存名称

    public static void init(Context context){
//        LOG_FILE_PATH= Environment.getExternalStorageDirectory().getPath()+ File.separator+
    }
}
