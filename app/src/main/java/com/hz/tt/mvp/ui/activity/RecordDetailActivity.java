package com.hz.tt.mvp.ui.activity;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.hz.tt.R;
import com.hz.tt.mvp.model.entity.response.QueryResponseSingle;
import com.hz.tt.mvp.presenter.impl.RecordDetailAtPresenter;
import com.hz.tt.mvp.ui.common.BaseActivity;
import com.hz.tt.mvp.ui.view.IRecordDetailAtView;
import com.zhy.autolayout.AutoLinearLayout;

import butterknife.Bind;

/**
 * Created by Administrator on 2018-02-27.
 */

public class RecordDetailActivity extends BaseActivity<IRecordDetailAtView,RecordDetailAtPresenter> implements IRecordDetailAtView{

    @Bind(R.id.tvOutpersonLable)
    TextView tvOutpersonLable;
    @Bind(R.id.tvRecPersonLable)
    TextView tvRecPersonLable;
    @Bind(R.id.tvRecTimeLable)
    TextView tvRecTimeLable;

    @Bind(R.id.tvOutperson)
    TextView tvOutperson;
    @Bind(R.id.tvRecPerson)
    TextView tvRecPerson;
    @Bind(R.id.tvRecTime)
    TextView tvRecTime;
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
    @Bind(R.id.tvUpTime)
    TextView tvUpTime;
    @Bind(R.id.ivImg)
    ImageView ivImg;
    @Bind(R.id.lineOut)
    AutoLinearLayout lineOut;

    private QueryResponseSingle outBean;

    @Override
    public void initView() {
        setToolbarTitle("记录详情");
        outBean=getIntent().getParcelableExtra("bean");
        if (outBean.getOperation().equals("入库")){
            lineOut.setVisibility(View.GONE);
//            tvOutpersonLable.setVisibility(View.GONE);
//            tvOutperson.setVisibility(View.GONE);
            tvRecPersonLable.setText("入库员：");
            tvRecTimeLable.setText("入库时间：");
            ivImg.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void initData() {
        if (outBean!=null){
            if (outBean.getOperation().equals("出库")){
                tvOutperson.setText(outBean.getOutCustomer());
            }
                tvRecPerson.setText(outBean.getReceiveCustomer());
                tvRecTime.setText(outBean.getIntoDate());
                tv_type.setText(outBean.getCategory());
                tv_country.setText(outBean.getCountry());
                tv_birthplace.setText(outBean.getOrigin());
                tv_capacity.setText(outBean.getVolume());
                tv_year.setText(outBean.getProductiveYear());
                tv_num.setText(outBean.getCountNum());
                tv_position.setText(outBean.getPosition());
                tvUpTime.setText(outBean.getIntoDate());
        }


    }

    @Override
    protected RecordDetailAtPresenter createPresenter() {
        return new RecordDetailAtPresenter(this);
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_record_detail;
    }

    @Override
    public TextView gettvOutpersonLable() {
        return tvOutpersonLable;
    }

    @Override
    public TextView gettvRecPersonLable() {
        return tvRecPersonLable;
    }

    @Override
    public TextView gettvRecTimeLable() {
        return tvRecTimeLable;
    }

    @Override
    public TextView tvOutperson() {
        return tvOutperson;
    }

    @Override
    public TextView tvRecPerson() {
        return tvRecPerson;
    }

    @Override
    public TextView tvRecTime() {
        return tvRecTime;
    }

    @Override
    public TextView tv_type() {
        return tv_type;
    }

    @Override
    public TextView tv_country() {
        return tv_country;
    }

    @Override
    public TextView tv_birthplace() {
        return tv_birthplace;
    }

    @Override
    public TextView tv_capacity() {
        return tv_capacity;
    }

    @Override
    public TextView tv_year() {
        return tv_year;
    }

    @Override
    public TextView tv_num() {
        return tv_num;
    }

    @Override
    public TextView tv_position() {
        return tv_position;
    }

    @Override
    public TextView tvUpTime() {
        return tvUpTime;
    }

    @Override
    public ImageView ivImg() {
        return ivImg;
    }
}
