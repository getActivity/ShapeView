package com.hjq.shape.styleable;

import com.hjq.shape.R;
import com.hjq.shape.config.IShapeDrawableStyleable;
import com.hjq.shape.config.ITextColorStyleable;

/**
 *    author : Android 轮子哥
 *    github : https://github.com/getActivity/ShapeView
 *    time   : 2021/08/28
 *    desc   : TextView 的 Shape 属性值
 */
public final class ShapeTextViewStyleable implements IShapeDrawableStyleable, ITextColorStyleable {

    /**
     * {@link IShapeDrawableStyleable}
     */

    @Override
    public int getShapeTypeStyleable() {
        return R.styleable.ShapeTextView_shape_type;
    }

    @Override
    public int getShapeWidthStyleable() {
        return R.styleable.ShapeTextView_shape_width;
    }

    @Override
    public int getShapeHeightStyleable() {
        return R.styleable.ShapeTextView_shape_height;
    }

    @Override
    public int getRadiusStyleable() {
        return R.styleable.ShapeTextView_shape_radius;
    }

    @Override
    public int getRadiusInTopLeftStyleable() {
        return R.styleable.ShapeTextView_shape_radiusInTopLeft;
    }

    @Override
    public int getRadiusInTopRightStyleable() {
        return R.styleable.ShapeTextView_shape_radiusInTopRight;
    }

    @Override
    public int getRadiusInBottomLeftStyleable() {
        return R.styleable.ShapeTextView_shape_radiusInBottomLeft;
    }

    @Override
    public int getRadiusInBottomRightStyleable() {
        return R.styleable.ShapeTextView_shape_radiusInBottomRight;
    }

    @Override
    public int getSolidColorStyleable() {
        return R.styleable.ShapeTextView_shape_solidColor;
    }

    @Override
    public int getSolidPressedColorStyleable() {
        return R.styleable.ShapeTextView_shape_solidPressedColor;
    }

    @Override
    public int getSolidDisabledColorStyleable() {
        return R.styleable.ShapeTextView_shape_solidDisabledColor;
    }

    @Override
    public int getSolidFocusedColorStyleable() {
        return R.styleable.ShapeTextView_shape_solidFocusedColor;
    }

    @Override
    public int getSolidSelectedColorStyleable() {
        return R.styleable.ShapeTextView_shape_solidSelectedColor;
    }

    @Override
    public int getSolidStartColorStyleable() {
        return R.styleable.ShapeTextView_shape_solidStartColor;
    }

    @Override
    public int getSolidCenterColorStyleable() {
        return R.styleable.ShapeTextView_shape_solidCenterColor;
    }

    @Override
    public int getSolidEndColorStyleable() {
        return R.styleable.ShapeTextView_shape_solidEndColor;
    }

    @Override
    public int getSolidGradientOrientationStyleable() {
        return R.styleable.ShapeTextView_shape_solidGradientOrientation;
    }

    @Override
    public int getSolidGradientTypeStyleable() {
        return R.styleable.ShapeTextView_shape_solidGradientType;
    }

    @Override
    public int getSolidCenterXStyleable() {
        return R.styleable.ShapeTextView_shape_solidCenterX;
    }

    @Override
    public int getSolidCenterYStyleable() {
        return R.styleable.ShapeTextView_shape_solidCenterY;
    }

    @Override
    public int getSolidGradientRadiusStyleable() {
        return R.styleable.ShapeTextView_shape_solidGradientRadius;
    }

    @Override
    public int getStrokeColorStyleable() {
        return R.styleable.ShapeTextView_shape_strokeColor;
    }

    @Override
    public int getStrokePressedColorStyleable() {
        return R.styleable.ShapeTextView_shape_strokePressedColor;
    }

    @Override
    public int getStrokeDisabledColorStyleable() {
        return R.styleable.ShapeTextView_shape_strokeDisabledColor;
    }

    @Override
    public int getStrokeFocusedColorStyleable() {
        return R.styleable.ShapeTextView_shape_strokeFocusedColor;
    }

    @Override
    public int getStrokeSelectedColorStyleable() {
        return R.styleable.ShapeTextView_shape_strokeSelectedColor;
    }

    @Override
    public int getStrokeStartColorStyleable() {
        return R.styleable.ShapeTextView_shape_strokeStartColor;
    }

    @Override
    public int getStrokeCenterColorStyleable() {
        return R.styleable.ShapeTextView_shape_strokeCenterColor;
    }

    @Override
    public int getStrokeEndColorStyleable() {
        return R.styleable.ShapeTextView_shape_strokeEndColor;
    }

    @Override
    public int getStrokeGradientOrientationStyleable() {
        return R.styleable.ShapeTextView_shape_strokeGradientOrientation;
    }

    @Override
    public int getStrokeSizeStyleable() {
        return R.styleable.ShapeTextView_shape_strokeSize;
    }

    @Override
    public int getStrokeDashSizeStyleable() {
        return R.styleable.ShapeTextView_shape_strokeDashSize;
    }

    @Override
    public int getStrokeDashGapStyleable() {
        return R.styleable.ShapeTextView_shape_strokeDashGap;
    }

    @Override
    public int getShadowSizeStyleable() {
        return R.styleable.ShapeTextView_shape_shadowSize;
    }

    @Override
    public int getShadowColorStyleable() {
        return R.styleable.ShapeTextView_shape_shadowColor;
    }

    @Override
    public int getShadowOffsetXStyleable() {
        return R.styleable.ShapeTextView_shape_shadowOffsetX;
    }

    @Override
    public int getShadowOffsetYStyleable() {
        return R.styleable.ShapeTextView_shape_shadowOffsetY;
    }

    @Override
    public int getRingInnerRadiusSizeStyleable() {
        return R.styleable.ShapeTextView_shape_ringInnerRadiusSize;
    }

    @Override
    public int getRingInnerRadiusRatioStyleable() {
        return R.styleable.ShapeTextView_shape_ringInnerRadiusRatio;
    }

    @Override
    public int getRingThicknessSizeStyleable() {
        return R.styleable.ShapeTextView_shape_ringThicknessSize;
    }

    @Override
    public int getRingThicknessRatioStyleable() {
        return R.styleable.ShapeTextView_shape_ringThicknessRatio;
    }

    @Override
    public int getLineGravityStyleable() {
        return R.styleable.ShapeTextView_shape_lineGravity;
    }

    /**
     * {@link ITextColorStyleable}
     */

    @Override
    public int getTextColorStyleable() {
        return R.styleable.ShapeTextView_shape_textColor;
    }

    @Override
    public int getTextPressedColorStyleable() {
        return R.styleable.ShapeTextView_shape_textPressedColor;
    }

    @Override
    public int getTextDisabledColorStyleable() {
        return R.styleable.ShapeTextView_shape_textDisabledColor;
    }

    @Override
    public int getTextFocusedColorStyleable() {
        return R.styleable.ShapeTextView_shape_textFocusedColor;
    }

    @Override
    public int getTextSelectedColorStyleable() {
        return R.styleable.ShapeTextView_shape_textSelectedColor;
    }

    @Override
    public int getTextStartColorStyleable() {
        return R.styleable.ShapeTextView_shape_textStartColor;
    }

    @Override
    public int getTextCenterColorStyleable() {
        return R.styleable.ShapeTextView_shape_textCenterColor;
    }

    @Override
    public int getTextEndColorStyleable() {
        return R.styleable.ShapeTextView_shape_textEndColor;
    }

    @Override
    public int getTextGradientOrientationStyleable() {
        return R.styleable.ShapeTextView_shape_textGradientOrientation;
    }

    @Override
    public int getTextStrokeColorStyleable() {
        return R.styleable.ShapeTextView_shape_textStrokeColor;
    }

    @Override
    public int getTextStrokeSizeStyleable() {
        return R.styleable.ShapeTextView_shape_textStrokeSize;
    }
}