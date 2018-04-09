package com.hz.tt.mvp.presenter.impl;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.hz.tt.R;
import com.hz.tt.api.okHttpUtils.OkHttpUtils;
import com.hz.tt.app.MyApp;
import com.hz.tt.greendao.gen.OutBeanDao;
import com.hz.tt.mvp.model.entity.OutBean;
import com.hz.tt.mvp.model.entity.QueryBean;
import com.hz.tt.mvp.model.entity.cache.UserCache;
import com.hz.tt.mvp.model.entity.request.QueryRequest;
import com.hz.tt.mvp.model.entity.request.UpOutRequest;
import com.hz.tt.mvp.model.entity.response.OutResponse;
import com.hz.tt.mvp.model.entity.response.QueryResponse;
import com.hz.tt.mvp.model.entity.response.QueryResponseSingle;
import com.hz.tt.mvp.presenter.base.BasePresenter;
import com.hz.tt.mvp.ui.common.BaseActivity;
import com.hz.tt.mvp.ui.view.IContactsFgView;
import com.hz.tt.util.UIUtils;
import com.lqr.adapter.LQRAdapterForRecyclerView;
import com.lqr.adapter.LQRViewHolderForRecyclerView;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ContactsFgPresenter extends BasePresenter<IContactsFgView> {

    private LQRAdapterForRecyclerView mAdapter;
    private List<OutBean> datas= new ArrayList<>();
    private List<OutBean> removeDatas= new ArrayList<>();
    private String responseImgUrl;
    public ContactsFgPresenter(BaseActivity context) {
        super(context);
    }

    public void clearYesUp(){
        datas.removeAll(removeDatas);
    }

    /**
     * 清空界面列表
     */
    public void clearAllData(){
        datas.clear();
        removeDatas.clear();
        mAdapter.notifyDataSetChanged();
        responseImgUrl=null;
    }
    public void getConversations() {
        setAdapter(datas);
    }
    public void setAdapter(List<OutBean> datas) {
        if (mAdapter == null) {
            mAdapter = new LQRAdapterForRecyclerView<OutBean>(mContext, datas, R.layout.iten_in) {
                @Override
                public void convert(LQRViewHolderForRecyclerView helper, OutBean item, int position) {
                    TextView danHao = helper.getView(R.id.danHao);
                    TextView states = helper.getView(R.id.states);
                    TextView num = helper.getView(R.id.num);
                    ImageView delete_img = helper.getView(R.id.delete_img);

                    danHao.setText(item.getRecordCode());
                    String status=item.getStatus();

                    if (status.equals("未上传")){
                        states.setTextColor(Color.RED);
                        delete_img.setVisibility(View.VISIBLE);
                    }else {
                        states.setTextColor(Color.GREEN);
                        delete_img.setVisibility(View.INVISIBLE);
                    }
                    states.setText(status);
                    delete_img.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                            builder.setTitle("注意:删除操作");
                            builder.setMessage("确定要删除吗？");
                            builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    OutBean outBean=datas.get(position);
                                    OutBeanDao inBeanDao=MyApp.getInstances().getDaoSession().getOutBeanDao();
                                    inBeanDao.delete(outBean);
                                    datas.remove(position);
                                    mAdapter.notifyDataSetChanged();
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
                    });



                    int size=datas.size();
                    num.setText(size-position+"");
                }
            };
            getView().getRvContacts().setAdapter(mAdapter);
        }

    }

    public boolean addRecord(){
        if (datas!=null){
            OutBean bean=new OutBean();
            IContactsFgView view=getView();

            String recordCode=view.getCode().getText().toString().trim();
            String operation=view.getDoType().getText().toString().trim();


            int totalNum=0;
            if (!view.getNum().getText().toString().trim().equals("")){
                totalNum=Integer.valueOf(view.getNum().getText().toString());
            }else{
                UIUtils.showToast("请检查是否获取该商品的网络信息，手动输入的单号请按回车键！");
                return false;
            }


            if (operation.equals("出库")){
                if (!view.getOutNum().getText().toString().trim().equals("")){
                    int outNum=Integer.valueOf(view.getOutNum().getText().toString());
                    if (outNum>totalNum){
                        UIUtils.showToast("出库数量大于当前库存！");
                        return false;
                    }else if (outNum<=0){
                        UIUtils.showToast("出库数量必须大于0！");
                        return false;
                    }
                }else {
                    UIUtils.showToast("请检查必填数据不能为空！");
                    return false;
                }


            }

            String position=view.getPosition().getText().toString().trim();
            String outRecordSource="出库指令来自";
            String outRecordDate="2000-00-00 00:00:00";
            String outDate=view.getRecDate().getText().toString().trim();
            String outCustomer= UserCache.getPhone();
            String receiveCustomer=view.getRecPerson().getText().toString().trim();
            String receiveDate=view.getRecDate().getText().toString().trim();
            String countNum=view.getOutNum().getText().toString().trim();
            String intoDate=view.getRecDate().getText().toString().trim();
            String intoCustomer= UserCache.getPhone();
            String remark=view.getRemak().getText().toString().trim();
            if (!receiveCustomer.equals("")
                    &&!receiveDate.equals("")
                    &&!recordCode.equals("")
                    &&!countNum.equals("")
                    )
            {
                bean.setRecordCode(recordCode);
                bean.setOperation(operation);
                bean.setPosition(position);
                bean.setOutRecordSource(outRecordSource);
                bean.setOutRecordDate(outRecordDate);
                if (operation.equals("出库")){
                    bean.setOutDate(outDate);
                    bean.setOutCustomer(outCustomer);
                    bean.setReceiveCustomer(receiveCustomer);
                    bean.setRemark(remark);
                }

                if (operation.equals("入库")){
                    bean.setIntoDate(intoDate);
                    bean.setIntoCustomer(intoCustomer);
                }

                bean.setCountNum(countNum);

                bean.setStatus("未上传");
                datas.add(0,bean);
                mAdapter.notifyDataSetChanged();

                OutBeanDao inBeanDao = MyApp.getInstances().getDaoSession().getOutBeanDao();
                inBeanDao.insert(bean);
                return true;
            }else{
                UIUtils.showToast("请检查数据完整性！");
                return false;
            }
        }
        return false;
    }
    public void upRecord(){
        if (datas!=null&&datas.size()!=0){
            mContext.showWaitingDialog(UIUtils.getString(R.string.please_wait));
            OkHttpUtils okHttpUtils=OkHttpUtils.initClient();
            okHttpUtils.setOnResultListener(newstr1 -> mContext.runOnUiThread(() -> {
                if (newstr1.equals("数据请求失败")) {
                    mContext.hideWaitingDialog();
                    mContext.speechUtil.speakXunFei("数据请求失败");
                    return;
                }
                Gson gson = new Gson();
                OutResponse response = null;
                try {
                    response = gson.fromJson(newstr1,OutResponse.class);
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
                    OutBean inBean=datas.get(0);
                    inBean.setStatus("已上传");
                    OutBeanDao inBeanDao = MyApp.getInstances().getDaoSession().getOutBeanDao();
                    inBeanDao.update(inBean);
//                    UserCache.save(loginResponse.getResult().getId(), phone, loginResponse.getResult().getToken());
                    removeDatas.add(0,datas.get(0));
                    datas.remove(0);
                    upRecord();
                } else {
                    mContext.speechUtil.speakXunFei(response.getErrorMsg());
                    mContext.hideWaitingDialog();
                    return;
//                    loginError(new ServerException(UIUtils.getString(R.string.login_error) + code));
                }
            }));

            okHttpUtils.myEnqueue(new UpOutRequest(datas).getUrl(),null);
        }else{
            datas.addAll(removeDatas);
            mAdapter.notifyDataSetChanged();
        }

    }
    public void jumpActivity(Class activity){
        Intent intent=new Intent(mContext,activity);
        intent.putExtra("url",responseImgUrl);
        mContext.jumpToActivity(intent);

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


}
