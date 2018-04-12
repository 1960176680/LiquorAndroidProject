package com.hz.tt.mvp.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.hz.tt.R;
import com.hz.tt.mvp.presenter.impl.GoodsEvaluateAtPresenter;
import com.hz.tt.mvp.ui.common.BaseActivity;
import com.hz.tt.mvp.ui.view.IGoodsEvaluateAtView;
import com.lqr.recyclerview.LQRRecyclerView;

import butterknife.Bind;

/**
 * Created by Administrator on 2018-04-11.
 */

public class GoodsEvaluateActivity extends BaseActivity<IGoodsEvaluateAtView,GoodsEvaluateAtPresenter> implements IGoodsEvaluateAtView{
    @Bind(R.id.iv_scan)
    ImageView iv_scan;
    @Bind(R.id.et_scan)
    EditText et_scan;

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
    @Bind(R.id.ratingBar)
    RatingBar ratingBar;
    @Bind(R.id.rvRecycleView)
    LQRRecyclerView rvRecycleView;

    private final int REQUEST_OUT=1;
    @Override
    public void initView() {
        setToolbarTitle("商品评价查询");
    }

    @Override
    public void initListener() {
        et_scan.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                mPresenter.query();
//                mPresenter.queryComment();
                return true;
            }
            return false;
        });



        iv_scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(GoodsEvaluateActivity.this,ScanActivity.class);
                startActivityForResult(intent,REQUEST_OUT);
//                intent.putExtra("flag","out");
//                ((MainActivity) ContactsFragment.this.getActivity()).jumpToActivity(intent);
            }
        });
    }

    @Override
    public void initData() {
        mPresenter.getConversations();
    }

    @Override
    protected GoodsEvaluateAtPresenter createPresenter() {
        return new GoodsEvaluateAtPresenter(this);
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_goodsevaluate;
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==1&&resultCode== Activity.RESULT_OK){
            String result=data.getStringExtra("result");
            et_scan.setText(result);
            et_scan.setSelection(et_scan.getText().toString().trim().length());
//            查询该条数据网络值
            mPresenter.query();
//            mPresenter.queryComment();
        }




    }
    @Override
    public EditText getCode() {
        return et_scan;
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
    public RatingBar getRatingBar() {
        return ratingBar;
    }

    @Override
    public LQRRecyclerView getRecycleView() {
        return rvRecycleView;
    }
}
