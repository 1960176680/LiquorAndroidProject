package com.hz.tt.mvp.ui.fragment;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.hz.tt.R;
import com.hz.tt.mvp.presenter.MeFgPresenter;
import com.hz.tt.mvp.ui.activity.MainActivity;
import com.hz.tt.mvp.ui.common.BaseFragment;
import com.hz.tt.mvp.ui.view.IMeFgView;
import com.hz.tt.util.LogUtils;
import com.hz.tt.util.UIUtils;
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
    }

    @Override
    public void initListener() {

        line_suggest.setOnClickListener(v -> UIUtils.showToast("正在研发中，敬请期待！"));
        line_record.setOnClickListener(v -> UIUtils.showToast("正在研发中，敬请期待！"));
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
