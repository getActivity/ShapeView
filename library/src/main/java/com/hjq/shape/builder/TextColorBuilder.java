package com.hjq.shape.builder;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TextView.BufferType;
import com.hjq.shape.config.ITextColorStyleable;
import com.hjq.shape.config.ITextViewAttribute;
import com.hjq.shape.other.TextViewAttribute;
import com.hjq.shape.span.LinearGradientFontSpan;
import com.hjq.shape.span.StrokeFontSpan;

/**
 *    author : Android 轮子哥
 *    github : https://github.com/getActivity/ShapeView
 *    time   : 2021/08/28
 *    desc   : TextColor 构建类
 */
public final class TextColorBuilder {

    /** 水平渐变方向 */
    public static final int GRADIENT_ORIENTATION_HORIZONTAL = LinearLayout.HORIZONTAL;
    /** 垂直渐变方向 */
    public static final int GRADIENT_ORIENTATION_VERTICAL = LinearLayout.VERTICAL;

    private final TextView mTextView;
    private final ITextViewAttribute mTextViewAttribute;

    private int mTextColor;
    private Integer mTextPressedColor;
    private Integer mTextCheckedColor;
    private Integer mTextDisabledColor;
    private Integer mTextFocusedColor;
    private Integer mTextSelectedColor;

    private int[] mTextGradientColors;
    private int mTextGradientOrientation;

    private int mTextStrokeColor;
    private int mTextStrokeSize;

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
                mTextGradientColors = new int[] {typedArray.getColor(styleable.getTextStartColorStyleable(), mTextColor),
                        typedArray.getColor(styleable.getTextCenterColorStyleable(), mTextColor),
                        typedArray.getColor(styleable.getTextEndColorStyleable(), mTextColor)};
            } else {
                mTextGradientColors = new int[] {typedArray.getColor(styleable.getTextStartColorStyleable(), mTextColor),
                        typedArray.getColor(styleable.getTextEndColorStyleable(), mTextColor)};
            }
        }

        mTextGradientOrientation = typedArray.getColor(styleable.getTextGradientOrientationStyleable(),
                LinearGradientFontSpan.GRADIENT_ORIENTATION_HORIZONTAL);

        if (typedArray.hasValue(styleable.getTextStrokeColorStyleable())) {
            mTextStrokeColor = typedArray.getColor(styleable.getTextStrokeColorStyleable(), Color.TRANSPARENT);
        }

        if (typedArray.hasValue(styleable.getTextStrokeSizeStyleable())) {
            mTextStrokeSize = typedArray.getDimensionPixelSize(styleable.getTextStrokeSizeStyleable(), 0);
        }

        mTextViewAttribute = new TextViewAttribute(mTextView);
    }

    public TextColorBuilder setTextColor(int color) {
        mTextColor = color;
        return this;
    }

    public int getTextColor() {
        return mTextColor;
    }

    public TextColorBuilder setTextPressedColor(Integer color) {
        mTextPressedColor = color;
        return this;
    }

    @Nullable
    public Integer getTextPressedColor() {
        return mTextPressedColor;
    }

    public TextColorBuilder setTextCheckedColor(Integer color) {
        mTextCheckedColor = color;
        return this;
    }

    @Nullable
    public Integer getTextCheckedColor() {
        return mTextCheckedColor;
    }

    public TextColorBuilder setTextDisabledColor(Integer color) {
        mTextDisabledColor = color;
        return this;
    }

    @Nullable
    public Integer getTextDisabledColor() {
        return mTextDisabledColor;
    }

    public TextColorBuilder setTextFocusedColor(Integer color) {
        mTextFocusedColor = color;
        return this;
    }

    @Nullable
    public Integer getTextFocusedColor() {
        return mTextFocusedColor;
    }

    public TextColorBuilder setTextSelectedColor(Integer color) {
        mTextSelectedColor = color;
        return this;
    }

    @Nullable
    public Integer getTextSelectedColor() {
        return mTextSelectedColor;
    }

    public TextColorBuilder setTextGradientColors(int startColor, int endColor) {
        return setTextGradientColors(new int[]{startColor, endColor});
    }

    public TextColorBuilder setTextGradientColors(int startColor, int centerColor, int endColor) {
        return setTextGradientColors(new int[]{startColor, centerColor, endColor});
    }

    public TextColorBuilder setTextGradientColors(int[] colors) {
        mTextGradientColors = colors;
        return this;
    }

    @Nullable
    public int[] getTextGradientColors() {
        return mTextGradientColors;
    }

    public boolean isTextGradientColorsEnable() {
        return mTextGradientColors != null && mTextGradientColors.length > 0;
    }

    public TextColorBuilder setTextGradientOrientation(int orientation) {
        mTextGradientOrientation = orientation;
        return this;
    }

    public int getTextGradientOrientation() {
        return mTextGradientOrientation;
    }

    public TextColorBuilder setTextStrokeColor(int color) {
        mTextStrokeColor = color;
        return this;
    }

    public TextColorBuilder setTextStrokeSize(int size) {
        mTextStrokeSize = size;
        return this;
    }

    public int getTextStrokeColor() {
        return mTextStrokeColor;
    }

    public int getTextStrokeSize() {
        return mTextStrokeSize;
    }

    public boolean isTextStrokeColorEnable() {
        return mTextStrokeColor != Color.TRANSPARENT && mTextStrokeSize > 0;
    }

    /**
     * 清除文本渐变色
     */
    public void clearTextGradientColor() {
        if (!isTextGradientColorsEnable()) {
            mTextView.setTextColor(mTextColor);
        }
        mTextGradientColors = null;
        mTextView.postInvalidate();
    }

    /**
     * 清除文本边框色
     */
    public void clearTextStrokeColor() {
        mTextStrokeColor = Color.TRANSPARENT;
        mTextView.setText(mTextView.getText().toString(), BufferType.NORMAL);
    }

    public SpannableStringBuilder buildStrokeFontSpannable(CharSequence text) {
        SpannableStringBuilder builder = new SpannableStringBuilder(text);

        StrokeFontSpan strokeFontSpan = null;

        if (isTextStrokeColorEnable()) {
            strokeFontSpan = new StrokeFontSpan(mTextViewAttribute)
                    .setTextStrokeColor(mTextStrokeColor)
                    .setTextStrokeSize(mTextStrokeSize);
        }

        if (strokeFontSpan != null) {
            strokeFontSpan.setTextSolidColor(mTextColor);
            builder.setSpan(strokeFontSpan, 0, builder.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }

        return builder;
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
        if (isTextGradientColorsEnable()) {
            // 如果 TextView 设置了不透明，那么就强制给它设置成不透明的
            mTextColor = mTextColor | 0xFF000000;
        }

        mTextView.setTextColor(buildColorState());

        if (isTextStrokeColorEnable()) {
            mTextView.setText(buildStrokeFontSpannable(mTextView.getText().toString()), BufferType.SPANNABLE);
        }

        mTextView.postInvalidate();
    }

    public void onDraw(@NonNull View view, @NonNull Canvas canvas, Paint paint) {
        if (isTextGradientColorsEnable()) {
            int[] textGradientColors;
            if (mTextGradientOrientation == GRADIENT_ORIENTATION_HORIZONTAL &&
                getLayoutDirectionByContext(view.getContext()) == View.LAYOUT_DIRECTION_RTL) {
                textGradientColors = reverseArray(mTextGradientColors);
            } else {
                textGradientColors = mTextGradientColors;
            }
            LinearGradient linearGradient = getLinearGradient(view, canvas, mTextGradientOrientation, textGradientColors);
            paint.setShader(linearGradient);
        } else {
            Shader shader = paint.getShader();
            if (shader instanceof LinearGradient) {
                paint.setShader(null);
            }
        }
    }

    /**
     * 获取线性渐变对象
     */
    private static LinearGradient getLinearGradient(@NonNull View view, @NonNull Canvas canvas,
                                                    int textGradientOrientation,
                                                    @Nullable int[] textGradientColors) {
        LinearGradient linearGradient;
        if (textGradientOrientation == GRADIENT_ORIENTATION_VERTICAL) {
            linearGradient = new LinearGradient(
                view.getPaddingLeft(), view.getPaddingTop(), 0,
                (float) canvas.getHeight() - view.getPaddingBottom(),
                textGradientColors, null, Shader.TileMode.CLAMP);
        } else {
            linearGradient = new LinearGradient(
                view.getPaddingLeft(), view.getPaddingTop(),
                (float) canvas.getWidth() - view.getPaddingEnd(),
                (float) canvas.getHeight() - view.getPaddingBottom(),
                textGradientColors, null, Shader.TileMode.CLAMP);
        }
        return linearGradient;
    }

    /**
     * 从 Context 中获取当前布局方向
     */
    private static int getLayoutDirectionByContext(@Nullable Context context) {
        int layoutDirection;
        Resources resources = null;
        Configuration configuration = null;
        if (context != null) {
            resources = context.getResources();
        }
        if (resources != null) {
            configuration = resources.getConfiguration();
        }
        if (configuration != null) {
            layoutDirection = configuration.getLayoutDirection();
        } else {
            layoutDirection = View.LAYOUT_DIRECTION_LTR;
        }
        return layoutDirection;
    }

    /**
     * 反转 int 数组
     */
    public static int[] reverseArray(@NonNull int[] originalArray) {
        int length = originalArray.length;
        int[] newArray = new int[length];
        for (int i = 0; i < length; i++) {
            newArray[i] = originalArray[length - 1 - i];
        }
        return newArray;
    }
}