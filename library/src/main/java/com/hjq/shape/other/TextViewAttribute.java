package com.hjq.shape.other;

import android.widget.TextView;
import com.hjq.shape.config.ITextViewAttribute;

/**
 *    author : Android 轮子哥
 *    github : https://github.com/getActivity/ShapeView
 *    time   : 2024/09/15
 *    desc   : 获取文本控件的属性
 */
public class TextViewAttribute implements ITextViewAttribute {

    private final TextView mTextView;

    public TextViewAttribute(TextView textView) {
        mTextView = textView;
    }

    @Override
    public int getLayoutDirection() {
        return mTextView.getLayoutDirection();
    }

    @Override
    public int getTextGravity() {
        return mTextView.getGravity();
    }

    @Override
    public int getPaddingLeft() {
        return mTextView.getPaddingLeft();
    }

    @Override
    public int getPaddingRight() {
        return mTextView.getPaddingRight();
    }
}
