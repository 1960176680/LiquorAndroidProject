package com.hz.tt.mvp.presenter.impl;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.hz.tt.R;
import com.hz.tt.api.okHttpUtils.OkHttpUtils;
import com.hz.tt.mvp.model.entity.RegisterBean;
import com.hz.tt.mvp.model.entity.request.RegisterRequest;
import com.hz.tt.mvp.model.entity.response.LoginResponse;
import com.hz.tt.mvp.presenter.base.BasePresenter;
import com.hz.tt.mvp.ui.common.BaseActivity;
import com.hz.tt.mvp.ui.view.IRegisterAtView;
import com.hz.tt.util.LogUtils;
import com.hz.tt.util.RegularUtils;
import com.hz.tt.util.UIUtils;

public class RegisterAtPresenter extends BasePresenter<IRegisterAtView> {

    public RegisterAtPresenter(BaseActivity context) {
        super(context);
    }

    private void sendCodeError(Throwable throwable) {
        mContext.hideWaitingDialog();
        LogUtils.e(throwable.getLocalizedMessage());
        UIUtils.showToast(throwable.getLocalizedMessage());
    }

    public void register() {
        String phone = getView().getEtPhone().getText().toString().trim();
        String password = getView().getEtPwd().getText().toString().trim();
        if (TextUtils.isEmpty(phone)) {
            UIUtils.showToast(UIUtils.getString(R.string.phone_not_empty));
            return;
        }
        if (TextUtils.isEmpty(password)) {
            UIUtils.showToast(UIUtils.getString(R.string.password_not_empty));
            return;
        }
        if (!RegularUtils.isMobile(phone)) {
            UIUtils.showToast(UIUtils.getString(R.string.phone_format_error));
            return;
        }
        mContext.showWaitingDialog(UIUtils.getString(R.string.please_wait));
        OkHttpUtils okHttpUtils=OkHttpUtils.initClient();
        okHttpUtils.setOnResultListener(new OkHttpUtils.ResultListener() {
            @Override
            public void sendData(String newstr1) {
                mContext.runOnUiThread(() -> {
                    mContext.hideWaitingDialog();
                    if (newstr1.equals("数据请求失败")) {
                        UIUtils.showToast("数据请求失败");
                        return;
                    }
                    Gson gson = new Gson();
                    LoginResponse response = gson.fromJson(newstr1, LoginResponse.class);
                    String code = response.getErrorCode();
                    if (code.equals("1000")) {
//                    UserCache.save(loginResponse.getResult().getId(), phone, loginResponse.getResult().getToken());
//                        mContext.jumpToActivity(MainActivity.class);
                        UIUtils.showToast("注册成功！");
//                        mContext.finish();
                    } else {
//                        loginError(new ServerException(UIUtils.getString(R.string.login_error) + code));
                    }
                });
            }
        });
        RegisterBean bean=new RegisterBean();
        bean.setPhone(getView().getEtPhone().getText().toString());
        bean.setPassword(getView().getEtPwd().getText().toString());
        okHttpUtils.myEnqueue(new RegisterRequest(bean).getUrl(),null);
    }

    private void registerError(Throwable throwable) {
        LogUtils.sf(throwable.getLocalizedMessage());
        UIUtils.showToast(throwable.getLocalizedMessage());
    }

    public void unsubscribe() {
//        if (mSubscription != null) {
//            mSubscription.unsubscribe();
//            mSubscription = null;
//        }
    }

}
