package com.hz.tt.mvp.ui.view;

import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

/**
 * Created by Administrator on 2018-04-17.
 */

public interface IUserEvaluateDetailAtView {
    TextView getType();
    TextView getCountry();
    TextView getBirthday();
    TextView getCapacity();
    TextView getYear();
    TextView getNum();
    TextView getPosition();
    ImageView getImgV();
    TextView getName();
    TextView getCode();
    RatingBar getRatingBar();
}
