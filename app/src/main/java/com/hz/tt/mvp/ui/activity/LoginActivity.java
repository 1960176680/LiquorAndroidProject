package com.hz.tt.mvp.ui.activity;


import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.hz.tt.R;
import com.hz.tt.mvp.presenter.impl.LoginAtPresenter;
import com.hz.tt.mvp.ui.common.BaseActivity;
import com.hz.tt.mvp.ui.view.LoginAtView;

import butterknife.Bind;

/**
 * @描述 登录界面
 */
public class LoginActivity extends BaseActivity<LoginAtView, LoginAtPresenter> implements LoginAtView {
//    用户名
    @Bind(R.id.etPhone)
    EditText mEtPhone;
//    密码
    @Bind(R.id.etPwd)
    EditText mEtPwd;
    //    登录
    @Bind(R.id.btnLogin)
    Button mBtnLogin;
//    注册
    @Bind(R.id.tv_register)
    TextView tv_register;
//    忘记密码
    @Bind(R.id.tv_forget)
    TextView tv_forget;



    @Override
    public void initView() {
//        mIbAddMenu.setVisibility(View.GONE);
    }

    @Override
    public void initListener() {
        mBtnLogin.setOnClickListener(v -> {
            speechUtil.speakXunFei("正在登录请稍后");
            mPresenter.login();
        });
        tv_register.setOnClickListener(v -> jumpToActivity(SettingPasswordActivity.class));
        tv_forget.setOnClickListener(v -> mPresenter.forgetKey());

        mEtPwd.addTextChangedListener(watcher);
        mEtPhone.addTextChangedListener(watcher);


        //        mEtPwd.setOnFocusChangeListener((v, hasFocus) -> {
//            if (hasFocus) {
////                mVLinePwd.setBackgroundColor(UIUtils.getColor(R.color.green0));
//            } else {
////                mVLinePwd.setBackgroundColor(UIUtils.getColor(R.color.line));
//            }
//        });
//        mEtPhone.setOnFocusChangeListener((v, hasFocus) -> {
//            if (hasFocus) {
////                mVLinePhone.setBackgroundColor(UIUtils.getColor(R.color.green0));
//            } else {
////                mVLinePhone.setBackgroundColor(UIUtils.getColor(R.color.line));
//            }
//        });
    }

    private boolean canLogin() {
        int pwdLength = mEtPwd.getText().toString().trim().length();
        int phoneLength = mEtPhone.getText().toString().trim().length();
        if (pwdLength > 0 && phoneLength > 0) {
            return true;
        }
        return false;
    }


    @Override
    protected LoginAtPresenter createPresenter() {
        return new LoginAtPresenter(this);
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_login;
    }

    @Override
    public EditText getEtPhone() {
        return mEtPhone;
    }

    @Override
    public EditText getEtPwd() {
        return mEtPwd;
    }

    TextWatcher watcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            mBtnLogin.setEnabled(canLogin());
        }

        @Override
        public void afterTextChanged(Editable s) {
        }
    };
}