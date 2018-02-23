package com.hz.tt.mvp.ui.activity;

import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.hz.tt.R;
import com.hz.tt.mvp.presenter.impl.SettingPasswordAtPresent;
import com.hz.tt.mvp.ui.common.BaseActivity;
import com.hz.tt.mvp.ui.view.ISettingPasswordAtView;

import butterknife.Bind;

/**
 * Created by Administrator on 2018-02-13.
 */

public class SettingPasswordActivity extends BaseActivity<ISettingPasswordAtView,SettingPasswordAtPresent> implements ISettingPasswordAtView{
     @Bind(R.id.etPhone)
     EditText etPhone;
    @Bind(R.id.etOld)
    EditText etOld;
    @Bind(R.id.etNew)
    EditText etNew;
    @Bind(R.id.btnRegister)
    TextView btnRegister;
    @Bind(R.id.ivSeePwd)
    ImageView ivSeePwd;
    @Override
    public void initView() {
        setToolbarTitle("重置密码");
    }

    @Override
    public void initListener() {
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.setPsw();
            }
        });


        ivSeePwd.setOnClickListener(v -> {
            if (etNew.getTransformationMethod() == HideReturnsTransformationMethod.getInstance()) {
                etNew.setTransformationMethod(PasswordTransformationMethod.getInstance());
            } else {
                etNew.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            }

            etNew.setSelection(etNew.getText().toString().trim().length());
        });
    }

    @Override
    protected SettingPasswordAtPresent createPresenter() {
        return new SettingPasswordAtPresent(this);
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_setpsw;
    }


    @Override
    public EditText getEtUser() {
        return etPhone;
    }

    @Override
    public EditText getEtOldPassword() {
        return etOld;
    }

    @Override
    public EditText getEtNewPassword() {
        return etNew;
    }
}
