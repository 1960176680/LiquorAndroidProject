package com.hz.tt.mvp.ui.activity;

import android.graphics.Color;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hz.tt.R;
import com.hz.tt.app.MyApp;
import com.hz.tt.mvp.presenter.impl.MainAtPresenter;
import com.hz.tt.mvp.ui.adapter.CommonFragmentPagerAdapter;
import com.hz.tt.mvp.ui.common.BaseActivity;
import com.hz.tt.mvp.ui.common.BaseFragment;
import com.hz.tt.mvp.ui.fragment.FragmentFactory;
import com.hz.tt.mvp.ui.view.IMainAtView;
import com.hz.tt.util.UIUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.Bind;

import static com.hz.tt.R.id.tv_time;

/**
 * Created by Administrator on 2018-02-06.
 */

public class MainActivity extends BaseActivity<IMainAtView,MainAtPresenter> implements ViewPager.OnPageChangeListener,IMainAtView{
    @Bind(R.id.vpContent)
    ViewPager mVpContent;
    @Bind(R.id.tvMessageNormal)
    TextView tvMessageNormal;
    @Bind(R.id.tvContactsNormal)
    TextView tvContactsNormal;
    @Bind(R.id.tvDiscoveryNormal)
    TextView tvDiscoveryNormal;
    @Bind(R.id.tvMeNormal)
    TextView tvMeNormal;
    @Bind(R.id.tvMessagePress)
    TextView tvMessagePress;
    @Bind(R.id.tvContactsPress)
    TextView tvContactsPress;
    @Bind(R.id.tvDiscoveryPress)
    TextView tvDiscoveryPress;
    @Bind(R.id.tvMePress)
    TextView tvMePress;
    @Bind(R.id.tvMessageTextNormal)
    TextView tvMessageTextNormal;
    @Bind(R.id.tvContactsTextNormal)
    TextView tvContactsTextNormal;
    @Bind(R.id.tvDiscoveryTextNormal)
    TextView tvDiscoveryTextNormal;
    @Bind(R.id.tvMessageTextPress)
    TextView tvMessageTextPress;
    @Bind(R.id.tvContactsTextPress)
    TextView tvContactsTextPress;
    @Bind(R.id.tvDiscoveryTextPress)
    TextView tvDiscoveryTextPress;
    @Bind(R.id.tvMeTextPress)
    TextView tvMeTextPress;
    @Bind(R.id.tvMeTextNormal)
    TextView tvMeTextNormal;

    @Bind(R.id.llMessage)
    LinearLayout mLlMessage;
    @Bind(R.id.llContacts)
    LinearLayout mLlContacts;
    @Bind(R.id.llDiscovery)
    LinearLayout mLlDiscovery;
    @Bind(R.id.llMe)
    LinearLayout mLlMe;
    private List<BaseFragment> mFragmentList;

    @Override
    public void initView() {
        setToolbarTitle(UIUtils.getString(R.string.tv_title_in));
        //默认选中第一个
        setTransparency();
        tvMessagePress.getBackground().setAlpha(255);
        tvMessageTextPress.setTextColor(Color.argb(255, 69, 192, 26));
        //设置ViewPager的最大缓存页面
        mVpContent.setOffscreenPageLimit(3);

    }

    @Override
    public void initData() {
        mFragmentList = new ArrayList<>(4);
        mFragmentList.add(FragmentFactory.getInstance().getRecentMessageFragment());
        mFragmentList.add(FragmentFactory.getInstance().getContactsFragment());
        mFragmentList.add(FragmentFactory.getInstance().getDiscoveryFragment());
        mFragmentList.add(FragmentFactory.getInstance().getMeFragment());
        mVpContent.setAdapter(new CommonFragmentPagerAdapter(getSupportFragmentManager(), mFragmentList));
    }

    @Override
    public void initListener() {
        mLlMessage.setOnClickListener(v -> bottomBtnClick(v));
        mLlContacts.setOnClickListener(v -> bottomBtnClick(v));
        mLlDiscovery.setOnClickListener(v -> bottomBtnClick(v));
        mLlMe.setOnClickListener(v -> bottomBtnClick(v));
        mVpContent.addOnPageChangeListener(this);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
//        unRegisterBR();
    }
    public void bottomBtnClick(View view) {
        setTransparency();
        switch (view.getId()) {
            case R.id.llMessage:
                setToolbarTitle(UIUtils.getString(R.string.tv_title_in));
                mVpContent.setCurrentItem(0, false);
                tvMessagePress.getBackground().setAlpha(255);
                tvMessageTextPress.setTextColor(Color.argb(255, 69, 192, 26));
                break;
            case R.id.llContacts:
                setToolbarTitle(UIUtils.getString(R.string.tv_title_out));
                mVpContent.setCurrentItem(1, false);
                tvContactsPress.getBackground().setAlpha(255);
                tvContactsTextPress.setTextColor(Color.argb(255, 69, 192, 26));
                break;
            case R.id.llDiscovery:
                setToolbarTitle(UIUtils.getString(R.string.tv_title_query));
                mVpContent.setCurrentItem(2, false);
                tvDiscoveryPress.getBackground().setAlpha(255);
                tvDiscoveryTextPress.setTextColor(Color.argb(255, 69, 192, 26));
                break;
            case R.id.llMe:
                setToolbarTitle(UIUtils.getString(R.string.tv_title_more));
                mVpContent.setCurrentItem(3, false);
                tvMePress.getBackground().setAlpha(255);
                tvMeTextPress.setTextColor(Color.argb(255, 69, 192, 26));
                break;
        }
    }
    /**
     * 把press图片、文字全部隐藏(设置透明度)
     */
    private void setTransparency() {
        tvMessageNormal.getBackground().setAlpha(255);
        tvContactsNormal.getBackground().setAlpha(255);
        tvDiscoveryNormal.getBackground().setAlpha(255);
        tvMeNormal.getBackground().setAlpha(255);
        tvMessagePress.getBackground().setAlpha(1);
        tvContactsPress.getBackground().setAlpha(1);
        tvDiscoveryPress.getBackground().setAlpha(1);
        tvMePress.getBackground().setAlpha(1);
        tvMessageTextNormal.setTextColor(Color.argb(255, 153, 153, 153));
        tvContactsTextNormal.setTextColor(Color.argb(255, 153, 153, 153));
        tvDiscoveryTextNormal.setTextColor(Color.argb(255, 153, 153, 153));
        tvMeTextNormal.setTextColor(Color.argb(255, 153, 153, 153));
        tvMessageTextPress.setTextColor(Color.argb(0, 69, 192, 26));
        tvContactsTextPress.setTextColor(Color.argb(0, 69, 192, 26));
        tvDiscoveryTextPress.setTextColor(Color.argb(0, 69, 192, 26));
        tvMeTextPress.setTextColor(Color.argb(0, 69, 192, 26));
    }

    @Override
    protected MainAtPresenter createPresenter() {
        return new MainAtPresenter(this);
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_main;
    }
    @Override
    protected boolean isToolbarCanBack() {
        return false;
    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        //根据ViewPager滑动位置更改透明度
        int diaphaneity_one = (int) (255 * positionOffset);
        int diaphaneity_two = (int) (255 * (1 - positionOffset));
        SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        switch (position) {
            case 0:
                ((TextView) mFragmentList.get(0).getView().findViewById(R.id.tv_time)).setText(dateFormat.format(new Date()));
                setToolbarTitle(UIUtils.getString(R.string.tv_title_in));
                tvMessageNormal.getBackground().setAlpha(diaphaneity_one);
                tvMessagePress.getBackground().setAlpha(diaphaneity_two);
                tvContactsNormal.getBackground().setAlpha(diaphaneity_two);
                tvContactsPress.getBackground().setAlpha(diaphaneity_one);
                tvMessageTextNormal.setTextColor(Color.argb(diaphaneity_one, 153, 153, 153));
                tvMessageTextPress.setTextColor(Color.argb(diaphaneity_two, 69, 192, 26));
                tvContactsTextNormal.setTextColor(Color.argb(diaphaneity_two, 153, 153, 153));
                tvContactsTextPress.setTextColor(Color.argb(diaphaneity_one, 69, 192, 26));
                break;
            case 1:
                ((TextView) mFragmentList.get(1).getView().findViewById(R.id.tv_rec_time)).setText(dateFormat.format(new Date()));
                setToolbarTitle(UIUtils.getString(R.string.tv_title_out));
                tvContactsNormal.getBackground().setAlpha(diaphaneity_one);
                tvContactsPress.getBackground().setAlpha(diaphaneity_two);
                tvDiscoveryNormal.getBackground().setAlpha(diaphaneity_two);
                tvDiscoveryPress.getBackground().setAlpha(diaphaneity_one);
                tvContactsTextNormal.setTextColor(Color.argb(diaphaneity_one, 153, 153, 153));
                tvContactsTextPress.setTextColor(Color.argb(diaphaneity_two, 69, 192, 26));
                tvDiscoveryTextNormal.setTextColor(Color.argb(diaphaneity_two, 153, 153, 153));
                tvDiscoveryTextPress.setTextColor(Color.argb(diaphaneity_one, 69, 192, 26));
                break;
            case 2:
                setToolbarTitle(UIUtils.getString(R.string.tv_title_query));
                tvDiscoveryNormal.getBackground().setAlpha(diaphaneity_one);
                tvDiscoveryPress.getBackground().setAlpha(diaphaneity_two);
                tvMeNormal.getBackground().setAlpha(diaphaneity_two);
                tvMePress.getBackground().setAlpha(diaphaneity_one);
                tvDiscoveryTextNormal.setTextColor(Color.argb(diaphaneity_one, 153, 153, 153));
                tvDiscoveryTextPress.setTextColor(Color.argb(diaphaneity_two, 69, 192, 26));
                tvMeTextNormal.setTextColor(Color.argb(diaphaneity_two, 153, 153, 153));
                tvMeTextPress.setTextColor(Color.argb(diaphaneity_one, 69, 192, 26));
                break;
            case 3:
                setToolbarTitle(UIUtils.getString(R.string.tv_title_more));
                break;
        }
    }

    @Override
    public void onPageSelected(int position) {
        if (position == 1) {
            //如果是“通讯录”页被选中，则显示快速导航条
            FragmentFactory.getInstance().getContactsFragment().showQuickIndexBar(true);
        } else {
            FragmentFactory.getInstance().getContactsFragment().showQuickIndexBar(false);
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {
        if (state != ViewPager.SCROLL_STATE_IDLE) {
            //滚动过程中隐藏快速导航条
            FragmentFactory.getInstance().getContactsFragment().showQuickIndexBar(false);
        } else {
            FragmentFactory.getInstance().getContactsFragment().showQuickIndexBar(true);
        }
    }


    @Override
    public TextView getTvMessageCount() {
        return null;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        MyApp.exit();
    }
}
