package com.hz.tt.mvp.ui.view;

import android.widget.EditText;
import android.widget.TextView;

import com.hz.tt.widget.MyListViewInScrollView;

public interface IRecentMessageFgView {
    MyListViewInScrollView getRvRecentMessage();
    TextView getPerson();
    TextView getTime();
    EditText getName();
    EditText getType();
    EditText getCountry();
    EditText getBirthday();
    EditText getCapacity();
    EditText getYear();
    EditText getNum();
    EditText getLocation();
    EditText getCode();
}
