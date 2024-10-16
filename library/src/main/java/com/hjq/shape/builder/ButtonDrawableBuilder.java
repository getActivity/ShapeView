package com.hjq.shape.builder;

import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import androidx.core.widget.CompoundButtonCompat;
import android.widget.CompoundButton;

import com.hjq.shape.R;
import com.hjq.shape.config.ICompoundButtonStyleable;

/**
 *    author : Android 轮子哥
 *    github : https://github.com/getActivity/ShapeView
 *    time   : 2021/08/28
 *    desc   : ButtonDrawable  构建类
 */
public final class ButtonDrawableBuilder {

    private final CompoundButton mCompoundButton;

    private Drawable mButtonDrawable;
    private Drawable mButtonPressedDrawable;
    private Drawable mButtonCheckedDrawable;
    private Drawable mButtonDisabledDrawable;
    private Drawable mButtonFocusedDrawable;
    private Drawable mButtonSelectedDrawable;

    public ButtonDrawableBuilder(CompoundButton compoundButton, TypedArray typedArray, ICompoundButtonStyleable styleable) {
        mCompoundButton = compoundButton;

        if (typedArray.hasValue(styleable.getButtonDrawableStyleable())) {
            if (typedArray.getResourceId(styleable.getButtonDrawableStyleable(), 0) != R.drawable.shape_view_placeholder) {
                mButtonDrawable = typedArray.getDrawable(styleable.getButtonDrawableStyleable());
            } else {
                mButtonDrawable = CompoundButtonCompat.getButtonDrawable(mCompoundButton);
            }
        } else {
            mButtonDrawable = null;
            mCompoundButton.setButtonDrawable(null);
        }

        if (typedArray.hasValue(styleable.getButtonPressedDrawableStyleable())) {
            mButtonPressedDrawable = typedArray.getDrawable(styleable.getButtonPressedDrawableStyleable());
        }

        if (typedArray.hasValue(styleable.getButtonCheckedDrawableStyleable())) {
            mButtonCheckedDrawable = typedArray.getDrawable(styleable.getButtonCheckedDrawableStyleable());
        }

        if (typedArray.hasValue(styleable.getButtonDisabledDrawableStyleable())) {
            mButtonDisabledDrawable = typedArray.getDrawable(styleable.getButtonDisabledDrawableStyleable());
        }

        if (typedArray.hasValue(styleable.getButtonFocusedDrawableStyleable())) {
            mButtonFocusedDrawable = typedArray.getDrawable(styleable.getButtonFocusedDrawableStyleable());
        }

        if (typedArray.hasValue(styleable.getButtonSelectedDrawableStyleable())) {
            mButtonSelectedDrawable = typedArray.getDrawable(styleable.getButtonSelectedDrawableStyleable());
        }
    }

    public ButtonDrawableBuilder setButtonDrawable(Drawable drawable) {
        if (mButtonPressedDrawable == mButtonDrawable) {
            mButtonPressedDrawable = drawable;
        }
        if (mButtonCheckedDrawable == mButtonDrawable) {
            mButtonCheckedDrawable = drawable;
        }
        if (mButtonDisabledDrawable == mButtonDrawable) {
            mButtonDisabledDrawable = drawable;
        }
        if (mButtonFocusedDrawable == mButtonDrawable) {
            mButtonFocusedDrawable = drawable;
        }
        if (mButtonSelectedDrawable == mButtonDrawable) {
            mButtonSelectedDrawable = drawable;
        }
        mButtonDrawable = drawable;
        return this;
    }

    public Drawable getButtonDrawable() {
        return mButtonDrawable;
    }

    public ButtonDrawableBuilder setButtonPressedDrawable(Drawable drawable) {
        mButtonPressedDrawable = drawable;
        return this;
    }

    public Drawable getButtonPressedDrawable() {
        return mButtonPressedDrawable;
    }

    public ButtonDrawableBuilder setButtonCheckedDrawable(Drawable drawable) {
        mButtonCheckedDrawable = drawable;
        return this;
    }

    public Drawable getButtonCheckedDrawable() {
        return mButtonCheckedDrawable;
    }

    public ButtonDrawableBuilder setButtonDisabledDrawable(Drawable drawable) {
        mButtonDisabledDrawable = drawable;
        return this;
    }

    public Drawable getButtonDisabledDrawable() {
        return mButtonDisabledDrawable;
    }

    public ButtonDrawableBuilder setButtonFocusedDrawable(Drawable drawable) {
        mButtonFocusedDrawable = drawable;
        return this;
    }

    public Drawable getButtonFocusedDrawable() {
        return mButtonFocusedDrawable;
    }

    public ButtonDrawableBuilder setButtonSelectedDrawable(Drawable drawable) {
        mButtonSelectedDrawable = drawable;
        return this;
    }

    public Drawable getButtonSelectedDrawable() {
        return mButtonSelectedDrawable;
    }

    public void intoButtonDrawable() {
        if (mButtonDrawable == null) {
            return;
        }

        if (mButtonPressedDrawable == null &&
                mButtonCheckedDrawable == null &&
                mButtonDisabledDrawable == null &&
                mButtonFocusedDrawable == null &&
                mButtonSelectedDrawable == null) {
            mCompoundButton.setButtonDrawable(mButtonDrawable);
            return;
        }

        StateListDrawable drawable = new StateListDrawable();
        if (mButtonPressedDrawable != null) {
            drawable.addState(new int[]{android.R.attr.state_pressed}, mButtonPressedDrawable);
        }
        if (mButtonCheckedDrawable != null) {
            drawable.addState(new int[]{android.R.attr.state_checked}, mButtonCheckedDrawable);
        }
        if (mButtonDisabledDrawable != null) {
            drawable.addState(new int[]{-android.R.attr.state_enabled}, mButtonDisabledDrawable);
        }
        if (mButtonFocusedDrawable != null) {
            drawable.addState(new int[]{android.R.attr.state_focused}, mButtonFocusedDrawable);
        }
        if (mButtonSelectedDrawable != null) {
            drawable.addState(new int[]{android.R.attr.state_selected}, mButtonSelectedDrawable);
        }
        drawable.addState(new int[]{}, mButtonDrawable);
        mCompoundButton.setButtonDrawable(drawable);
    }
}