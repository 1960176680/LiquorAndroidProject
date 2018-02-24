package com.hz.tt.mvp.ui.fragment.workrecord;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.hz.tt.R;
import com.hz.tt.mvp.presenter.impl.UnUpLoadFgPresenter;
import com.hz.tt.mvp.ui.activity.WorkRecordActivity;
import com.hz.tt.mvp.ui.common.BaseFragment;
import com.hz.tt.mvp.ui.view.IUnUpLoadFgView;

/**
 * 未上传数据列表
 * Created by ZhouWenGuang
 */

public class UnUpLoadDataFragment extends BaseFragment<IUnUpLoadFgView, UnUpLoadFgPresenter> implements IUnUpLoadFgView {
//    TextView total_count;
//    RecyclerView recyclerView;
//    Button btn_upload;
//    private List<NoUploadData> dataList;
//    private UnUpLoadDataAdapter adapter;
//    private UnUpLoadDataFragmentController controller;
//    private CountDownTimer timer;
//    private String OldCount;
//    private String NewCount;
//
//    @Override
//    protected void afterView(View view) {
//    }

//    @Override
//    protected int initLayout() {
//        return R.layout.fragment_unupload_data;
//    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);
//        initFragmentView(view);
//        initFragmentData();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
//        if (isVisibleToUser) {
//            OldCount = OperationDataUtil.getAllUnUploadDataNum();
//            timer = new CountDownTimer(300000, 6000) {
//                @Override
//                public void onTick(long millisUntilFinished) {
//                    NewCount = OperationDataUtil.getAllUnUploadDataNum();
//                    if (NewCount.equals("0")){
//                        if ((MenuActivity) getActivity()!=null){
//                            ((MenuActivity) getActivity()).red_point1.setVisibility(View.INVISIBLE);
//                        }
//                    }
//                    if (!OldCount.equals(NewCount)) {
//                        OldCount = NewCount;
//                        initFragmentData();
//                    }
//                }
//
//                @Override
//                public void onFinish() {
//                }
//            }.start();
//        } else {
//            if (timer != null) {
//                timer.cancel();
//            }
//        }

    }

    private void initFragmentView(View view) {
//        total_count = (TextView) view.findViewById(R.id.total_count);
//        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
//        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
//        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(),
//                DividerItemDecoration.VERTICAL));
//        btn_upload = (Button) view.findViewById(R.id.btn_upload);
//        btn_upload.setOnClickListener(this);
    }

    public void initFragmentData() {
//        try {
//            if (total_count == null)return;
//            total_count.setText(Integer.valueOf(OperationDataUtil.getAllUnUploadDataNum())<0?0+"":OperationDataUtil.getAllUnUploadDataNum());
//            dataList = new ArrayList<>();
//            adapter = new UnUpLoadDataAdapter(getActivity(), dataList);
//            controller = new UnUpLoadDataFragmentController(adapter, dataList);
//            controller.ininAllData();
//            recyclerView.setAdapter(adapter);
//        }catch (Exception e)
//        {
//            e.printStackTrace();
//        }
    }

//    @Override
//    public void onClick(View v) {
//        int id = v.getId();
////        switch (id) {
////            case R.id.btn_upload:
////                if (total_count.getText().equals("0")) {
////                    ToastTools.showLazzToast("没有可上传的数据！");
////                    return;
////                }
////                ContextViewUtils viewUtils = new ContextViewUtils(getActivity());
////                viewUtils.dialogCommon("提示", "确定要上传记录吗？", true, new View.OnClickListener() {
////                    @Override
////                    public void onClick(View v) {
////                        upLoadDataList();
////                    }
////                });
////
////                break;
////        }
//    }

    private void upLoadDataList() {
//        ToastTools.showToast(R.string.start_upload_data);
//        Intent intent = new Intent("com.mainmodule.upload.service.ACTION_UPLOAD");
//        mActivity.sendBroadcast(intent);
    }

    @Override
    protected UnUpLoadFgPresenter createPresenter() {
        return new UnUpLoadFgPresenter(((WorkRecordActivity) getActivity()));
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.fragment_unuprecord;
    }
}
