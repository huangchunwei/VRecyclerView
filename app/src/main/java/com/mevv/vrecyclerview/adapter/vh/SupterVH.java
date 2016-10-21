package com.mevv.vrecyclerview.adapter.vh;

import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.ColorFilter;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.annotation.FloatRange;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.text.method.MovementMethod;
import android.util.SparseArray;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.Checkable;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;

/**
 * Created by VV on 2016/10/21.
 */

public class SupterVH extends RecyclerView.ViewHolder implements ChainSetter<SupterVH> {

    private SparseArray<View> childViews = new SparseArray<>();

    public SupterVH(View itemView) {
        super(itemView);
    }

    public static SupterVH get(View convertView, View itemView) {
        SupterVH holder;
        if (convertView == null) {
            holder = new SupterVH(itemView);
            convertView = itemView;
            convertView.setTag(holder);
        } else {
            holder = (SupterVH) convertView.getTag();
        }
        return holder;
    }

    /**
     * Deprecated. Use {@link #findViewById(int)} instead for a better understanding.
     */
    @Deprecated
    public <T extends View> T getView(int id) {
        return findViewById(id);
    }

    @SuppressWarnings("unchecked")
    public <T extends View> T findViewById(int id) {
        View childView = childViews.get(id);
        if (childView == null) {
            childView = itemView.findViewById(id);
            if (childView != null)
                childViews.put(id, childView);
        }
        return (T) childView;
    }

    @Override
    public SupterVH setText(int viewId, CharSequence text) {
        TextView textView = findViewById(viewId);
        textView.setText(text);
        return this;
    }

    @Override
    public SupterVH setTextColor(int viewId, int textColor) {
        TextView view = findViewById(viewId);
        view.setTextColor(textColor);
        return this;
    }

    @Override
    public SupterVH setTextColor(int viewId, ColorStateList colorStateList) {
        TextView view = findViewById(viewId);
        view.setTextColor(colorStateList);
        return this;
    }

    @Override
    public SupterVH setMovementMethod(int viewId, MovementMethod method) {
        TextView textView = findViewById(viewId);
        textView.setMovementMethod(method);
        return this;
    }

    @Override
    public SupterVH setImageResource(int viewId, int imgResId) {
        ImageView view = findViewById(viewId);
        view.setImageResource(imgResId);
        return this;
    }

    @Override
    public SupterVH setImageDrawable(int viewId, Drawable drawable) {
        ImageView view = findViewById(viewId);
        view.setImageDrawable(drawable);
        return this;
    }

    @Override
    public SupterVH setImageBitmap(int viewId, Bitmap bitmap) {
        ImageView view = findViewById(viewId);
        view.setImageBitmap(bitmap);
        return this;
    }

    @Override
    public SupterVH setImageUri(int viewId, Uri imageUri) {
        ImageView view = findViewById(viewId);
        view.setImageURI(imageUri);
        return this;
    }

    @Override
    public SupterVH setScaleType(int viewId, ImageView.ScaleType type) {
        ImageView view = findViewById(viewId);
        view.setScaleType(type);
        return this;
    }

    @Override
    public SupterVH setBackgroundColor(int viewId, int bgColor) {
        View view = findViewById(viewId);
        view.setBackgroundColor(bgColor);
        return this;
    }

    @Override
    public SupterVH setBackgroundResource(int viewId, int bgRes) {
        View view = findViewById(viewId);
        view.setBackgroundResource(bgRes);
        return this;
    }

    @Override
    public SupterVH setColorFilter(int viewId, ColorFilter colorFilter) {
        ImageView view = findViewById(viewId);
        view.setColorFilter(colorFilter);
        return this;
    }

    @Override
    public SupterVH setColorFilter(int viewId, int colorFilter) {
        ImageView view = findViewById(viewId);
        view.setColorFilter(colorFilter);
        return this;
    }

    @Override
    public SupterVH setAlpha(int viewId, @FloatRange(from = 0.0, to = 1.0) float value) {
        View view = findViewById(viewId);
        ViewCompat.setAlpha(view, value);
        return this;
    }

    @Override
    public SupterVH setVisibility(int viewId, int visibility) {
        View view = findViewById(viewId);
        view.setVisibility(visibility);
        return this;
    }

    @Override
    public SupterVH setMax(int viewId, int max) {
        ProgressBar view = findViewById(viewId);
        view.setMax(max);
        return this;
    }

    @Override
    public SupterVH setProgress(int viewId, int progress) {
        ProgressBar view = findViewById(viewId);
        view.setProgress(progress);
        return this;
    }

    @Override
    public SupterVH setRating(int viewId, float rating) {
        RatingBar view = findViewById(viewId);
        view.setRating(rating);
        return this;
    }

    @Override
    public SupterVH setTag(int viewId, Object tag) {
        View view = findViewById(viewId);
        view.setTag(tag);
        return this;
    }

    @Override
    public SupterVH setTag(int viewId, int key, Object tag) {
        View view = findViewById(viewId);
        view.setTag(key, tag);
        return this;
    }

    @Override
    public SupterVH setEnabled(int viewId, boolean enabled) {
        View view = findViewById(viewId);
        view.setEnabled(enabled);
        return this;
    }

    @Override
    public SupterVH setAdapter(int viewId, Adapter adapter) {
        AdapterView<Adapter> view = findViewById(viewId);
        view.setAdapter(adapter);
        return this;
    }

    @Override
    public SupterVH setAdapter(int viewId, RecyclerView.Adapter adapter) {
        RecyclerView view = findViewById(viewId);
        view.setAdapter(adapter);
        return this;
    }

    @Override
    public SupterVH setChecked(int viewId, boolean checked) {
        Checkable view = findViewById(viewId);
        view.setChecked(checked);
        return this;
    }

    @Override
    public SupterVH setOnClickListener(int viewId, View.OnClickListener listener) {
        findViewById(viewId).setOnClickListener(listener);
        return this;
    }

    @Override
    public SupterVH setOnLongClickListener(int viewId, View.OnLongClickListener listener) {
        findViewById(viewId).setOnLongClickListener(listener);
        return this;
    }

    @Override
    public SupterVH setOnTouchListener(int viewId, View.OnTouchListener listener) {
        findViewById(viewId).setOnTouchListener(listener);
        return this;
    }
}
