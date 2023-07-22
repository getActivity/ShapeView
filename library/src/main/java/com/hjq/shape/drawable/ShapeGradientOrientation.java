package com.hjq.shape.drawable;

/**
 *    author : Android 轮子哥
 *    github : https://github.com/getActivity/ShapeView
 *    time   : 2021/08/15
 *    desc   : Shape 渐变方向
 */
public final class ShapeGradientOrientation {

    /** 从左到右绘制渐变（0 度） */
    public static final int LEFT_TO_RIGHT = 0;

    /** 从右到左绘制渐变（180 度） */
    public static final int RIGHT_TO_LEFT = 180;

    /** 从下到上绘制渐变（90 度） */
    public static final int BOTTOM_TO_TOP = 90;

    /** 从上到下绘制渐变（270 度） */
    public static final int TOP_TO_BOTTOM = 270;

    // ------------------------------ //

    /** 从左上角到右下角绘制渐变（315 度） */
    public static final int TOP_LEFT_TO_BOTTOM_RIGHT = 315;

    /** 从左下角到右上角绘制渐变（45 度） */
    public static final int BOTTOM_LEFT_TO_TOP_RIGHT = 45;

    /** 从右上角到左下角绘制渐变（225 度） */
    public static final int TOP_RIGHT_TO_BOTTOM_LEFT = 225;

    /** 从右下角到左上角绘制渐变（135 度） */
    public static final int BOTTOM_RIGHT_TO_TOP_LEFT = 135;
}