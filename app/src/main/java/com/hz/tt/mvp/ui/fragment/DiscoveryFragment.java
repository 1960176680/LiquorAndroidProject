package com.hz.tt.mvp.ui.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.hz.tt.R;
import com.hz.tt.api.okHttpUtils.NetConstant;
import com.hz.tt.mvp.presenter.impl.DiscoveryFgPresenter;
import com.hz.tt.mvp.ui.activity.MainActivity;
import com.hz.tt.mvp.ui.activity.ScanActivity;
import com.hz.tt.mvp.ui.common.BaseFragment;
import com.hz.tt.mvp.ui.view.IDiscoveryFgView;
import com.lqr.recyclerview.LQRRecyclerView;

import butterknife.Bind;

/**
 * @描述 发现界面
 */
public class DiscoveryFragment extends BaseFragment<IDiscoveryFgView, DiscoveryFgPresenter> implements IDiscoveryFgView {

    @Bind(R.id.iv_scan)
    ImageView iv_scan;
    @Bind(R.id.et_scan)
    EditText et_scan;
    @Bind(R.id.iv_query)
    ImageView iv_query;
    @Bind(R.id.listview)
    LQRRecyclerView listview;
//    @Bind(R.id.oivShop)
//    OptionItemView mOivShop;
//    @Bind(R.id.oivGame)
//    OptionItemView mOivGame;


    @Override
    public void initView(View rootView) {
        mPresenter.setAdapter();



     }

    @Override
    public void initListener() {
        Intent intent=new Intent(this.getActivity(),ScanActivity.class);
        intent.putExtra("flag","query");
        iv_scan.setOnClickListener(v -> ((MainActivity) getActivity()).jumpToActivity(intent));
        iv_query.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.query();
            }
        });


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

    @Override
    public void onResume() {
        super.onResume();
        if (!NetConstant.SCAN_RESULT_QUERY.equals("")){
            et_scan.setText(NetConstant.SCAN_RESULT_QUERY);
        }
    }


    @Override
    public ImageView getQueryV() {
        return iv_query;
    }

    @Override
    public EditText getEtScanV() {
        return et_scan;
    }

    @Override
    public ImageView getIvScanV() {
        return iv_scan;
    }

    @Override
    public LQRRecyclerView getRvRecyclerView() {
        return listview;
    }

}
