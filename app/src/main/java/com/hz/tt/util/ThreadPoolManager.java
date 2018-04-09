package com.hz.tt.util;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 2018-04-08.
 */

public class ThreadPoolManager {
    private static ThreadPoolManager mThreadPoolManager;
    private final BlockingQueue<Runnable> mDecodeWorkQueue;
    public ThreadPoolExecutor mThreadPoolExecutor;
    private static final int KEEP_ALIVE_TIME = 1;
    private static final TimeUnit KEEP_ALIVE_TIME_UNIT = TimeUnit.SECONDS;
    private static int NUMBER_OF_CORES =
            Runtime.getRuntime().availableProcessors();
    private static int CORE_OF_SIZE =3;
    public Handler mHandler=new Handler(Looper.getMainLooper()){
        @Override
        public void handleMessage(Message msg) {

        }
    };
    private ThreadPoolManager() {
        mDecodeWorkQueue = new LinkedBlockingQueue<Runnable>();
        mThreadPoolExecutor = new ThreadPoolExecutor(
                CORE_OF_SIZE,
                NUMBER_OF_CORES,
                KEEP_ALIVE_TIME,
                KEEP_ALIVE_TIME_UNIT,
                mDecodeWorkQueue);
    }
    static  {
        mThreadPoolManager = new ThreadPoolManager();
    }
    public static ThreadPoolManager getInstance(){
        return mThreadPoolManager;
    }

    public void handleState(int state) {
        switch (state) {

            case 1:

                mThreadPoolExecutor.execute(new Runnable() {
                    @Override
                    public void run() {

                    }
                });
        }
    }
}
