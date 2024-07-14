package com.hjq.shape.config;

/**
 *    author : Android 轮子哥
 *    github : https://github.com/getActivity/ShapeView
 *    time   : 2021/08/28
 *    desc   : ShapeDrawable View 属性收集接口
 */
public interface IShapeDrawableStyleable {

    int getShapeTypeStyleable();

    int getShapeWidthStyleable();

    int getShapeHeightStyleable();

    int getRadiusStyleable();

    int getRadiusInTopLeftStyleable();

    int getRadiusInTopStartStyleable();

    int getRadiusInTopRightStyleable();

    int getRadiusInTopEndStyleable();

    int getRadiusInBottomLeftStyleable();

    int getRadiusInBottomStartStyleable();

    int getRadiusInBottomRightStyleable();

    int getRadiusInBottomEndStyleable();

    int getSolidColorStyleable();

    int getSolidPressedColorStyleable();

    default int getSolidCheckedColorStyleable() {
        return 0;
    }

    int getSolidDisabledColorStyleable();

    int getSolidFocusedColorStyleable();

    int getSolidSelectedColorStyleable();

    int getSolidGradientStartColorStyleable();

    int getSolidGradientCenterColorStyleable();

    int getSolidGradientEndColorStyleable();

    int getSolidGradientOrientationStyleable();

    int getSolidGradientTypeStyleable();

    int getSolidGradientCenterXStyleable();

    int getSolidGradientCenterYStyleable();

    int getSolidGradientRadiusStyleable();

    int getStrokeColorStyleable();

    int getStrokePressedColorStyleable();

    default int getStrokeCheckedColorStyleable() {
        return 0;
    }

    int getStrokeDisabledColorStyleable();

    int getStrokeFocusedColorStyleable();

    int getStrokeSelectedColorStyleable();

    int getStrokeGradientStartColorStyleable();

    int getStrokeGradientCenterColorStyleable();

    int getStrokeGradientEndColorStyleable();

    int getStrokeGradientOrientationStyleable();

    int getStrokeSizeStyleable();

    int getStrokeDashSizeStyleable();

    int getStrokeDashGapStyleable();

    int getShadowSizeStyleable();

    int getShadowColorStyleable();

    int getShadowOffsetXStyleable();

    int getShadowOffsetYStyleable();

    int getRingInnerRadiusSizeStyleable();

    int getRingInnerRadiusRatioStyleable();

    int getRingThicknessSizeStyleable();

    int getRingThicknessRatioStyleable();

    int getLineGravityStyleable();
}