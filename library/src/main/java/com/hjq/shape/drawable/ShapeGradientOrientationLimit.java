package com.hjq.shape.drawable;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 *    author : Android 轮子哥
 *    github : https://github.com/getActivity/ShapeView
 *    time   : 2023/07/16
 *    desc   : Shape 渐变方向赋值限制
 */
@IntDef({ShapeGradientOrientation.LEFT_TO_RIGHT, ShapeGradientOrientation.RIGHT_TO_LEFT,
        ShapeGradientOrientation.BOTTOM_TO_TOP, ShapeGradientOrientation.TOP_TO_BOTTOM,
        ShapeGradientOrientation.TOP_LEFT_TO_BOTTOM_RIGHT, ShapeGradientOrientation.BOTTOM_LEFT_TO_TOP_RIGHT,
        ShapeGradientOrientation.TOP_RIGHT_TO_BOTTOM_LEFT, ShapeGradientOrientation.BOTTOM_RIGHT_TO_TOP_LEFT})
@Retention(RetentionPolicy.SOURCE)
public @interface ShapeGradientOrientationLimit {}