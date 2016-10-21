package com.mevv.vrecyclerview;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

/**
 * Created by VV on 2016/10/12.
 * 自定义RecyclerView
 */

public class VRecyclerView extends RecyclerView {
    public VRecyclerView(Context context) {
        super(context);
    }

    public VRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public VRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }
}
