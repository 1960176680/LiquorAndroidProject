package com.hz.tt.mvp.ui.activity;

import android.content.Intent;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hz.tt.R;
import com.hz.tt.mvp.model.entity.response.QueryResponseSingle;
import com.hz.tt.mvp.presenter.impl.QueryDetailAtPresenter;
import com.hz.tt.mvp.ui.common.BaseActivity;
import com.hz.tt.mvp.ui.view.IQueryDetailAtView;

import butterknife.Bind;

/**
 * Created by Administrator on 2018-05-29.
 */

public class QueryDetailActivity extends BaseActivity<IQueryDetailAtView,QueryDetailAtPresenter> {
    @Bind(R.id.tvOutperson)
    TextView tvOutperson;
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
    @Bind(R.id.tvRemark)
    TextView tvRemark;
    @Bind(R.id.ivImg)
    ImageView ivImg;


    @Override
    public void initView() {
        super.initView();
        setToolbarTitle("库存详情");
    }

    @Override
    public void initData() {
        Intent intent=getIntent();
        QueryResponseSingle item=intent.getParcelableExtra("bean");
        tvOutperson.setText(item.getIntoCustomer());
        tvRecTime.setText(item.getIntoDate());
        tv_type.setText(item.getCategory());
        tv_country.setText(item.getCountry());
        tv_birthplace.setText(item.getOrigin());
        tv_capacity.setText(item.getVolume());
        tv_year.setText(item.getProductiveYear());
        tv_num.setText(item.getCountNum());
        tv_position.setText(item.getPosition());
        tvRemark.setText(item.getRemark().equals("")?"无":item.getRemark());
        Glide.with(this)
                .load("http://121.43.167.170:8001/Wine/"+item.getPhoto())
                .centerCrop()
                .into(ivImg);
    }

    @Override
    protected QueryDetailAtPresenter createPresenter() {
        return new QueryDetailAtPresenter(this);
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_query_detail;
    }
}
