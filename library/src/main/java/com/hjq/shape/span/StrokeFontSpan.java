package com.hjq.shape.span;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.NonNull;

public class StrokeFontSpan extends CommonFontSpan {

    /** 描边画笔 */
    private final Paint mStrokePaint = new Paint();

    private int mTextStrokeColor;
    private int mTextStrokeSize;

    @Override
    public void onDraw(@NonNull Canvas canvas, @NonNull Paint paint, CharSequence text, int start, int end, float x, int top, int y, int bottom) {
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
