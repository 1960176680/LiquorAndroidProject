package com.hz.tt.mvp.ui.activity;

import com.hz.tt.R;
import com.hz.tt.mvp.presenter.impl.SettingAtPresenter;
import com.hz.tt.mvp.ui.common.BaseActivity;
import com.hz.tt.mvp.ui.view.ISettingAtView;

/**
 * Created by Administrator on 2018-02-23.
 */
public class SettingActivity extends BaseActivity<ISettingAtView,SettingAtPresenter>{
    @Override
    protected SettingAtPresenter createPresenter() {
        return new SettingAtPresenter(this);
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_setting;
    }
}
