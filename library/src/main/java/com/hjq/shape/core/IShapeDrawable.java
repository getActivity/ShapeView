package com.hjq.shape.core;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.view.View;

/**
 *    author : Android 轮子哥
 *    github : https://github.com/getActivity/ShapeView
 *    time   : 2021/07/17
 *    desc   : ShapeDrawable 参数接口
 */
public interface IShapeDrawable<V extends View> {

    int DEFAULT_SHAPE_TYPE = ShapeType.RECTANGLE;
    int DEFAULT_SHAPE_WIDTH = -1;
    int DEFAULT_SHAPE_HEIGHT = -1;
    int DEFAULT_SHAPE_SOLID_COLOR = Color.TRANSPARENT;
    int DEFAULT_SHAPE_RADIUS = 0;
    boolean DEFAULT_SHAPE_USE_LEVEL = false;
    int DEFAULT_SHAPE_GRADIENT_TYPE = ShapeGradientType.LINEAR_GRADIENT;
    int DEFAULT_SHAPE_ANGLE = 0;
    float DEFAULT_SHAPE_CENTER_X = 0.5f;
    float DEFAULT_SHAPE_CENTER_Y = 0.5f;
    int DEFAULT_SHAPE_STROKE_COLOR = Color.TRANSPARENT;
    int DEFAULT_SHAPE_STROKE_WIDTH = 0;
    int DEFAULT_SHAPE_DASH_WIDTH = 0;
    int DEFAULT_SHAPE_DASH_GAP = 0;

    float DEFAULT_SHAPE_INNER_RADIUS_RATIO = 3.0f;
    int DEFAULT_SHAPE_INNER_RADIUS = -1;
    float DEFAULT_SHAPE_THICKNESS_RATIO = 9.0f;
    int DEFAULT_SHAPE_THICKNESS = -1;

    int DEFAULT_SHAPE_SHADOW_SIZE = 0;
    int DEFAULT_SHAPE_SHADOW_COLOR = 0x10000000;
    int DEFAULT_SHAPE_SHADOW_OFFSET_X = 0;
    int DEFAULT_SHAPE_SHADOW_OFFSET_Y = 0;

    V setShapeType(int type);

    int getShapeType();

    V setShapeWidth(int width);

    int getShapeWidth();

    V setShapeHeight(int height);

    int getShapeHeight();

    V setSolidColor(int color);

    int getSolidColor();

    V setSolidPressedColor(int color);

    int getSolidPressedColor();

    default V setSolidCheckedColor(int color) {
        return setSolidColor(color);
    }

    default int getSolidCheckedColor() {
        return getSolidColor();
    }

    V setSolidDisabledColor(int color);

    int getSolidDisabledColor();

    V setSolidFocusedColor(int color);

    int getSolidFocusedColor();

    V setSolidSelectedColor(int color);

    int getSolidSelectedColor();

    default V setRadius(int radius) {
        setTopLeftRadius(radius);
        setTopRightRadius(radius);
        setBottomLeftRadius(radius);
        return setBottomRightRadius(radius);
    }

    V setTopLeftRadius(int radius);

    int getTopLeftRadius();

    V setTopRightRadius(int radius);

    int getTopRightRadius();

    V setBottomLeftRadius(int radius);

    int getBottomLeftRadius();

    V setBottomRightRadius(int radius);

    int getBottomRightRadius();

    V setStartColor(int color);

    int getStartColor();

    V setCenterColor(int color);

    int getCenterColor();

    V setEndColor(int color);

    int getEndColor();

    default boolean isGradientColor() {
        return getSolidColor() != getStartColor() &&
                getSolidColor() != getEndColor();
    }

    default void clearGradientColor() {
        setStartColor(getSolidColor());
        setCenterColor(getSolidColor());
        setEndColor(getSolidColor());
    }

    V setUseLevel(boolean useLevel);

    boolean isUseLevel();

    V setAngle(int angle);

    int getAngle();

    V setGradientType(int type);

    int getGradientType();

    V setCenterX(float x);

    float getCenterX();

    V setCenterY(float y);

    float getCenterY();

    V setGradientRadius(int radius);

    int getGradientRadius();

    V setStrokeColor(int color);

    int getStrokeColor();

    V setStrokePressedColor(int color);

    int getStrokePressedColor();

    default V setStrokeCheckedColor(int color) {
        return setStrokeColor(color);
    }

    default int getStrokeCheckedColor() {
        return getStrokeColor();
    }

    V setStrokeDisabledColor(int color);

    int getStrokeDisabledColor();

    V setStrokeFocusedColor(int color);

    int getStrokeFocusedColor();

    V setStrokeSelectedColor(int color);

    int getStrokeSelectedColor();

    V setStrokeWidth(int width);

    int getStrokeWidth();

    V setDashWidth(int width);

    int getDashWidth();

    V setDashGap(int gap);

    int getDashGap();

    V setInnerRadius(int radius);

    int getInnerRadius();

    V setInnerRadiusRatio(float ratio);

    float getInnerRadiusRatio();

    V setThickness(int size);

    int getThickness();

    V setThicknessRatio(float ratio);

    float getThicknessRatio();

    default boolean isShadowEnable() {
        return getShadowSize() > 0;
    }

    V setShadowSize(int size);

    int getShadowSize();

    V setShadowColor(int color);

    int getShadowColor();

    V setShadowOffsetX(int offsetX);

    int getShadowOffsetX();

    V setShadowOffsetY(int offsetY);

    int getShadowOffsetY();

    default Drawable buildBackgroundDrawable() {
        if (!isGradientColor() && getSolidColor() == DEFAULT_SHAPE_SOLID_COLOR && getStrokeColor() == DEFAULT_SHAPE_STROKE_COLOR) {
            return null;
        }

        if (isGradientColor() || getSolidColor() == getSolidPressedColor() && getStrokeColor() == getStrokePressedColor() &&
                getSolidColor() == getSolidCheckedColor() && getStrokeColor() == getStrokeCheckedColor() &&
                getSolidColor() == getSolidDisabledColor() && getStrokeColor() == getStrokeDisabledColor() &&
                getSolidColor() == getSolidFocusedColor() && getStrokeColor() == getStrokeFocusedColor() &&
                getSolidColor() == getSolidSelectedColor() && getStrokeColor() == getStrokeSelectedColor()) {
            return createShapeDrawable(getSolidColor(), getStrokeColor());
        }

        StateListDrawable drawable = new StateListDrawable();
        if (getSolidColor() != getSolidPressedColor() || getStrokeColor() != getStrokePressedColor()) {
            drawable.addState(new int[]{android.R.attr.state_pressed}, createShapeDrawable(getSolidPressedColor(), getStrokePressedColor()));
        }
        if (getSolidColor() != getSolidCheckedColor() || getStrokeColor() != getStrokeCheckedColor()) {
            drawable.addState(new int[]{android.R.attr.state_checked}, createShapeDrawable(getSolidCheckedColor(), getStrokeCheckedColor()));
        }
        if (getSolidColor() != getSolidDisabledColor() || getStrokeColor() != getStrokeDisabledColor()) {
            drawable.addState(new int[]{-android.R.attr.state_enabled}, createShapeDrawable(getSolidDisabledColor(), getStrokeDisabledColor()));
        }
        if (getSolidColor() != getSolidFocusedColor() || getStrokeColor() != getStrokeFocusedColor()) {
            drawable.addState(new int[]{android.R.attr.state_focused}, createShapeDrawable(getSolidFocusedColor(), getStrokeFocusedColor()));
        }
        if (getSolidColor() != getSolidSelectedColor() || getStrokeColor() != getStrokeSelectedColor()) {
            drawable.addState(new int[]{android.R.attr.state_selected}, createShapeDrawable(getSolidSelectedColor(), getStrokeSelectedColor()));
        }
        drawable.addState(new int[]{}, createShapeDrawable(getSolidColor(), getStrokeColor()));
        return drawable;
    }

    default Drawable createShapeDrawable(int solidColor, int strokeColor) {
        ShapeDrawable drawable = new ShapeDrawable();
        drawable.setShape(getShapeType());
        drawable.setSize(getShapeWidth(), getShapeHeight());
        drawable.setCornerRadii(new float[]{getTopLeftRadius(), getTopLeftRadius(), getTopRightRadius(), getTopRightRadius(),
                getBottomRightRadius(), getBottomRightRadius(), getBottomLeftRadius(), getBottomLeftRadius()});

        int startColor = getStartColor();
        int centerColor = getCenterColor();
        int endColor = getEndColor();
        if (isGradientColor()) {
            if (centerColor == solidColor) {
                drawable.setColors(new int[]{startColor, endColor});
            } else {
                drawable.setColors(new int[]{startColor, centerColor, endColor});
            }
        } else {
            drawable.setColor(solidColor);
        }

        drawable.setGradientCenter(getCenterX(), getCenterY());
        drawable.setUseLevel(isUseLevel());
        drawable.setGradientAngle(getAngle());

        drawable.setGradientType(getGradientType());
        drawable.setGradientRadius(getGradientRadius());
        drawable.setStroke(getStrokeWidth(), strokeColor, getDashWidth(), getDashGap());

        if (getShapeType() == ShapeType.RING) {
            drawable.setInnerRadiusRatio(getInnerRadiusRatio());
            drawable.setInnerRadius(getInnerRadius());
            drawable.setThicknessRatio(getThicknessRatio());
            drawable.setThickness(getThickness());
        }

        int shadowSize = getShadowSize();
        if (isShadowEnable()) {
            drawable.setShadowSize(shadowSize);
            drawable.setShadowColor(getShadowColor());
            drawable.setShadowOffsetX(getShadowOffsetX());
            drawable.setShadowOffsetY(getShadowOffsetY());
        }
        return drawable;
    }

    void intoBackground();
}