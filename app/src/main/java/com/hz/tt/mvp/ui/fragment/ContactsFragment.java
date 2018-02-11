package com.hz.tt.mvp.ui.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.hz.tt.R;
import com.hz.tt.api.okHttpUtils.NetConstant;
import com.hz.tt.app.MyApp;
import com.hz.tt.greendao.gen.InBeanDao;
import com.hz.tt.mvp.model.entity.InBean;
import com.hz.tt.mvp.presenter.impl.ContactsFgPresenter;
import com.hz.tt.mvp.ui.activity.MainActivity;
import com.hz.tt.mvp.ui.activity.ScanActivity;
import com.hz.tt.mvp.ui.common.BaseFragment;
import com.hz.tt.mvp.ui.view.IContactsFgView;

import java.util.List;

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
    @Bind(R.id.et_scan)
    EditText et_scan;




    @Bind(R.id.et_rec_person)
    EditText et_rec_person;
    @Bind(R.id.tv_rec_time)
    TextView tv_rec_time;
    @Bind(R.id.et_remark)
    EditText et_remark;
    @Bind(R.id.tv_type)
    TextView tv_type;
    @Bind(R.id.tv_country)
    TextView tv_country;
    @Bind(R.id.tv_birthplace)
    TextView tv_birthplace;
    @Bind(R.id.tv_capacity)
    TextView tv_capacity;
    @Bind(R.id.tv_year)
    TextView tv_year;
    @Bind(R.id.tv_num)
    TextView tv_num;
    @Bind(R.id.tv_position)
    TextView tv_position;






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
        iv_scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),ScanActivity.class);
                intent.putExtra("flag","out");
                ((MainActivity) ContactsFragment.this.getActivity()).jumpToActivity(intent);
            }
        });
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

    @Override
    public void onResume() {
        super.onResume();
        if (!NetConstant.SCAN_RESULT_OUT.equals("")){
            et_scan.setText(NetConstant.SCAN_RESULT_OUT);
            InBeanDao inBeanDao = MyApp.getInstances().getDaoSession().getInBeanDao();
            List<InBean> list=inBeanDao.queryBuilder().where(InBeanDao.Properties.Code.eq(NetConstant.SCAN_RESULT_OUT.trim())).build().list();
            InBean inBean=list.get(0);
            String type=inBean.getType();
            String Country=inBean.getCountry();
            String Birthday=inBean.getBirthday();
            String Capacity=inBean.getCapacity();
            String Year=inBean.getYear();
            String Number=inBean.getNumber();
            String Location=inBean.getLocation();
            tv_type.setText(type);
            tv_country.setText(Country);
            tv_birthplace.setText(Birthday);
            tv_capacity.setText(Capacity);
            tv_year.setText(Year);
            tv_num.setText(Number);
            tv_position.setText(Location);
        }
    }
}
