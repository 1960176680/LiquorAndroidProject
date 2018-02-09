package com.hz.tt.mvp.ui.fragment;

import android.view.View;
import android.widget.ImageView;

import com.hz.tt.R;
import com.hz.tt.mvp.presenter.impl.DiscoveryFgPresenter;
import com.hz.tt.mvp.ui.activity.MainActivity;
import com.hz.tt.mvp.ui.activity.ScanActivity;
import com.hz.tt.mvp.ui.common.BaseFragment;
import com.hz.tt.mvp.ui.view.IDiscoveryFgView;

import butterknife.Bind;

/**
 * @描述 发现界面
 */
public class DiscoveryFragment extends BaseFragment<IDiscoveryFgView, DiscoveryFgPresenter> implements IDiscoveryFgView {

    @Bind(R.id.iv_scan)
    ImageView iv_scan;
//    @Bind(R.id.oivShop)
//    OptionItemView mOivShop;
//    @Bind(R.id.oivGame)
//    OptionItemView mOivGame;


    @Override
    public void initView(View rootView) {
     }

    @Override
    public void initListener() {
        iv_scan.setOnClickListener(v -> ((MainActivity) getActivity()).jumpToActivity(ScanActivity.class));
//        mOivShop.setOnClickListener(v -> ((MainActivity) getActivity()).jumpToWebViewActivity(AppConst.WeChatUrl.JD));
//        mOivGame.setOnClickListener(v -> ((MainActivity) getActivity()).jumpToWebViewActivity(AppConst.WeChatUrl.GAME));
    }

    @Override
    protected DiscoveryFgPresenter createPresenter() {
        return new DiscoveryFgPresenter((MainActivity) getActivity());
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.fragment_query;
    }
}
