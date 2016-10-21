package com.mevv.vrecyclerview.adapter;

import android.view.View;

/**
 * Created by VV on 2016/10/21.
 */

public interface IHeaderFooter {
    View getHeaderView();

    View getFooterView();

    void addHeaderView(View header);

    void addFooterView(View footer);

    boolean removeHeaderView();

    boolean removeFooterView();

    boolean hasHeaderView();

    boolean hasFooterView();

    boolean isHeaderView(int position);

    boolean isFooterView(int position);
}
