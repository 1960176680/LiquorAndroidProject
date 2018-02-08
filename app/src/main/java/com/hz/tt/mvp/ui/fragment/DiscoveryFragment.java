package com.hz.tt.mvp.ui.fragment;
import android.view.View;

import com.hz.tt.R;
import com.hz.tt.mvp.presenter.impl.DiscoveryFgPresenter;
import com.hz.tt.mvp.ui.activity.MainActivity;
import com.hz.tt.mvp.ui.common.BaseFragment;
import com.hz.tt.mvp.ui.view.IDiscoveryFgView;

/**
 * @描述 发现界面
 */
public class DiscoveryFragment extends BaseFragment<IDiscoveryFgView, DiscoveryFgPresenter> implements IDiscoveryFgView {

//    @Bind(R.id.oivScan)
//    OptionItemView mOivScan;
//    @Bind(R.id.oivShop)
//    OptionItemView mOivShop;
//    @Bind(R.id.oivGame)
//    OptionItemView mOivGame;


    @Override
    public void initView(View rootView) {
     }

    @Override
    public void initListener() {
//        mOivScan.setOnClickListener(v -> ((MainActivity) getActivity()).jumpToActivity(MainActivity.class/*ScanActivity.class*/));
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
