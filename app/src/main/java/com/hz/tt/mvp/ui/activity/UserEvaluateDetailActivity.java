package com.hz.tt.mvp.ui.activity;

import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.hz.tt.R;
import com.hz.tt.mvp.presenter.impl.UserEvaluateDetailAtPresenter;
import com.hz.tt.mvp.ui.common.BaseActivity;
import com.hz.tt.mvp.ui.view.IUserEvaluateDetailAtView;

import butterknife.Bind;

/**
 * Created by Administrator on 2018-04-17.
 */

public class UserEvaluateDetailActivity extends BaseActivity<IUserEvaluateDetailAtView,UserEvaluateDetailAtPresenter> implements IUserEvaluateDetailAtView{
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
    @Bind(R.id.ivImg)
    ImageView ivImg;
    @Bind(R.id.tvName)
    TextView tvName;
    @Bind(R.id.tvCode)
    TextView tvCode;
    @Bind(R.id.ratingBar)
    RatingBar ratingBar;


    @Override
    public void initData() {
        setToolbarTitle("评价详情");
        String recordCode=getIntent().getStringExtra("code");
        mPresenter.query(recordCode);
    }

    @Override
    protected UserEvaluateDetailAtPresenter createPresenter() {
        return new UserEvaluateDetailAtPresenter(this);
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_userevaluatedetail;
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
    public ImageView getImgV() {
        return ivImg;
    }

    @Override
    public TextView getName() {
        return tvName;
    }

    @Override
    public TextView getCode() {
        return tvCode;
    }

    @Override
    public RatingBar getRatingBar() {
        return ratingBar;
    }
}
