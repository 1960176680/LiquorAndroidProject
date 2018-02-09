package com.hz.tt.mvp.ui.fragment;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.hz.tt.R;
import com.hz.tt.mvp.presenter.impl.ContactsFgPresenter;
import com.hz.tt.mvp.ui.activity.MainActivity;
import com.hz.tt.mvp.ui.activity.ScanActivity;
import com.hz.tt.mvp.ui.common.BaseFragment;
import com.hz.tt.mvp.ui.view.IContactsFgView;

import butterknife.Bind;

/**
 * @创建者 CSDN_LQR
 * @描述 通讯录界面
 */
public class ContactsFragment extends BaseFragment<IContactsFgView, ContactsFgPresenter> implements IContactsFgView {

//    @Bind(R.id.rvContacts)
//    LQRRecyclerView mRvContacts;
//    @Bind(R.id.qib)
//    QuickIndexBar mQib;
//    @Bind(R.id.tvLetter)
//    TextView mTvLetter;
    @Bind(R.id.iv_scan)
    ImageView iv_scan;
    private View mHeaderView;
    private TextView mFooterView;
    private TextView mTvNewFriendUnread;
    @Override
    public void initView(View rootView) {



//        mHeaderView = View.inflate(getActivity(), R.layout.header_rv_contacts, null);
//        mTvNewFriendUnread = (TextView) mHeaderView.findViewById(R.id.tvNewFriendUnread);
//        mFooterView = new TextView(getContext());
//        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, UIUtils.dip2Px(50));
//        mFooterView.setLayoutParams(params);
//        mFooterView.setGravity(Gravity.CENTER);
//
//        registerBR();
    }

    @Override
    public void initData() {
//        mPresenter.loadContacts();
    }

    @Override
    public void initListener() {
        iv_scan.setOnClickListener(v -> ((MainActivity) getActivity()).jumpToActivity(ScanActivity.class));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
//        unregisterBR();
    }

    private void registerBR() {

    }

    private void unregisterBR() {

    }

    private void showLetter(String letter) {

    }

    private void hideLetter() {

    }

    /**
     * 是否显示快速导航条
     *
     * @param show
     */
    public void showQuickIndexBar(boolean show) {

    }

    @Override
    protected ContactsFgPresenter createPresenter() {
        return new ContactsFgPresenter((MainActivity) getActivity());
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.fragment_out;
    }

//    @Override
//    public View getHeaderView() {
//        return mHeaderView;
//    }
//
//    @Override
//    public LQRRecyclerView getRvContacts() {
//        return null;
//    }
//
//    @Override
//    public TextView getFooterView() {
//        return mFooterView;
//    }
}
