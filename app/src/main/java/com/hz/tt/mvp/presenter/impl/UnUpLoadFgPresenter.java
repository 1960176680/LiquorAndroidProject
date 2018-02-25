package com.hz.tt.mvp.presenter.impl;

import android.widget.ImageView;
import android.widget.TextView;

import com.hz.tt.R;
import com.hz.tt.app.MyApp;
import com.hz.tt.greendao.gen.InBeanDao;
import com.hz.tt.greendao.gen.OutBeanDao;
import com.hz.tt.mvp.model.entity.InBean;
import com.hz.tt.mvp.model.entity.OutBean;
import com.hz.tt.mvp.model.entity.RecordDataBean;
import com.hz.tt.mvp.presenter.base.BasePresenter;
import com.hz.tt.mvp.ui.common.BaseActivity;
import com.hz.tt.mvp.ui.view.IUnUpLoadFgView;
import com.lqr.adapter.LQRAdapterForRecyclerView;
import com.lqr.adapter.LQRViewHolderForRecyclerView;

import java.util.ArrayList;
import java.util.List;

import static com.hz.tt.R.id.tvCount;

/**
 * Created by Administrator on 2018-02-24.
 */

public class UnUpLoadFgPresenter extends BasePresenter<IUnUpLoadFgView> {
    private List<RecordDataBean> dataBeenList=new ArrayList<>();
    private LQRAdapterForRecyclerView mAdapter;
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
}
