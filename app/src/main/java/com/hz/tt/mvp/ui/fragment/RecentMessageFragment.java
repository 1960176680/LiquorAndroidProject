package com.hz.tt.mvp.ui.fragment;

import android.view.View;

import com.hz.tt.R;
import com.hz.tt.mvp.presenter.impl.RecentMessageFgPresenter;
import com.hz.tt.mvp.ui.activity.MainActivity;
import com.hz.tt.mvp.ui.common.BaseFragment;
import com.hz.tt.mvp.ui.view.IRecentMessageFgView;
import com.hz.tt.util.UIUtils;

/**
 * @描述 最近会话列表界面
 */
public class RecentMessageFragment extends BaseFragment<IRecentMessageFgView, RecentMessageFgPresenter> implements IRecentMessageFgView {

    private boolean isFirst = true;
//    @Bind(R.id.rvRecentMessage)
//    LQRRecyclerView mRvRecentMessage;

    @Override
    public void init() {
//        registerBR();
    }

    @Override
    public void initView(View rootView) {

    }

    @Override
    public void onResume() {
        super.onResume();
        if (!isFirst) {
//            mPresenter.getConversations();
        }
    }

    @Override
    public void initData() {
//        UIUtils.postTaskDelay(() -> {
//        mPresenter.getConversations();
//        }, 1000);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
//        unRegisterBR();
    }

//    private void registerBR() {
//        BroadcastManager.getInstance(getActivity()).register(AppConst.UPDATE_CONVERSATIONS, new BroadcastReceiver() {
//            @Override
//            public void onReceive(Context context, Intent intent) {
//                mPresenter.getConversations();
//                isFirst = false;
//            }
//        });
//    }

//    private void unRegisterBR() {
//        BroadcastManager.getInstance(getActivity()).unregister(AppConst.UPDATE_CONVERSATIONS);
//    }

    @Override
    protected RecentMessageFgPresenter createPresenter() {
        return new RecentMessageFgPresenter((MainActivity) getActivity());
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.fragment_recent_message;
    }

//    @Override
//    public LQRRecyclerView getRvRecentMessage() {
//        return null;
//    }
}
