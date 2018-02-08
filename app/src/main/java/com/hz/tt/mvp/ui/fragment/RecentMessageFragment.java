package com.hz.tt.mvp.ui.fragment;

import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.hz.tt.R;
import com.hz.tt.mvp.presenter.impl.RecentMessageFgPresenter;
import com.hz.tt.mvp.ui.activity.MainActivity;
import com.hz.tt.mvp.ui.common.BaseFragment;
import com.hz.tt.mvp.ui.view.IRecentMessageFgView;
import com.hz.tt.widget.SelectDialog;

import butterknife.Bind;

/**
 * @描述 最近会话列表界面
 */
public class RecentMessageFragment extends BaseFragment<IRecentMessageFgView, RecentMessageFgPresenter> implements IRecentMessageFgView {

    private boolean isFirst = true;
//    @Bind(R.id.rvRecentMessage)
//    LQRRecyclerView mRvRecentMessage;
    @Bind(R.id.iv_type)
    ImageView iv_type;
    @Bind(R.id.iv_country)
    ImageView iv_country;
    @Bind(R.id.iv_birthplace)
    ImageView iv_birthplace;
    @Bind(R.id.iv_capacity)
    ImageView iv_capacity;

    @Bind(R.id.et_type)
    EditText et_type;
    @Bind(R.id.et_country)
    EditText et_country;
    @Bind(R.id.et_birthplace)
    EditText et_birthplace;
    @Bind(R.id.et_capacity)
    EditText et_capacity;
    @Override
    public void init() {
//        registerBR();
    }

    @Override
    public void initView(View rootView) {
        iv_type.setOnClickListener(v -> selectDialog("type"));
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

    public void selectDialog(String type){
        switch (type){
            case "type":
                SelectDialog.setOnResultListener(result -> et_type.setText(result));
                SelectDialog.initDialog(getActivity(),"选择品类","",R.array.CXH_list,0)
                .show();
                break;
            case "country":
                SelectDialog.setOnResultListener(result -> et_country.setText(result));
                SelectDialog.initDialog(getActivity(),"选择国家","",R.array.CXH_list,0)
                        .show();
                break;
            case "birthplace":
                SelectDialog.setOnResultListener(result -> et_birthplace.setText(result));
                SelectDialog.initDialog(getActivity(),"选择产地","",R.array.CXH_list,0)
                        .show();
                break;
            case "capacity":
                SelectDialog.setOnResultListener(result -> et_capacity.setText(result));
                SelectDialog.initDialog(getActivity(),"选择容量","",R.array.CXH_list,0)
                        .show();
                break;
        }

    }
}
