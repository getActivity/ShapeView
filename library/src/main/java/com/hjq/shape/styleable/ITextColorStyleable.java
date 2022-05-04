package com.hjq.shape.styleable;

/**
 *    author : Android 轮子哥
 *    github : https://github.com/getActivity/ShapeView
 *    time   : 2021/08/28
 *    desc   : 文本颜色 View 属性收集接口
 */
public interface ITextColorStyleable {

    int getTextColorStyleable();

    int getTextPressedColorStyleable();

    default int getTextCheckedColorStyleable() {
        return 0;
    }

    int getTextDisabledColorStyleable();

    int getTextFocusedColorStyleable();

    int getTextSelectedColorStyleable();

    int getTextStartColorStyleable();

    int getTextCenterColorStyleable();

    int getTextEndColorStyleable();

    int getTextGradientOrientationStyleable();

    int getTextStrokeColorStyleable();

    int getTextStrokeSizeStyleable();
}