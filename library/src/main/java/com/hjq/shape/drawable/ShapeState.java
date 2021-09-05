package com.hjq.shape.drawable;

import android.content.res.Resources;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;

/**
 *    author : Android 轮子哥
 *    github : https://github.com/getActivity/ToastUtils
 *    time   : 2021/08/15
 *    desc   : ShapeDrawable 参数构建
 */
public class ShapeState extends Drawable.ConstantState {

    public int mChangingConfigurations;
    public int mShapeType = ShapeType.RECTANGLE;
    public int mGradientType = ShapeGradientType.LINEAR_GRADIENT;
    public ShapeGradientOrientation mGradientOrientation = ShapeGradientOrientation.TOP_BOTTOM;
    public int[] mGradientColors;
    public int[] mTempColors; // no need to copy
    public float[] mTempPositions; // no need to copy
    public float[] mPositions;
    public boolean mHasSolidColor;
    public int mSolidColor;
    public int mStrokeWidth = -1;   // if >= 0 use stroking.
    public int mStrokeColor;
    public float mStrokeDashWidth;
    public float mStrokeDashGap;
    public float mRadius;    // use this if mRadiusArray is null
    public float[] mRadiusArray;
    public Rect mPadding;
    public int mWidth = -1;
    public int mHeight = -1;
    public float mInnerRadiusRatio;
    public float mThicknessRatio;
    public int mInnerRadius;
    public int mThickness;
    public float mCenterX = 0.5f;
    public float mCenterY = 0.5f;
    public float mGradientRadius = 0.5f;
    public boolean mUseLevel;
    public boolean mUseLevelForShape;
    public boolean mOpaque;

    public int mShadowSize;
    public int mShadowColor;
    public int mShadowOffsetX;
    public int mShadowOffsetY;

    public ShapeState() {}

    public ShapeState(ShapeState state) {
        mChangingConfigurations = state.mChangingConfigurations;
        mShapeType = state.mShapeType;
        mGradientType = state.mGradientType;
        mGradientOrientation = state.mGradientOrientation;
        if (state.mGradientColors != null) {
            mGradientColors = state.mGradientColors.clone();
        }
        if (state.mPositions != null) {
            mPositions = state.mPositions.clone();
        }
        mHasSolidColor = state.mHasSolidColor;
        mSolidColor = state.mSolidColor;
        mStrokeWidth = state.mStrokeWidth;
        mStrokeColor = state.mStrokeColor;
        mStrokeDashWidth = state.mStrokeDashWidth;
        mStrokeDashGap = state.mStrokeDashGap;
        mRadius = state.mRadius;
        if (state.mRadiusArray != null) {
            mRadiusArray = state.mRadiusArray.clone();
        }
        if (state.mPadding != null) {
            mPadding = new Rect(state.mPadding);
        }
        mWidth = state.mWidth;
        mHeight = state.mHeight;
        mInnerRadiusRatio = state.mInnerRadiusRatio;
        mThicknessRatio = state.mThicknessRatio;
        mInnerRadius = state.mInnerRadius;
        mThickness = state.mThickness;
        mCenterX = state.mCenterX;
        mCenterY = state.mCenterY;
        mGradientRadius = state.mGradientRadius;
        mUseLevel = state.mUseLevel;
        mUseLevelForShape = state.mUseLevelForShape;
        mOpaque = state.mOpaque;

        mShadowSize = state.mShadowSize;
        mShadowColor = state.mShadowColor;
        mShadowOffsetX = state.mShadowOffsetX;
        mShadowOffsetY = state.mShadowOffsetY;
    }

    @Override
    public Drawable newDrawable() {
        return new ShapeDrawable(this);
    }

    @Override
    public Drawable newDrawable(Resources res) {
        return new ShapeDrawable(this);
    }

    @Override
    public int getChangingConfigurations() {
        return mChangingConfigurations;
    }

    public void setShape(int shape) {
        mShapeType = shape;
        computeOpacity();
    }

    public void setGradientType(int gradientType) {
        mGradientType = gradientType;
    }

    public void setGradientCenter(float x, float y) {
        mCenterX = x;
        mCenterY = y;
    }

    public void setGradientColor(int[] colors) {
        mHasSolidColor = false;
        mGradientColors = colors;
        computeOpacity();
    }

    public void setSolidColor(int argb) {
        mHasSolidColor = true;
        mSolidColor = argb;
        mGradientColors = null;
        computeOpacity();
    }

    private void computeOpacity() {
        if (mShapeType != ShapeType.RECTANGLE) {
            mOpaque = false;
            return;
        }

        if (mRadius > 0 || mRadiusArray != null) {
            mOpaque = false;
            return;
        }

        if (mStrokeWidth > 0 && !isOpaque(mStrokeColor)) {
            mOpaque = false;
            return;
        }

        if (mHasSolidColor) {
            mOpaque = isOpaque(mSolidColor);
            return;
        }

        if (mGradientColors != null) {
            for (int color : mGradientColors) {
                if (!isOpaque(color)) {
                    mOpaque = false;
                    return;
                }
            }
        }

        mOpaque = true;
    }

    private static boolean isOpaque(int color) {
        return ((color >> 24) & 0xff) == 0xff;
    }

    public void setStroke(int width, int color) {
        mStrokeWidth = width;
        mStrokeColor = color;
        computeOpacity();
    }

    public void setStroke(int width, int color, float dashWidth, float dashGap) {
        mStrokeWidth = width;
        mStrokeColor = color;
        mStrokeDashWidth = dashWidth;
        mStrokeDashGap = dashGap;
        computeOpacity();
    }

    public void setCornerRadius(float radius) {
        if (radius < 0) {
            radius = 0;
        }
        mRadius = radius;
        mRadiusArray = null;
    }

    public void setCornerRadii(float[] radii) {
        mRadiusArray = radii;
        if (radii == null) {
            mRadius = 0;
        }
    }

    public void setSize(int width, int height) {
        mWidth = width;
        mHeight = height;
    }

    public void setGradientRadius(float gradientRadius) {
        mGradientRadius = gradientRadius;
    }

    public void setShadowColor(int color) {
        mShadowColor = color;
    }

    public void setShadowSize(int size) {
        mShadowSize = size;
    }

    public void setShadowOffsetX(int offsetX) {
        mShadowOffsetX = offsetX;
    }

    public void setShadowOffsetY(int offsetY) {
        mShadowOffsetY = offsetY;
    }
}