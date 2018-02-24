package com.hz.tt.mvp.model.entity.cache;

import com.hz.tt.app.AppConst;
import com.hz.tt.app.MyApp;
import com.hz.tt.util.SPUtils;

/**
 * @描述 用户缓存
 */
public class UserCache {

    public static String getId() {
        return SPUtils.getInstance(MyApp.getInstances().getApplicationContext()).getString(AppConst.User.ID, "");
    }

    public static String getPhone() {
        return SPUtils.getInstance(MyApp.getInstances().getApplicationContext()).getString(AppConst.User.PHONE, "");
    }

    public static String getToken() {
        return SPUtils.getInstance(MyApp.getInstances().getApplicationContext()).getString(AppConst.User.TOKEN, "");
    }

    public static void save(String phone, String key) {
        SPUtils.getInstance(MyApp.getInstances().getApplicationContext()).putString(AppConst.User.PHONE, phone);
        SPUtils.getInstance(MyApp.getInstances().getApplicationContext()).putString(AppConst.User.PASSWORD, key);
    }

    public static void clear() {
//        SPUtils.getInstance(MyApp.getInstances().getApplicationContext()).remove(AppConst.User.ID);
        SPUtils.getInstance(MyApp.getInstances().getApplicationContext()).remove(AppConst.User.PHONE);
//        SPUtils.getInstance(MyApp.getInstances().getApplicationContext()).remove(AppConst.User.TOKEN);
//        DBManager.getInstance().deleteAllUserInfo();
    }

}
