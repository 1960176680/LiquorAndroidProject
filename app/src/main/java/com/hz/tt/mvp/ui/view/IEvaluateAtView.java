package com.hz.tt.mvp.ui.view;

import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

/**
 * Created by Administrator on 2018-04-10.
 */

public interface IEvaluateAtView {
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
    RatingBar getRatingBarLike();
    EditText getSuggestPerson();
    EditText getSuggestArea();
}
