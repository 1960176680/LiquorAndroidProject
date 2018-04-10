package com.hz.tt.mvp.ui.fragment;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.hz.tt.R;
import com.hz.tt.mvp.model.entity.cache.UserCache;
import com.hz.tt.mvp.presenter.MeFgPresenter;
import com.hz.tt.mvp.ui.activity.EvaluateActivity;
import com.hz.tt.mvp.ui.activity.MainActivity;
import com.hz.tt.mvp.ui.activity.WorkRecordActivity;
import com.hz.tt.mvp.ui.common.BaseFragment;
import com.hz.tt.mvp.ui.view.IMeFgView;
import com.hz.tt.util.LogUtils;
import com.hz.tt.widget.CustomDialog;
import com.zhy.autolayout.AutoLinearLayout;

import butterknife.Bind;

/**
 * @描述 我界面
 */
public class MeFragment extends BaseFragment<IMeFgView, MeFgPresenter> implements IMeFgView {

    private CustomDialog mQrCardDialog;

    @Bind(R.id.line_suggest)
    AutoLinearLayout line_suggest;
    @Bind(R.id.line_record)
    AutoLinearLayout line_record;
    @Bind(R.id.line_forgetkey)
    AutoLinearLayout line_forgetkey;
    @Bind(R.id.tv_user)
    TextView tv_user;
    @Override
    public void init() {
        registerBR();
    }

    @Override
    public void initData() {
//        mPresenter.loadUserInfo();
    }

    @Override
    public void initView(View rootView) {
        tv_user.setText(UserCache.getPhone());
    }

    @Override
    public void initListener() {

        line_suggest.setOnClickListener(v -> ((MainActivity) getActivity()).jumpToActivity(EvaluateActivity.class));
        line_record.setOnClickListener(v -> ((MainActivity) getActivity()).jumpToActivity(WorkRecordActivity.class));
        line_forgetkey.setOnClickListener(v -> mPresenter.resetKey());

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
//        unregisterBR();
    }

    private void showQRCard() {
    }

    private void loadQRCardError(Throwable throwable) {
        LogUtils.sf(throwable.getLocalizedMessage());
    }

    private void registerBR() {
    }

    private void unregisterBR() {
//        BroadcastManager.getInstance(getActivity()).unregister(AppConst.CHANGE_INFO_FOR_ME);
    }

    @Override
    protected MeFgPresenter createPresenter() {
        return new MeFgPresenter((MainActivity) getActivity());
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.fragment_more;
    }

    @Override
    public ImageView getIvHeader() {
        return null;
    }

    @Override
    public TextView getTvName() {
        return null;
    }

    @Override
    public TextView getTvAccount() {
        return null;
    }
}
