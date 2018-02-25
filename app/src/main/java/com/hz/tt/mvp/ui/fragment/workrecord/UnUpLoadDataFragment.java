package com.hz.tt.mvp.ui.fragment.workrecord;


import android.widget.Button;
import android.widget.TextView;

import com.hz.tt.R;
import com.hz.tt.mvp.presenter.impl.UnUpLoadFgPresenter;
import com.hz.tt.mvp.ui.activity.WorkRecordActivity;
import com.hz.tt.mvp.ui.common.BaseFragment;
import com.hz.tt.mvp.ui.view.IUnUpLoadFgView;
import com.lqr.recyclerview.LQRRecyclerView;

import butterknife.Bind;

/**
 * 未上传数据列表
 * Created by ZhouWenGuang
 */

public class UnUpLoadDataFragment extends BaseFragment<IUnUpLoadFgView, UnUpLoadFgPresenter> implements IUnUpLoadFgView {

    @Bind(R.id.tvCount)
    TextView tvCount;
    @Bind(R.id.recyclerView)
    LQRRecyclerView recyclerView;
    @Bind(R.id.btnUpload)
    Button btnUpload;


    @Override
    public void initData() {
        mPresenter.getConversations();
        mPresenter.loadRecordData();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        if (isVisibleToUser){
            if (mPresenter!=null){
                mPresenter.loadRecordData();
            }
        }


    }


    @Override
    protected UnUpLoadFgPresenter createPresenter() {
        return new UnUpLoadFgPresenter(((WorkRecordActivity) getActivity()));
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.fragment_unuprecord;
    }

    @Override
    public LQRRecyclerView getLQRRecyclerView() {
        return recyclerView;
    }

    @Override
    public TextView getVCount() {
        return tvCount;
    }
}
