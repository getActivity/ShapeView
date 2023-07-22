package com.hjq.shape.drawable;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 *    author : Android 轮子哥
 *    github : https://github.com/getActivity/ShapeView
 *    time   : 2023/07/16
 *    desc   : Shape 形状类型赋值限制
 */
@IntDef({ShapeType.RECTANGLE, ShapeType.OVAL,
        ShapeType.LINE, ShapeType.RING})
@Retention(RetentionPolicy.SOURCE)
public @interface ShapeTypeLimit {}