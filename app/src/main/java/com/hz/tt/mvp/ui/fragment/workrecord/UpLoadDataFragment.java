package com.hz.tt.mvp.ui.fragment.workrecord;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.hz.tt.R;
import com.hz.tt.mvp.presenter.impl.UpLoadFgPresenter;
import com.hz.tt.mvp.ui.activity.WorkRecordActivity;
import com.hz.tt.mvp.ui.common.BaseFragment;
import com.hz.tt.mvp.ui.view.IUpLoadFgView;

/**
 * Created by ZhouWenGuang on 2017/7/10.
 */

public class UpLoadDataFragment extends BaseFragment<IUpLoadFgView, UpLoadFgPresenter> implements IUpLoadFgView{
//    private RelativeLayout select_start_date;
//    private TextView tv_date;
//    private RecyclerView recyclerView;
//    private TimeSelector timeSelector;
//    private UpLoadDataAdapter upLoadDataAdapter;
//    private List<Map<String,String>> dataList;
//    private UpLoadDataFragmentController controller;
//    private String currentDate="";
//    @Override
//    protected void afterView(View view) {}
//    @Override
//    protected int initLayout() {
//        return R.layout.fragment_upload_data;
//    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//            initFragmentView(view);
//            initFragmentData();
    }



    public void initFragmentView(View view) {
//        select_start_date= (RelativeLayout) view.findViewById(R.id.select_start_date);
//        select_start_date.setOnClickListener(this);
//        tv_date= (TextView) view.findViewById(R.id.tv_date);
//        recyclerView= (RecyclerView) view.findViewById(R.id.recyclerView);
//        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
//        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(),
//                DividerItemDecoration.VERTICAL));
    }

    @Override
    protected UpLoadFgPresenter createPresenter() {
        return new UpLoadFgPresenter(((WorkRecordActivity) getActivity()));
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.fragment_uprecord;
    }
//    public void initFragmentData() {
//        SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
//        if (currentDate.equals("")){
//            currentDate=dateFormat.format(new Date());
//        }
//        if (tv_date!=null){
//            tv_date.setText(currentDate);
//        }
//        /**
//         * inflate data
//         */
//        dataList=new ArrayList<>();
//        upLoadDataAdapter=new UpLoadDataAdapter(getActivity(),dataList);
//        controller=new UpLoadDataFragmentController(upLoadDataAdapter,dataList);
//        controller.ininAllData(currentDate);
//        recyclerView.setAdapter(upLoadDataAdapter);
//        upLoadDataAdapter.setOnItemClickLitener(new UpLoadDataAdapter.OnItemClickLitener() {
//            @Override
//            public void onItemClick(View view, int position) {
//                Intent intent=new Intent(getContext(), HistoryRecordListActivity.class);
//                intent.putExtra("flag",position);
//                intent.putExtra("date",tv_date.getText());
//                startActivity(intent);
////                ToastTools.showLazzToast(position+"");
//            }
//        });
//    }
//    @Override
//    public void onClick(View v) {
//        int id=v.getId();
//        switch (id){
//            case R.id.select_start_date:
//                timeSelector = new TimeSelector(getActivity(), new TimeSelector.ResultHandler() {
//                    @Override
//                    public void handle(String time) {
//                        currentDate=time;
//                        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
//                        try {
////                            Date Adate=format.parse(currentDate);
//                            Date Bdate=format.parse(time);
//                            int startAndEnd= Util.getGapCount(Bdate,new Date());
//                            if (startAndEnd>15){
//                                ToastTools.showLazzToast("最多只能查询15天前的记录");
//                                return;
//                            }
//                            tv_date.setText(time);
//                            queryUploadData(time);
//                        } catch (ParseException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                },"2017-01-01 00:00", "2022-12-31 00:00");
//                timeSelector.show();
//                break;
//        }
//
//    }

//    public  void queryUploadData(String date) {
//        initFragmentData();
//    }
}
