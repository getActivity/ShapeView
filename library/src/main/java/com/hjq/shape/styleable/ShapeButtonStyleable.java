package com.hjq.shape.styleable;

import com.hjq.shape.R;
import com.hjq.shape.config.IShapeDrawableStyleable;
import com.hjq.shape.config.ITextColorStyleable;

/**
 *    author : Android 轮子哥
 *    github : https://github.com/getActivity/ShapeView
 *    time   : 2021/08/28
 *    desc   : Button 的 Shape 属性值
 */
public final class ShapeButtonStyleable implements IShapeDrawableStyleable, ITextColorStyleable {

    /**
     * {@link IShapeDrawableStyleable}
     */

    @Override
    public int getShapeTypeStyleable() {
        return R.styleable.ShapeButton_shape_type;
    }

    @Override
    public int getShapeWidthStyleable() {
        return R.styleable.ShapeButton_shape_width;
    }

    @Override
    public int getShapeHeightStyleable() {
        return R.styleable.ShapeButton_shape_height;
    }

    @Override
    public int getRadiusStyleable() {
        return R.styleable.ShapeButton_shape_radius;
    }

    @Override
    public int getRadiusInTopLeftStyleable() {
        return R.styleable.ShapeButton_shape_radiusInTopLeft;
    }

    @Override
    public int getRadiusInTopStartStyleable() {
        return R.styleable.ShapeButton_shape_radiusInTopStart;
    }

    @Override
    public int getRadiusInTopRightStyleable() {
        return R.styleable.ShapeButton_shape_radiusInTopRight;
    }

    @Override
    public int getRadiusInTopEndStyleable() {
        return R.styleable.ShapeButton_shape_radiusInTopEnd;
    }

    @Override
    public int getRadiusInBottomLeftStyleable() {
        return R.styleable.ShapeButton_shape_radiusInBottomLeft;
    }

    @Override
    public int getRadiusInBottomStartStyleable() {
        return R.styleable.ShapeButton_shape_radiusInBottomStart;
    }

    @Override
    public int getRadiusInBottomRightStyleable() {
        return R.styleable.ShapeButton_shape_radiusInBottomRight;
    }

    @Override
    public int getRadiusInBottomEndStyleable() {
        return R.styleable.ShapeButton_shape_radiusInBottomEnd;
    }

    @Override
    public int getSolidColorStyleable() {
        return R.styleable.ShapeButton_shape_solidColor;
    }

    @Override
    public int getSolidPressedColorStyleable() {
        return R.styleable.ShapeButton_shape_solidPressedColor;
    }

    @Override
    public int getSolidDisabledColorStyleable() {
        return R.styleable.ShapeButton_shape_solidDisabledColor;
    }

    @Override
    public int getSolidFocusedColorStyleable() {
        return R.styleable.ShapeButton_shape_solidFocusedColor;
    }

    @Override
    public int getSolidSelectedColorStyleable() {
        return R.styleable.ShapeButton_shape_solidSelectedColor;
    }

    @Override
    public int getSolidGradientStartColorStyleable() {
        return R.styleable.ShapeButton_shape_solidGradientStartColor;
    }

    @Override
    public int getSolidGradientCenterColorStyleable() {
        return R.styleable.ShapeButton_shape_solidGradientCenterColor;
    }

    @Override
    public int getSolidGradientEndColorStyleable() {
        return R.styleable.ShapeButton_shape_solidGradientEndColor;
    }

    @Override
    public int getSolidGradientOrientationStyleable() {
        return R.styleable.ShapeButton_shape_solidGradientOrientation;
    }

    @Override
    public int getSolidGradientTypeStyleable() {
        return R.styleable.ShapeButton_shape_solidGradientType;
    }

    @Override
    public int getSolidGradientCenterXStyleable() {
        return R.styleable.ShapeButton_shape_solidGradientCenterX;
    }

    @Override
    public int getSolidGradientCenterYStyleable() {
        return R.styleable.ShapeButton_shape_solidGradientCenterY;
    }

    @Override
    public int getSolidGradientRadiusStyleable() {
        return R.styleable.ShapeButton_shape_solidGradientRadius;
    }

    @Override
    public int getStrokeColorStyleable() {
        return R.styleable.ShapeButton_shape_strokeColor;
    }

    @Override
    public int getStrokePressedColorStyleable() {
        return R.styleable.ShapeButton_shape_strokePressedColor;
    }

    @Override
    public int getStrokeDisabledColorStyleable() {
        return R.styleable.ShapeButton_shape_strokeDisabledColor;
    }

    @Override
    public int getStrokeFocusedColorStyleable() {
        return R.styleable.ShapeButton_shape_strokeFocusedColor;
    }

    @Override
    public int getStrokeSelectedColorStyleable() {
        return R.styleable.ShapeButton_shape_strokeSelectedColor;
    }

    @Override
    public int getStrokeGradientStartColorStyleable() {
        return R.styleable.ShapeButton_shape_strokeGradientStartColor;
    }

    @Override
    public int getStrokeGradientCenterColorStyleable() {
        return R.styleable.ShapeButton_shape_strokeGradientCenterColor;
    }

    @Override
    public int getStrokeGradientEndColorStyleable() {
        return R.styleable.ShapeButton_shape_strokeGradientColor;
    }

    @Override
    public int getStrokeGradientOrientationStyleable() {
        return R.styleable.ShapeButton_shape_strokeGradientOrientation;
    }

    @Override
    public int getStrokeSizeStyleable() {
        return R.styleable.ShapeButton_shape_strokeSize;
    }

    @Override
    public int getStrokeDashSizeStyleable() {
        return R.styleable.ShapeButton_shape_strokeDashSize;
    }

    @Override
    public int getStrokeDashGapStyleable() {
        return R.styleable.ShapeButton_shape_strokeDashGap;
    }

    @Override
    public int getShadowSizeStyleable() {
        return R.styleable.ShapeButton_shape_shadowSize;
    }

    @Override
    public int getShadowColorStyleable() {
        return R.styleable.ShapeButton_shape_shadowColor;
    }

    @Override
    public int getShadowOffsetXStyleable() {
        return R.styleable.ShapeButton_shape_shadowOffsetX;
    }

    @Override
    public int getShadowOffsetYStyleable() {
        return R.styleable.ShapeButton_shape_shadowOffsetY;
    }

    @Override
    public int getRingInnerRadiusSizeStyleable() {
        return R.styleable.ShapeButton_shape_ringInnerRadiusSize;
    }

    @Override
    public int getRingInnerRadiusRatioStyleable() {
        return R.styleable.ShapeButton_shape_ringInnerRadiusRatio;
    }

    @Override
    public int getRingThicknessSizeStyleable() {
        return R.styleable.ShapeButton_shape_ringThicknessSize;
    }

    @Override
    public int getRingThicknessRatioStyleable() {
        return R.styleable.ShapeButton_shape_ringThicknessRatio;
    }

    @Override
    public int getLineGravityStyleable() {
        return R.styleable.ShapeButton_shape_lineGravity;
    }

    /**
     * {@link ITextColorStyleable}
     */

    @Override
    public int getTextColorStyleable() {
        return R.styleable.ShapeButton_shape_textColor;
    }

    @Override
    public int getTextPressedColorStyleable() {
        return R.styleable.ShapeButton_shape_textPressedColor;
    }

    @Override
    public int getTextDisabledColorStyleable() {
        return R.styleable.ShapeButton_shape_textDisabledColor;
    }

    @Override
    public int getTextFocusedColorStyleable() {
        return R.styleable.ShapeButton_shape_textFocusedColor;
    }

    @Override
    public int getTextSelectedColorStyleable() {
        return R.styleable.ShapeButton_shape_textSelectedColor;
    }

    @Override
    public int getTextStartColorStyleable() {
        return R.styleable.ShapeButton_shape_textStartColor;
    }

    @Override
    public int getTextCenterColorStyleable() {
        return R.styleable.ShapeButton_shape_textCenterColor;
    }

    @Override
    public int getTextEndColorStyleable() {
        return R.styleable.ShapeButton_shape_textEndColor;
    }

    @Override
    public int getTextGradientOrientationStyleable() {
        return R.styleable.ShapeButton_shape_textGradientOrientation;
    }

    @Override
    public int getTextStrokeColorStyleable() {
        return R.styleable.ShapeButton_shape_textStrokeColor;
    }

    @Override
    public int getTextStrokeSizeStyleable() {
        return R.styleable.ShapeButton_shape_textStrokeSize;
    }
}