package com.hjq.shape.drawable;

/**
 *    author : Android 轮子哥
 *    github : https://github.com/getActivity/ShapeView
 *    time   : 2021/08/15
 *    desc   : Shape 渐变方向
 */
public enum ShapeGradientOrientation {

    /** 从右上角到左下角绘制渐变 */
    TR_BL,

    /** 从右到左绘制渐变 */
    RIGHT_LEFT,

    /** 从右下角到左上角绘制渐变 */
    BR_TL,

    /** 从下到上绘制渐变 */
    BOTTOM_TOP,

    /** 从左下角到右上角绘制渐变 */
    BL_TR,

    /** 从左到右绘制渐变 */
    LEFT_RIGHT,

    /** 从左上角到右下角绘制渐变 */
    TL_BR,

    /** 从上到下绘制渐变 */
    TOP_BOTTOM
}