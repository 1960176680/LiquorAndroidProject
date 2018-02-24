package com.hz.tt.app;


import com.hz.tt.util.FileUtils;
import com.hz.tt.util.LogUtils;

/**
 * @描述 全局常量类
 */
public class AppConst {

    public static final String TAG = "CSDN_LQR";
    public static final int DEBUGLEVEL = LogUtils.LEVEL_ALL;//日志输出级别

    public static final String REGION = "86";

    /*================== 广播Action begin ==================*/
    public static final class User {

        public static final String PHONE = "phone";
        public static final String PASSWORD = "password";
        public static final String ID = "id";
                public static final String ACCOUNT = "account";
        public static final String TOKEN = "token";
    }
    //语音存放位置
    public static final String AUDIO_SAVE_DIR = FileUtils.getDir("audio");
    public static final int DEFAULT_MAX_AUDIO_RECORD_TIME_SECOND = 120;
    //视频存放位置
    public static final String VIDEO_SAVE_DIR = FileUtils.getDir("video");
    //照片存放位置
    public static final String PHOTO_SAVE_DIR = FileUtils.getDir("photo");
    //头像保存位置
    public static final String HEADER_SAVE_DIR = FileUtils.getDir("header");
}
