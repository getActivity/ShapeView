package com.hjq.shape.config;

/**
 *    author : Android 轮子哥
 *    github : https://github.com/getActivity/ShapeView
 *    time   : 2024/09/15
 *    desc   : TextView 属性接口类
 */
public interface ITextViewAttribute {

    /**
     * 获取当前布局方向
     */
    int getLayoutDirection();

    /**
     * 获取当前文本重心
     */
    int getTextGravity();

    /**
     * 获取 TextView 左内间距
     */
    int getPaddingLeft();

    /**
     * 获取 TextView 右内间距
     */
    int getPaddingRight();
}