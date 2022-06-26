package com.hjq.shape.styleable;

import com.hjq.shape.R;

/**
 *    author : Android 轮子哥
 *    github : https://github.com/getActivity/ShapeView
 *    time   : 2021/08/28
 *    desc   : EditText 的 Shape 属性值
 */
public final class ShapeEditTextStyleable implements IShapeDrawableStyleable, ITextColorStyleable {

    /**
     * {@link IShapeDrawableStyleable}
     */

    @Override
    public int getShapeTypeStyleable() {
        return R.styleable.ShapeEditText_shape;
    }

    @Override
    public int getShapeWidthStyleable() {
        return R.styleable.ShapeEditText_shape_width;
    }

    @Override
    public int getShapeHeightStyleable() {
        return R.styleable.ShapeEditText_shape_height;
    }

    @Override
    public int getSolidColorStyleable() {
        return R.styleable.ShapeEditText_shape_solidColor;
    }

    @Override
    public int getSolidPressedColorStyleable() {
        return R.styleable.ShapeEditText_shape_solidPressedColor;
    }

    @Override
    public int getSolidDisabledColorStyleable() {
        return R.styleable.ShapeEditText_shape_solidDisabledColor;
    }

    @Override
    public int getSolidFocusedColorStyleable() {
        return R.styleable.ShapeEditText_shape_solidFocusedColor;
    }

    @Override
    public int getSolidSelectedColorStyleable() {
        return R.styleable.ShapeEditText_shape_solidSelectedColor;
    }

    @Override
    public int getRadiusStyleable() {
        return R.styleable.ShapeEditText_shape_radius;
    }

    @Override
    public int getTopLeftRadiusStyleable() {
        return R.styleable.ShapeEditText_shape_topLeftRadius;
    }

    @Override
    public int getTopRightRadiusStyleable() {
        return R.styleable.ShapeEditText_shape_topRightRadius;
    }

    @Override
    public int getBottomLeftRadiusStyleable() {
        return R.styleable.ShapeEditText_shape_bottomLeftRadius;
    }

    @Override
    public int getBottomRightRadiusStyleable() {
        return R.styleable.ShapeEditText_shape_bottomRightRadius;
    }

    @Override
    public int getSolidStartColorStyleable() {
        return R.styleable.ShapeEditText_shape_startColor;
    }

    @Override
    public int getSolidCenterColorStyleable() {
        return R.styleable.ShapeEditText_shape_centerColor;
    }

    @Override
    public int getSolidEndColorStyleable() {
        return R.styleable.ShapeEditText_shape_endColor;
    }

    @Override
    public int getStrokeStartColorStyleable() {
        return R.styleable.ShapeEditText_shape_strokeStartColor;
    }

    @Override
    public int getStrokeCenterColorStyleable() {
        return R.styleable.ShapeEditText_shape_strokeCenterColor;
    }

    @Override
    public int getStrokeEndColorStyleable() {
        return R.styleable.ShapeEditText_shape_strokeEndColor;
    }

    @Override
    public int getUseLevelStyleable() {
        return R.styleable.ShapeEditText_shape_useLevel;
    }

    @Override
    public int getAngleStyleable() {
        return R.styleable.ShapeEditText_shape_angle;
    }

    @Override
    public int getGradientTypeStyleable() {
        return R.styleable.ShapeEditText_shape_gradientType;
    }

    @Override
    public int getCenterXStyleable() {
        return R.styleable.ShapeEditText_shape_centerX;
    }

    @Override
    public int getCenterYStyleable() {
        return R.styleable.ShapeEditText_shape_centerY;
    }

    @Override
    public int getGradientRadiusStyleable() {
        return R.styleable.ShapeEditText_shape_gradientRadius;
    }

    @Override
    public int getStrokeColorStyleable() {
        return R.styleable.ShapeEditText_shape_strokeColor;
    }

    @Override
    public int getStrokePressedColorStyleable() {
        return R.styleable.ShapeEditText_shape_strokePressedColor;
    }

    @Override
    public int getStrokeDisabledColorStyleable() {
        return R.styleable.ShapeEditText_shape_strokeDisabledColor;
    }

    @Override
    public int getStrokeFocusedColorStyleable() {
        return R.styleable.ShapeEditText_shape_strokeFocusedColor;
    }

    @Override
    public int getStrokeSelectedColorStyleable() {
        return R.styleable.ShapeEditText_shape_strokeSelectedColor;
    }

    @Override
    public int getStrokeWidthStyleable() {
        return R.styleable.ShapeEditText_shape_strokeWidth;
    }

    @Override
    public int getDashWidthStyleable() {
        return R.styleable.ShapeEditText_shape_dashWidth;
    }

    @Override
    public int getDashGapStyleable() {
        return R.styleable.ShapeEditText_shape_dashGap;
    }

    @Override
    public int getShadowSizeStyleable() {
        return R.styleable.ShapeEditText_shape_shadowSize;
    }

    @Override
    public int getShadowColorStyleable() {
        return R.styleable.ShapeEditText_shape_shadowColor;
    }

    @Override
    public int getShadowOffsetXStyleable() {
        return R.styleable.ShapeEditText_shape_shadowOffsetX;
    }

    @Override
    public int getShadowOffsetYStyleable() {
        return R.styleable.ShapeEditText_shape_shadowOffsetY;
    }

    @Override
    public int getInnerRadiusStyleable() {
        return R.styleable.ShapeEditText_shape_innerRadius;
    }

    @Override
    public int getInnerRadiusRatioStyleable() {
        return R.styleable.ShapeEditText_shape_innerRadiusRatio;
    }

    @Override
    public int getThicknessStyleable() {
        return R.styleable.ShapeEditText_shape_thickness;
    }

    @Override
    public int getThicknessRatioStyleable() {
        return R.styleable.ShapeEditText_shape_thicknessRatio;
    }

    @Override
    public int getLineGravityStyleable() {
        return R.styleable.ShapeEditText_shape_lineGravity;
    }

    /**
     * {@link ITextColorStyleable}
     */

    @Override
    public int getTextColorStyleable() {
        return R.styleable.ShapeEditText_shape_textColor;
    }

    @Override
    public int getTextPressedColorStyleable() {
        return R.styleable.ShapeEditText_shape_textPressedColor;
    }

    @Override
    public int getTextDisabledColorStyleable() {
        return R.styleable.ShapeEditText_shape_textDisabledColor;
    }

    @Override
    public int getTextFocusedColorStyleable() {
        return R.styleable.ShapeEditText_shape_textFocusedColor;
    }

    @Override
    public int getTextSelectedColorStyleable() {
        return R.styleable.ShapeEditText_shape_textSelectedColor;
    }

    @Override
    public int getTextStartColorStyleable() {
        return R.styleable.ShapeEditText_shape_textStartColor;
    }

    @Override
    public int getTextCenterColorStyleable() {
        return R.styleable.ShapeEditText_shape_textCenterColor;
    }

    @Override
    public int getTextEndColorStyleable() {
        return R.styleable.ShapeEditText_shape_textEndColor;
    }

    @Override
    public int getTextGradientOrientationStyleable() {
        return R.styleable.ShapeEditText_shape_textGradientOrientation;
    }

    @Override
    public int getTextStrokeColorStyleable() {
        return R.styleable.ShapeEditText_shape_textStrokeColor;
    }

    @Override
    public int getTextStrokeSizeStyleable() {
        return R.styleable.ShapeEditText_shape_textStrokeSize;
    }
}