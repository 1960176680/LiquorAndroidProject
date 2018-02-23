package com.hz.tt.mvp.presenter.impl;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.hz.tt.R;
import com.hz.tt.api.okHttpUtils.OkHttpUtils;
import com.hz.tt.mvp.model.entity.SettingPswBean;
import com.hz.tt.mvp.model.entity.request.SetPswRequest;
import com.hz.tt.mvp.model.entity.response.SetPswResponse;
import com.hz.tt.mvp.presenter.base.BasePresenter;
import com.hz.tt.mvp.ui.common.BaseActivity;
import com.hz.tt.mvp.ui.view.ISettingPasswordAtView;
import com.hz.tt.util.RegularUtils;
import com.hz.tt.util.UIUtils;

/**
 * Created by Administrator on 2018-02-13.
 */

public class SettingPasswordAtPresent extends BasePresenter<ISettingPasswordAtView> {
    public SettingPasswordAtPresent(BaseActivity context) {
        super(context);
    }

    public void setPsw(){
        String phone = getView().getEtUser().getText().toString().trim();
        String oldPsd = getView().getEtOldPassword().getText().toString().trim();
        String newPsd = getView().getEtNewPassword().getText().toString().trim();
        if (TextUtils.isEmpty(phone)) {
            UIUtils.showToast(UIUtils.getString(R.string.phone_not_empty));
            mContext.speechUtil.speakXunFei("手机号不能为空");
            return;
        }
        if (TextUtils.isEmpty(oldPsd)) {
            UIUtils.showToast(UIUtils.getString(R.string.password_not_empty));
            mContext.speechUtil.speakXunFei("旧密码不能为空");
            return;
        }
        if (TextUtils.isEmpty(newPsd)) {
            UIUtils.showToast(UIUtils.getString(R.string.password_not_empty));
            mContext.speechUtil.speakXunFei("新密码不能为空");
            return;
        }

        if (!RegularUtils.isMobile(phone)) {
            UIUtils.showToast(UIUtils.getString(R.string.phone_format_error));
            mContext.speechUtil.speakXunFei("手机号不正确请检查");
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
                    SetPswResponse response = gson.fromJson(newstr1, SetPswResponse.class);
                    String code = response.getErrorCode();
                    if (code.equals("1000")) {
//                    UserCache.save(loginResponse.getResult().getId(), phone, loginResponse.getResult().getToken());
//                        mContext.jumpToActivity(MainActivity.class);
                        UIUtils.showToast("修改成功！");
                        mContext.hideWaitingDialog();
                        mContext.finish();
                    } else {
                        mContext.hideWaitingDialog();
                        mContext.speechUtil.speakXunFei(response.getErrorMsg());
                        UIUtils.showToast(response.getErrorMsg());
//                        loginError(new ServerException(UIUtils.getString(R.string.login_error) + code));
                    }
                });
            }
        });
        SettingPswBean bean=new SettingPswBean();
        bean.setUserName(getView().getEtUser().getText().toString());
        bean.setOldPassword(getView().getEtOldPassword().getText().toString());
        bean.setNewPassword(getView().getEtNewPassword().getText().toString());
        okHttpUtils.myEnqueue(new SetPswRequest().getUrl(bean),null);
    }

}
