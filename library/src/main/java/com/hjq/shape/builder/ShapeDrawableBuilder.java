package com.hjq.shape.builder;

import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.support.annotation.Nullable;
import android.view.View;

import com.hjq.shape.drawable.ShapeDrawable;
import com.hjq.shape.drawable.ShapeGradientType;
import com.hjq.shape.drawable.ShapeType;
import com.hjq.shape.styleable.IShapeDrawableStyleable;

/**
 *    author : Android 轮子哥
 *    github : https://github.com/getActivity/ShapeView
 *    time   : 2021/08/28
 *    desc   : ShapeDrawable 构建类
 */
public final class ShapeDrawableBuilder {

    private static final int NO_COLOR = Color.TRANSPARENT;

    private final View mView;

    private int mShape;
    private int mShapeWidth;
    private int mShapeHeight;

    private int mSolidColor;
    private Integer mSolidPressedColor;
    private Integer mSolidCheckedColor;
    private Integer mSolidDisabledColor;
    private Integer mSolidFocusedColor;
    private Integer mSolidSelectedColor;

    private float mTopLeftRadius;
    private float mTopRightRadius;
    private float mBottomLeftRadius;
    private float mBottomRightRadius;

    private int[] mGradientColors;
    private boolean mUseLevel;
    private int mAngle;
    private int mGradientType;
    private float mCenterX;
    private float mCenterY;
    private int mGradientRadius;

    private int mStrokeColor;
    private Integer mStrokePressedColor;
    private Integer mStrokeCheckedColor;
    private Integer mStrokeDisabledColor;
    private Integer mStrokeFocusedColor;
    private Integer mStrokeSelectedColor;

    private int mStrokeWidth;
    private int mDashWidth;
    private int mDashGap;

    private int mInnerRadius;
    private float mInnerRadiusRatio;
    private int mThickness;
    private float mThicknessRatio;

    private int mShadowSize;
    private int mShadowColor;
    private int mShadowOffsetX;
    private int mShadowOffsetY;

    public ShapeDrawableBuilder(View view, TypedArray typedArray, IShapeDrawableStyleable styleable) {
        mView = view;
        mShape = typedArray.getInt(styleable.getShapeTypeStyleable(), ShapeType.RECTANGLE);
        mShapeWidth = typedArray.getDimensionPixelSize(styleable.getShapeWidthStyleable(), -1);
        mShapeHeight = typedArray.getDimensionPixelSize(styleable.getShapeHeightStyleable(), -1);

        mSolidColor = typedArray.getColor(styleable.getSolidColorStyleable(), NO_COLOR);
        if (typedArray.hasValue(styleable.getSolidPressedColorStyleable())) {
            mSolidPressedColor = typedArray.getColor(styleable.getSolidPressedColorStyleable(), NO_COLOR);
        }
        if (styleable.getSolidCheckedColorStyleable() > 0 && typedArray.hasValue(styleable.getSolidCheckedColorStyleable())) {
            mSolidCheckedColor = typedArray.getColor(styleable.getSolidCheckedColorStyleable(), NO_COLOR);
        }
        if (typedArray.hasValue(styleable.getSolidDisabledColorStyleable())) {
            mSolidDisabledColor = typedArray.getColor(styleable.getSolidDisabledColorStyleable(), NO_COLOR);
        }
        if (typedArray.hasValue(styleable.getSolidFocusedColorStyleable())) {
            mSolidFocusedColor = typedArray.getColor(styleable.getSolidFocusedColorStyleable(), NO_COLOR);
        }
        if (typedArray.hasValue(styleable.getSolidSelectedColorStyleable())) {
            mSolidSelectedColor = typedArray.getColor(styleable.getSolidSelectedColorStyleable(), NO_COLOR);
        }

        int radius = typedArray.getDimensionPixelSize(styleable.getRadiusStyleable(), 0);
        mTopLeftRadius = typedArray.getDimensionPixelSize(styleable.getTopLeftRadiusStyleable(), radius);
        mTopRightRadius = typedArray.getDimensionPixelSize(styleable.getTopRightRadiusStyleable(), radius);
        mBottomLeftRadius = typedArray.getDimensionPixelSize(styleable.getBottomLeftRadiusStyleable(), radius);
        mBottomRightRadius = typedArray.getDimensionPixelSize(styleable.getBottomRightRadiusStyleable(), radius);

        if (typedArray.hasValue(styleable.getStartColorStyleable()) && typedArray.hasValue(styleable.getEndColorStyleable())) {
            if (typedArray.hasValue(styleable.getCenterColorStyleable())) {
                mGradientColors = new int[] {typedArray.getColor(styleable.getStartColorStyleable(), NO_COLOR),
                        typedArray.getColor(styleable.getCenterColorStyleable(), NO_COLOR),
                        typedArray.getColor(styleable.getEndColorStyleable(), NO_COLOR)};
            } else {
                mGradientColors = new int[] {typedArray.getColor(styleable.getStartColorStyleable(), NO_COLOR),
                        typedArray.getColor(styleable.getEndColorStyleable(), NO_COLOR)};
            }
        }

        mUseLevel = typedArray.getBoolean(styleable.getUseLevelStyleable(), false);
        mAngle = (int) typedArray.getFloat(styleable.getAngleStyleable(), 0);
        mGradientType = typedArray.getInt(styleable.getGradientTypeStyleable(), ShapeGradientType.LINEAR_GRADIENT);
        mCenterX = typedArray.getFloat(styleable.getCenterXStyleable(), 0.5f);
        mCenterY = typedArray.getFloat(styleable.getCenterYStyleable(), 0.5f);
        mGradientRadius = typedArray.getDimensionPixelSize(styleable.getGradientRadiusStyleable(), radius);

        mStrokeColor = typedArray.getColor(styleable.getStrokeColorStyleable(), NO_COLOR);
        if (typedArray.hasValue(styleable.getStrokePressedColorStyleable())) {
            mStrokePressedColor = typedArray.getColor(styleable.getStrokePressedColorStyleable(), NO_COLOR);
        }
        if (styleable.getStrokeCheckedColorStyleable() > 0 && typedArray.hasValue(styleable.getStrokeCheckedColorStyleable())) {
            mStrokeCheckedColor = typedArray.getColor(styleable.getStrokeCheckedColorStyleable(), NO_COLOR);
        }
        if (typedArray.hasValue(styleable.getStrokeDisabledColorStyleable())) {
            mStrokeDisabledColor = typedArray.getColor(styleable.getStrokeDisabledColorStyleable(), NO_COLOR);
        }
        if (typedArray.hasValue(styleable.getStrokeFocusedColorStyleable())) {
            mStrokeFocusedColor = typedArray.getColor(styleable.getStrokeFocusedColorStyleable(), NO_COLOR);
        }
        if (typedArray.hasValue(styleable.getStrokeSelectedColorStyleable())) {
            mStrokeSelectedColor = typedArray.getColor(styleable.getStrokeSelectedColorStyleable(), NO_COLOR);
        }

        mStrokeWidth = typedArray.getDimensionPixelSize(styleable.getStrokeWidthStyleable(), 0);
        mDashWidth = typedArray.getDimensionPixelSize(styleable.getDashWidthStyleable(), 0);
        mDashGap = typedArray.getDimensionPixelSize(styleable.getDashGapStyleable(), 0);

        mInnerRadius = typedArray.getDimensionPixelOffset(styleable.getInnerRadiusStyleable(), -1);
        mInnerRadiusRatio = typedArray.getFloat(styleable.getInnerRadiusRatioStyleable(), 3.0f);
        mThickness = typedArray.getDimensionPixelOffset(styleable.getThicknessStyleable(), -1);
        mThicknessRatio = typedArray.getFloat(styleable.getThicknessRatioStyleable(), 9.0f);

        mShadowSize = typedArray.getDimensionPixelSize(styleable.getShadowSizeStyleable(), 0);
        mShadowColor = typedArray.getColor(styleable.getShadowColorStyleable(), 0x10000000);
        mShadowOffsetX = typedArray.getDimensionPixelOffset(styleable.getShadowOffsetXStyleable(), 0);
        mShadowOffsetY = typedArray.getDimensionPixelOffset(styleable.getShadowOffsetYStyleable(), 0);
    }

    public ShapeDrawableBuilder setShape(int shape) {
        mShape = shape;
        return this;
    }

    public int getShape() {
        return mShape;
    }

    public ShapeDrawableBuilder setShapeWidth(int width) {
        mShapeWidth = width;
        return this;
    }

    public int getShapeWidth() {
        return mShapeWidth;
    }

    public ShapeDrawableBuilder setShapeHeight(int height) {
        mShapeHeight = height;
        return this;
    }

    public int getShapeHeight() {
        return mShapeHeight;
    }

    public ShapeDrawableBuilder setSolidColor(int color) {
        mSolidColor = color;
        clearGradientColors();
        return this;
    }

    public int getSolidColor() {
        return mSolidColor;
    }

    public ShapeDrawableBuilder setSolidPressedColor(Integer color) {
        mSolidPressedColor = color;
        return this;
    }

    @Nullable
    public Integer getSolidPressedColor() {
        return mSolidPressedColor;
    }

    public ShapeDrawableBuilder setSolidCheckedColor(Integer color) {
        mSolidCheckedColor = color;
        return this;
    }

    @Nullable
    public Integer getSolidCheckedColor() {
        return mSolidCheckedColor;
    }

    public ShapeDrawableBuilder setSolidDisabledColor(Integer color) {
        mSolidDisabledColor = color;
        return this;
    }

    @Nullable
    public Integer getSolidDisabledColor() {
        return mSolidDisabledColor;
    }

    public ShapeDrawableBuilder setSolidFocusedColor(Integer color) {
        mSolidFocusedColor = color;
        return this;
    }

    @Nullable
    public Integer getSolidFocusedColor() {
        return mSolidFocusedColor;
    }

    public ShapeDrawableBuilder setSolidSelectedColor(Integer color) {
        mSolidSelectedColor = color;
        return this;
    }

    @Nullable
    public Integer getSolidSelectedColor() {
        return mSolidSelectedColor;
    }

    public ShapeDrawableBuilder setRadius(float radius) {
        return setRadius(radius, radius, radius, radius);
    }

    public ShapeDrawableBuilder setRadius(float topLeftRadius, float topRightRadius, float bottomLeftRadius, float bottomRightRadius) {
        mTopLeftRadius = topLeftRadius;
        mTopRightRadius = topRightRadius;
        mBottomLeftRadius = bottomLeftRadius;
        mBottomRightRadius = bottomRightRadius;
        return this;
    }

    public float getTopLeftRadius() {
        return mTopLeftRadius;
    }

    public float getTopRightRadius() {
        return mTopRightRadius;
    }

    public float getBottomLeftRadius() {
        return mBottomLeftRadius;
    }

    public float getBottomRightRadius() {
        return mBottomRightRadius;
    }

    public ShapeDrawableBuilder setGradientColors(int startColor, int endColor) {
        return setGradientColors(new int[]{startColor, endColor});
    }

    public ShapeDrawableBuilder setGradientColors(int startColor, int centerColor, int endColor) {
        return setGradientColors(new int[]{startColor, centerColor, endColor});
    }

    public ShapeDrawableBuilder setGradientColors(int[] colors) {
        mGradientColors = colors;
        return this;
    }

    @Nullable
    public int[] getGradientColors() {
        return mGradientColors;
    }

    public boolean isGradientColors() {
        return mGradientColors != null &&
                mGradientColors.length > 0;
    }

    public void clearGradientColors() {
        mGradientColors = null;
    }

    public ShapeDrawableBuilder setUseLevel(boolean useLevel) {
        mUseLevel = useLevel;
        return this;
    }

    public boolean isUseLevel() {
        return mUseLevel;
    }

    public ShapeDrawableBuilder setAngle(int angle) {
        mAngle = angle;
        return this;
    }

    public int getAngle() {
        return mAngle;
    }

    public ShapeDrawableBuilder setGradientType(int type) {
        mGradientType = type;
        return this;
    }

    public int getGradientType() {
        return mGradientType;
    }

    public ShapeDrawableBuilder setCenterX(float x) {
        mCenterX = x;
        return this;
    }

    public float getCenterX() {
        return mCenterX;
    }

    public ShapeDrawableBuilder setCenterY(float y) {
        mCenterY = y;
        return this;
    }

    public float getCenterY() {
        return mCenterY;
    }

    public ShapeDrawableBuilder setGradientRadius(int radius) {
        mGradientRadius = radius;
        return this;
    }

    public int getGradientRadius() {
        return mGradientRadius;
    }

    public ShapeDrawableBuilder setStrokeColor(int color) {
        mStrokeColor = color;
        return this;
    }

    public int getStrokeColor() {
        return mStrokeColor;
    }

    public ShapeDrawableBuilder setStrokePressedColor(Integer color) {
        mStrokePressedColor = color;
        return this;
    }

    @Nullable
    public Integer getStrokePressedColor() {
        return mStrokePressedColor;
    }

    public ShapeDrawableBuilder setStrokeCheckedColor(Integer color) {
        mStrokeCheckedColor = color;
        return this;
    }

    @Nullable
    public Integer getStrokeCheckedColor() {
        return mStrokeCheckedColor;
    }

    public ShapeDrawableBuilder setStrokeDisabledColor(Integer color) {
        mStrokeDisabledColor = color;
        return this;
    }

    @Nullable
    public Integer getStrokeDisabledColor() {
        return mStrokeDisabledColor;
    }

    public ShapeDrawableBuilder setStrokeFocusedColor(Integer color) {
        mStrokeFocusedColor = color;
        return this;
    }

    @Nullable
    public Integer getStrokeFocusedColor() {
        return mStrokeFocusedColor;
    }

    public ShapeDrawableBuilder setStrokeSelectedColor(Integer color) {
        mStrokeSelectedColor = color;
        return this;
    }

    @Nullable
    public Integer getStrokeSelectedColor() {
        return mStrokeSelectedColor;
    }

    public ShapeDrawableBuilder setStrokeWidth(int width) {
        mStrokeWidth = width;
        return this;
    }

    public int getStrokeWidth() {
        return mStrokeWidth;
    }

    public ShapeDrawableBuilder setDashWidth(int width) {
        mDashWidth = width;
        return this;
    }

    public int getDashWidth() {
        return mDashWidth;
    }

    public ShapeDrawableBuilder setDashGap(int gap) {
        mDashGap = gap;
        return this;
    }

    public int getDashGap() {
        return mDashGap;
    }

    public boolean isDashLineEnable() {
        return mDashGap > 0;
    }

    public ShapeDrawableBuilder setInnerRadius(int radius) {
        mInnerRadius = radius;
        return this;
    }

    public int getInnerRadius() {
        return mInnerRadius;
    }

    public ShapeDrawableBuilder setInnerRadiusRatio(float ratio) {
        mInnerRadiusRatio = ratio;
        return this;
    }

    public float getInnerRadiusRatio() {
        return mInnerRadiusRatio;
    }

    public ShapeDrawableBuilder setThickness(int size) {
        mThickness = size;
        return this;
    }

    public int getThickness() {
        return mThickness;
    }

    public ShapeDrawableBuilder setThicknessRatio(float ratio) {
        mThicknessRatio = ratio;
        return this;
    }

    public float getThicknessRatio() {
        return mThicknessRatio;
    }

    public boolean isShadowEnable() {
        return mShadowSize > 0;
    }

    public ShapeDrawableBuilder setShadowSize(int size) {
        mShadowSize = size;
        return this;
    }

    public int getShadowSize() {
        return mShadowSize;
    }

    public ShapeDrawableBuilder setShadowColor(int color) {
        mShadowColor = color;
        return this;
    }

    public int getShadowColor() {
        return mShadowColor;
    }

    public ShapeDrawableBuilder setShadowOffsetX(int offsetX) {
        mShadowOffsetX = offsetX;
        return this;
    }

    public int getShadowOffsetX() {
        return mShadowOffsetX;
    }

    public ShapeDrawableBuilder setShadowOffsetY(int offsetY) {
        mShadowOffsetY = offsetY;
        return this;
    }

    public int getShadowOffsetY() {
        return mShadowOffsetY;
    }

    public Drawable buildBackgroundDrawable() {
        if (!isGradientColors() && mSolidColor == NO_COLOR && mStrokeColor == NO_COLOR) {
            return null;
        }

        ShapeDrawable defaultDrawable = createShapeDrawable(mSolidColor, mStrokeColor);
        // 判断是否设置了渐变色
        if (isGradientColors()) {
            defaultDrawable.setGradientColors(mGradientColors);
        }

        if (mSolidPressedColor != null && mStrokePressedColor != null &&
                mSolidCheckedColor != null && mStrokeCheckedColor != null &&
                mSolidDisabledColor != null && mStrokeDisabledColor != null &&
                mSolidFocusedColor != null && mStrokeFocusedColor != null &&
                mSolidSelectedColor != null && mStrokeSelectedColor != null) {
            return defaultDrawable;
        }

        StateListDrawable drawable = new StateListDrawable();
        if (mSolidPressedColor != null || mStrokePressedColor != null) {
            drawable.addState(new int[]{android.R.attr.state_pressed}, createShapeDrawable(
                    mSolidPressedColor != null ? mSolidPressedColor : mSolidColor,
                    mStrokePressedColor != null ? mStrokePressedColor : mStrokeColor));
        }
        if (mSolidCheckedColor != null || mStrokeCheckedColor != null) {
            drawable.addState(new int[]{android.R.attr.state_checked}, createShapeDrawable(
                    mSolidCheckedColor != null ? mSolidCheckedColor : mSolidColor,
                    mStrokeCheckedColor != null ? mStrokeCheckedColor : mStrokeColor));
        }
        if (mSolidDisabledColor != null || mStrokeDisabledColor != null) {
            drawable.addState(new int[]{-android.R.attr.state_enabled}, createShapeDrawable(
                    mSolidDisabledColor != null ? mSolidDisabledColor : mSolidColor,
                    mStrokeDisabledColor != null ? mStrokeDisabledColor : mStrokeColor));
        }
        if (mSolidFocusedColor != null || mStrokeFocusedColor != null) {
            drawable.addState(new int[]{android.R.attr.state_focused}, createShapeDrawable(
                    mSolidFocusedColor != null ? mSolidFocusedColor : mSolidColor,
                    mStrokeFocusedColor != null ? mStrokeFocusedColor : mStrokeColor));
        }
        if (mSolidSelectedColor != null || mStrokeSelectedColor != null) {
            drawable.addState(new int[]{android.R.attr.state_selected}, createShapeDrawable(
                    mSolidSelectedColor != null ? mSolidSelectedColor : mSolidColor,
                    mStrokeSelectedColor != null ? mStrokeSelectedColor : mStrokeColor));
        }

        drawable.addState(new int[]{}, defaultDrawable);
        return drawable;
    }

    public ShapeDrawable createShapeDrawable(int solidColor, int strokeColor) {

        ShapeDrawable drawable = new ShapeDrawable();

        drawable.setShape(mShape)
                .setSize(mShapeWidth, mShapeHeight)
                .setRadius(mTopLeftRadius, mTopRightRadius, mBottomLeftRadius, mBottomRightRadius)
                .setSolidColor(solidColor)
                .setUseLevel(mUseLevel)
                .setStroke(mStrokeWidth, strokeColor, mDashWidth, mDashGap);

        drawable.setGradientAngle(mAngle)
                .setGradientType(mGradientType)
                .setGradientRadius(mGradientRadius)
                .setGradientCenter(mCenterX, mCenterY);

        drawable.setInnerRadiusRatio(mInnerRadiusRatio)
                .setInnerRadius(mInnerRadius)
                .setThicknessRatio(mThicknessRatio)
                .setThickness(mThickness);

        drawable.setShadowSize(mShadowSize)
                .setShadowColor(mShadowColor)
                .setShadowOffsetX(mShadowOffsetX)
                .setShadowOffsetY(mShadowOffsetY);

        return drawable;
    }

    public void intoBackground() {
        Drawable drawable = buildBackgroundDrawable();
        if (drawable == null) {
            return;
        }
        if (isDashLineEnable() || isShadowEnable()) {
            // 需要关闭硬件加速，否则虚线或者阴影在某些手机上面无法生效
            mView.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        }
        mView.setBackground(drawable);
    }
}