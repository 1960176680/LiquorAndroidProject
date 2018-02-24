package com.hz.tt.mvp.presenter.impl;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.hz.tt.R;
import com.hz.tt.api.okHttpUtils.OkHttpUtils;
import com.hz.tt.mvp.model.entity.cache.UserCache;
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

public class LoginAtPresenter extends BasePresenter<LoginAtView> {


    public LoginAtPresenter(BaseActivity context) {
        super(context);}

    public void login() {
        String phone = getView().getEtPhone().getText().toString().trim();
//        NetConstant.USER_NAME=phone;
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
            LoginResponse response = null;
            try {
                response = gson.fromJson(newstr1, LoginResponse.class);
            } catch (JsonSyntaxException e) {
                mContext.speechUtil.speakXunFei("服务器异常");
                loginError(new ServerException(UIUtils.getString(R.string.login_error)));
                e.printStackTrace();
                return;
            }
            String code = response.getErrorCode();
            if (code.equals("1000")) {
                UserCache.save(phone, pwd);
                mContext.jumpToActivityAndClearTop(MainActivity.class);
                mContext.finish();
            } else {
                loginError(new ServerException(UIUtils.getString(R.string.login_error) + code));
            }
        }));
        okHttpUtils.myEnqueue(new LoginRequest(phone,pwd).getUrl(),null);
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
