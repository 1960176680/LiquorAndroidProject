package com.hz.tt.mvp.ui.view;


import android.widget.EditText;
import android.widget.TextView;

import com.hz.tt.widget.MyListViewInScrollView;

public interface IContactsFgView {
    EditText getRecPerson();
    TextView getRecDate();
    EditText getCode();
    TextView getRemak();


    TextView getType();
    TextView getCountry();
    TextView getBirthday();
    TextView getCapacity();
    TextView getYear();
    TextView getNum();
    TextView getPosition();

//    View getHeaderView();
//
    MyListViewInScrollView getRvContacts();
//
//    TextView getFooterView();
}
