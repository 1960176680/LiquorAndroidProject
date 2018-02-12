package com.hz.tt.mvp.presenter.impl;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.hz.tt.R;
import com.hz.tt.api.okHttpUtils.OkHttpUtils;
import com.hz.tt.app.MyApp;
import com.hz.tt.greendao.gen.OutBeanDao;
import com.hz.tt.mvp.model.entity.OutBean;
import com.hz.tt.mvp.model.entity.request.UpOutRequest;
import com.hz.tt.mvp.model.entity.response.OutResponse;
import com.hz.tt.mvp.presenter.base.BasePresenter;
import com.hz.tt.mvp.ui.common.BaseActivity;
import com.hz.tt.mvp.ui.view.IContactsFgView;
import com.hz.tt.util.UIUtils;
import com.lqr.adapter.LQRAdapterForRecyclerView;
import com.lqr.adapter.LQRViewHolderForRecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ContactsFgPresenter extends BasePresenter<IContactsFgView> {

    private LQRAdapterForRecyclerView mAdapter;
    private List<OutBean> datas= new ArrayList<>();
    private List<OutBean> removeDatas= new ArrayList<>();
    public ContactsFgPresenter(BaseActivity context) {
        super(context);
    }

    public void clearYesUp(){
        datas.removeAll(removeDatas);
    }
    public void getConversations() {
//        loadData();
//        this.datas=datas;
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
                        delete_img.setVisibility(View.VISIBLE);
                    }
                    states.setText(status);
                    int size=datas.size();
                    num.setText(size-position+"");
                }
            };
            getView().getRvContacts().setAdapter(mAdapter);
        }

    }

    public void addRecord(){
        if (datas!=null){
            OutBean bean=new OutBean();
            IContactsFgView view=getView();
            String Person=view.getRecPerson().getText().toString().trim();
            String Time=view.getRecDate().getText().toString().trim();
            String Code=view.getCode().getText().toString().trim();

            String Position="杭州";
            String outRecordSource="出库指令";
            String outRecordDate="2018-02-12 16:00:00";
            String outDate="2018-02-12 16:00:00";
            String outCustomer= "123456";

//            String Country=view.getCountry().getText().toString().trim();
//            String Birthday=view.getBirthday().getText().toString().trim();
//            String Capacity=view.getCapacity().getText().toString().trim();
//            String Year=view.getYear().getText().toString().trim();
//            String Num=view.getNum().getText().toString().trim();
//            String Location=view.getLocation().getText().toString().trim();
//            String Code=view.getCode().getText().toString().trim();
            if (!Time.equals("")
                    &&!Person.equals("")
                    &&!Code.equals("")
                    &&!Time.equals("")
                    &&!Position.equals("")
                    &&!outRecordSource.equals("")
                    &&!outRecordDate.equals("")
                    &&!outDate.equals("")
                    &&!outCustomer.equals("")
                    ){
                bean.setReceiveCustomer(Person);
                bean.setReceiveDate(Time);
                bean.setRecordCode(Code);
                bean.setPosition(Position);
                bean.setOutRecordSource(outRecordSource);
                bean.setOutDate(outDate);
                bean.setOutCustomer(outCustomer);
                bean.setOutRecordDate(outRecordDate);

                bean.setStatus("未上传");
                datas.add(0,bean);
                mAdapter.notifyDataSetChanged();

                OutBeanDao inBeanDao = MyApp.getInstances().getDaoSession().getOutBeanDao();
                inBeanDao.insert(bean);
            }else{
                UIUtils.showToast("请检查数据完整性！");
            }
        }
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
                    removeDatas.add(datas.get(0));
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
}
