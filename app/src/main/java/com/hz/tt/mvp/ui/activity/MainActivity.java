package com.hz.tt.mvp.ui.activity;

import android.graphics.Color;
import android.support.v4.view.ViewPager;
import android.widget.TextView;

import com.hz.tt.R;
import com.hz.tt.mvp.presenter.impl.MainAtPresenter;
import com.hz.tt.mvp.ui.common.BaseActivity;
import com.hz.tt.mvp.ui.common.BaseFragment;
import com.hz.tt.mvp.ui.fragment.FragmentFactory;
import com.hz.tt.mvp.ui.view.IMainAtView;
import com.hz.tt.util.UIUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

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
    protected MainAtPresenter createPresenter() {
        return new MainAtPresenter(this);
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_main;
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
    public TextView getTvMessageCount() {
        return null;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
