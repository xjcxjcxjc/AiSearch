package com.example.aisearch.base;

import android.app.Application;
import android.content.Context;

import com.baidu.mapapi.CoordType;
import com.baidu.mapapi.SDKInitializer;

/*
 * @Author : XJC
 * @Time : 2021/2/16 20:44
 * @Description :
 *
 */
public class myApplication extends Application {

    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        SDKInitializer.initialize(this);
        //自4.3.0起，百度地图SDK所有接口均支持百度坐标和国测局坐标，用此方法设置您使用的坐标类型.
        //包括BD09LL和GCJ02两种坐标，默认是BD09LL坐标。
        SDKInitializer.setCoordType(CoordType.BD09LL);
        context=getApplicationContext();
    }

    public static Context getMyContext(){
        return context;
    }



}