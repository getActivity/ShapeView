package com.hjq.shape.span;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.widget.TextView;
import com.hjq.shape.config.ITextViewAttribute;
import com.hjq.shape.other.TextViewAttribute;

/**
 *    author : Android 轮子哥
 *    github : https://github.com/getActivity/ShapeView
 *    time   : 2022/05/04
 *    desc   : 支持绘制描边 Span
 */
public class StrokeFontSpan extends CommonFontSpan {

    /** 描边画笔 */
    private final Paint mStrokePaint = new Paint();

    private int mTextStrokeColor;
    private int mTextStrokeSize;

    /**
     * 构建一个文字描边的 Spannable 对象
     */
    public static SpannableStringBuilder buildStrokeFontSpannable(TextView textView, CharSequence text, int textStrokeColor, int textStrokeSize) {
        return buildStrokeFontSpannable(new TextViewAttribute(textView), text, textStrokeColor, textStrokeSize);
    }

    public static SpannableStringBuilder buildStrokeFontSpannable(ITextViewAttribute textViewAttribute, CharSequence text, int textStrokeColor, int textStrokeSize) {
        SpannableStringBuilder builder = new SpannableStringBuilder(text);
        StrokeFontSpan span = new StrokeFontSpan(textViewAttribute)
            .setTextStrokeColor(textStrokeColor)
            .setTextStrokeSize(textStrokeSize);
        builder.setSpan(span, 0, builder.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        return builder;
    }

    public StrokeFontSpan(ITextViewAttribute textViewAttribute) {
        super(textViewAttribute);
    }

    @Override
    public void onDraw(@NonNull Canvas canvas, @NonNull Paint paint, CharSequence text, float textWidth,
                                int start, int end, float x, int top, int y, int bottom) {
        mStrokePaint.set(paint);
        // 设置抗锯齿
        mStrokePaint.setAntiAlias(true);
        // 设置防抖动
        mStrokePaint.setDither(true);
        mStrokePaint.setTextSize(paint.getTextSize());
        // 描边宽度
        mStrokePaint.setStrokeWidth(mTextStrokeSize);
        mStrokePaint.setStyle(Paint.Style.STROKE);
        // 设置粗体
        //mStrokePaint.setFakeBoldText(true);
        mStrokePaint.setColor(mTextStrokeColor);
        canvas.drawText(text, start, end, x, y, mStrokePaint);
    }

    public StrokeFontSpan setTextStrokeColor(int color) {
        mTextStrokeColor = color;
        return this;
    }

    public StrokeFontSpan setTextStrokeSize(int size) {
        mTextStrokeSize = size;
        return this;
    }
}
