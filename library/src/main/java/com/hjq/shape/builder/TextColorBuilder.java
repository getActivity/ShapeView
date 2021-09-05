package com.hjq.shape.builder;

import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.text.SpannableStringBuilder;
import android.widget.TextView;

import com.hjq.shape.other.LinearGradientFontSpan;
import com.hjq.shape.styleable.ITextColorStyleable;

/**
 *    author : Android 轮子哥
 *    github : https://github.com/getActivity/ShapeView
 *    time   : 2021/08/28
 *    desc   : TextColor 构建类
 */
public final class TextColorBuilder {

    private final TextView mTextView;

    private int mTextColor;
    private Integer mTextPressedColor;
    private Integer mTextCheckedColor;
    private Integer mTextDisabledColor;
    private Integer mTextFocusedColor;
    private Integer mTextSelectedColor;

    private int[] mTextGradientColor;
    private int mTextGradientOrientation;

    public TextColorBuilder(TextView textView, TypedArray typedArray, ITextColorStyleable styleable) {
        mTextView = textView;
        mTextColor = typedArray.getColor(styleable.getTextColorStyleable(), textView.getTextColors().getDefaultColor());
        if (typedArray.hasValue(styleable.getTextPressedColorStyleable())) {
            mTextPressedColor = typedArray.getColor(styleable.getTextPressedColorStyleable(), mTextColor);
        }
        if (styleable.getTextCheckedColorStyleable() > 0 && typedArray.hasValue(styleable.getTextCheckedColorStyleable())) {
            mTextCheckedColor = typedArray.getColor(styleable.getTextCheckedColorStyleable(), mTextColor);
        }
        if (typedArray.hasValue(styleable.getTextDisabledColorStyleable())) {
            mTextDisabledColor = typedArray.getColor(styleable.getTextDisabledColorStyleable(), mTextColor);
        }
        if (typedArray.hasValue(styleable.getTextFocusedColorStyleable())) {
            mTextFocusedColor = typedArray.getColor(styleable.getTextFocusedColorStyleable(), mTextColor);
        }
        if (typedArray.hasValue(styleable.getTextSelectedColorStyleable())) {
            mTextSelectedColor = typedArray.getColor(styleable.getTextSelectedColorStyleable(), mTextColor);
        }

        if (typedArray.hasValue(styleable.getTextStartColorStyleable()) && typedArray.hasValue(styleable.getTextEndColorStyleable())) {
            if (typedArray.hasValue(styleable.getTextCenterColorStyleable())) {
                mTextGradientColor = new int[] {typedArray.getColor(styleable.getTextStartColorStyleable(), mTextColor),
                        typedArray.getColor(styleable.getTextCenterColorStyleable(), mTextColor),
                        typedArray.getColor(styleable.getTextEndColorStyleable(), mTextColor)};
            } else {
                mTextGradientColor = new int[] {typedArray.getColor(styleable.getTextStartColorStyleable(), mTextColor),
                        typedArray.getColor(styleable.getTextEndColorStyleable(), mTextColor)};
            }
        }

        mTextGradientOrientation = typedArray.getColor(styleable.getTextGradientOrientationStyleable(),
                LinearGradientFontSpan.GRADIENT_ORIENTATION_HORIZONTAL);
    }

    public TextColorBuilder setTextColor(Integer color) {
        mTextColor = color;
        clearTextGradientColor();
        return this;
    }

    public Integer getTextColor() {
        return mTextColor;
    }

    public TextColorBuilder setTextPressedColor(Integer color) {
        mTextPressedColor = color;
        return this;
    }

    public Integer getTextPressedColor() {
        return mTextPressedColor;
    }

    public TextColorBuilder setTextCheckedColor(Integer color) {
        mTextCheckedColor = color;
        return this;
    }

    public Integer getTextCheckedColor() {
        return mTextCheckedColor;
    }

    public TextColorBuilder setTextDisabledColor(Integer color) {
        mTextDisabledColor = color;
        return this;
    }

    public Integer getTextDisabledColor() {
        return mTextDisabledColor;
    }

    public TextColorBuilder setTextFocusedColor(Integer color) {
        mTextFocusedColor = color;
        return this;
    }

    public Integer getTextFocusedColor() {
        return mTextFocusedColor;
    }

    public TextColorBuilder setTextSelectedColor(Integer color) {
        mTextSelectedColor = color;
        return this;
    }

    public Integer getTextSelectedColor() {
        return mTextSelectedColor;
    }

    public TextColorBuilder setTextGradientColor(int[] color) {
        mTextGradientColor = color;
        return this;
    }

    public int[] getTextGradientColor() {
        return mTextGradientColor;
    }

    public TextColorBuilder setTextGradientOrientation(int orientation) {
        mTextGradientOrientation = orientation;
        return this;
    }

    public Integer getTextGradientOrientation() {
        return mTextGradientOrientation;
    }

    public boolean isTextGradientColor() {
        return mTextGradientColor != null && mTextGradientColor.length > 0;
    }

    public void clearTextGradientColor() {
        mTextGradientColor = null;
    }

    public SpannableStringBuilder buildLinearGradientSpannable(CharSequence text) {
        return LinearGradientFontSpan.buildLinearGradientSpannable(text, mTextGradientColor, null, mTextGradientOrientation);
    }

    public ColorStateList buildColorState() {
        if (mTextPressedColor == null &&
                mTextCheckedColor == null &&
                mTextDisabledColor == null &&
                mTextFocusedColor == null &&
                mTextSelectedColor == null) {
            return ColorStateList.valueOf(mTextColor);
        }

        int maxSize = 6;
        int arraySize = 0;
        int[][] statesTemp = new int[maxSize][];
        int[] colorsTemp = new int[maxSize];

        if (mTextPressedColor != null) {
            statesTemp[arraySize] = new int[]{android.R.attr.state_pressed};
            colorsTemp[arraySize] = mTextPressedColor;
            arraySize++;
        }
        if (mTextCheckedColor != null) {
            statesTemp[arraySize] = new int[]{android.R.attr.state_checked};
            colorsTemp[arraySize] = mTextCheckedColor;
            arraySize++;
        }
        if (mTextDisabledColor != null) {
            statesTemp[arraySize] = new int[]{-android.R.attr.state_enabled};
            colorsTemp[arraySize] = mTextDisabledColor;
            arraySize++;
        }
        if (mTextFocusedColor != null) {
            statesTemp[arraySize] = new int[]{android.R.attr.state_focused};
            colorsTemp[arraySize] = mTextFocusedColor;
            arraySize++;
        }
        if (mTextSelectedColor != null) {
            statesTemp[arraySize] = new int[]{android.R.attr.state_selected};
            colorsTemp[arraySize] = mTextSelectedColor;
            arraySize++;
        }

        statesTemp[arraySize] = new int[]{};
        colorsTemp[arraySize] = mTextColor;
        arraySize++;

        int[][] states;
        int[] colors;
        if (arraySize == maxSize) {
            states = statesTemp;
            colors = colorsTemp;
        } else {
            states = new int[arraySize][];
            colors = new int[arraySize];
            // 对数组进行拷贝
            System.arraycopy(statesTemp, 0, states, 0, arraySize);
            System.arraycopy(colorsTemp, 0, colors, 0, arraySize);
        }
        return new ColorStateList(states, colors);
    }

    public void intoTextColor() {
        if (isTextGradientColor()) {
            mTextView.setText(buildLinearGradientSpannable(mTextView.getText()));
            return;
        }
        mTextView.setTextColor(buildColorState());
    }
}