package com.hjq.shape;

import android.content.res.ColorStateList;
import android.view.View;

/**
 *    author : Android 轮子哥
 *    github : https://github.com/getActivity/ShapeView
 *    time   : 2021/07/31
 *    desc   : Shape TextColor 接口规范
 */
public interface IShapeTextColor<V extends View> {

    V setNormalTextColor(int color);

    int getNormalTextColor();

    default V setTextPressedColor(int color) {
        return setNormalTextColor(color);
    }

    default int getTextPressedColor() {
        return getNormalTextColor();
    }

    default V setTextCheckedColor(int color) {
        return setNormalTextColor(color);
    }

    default int getTextCheckedColor() {
        return getNormalTextColor();
    }

    default V setTextDisabledColor(int color) {
        return setNormalTextColor(color);
    }

    default int getTextDisabledColor() {
        return getNormalTextColor();
    }

    default V setTextFocusedColor(int color) {
        return setNormalTextColor(color);
    }

    default int getTextFocusedColor() {
        return getNormalTextColor();
    }

    default V setTextSelectedColor(int color) {
        return setNormalTextColor(color);
    }

    default int getTextSelectedColor() {
        return getNormalTextColor();
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