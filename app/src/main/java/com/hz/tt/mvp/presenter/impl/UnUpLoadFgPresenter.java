package com.hz.tt.mvp.presenter.impl;

import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.hz.tt.R;
import com.hz.tt.api.okHttpUtils.NetConstant;
import com.hz.tt.api.okHttpUtils.OkHttpUtils;
import com.hz.tt.app.MyApp;
import com.hz.tt.greendao.gen.InBeanDao;
import com.hz.tt.greendao.gen.OutBeanDao;
import com.hz.tt.mvp.model.entity.InBean;
import com.hz.tt.mvp.model.entity.OutBean;
import com.hz.tt.mvp.model.entity.RecordDataBean;
import com.hz.tt.mvp.model.entity.request.UpInRecordRequest;
import com.hz.tt.mvp.model.entity.response.ImageUpResponse;
import com.hz.tt.mvp.model.entity.response.UpInRecordResponse;
import com.hz.tt.mvp.presenter.base.BasePresenter;
import com.hz.tt.mvp.ui.common.BaseActivity;
import com.hz.tt.mvp.ui.view.IUnUpLoadFgView;
import com.hz.tt.util.UIUtils;
import com.lqr.adapter.LQRAdapterForRecyclerView;
import com.lqr.adapter.LQRViewHolderForRecyclerView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MultipartBody;

import static com.hz.tt.app.MyApp.getInstances;

/**
 * Created by Administrator on 2018-02-24.
 */

public class UnUpLoadFgPresenter extends BasePresenter<IUnUpLoadFgView> {
    private List<RecordDataBean> dataBeenList=new ArrayList<>();
    private LQRAdapterForRecyclerView mAdapter;
    List<InBean> inBeanList;
    List<OutBean> outBeanList;
    public UnUpLoadFgPresenter(BaseActivity context) {
        super(context);
    }

    public void getConversations() {
        setAdapter(dataBeenList);
    }

    public void loadRecordData(){
        if (dataBeenList.size()!=0){
            dataBeenList.clear();
        }
        List<InBean> inBeanList= MyApp.getInstances().getDaoSession().getInBeanDao().queryBuilder().where(InBeanDao.Properties.Status.eq("未上传")).build().list();
        List<OutBean> outBeanList= MyApp.getInstances().getDaoSession().getOutBeanDao().queryBuilder().where(OutBeanDao.Properties.Status.eq("未上传")).build().list();
        for (InBean bean:inBeanList){
            RecordDataBean recordDataBean=new RecordDataBean();
            recordDataBean.setType("入：");
            recordDataBean.setcode(bean.getCode());
            recordDataBean.setDate(bean.getTime());
            dataBeenList.add(recordDataBean);
        }
        for (OutBean bean:outBeanList){
            RecordDataBean recordDataBean=new RecordDataBean();
            recordDataBean.setType("出：");
            recordDataBean.setcode(bean.getRecordCode());
            recordDataBean.setDate(bean.getOutDate());
            dataBeenList.add(recordDataBean);
        }

        if (getView()!=null){
            getView().getVCount().setText(String.valueOf(dataBeenList.size()));
            mAdapter.notifyDataSetChanged();
        }
    }
    public void setAdapter(List<RecordDataBean> datas) {
        if (mAdapter == null) {
            mAdapter = new LQRAdapterForRecyclerView<RecordDataBean>(mContext, datas, R.layout.unupload_data_item) {
                @Override
                public void convert(LQRViewHolderForRecyclerView helper, RecordDataBean item, int position) {
                    TextView danHao = helper.getView(R.id.tv_YDCode);
                    TextView type = helper.getView(R.id.tv_type);
                    ImageView ivDel = helper.getView(R.id.iv_delete);
                    type.setText(item.getType());
                    danHao.setText(item.getcode());


//                    if (status.equals("未上传")){
//                        states.setTextColor(Color.RED);
////                        delete_img.setVisibility(View.VISIBLE);
//                    }else {
//                        states.setTextColor(Color.GREEN);
//                    }
//                    states.setText(status);
//                    int size=datas.size();
//                    num.setText(size-position+"");
                }
            };
            getView().getLQRRecyclerView().setAdapter(mAdapter);
        }

    }

    public void loadInAndOutList(){
        inBeanList= MyApp.getInstances().getDaoSession().getInBeanDao().queryBuilder().where(InBeanDao.Properties.Status.eq("未上传")).build().list();
        outBeanList= MyApp.getInstances().getDaoSession().getOutBeanDao().queryBuilder().where(OutBeanDao.Properties.Status.eq("未上传")).build().list();

    }

    public void upRecordImg(){
        if (inBeanList!=null&&inBeanList.size()!=0){
            mContext.showWaitingDialog(UIUtils.getString(R.string.please_wait));

            OkHttpUtils okHttpUtils=OkHttpUtils.initClient();
            /**
             * 下面上传图片监听
             * ============================================================================
             * ===========================================================================
             * ============================================================================
             */
            okHttpUtils.setOnResultListener(newstr1 -> mContext.runOnUiThread(() -> {
                mContext.hideWaitingDialog();
                if (newstr1.equals("数据请求失败")) {
                    mContext.speechUtil.speakXunFei("数据请求失败");
                    return;
                }
                Gson gson = new Gson();
                ImageUpResponse response = null;
                try {
                    response = gson.fromJson(newstr1,ImageUpResponse.class);
                } catch (JsonSyntaxException e) {
                    mContext.hideWaitingDialog();
                    mContext.speechUtil.speakXunFei("服务器异常");
//                    mContext.loginError(new ServerException(UIUtils.getString(R.string.login_error)));
                    e.printStackTrace();
                    return;
                }
                String code = response.getCode();
                if (code.equals("1000")) {
//                    UserCache.save(loginResponse.getResult().getId(), phone, loginResponse.getResult().getToken());
                    String imgUri=response.getData();
                    try {
                        JSONObject jsonObject = new JSONObject(imgUri);
                        String imgtrue=jsonObject.getString("url");
                        inBeanList.get(0).setImgstr(imgtrue);
                        mContext.hideWaitingDialog();
                        upRecord();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                } else {
                    mContext.speechUtil.speakXunFei(code);
                    mContext.hideWaitingDialog();
                    return;
//                    loginError(new ServerException(UIUtils.getString(R.string.login_error) + code));
                }
            }));
            /**
             * 上面上传图片监听
             * ============================================================================
             * ===========================================================================
             * ============================================================================
             */

//            上传图片
            InBean bean=inBeanList.get(0);
            String bitmapPath=bean.getImgstr();
            File file = new File(bitmapPath);
            MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);
            builder.addFormDataPart("file", file.getName(), okhttp3.RequestBody.create(okhttp3.MediaType.parse("image/png"), file));
//            "http://121.43.167.170:8001/Wine/upload"
            okHttpUtils.myEnqueue(NetConstant.BASE_URL_IMG,builder.build());
//            mAdapter.notifyDataSetChanged();
        }else{
            for (RecordDataBean bean:dataBeenList){
                if (bean.getType().equals("入：")){
                    dataBeenList.remove(bean);
                }
            }
            mAdapter.notifyDataSetChanged();
        }
    }
    public void upRecord(){
        mContext.showWaitingDialog(UIUtils.getString(R.string.please_wait));
        OkHttpUtils okHttpUtils=OkHttpUtils.initClient();
        /**
         * ============================================================
         * 下面上传入库记录监听
         * ============================================================
         */
        okHttpUtils.setOnResultListener(newstr1 -> mContext.runOnUiThread(() -> {
            if (newstr1.equals("数据请求失败")) {
                mContext.hideWaitingDialog();
                mContext.speechUtil.speakXunFei("数据请求失败");
                return;
            }
            Gson gson = new Gson();
            UpInRecordResponse response = null;
            try {
                response = gson.fromJson(newstr1,UpInRecordResponse.class);
            } catch (JsonSyntaxException e) {
                mContext.speechUtil.speakXunFei("服务器异常");
                mContext.hideWaitingDialog();
//                    mContext.loginError(new ServerException(UIUtils.getString(R.string.login_error)));
                e.printStackTrace();
                return;
            }
            String code = response.getErrorCode();
            if (code.equals("1000")) {
                mContext.hideWaitingDialog();
                InBean inBean=inBeanList.get(0);
                inBean.setStatus("已上传");
                InBeanDao inBeanDao = getInstances().getDaoSession().getInBeanDao();
                inBeanDao.update(inBean);
//                    UserCache.save(loginResponse.getResult().getId(), phone, loginResponse.getResult().getToken());
//                removeDatas.add(0,datas.get(0));
                inBeanList.remove(0);
                upRecordImg();
            } else {
                mContext.speechUtil.speakXunFei(response.getErrorMsg());
                mContext.hideWaitingDialog();
                return;
//                    loginError(new ServerException(UIUtils.getString(R.string.login_error) + code));
            }
        }));
/**
 * ============================================================
 * 上面上传入库记录监听
 * ============================================================
 */
        okHttpUtils.myEnqueue(new UpInRecordRequest(inBeanList).getUrl(),null);
    }
}
