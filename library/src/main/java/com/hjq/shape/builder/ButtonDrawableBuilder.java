package com.hjq.shape.builder;

import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.support.v4.widget.CompoundButtonCompat;
import android.widget.CompoundButton;

import com.hjq.shape.styleable.ICompoundButtonStyleable;

/**
 *    author : Android 轮子哥
 *    github : https://github.com/getActivity/ShapeView
 *    time   : 2021/08/28
 *    desc   : ButtonDrawable  构建类
 */
public final class ButtonDrawableBuilder {

    private final CompoundButton mCompoundButton;

    private Drawable mButtonDrawable;
    private Drawable mDrawablePressedDrawable;
    private Drawable mDrawableCheckedDrawable;
    private Drawable mDrawableDisabledDrawable;
    private Drawable mDrawableFocusedDrawable;
    private Drawable mDrawableSelectedDrawable;

    public ButtonDrawableBuilder(CompoundButton compoundButton, TypedArray typedArray, ICompoundButtonStyleable styleable) {
        mCompoundButton = compoundButton;
        if (typedArray.hasValue(styleable.getButtonDrawableStyleable())) {
            mButtonDrawable = typedArray.getDrawable(styleable.getButtonDrawableStyleable());
        } else {
            mButtonDrawable = CompoundButtonCompat.getButtonDrawable(mCompoundButton);
        }

        if (typedArray.hasValue(styleable.getButtonPressedDrawableStyleable())) {
            mDrawablePressedDrawable = typedArray.getDrawable(styleable.getButtonPressedDrawableStyleable());
        }

        if (typedArray.hasValue(styleable.getButtonCheckedDrawableStyleable())) {
            mDrawableCheckedDrawable = typedArray.getDrawable(styleable.getButtonCheckedDrawableStyleable());
        }

        if (typedArray.hasValue(styleable.getButtonDisabledDrawableStyleable())) {
            mDrawableDisabledDrawable = typedArray.getDrawable(styleable.getButtonDisabledDrawableStyleable());
        }

        if (typedArray.hasValue(styleable.getButtonFocusedDrawableStyleable())) {
            mDrawableFocusedDrawable = typedArray.getDrawable(styleable.getButtonFocusedDrawableStyleable());
        }

        if (typedArray.hasValue(styleable.getButtonSelectedDrawableStyleable())) {
            mDrawableSelectedDrawable = typedArray.getDrawable(styleable.getButtonSelectedDrawableStyleable());
        }
    }

    public ButtonDrawableBuilder setButtonDrawable(Drawable drawable) {
        if (mDrawablePressedDrawable == mButtonDrawable) {
            mDrawablePressedDrawable = drawable;
        }
        if (mDrawableCheckedDrawable == mButtonDrawable) {
            mDrawableCheckedDrawable = drawable;
        }
        if (mDrawableDisabledDrawable == mButtonDrawable) {
            mDrawableDisabledDrawable = drawable;
        }
        if (mDrawableFocusedDrawable == mButtonDrawable) {
            mDrawableFocusedDrawable = drawable;
        }
        if (mDrawableSelectedDrawable == mButtonDrawable) {
            mDrawableSelectedDrawable = drawable;
        }
        mButtonDrawable = drawable;
        return this;
    }

    public Drawable getButtonDrawable() {
        return mButtonDrawable;
    }

    public ButtonDrawableBuilder setDrawablePressedDrawable(Drawable drawable) {
        mDrawablePressedDrawable = drawable;
        return this;
    }

    public Drawable getDrawablePressedDrawable() {
        return mDrawablePressedDrawable;
    }

    public ButtonDrawableBuilder setDrawableCheckedDrawable(Drawable drawable) {
        mDrawableCheckedDrawable = drawable;
        return this;
    }

    public Drawable getDrawableCheckedDrawable() {
        return mDrawableCheckedDrawable;
    }

    public ButtonDrawableBuilder setDrawableDisabledDrawable(Drawable drawable) {
        mDrawableDisabledDrawable = drawable;
        return this;
    }

    public Drawable getDrawableDisabledDrawable() {
        return mDrawableDisabledDrawable;
    }

    public ButtonDrawableBuilder setDrawableFocusedDrawable(Drawable drawable) {
        mDrawableFocusedDrawable = drawable;
        return this;
    }

    public Drawable getDrawableFocusedDrawable() {
        return mDrawableFocusedDrawable;
    }

    public ButtonDrawableBuilder setDrawableSelectedDrawable(Drawable drawable) {
        mDrawableSelectedDrawable = drawable;
        return this;
    }

    public Drawable getDrawableSelectedDrawable() {
        return mDrawableSelectedDrawable;
    }

    public void intoButtonDrawable() {
        if (mButtonDrawable == null) {
            return;
        }

        if (mDrawablePressedDrawable == null &&
                mDrawableCheckedDrawable == null &&
                mDrawableDisabledDrawable == null &&
                mDrawableFocusedDrawable == null &&
                mDrawableSelectedDrawable == null) {
            mCompoundButton.setButtonDrawable(mButtonDrawable);
            return;
        }

        StateListDrawable drawable = new StateListDrawable();
        if (mDrawablePressedDrawable != null) {
            drawable.addState(new int[]{android.R.attr.state_pressed}, mDrawablePressedDrawable);
        }
        if (mDrawableCheckedDrawable != null) {
            drawable.addState(new int[]{android.R.attr.state_checked}, mDrawableCheckedDrawable);
        }
        if (mDrawableDisabledDrawable != null) {
            drawable.addState(new int[]{-android.R.attr.state_enabled}, mDrawableDisabledDrawable);
        }
        if (mDrawableFocusedDrawable != null) {
            drawable.addState(new int[]{android.R.attr.state_focused}, mDrawableFocusedDrawable);
        }
        if (mDrawableSelectedDrawable != null) {
            drawable.addState(new int[]{android.R.attr.state_selected}, mDrawableSelectedDrawable);
        }
        drawable.addState(new int[]{}, mButtonDrawable);
        mCompoundButton.setButtonDrawable(drawable);
    }
}