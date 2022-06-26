package com.hjq.shape.builder;

import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.View;

import com.hjq.shape.drawable.ExtendStateListDrawable;
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

    private int[] mSolidGradientColors;
    private int[] mStrokeGradientColors;
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

    private int mShadowSize;
    private int mShadowColor;
    private int mShadowOffsetX;
    private int mShadowOffsetY;

    private int mInnerRadius;
    private float mInnerRadiusRatio;
    private int mThickness;
    private float mThicknessRatio;

    private int mLineGravity;

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

        if (typedArray.hasValue(styleable.getSolidStartColorStyleable()) && typedArray.hasValue(styleable.getSolidEndColorStyleable())) {
            if (typedArray.hasValue(styleable.getSolidCenterColorStyleable())) {
                mSolidGradientColors = new int[] {typedArray.getColor(styleable.getSolidStartColorStyleable(), NO_COLOR),
                        typedArray.getColor(styleable.getSolidCenterColorStyleable(), NO_COLOR),
                        typedArray.getColor(styleable.getSolidEndColorStyleable(), NO_COLOR)};
            } else {
                mSolidGradientColors = new int[] {typedArray.getColor(styleable.getSolidStartColorStyleable(), NO_COLOR),
                        typedArray.getColor(styleable.getSolidEndColorStyleable(), NO_COLOR)};
            }
        }

        if (typedArray.hasValue(styleable.getStrokeStartColorStyleable()) && typedArray.hasValue(styleable.getStrokeEndColorStyleable())) {
            if (typedArray.hasValue(styleable.getStrokeCenterColorStyleable())) {
                mStrokeGradientColors = new int[] {typedArray.getColor(styleable.getStrokeStartColorStyleable(), NO_COLOR),
                        typedArray.getColor(styleable.getStrokeCenterColorStyleable(), NO_COLOR),
                        typedArray.getColor(styleable.getStrokeEndColorStyleable(), NO_COLOR)};
            } else {
                mStrokeGradientColors = new int[] {typedArray.getColor(styleable.getStrokeStartColorStyleable(), NO_COLOR),
                        typedArray.getColor(styleable.getStrokeEndColorStyleable(), NO_COLOR)};
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

        mShadowSize = typedArray.getDimensionPixelSize(styleable.getShadowSizeStyleable(), 0);
        mShadowColor = typedArray.getColor(styleable.getShadowColorStyleable(), 0x10000000);
        mShadowOffsetX = typedArray.getDimensionPixelOffset(styleable.getShadowOffsetXStyleable(), 0);
        mShadowOffsetY = typedArray.getDimensionPixelOffset(styleable.getShadowOffsetYStyleable(), 0);

        mInnerRadius = typedArray.getDimensionPixelOffset(styleable.getInnerRadiusStyleable(), -1);
        mInnerRadiusRatio = typedArray.getFloat(styleable.getInnerRadiusRatioStyleable(), 3.0f);
        mThickness = typedArray.getDimensionPixelOffset(styleable.getThicknessStyleable(), -1);
        mThicknessRatio = typedArray.getFloat(styleable.getThicknessRatioStyleable(), 9.0f);

        mLineGravity = typedArray.getInt(styleable.getLineGravityStyleable(), Gravity.CENTER);
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
        clearSolidGradientColors();
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

    public ShapeDrawableBuilder setTopLeftRadius(float radius) {
        mTopLeftRadius = radius;
        return this;
    }

    public float getTopLeftRadius() {
        return mTopLeftRadius;
    }

    public ShapeDrawableBuilder setTopRightRadius(float radius) {
        mTopRightRadius = radius;
        return this;
    }

    public float getTopRightRadius() {
        return mTopRightRadius;
    }

    public ShapeDrawableBuilder setBottomLeftRadius(float radius) {
        mBottomLeftRadius = radius;
        return this;
    }

    public float getBottomLeftRadius() {
        return mBottomLeftRadius;
    }

    public ShapeDrawableBuilder setBottomRightRadius(float radius) {
        mBottomRightRadius = radius;
        return this;
    }

    public float getBottomRightRadius() {
        return mBottomRightRadius;
    }

    public ShapeDrawableBuilder setSolidGradientColors(int startColor, int endColor) {
        return setSolidGradientColors(new int[]{startColor, endColor});
    }

    public ShapeDrawableBuilder setSolidGradientColors(int startColor, int centerColor, int endColor) {
        return setSolidGradientColors(new int[]{startColor, centerColor, endColor});
    }

    public ShapeDrawableBuilder setSolidGradientColors(int[] colors) {
        mSolidGradientColors = colors;
        return this;
    }

    @Nullable
    public int[] getSolidGradientColors() {
        return mSolidGradientColors;
    }

    public boolean isSolidGradientColors() {
        return mSolidGradientColors != null &&
                mSolidGradientColors.length > 0;
    }

    public void clearSolidGradientColors() {
        mSolidGradientColors = null;
    }

    public ShapeDrawableBuilder setStrokeGradientColors(int startColor, int endColor) {
        return setStrokeGradientColors(new int[]{startColor, endColor});
    }

    public ShapeDrawableBuilder setStrokeGradientColors(int startColor, int centerColor, int endColor) {
        return setStrokeGradientColors(new int[]{startColor, centerColor, endColor});
    }

    public ShapeDrawableBuilder setStrokeGradientColors(int[] colors) {
        mStrokeGradientColors = colors;
        return this;
    }

    @Nullable
    public int[] getStrokeGradientColors() {
        return mStrokeGradientColors;
    }

    public boolean isStrokeGradientColors() {
        return mStrokeGradientColors != null &&
                mStrokeGradientColors.length > 0;
    }

    public void clearStrokeGradientColors() {
        mStrokeGradientColors = null;
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
        clearStrokeGradientColors();
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
        boolean hasSolidColorState = mSolidPressedColor != null || mSolidCheckedColor != null ||
                mSolidDisabledColor != null || mSolidFocusedColor != null || mSolidSelectedColor != null;

        boolean hasStrokeColorState = mStrokePressedColor != null || mStrokeCheckedColor != null ||
                mStrokeDisabledColor != null || mStrokeFocusedColor != null || mStrokeSelectedColor != null;

        if (!isSolidGradientColors() && !isStrokeGradientColors() &&
                mSolidColor == NO_COLOR && !hasSolidColorState && mStrokeColor == NO_COLOR && !hasStrokeColorState) {
            // 啥都没有设置，直接 return
            return null;
        }

        ShapeDrawable defaultDrawable;

        Drawable viewBackground = mView.getBackground();
        if (viewBackground instanceof ExtendStateListDrawable) {
            defaultDrawable = convertShapeDrawable(((ExtendStateListDrawable) viewBackground).getDefaultDrawable());
        } else {
            defaultDrawable = convertShapeDrawable(viewBackground);
        }

        refreshShapeDrawable(defaultDrawable, null, null);

        if (!hasSolidColorState && !hasStrokeColorState) {
            return defaultDrawable;
        }

        ExtendStateListDrawable stateListDrawable = new ExtendStateListDrawable();
        if (mSolidPressedColor != null || mStrokePressedColor != null) {
            ShapeDrawable drawable = convertShapeDrawable(stateListDrawable.getPressedDrawable());
            refreshShapeDrawable(drawable, mSolidPressedColor, mStrokePressedColor);
            stateListDrawable.setPressedDrawable(drawable);
        }

        if (mSolidCheckedColor != null || mStrokeCheckedColor != null) {
            ShapeDrawable drawable = convertShapeDrawable(stateListDrawable.getCheckDrawable());
            refreshShapeDrawable(drawable, mSolidCheckedColor, mStrokeCheckedColor);
            stateListDrawable.setCheckDrawable(drawable);
        }

        if (mSolidDisabledColor != null || mStrokeDisabledColor != null) {
            ShapeDrawable drawable = convertShapeDrawable(stateListDrawable.getDisabledDrawable());
            refreshShapeDrawable(drawable, mSolidDisabledColor, mStrokeDisabledColor);
            stateListDrawable.setDisabledDrawable(drawable);
        }

        if (mSolidFocusedColor != null || mStrokeFocusedColor != null) {
            ShapeDrawable drawable = convertShapeDrawable(stateListDrawable.getFocusedDrawable());
            refreshShapeDrawable(drawable, mSolidFocusedColor, mStrokeFocusedColor);
            stateListDrawable.setFocusedDrawable(drawable);
        }

        if (mSolidSelectedColor != null || mStrokeSelectedColor != null) {
            ShapeDrawable drawable = convertShapeDrawable(stateListDrawable.getSelectDrawable());
            refreshShapeDrawable(drawable, mSolidSelectedColor, mStrokeSelectedColor);
            stateListDrawable.setSelectDrawable(drawable);
        }

        stateListDrawable.setDefaultDrawable(defaultDrawable);
        return stateListDrawable;
    }

    public void refreshShapeDrawable(ShapeDrawable drawable,
                                     @Nullable Integer solidStateColor,
                                     @Nullable Integer strokeStateColor) {
        drawable.setShape(mShape)
                .setSize(mShapeWidth, mShapeHeight)
                .setRadius(mTopLeftRadius, mTopRightRadius, mBottomLeftRadius, mBottomRightRadius)
                .setUseLevel(mUseLevel)
                .setStrokeWidth(mStrokeWidth)
                .setStrokeDash(mDashWidth, mDashGap);

        drawable.setGradientAngle(mAngle)
                .setGradientType(mGradientType)
                .setGradientRadius(mGradientRadius)
                .setGradientCenter(mCenterX, mCenterY);

        drawable.setShadowSize(mShadowSize)
                .setShadowColor(mShadowColor)
                .setShadowOffsetX(mShadowOffsetX)
                .setShadowOffsetY(mShadowOffsetY);

        drawable.setInnerRadiusRatio(mInnerRadiusRatio)
                .setInnerRadius(mInnerRadius)
                .setThicknessRatio(mThicknessRatio)
                .setThickness(mThickness);

        drawable.setLineGravity(mLineGravity);

        // 填充色设置
        if (solidStateColor != null) {
            drawable.setSolidColor(solidStateColor);
        } else if (isSolidGradientColors()){
            drawable.setSolidColor(mSolidGradientColors);
        } else {
            drawable.setSolidColor(mSolidColor);
        }

        // 边框色设置
        if (strokeStateColor != null) {
            drawable.setStrokeColor(strokeStateColor);
        } else if (isStrokeGradientColors()) {
            drawable.setStrokeColor(mStrokeGradientColors);
        } else {
            drawable.setStrokeColor(mStrokeColor);
        }
    }

    @NonNull
    public ShapeDrawable convertShapeDrawable(Drawable drawable) {
        if (drawable instanceof ShapeDrawable) {
            return (ShapeDrawable) drawable;
        }
        return new ShapeDrawable();
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