package com.hjq.shape.layout;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

import com.hjq.shape.R;
import com.hjq.shape.builder.ShapeDrawableBuilder;
import com.hjq.shape.config.IGetShapeDrawableBuilder;
import com.hjq.shape.styleable.ShapeRecyclerViewStyleable;

/**
 *    author : Android 轮子哥
 *    github : https://github.com/getActivity/ShapeView
 *    time   : 2021/07/17
 *    desc   : 支持直接定义 Shape 背景的 RecyclerView
 */
public class ShapeRecyclerView extends RecyclerView implements IGetShapeDrawableBuilder {

    private static final ShapeRecyclerViewStyleable STYLEABLE = new ShapeRecyclerViewStyleable();

    private final ShapeDrawableBuilder mShapeDrawableBuilder;

    public ShapeRecyclerView(Context context) {
        this(context, null);
    }

    public ShapeRecyclerView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ShapeRecyclerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ShapeRecyclerView);
        mShapeDrawableBuilder = new ShapeDrawableBuilder(this, typedArray, STYLEABLE);
        typedArray.recycle();

        mShapeDrawableBuilder.intoBackground();
    }

    @Override
    public ShapeDrawableBuilder getShapeDrawableBuilder() {
        return mShapeDrawableBuilder;
    }
}