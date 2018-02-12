package com.hz.tt.api.okHttpUtils;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


/**
 *
 */
public class OkHttpUtils
{
    private static OkHttpUtils mInstance;
    private OkHttpClient mOkHttpClient;
    private  ResultListener mResultListener;

    public OkHttpUtils()
    {
        if (mOkHttpClient == null)
        {
            mOkHttpClient = new OkHttpClient();
//            mOkHttpClient.setConnectTimeout(10, TimeUnit.SECONDS);
//            mOkHttpClient.setWriteTimeout(10, TimeUnit.SECONDS);
//            mOkHttpClient.setReadTimeout(30, TimeUnit.SECONDS);
        }
//        else{
//            mOkHttpClient = okHttpClient;
//        }

    }


//    OkHttp使用第一步
    public static OkHttpUtils initClient()
    {
        if (mInstance == null)
        {
            synchronized (OkHttpUtils.class)
            {
                if (mInstance == null)
                {
                    mInstance = new OkHttpUtils();
                }
            }
        }
        return mInstance;
    }

    public OkHttpClient getOkHttpClient()
    {
        return mOkHttpClient;
    }

//OkHttp使用第三步
    public void myEnqueue(String url, RequestBody requestBody)
    {
        Request request;
        if (requestBody==null){
            request = new Request.Builder()
                    .url(url)
                    .build();
        }else{
            request = new Request.Builder()
                    .post(requestBody)
                    .url(url)
                    .build();
        }

        mOkHttpClient.newCall(request)
                .enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        mResultListener.sendData("数据请求失败");
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        String responseUrl = response.body().string();
                        mResultListener.sendData(responseUrl);
                        if (response.body() != null)
                            response.body().close();
                    }

//                    @Override
//                    public void onFailure(Request request, IOException e) {
//                        mResultListener.sendData("数据请求失败");
//                    }

//                    @Override
//                    public void onResponse(Response response) throws IOException {
//                        String responseUrl = response.body().string();
//                        mResultListener.sendData(responseUrl);
//                        if (response.body() != null)
//                         response.body().close();
//                    }
        });
    }
    //OkHttp使用第二步
    public void setOnResultListener(ResultListener resultListener)
    {
        mResultListener=resultListener;
    }
    public interface ResultListener{
        public void sendData(String newstr1);
    }
}

