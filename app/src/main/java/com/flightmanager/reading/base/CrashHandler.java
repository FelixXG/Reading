package com.flightmanager.reading.base;


import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import java.util.HashMap;
import java.util.Map;

public class CrashHandler implements Thread.UncaughtExceptionHandler {

    //系统默认的UncaughtException处理类
    private Thread.UncaughtExceptionHandler  mDefautlHandler;

    //CrashHandler实例
    private static CrashHandler INSTANCE;

    //程序的Context对象
    private Context mContext;

    //用来存储设备信息和异常信息
    private Map<String,String>infos=new HashMap<>();

    private CrashHandler(){

    }

    /**
     * 获取CrashHandler实例，单例模式
     * @return
     */

    public static CrashHandler getInstance(){
        if(INSTANCE==null)
            INSTANCE=new CrashHandler();
            return INSTANCE;
    }

    /**
     * 初始化
     * @param context
     */
    public void init(Context context){
        mContext=context;
        //获取系统默认的UncaughtException处理器
        mDefautlHandler=Thread.getDefaultUncaughtExceptionHandler();
        //设置该CrashHandler为程序的默认处理器
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    /**
     * 当UncaughtException发生时会转入该函数来处理
     * @param t
     * @param e
     */

    @Override
    public void uncaughtException(Thread t, Throwable e) {

    }

    private boolean handleException(Throwable ex){
        if(ex==null){
            return false;
        }
        //收集设备参数信息
        return false;
    }

    public void collectDeviceInfo(Context ctx){
        try {
            PackageManager pm=ctx.getPackageManager();
            PackageInfo pi=pm.getPackageInfo(ctx.getPackageName(),PackageManager.GET_ACTIVITIES);
            if(pi!=null){
                String versionName=pi.versionName==null?"null":pi.versionName;
                String versionCode=pi.versionCode+"";
                infos.put("versionName",versionName);
                infos.put("versionCode",versionCode);
            }

        } catch (PackageManager.NameNotFoundException e) {
           // LogUtils.e("CrashHandleran.NameNotFoundException---> error occured when collect package info", e);
        }
    }
}
