package com.hz.tt.mvp.presenter.impl;

import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.hz.tt.R;
import com.hz.tt.api.okHttpUtils.OkHttpUtils;
import com.hz.tt.mvp.model.entity.QueryBean;
import com.hz.tt.mvp.model.entity.request.GoodsAndUserComRequest;
import com.hz.tt.mvp.model.entity.request.QueryRequest;
import com.hz.tt.mvp.model.entity.response.GoodsAndUserCommentBean;
import com.hz.tt.mvp.model.entity.response.QueryResponse;
import com.hz.tt.mvp.model.entity.response.QueryResponseSingle;
import com.hz.tt.mvp.presenter.base.BasePresenter;
import com.hz.tt.mvp.ui.common.BaseActivity;
import com.hz.tt.mvp.ui.view.IGoodsEvaluateAtView;
import com.hz.tt.util.UIUtils;
import com.lqr.adapter.LQRAdapterForRecyclerView;
import com.lqr.adapter.LQRViewHolderForRecyclerView;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018-04-11.
 */

public class GoodsEvaluateAtPresenter extends BasePresenter<IGoodsEvaluateAtView>{
    private LQRAdapterForRecyclerView<GoodsAndUserCommentBean> mAdapter;
    private List<GoodsAndUserCommentBean> datas= new ArrayList<>();
    private String responseImgUrl;
    public GoodsEvaluateAtPresenter(BaseActivity context) {
        super(context);
    }

    public void getConversations() {
        setAdapter(datas);
    }
    public String query() {
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
                if (jsonObjects.size()!=0){
                    QueryResponseSingle queryResponseSingle=jsonObjects.get(0);
                    getView().getName().setText(queryResponseSingle.getRecordName());
                    getView().getType().setText(queryResponseSingle.getCategory());
                    getView().getCountry().setText(queryResponseSingle.getCountry());
                    getView().getBirthday().setText(queryResponseSingle.getOrigin());
                    getView().getCapacity().setText(queryResponseSingle.getVolume());
                    getView().getYear().setText(queryResponseSingle.getProductiveYear());
                    getView().getNum().setText(queryResponseSingle.getCountNum());
                    getView().getPosition().setText(queryResponseSingle.getPosition());
                    getView().getRatingBar().setRating(queryResponseSingle.getStarLevelAvg());
                    responseImgUrl=queryResponseSingle.getPhoto();
                    Glide.with(mContext).load("http://121.43.167.170:8001/Wine/"+queryResponseSingle.getPhoto()).centerCrop().into(getView().getImgV());
                }else {
                    UIUtils.showToast("无此商品信息，请新增！");
                    mContext.speechUtil.speakXunFei("无此商品信息");
                }
//                datas.addAll(jsonObjects);
//                mAdapter.notifyDataSetChanged();

                mContext.hideWaitingDialog();
//                    UserCache.save(loginResponse.getResult().getId(), phone, loginResponse.getResult().getToken());
            queryComment();
            } else {
                mContext.speechUtil.speakXunFei(response.getErrorMsg());
                mContext.hideWaitingDialog();
                return;
//                    loginError(new ServerException(UIUtils.getString(R.string.login_error) + code));
            }
        }));

        QueryBean queryBean=new QueryBean();
        if (!getView().getCode().getText().toString().trim().equals("")){
            queryBean.setRecordCode(getView().getCode().getText().toString().trim());
            okHttpUtils.myEnqueue(new QueryRequest(queryBean).getUrl(), null);
        }else {
            mContext.hideWaitingDialog();
            UIUtils.showToast("查询条码为空！");
        }


//            mAdapter.notifyDataSetChanged();
        return responseImgUrl;
    }


    public void queryComment() {
        datas.clear();
        OkHttpUtils okHttpUtils = OkHttpUtils.initClient();
        okHttpUtils.setOnResultListener(newstr1 -> mContext.runOnUiThread(() -> {
            if (newstr1.equals("数据请求失败")) {
                mContext.speechUtil.speakXunFei("数据请求失败");
                return;
            }
            Gson gson = new Gson();
            QueryResponse response = null;
            try {
                response = gson.fromJson(newstr1, QueryResponse.class);
            } catch (JsonSyntaxException e) {
                mContext.speechUtil.speakXunFei("服务器异常");
//                    mContext.loginError(new ServerException(UIUtils.getString(R.string.login_error)));
                e.printStackTrace();
                return;
            }
            String code = response.getErrorCode();
            if (code.equals("1000")) {
//                QueryResponseSingle queryResponseSingle=gson.fromJson(response.getData(),QueryResponseSingle.class);

                Type type = new TypeToken<ArrayList<GoodsAndUserCommentBean>>()
                {}.getType();
                ArrayList<GoodsAndUserCommentBean> jsonObjects = new Gson().fromJson(response.getData(), type);
                if (jsonObjects.size()!=0){
                    for (int i=0;i<jsonObjects.size();i++){
                        GoodsAndUserCommentBean queryResponseSingle=jsonObjects.get(i);
                        datas.add(queryResponseSingle);
                    }
                }else {
                    UIUtils.showToast("无此商品信息，请新增！");
                    mContext.speechUtil.speakXunFei("无此商品信息");
                }
            } else {
                mContext.speechUtil.speakXunFei(response.getErrorMsg());

                return;
 }
            mAdapter.notifyDataSetChanged();
        }));

        GoodsAndUserCommentBean queryBean=new GoodsAndUserCommentBean();
        if (!getView().getCode().getText().toString().trim().equals("")){
            queryBean.setRecordCode(getView().getCode().getText().toString().trim());
            okHttpUtils.myEnqueue(new GoodsAndUserComRequest(queryBean).getUrl(), null);
        }else {
            UIUtils.showToast("查询条码为空！");
        }
    }

    private void setAdapter(List<GoodsAndUserCommentBean> datas) {
        if (mAdapter == null) {
            mAdapter = new LQRAdapterForRecyclerView<GoodsAndUserCommentBean>(mContext, datas, R.layout.item_goodsevaluate) {
                @Override
                public void convert(LQRViewHolderForRecyclerView helper, GoodsAndUserCommentBean item, int position) {
                    TextView tvTime = helper.getView(R.id.tvTime);
                    TextView tvMan = helper.getView(R.id.tvMan);
                    TextView tvComment = helper.getView(R.id.tvComment);
                    RatingBar ratingBar = helper.getView(R.id.ratingBar);

                    tvTime.setText(item.getCreateDate());
                    tvMan.setText(item.getCommentUser());
                    tvComment.setText(item.getContent());
                    ratingBar.setRating(item.getStarLevel());
                }
            };
            getView().getRecycleView().setAdapter(mAdapter);
        }

    }
}
