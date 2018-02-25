package com.hz.tt.mvp.ui.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
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
import com.hz.tt.util.UIUtils;
import com.hz.tt.widget.MyListViewInScrollView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import butterknife.Bind;

/**
 * @描述
 */
public class ContactsFragment extends BaseFragment<IContactsFgView, ContactsFgPresenter> implements IContactsFgView {

    private boolean addRecordOk;
    @Bind(R.id.iv_scan)
    ImageView iv_scan;
    @Bind(R.id.et_scan)
    EditText et_scan;

    @Bind(R.id.btn_add)
    Button btn_add;
    @Bind(R.id.btn_upload)
    Button btn_upload;



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

    @Bind(R.id.rvRecentMessage)
    MyListViewInScrollView rvRecentMessage;

    @Override
    public void initView(View rootView) {

    }

    @Override
    public void initData() {
        mPresenter.getConversations();
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


        btn_add.setOnClickListener(v -> {
            InBeanDao inBeanDao = MyApp.getInstances().getDaoSession().getInBeanDao();
            List<InBean> list=inBeanDao.queryBuilder().where(InBeanDao.Properties.Code.eq(et_scan.getText().toString())).build().list();
            if (list==null||list.size()==0){
                UIUtils.showToast("无此商品");
                return;
            }

            addRecordOk=mPresenter.addRecord();
            if (addRecordOk){
//                    清字段
                et_rec_person.setText("");
                et_scan.setText("");
                et_remark.setText("");
                tv_type.setText("");
                tv_country.setText("");
                tv_birthplace.setText("");
                tv_capacity.setText("");
                tv_year.setText("");
                tv_num.setText("");
                tv_position.setText("");

            }
        });
        btn_upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.clearYesUp();
                mPresenter.upRecord();
            }
        });


    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }
    public void clearAllData(){
        mPresenter.clearAllData();
    }


    @Override
    protected ContactsFgPresenter createPresenter() {
        return new ContactsFgPresenter((MainActivity) getActivity());
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.fragment_out;
    }

    @Override
    public void onResume() {
        super.onResume();
        SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        tv_rec_time.setText(dateFormat.format(new Date()));
        if (!NetConstant.SCAN_RESULT_OUT.equals("")){
            et_scan.setText(NetConstant.SCAN_RESULT_OUT);
            InBeanDao inBeanDao = MyApp.getInstances().getDaoSession().getInBeanDao();
            List<InBean> list=inBeanDao.queryBuilder().where(InBeanDao.Properties.Code.eq(NetConstant.SCAN_RESULT_OUT.trim())).build().list();
            if (list==null||list.size()==0){
                return;
            }
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

    @Override
    public EditText getRecPerson() {
        return et_rec_person;
    }

    @Override
    public TextView getRecDate() {
        return tv_rec_time;
    }

    @Override
    public EditText getCode() {
        return et_scan;
    }

    @Override
    public EditText getRemak() {
        return et_remark;
    }

    @Override
    public TextView getType() {
        return tv_type;
    }

    @Override
    public TextView getCountry() {
        return tv_country;
    }

    @Override
    public TextView getBirthday() {
        return tv_birthplace;
    }

    @Override
    public TextView getCapacity() {
        return tv_capacity;
    }

    @Override
    public TextView getYear() {
        return tv_year;
    }

    @Override
    public TextView getNum() {
        return tv_num;
    }

    @Override
    public TextView getPosition() {
        return tv_position;
    }

    @Override
    public MyListViewInScrollView getRvContacts() {
        rvRecentMessage.setNestedScrollingEnabled(false);
        return rvRecentMessage;
    }
}
