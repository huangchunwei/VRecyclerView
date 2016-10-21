package com.mevv.vrecyclerview.adapter;

import android.view.View;
import android.view.ViewGroup;

/**
 * Created by VV on 2016/10/21.
 */

public interface IViewBindData<T, VH> {

    VH onCreate(View convertView, ViewGroup parent, int viewType);

    void onBind(VH holder, int viewType, int layoutPosition, T item);
}
