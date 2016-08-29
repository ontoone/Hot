package com.mansoul.hot.app;

import android.app.Application;
import android.os.Handler;

import com.orhanobut.logger.LogLevel;
import com.orhanobut.logger.Logger;

/**
 * Created by Mansoul on 16/8/13.
 */
public class MyApplication extends Application {
    private static MyApplication mApplication;
    private static long mUIThreadId;
    private static Handler mHandler;

    @Override
    public void onCreate() {
        super.onCreate();

        mApplication = this;
        mHandler = new Handler();
        Logger
                .init()
                .hideThreadInfo()
                .setLogLevel(LogLevel.FULL);
        mUIThreadId = Thread.currentThread().getId();

    }


    public static MyApplication getInstance() {
        return mApplication;
    }

    public static Handler getHandler() {
        return mHandler;
    }

    public static void runOnUIThread(Runnable r) {
        mHandler.post(r);
    }

    public static boolean isRunOnUIThread(long threadId) {
        return mUIThreadId == threadId;
    }

}
