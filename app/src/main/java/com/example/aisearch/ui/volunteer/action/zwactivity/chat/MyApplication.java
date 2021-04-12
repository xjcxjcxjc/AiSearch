package com.example.aisearch.ui.volunteer.action.zwactivity.chat;

import android.app.Application;

public class MyApplication extends Application {
    public static Application mApplication;

    @Override
    public void onCreate() {
        super.onCreate();
        mApplication=this;
    }
}
