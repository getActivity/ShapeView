package com.hjq.shape.span;

import android.graphics.Canvas;
import android.graphics.Paint;
import androidx.annotation.NonNull;
import android.text.TextPaint;
import android.text.style.ReplacementSpan;

import java.util.Arrays;
import java.util.List;

public class MultiFontSpan extends ReplacementSpan {

    /** 测量的文本宽度 */
    private float mMeasureTextWidth;

    private final List<ReplacementSpan> mReplacementSpans;

    public MultiFontSpan(ReplacementSpan... replacementSpans) {
        mReplacementSpans = Arrays.asList(replacementSpans);
    }

    @Override
    public int getSize(@NonNull Paint paint, CharSequence text, int start, int end, Paint.FontMetricsInt fm) {
        for (ReplacementSpan replacementSpan : mReplacementSpans) {
            int size = replacementSpan.getSize(paint, text, start, end, fm);
            mMeasureTextWidth = Math.max(mMeasureTextWidth, size);
        }
        return (int) mMeasureTextWidth;
    }

    @Override
    public void draw(@NonNull Canvas canvas, CharSequence text, int start, int end, float x, int top, int y, int bottom, @NonNull Paint paint) {
        for (ReplacementSpan replacementSpan : mReplacementSpans) {
            replacementSpan.draw(canvas, text, start, end, x, top, y, bottom, paint);
        }
    }

    @Override
    public void updateMeasureState(TextPaint p) {
        super.updateMeasureState(p);
        for (ReplacementSpan replacementSpan : mReplacementSpans) {
            replacementSpan.updateMeasureState(p);
        }
    }

    @Override
    public void updateDrawState(TextPaint ds) {
        super.updateDrawState(ds);
        for (ReplacementSpan replacementSpan : mReplacementSpans) {
            replacementSpan.updateDrawState(ds);
        }
    }
}