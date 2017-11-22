package com.flightmanager.reading;


import android.app.Application;
import android.content.Context;
import android.support.v7.app.AppCompatDelegate;

import com.flightmanager.reading.base.Constant;
import com.flightmanager.reading.component.AppComponent;
import com.flightmanager.reading.component.DaggerAppComponent;
import com.flightmanager.reading.module.AppModule;
import com.flightmanager.reading.module.BookApiModule;
import com.flightmanager.reading.utils.FileUtils;
import com.flightmanager.reading.utils.LogUtils;
import com.flightmanager.reading.utils.SharedPreferencesUtil;
import com.sinovoice.hcicloudsdk.api.HciCloudSys;
import com.sinovoice.hcicloudsdk.common.HciErrorCode;
import com.sinovoice.hcicloudsdk.common.InitParam;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

public class ReadingApplication extends Application {

    private static ReadingApplication sInstance;
    private AppComponent appComponent;

    private RefWatcher refWatcher;

    public static RefWatcher getRefWatcher(Context context){
        ReadingApplication application=(ReadingApplication)context.getApplicationContext();
        return application.refWatcher;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        refWatcher= LeakCanary.install(this);
        sInstance=this;
    }

    public static ReadingApplication getsInstance(){
        return sInstance;
    }

    private void initComponent(){
            appComponent= DaggerAppComponent.builder()
                      .bookApiModule(new BookApiModule())
                      .appModule(new AppModule(this))
                      .build();
    }

   public AppComponent getAppComponent(){
       return appComponent;
   }

    /**
     * 初始化SharedPreference
     */
   protected void initPrefs(){
       SharedPreferencesUtil.init(getApplicationContext(),getPackageName()+"_preference",Context.MODE_MULTI_PROCESS);
   }

   protected void initNightMode(){
       boolean isNight=SharedPreferencesUtil.getInstance().getBoolean(Constant.ISNIGHT);
       if(isNight){
           AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
       }else{
           AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
       }
   }

   protected void initHciCloud(){
       InitParam initparam = new InitParam();
       String authDirPath = getFilesDir().getAbsolutePath();
       initparam.addParam(InitParam.AuthParam.PARAM_KEY_AUTH_PATH, authDirPath);
       initparam.addParam(InitParam.AuthParam.PARAM_KEY_AUTO_CLOUD_AUTH, "no");
       initparam.addParam(InitParam.AuthParam.PARAM_KEY_CLOUD_URL, "test.api.hcicloud.com:8888");
       initparam.addParam(InitParam.AuthParam.PARAM_KEY_DEVELOPER_KEY, "0a5e69f8fb1c019b2d87a17acf200889");
       initparam.addParam(InitParam.AuthParam.PARAM_KEY_APP_KEY, "0d5d5466");
       String logDirPath = FileUtils.createRootPath(this) + "/hcicloud";
       FileUtils.createDir(logDirPath);
       initparam.addParam(InitParam.LogParam.PARAM_KEY_LOG_FILE_PATH, logDirPath);
       initparam.addParam(InitParam.LogParam.PARAM_KEY_LOG_FILE_COUNT, "5");
       initparam.addParam(InitParam.LogParam.PARAM_KEY_LOG_FILE_SIZE, "1024");
       initparam.addParam(InitParam.LogParam.PARAM_KEY_LOG_LEVEL, "5");
       int errCode = HciCloudSys.hciInit(initparam.getStringConfig(), this);
       if (errCode != HciErrorCode.HCI_ERR_NONE) {
           LogUtils.e("HciCloud初始化失败" + errCode);
           return;
       }
       LogUtils.e("HciCloud初始化成功");
   }
}
