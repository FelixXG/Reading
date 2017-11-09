package com.flightmanager.reading.utils;


import android.content.Context;

public class FileUtils {

    /**
     * 创建根缓存目录
     *
     * @return
     */
    public static String createRootPath(Context context) {
//        String cacheRootPath = "";
//        if (isSdCardAvailable()) {
//            // /sdcard/Android/data/<application package>/cache
//            cacheRootPath = context.getExternalCacheDir().getPath();
//        } else {
//            // /data/data/<application package>/cache
//            cacheRootPath = context.getCacheDir().getPath();
//        }
//        return cacheRootPath;
        return "0";
    }
}
