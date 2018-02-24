package com.hz.tt.mvp.ui.activity;

import android.view.View;

import com.hz.tt.R;
import com.hz.tt.app.MyApp;
import com.hz.tt.mvp.model.entity.cache.UserCache;
import com.hz.tt.mvp.presenter.impl.SettingAtPresenter;
import com.hz.tt.mvp.ui.common.BaseActivity;
import com.hz.tt.mvp.ui.view.ISettingAtView;
import com.hz.tt.util.UIUtils;
import com.hz.tt.widget.CustomDialog;
import com.zhy.autolayout.AutoLinearLayout;

import butterknife.Bind;

/**
 * Created by Administrator on 2018-02-23.
 */
public class SettingActivity extends BaseActivity<ISettingAtView,SettingAtPresenter>{
    @Bind(R.id.line_about)
    AutoLinearLayout line_about;
    @Bind(R.id.line_exit)
    AutoLinearLayout line_exit;


    private View mExitView;
    private CustomDialog mExitDialog;
    @Override
    public void initListener() {
        line_about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UIUtils.showToast("正在研发中...");
            }
        });

        line_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UIUtils.showToast("正在研发中...");
            }
        });


        line_exit.setOnClickListener(v -> {
            if (mExitView == null) {
                mExitView = View.inflate(this, R.layout.dialog_exit, null);
                mExitDialog = new CustomDialog(this, mExitView, R.style.MyDialog);
                mExitView.findViewById(R.id.tvExitAccount).setOnClickListener(v1 -> {
                    UserCache.clear();
                    mExitDialog.dismiss();
                    MyApp.exit();
                    jumpToActivityAndClearTask(LoginActivity.class);
                });
                mExitView.findViewById(R.id.tvExitApp).setOnClickListener(v1 -> {
//                    RongIMClient.getInstance().disconnect();
                    mExitDialog.dismiss();
//                    onBackPressed();
//                    MyApp.exit();
                });
            }
            mExitDialog.show();
        });






    }

    @Override
    protected SettingAtPresenter createPresenter() {
        return new SettingAtPresenter(this);
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_setting;
    }
}
