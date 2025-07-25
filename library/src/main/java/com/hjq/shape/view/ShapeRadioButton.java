package com.hjq.shape.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.AppCompatRadioButton;
import android.util.AttributeSet;
import com.hjq.shape.R;
import com.hjq.shape.builder.ButtonDrawableBuilder;
import com.hjq.shape.builder.ShapeDrawableBuilder;
import com.hjq.shape.builder.TextColorBuilder;
import com.hjq.shape.config.IGetButtonDrawableBuilder;
import com.hjq.shape.config.IGetShapeDrawableBuilder;
import com.hjq.shape.config.IGetTextColorBuilder;
import com.hjq.shape.styleable.ShapeRadioButtonStyleable;

/**
 *    author : Android 轮子哥
 *    github : https://github.com/getActivity/ShapeView
 *    time   : 2021/07/17
 *    desc   : 支持直接定义 Shape 背景的 RadioButton
 */
public class ShapeRadioButton extends AppCompatRadioButton implements
        IGetShapeDrawableBuilder, IGetTextColorBuilder, IGetButtonDrawableBuilder {

    private static final ShapeRadioButtonStyleable STYLEABLE = new ShapeRadioButtonStyleable();

    private final ShapeDrawableBuilder mShapeDrawableBuilder;
    private final TextColorBuilder mTextColorBuilder;
    private final ButtonDrawableBuilder mButtonDrawableBuilder;

    public ShapeRadioButton(Context context) {
        this(context, null);
    }

    public ShapeRadioButton(Context context, AttributeSet attrs) {
        this(context, attrs, R.attr.radioButtonStyle);
    }

    public ShapeRadioButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ShapeRadioButton, 0, R.style.ShapeRadioButtonStyle);
        mShapeDrawableBuilder = new ShapeDrawableBuilder(this, typedArray, STYLEABLE);
        mTextColorBuilder = new TextColorBuilder(this, typedArray, STYLEABLE);
        mButtonDrawableBuilder = new ButtonDrawableBuilder(this, typedArray, STYLEABLE);
        typedArray.recycle();

        mShapeDrawableBuilder.intoBackground();
        mTextColorBuilder.intoTextColor();
        mButtonDrawableBuilder.intoButtonDrawable();
    }

    @Override
    public void setTextColor(int color) {
        super.setTextColor(color);
        if (mTextColorBuilder == null) {
            return;
        }
        mTextColorBuilder.setTextColor(color);
    }

    @Override
    public void setButtonDrawable(Drawable drawable) {
        super.setButtonDrawable(drawable);
        if (mButtonDrawableBuilder == null) {
            return;
        }
        mButtonDrawableBuilder.setButtonDrawable(drawable);
    }

    @Override
    public void setText(CharSequence text, BufferType type) {
        if (type != BufferType.SPANNABLE  &&
            mTextColorBuilder != null &&
            mTextColorBuilder.isTextStrokeColorEnable()) {
            super.setText(mTextColorBuilder.buildStrokeFontSpannable(text), BufferType.SPANNABLE);
        } else {
            super.setText(text, type);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        mTextColorBuilder.onDraw(this, canvas, getPaint());
        super.onDraw(canvas);
    }

    @Override
    public ShapeDrawableBuilder getShapeDrawableBuilder() {
        return mShapeDrawableBuilder;
    }

    @Override
    public TextColorBuilder getTextColorBuilder() {
        return mTextColorBuilder;
    }

    @Override
    public ButtonDrawableBuilder getButtonDrawableBuilder() {
        return mButtonDrawableBuilder;
    }
}