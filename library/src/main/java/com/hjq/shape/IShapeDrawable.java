package com.hjq.shape;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;
import android.view.View;

/**
 *    author : Android 轮子哥
 *    github : https://github.com/getActivity/ShapeView
 *    time   : 2021/07/17
 *    desc   : Shape Drawable 接口规范
 */
public interface IShapeDrawable<V extends View> {

    int DEFAULT_SHAPE = GradientDrawable.RECTANGLE;
    int DEFAULT_SHAPE_WIDTH = -1;
    int DEFAULT_SHAPE_HEIGHT = -1;
    int DEFAULT_SHAPE_SOLID_COLOR = Color.TRANSPARENT;
    int DEFAULT_SHAPE_RADIUS = 0;
    int DEFAULT_SHAPE_START_COLOR = Color.TRANSPARENT;
    int DEFAULT_SHAPE_CENTER_COLOR = Color.TRANSPARENT;
    int DEFAULT_SHAPE_END_COLOR = Color.TRANSPARENT;
    boolean DEFAULT_SHAPE_USE_LEVEL = false;
    int DEFAULT_SHAPE_GRADIENT_TYPE = GradientDrawable.LINEAR_GRADIENT;
    int DEFAULT_SHAPE_ANGLE = 0;
    float DEFAULT_SHAPE_CENTER_X = 0.5f;
    float DEFAULT_SHAPE_CENTER_Y = 0.5f;
    int DEFAULT_SHAPE_STROKE_COLOR = Color.TRANSPARENT;
    int DEFAULT_SHAPE_STROKE_WIDTH = 0;
    int DEFAULT_SHAPE_DASH_WIDTH = 0;
    int DEFAULT_SHAPE_DASH_GAP = 0;

    V setShape(int shape);

    int getShape();

    V setShapeWidth(int width);

    int getShapeWidth();

    V setShapeHeight(int height);

    int getShapeHeight();

    V setSolidColor(int color);

    int getSolidColor();

    default V setSolidPressedColor(int color) {
        return setSolidColor(color);
    }

    default int getSolidPressedColor() {
        return getSolidColor();
    }

    default V setSolidCheckedColor(int color) {
        return setSolidColor(color);
    }

    default int getSolidCheckedColor() {
        return getSolidColor();
    }

    default V setSolidDisabledColor(int color) {
        return setSolidColor(color);
    }

    default int getSolidDisabledColor() {
        return getSolidColor();
    }

    default V setSolidFocusedColor(int color) {
        return setSolidColor(color);
    }

    default int getSolidFocusedColor() {
        return getSolidColor();
    }

    default V setSolidSelectedColor(int color) {
        return setSolidColor(color);
    }

    default int getSolidSelectedColor() {
        return getSolidColor();
    }

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

    default V setStrokePressedColor(int color) {
        return setStrokeColor(color);
    }

    default int getStrokePressedColor() {
        return getStrokeColor();
    }

    default V setStrokeCheckedColor(int color) {
        return setStrokeColor(color);
    }

    default int getStrokeCheckedColor() {
        return getStrokeColor();
    }

    default V setStrokeDisabledColor(int color) {
        return setStrokeColor(color);
    }

    default int getStrokeDisabledColor() {
        return getStrokeColor();
    }

    default V setStrokeFocusedColor(int color) {
        return setStrokeColor(color);
    }

    default int getStrokeFocusedColor() {
        return getStrokeColor();
    }

    default V setStrokeSelectedColor(int color) {
        return setStrokeColor(color);
    }

    default int getStrokeSelectedColor() {
        return getStrokeColor();
    }

    V setStrokeWidth(int width);

    int getStrokeWidth();

    V setDashWidth(int width);

    int getDashWidth();

    V setDashGap(int gap);

    int getDashGap();

    default Drawable buildDrawable() {
        if (getSolidColor() != getSolidPressedColor()  || getStrokeColor() != getStrokePressedColor() ||
                getSolidColor() != getSolidCheckedColor() || getStrokeColor() != getStrokeCheckedColor() ||
                getSolidColor() != getSolidDisabledColor() || getStrokeColor() != getStrokeDisabledColor() ||
                getSolidColor() != getSolidFocusedColor() || getStrokeColor() != getStrokeFocusedColor() ||
                getSolidColor() != getSolidSelectedColor() || getStrokeColor() != getStrokeSelectedColor()) {
            StateListDrawable drawable = new StateListDrawable();
            if (getSolidColor() != getSolidPressedColor() || getStrokeColor() != getStrokePressedColor()) {
                drawable.addState(new int[]{android.R.attr.state_pressed}, createDrawable(getSolidPressedColor(), getStrokePressedColor()));
            }
            if (getSolidColor() != getSolidCheckedColor() || getStrokeColor() != getStrokeCheckedColor()) {
                drawable.addState(new int[]{android.R.attr.state_checked}, createDrawable(getSolidCheckedColor(), getStrokeCheckedColor()));
            }
            if (getSolidColor() != getSolidDisabledColor() || getStrokeColor() != getStrokeDisabledColor()) {
                drawable.addState(new int[]{-android.R.attr.state_enabled}, createDrawable(getSolidDisabledColor(), getStrokeDisabledColor()));
            }
            if (getSolidColor() != getSolidFocusedColor() || getStrokeColor() != getStrokeFocusedColor()) {
                drawable.addState(new int[]{android.R.attr.state_focused}, createDrawable(getSolidFocusedColor(), getStrokeFocusedColor()));
            }
            if (getSolidColor() != getSolidSelectedColor() || getStrokeColor() != getStrokeSelectedColor()) {
                drawable.addState(new int[]{android.R.attr.state_selected}, createDrawable(getSolidSelectedColor(), getStrokeSelectedColor()));
            }
            drawable.addState(new int[]{}, createDrawable(getSolidColor(), getStrokeColor()));
            return drawable;
        }

        return createDrawable(getSolidColor(), getStrokeColor());
    }

    default Drawable createDrawable(int solidColor, int strokeColor) {
        GradientDrawable drawable = new GradientDrawable();
        drawable.setShape(getShape());
        drawable.setSize(getShapeWidth(), getShapeHeight());
        drawable.setCornerRadii(new float[]{getTopLeftRadius(), getTopLeftRadius(), getTopRightRadius(), getTopRightRadius(),
                getBottomRightRadius(), getBottomRightRadius(), getBottomLeftRadius(), getBottomLeftRadius()});
        drawable.setColor(solidColor);

        int startColor = getStartColor();
        int centerColor = getCenterColor();
        int endColor = getEndColor();
        if (startColor != 0 || centerColor != 0 || endColor != 0) {
            if (centerColor == 0) {
                drawable.setColors(new int[]{startColor, endColor});
            } else {
                drawable.setColors(new int[]{startColor, centerColor, endColor});
            }
        }

        drawable.setGradientCenter(getCenterX(), getCenterY());
        drawable.setUseLevel(isUseLevel());
        int angle = getAngle();
        angle %= 360;
        // angle 必须为 45 的整数倍
        if (angle % 45 == 0) {
            switch (angle) {
                case 0:
                    drawable.setOrientation(GradientDrawable.Orientation.LEFT_RIGHT);
                    break;
                case 45:
                    drawable.setOrientation(GradientDrawable.Orientation.BL_TR);
                    break;
                case 90:
                    drawable.setOrientation(GradientDrawable.Orientation.BOTTOM_TOP);
                    break;
                case 135:
                    drawable.setOrientation(GradientDrawable.Orientation.BR_TL);
                    break;
                case 180:
                    drawable.setOrientation(GradientDrawable.Orientation.RIGHT_LEFT);
                    break;
                case 225:
                    drawable.setOrientation(GradientDrawable.Orientation.TR_BL);
                    break;
                case 270:
                    drawable.setOrientation(GradientDrawable.Orientation.TOP_BOTTOM);
                    break;
                case 315:
                    drawable.setOrientation(GradientDrawable.Orientation.TL_BR);
                    break;
                default:
                    break;
            }
        }

        drawable.setGradientType(getGradientType());
        drawable.setGradientRadius(getGradientRadius());
        drawable.setStroke(getStrokeWidth(), strokeColor, getDashWidth(), getDashGap());
        return drawable;
    }

    void intoBackground();
}