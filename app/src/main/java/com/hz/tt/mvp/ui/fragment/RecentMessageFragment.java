package com.hz.tt.mvp.ui.fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.hz.tt.R;
import com.hz.tt.api.okHttpUtils.NetConstant;
import com.hz.tt.mvp.presenter.impl.RecentMessageFgPresenter;
import com.hz.tt.mvp.ui.activity.MainActivity;
import com.hz.tt.mvp.ui.activity.ScanActivity;
import com.hz.tt.mvp.ui.common.BaseFragment;
import com.hz.tt.mvp.ui.view.IRecentMessageFgView;
import com.hz.tt.util.BitmapUtils;
import com.hz.tt.util.DateUtil;
import com.hz.tt.util.UIUtils;
import com.hz.tt.widget.MyListViewInScrollView;
import com.hz.tt.widget.SelectDialog;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.Bind;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

import static android.app.Activity.RESULT_OK;

/**
 * @描述 最近会话列表界面
 */
public class RecentMessageFragment extends BaseFragment<IRecentMessageFgView, RecentMessageFgPresenter> implements IRecentMessageFgView {

    private boolean isFirst = true;

    Disposable disposable;
//    列表ListView
    @Bind(R.id.rvRecentMessage)
    MyListViewInScrollView mRvRecentMessage;



//入库员
    @Bind(R.id.tv_in)
    TextView tv_in;
    //入库员
    @Bind(R.id.tv_time)
    TextView tv_time;

    //    下拉
    @Bind(R.id.iv_type)
    ImageView iv_type;
    @Bind(R.id.iv_country)
    ImageView iv_country;
    @Bind(R.id.iv_birthplace)
    ImageView iv_birthplace;
    @Bind(R.id.iv_capacity)
    ImageView iv_capacity;
//输入文本框
    @Bind(R.id.et_type)
    EditText et_type;
    @Bind(R.id.et_country)
    EditText et_country;
    @Bind(R.id.et_birthplace)
    EditText et_birthplace;
    @Bind(R.id.et_capacity)
    EditText et_capacity;

    @Bind(R.id.et_year)
    EditText et_year;
    @Bind(R.id.et_num)
    EditText et_num;
    @Bind(R.id.et_location)
    EditText et_location;
//    扫描
    @Bind(R.id.iv_scan)
    ImageView iv_scan;

    @Bind(R.id.et_scan)
    EditText et_scan;
// 拍照
    @Bind(R.id.btn_picture)
    ImageButton btn_picture;
    private String bitmapPath;
    @Bind(R.id.img_close)
    ImageButton img_close;
//    添加上传
    @Bind(R.id.btn_add)
    Button btn_add;
    @Bind(R.id.btn_upload)
    Button btn_upload;

    @Override
    public void init() {
//        registerBR();
    }

    @Override
    public void initView(View rootView) {
//        datas.add(new InBean());
//        datas.add(new InBean());
//        datas.add(new InBean());
//        datas.add(new InBean());
//        datas.add(new InBean());
        mPresenter.getConversations();

        tv_in.setText(NetConstant.USER_NAME);
        SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        tv_time.setText(dateFormat.format(new Date()));

        iv_type.setOnClickListener(v -> selectDialog("type"));
        iv_country.setOnClickListener(v -> selectDialog("country"));
        iv_birthplace.setOnClickListener(v -> selectDialog("birthplace"));
        iv_capacity.setOnClickListener(v -> selectDialog("capacity"));
        iv_scan.setOnClickListener(v -> ((MainActivity) getActivity()).jumpToActivity(ScanActivity.class));
        btn_picture.setOnClickListener(v -> takePhoto("1"));
        mPresenter.speech("登录成功");

//        添加上传
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.addRecord(bitmapPath);
            }
        });

        btn_upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.upRecordImg();
            }
        });



    }

    @Override
    public void onResume() {
        super.onResume();
        if (NetConstant.SCAN_RESULT.equals("")){
            et_scan.setText(et_scan.getText());
        }else{
            et_scan.setText(NetConstant.SCAN_RESULT);
        }


        if (!isFirst) {
//            mPresenter.getConversations();
        }
    }

    @Override
    public void initData() {
//        UIUtils.postTaskDelay(() -> {
//        mPresenter.getConversations();
//        }, 1000);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
//        unRegisterBR();
    }

//    private void registerBR() {
//        BroadcastManager.getInstance(getActivity()).register(AppConst.UPDATE_CONVERSATIONS, new BroadcastReceiver() {
//            @Override
//            public void onReceive(Context context, Intent intent) {
//                mPresenter.getConversations();
//                isFirst = false;
//            }
//        });
//    }

//    private void unRegisterBR() {
//        BroadcastManager.getInstance(getActivity()).unregister(AppConst.UPDATE_CONVERSATIONS);
//    }

    @Override
    protected RecentMessageFgPresenter createPresenter() {
        return new RecentMessageFgPresenter((MainActivity) getActivity());
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.fragment_recent_message;
    }

//    @Override
//    public LQRRecyclerView getRvRecentMessage() {
//        return null;
//    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode ==0 && resultCode == RESULT_OK) {
            img_close.setVisibility(View.VISIBLE);
            showPic(btn_picture);
        }


    }
    /**
     * 执行拍照
     */
    private void takePhoto(String s) {
        String status = Environment.getExternalStorageState();
        if (status.equals(Environment.MEDIA_MOUNTED)) {
            File dir = new File(Environment.getExternalStorageDirectory() + "/StayWareHouseImage/");
            if (!dir.exists() && !dir.mkdirs())
                return;


            Intent openCameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            if (openCameraIntent.resolveActivity(getActivity().getPackageManager())== null) {
                UIUtils.showToast("打开相机失败！");
                return;
            }
            File file = new File(dir, DateUtil.format(new Date(), "yyyyMMddhhmmss") + "stayHorse_0" + s + ".jpg");
            bitmapPath = file.getPath();

            openCameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
            openCameraIntent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 0);
            startActivityForResult(openCameraIntent,0);
        } else {
//            speech("没有储存卡");
        }
    }

    /**
     * 显示拍照缩略图并压缩图片
     */
    private void showPic(final ImageButton btnPic) {
        if (!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
//            speech("外部存储不可用，请重新拍照");
            return;
        }
        Bitmap bitmap = BitmapUtils.getBitmapFromPath(bitmapPath);
        if (bitmap == null) {
            btnPic.setImageResource(R.mipmap.ic_photo);
            btnPic.setScaleType(ImageView.ScaleType.CENTER);
//            speech("请重新拍照！");
            return;
        }
        Bitmap showBitmap = ThumbnailUtils.extractThumbnail(bitmap, 200, 200);
        btnPic.setImageBitmap(showBitmap);
        btnPic.setScaleType(ImageView.ScaleType.FIT_XY);
        disposable= Observable.just(showBitmap)
                .map(bitmap1 -> BitmapUtils.compressImage(bitmap1, bitmapPath) != null)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean aBoolean) throws Exception {
                        if (aBoolean){
                            Log.i("TakePhoto", "图片压缩成功！");
                        }else{
                            Log.e("TakePhoto", "图片压缩失败：");
                            btnPic.setImageResource(R.mipmap.ic_photo);
//                speech("请重新拍照！");
                        }
                    }
                });
    }
    @Override
    public void onDetach() {
        super.onDetach();
        if (disposable!=null){
            disposable.dispose();
        }
    }

    public void selectDialog(String type){
        switch (type){
            case "type":
                SelectDialog.setOnResultListener(result -> et_type.setText(result));
                SelectDialog.initDialog(getActivity(),"品类选择","",R.array.CXH_list,0)
                .show();
                break;
            case "country":
                SelectDialog.setOnResultListener(result -> et_country.setText(result));
                SelectDialog.initDialog(getActivity(),"国家选择","",R.array.CXH_list0,0)
                        .show();
                break;
            case "birthplace":
                SelectDialog.setOnResultListener(result -> et_birthplace.setText(result));
                SelectDialog.initDialog(getActivity(),"产地选择","",R.array.CXH_list1,0)
                        .show();
                break;
            case "capacity":
                SelectDialog.setOnResultListener(result -> et_capacity.setText(result));
                SelectDialog.initDialog(getActivity(),"容量选择","",R.array.CXH_list2,0)
                        .show();
                break;
        }

    }

    @Override
    public MyListViewInScrollView getRvRecentMessage() {
        return mRvRecentMessage;
    }

    @Override
    public TextView getPerson() {
        return tv_in;
    }

    @Override
    public TextView getTime() {
        return tv_time;
    }

    @Override
    public EditText getType() {
        return et_type;
    }

    @Override
    public EditText getCountry() {
        return et_country;
    }

    @Override
    public EditText getBirthday() {
        return et_birthplace;
    }

    @Override
    public EditText getCapacity() {
        return et_capacity;
    }

    @Override
    public EditText getYear() {
        return et_year;
    }

    @Override
    public EditText getNum() {
        return et_num;
    }

    @Override
    public EditText getLocation() {
        return et_location;
    }

    @Override
    public EditText getCode() {
        return et_scan;
    }
}
