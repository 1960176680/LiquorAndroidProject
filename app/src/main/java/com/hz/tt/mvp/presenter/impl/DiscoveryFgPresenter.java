package com.hz.tt.mvp.presenter.impl;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.hz.tt.R;
import com.hz.tt.api.okHttpUtils.OkHttpUtils;
import com.hz.tt.mvp.model.entity.QueryBean;
import com.hz.tt.mvp.model.entity.request.QueryRequest;
import com.hz.tt.mvp.model.entity.response.QueryResponse;
import com.hz.tt.mvp.model.entity.response.QueryResponseSingle;
import com.hz.tt.mvp.presenter.base.BasePresenter;
import com.hz.tt.mvp.ui.common.BaseActivity;
import com.hz.tt.mvp.ui.view.IDiscoveryFgView;
import com.hz.tt.util.UIUtils;
import com.hz.tt.util.excel.WriteExcelUtils;
import com.lqr.adapter.LQRAdapterForRecyclerView;
import com.lqr.adapter.LQRViewHolderForRecyclerView;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class DiscoveryFgPresenter extends BasePresenter<IDiscoveryFgView> {
    private List<QueryResponseSingle> datas= new ArrayList<>();
    private LQRAdapterForRecyclerView<QueryResponseSingle> mAdapter;
    public DiscoveryFgPresenter(BaseActivity context) {
        super(context);
    }

    /**
     * 清空界面列表
     */
    public void clearAllData(){
        datas.clear();
        mAdapter.notifyDataSetChanged();
    }
    public void query() {
        datas.clear();
        mAdapter.notifyDataSetChanged();
        mContext.showWaitingDialog(UIUtils.getString(R.string.please_wait));
        OkHttpUtils okHttpUtils = OkHttpUtils.initClient();
        okHttpUtils.setOnResultListener(newstr1 -> mContext.runOnUiThread(() -> {
            mContext.hideWaitingDialog();
            if (newstr1.equals("数据请求失败")) {

                mContext.speechUtil.speakXunFei("数据请求失败");
                return;
            }
            Gson gson = new Gson();
            QueryResponse response = null;
            try {
                response = gson.fromJson(newstr1, QueryResponse.class);
            } catch (JsonSyntaxException e) {
                mContext.hideWaitingDialog();
                mContext.speechUtil.speakXunFei("服务器异常");
//                    mContext.loginError(new ServerException(UIUtils.getString(R.string.login_error)));
                e.printStackTrace();
                return;
            }
            String code = response.getErrorCode();
            if (code.equals("1000")) {
//                QueryResponseSingle queryResponseSingle=gson.fromJson(response.getData(),QueryResponseSingle.class);

                    Type type = new TypeToken<ArrayList<QueryResponseSingle>>()
                    {}.getType();
                    ArrayList<QueryResponseSingle> jsonObjects = new Gson().fromJson(response.getData(), type);
                    datas.addAll(jsonObjects);
                    mAdapter.notifyDataSetChanged();
                mContext.hideWaitingDialog();
//                    UserCache.save(loginResponse.getResult().getId(), phone, loginResponse.getResult().getToken());
            } else {
                mContext.speechUtil.speakXunFei(response.getErrorMsg());
                mContext.hideWaitingDialog();
                return;
//                    loginError(new ServerException(UIUtils.getString(R.string.login_error) + code));
            }
        }));

        String country=getView().getEtCountryV().getText().toString().trim();
        String birthplace=getView().getEtBirthplaceV().getText().toString().trim();
        String type=getView().getEtTypeV().getText().toString().trim();
        String name=getView().getName().getText().toString().trim();
        String capacity=getView().getEtCapacityV().getText().toString().trim();
        String year=getView().getEtYearV().getText().toString().trim();
        QueryBean queryBean=new QueryBean();
        queryBean.setRecordName(name);
        queryBean.setCountry(country);
        queryBean.setOrigin(birthplace);
        queryBean.setCategory(type);
        queryBean.setVolume(capacity);
        queryBean.setProductiveYear(year);

        okHttpUtils.myEnqueue(new QueryRequest(queryBean).getUrl(), null);
//            mAdapter.notifyDataSetChanged();
    }

    public boolean exportToExcel(){
        String[][] excelArr=new String[datas.size()][];
        for (int i=0;i<datas.size();i++){
            String[] arr=datas.get(i).toString().split("#");
            excelArr[i]=arr;
        }
        if (datas.size()!=0){
            boolean isOk= WriteExcelUtils.writeExecleToFile(mContext,excelArr);
            return isOk;
        }

        return false;
    }

    public void showTipDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setTitle("注意:重要提示");
        builder.setMessage("如果您已安装WPS等软件，点击界面打开按钮查看Excel数据；如果没有安装WPS等第三方软件，您可以切换到手机文件管理的文件目录，找到EXCEL_DIR文件夹查看！");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.show();

    }

//    下面的废弃
    public void query1() {
        datas.clear();
        mAdapter.notifyDataSetChanged();
        mContext.showWaitingDialog(UIUtils.getString(R.string.please_wait));
        OkHttpUtils okHttpUtils = OkHttpUtils.initClient();
        okHttpUtils.setOnResultListener(newstr1 -> mContext.runOnUiThread(() -> {
//                mContext.hideWaitingDialog();
            if (newstr1.equals("数据请求失败")) {
                mContext.speechUtil.speakXunFei("数据请求失败");
                return;
            }
            Gson gson = new Gson();
            QueryResponse response = null;
            try {
                response = gson.fromJson(newstr1, QueryResponse.class);
            } catch (JsonSyntaxException e) {
                mContext.hideWaitingDialog();
                mContext.speechUtil.speakXunFei("服务器异常");
//                    mContext.loginError(new ServerException(UIUtils.getString(R.string.login_error)));
                e.printStackTrace();
                return;
            }
            String code = response.getErrorCode();
            if (code.equals("1000")) {
                 QueryResponseSingle queryResponseSingle=gson.fromJson(response.getData(),QueryResponseSingle.class);
                datas.add(queryResponseSingle);
                mAdapter.notifyDataSetChanged();
                mContext.hideWaitingDialog();
//                    UserCache.save(loginResponse.getResult().getId(), phone, loginResponse.getResult().getToken());
            } else {
                mContext.speechUtil.speakXunFei(response.getErrorMsg());
                mContext.hideWaitingDialog();
                return;
//                    loginError(new ServerException(UIUtils.getString(R.string.login_error) + code));
            }
        }));
//        okHttpUtils.myEnqueue(new QueryRequest(getView().getEtScanV().getText().toString().trim()).getUrl(), null);
//            mAdapter.notifyDataSetChanged();
    }

    public void setAdapter() {
        if (mAdapter == null) {
            mAdapter = new LQRAdapterForRecyclerView<QueryResponseSingle>(mContext, datas, R.layout.iten_query) {
                @Override
                public void convert(LQRViewHolderForRecyclerView helper, QueryResponseSingle item, int position) {
                    LinearLayout listitem=helper.getView(R.id.item);
                    TextView danHao = helper.getView(R.id.danHao);
                    TextView country = helper.getView(R.id.tv_country);
                    TextView birthday = helper.getView(R.id.tv_birthday);
                    TextView type= helper.getView(R.id.tv_type);
                    TextView count = helper.getView(R.id.tv_count);
                    TextView location = helper.getView(R.id.tv_location);
                    TextView num = helper.getView(R.id.tv_num);

                    danHao.setText(item.getRecordCode());
                    country.setText(item.getCountry());
                    birthday.setText(item.getOrigin());
                    type.setText(item.getCategory());
                    count.setText(item.getCountNum());
                    location.setText(item.getPosition());
                    num.setText(String.valueOf(datas.size()-position));
//                    listitem.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View v) {
//                            //                    跳转详情
//                            Intent intent=new Intent(mContext, RecordDetailActivity.class);
//                            intent.putExtra("bean",item);
//                            mContext.jumpToActivity(intent);
//
//                        }
//                    });
                }
            };
            getView().getRvRecyclerView().setAdapter(mAdapter);
        }

    }
}
