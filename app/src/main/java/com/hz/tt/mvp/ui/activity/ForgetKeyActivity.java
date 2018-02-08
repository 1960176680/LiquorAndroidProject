package com.hz.tt.mvp.ui.activity;

import com.hz.tt.R;
import com.hz.tt.mvp.presenter.impl.ForgetKeyAtPresenter;
import com.hz.tt.mvp.ui.common.BaseActivity;
import com.hz.tt.mvp.ui.view.ForgetKeyAtView;

/**
 * Created ZhouWenGuang
 */

public class ForgetKeyActivity extends BaseActivity<ForgetKeyAtView,ForgetKeyAtPresenter> {
    @Override
    protected ForgetKeyAtPresenter createPresenter() {
        return new ForgetKeyAtPresenter(this);
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_forgetkey;
    }
}
