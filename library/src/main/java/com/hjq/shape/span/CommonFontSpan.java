package com.hjq.shape.span;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.View;
import com.hjq.shape.config.ITextViewAttribute;

/**
 *    author : Android 轮子哥
 *    github : https://github.com/getActivity/ShapeView
 *    time   : 2022/05/04
 *    desc   : 通用 Span 类
 */
public abstract class CommonFontSpan extends AlignmentReplacementSpan {

    /** 测量的文本宽度 */
    private float mMeasureTextWidth;

    public CommonFontSpan(ITextViewAttribute textViewAttribute) {
        super(textViewAttribute);
    }

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

        // 获取文本和画布宽度
        float textWidth = paint.measureText(text, start, end);
        // 根据对齐方式调整 x 坐标
        float drawX;

        ITextViewAttribute textAttribute = getTextAttribute();
        float canvasWidth = canvas.getWidth() - textAttribute.getPaddingLeft() - textAttribute.getPaddingRight();
        // 获取 TextView 文本重心
        int gravity = textAttribute.getTextGravity();
        // 获取当前布局方向（LTR 或 RTL）
        boolean isRtl = (textAttribute.getLayoutDirection() == View.LAYOUT_DIRECTION_RTL);

        // 判断的顺序必须为：left 和 right，start 和 end，center 和 center_horizontal
        if (hasFlag(gravity, Gravity.LEFT)) {
            // 左对齐
            drawX = x;
        } else if (hasFlag(gravity, Gravity.RIGHT)) {
            // 右对齐
            drawX = canvasWidth - textWidth;
        } else if ((isRtl && hasFlag(gravity, Gravity.END)) || (!isRtl && hasFlag(gravity, Gravity.START))) {
            // 左对齐或 START 对齐（适配布局方向）
            drawX = x;
        } else if ((isRtl && hasFlag(gravity, Gravity.START)) || (!isRtl && hasFlag(gravity, Gravity.END))) {
            // 右对齐或 END 对齐（适配布局方向）
            drawX = Math.max(canvasWidth - textWidth, 0);
        } else if (hasFlag(gravity, Gravity.CENTER) || hasFlag(gravity, Gravity.CENTER_HORIZONTAL)) {
            // 居中对齐
            drawX = Math.max((canvasWidth - textWidth) / 2, 0);
        } else {
            // 默认左对齐
            drawX = x;
        }

        // 绘制文本
        onDraw(canvas, paint, text, textWidth, start, end, drawX, top, y, bottom);

        // 绘制完成之后将画笔的透明度还原回去
        paint.setAlpha(alpha);
    }

    public float onMeasure(@NonNull Paint paint, @Nullable Paint.FontMetricsInt fontMetricsInt, CharSequence text, @IntRange(from = 0) int start, @IntRange(from = 0) int end) {
        return paint.measureText(text, start, end);
    }

    public abstract void onDraw(@NonNull Canvas canvas, @NonNull Paint paint, CharSequence text, float textWidth, @IntRange(from = 0) int start, @IntRange(from = 0) int end, float x, int top, int y, int bottom);
}
