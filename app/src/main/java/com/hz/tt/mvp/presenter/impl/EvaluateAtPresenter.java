package com.hz.tt.mvp.presenter.impl;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.hz.tt.R;
import com.hz.tt.api.okHttpUtils.OkHttpUtils;
import com.hz.tt.mvp.model.entity.CommentBean;
import com.hz.tt.mvp.model.entity.QueryBean;
import com.hz.tt.mvp.model.entity.cache.UserCache;
import com.hz.tt.mvp.model.entity.request.CommentRequest;
import com.hz.tt.mvp.model.entity.request.QueryRequest;
import com.hz.tt.mvp.model.entity.response.QueryResponse;
import com.hz.tt.mvp.model.entity.response.QueryResponseSingle;
import com.hz.tt.mvp.model.entity.response.RegisterResponse;
import com.hz.tt.mvp.presenter.base.BasePresenter;
import com.hz.tt.mvp.ui.common.BaseActivity;
import com.hz.tt.mvp.ui.view.IEvaluateAtView;
import com.hz.tt.util.UIUtils;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by Administrator on 2018-04-10.
 */

public class EvaluateAtPresenter extends BasePresenter<IEvaluateAtView> {
    private String responseImgUrl;

    public EvaluateAtPresenter(BaseActivity context) {
        super(context);
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
                    getView().getName().setText("品名："+queryResponseSingle.getRecordName());
                    getView().getType().setText(queryResponseSingle.getCategory());
                    getView().getCountry().setText(queryResponseSingle.getCountry());
                    getView().getBirthday().setText(queryResponseSingle.getOrigin());
                    getView().getCapacity().setText(queryResponseSingle.getVolume());
                    getView().getYear().setText(queryResponseSingle.getProductiveYear());
                    getView().getNum().setText(queryResponseSingle.getCountNum());
                    getView().getPosition().setText(queryResponseSingle.getPosition());
                    getView().getRatingBar().setProgress(queryResponseSingle.getStarLevelAvg());
//                    Bitmap bitmap = ((BitmapDrawable) getView().getImgV().getDrawable()).getBitmap();
//                    getView().getImgV().setImageResource(R.mipmap.ic_launcher);
//                    if (bitmap != null && !bitmap.isRecycled()){
//                        bitmap.recycle();
//                        bitmap = null;
//                    }
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
            } else {
                mContext.speechUtil.speakXunFei(response.getErrorMsg());
                mContext.hideWaitingDialog();
                return;
//                    loginError(new ServerException(UIUtils.getString(R.string.login_error) + code));
            }
        }));


//        查询的json数据
//        String country=getView().getEtCountryV().getText().toString().trim();
//        String birthplace=getView().getEtBirthplaceV().getText().toString().trim();
//        String type=getView().getEtTypeV().getText().toString().trim();
//        String capacity=getView().getEtCapacityV().getText().toString().trim();
//        String year=getView().getEtYearV().getText().toString().trim();
        QueryBean queryBean=new QueryBean();
//        queryBean.setCountry(country);
//        queryBean.setOrigin(birthplace);
//        queryBean.setCategory(type);
//        queryBean.setVolume(capacity);
//        queryBean.setProductiveYear(year);
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

    public String commit() {
        mContext.showWaitingDialog(UIUtils.getString(R.string.please_wait));
        OkHttpUtils okHttpUtils = OkHttpUtils.initClient();
        okHttpUtils.setOnResultListener(newstr1 -> mContext.runOnUiThread(() -> {
            mContext.hideWaitingDialog();
            if (newstr1.equals("数据请求失败")) {
                mContext.speechUtil.speakXunFei("数据请求失败");
                return;
            }
            Gson gson = new Gson();
            RegisterResponse response = null;
            try {
                response = gson.fromJson(newstr1, RegisterResponse.class);
            } catch (JsonSyntaxException e) {
                mContext.hideWaitingDialog();
                mContext.speechUtil.speakXunFei("服务器异常");
//                    mContext.loginError(new ServerException(UIUtils.getString(R.string.login_error)));
                e.printStackTrace();
                return;
            }
            String code = response.getErrorCode();
            if (code.equals("1000")) {
                UIUtils.showToast("评论成功");
                mContext.hideWaitingDialog();
            } else {
                mContext.speechUtil.speakXunFei(response.getErrorMsg());
                mContext.hideWaitingDialog();
                return;

            }
        }));


        CommentBean bean=new CommentBean();
        if (!getView().getCode().getText().toString().trim().equals("")
                &&!getView().getSuggestPerson().getText().toString().trim().equals("")
                &&!getView().getSuggestArea().getText().toString().trim().equals("")
                ){
            bean.setRecordCode(getView().getCode().getText().toString().trim());
            bean.setStarLevel(String.valueOf(getView().getRatingBarLike().getNumStars()));
            bean.setCommentUser(getView().getSuggestPerson().getText().toString().trim());
            bean.setContent(getView().getSuggestArea().getText().toString().trim());
            bean.setCreateUser(UserCache.getPhone());
            okHttpUtils.myEnqueue(new CommentRequest(bean).getUrl(), null);
        }else {
            mContext.hideWaitingDialog();
            UIUtils.showToast("查询条码为空！");
        }


//            mAdapter.notifyDataSetChanged();
        return responseImgUrl;
    }
}
