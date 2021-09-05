package com.hjq.shape.other;

import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ReplacementSpan;
import android.widget.LinearLayout;

/**
 *    author : Android 轮子哥
 *    github : https://github.com/getActivity/ShapeView
 *    time   : 2021/08/17
 *    desc   : 支持直接定义文本渐变色的 Span
 */
public class LinearGradientFontSpan extends ReplacementSpan {

    /** 水平渐变方向 */
    public static final int GRADIENT_ORIENTATION_HORIZONTAL = LinearLayout.HORIZONTAL;
    /** 垂直渐变方向 */
    public static final int GRADIENT_ORIENTATION_VERTICAL = LinearLayout.VERTICAL;

    /**
     * 构建一个文字渐变色的 Spannable 对象
     */
    public static SpannableStringBuilder buildLinearGradientSpannable(CharSequence text, int[] colors, float[] positions, int orientation) {
        SpannableStringBuilder builder = new SpannableStringBuilder(text);
        LinearGradientFontSpan span = new LinearGradientFontSpan();
        span.setTextGradientColor(colors);
        span.setTextGradientOrientation(orientation);
        span.setTextGradientPositions(positions);
        builder.setSpan(span, 0, builder.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        return builder;
    }

    /** 测量的文本宽度 */
    private float mMeasureTextWidth;

    /** 文字渐变方向 */
    private int mTextGradientOrientation;
    /** 文字渐变颜色组 */
    private int[] mTextGradientColors;
    /** 文字渐变位置组 */
    private float[] mTextGradientPositions;

    @Override
    public int getSize(Paint paint, CharSequence text, int start, int end, Paint.FontMetricsInt fontMetricsInt) {
        mMeasureTextWidth = paint.measureText(text, start, end);

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
    public void draw(Canvas canvas, CharSequence text, int start, int end, float x, int top, int y, int bottom, Paint paint) {
        LinearGradient linearGradient;
        if (mTextGradientOrientation == GRADIENT_ORIENTATION_VERTICAL) {
            linearGradient = new LinearGradient(0, 0, 0, paint.descent() - paint.ascent(),
                    mTextGradientColors, mTextGradientPositions, Shader.TileMode.REPEAT);
        } else {
            linearGradient = new LinearGradient(x, 0, x + mMeasureTextWidth, 0,
                    mTextGradientColors, mTextGradientPositions, Shader.TileMode.REPEAT);
        }
        paint.setShader(linearGradient);

        int alpha = paint.getAlpha();
        // 判断是否给画笔设置了透明度
        if (alpha != 255) {
            // 如果是则设置不透明
            paint.setAlpha(255);
        }
        canvas.drawText(text, start, end, x, y, paint);
        // 绘制完成之后将画笔的透明度还原回去
        paint.setAlpha(alpha);
    }

    public LinearGradientFontSpan setTextGradientOrientation(int orientation) {
        mTextGradientOrientation = orientation;
        return this;
    }

    public LinearGradientFontSpan setTextGradientColor(int[] colors) {
        mTextGradientColors = colors;
        return this;
    }

    public LinearGradientFontSpan setTextGradientPositions(float[] positions) {
        mTextGradientPositions = positions;
        return this;
    }
}