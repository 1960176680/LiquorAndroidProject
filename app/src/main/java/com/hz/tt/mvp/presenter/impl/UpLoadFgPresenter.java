package com.hz.tt.mvp.presenter.impl;

import android.content.Intent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.hz.tt.R;
import com.hz.tt.api.okHttpUtils.OkHttpUtils;
import com.hz.tt.mvp.model.entity.request.QueryUpRequest;
import com.hz.tt.mvp.model.entity.response.QueryResponse;
import com.hz.tt.mvp.model.entity.response.QueryResponseSingle;
import com.hz.tt.mvp.presenter.base.BasePresenter;
import com.hz.tt.mvp.ui.activity.RecordDetailActivity;
import com.hz.tt.mvp.ui.common.BaseActivity;
import com.hz.tt.mvp.ui.view.IUpLoadFgView;
import com.hz.tt.util.UIUtils;
import com.lqr.adapter.LQRAdapterForRecyclerView;
import com.lqr.adapter.LQRViewHolderForRecyclerView;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018-02-24.
 */

public class UpLoadFgPresenter extends BasePresenter<IUpLoadFgView> {
    private LQRAdapterForRecyclerView<QueryResponseSingle> mAdapter;
    private List<QueryResponseSingle> datas= new ArrayList<>();

    public UpLoadFgPresenter(BaseActivity context) {
        super(context);
    }



    public void setAdapter() {
        if (mAdapter == null) {
            mAdapter = new LQRAdapterForRecyclerView<QueryResponseSingle>(mContext, datas, R.layout.list_item_upload) {
                @Override
                public void convert(LQRViewHolderForRecyclerView helper, QueryResponseSingle item, int position) {
                    RelativeLayout listitem=helper.getView(R.id.item);
                    TextView tvType = helper.getView(R.id.tvType);
                    TextView tvCode = helper.getView(R.id.tvCode);
                    TextView tvTime = helper.getView(R.id.tvTime);

                    tvType.setText(item.getOperation()+"：");
                    tvCode.setText(item.getRecordCode());

                    if (item.getOperation().equals("入库")){
                        tvTime.setText(item.getIntoDate());
                    }else{
                        tvTime.setText(item.getOutDate());
                    }
                    listitem.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
//                            跳转详情
                            Intent intent=new Intent(mContext, RecordDetailActivity.class);
                            intent.putExtra("bean",item);
                            mContext.jumpToActivity(intent);



                        }
                    });


                }
            };
            getView().getRvRecyclerView().setAdapter(mAdapter);
        }

    }



    public void queryData() {
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

        okHttpUtils.myEnqueue(new QueryUpRequest(getView().getTimeV().getText().toString().trim()).getUrl(), null);
//            mAdapter.notifyDataSetChanged();
    }

}
