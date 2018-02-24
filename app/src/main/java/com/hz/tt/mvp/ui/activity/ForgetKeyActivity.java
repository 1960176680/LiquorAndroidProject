package com.hz.tt.mvp.ui.activity;

import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.hz.tt.R;
import com.hz.tt.mvp.presenter.impl.ForgetKeyAtPresenter;
import com.hz.tt.mvp.ui.common.BaseActivity;
import com.hz.tt.mvp.ui.view.ForgetKeyAtView;
import com.hz.tt.util.UIUtils;

import butterknife.Bind;

/**
 * Created ZhouWenGuang
 */

public class ForgetKeyActivity extends BaseActivity<ForgetKeyAtView,ForgetKeyAtPresenter> implements ForgetKeyAtView{

    @Bind(R.id.etPhone)
    EditText etPhone;
    @Bind(R.id.etKey)
    EditText etKey;
    @Bind(R.id.btnSubmit)
    Button btnSubmit;
    @Bind(R.id.btnCancel)
    Button btnCancel;
    @Bind(R.id.ivSeePwd)
    ImageView ivSeePwd;
    @Override
    public void initView() {
        setToolbarTitle(UIUtils.getString(R.string.forget_title));
    }

    @Override
    public void initListener() {
        btnSubmit.setOnClickListener(v -> {
            if (canSubmit()){
                mPresenter.forgetKey();
            }else {
                UIUtils.showToast("请检查手机号号密码是否为空！");
            }

        });
        btnCancel.setOnClickListener(v -> finish());


        ivSeePwd.setOnClickListener(v -> {
            if (etKey.getTransformationMethod() == HideReturnsTransformationMethod.getInstance()) {
                etKey.setTransformationMethod(PasswordTransformationMethod.getInstance());
            } else {
                etKey.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            }

            etKey.setSelection(etKey.getText().toString().trim().length());
        });

    }

    @Override
    protected ForgetKeyAtPresenter createPresenter() {
        return new ForgetKeyAtPresenter(this);
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_forgetkey;
    }
    private boolean canSubmit() {
        int pwdLength = etKey.getText().toString().trim().length();
        int phoneLength = etPhone.getText().toString().trim().length();
        if (pwdLength >0 && phoneLength >0) {
            return true;
        }
        return false;
    }
    @Override
    public EditText getEtPhoneV() {
        return etPhone;
    }

    @Override
    public EditText getEtKeyV() {
        return etKey;
    }
}
