package com.hjq.shape.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

import com.hjq.shape.R;
import com.hjq.shape.builder.ShapeDrawableBuilder;
import com.hjq.shape.builder.TextColorBuilder;
import com.hjq.shape.styleable.ShapeTextViewStyleable;

/**
 *    author : Android 轮子哥
 *    github : https://github.com/getActivity/ShapeView
 *    time   : 2021/07/17
 *    desc   : 支持直接定义 Shape 背景的 TextView
 */
public class ShapeTextView extends AppCompatTextView {

    private static final ShapeTextViewStyleable STYLEABLE = new ShapeTextViewStyleable();

    private final ShapeDrawableBuilder mShapeDrawableBuilder;
    private final TextColorBuilder mTextColorBuilder;

    public ShapeTextView(Context context) {
        this(context, null);
    }

    public ShapeTextView(Context context, AttributeSet attrs) {
        this(context, attrs, android.R.attr.textViewStyle);
    }

    public ShapeTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ShapeTextView);
        mShapeDrawableBuilder = new ShapeDrawableBuilder(this, typedArray, STYLEABLE);
        mTextColorBuilder = new TextColorBuilder(this, typedArray, STYLEABLE);
        typedArray.recycle();

        mShapeDrawableBuilder.intoBackground();

        if (mTextColorBuilder.isTextGradientColors() || mTextColorBuilder.isTextStrokeColor()) {
            setText(getText());
        } else {
            mTextColorBuilder.intoTextColor();
        }
    }

    @Override
    public void setTextColor(int color) {
        super.setTextColor(color);
        if (mTextColorBuilder == null) {
            return;
        }
        mTextColorBuilder.setTextColor(color);
        mTextColorBuilder.clearTextGradientColors();
    }

    @Override
    public void setText(CharSequence text, BufferType type) {
        if (mTextColorBuilder != null &&
                (mTextColorBuilder.isTextGradientColors() || mTextColorBuilder.isTextStrokeColor())) {
            super.setText(mTextColorBuilder.buildTextSpannable(text), type);
        } else {
            super.setText(text, type);
        }
    }

    public ShapeDrawableBuilder getShapeDrawableBuilder() {
        return mShapeDrawableBuilder;
    }

    public TextColorBuilder getTextColorBuilder() {
        return mTextColorBuilder;
    }
}