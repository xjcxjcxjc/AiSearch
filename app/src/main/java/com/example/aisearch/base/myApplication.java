package com.example.aisearch.base;

import android.app.Application;
import android.content.Context;

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
        context=getApplicationContext();
    }

    public static Context getMyContext(){
        return context;
    }


}