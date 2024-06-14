package com.example.hxplay.app;


import android.app.Application;

import com.example.hxplay.utils.okHttpUtil;


public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        okHttpUtil.getInstance().getOkHttpClient();
    }

}
