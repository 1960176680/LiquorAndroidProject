package com.hz.tt.mvp.presenter.impl;

import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.hz.tt.R;
import com.hz.tt.api.okHttpUtils.OkHttpUtils;
import com.hz.tt.mvp.model.entity.request.QueryRequest;
import com.hz.tt.mvp.model.entity.response.QueryResponse;
import com.hz.tt.mvp.model.entity.response.QueryResponseSingle;
import com.hz.tt.mvp.presenter.base.BasePresenter;
import com.hz.tt.mvp.ui.common.BaseActivity;
import com.hz.tt.mvp.ui.view.IDiscoveryFgView;
import com.hz.tt.util.UIUtils;
import com.lqr.adapter.LQRAdapterForRecyclerView;
import com.lqr.adapter.LQRViewHolderForRecyclerView;

import java.util.ArrayList;
import java.util.List;

public class DiscoveryFgPresenter extends BasePresenter<IDiscoveryFgView> {
    private List<QueryResponseSingle> datas= new ArrayList<>();
    private LQRAdapterForRecyclerView<QueryResponseSingle> mAdapter;
    public DiscoveryFgPresenter(BaseActivity context) {
        super(context);
    }

    public void query() {
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
                mContext.speechUtil.speakXunFei(code);
                mContext.hideWaitingDialog();
                return;
//                    loginError(new ServerException(UIUtils.getString(R.string.login_error) + code));
            }
        }));
        okHttpUtils.myEnqueue(new QueryRequest(getView().getEtScanV().getText().toString().trim()).getUrl(), null);
//            mAdapter.notifyDataSetChanged();
    }

    public void setAdapter() {
        if (mAdapter == null) {
            mAdapter = new LQRAdapterForRecyclerView<QueryResponseSingle>(mContext, datas, R.layout.iten_query) {
                @Override
                public void convert(LQRViewHolderForRecyclerView helper, QueryResponseSingle item, int position) {
                    TextView danHao = helper.getView(R.id.danHao);
                    TextView birthday = helper.getView(R.id.tv_birthday);
                    TextView year= helper.getView(R.id.tv_year);
                    TextView count = helper.getView(R.id.tv_count);
                    TextView location = helper.getView(R.id.tv_location);
                    TextView num = helper.getView(R.id.tv_num);

                    danHao.setText(item.getRecordCode());
                    birthday.setText(item.getOrigin());
                    year.setText(item.getProductiveYear());
                    count.setText(item.getCountNum());
                    location.setText(item.getPosition());
                    num.setText("1");

                }
            };
            getView().getRvRecyclerView().setAdapter(mAdapter);
        }

    }
}
