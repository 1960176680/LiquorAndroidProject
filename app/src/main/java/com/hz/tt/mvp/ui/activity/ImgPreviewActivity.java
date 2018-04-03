package com.hz.tt.mvp.ui.activity;

import android.app.Dialog;
import android.content.Intent;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.hz.tt.R;
import com.hz.tt.api.okHttpUtils.NetConstant;
import com.hz.tt.mvp.presenter.impl.ImgPreviewAtPresenter;
import com.hz.tt.mvp.ui.common.BaseActivity;
import com.hz.tt.mvp.ui.view.IImgPreviewAtView;
import com.hz.tt.util.AndroidShare;
import com.hz.tt.util.TextUtil;
import com.hz.tt.util.UIUtils;

import butterknife.Bind;

/**
 * Created by Administrator on 2018-04-02.
 */

public class ImgPreviewActivity extends BaseActivity<IImgPreviewAtView,ImgPreviewAtPresenter> implements IImgPreviewAtView,View.OnClickListener {
    @Bind(R.id.ivImg)
    ImageView ivImg;


    private Button btnQQFrient;
    private Button btnWeChatFrient;
//    private Button btnQQCircle;
    private Button btnWeChatCircle;
    private Button cancel;



    private View inflate;

    private Dialog dialog;
    private AndroidShare androidShare;
    @Override
    public void initView() {
         setToolbarTitle("图片预览");
    }

    @Override
    public void initData() {
        Intent intent=getIntent();
        String imgUrl=intent.getStringExtra("url");
        if (!TextUtil.isEmpty(imgUrl)){
            Glide.with(this).load(NetConstant.RESPONSE_URL_IMG+imgUrl).centerCrop().into(ivImg);
        }else{
            ivImg.setImageResource(R.mipmap.ic_launcher);
        }

    }

    @Override
    public void initListener() {
        ivImg.setOnLongClickListener(v -> {
            show();
            androidShare=new AndroidShare(ImgPreviewActivity.this);
            return true;
        });
    }

    @Override
    protected ImgPreviewAtPresenter createPresenter() {
        return new ImgPreviewAtPresenter(this);
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_imgpreview;
    }

    public void show(){
        dialog = new Dialog(this,R.style.ActionSheetDialogStyle);
        inflate = LayoutInflater.from(this).inflate(R.layout.dialog_layout, null);
        btnQQFrient = (Button) inflate.findViewById(R.id.btnQQFrient);
        btnWeChatFrient = (Button) inflate.findViewById(R.id.btnWeChatFrient);
//        btnQQCircle = (Button) inflate.findViewById(R.id.btnQQCircle);
        btnWeChatCircle = (Button) inflate.findViewById(R.id.btnWeChatCircle);

        cancel = (Button) inflate.findViewById(R.id.btn_cancel);
        btnQQFrient.setOnClickListener(this);
        btnWeChatFrient.setOnClickListener(this);
//        btnQQCircle.setOnClickListener(this);
        btnWeChatCircle.setOnClickListener(this);
        cancel.setOnClickListener(this);
        dialog.setContentView(inflate);
        Window dialogWindow = dialog.getWindow();
        dialogWindow.setGravity( Gravity.BOTTOM);
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.y = 20;
        UIUtils.GetInfo(ImgPreviewActivity.this);
        lp.width=1000;
        dialogWindow.setAttributes(lp);
        dialog.show();
    }
    @Override
    public void onClick(View v) {
        ivImg.setDrawingCacheEnabled(true);
        switch (v.getId()){
            case R.id.btnQQFrient:
                androidShare.shareQQFriend("测试","分享图片",AndroidShare.DRAWABLE,ivImg.getDrawingCache());

                break;
            case R.id.btnWeChatFrient:
                androidShare.shareWeChatFriend("测试","分享图片",AndroidShare.DRAWABLE,ivImg.getDrawingCache());

                break;
//            case btnQQCircle:
//                androidShare.shareQQFriendCircle("测试","分享图片",AndroidShare.DRAWABLE,ivImg.getDrawingCache());
////                androidShare.sharedQQ(ImgPreviewActivity.this,ivImg.getDrawingCache());
//
//                break;
            case R.id.btnWeChatCircle:
                androidShare.shareWeChatFriendCircle("测试","分享图片",ivImg.getDrawingCache());

                break;
            case R.id.btn_cancel:
                dialog.dismiss();
                break;
            default:
                ivImg.setDrawingCacheEnabled(false);
        }
    }
}
