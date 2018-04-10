package com.hz.tt.mvp.ui.fragment;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.hz.tt.R;
import com.hz.tt.mvp.model.entity.cache.UserCache;
import com.hz.tt.mvp.presenter.impl.ContactsFgPresenter;
import com.hz.tt.mvp.ui.activity.ImgPreviewActivity;
import com.hz.tt.mvp.ui.activity.MainActivity;
import com.hz.tt.mvp.ui.activity.ScanActivity;
import com.hz.tt.mvp.ui.common.BaseFragment;
import com.hz.tt.mvp.ui.view.IContactsFgView;
import com.hz.tt.widget.MyListViewInScrollView;

import java.text.SimpleDateFormat;
import java.util.Date;

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


    @Bind(R.id.tvRecPerson)
    TextView tvRecPerson;
    @Bind(R.id.tvRecTime)
    TextView tvRecTime;
    @Bind(R.id.tvExtra)
    TextView tvExtra;
    @Bind(R.id.etNum)
    EditText etNum;
    @Bind(R.id.btnSwitch)
    Button btnSwitch;
    @Bind(R.id.ivImg)
    ImageView ivImg;
    @Bind(R.id.tvName)
    TextView tvName;
    @Bind(R.id.ratingBar)
    RatingBar ratingBar;

    private final int REQUEST_OUT=1;


    @Override
    public void initView(View rootView) {

    }

    @Override
    public void initData() {
        mPresenter.getConversations();
    }

    @Override
    public void initListener() {
        ivImg.setOnClickListener(v -> mPresenter.jumpActivity(ImgPreviewActivity.class));
        et_scan.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                mPresenter.query();
                return true;
            }
            return false;
        });



        iv_scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),ScanActivity.class);
                startActivityForResult(intent,REQUEST_OUT);
//                intent.putExtra("flag","out");
//                ((MainActivity) ContactsFragment.this.getActivity()).jumpToActivity(intent);
            }
        });
        btnSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text=btnSwitch.getText().toString();
                if (text.equals("出库")){
                    btnSwitch.setText("入库");
                    tvRecPerson.setText("入库员：");
                    et_rec_person.setText(UserCache.getPhone());
                    et_rec_person.setEnabled(false);
                    tvRecTime.setText("入库时间：");
                    tvExtra.setText("当前位置：");
                }else {
                    btnSwitch.setText("出库");
                    tvRecPerson.setText("接收人：");
                    et_rec_person.setEnabled(true);
                    et_rec_person.setText("");
                    tvRecTime.setText("接收时间：");
                    tvExtra.setText("备注：");
                }
            }
        });







        btn_add.setOnClickListener(v -> {
//            InBeanDao inBeanDao = MyApp.getInstances().getDaoSession().getInBeanDao();
//            List<InBean> list=inBeanDao.queryBuilder().where(InBeanDao.Properties.Code.eq(et_scan.getText().toString())).build().list();
//            if (list==null||list.size()==0){
//                UIUtils.showToast("无此商品");
//                return;
//            }


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
                etNum.setText("");
                ivImg.setImageResource(R.mipmap.ic_launcher);


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
        et_rec_person.setText("");
        et_scan.setText("");
        et_remark.setText("");
        tvName.setText("品名：");
        tv_type.setText("");
        tv_country.setText("");
        tv_birthplace.setText("");
        tv_capacity.setText("");
        tv_year.setText("");
        tv_num.setText("");
        tv_position.setText("");
        etNum.setText("");
        ivImg.setImageResource(R.mipmap.ic_launcher);
        ratingBar.setProgress(0);
        mPresenter.clearAllData();
    }


    @Override
    protected ContactsFgPresenter createPresenter() {
        return new ContactsFgPresenter((MainActivity) getActivity());
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==1&&resultCode==Activity.RESULT_OK){
            String result=data.getStringExtra("result");
            et_scan.setText(result);
            et_scan.setSelection(et_scan.getText().toString().trim().length());
//            查询该条数据网络值
            mPresenter.query();
        }




    }

    @Override
    protected int provideContentViewId() {
        return R.layout.fragment_out1;
    }

    @Override
    public void onResume() {
        super.onResume();
        SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        tv_rec_time.setText(dateFormat.format(new Date()));
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
    public Button getDoType() {
        return btnSwitch;
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
    public EditText getOutNum() {
        return etNum;
    }

    @Override
    public ImageView getImgV() {
        return ivImg;
    }

    @Override
    public TextView getName() {
        return tvName;
    }

    @Override
    public RatingBar getRatingBar() {
        return ratingBar;
    }

    @Override
    public MyListViewInScrollView getRvContacts() {
        rvRecentMessage.setNestedScrollingEnabled(false);
        return rvRecentMessage;
    }
}
