package com.hjq.shape.layout;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;

import com.hjq.shape.R;
import com.hjq.shape.builder.ShapeDrawableBuilder;
import com.hjq.shape.styleable.ShapeConstraintLayoutStyleable;

/**
 *    author : Android 轮子哥
 *    github : https://github.com/getActivity/ShapeView
 *    time   : 2021/07/17
 *    desc   : 支持直接定义 Shape 背景的 ConstraintLayout
 */
public class ShapeConstraintLayout extends ConstraintLayout {

    private static final ShapeConstraintLayoutStyleable STYLEABLE = new ShapeConstraintLayoutStyleable();

    private final ShapeDrawableBuilder mShapeDrawableBuilder;

    public ShapeConstraintLayout(Context context) {
        this(context, null);
    }

    public ShapeConstraintLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ShapeConstraintLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ShapeConstraintLayout);
        mShapeDrawableBuilder = new ShapeDrawableBuilder(this, typedArray, STYLEABLE);
        typedArray.recycle();

        mShapeDrawableBuilder.intoBackground();
    }

    public ShapeDrawableBuilder getShapeDrawableBuilder() {
        return mShapeDrawableBuilder;
    }
}