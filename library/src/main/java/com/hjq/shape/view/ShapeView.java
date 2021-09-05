package com.hjq.shape.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;

import com.hjq.shape.R;
import com.hjq.shape.builder.ShapeDrawableBuilder;
import com.hjq.shape.styleable.ShapeViewStyleable;

/**
 *    author : Android 轮子哥
 *    github : https://github.com/getActivity/ShapeView
 *    time   : 2021/07/17
 *    desc   : 支持直接定义 Shape 背景的 View
 */
public class ShapeView extends View {

    private static final ShapeViewStyleable STYLEABLE = new ShapeViewStyleable();

    private final ShapeDrawableBuilder mShapeDrawableBuilder;

    public ShapeView(Context context) {
        this(context, null);
    }

    public ShapeView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ShapeView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ShapeView);
        mShapeDrawableBuilder = new ShapeDrawableBuilder(this, typedArray, STYLEABLE);
        typedArray.recycle();

        mShapeDrawableBuilder.intoBackground();
    }

    public ShapeDrawableBuilder getShapeDrawableBuilder() {
        return mShapeDrawableBuilder;
    }
}