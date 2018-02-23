package com.hz.tt.mvp.ui.fragment;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.hz.tt.R;
import com.hz.tt.mvp.presenter.impl.DiscoveryFgPresenter;
import com.hz.tt.mvp.ui.activity.MainActivity;
import com.hz.tt.mvp.ui.common.BaseFragment;
import com.hz.tt.mvp.ui.view.IDiscoveryFgView;
import com.hz.tt.widget.MyListViewInScrollView;
import com.zhy.autolayout.AutoLinearLayout;

import butterknife.Bind;

/**
 * @描述 发现界面
 */
public class DiscoveryFragment extends BaseFragment<IDiscoveryFgView, DiscoveryFgPresenter> implements IDiscoveryFgView{

//    @Bind(R.id.iv_scan)
//    ImageView iv_scan;
//    @Bind(R.id.et_scan)
//    EditText et_scan;
//    @Bind(R.id.iv_query)
//    ImageView iv_query;

    @Bind(R.id.listview)
    MyListViewInScrollView listview;
    @Bind(R.id.iv_pull)
    ImageView iv_pull;
    @Bind(R.id.line_more)
    AutoLinearLayout line_more;

    @Bind(R.id.et_country)
    EditText et_country;
    @Bind(R.id.et_birthplace)
    EditText et_birthplace;
    @Bind(R.id.et_type)
    EditText et_type;
    @Bind(R.id.et_capacity)
    EditText et_capacity;
    @Bind(R.id.et_year)
    EditText et_year;
    @Bind(R.id.btn_query)
    Button btn_query;



    private boolean isVisiable=true;


    @Override
    public void initView(View rootView) {
        mPresenter.setAdapter();

     }

    @Override
    public void initListener() {
        iv_pull.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isVisiable){
                    isVisiable=false;
                    line_more.setVisibility(View.VISIBLE);
                }else{
                    isVisiable=true;
                    line_more.setVisibility(View.GONE);
                }
            }
        });

        btn_query.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.query();
            }
        });

//        Intent intent=new Intent(this.getActivity(),ScanActivity.class);
//        intent.putExtra("flag","query");
//        iv_scan.setOnClickListener(v -> ((MainActivity) getActivity()).jumpToActivity(intent));
//        iv_query.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mPresenter.query();
//            }
//        });


//        mOivShop.setOnClickListener(v -> ((MainActivity) getActivity()).jumpToWebViewActivity(AppConst.WeChatUrl.JD));
//        mOivGame.setOnClickListener(v -> ((MainActivity) getActivity()).jumpToWebViewActivity(AppConst.WeChatUrl.GAME));
    }

    @Override
    protected DiscoveryFgPresenter createPresenter() {
        return new DiscoveryFgPresenter((MainActivity) getActivity());
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.fragment_query;
    }

    @Override
    public void onResume() {
        super.onResume();
//        if (!NetConstant.SCAN_RESULT_QUERY.equals("")){
////            et_scan.setText(NetConstant.SCAN_RESULT_QUERY);
//        }
    }




    @Override
    public EditText getEtCountryV() {
        return et_country;
    }

    @Override
    public EditText getEtBirthplaceV() {
        return et_birthplace;
    }

    @Override
    public EditText getEtTypeV() {
        return et_type;
    }

    @Override
    public EditText getEtCapacityV() {
        return et_capacity;
    }

    @Override
    public EditText getEtYearV() {
        return et_year;
    }

    @Override
    public MyListViewInScrollView getRvRecyclerView() {
        listview.setNestedScrollingEnabled(false);
        return listview;
    }


}
