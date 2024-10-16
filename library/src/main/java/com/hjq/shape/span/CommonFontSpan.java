package com.hjq.shape.span;

import android.graphics.Canvas;
import android.graphics.Paint;
import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.text.style.ReplacementSpan;

/**
 *    author : Android 轮子哥
 *    github : https://github.com/getActivity/ShapeView
 *    time   : 2022/05/04
 *    desc   : 通用 Span 类
 */
public abstract class CommonFontSpan extends ReplacementSpan {

    /** 测量的文本宽度 */
    private float mMeasureTextWidth;

    @Override
    public int getSize(@NonNull Paint paint, CharSequence text, int start, int end, @Nullable Paint.FontMetricsInt fontMetricsInt) {
        mMeasureTextWidth = onMeasure(paint, fontMetricsInt, text, start, end);
        // 这段不可以去掉，字体高度没设置，会出现 draw 方法没有被调用的问题
        // 详情请见：https://stackoverflow.com/questions/20069537/replacementspans-draw-method-isnt-called
        Paint.FontMetricsInt metrics = paint.getFontMetricsInt();
        if (fontMetricsInt != null) {
            fontMetricsInt.top = metrics.top;
            fontMetricsInt.ascent = metrics.ascent;
            fontMetricsInt.descent = metrics.descent;
            fontMetricsInt.bottom = metrics.bottom;
        }
        return (int) mMeasureTextWidth;
    }

    @Override
    public void draw(@NonNull Canvas canvas, CharSequence text, int start, int end, float x, int top, int y, int bottom, @NonNull Paint paint) {
        int alpha = paint.getAlpha();
        // 判断是否给画笔设置了透明度
        if (alpha != 255) {
            // 如果是则设置不透明
            paint.setAlpha(255);
        }
        onDraw(canvas, paint, text, start, end, x, top, y, bottom);
        // 绘制完成之后将画笔的透明度还原回去
        paint.setAlpha(alpha);
    }

    public float onMeasure(@NonNull Paint paint, @Nullable Paint.FontMetricsInt fontMetricsInt, CharSequence text, @IntRange(from = 0) int start, @IntRange(from = 0) int end) {
        return paint.measureText(text, start, end);
    }

    public abstract void onDraw(@NonNull Canvas canvas, @NonNull Paint paint, CharSequence text, @IntRange(from = 0) int start, @IntRange(from = 0) int end, float x, int top, int y, int bottom);

    public float getMeasureTextWidth() {
        return mMeasureTextWidth;
    }
}
