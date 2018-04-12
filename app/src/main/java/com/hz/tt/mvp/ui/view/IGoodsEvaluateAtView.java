package com.hz.tt.mvp.ui.view;

import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.lqr.recyclerview.LQRRecyclerView;

/**
 * Created by Administrator on 2018-04-11.
 */

public interface IGoodsEvaluateAtView {


    EditText getCode();
    TextView getType();
    TextView getCountry();
    TextView getBirthday();
    TextView getCapacity();
    TextView getYear();
    TextView getNum();
    TextView getPosition();
    ImageView getImgV();
    TextView getName();
    RatingBar getRatingBar();
    //
    LQRRecyclerView getRecycleView();
}
