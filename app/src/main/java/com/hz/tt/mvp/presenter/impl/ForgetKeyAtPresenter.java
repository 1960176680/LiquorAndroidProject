package com.hz.tt.mvp.presenter.impl;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.hz.tt.R;
import com.hz.tt.api.okHttpUtils.OkHttpUtils;
import com.hz.tt.mvp.model.entity.cache.UserCache;
import com.hz.tt.mvp.model.entity.exception.ServerException;
import com.hz.tt.mvp.model.entity.request.ForgetKeyRequest;
import com.hz.tt.mvp.model.entity.response.LoginResponse;
import com.hz.tt.mvp.presenter.base.BasePresenter;
import com.hz.tt.mvp.ui.common.BaseActivity;
import com.hz.tt.mvp.ui.view.ForgetKeyAtView;
import com.hz.tt.util.LogUtils;
import com.hz.tt.util.UIUtils;

/**
 * Created by ZhouWenGuang
 */

public class ForgetKeyAtPresenter extends BasePresenter<ForgetKeyAtView>{
    public ForgetKeyAtPresenter(BaseActivity context) {
        super(context);
    }

    public void forgetKey() {
        String phone = getView().getEtPhoneV().getText().toString().trim();
        String key = getView().getEtKeyV().getText().toString().trim();
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
                UserCache.save(phone, key);
                mContext.speechUtil.speakXunFei("重置成功");
//                mContext.finish();
            } else {
                loginError(new ServerException(response.getErrorMsg()));
            }
        }));
        okHttpUtils.myEnqueue(new ForgetKeyRequest(phone,key).getUrl(),null);



    }
    private void loginError(Throwable throwable) {
        LogUtils.e(throwable.getLocalizedMessage());
        UIUtils.showToast(throwable.getLocalizedMessage());
        mContext.hideWaitingDialog();
    }
}
