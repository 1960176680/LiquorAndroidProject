package com.hz.tt.widget;

import android.content.Context;
import android.util.AttributeSet;

import com.lqr.recyclerview.LQRRecyclerView;

/**
 * Created by Administrator on 2016-08-15.
 */

public class MyListViewInScrollView extends LQRRecyclerView {
    public MyListViewInScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyListViewInScrollView(Context context) {
        super(context);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}
