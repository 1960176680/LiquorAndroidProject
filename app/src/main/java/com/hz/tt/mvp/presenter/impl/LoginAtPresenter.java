package com.hz.tt.mvp.presenter.impl;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.hz.tt.R;
import com.hz.tt.api.okHttpUtils.OkHttpUtils;
import com.hz.tt.mvp.model.entity.exception.ServerException;
import com.hz.tt.mvp.model.entity.request.LoginRequest;
import com.hz.tt.mvp.model.entity.response.LoginResponse;
import com.hz.tt.mvp.presenter.base.BasePresenter;
import com.hz.tt.mvp.ui.activity.ForgetKeyActivity;
import com.hz.tt.mvp.ui.activity.MainActivity;
import com.hz.tt.mvp.ui.activity.RegisterActivity;
import com.hz.tt.mvp.ui.common.BaseActivity;
import com.hz.tt.mvp.ui.view.LoginAtView;
import com.hz.tt.util.LogUtils;
import com.hz.tt.util.UIUtils;
import com.squareup.okhttp.OkHttpClient;

public class LoginAtPresenter extends BasePresenter<LoginAtView> {
    public LoginAtPresenter(BaseActivity context) {
        super(context);}

    public void login() {
        String phone = getView().getEtPhone().getText().toString().trim();
        String pwd = getView().getEtPwd().getText().toString().trim();

        if (TextUtils.isEmpty(phone)) {
            UIUtils.showToast(UIUtils.getString(R.string.phone_not_empty));
            mContext.speechUtil.speakXunFei("手机号不能为空");
            return;
        }
        if (TextUtils.isEmpty(pwd)) {
            UIUtils.showToast(UIUtils.getString(R.string.password_not_empty));
            mContext.speechUtil.speakXunFei("密码不能为空");
            return;
        }

        mContext.showWaitingDialog(UIUtils.getString(R.string.please_wait));
        OkHttpUtils okHttpUtils=OkHttpUtils.initClient();
        okHttpUtils.setOnResultListener(newstr1 -> mContext.runOnUiThread(() -> {
            mContext.hideWaitingDialog();
            if (newstr1.equals("数据请求失败")) {
                mContext.speechUtil.speakXunFei("数据请求失败");
                return;
            }
            Gson gson = new Gson();
            LoginResponse response = gson.fromJson(newstr1, LoginResponse.class);
            String code = response.getErrorCode();
            if (code.equals("1000")) {
//                    UserCache.save(loginResponse.getResult().getId(), phone, loginResponse.getResult().getToken());
                mContext.jumpToActivity(MainActivity.class);
                mContext.finish();
            } else {
                loginError(new ServerException(UIUtils.getString(R.string.login_error) + code));
            }
        }));
        okHttpUtils.myEnqueue(new LoginRequest(phone,pwd).getUrl());


//        ApiRetrofit.getInstance().login(phone, pwd)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Consumer<String>() {
//                    @Override
//                    public void accept(String loginResponse) throws Exception {
////                    int code = loginResponse.getCode();
////                    mContext.hideWaitingDialog();
////                    if (code == 200) {
////                        UserCache.save(loginResponse.getResult().getId(), phone, loginResponse.getResult().getToken());
////                        mContext.jumpToActivityAndClearTask(MainActivity.class);
////                        mContext.finish();
////                    } else {
////                        loginError(new ServerException(UIUtils.getString(R.string.login_error) + code));
////                    }
//                    }
//                }, this::loginError);
    }
    private void loginError(Throwable throwable) {
        LogUtils.e(throwable.getLocalizedMessage());
        UIUtils.showToast(throwable.getLocalizedMessage());
        mContext.hideWaitingDialog();
    }

    public void register(){
        mContext.jumpToActivity(RegisterActivity.class);
    }
    public void forgetKey(){
        mContext.jumpToActivity(ForgetKeyActivity.class);
    }

}
