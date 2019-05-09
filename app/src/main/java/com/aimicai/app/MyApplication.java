package com.aimicai.app;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;

public class MyApplication extends Application {

    @SuppressLint("StaticFieldLeak")
    private static Context context;
    private static Handler handler;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        handler = new Handler(Looper.getMainLooper());
    }


    /**
     * 获取全局Context，在代码的任意位置都可以调用，随时都能获取到全局Context对象。
     *
     * @return 全局Context对象。
     */
    public static Context getContext() {
        return context;
    }

    /**
     * 获取创建在主线程上的Handler对象。
     *
     * @return 创建在主线程上的Handler对象。
     */
    public static Handler getHandler() {
        return handler;
    }
}
