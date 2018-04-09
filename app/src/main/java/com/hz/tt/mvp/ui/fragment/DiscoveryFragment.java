package com.hz.tt.mvp.ui.fragment;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.hz.tt.R;
import com.hz.tt.mvp.presenter.impl.DiscoveryFgPresenter;
import com.hz.tt.mvp.ui.activity.MainActivity;
import com.hz.tt.mvp.ui.common.BaseFragment;
import com.hz.tt.mvp.ui.view.IDiscoveryFgView;
import com.hz.tt.util.UIUtils;
import com.hz.tt.util.excel.MyUtils;
import com.hz.tt.widget.MyListViewInScrollView;
import com.zhy.autolayout.AutoLinearLayout;

import java.io.File;

import butterknife.Bind;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

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
    @Bind(R.id.btnExport)
    Button btnExport;
    @Bind(R.id.btnOpen)
    Button btnOpen;
    @Bind(R.id.et_name)
    EditText et_name;
    private boolean isVisiable=true;
    private Disposable disposable;

    @Override
    public void initView(View rootView) {
        mPresenter.setAdapter();

     }

    @Override
    public void initListener() {
        iv_pull.setOnClickListener(v -> {
            if (isVisiable){
                isVisiable=false;
                line_more.setVisibility(View.VISIBLE);
            }else{
                isVisiable=true;
                line_more.setVisibility(View.GONE);
            }
        });

        btn_query.setOnClickListener(v -> mPresenter.query());

        btnExport.setOnClickListener(v -> {
            ((MainActivity) getActivity()).showWaitingDialog(UIUtils.getString(R.string.please_wait));
            disposable = Observable.just(mPresenter.exportToExcel())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(aBoolean -> {
                        if (aBoolean){
                            mPresenter.showTipDialog();
                            UIUtils.showToast("导出数据成功！");
                            ((MainActivity) getActivity()).hideWaitingDialog();
                            disposable.dispose();
                        }else{
                            ((MainActivity) getActivity()).hideWaitingDialog();
                            UIUtils.showToast("无数据导出！");
                        }

                    });
        });


        btnOpen.setOnClickListener(v -> {
            if (MyUtils.FILE_PATH!=null){
                try {
//                    MyUtils.openAssignFolder(getActivity(),MyUtils.FILE_PATH);
                    File file=new File(MyUtils.FILE_PATH);
                    MyUtils.startActionFile(getActivity(),file,"application/vnd.ms-excel");
                } catch (Exception e) {
                    Toast toast = Toast.makeText(getActivity(), "没有找到打开该文件的应用程序，请下载安装WPS等软件！", Toast.LENGTH_SHORT);
                    toast.show();
                    e.printStackTrace();
                }
            }
        });


    }


    public void clearAllData(){
        et_country.setText("");
        et_birthplace.setText("");
        et_type.setText("");
        et_name.setText("");
        et_capacity.setText("");
        et_year.setText("");
        mPresenter.clearAllData();
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
    public EditText getName() {
        return et_name;
    }

    @Override
    public MyListViewInScrollView getRvRecyclerView() {
        listview.setNestedScrollingEnabled(false);
        return listview;
    }


}
