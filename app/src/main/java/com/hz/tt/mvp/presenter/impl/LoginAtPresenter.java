package com.hz.tt.mvp.presenter.impl;

import android.text.TextUtils;

import com.hz.tt.R;
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
        super(context);
    }

    public void login() {
        String phone = getView().getEtPhone().getText().toString().trim();
        String pwd = getView().getEtPwd().getText().toString().trim();

        if (TextUtils.isEmpty(phone)) {
            UIUtils.showToast(UIUtils.getString(R.string.phone_not_empty));
            return;
        }
        if (TextUtils.isEmpty(pwd)) {
            UIUtils.showToast(UIUtils.getString(R.string.password_not_empty));
            return;
        }

        mContext.showWaitingDialog(UIUtils.getString(R.string.please_wait));
        mContext.hideWaitingDialog();
        mContext.jumpToActivityAndClearTask(MainActivity.class);
        mContext.finish();

//        ApiRetrofit.getInstance().login(AppConst.REGION, phone, pwd)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(loginResponse -> {
//                    int code = loginResponse.getCode();
//                    mContext.hideWaitingDialog();
//                    if (code == 200) {
//                        UserCache.save(loginResponse.getResult().getId(), phone, loginResponse.getResult().getToken());
//                        mContext.jumpToActivityAndClearTask(MainActivity.class);
//                        mContext.finish();
//                    } else {
//                        loginError(new ServerException(UIUtils.getString(R.string.login_error) + code));
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
