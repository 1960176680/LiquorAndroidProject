package com.hz.tt.mvp.ui.view;


import android.widget.EditText;

import com.hz.tt.widget.MyListViewInScrollView;

public interface IDiscoveryFgView {

    EditText getEtCountryV();
    EditText getEtBirthplaceV();
    EditText getEtTypeV();
    EditText getEtCapacityV();
    EditText getEtYearV();
    EditText getName();
    MyListViewInScrollView getRvRecyclerView();

}
