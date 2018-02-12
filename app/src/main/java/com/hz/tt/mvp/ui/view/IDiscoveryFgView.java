package com.hz.tt.mvp.ui.view;


import android.widget.EditText;
import android.widget.ImageView;

import com.lqr.adapter.LQRAdapterForRecyclerView;
import com.lqr.recyclerview.LQRRecyclerView;

public interface IDiscoveryFgView {
    ImageView getQueryV();
    EditText getEtScanV();
    ImageView getIvScanV();
    LQRRecyclerView getRvRecyclerView();

}
