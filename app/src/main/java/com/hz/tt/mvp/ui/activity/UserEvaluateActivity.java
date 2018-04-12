package com.hz.tt.mvp.ui.activity;

import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.hz.tt.R;
import com.hz.tt.mvp.presenter.impl.UserEvaluateAtPresenter;
import com.hz.tt.mvp.ui.common.BaseActivity;
import com.hz.tt.mvp.ui.view.IUserEvaluateAtView;
import com.lqr.recyclerview.LQRRecyclerView;

import butterknife.Bind;

/**
 * Created by Administrator on 2018-04-11.
 */

public class UserEvaluateActivity extends BaseActivity<IUserEvaluateAtView,UserEvaluateAtPresenter> implements IUserEvaluateAtView{
    //    扫描
    @Bind(R.id.iv_scan)
    ImageView iv_scan;
    @Bind(R.id.et_scan)
    EditText et_scan;
    @Bind(R.id.btnQuery)
    Button btnQuery;
    @Bind(R.id.mRecycleview)
    LQRRecyclerView mRecycleview;
    @Override
    public void initView() {
        setToolbarTitle("用户评价查询");
    }

    @Override
    public void initListener() {
        btnQuery.setOnClickListener(v -> mPresenter.queryComment());
    }

    @Override
    public void initData() {
        mPresenter.getConversations();
    }

    @Override
    protected UserEvaluateAtPresenter createPresenter() {
        return new UserEvaluateAtPresenter(this);
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_userevaluate;
    }

    @Override
    public EditText getEtScan() {
        return et_scan;
    }

    @Override
    public LQRRecyclerView getRecycleView() {
        return mRecycleview;
    }
}
