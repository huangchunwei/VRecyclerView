package com.mevv.vrecyclerview.adapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.widget.AdapterView;

import com.mevv.vrecyclerview.adapter.vh.SupterVH;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by VV on 2016/10/21.
 */

public abstract class VRecyclerAdapter<T> extends RecyclerView.Adapter<SupterVH>
        implements IViewBindData<T, SupterVH>, ILayoutManager, IHeaderFooter {

    protected RecyclerView mRecyclerView;

    protected Context mContext;
    protected List<T> mData;
    protected int mLayoutResId;
    protected OnItemClickListener mOnItemClickListener;
    protected OnItemLongClickListener mOnItemLongClickListener;

    protected static final int TYPE_HEADER = -0x100;
    protected static final int TYPE_FOOTER = -0x101;
    protected View mHeader;
    protected View mFooter;

    private Interpolator mInterpolator = new LinearInterpolator();
    private long mDuration = 300;
    private boolean mLoadAnimationEnabled;
    private boolean mOnlyOnce = true;
    //    private BaseAnimation mLoadAnimation;
    private int mLastPosition = -1;


    public VRecyclerAdapter(Context context, List<T> list, int layoutResId) {
        this.mContext = context;
        this.mData = list == null ? new ArrayList<T>() : list;
        this.mLayoutResId = layoutResId;
//        this.mMulItemViewType = null;
    }

   /* public VRecyclerAdapter(Context context, List<T> list, IMulItemViewType<T> mulItemViewType) {
        this.mContext = context;
        this.mData = list == null ? new ArrayList<T>() : list;
        this.mMulItemViewType = mulItemViewType == null ? offerMultiItemViewType() : mulItemViewType;
    }*/


    @Override
    public SupterVH onCreateViewHolder(ViewGroup parent, final int viewType) {
        final SupterVH holder;
        if (viewType == TYPE_HEADER && hasHeaderView()) {
            return new SupterVH(getHeaderView());
        } else if (viewType == TYPE_FOOTER && hasFooterView()) {
            return new SupterVH(getFooterView());
        } else {
            holder = onCreate(null, parent, viewType);
        }
        //设置点击事件
        if (!(holder.itemView instanceof AdapterView)) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mOnItemClickListener != null) {
                        mOnItemClickListener.onItemClick(v, viewType, holder.getAdapterPosition());
                    }
                }
            });
            //设置长点击事件
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    if (mOnItemLongClickListener != null) {
                        mOnItemLongClickListener.onItemLongClick(v, viewType, holder.getAdapterPosition());
                        return true;
                    }
                    return false;
                }
            });
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(SupterVH holder, int position, List<Object> payloads) {
        super.onBindViewHolder(holder, position, payloads);
    }

    @Override
    public void onBindViewHolder(SupterVH holder, int position) {
        int viewType = getItemViewType(position);
        if (viewType != TYPE_HEADER && viewType != TYPE_FOOTER) {
            onBind(holder, viewType, position, mData.get(hasHeaderView() ? --position : position));
//            addLoadAnimation(holder); // Load animation
        }
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    @Override
    public int getItemViewType(int position) {
        int viewType;
        if (isHeaderView(position)) {
            viewType = TYPE_HEADER;
        } else if (isFooterView(position)) {
            viewType = TYPE_FOOTER;
        } else {
            /*if (mMulItemViewType != null) {
                if (hasHeaderView()) {
                    position--;
                }
                return mMulItemViewType.getItemViewType(position, mData.get(position));
            }*/
            return 0;
        }
        return viewType;
    }

    /**
     * ----------------------------header And Footer---------------------------------
     */

    @Override
    public void onViewAttachedToWindow(SupterVH holder) {
        if (isHeaderView(holder.getLayoutPosition()) || isFooterView(holder.getLayoutPosition())) {
            ViewGroup.LayoutParams lp = holder.itemView.getLayoutParams();
            if (lp != null && lp instanceof StaggeredGridLayoutManager.LayoutParams) {
                ((StaggeredGridLayoutManager.LayoutParams) lp).setFullSpan(true);
            }
        }
    }

    @Override
    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        mRecyclerView = null;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        if (mRecyclerView != null && mRecyclerView != recyclerView)
            Log.i("hate", "不支持多item类型");
        mRecyclerView = recyclerView;
        ifGridLayoutManager();
    }

    private void ifGridLayoutManager() {
        if (hasHeaderView() || hasFooterView()) {
            final RecyclerView.LayoutManager layoutManager = getLayoutManager();
            if (layoutManager instanceof GridLayoutManager) {
                final GridLayoutManager.SpanSizeLookup originalSpanSizeLookup =
                        ((GridLayoutManager) layoutManager).getSpanSizeLookup();
                ((GridLayoutManager) layoutManager).setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                    @Override
                    public int getSpanSize(int position) {
                        return (isHeaderView(position) || isFooterView(position)) ?
                                ((GridLayoutManager) layoutManager).getSpanCount() :
                                originalSpanSizeLookup.getSpanSize(position);
                    }
                });
            }
        }
    }

    @Override
    public RecyclerView.LayoutManager getLayoutManager() {
        return hasLayoutManager() ? mRecyclerView.getLayoutManager() : null;
    }

    /**
     * -----------------------boolean----------------------------------
     */
    @Override
    public boolean hasHeaderView() {
        return getHeaderView() != null;
    }

    @Override
    public boolean hasFooterView() {
        return getFooterView() != null;
    }

    @Override
    public boolean isHeaderView(int position) {
        return hasHeaderView() && position == 0;
    }

    @Override
    public boolean isFooterView(int position) {
        return hasFooterView() && position == getItemCount() - 1;
    }

    @Override
    public boolean hasLayoutManager() {
        return mRecyclerView != null && mRecyclerView.getLayoutManager() != null;
    }

    @Override
    public void addHeaderView(View header) {
        if (hasHeaderView())
            throw new IllegalStateException("You have already added a header view.");
        mHeader = header;
        setLayoutParams(mHeader);
        ifGridLayoutManager();
        notifyItemInserted(0);
    }

    @Override
    public void addFooterView(View footer) {
        if (hasFooterView())
            throw new IllegalStateException("You have already added a footer view.");
        mFooter = footer;
        setLayoutParams(mFooter);
        ifGridLayoutManager();
        notifyItemInserted(getItemCount() - 1);
    }

    @Override
    public boolean removeHeaderView() {
        if (hasHeaderView()) {
            mHeader = null;
            notifyItemRemoved(0);
            return true;
        }
        return false;
    }

    @Override
    public boolean removeFooterView() {
        if (hasFooterView()) {
            int footerPosition = getItemCount() - 1;
            mFooter = null;
            notifyItemRemoved(footerPosition);
            return true;
        }
        return false;
    }



    /**
     * ----------------------------get And set-------------------------------------
     */


    private void setLayoutParams(View view) {
        if (hasHeaderView() || hasFooterView()) {
            RecyclerView.LayoutManager layoutManager = getLayoutManager();
            if (layoutManager instanceof StaggeredGridLayoutManager) {
                view.setLayoutParams(new StaggeredGridLayoutManager.LayoutParams(
                        StaggeredGridLayoutManager.LayoutParams.MATCH_PARENT,
                        StaggeredGridLayoutManager.LayoutParams.WRAP_CONTENT));
            } else if (layoutManager instanceof GridLayoutManager) {
                view.setLayoutParams(new GridLayoutManager.LayoutParams(
                        GridLayoutManager.LayoutParams.MATCH_PARENT,
                        GridLayoutManager.LayoutParams.WRAP_CONTENT));
            } else {
                view.setLayoutParams(new RecyclerView.LayoutParams(
                        RecyclerView.LayoutParams.MATCH_PARENT,
                        RecyclerView.LayoutParams.WRAP_CONTENT));
            }
        }
    }


    @Override
    public View getHeaderView() {
        return null;
    }

    @Override
    public View getFooterView() {
        return null;
    }

    public Context getContext() {
        return mContext;
    }

    public List<T> getData() {
        return mData;
    }

    public void setData(List<T> data) {
        mData = data;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener onItemLongClickListener) {
        this.mOnItemLongClickListener = onItemLongClickListener;
    }

    /**-------------------------Load Animator-------------------------------*/


}
