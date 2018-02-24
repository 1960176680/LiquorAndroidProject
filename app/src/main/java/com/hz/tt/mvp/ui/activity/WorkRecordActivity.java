package com.hz.tt.mvp.ui.activity;

import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hz.tt.R;
import com.hz.tt.mvp.presenter.impl.WorkRecordAtPresent;
import com.hz.tt.mvp.ui.adapter.CommonFragmentPagerAdapter;
import com.hz.tt.mvp.ui.common.BaseActivity;
import com.hz.tt.mvp.ui.common.BaseFragment;
import com.hz.tt.mvp.ui.fragment.FragmentFactory;
import com.hz.tt.mvp.ui.view.IWorkRecordAtView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * Created by ZhouWenGuang
 */

public class WorkRecordActivity extends BaseActivity<IWorkRecordAtView,WorkRecordAtPresent> implements IWorkRecordAtView,ViewPager.OnPageChangeListener{
    @Bind(R.id.vp_record)
    ViewPager vp_record;
    @Bind(R.id.tv_unupload)
    TextView tv_unupload;
    @Bind(R.id.tv_upload)
    TextView tv_upload;
    @Bind(R.id.view_line)
    View view_line;

    private List<BaseFragment> fragmentList;
    private int outMetrics;
    @Override
    public void initView() {
        setToolbarTitle("工作记录");
        initLine();
        vp_record.setOffscreenPageLimit(1);
    }

    @Override
    public void initListener() {
        tv_unupload.setOnClickListener(v -> topBtnClick(v));
        tv_upload.setOnClickListener(v -> topBtnClick(v));
        vp_record.addOnPageChangeListener(this);



    }

    @Override
    public void initData() {
        fragmentList=new ArrayList<>();
        fragmentList.add(FragmentFactory.getInstance().getUnUpLoadFragment());
        fragmentList.add(FragmentFactory.getInstance().getUpLoadFragment());
        vp_record.setAdapter(new CommonFragmentPagerAdapter(getSupportFragmentManager(),fragmentList));



    }

    @Override
    protected WorkRecordAtPresent createPresenter() {
        return new WorkRecordAtPresent(this);
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_workrecord;
    }

    public void topBtnClick(View view){
        switch (view.getId()) {
            case R.id.tv_unupload:
                vp_record.setCurrentItem(0,false);

                break;
            case R.id.tv_upload:
                vp_record.setCurrentItem(1,false);
                break;
        }

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        LinearLayout.LayoutParams params= ((LinearLayout.LayoutParams) view_line.getLayoutParams());
        params.leftMargin = (int) (positionOffset * outMetrics / 2 + position * outMetrics / 2);
        view_line.setLayoutParams(params);
    }

    @Override
    public void onPageSelected(int position) {
        if (position==0){
            tv_unupload.setEnabled(false);
            tv_upload.setEnabled(true);
//            unUpLoadDataFragment.initFragmentData();
        }else if (position==1){
            tv_upload.setEnabled(false);
            tv_unupload.setEnabled(true);
//            upLoadDataFragment.initFragmentData();
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }


    private void initLine() {
        outMetrics = getWindowManager().getDefaultDisplay().getWidth();
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) view_line.getLayoutParams();
        params.width = outMetrics / 2;
        view_line.setLayoutParams(params);
    }
}
