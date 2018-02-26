package com.hz.tt.mvp.ui.view;


import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.hz.tt.widget.MyListViewInScrollView;

public interface IContactsFgView {
    EditText getRecPerson();
    TextView getRecDate();
    EditText getCode();
    TextView getRemak();
    Button getDoType();

    TextView getType();
    TextView getCountry();
    TextView getBirthday();
    TextView getCapacity();
    TextView getYear();
    TextView getNum();
    TextView getPosition();
    EditText getOutNum();
    ImageView getImgV();
//    View getHeaderView();
//
    MyListViewInScrollView getRvContacts();
//
//    TextView getFooterView();
}
