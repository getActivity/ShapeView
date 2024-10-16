package com.hjq.shape.span;

import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import androidx.annotation.NonNull;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.widget.LinearLayout;

/**
 *    author : Android 轮子哥
 *    github : https://github.com/getActivity/ShapeView
 *    time   : 2021/08/17
 *    desc   : 支持直接定义文本渐变色的 Span
 */
public class LinearGradientFontSpan extends CommonFontSpan {

    /** 水平渐变方向 */
    public static final int GRADIENT_ORIENTATION_HORIZONTAL = LinearLayout.HORIZONTAL;
    /** 垂直渐变方向 */
    public static final int GRADIENT_ORIENTATION_VERTICAL = LinearLayout.VERTICAL;

    /**
     * 构建一个文字渐变色的 Spannable 对象
     */
    public static SpannableStringBuilder buildLinearGradientSpannable(CharSequence text, int[] colors, float[] positions, int orientation) {
        SpannableStringBuilder builder = new SpannableStringBuilder(text);
        LinearGradientFontSpan span = new LinearGradientFontSpan()
                .setTextGradientColor(colors)
                .setTextGradientOrientation(orientation)
                .setTextGradientPositions(positions);
        builder.setSpan(span, 0, builder.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        return builder;
    }

    /** 文字渐变方向 */
    private int mTextGradientOrientation;
    /** 文字渐变颜色组 */
    private int[] mTextGradientColor;
    /** 文字渐变位置组 */
    private float[] mTextGradientPositions;

    @Override
    public void onDraw(@NonNull Canvas canvas, @NonNull Paint paint, CharSequence text, int start, int end, float x, int top, int y, int bottom) {
        LinearGradient linearGradient;
        if (mTextGradientOrientation == GRADIENT_ORIENTATION_VERTICAL) {
            linearGradient = new LinearGradient(0, 0, 0, paint.descent() - paint.ascent(),
                    mTextGradientColor, mTextGradientPositions, Shader.TileMode.REPEAT);
        } else {
            linearGradient = new LinearGradient(x, 0, x + getMeasureTextWidth(), 0,
                    mTextGradientColor, mTextGradientPositions, Shader.TileMode.REPEAT);
        }
        paint.setShader(linearGradient);
        canvas.drawText(text, start, end, x, y, paint);
    }

    public LinearGradientFontSpan setTextGradientOrientation(int orientation) {
        mTextGradientOrientation = orientation;
        return this;
    }

    public LinearGradientFontSpan setTextGradientColor(int[] colors) {
        mTextGradientColor = colors;
        return this;
    }

    public LinearGradientFontSpan setTextGradientPositions(float[] positions) {
        mTextGradientPositions = positions;
        return this;
    }
}