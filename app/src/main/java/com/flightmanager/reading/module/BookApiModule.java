package com.flightmanager.reading.module;


import com.flightmanager.reading.api.BookApi;
import com.flightmanager.reading.api.support.HeaderInterceptor;
import com.flightmanager.reading.api.support.Logger;
import com.flightmanager.reading.api.support.LoggingInterceptor;

import java.util.concurrent.TimeUnit;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;

@Module
public class BookApiModule {

    @Provides
    public OkHttpClient provideOkHttpClient(){

        LoggingInterceptor logging=new LoggingInterceptor(new Logger());
        logging.setLevel(LoggingInterceptor.Level.BODY);

        OkHttpClient.Builder builder=new OkHttpClient.Builder().connectTimeout(10, TimeUnit.SECONDS)
                    .connectTimeout(20*1000,TimeUnit.MILLISECONDS)
                    .readTimeout(20*1000,TimeUnit.MILLISECONDS)
                    .retryOnConnectionFailure(true)//失败重发
                    .addInterceptor(new HeaderInterceptor())
                    .addInterceptor(logging);
        return builder.build();
    }


    @Provides
    protected BookApi provideBookService(OkHttpClient okHttpClient){
        return BookApi.getInstance(okHttpClient);
    }
}
