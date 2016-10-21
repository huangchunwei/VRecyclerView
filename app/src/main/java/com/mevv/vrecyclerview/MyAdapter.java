package com.mevv.vrecyclerview;

import android.content.Context;
import android.graphics.Color;
import android.widget.TextView;

import org.byteam.superadapter.SuperAdapter;
import org.byteam.superadapter.SuperViewHolder;

import java.util.List;

/**
 * Created by VV on 2016/10/21.
*/

public class MyAdapter extends SuperAdapter<MyBean> {


    public MyAdapter(Context context, List<MyBean> items, int layoutResId) {
        super(context, items, layoutResId);
    }

    @Override
    public void onBind(SuperViewHolder holder, int viewType, int layoutPosition, MyBean item) {
        if (layoutPosition % 2 == 0){
            holder.setBackgroundColor(R.id.ll_rootView, Color.GREEN);
        }else{
            holder.setBackgroundColor(R.id.ll_rootView, Color.YELLOW);
        }

        TextView tvid = holder.findViewById(R.id.tv_id);
        TextView tvName = holder.findViewById(R.id.tv_name);
        tvid.setText(item.getId()+"");
        tvName.setText(item.getName());
    }
}
