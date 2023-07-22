package com.hjq.shape.drawable;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 *    author : Android 轮子哥
 *    github : https://github.com/getActivity/ShapeView
 *    time   : 2023/07/16
 *    desc   : Shape 渐变类型赋值限制
 */
@IntDef({ShapeGradientType.LINEAR_GRADIENT, ShapeGradientType.RADIAL_GRADIENT, ShapeGradientType.SWEEP_GRADIENT})
@Retention(RetentionPolicy.SOURCE)
public @interface ShapeGradientTypeLimit {}