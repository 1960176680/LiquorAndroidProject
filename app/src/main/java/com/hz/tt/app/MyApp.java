package com.hz.tt.app;

import android.app.ActivityManager;
import android.content.Context;

import com.hz.tt.app.base.BaseApp;

import org.litepal.LitePal;

/**
 *
 * @创建者 CSDN_LQR
 * @描述 BaseApp的拓展，用于设置其他第三方的初始化
 */
public class MyApp extends BaseApp  {

    @Override
    public void onCreate() {
        super.onCreate();
        LitePal.initialize(this);
    }
    public static String getCurProcessName(Context context) {

        int pid = android.os.Process.myPid();

        ActivityManager activityManager = (ActivityManager) context
                .getSystemService(Context.ACTIVITY_SERVICE);

        for (ActivityManager.RunningAppProcessInfo appProcess : activityManager
                .getRunningAppProcesses()) {

            if (appProcess.pid == pid) {
                return appProcess.processName;
            }
        }
        return null;
    }

}
