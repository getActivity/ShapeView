package com.hjq.shape.core;

import android.content.res.ColorStateList;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.view.View;
import android.widget.LinearLayout;

/**
 *    author : Android 轮子哥
 *    github : https://github.com/getActivity/ShapeView
 *    time   : 2021/07/31
 *    desc   : ShapeTextColor 参数接口
 */
public interface IShapeTextColor<V extends View> {

    int SHAPE_TEXT_GRADIENT_TYPE_HORIZONTAL = LinearLayout.HORIZONTAL;
    int SHAPE_TEXT_GRADIENT_TYPE_VERTICAL = LinearLayout.VERTICAL;

    V setNormalTextColor(int color);

    int getNormalTextColor();

    V setTextPressedColor(int color);

    int getTextPressedColor();

    default V setTextCheckedColor(int color) {
        return setNormalTextColor(color);
    }

    default int getTextCheckedColor() {
        return getNormalTextColor();
    }

    V setTextDisabledColor(int color);

    int getTextDisabledColor();

    V setTextFocusedColor(int color);

    int getTextFocusedColor();

    V setTextSelectedColor(int color);

    int getTextSelectedColor();

    V setTextStartColor(int color);

    int getTextStartColor();

    V setTextCenterColor(int color);

    int getTextCenterColor();

    V setTextEndColor(int color);

    int getTextEndColor();

    V setTextGradientOrientation(int orientation);

    int getTextGradientOrientation();

    default boolean isTextGradientColor() {
        return getNormalTextColor() != getTextStartColor() &&
                getNormalTextColor() != getTextEndColor();
    }

    default void clearTextGradientColor() {
        setTextStartColor(getNormalTextColor());
        setTextCenterColor(getNormalTextColor());
        setTextEndColor(getNormalTextColor());
    }

    default SpannableStringBuilder buildLinearGradientSpannable(CharSequence text) {
        final int[] colors;
        final float[] positions;
        if (getNormalTextColor() != getTextCenterColor()) {
            colors = new int[]{getTextStartColor(), getTextCenterColor(), getTextEndColor()};
            positions = new float[] {0f, 0.5f, 1f};
        } else {
            colors = new int[]{getTextStartColor(), getTextEndColor()};
            positions = new float[] {0f, 1f};
        }
        return buildLinearGradientSpannable(text, colors, positions);
    }

    default SpannableStringBuilder buildLinearGradientSpannable(CharSequence text, int[] colors, float[] positions) {
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(text);
        LinearGradientFontSpan linearGradientFontSpan = new LinearGradientFontSpan();
        linearGradientFontSpan.setTextGradientColor(colors);
        linearGradientFontSpan.setTextGradientOrientation(getTextGradientOrientation());
        linearGradientFontSpan.setTextGradientPositions(positions);
        spannableStringBuilder.setSpan(linearGradientFontSpan, 0, spannableStringBuilder.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        return spannableStringBuilder;
    }

    default ColorStateList buildColorState() {
        return new ColorStateList(new int[][]{new int[]{
                android.R.attr.state_pressed}, new int[]{android.R.attr.state_checked}, new int[]{-android.R.attr.state_enabled},
                new int[]{android.R.attr.state_focused}, new int[]{android.R.attr.state_selected}, new int[]{}},
                new int[]{getTextPressedColor(), getTextCheckedColor(), getTextDisabledColor(),
                        getTextFocusedColor(), getTextSelectedColor(), getNormalTextColor()});
    }

    void intoTextColor();
}