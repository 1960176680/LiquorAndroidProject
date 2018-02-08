package com.hz.tt.widget;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;

/**
 * Created by ZhouWenGuang
 */

public class SelectDialog {
    private static OnItemClickListener mOnResultListener;

    public static AlertDialog  initDialog(final Context context, String title, String message, final int strList, int resId){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        if (title!=null&&!title.equals("")){
            builder.setTitle(title);
        }
        if (message!=null&&!message.equals("")){
            builder.setMessage(message);
        }
        if (strList!=0){
            builder.setItems(strList, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    String[] s=context.getResources().getStringArray(strList);
                    mOnResultListener.onResult(s[which]);

                }
            });
        }

        if (resId!=0){
            View inflater=LayoutInflater.from(context).inflate(resId,null);
            builder.setView(inflater);
        }

//        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int id) {
//                        dialog.dismiss();
//                    }
//                });
//        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int id) {
//
//                    }
//                });
        return builder.create();

    }



    public static void setOnResultListener(OnItemClickListener onResultListener){
        mOnResultListener=onResultListener;
    }

    public interface OnItemClickListener{
         void onResult(String result);
    }
}
