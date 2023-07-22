package com.hjq.shape.drawable;

import android.annotation.SuppressLint;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.DashPathEffect;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PixelFormat;
import android.graphics.RadialGradient;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.SweepGradient;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.support.v4.graphics.ColorUtils;
import android.view.Gravity;
import android.view.View;

/**
 *    author : Android 轮子哥
 *    github : https://github.com/getActivity/ShapeView
 *    time   : 2021/08/14
 *    desc   : 在 {@link android.graphics.drawable.GradientDrawable} 的基础上改造
 */
public class ShapeDrawable extends Drawable {

    private ShapeState mShapeState;
    
    private final Paint mSolidPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Rect mPadding;
    private final Paint mStrokePaint = new Paint(Paint.ANTI_ALIAS_FLAG);   // optional, set by the caller
    private Paint mShadowPaint;
    private ColorFilter mColorFilter;   // optional, set by the caller
    private int mAlpha = 0xFF;  // modified by the caller
    private boolean mDither;

    private final Path mPath = new Path();
    private final RectF mRect = new RectF();

    private final RectF mShadowRect = new RectF();
    private final Path mShadowPath = new Path();

    private Paint mLayerPaint;    // internal, used if we use saveLayer()
    private boolean mRectDirty;   // internal state
    private boolean mMutated;
    private Path mRingPath;
    private boolean mPathDirty = true;

    public ShapeDrawable() {
        this(new ShapeState());
    }
    
    public ShapeDrawable(ShapeState state) {
        mShapeState = state;
        initializeWithState(state);
        mRectDirty = true;
        mMutated = false;

        mStrokePaint.setStyle(Paint.Style.STROKE);
    }

    /**
     * 获取 Shape 状态对象
     */
    public ShapeState getShapeState() {
        return mShapeState;
    }

    @Override
    public boolean getPadding(@NonNull Rect padding) {
        if (mPadding != null) {
            padding.set(mPadding);
            return true;
        }
        return super.getPadding(padding);
    }

    public ShapeDrawable setPadding(int paddingLeft, int paddingTop, int paddingRight, int paddingBottom) {
        return setPadding(new Rect(paddingLeft, paddingTop, paddingRight, paddingBottom));
    }

    public ShapeDrawable setPadding(Rect padding) {
        mPadding = padding;
        mPathDirty = true;
        invalidateSelf();
        return this;
    }

    /**
     * 设置 Shape 形状
     *
     * @param shape         Shape 形状类型
     */
    public ShapeDrawable setType(@ShapeTypeLimit int shape) {
        mRingPath = null;
        mShapeState.setType(shape);
        mPathDirty = true;
        invalidateSelf();
        return this;
    }

    /**
     * 设置 Shape 宽度
     */
    public ShapeDrawable setWidth(int width) {
        mShapeState.width = width;
        mPathDirty = true;
        invalidateSelf();
        return this;
    }

    /**
     * 设置 Shape 高度
     */
    public ShapeDrawable setHeight(int height) {
        mShapeState.height = height;
        mPathDirty = true;
        invalidateSelf();
        return this;
    }

    /**
     * 设置矩形的圆角大小
     */
    public ShapeDrawable setRadius(float radius) {
        mShapeState.setCornerRadius(radius);
        mPathDirty = true;
        invalidateSelf();
        return this;
    }

    /**
     * 设置矩形的圆角大小
     *
     * @param topLeftRadius         左上圆角大小
     * @param topRightRadius        右上圆角大小
     * @param bottomLeftRadius      左下圆角大小
     * @param bottomRightRadius     右下圆角大小
     */
    public ShapeDrawable setRadius(float topLeftRadius, float topRightRadius, float bottomLeftRadius, float bottomRightRadius) {
        if (topLeftRadius == topRightRadius && topLeftRadius == bottomLeftRadius && topLeftRadius == bottomRightRadius) {
            return setRadius(topLeftRadius);
        }
        // 为 4 个角中的每一个指定半径，对于每个角，该数组包含 2 个值 [X_radius, Y_radius]，角的顺序是左上角、右上角、右下角、左下角
        mShapeState.setCornerRadii(new float[] {
                topLeftRadius, topLeftRadius, topRightRadius, topRightRadius,
                bottomRightRadius, bottomRightRadius, bottomLeftRadius, bottomLeftRadius});
        mPathDirty = true;
        invalidateSelf();
        return this;
    }

    /**
     * 设置填充色
     */

    public ShapeDrawable setSolidColor(@ColorInt int startColor, @ColorInt int endColor) {
        return setSolidColor(new int[] {startColor, endColor});
    }

    public ShapeDrawable setSolidColor(@ColorInt int startColor, @ColorInt int centerColor, @ColorInt int endColor) {
        return setSolidColor(new int[] {startColor, centerColor, endColor});
    }

    public ShapeDrawable setSolidColor(@ColorInt int... colors) {
        mShapeState.setSolidColor(colors);
        if (colors == null) {
            mSolidPaint.setColor(0);
        } else if (colors.length == 1) {
            mSolidPaint.setColor(colors[0]);
            mSolidPaint.clearShadowLayer();
        }
        mRectDirty = true;
        invalidateSelf();
        return this;
    }

    /**
     * 设置填充色渐变类型
     */
    public ShapeDrawable setSolidGradientType(@ShapeGradientTypeLimit int type) {
        mShapeState.setSolidGradientType(type);
        mRectDirty = true;
        invalidateSelf();
        return this;
    }

    /**
     * 设置填充色渐变方向
     */
    public ShapeDrawable setSolidGradientOrientation(@ShapeGradientOrientationLimit int orientation) {
        mShapeState.solidGradientOrientation = orientation;
        mRectDirty = true;
        invalidateSelf();
        return this;
    }

    /**
     * 设置填充色渐变中心 X 点坐标的相对位置（默认值为 0.5）
     */
    public ShapeDrawable setSolidGradientCenterX(float centerX) {
        mShapeState.solidCenterX = centerX;
        mRectDirty = true;
        invalidateSelf();
        return this;
    }

    /**
     * 设置填充色渐变中心 Y 点坐标的相对位置（默认值为 0.5）
     */
    public ShapeDrawable setSolidGradientCenterY(float centerY) {
        mShapeState.solidCenterY = centerY;
        mRectDirty = true;
        invalidateSelf();
        return this;
    }

    /**
     * 设置填充色渐变半径大小
     */
    public ShapeDrawable setSolidGradientRadius(float radius) {
        mShapeState.gradientRadius = radius;
        mRectDirty = true;
        invalidateSelf();
        return this;
    }

    /**
     * 设置边框色
     */

    public ShapeDrawable setStrokeColor(@ColorInt int startColor, @ColorInt int endColor) {
        return setStrokeColor(new int[] {startColor, endColor});
    }

    public ShapeDrawable setStrokeColor(@ColorInt int startColor, @ColorInt int centerColor, @ColorInt int endColor) {
        return setStrokeColor(new int[] {startColor, centerColor, endColor});
    }

    public ShapeDrawable setStrokeColor(@ColorInt int... colors) {
        mShapeState.setStrokeColor(colors);
        if (colors == null) {
            mStrokePaint.setColor(0);
        } else if (colors.length == 1) {
            mStrokePaint.setColor(colors[0]);
            mStrokePaint.clearShadowLayer();
        }
        mRectDirty = true;
        invalidateSelf();
        return this;
    }

    /**
     * 设置边框色渐变方向
     */
    public ShapeDrawable setStrokeGradientOrientation(@ShapeGradientOrientationLimit int orientation) {
        mShapeState.strokeGradientOrientation = orientation;
        mRectDirty = true;
        invalidateSelf();
        return this;
    }

    /**
     * 设置边框大小
     */
    public ShapeDrawable setStrokeSize(int size) {
        mShapeState.setStrokeSize(size);
        mStrokePaint.setStrokeWidth(size);
        mRectDirty = true;
        invalidateSelf();
        return this;
    }

    /**
     * 设置边框每一节虚线宽度
     */
    public ShapeDrawable setStrokeDashSize(float dashSize) {
        mShapeState.strokeDashSize = dashSize;
        mStrokePaint.setPathEffect(dashSize > 0 ?
                new DashPathEffect(new float[] {dashSize, mShapeState.strokeDashGap}, 0) : null);
        mRectDirty = true;
        invalidateSelf();
        return this;
    }

    /**
     * 设置边框虚线每一节间隔
     */
    public ShapeDrawable setStrokeDashGap(float dashGap) {
        mShapeState.strokeDashGap = dashGap;
        mStrokePaint.setPathEffect(mShapeState.strokeDashSize > 0 ?
                new DashPathEffect(new float[] {mShapeState.strokeDashSize, dashGap}, 0) : null);
        mRectDirty = true;
        invalidateSelf();
        return this;
    }

    /**
     * <p>Sets whether or not this drawable will honor its <code>level</code>
     * property.</p>
     * <p><strong>Note</strong>: changing this property will affect all instances
     * of a drawable loaded from a resource. It is recommended to invoke
     * {@link #mutate()} before changing this property.</p>
     *
     * @param useLevel True if this drawable should honor its level, false otherwise
     *
     * @see #mutate()
     * @see #setLevel(int) 
     * @see #getLevel() 
     */
    public ShapeDrawable setUseLevel(boolean useLevel) {
        mShapeState.useLevel = useLevel;
        mRectDirty = true;
        invalidateSelf();
        return this;
    }
    
    /**
     * 设置阴影颜色
     */
    public ShapeDrawable setShadowColor(@ColorInt int color) {
        mShapeState.shadowColor = color;
        mPathDirty = true;
        mRectDirty = true;
        invalidateSelf();
        return this;
    }

    /**
     * 设置阴影大小
     */
    public ShapeDrawable setShadowSize(int size) {
        mShapeState.shadowSize = size;
        mPathDirty = true;
        mRectDirty = true;
        invalidateSelf();
        return this;
    }

    /**
     * 设置阴影水平偏移
     */
    public ShapeDrawable setShadowOffsetX(int offsetX) {
        mShapeState.shadowOffsetX = offsetX;
        mPathDirty = true;
        mRectDirty = true;
        invalidateSelf();
        return this;
    }

    /**
     * 设置阴影垂直偏移
     */
    public ShapeDrawable setShadowOffsetY(int offsetY) {
        mShapeState.shadowOffsetY = offsetY;
        mPathDirty = true;
        mRectDirty = true;
        invalidateSelf();
        return this;
    }

    /**
     * 设置内环的半径大小
     */
    public ShapeDrawable setRingInnerRadiusSize(int size) {
        mShapeState.ringInnerRadiusSize = size;
        mShapeState.ringInnerRadiusRatio = 0;
        mRectDirty = true;
        invalidateSelf();
        return this;
    }

    /**
     * 设置内环的半径比率
     */
    public ShapeDrawable setRingInnerRadiusRatio(float ratio) {
        mShapeState.ringInnerRadiusRatio = ratio;
        mShapeState.ringInnerRadiusSize = -1;
        mRectDirty = true;
        invalidateSelf();
        return this;
    }

    /**
     * 设置外环的厚度大小
     */
    public ShapeDrawable setRingThicknessSize(int size) {
        mShapeState.ringThicknessSize = size;
        mShapeState.ringThicknessRatio = 0;
        mRectDirty = true;
        invalidateSelf();
        return this;
    }

    /**
     * 设置外环的厚度比率
     */
    public ShapeDrawable setRingThicknessRatio(float ratio) {
        mShapeState.ringThicknessRatio = ratio;
        mShapeState.ringThicknessSize = -1;
        mRectDirty = true;
        invalidateSelf();
        return this;
    }

    /**
     * 设置线条重心
     */
    public ShapeDrawable setLineGravity(int lineGravity) {
        mShapeState.lineGravity = lineGravity;
        mRectDirty = true;
        invalidateSelf();
        return this;
    }

    /**
     * 将当前 Drawable 对象应用到 View 背景
     */
    public void intoBackground(View view) {
        if (mShapeState.strokeDashGap > 0 || mShapeState.shadowSize > 0) {
            // 需要关闭硬件加速，否则虚线或者阴影在某些手机上面无法生效
            view.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        }
        view.setBackground(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            // 布局方向
            int layoutDirection = view.getLayoutDirection();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                setLayoutDirection(layoutDirection);
            }
        }
    }

    @SuppressLint("WrongConstant")
    @Override
    public void draw(@NonNull Canvas canvas) {
        if (!ensureValidRect()) {
            // nothing to draw
            return;
        }

        // remember the alpha values, in case we temporarily overwrite them
        // when we modulate them with mAlpha
        final int prevFillAlpha = mSolidPaint.getAlpha();
        final int prevStrokeAlpha = mStrokePaint.getAlpha();
        // compute the modulate alpha values
        final int currFillAlpha = modulateAlpha(prevFillAlpha);
        final int currStrokeAlpha = modulateAlpha(prevStrokeAlpha);

        final boolean haveShadow = mShapeState.shadowSize > 0;
        final boolean haveStroke = currStrokeAlpha > 0 && mStrokePaint.getStrokeWidth() > 0;
        final boolean haveFill = currFillAlpha > 0;
        final ShapeState st = mShapeState;
        /*  we need a layer iff we're drawing both a fill and stroke, and the
            stroke is non-opaque, and our shape type actually supports
            fill+stroke. Otherwise we can just draw the stroke (if any) on top
            of the fill (if any) without worrying about blending artifacts.
         */
         final boolean useLayer = haveStroke && haveFill && st.shapeType != ShapeType.LINE &&
                 currStrokeAlpha < 255 && (mAlpha < 255 || mColorFilter != null);

        /*  Drawing with a layer is slower than direct drawing, but it
            allows us to apply paint effects like alpha and color filter to
            the result of multiple separate draws. In our case, if the user
            asks for a non-opaque alpha value (via setAlpha), and we're
            stroking, then we need to apply the alpha AFTER we've drawn
            both the fill and the stroke.
        */

        if (useLayer) {
            if (mLayerPaint == null) {
                mLayerPaint = new Paint();
            }
            mLayerPaint.setDither(mDither);
            mLayerPaint.setAlpha(mAlpha);
            mLayerPaint.setColorFilter(mColorFilter);

            float rad = mStrokePaint.getStrokeWidth();
            ShapeDrawableUtils.saveCanvasLayer(canvas, mRect.left - rad, mRect.top - rad,
                    mRect.right + rad, mRect.bottom + rad, mLayerPaint);

            // don't perform the filter in our individual paints
            // since the layer will do it for us
            mSolidPaint.setColorFilter(null);
            mStrokePaint.setColorFilter(null);
        } else {
            /*  if we're not using a layer, apply the dither/filter to our
                individual paints
            */
            mSolidPaint.setAlpha(currFillAlpha);
            mSolidPaint.setDither(mDither);
            mSolidPaint.setColorFilter(mColorFilter);
            if (mColorFilter != null && !mShapeState.hasSolidColor) {
                mSolidPaint.setColor(mAlpha << 24);
            }
            if (haveStroke) {
                mStrokePaint.setAlpha(currStrokeAlpha);
                mStrokePaint.setDither(mDither);
                mStrokePaint.setColorFilter(mColorFilter);
            }
        }

        if (haveShadow) {
            if (mShadowPaint == null) {
                mShadowPaint = new Paint();
                mShadowPaint.setColor(Color.TRANSPARENT);
                mShadowPaint.setStyle(Paint.Style.STROKE);
            }

            if (haveStroke) {
                mShadowPaint.setStrokeWidth(mStrokePaint.getStrokeWidth());
            } else {
                mShadowPaint.setStrokeWidth(mShapeState.shadowSize / 4f);
            }

            int shadowColor = mShapeState.shadowColor;
            // 如果阴影颜色是非透明的，则需要设置一点透明度进去，否则会显示不出来
            if (ColorUtils.setAlphaComponent(mShapeState.shadowColor, 255) == mShapeState.shadowColor) {
                shadowColor = ColorUtils.setAlphaComponent(mShapeState.shadowColor, 254);
            }

             mShadowPaint.setColor(shadowColor);

             float shadowRadius;
             // 这里解释一下为什么要阴影大小除以倍数，因为如果不这么做会导致阴影显示会超过 View 边界，从而导致出现阴影被截断的效果
             if (Build.VERSION.SDK_INT >= 28) {
                 shadowRadius = mShapeState.shadowSize / 2f;
             } else {
                 shadowRadius = mShapeState.shadowSize / 3f;
             }
             mShadowPaint.setMaskFilter(new BlurMaskFilter(shadowRadius, BlurMaskFilter.Blur.NORMAL));

        } else {
            if (mShadowPaint != null) {
                mShadowPaint.clearShadowLayer();
            }
        }

        switch (st.shapeType) {
            case ShapeType.RECTANGLE:
                if (st.radiusArray != null) {
                    if (mPathDirty || mRectDirty) {
                        mPath.reset();
                        mPath.addRoundRect(mRect, st.radiusArray, Path.Direction.CW);
                        mPathDirty = mRectDirty = false;
                    }
                    if (haveShadow) {
                        mShadowPath.reset();
                        mShadowPath.addRoundRect(mShadowRect, st.radiusArray, Path.Direction.CW);
                        canvas.drawPath(mShadowPath, mShadowPaint);
                    }
                    canvas.drawPath(mPath, mSolidPaint);
                    if (haveStroke) {
                        canvas.drawPath(mPath, mStrokePaint);
                    }
                } else if (st.radius > 0.0f) {
                    // since the caller is only giving us 1 value, we will force
                    // it to be square if the rect is too small in one dimension
                    // to show it. If we did nothing, Skia would clamp the rad
                    // independently along each axis, giving us a thin ellipse
                    // if the rect were very wide but not very tall
                    float rad = st.radius;
                    float r = Math.min(mRect.width(), mRect.height()) * 0.5f;
                    if (rad > r) {
                        rad = r;
                    }
                    if (haveShadow) {
                        canvas.drawRoundRect(mShadowRect, rad, rad, mShadowPaint);
                    }
                    canvas.drawRoundRect(mRect, rad, rad, mSolidPaint);
                    if (haveStroke) {
                        canvas.drawRoundRect(mRect, rad, rad, mStrokePaint);
                    }
                } else {
                    if (haveShadow) {
                        canvas.drawRect(mShadowRect, mShadowPaint);
                    }
                    if (mSolidPaint.getColor() != 0 || mColorFilter != null ||
                            mSolidPaint.getShader() != null) {
                        canvas.drawRect(mRect, mSolidPaint);
                    }
                    if (haveStroke) {
                        canvas.drawRect(mRect, mStrokePaint);
                    }
                }
                break;
            case ShapeType.OVAL:
                if (haveShadow) {
                    canvas.drawOval(mShadowRect, mShadowPaint);
                }
                canvas.drawOval(mRect, mSolidPaint);
                if (haveStroke) {
                    canvas.drawOval(mRect, mStrokePaint);
                }
                break;
            case ShapeType.LINE: {
                RectF r = mRect;
                float startX;
                float startY;
                float stopX;
                float stopY;
                int lineGravity;
                Callback callback = getCallback();
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1 && callback instanceof View) {
                    int layoutDirection = ((View) callback).getContext().getResources().getConfiguration().getLayoutDirection();
                    lineGravity = Gravity.getAbsoluteGravity(st.lineGravity, layoutDirection);
                } else {
                    lineGravity = st.lineGravity;
                }

                switch (lineGravity) {
                    case Gravity.LEFT:
                        startX = 0;
                        startY = 0;
                        stopX = 0;
                        stopY = r.bottom;
                        break;
                    case Gravity.RIGHT:
                        startX = r.right;
                        startY = 0;
                        stopX = r.right;
                        stopY = r.bottom;
                        break;
                    case Gravity.TOP:
                        startX = 0;
                        startY = 0;
                        stopX = r.right;
                        stopY = 0;
                        break;
                    case Gravity.BOTTOM:
                        startX = 0;
                        startY = r.bottom;
                        stopX = r.right;
                        stopY = r.bottom;
                        break;
                    case Gravity.CENTER:
                    default:
                        float y = r.centerY();
                        startX = r.left;
                        startY = y;
                        stopX = r.right;
                        stopY = y;
                        break;
                }

                if (haveShadow) {
                    canvas.drawLine(startX, startY, stopX, stopY, mShadowPaint);
                }
                canvas.drawLine(startX, startY, stopX, stopY, mStrokePaint);
                break;
            }
            case ShapeType.RING:
                Path path = buildRing(st);
                if (haveShadow) {
                    canvas.drawPath(path, mShadowPaint);
                }
                canvas.drawPath(path, mSolidPaint);
                if (haveStroke) {
                    canvas.drawPath(path, mStrokePaint);
                }
                break;
            default:
                break;
        }

        if (useLayer) {
            canvas.restore();
        } else {
            mSolidPaint.setAlpha(prevFillAlpha);
            if (haveStroke) {
                mStrokePaint.setAlpha(prevStrokeAlpha);
            }
        }
    }

    @Override
    public boolean onLayoutDirectionChanged(int layoutDirection) {
        return mShapeState.shapeType == ShapeType.LINE;
    }

    private int modulateAlpha(int alpha) {
        int scale = mAlpha + (mAlpha >> 7);
        return alpha * scale >> 8;
    }
    
    @Override
    public int getChangingConfigurations() {
        return super.getChangingConfigurations() | mShapeState.changingConfigurations;
    }

    @Override
    public void setAlpha(int alpha) {
        if (alpha != mAlpha) {
            mAlpha = alpha;
            invalidateSelf();
        }
    }

    @Override
    public int getAlpha() {
        return mAlpha;
    }

    @Override
    public void setDither(boolean dither) {
        if (dither != mDither) {
            mDither = dither;
            invalidateSelf();
        }
    }

    @Override
    public void setColorFilter(ColorFilter cf) {
        if (cf != mColorFilter) {
            mColorFilter = cf;
            invalidateSelf();
        }
    }

    @Override
    public int getOpacity() {
        return mShapeState.opaque ? PixelFormat.OPAQUE : PixelFormat.TRANSLUCENT;
    }

    @Override
    protected void onBoundsChange(Rect r) {
        super.onBoundsChange(r);
        mRingPath = null;
        mPathDirty = true;
        mRectDirty = true;
    }

    @Override
    protected boolean onLevelChange(int level) {
        super.onLevelChange(level);
        mRectDirty = true;
        mPathDirty = true;
        invalidateSelf();
        return true;
    }

    private Path buildRing(ShapeState shapeState) {
        if (mRingPath != null && (!shapeState.useLevelForShape || !mPathDirty)) {
            return mRingPath;
        }
        mPathDirty = false;

        float sweep = shapeState.useLevelForShape ? (360.0f * getLevel() / 10000f) : 360f;

        RectF bounds = new RectF(mRect);

        float x = bounds.width() / 2.0f;
        float y = bounds.height() / 2.0f;

        float thickness = shapeState.ringThicknessSize != -1 ?
                shapeState.ringThicknessSize : bounds.width() / shapeState.ringThicknessRatio;
        // inner radius
        float radius = shapeState.ringInnerRadiusSize != -1 ?
                shapeState.ringInnerRadiusSize : bounds.width() / shapeState.ringInnerRadiusRatio;

        RectF innerBounds = new RectF(bounds);
        innerBounds.inset(x - radius, y - radius);

        bounds = new RectF(innerBounds);
        bounds.inset(-thickness, -thickness);

        if (mRingPath == null) {
            mRingPath = new Path();
        } else {
            mRingPath.reset();
        }

        final Path ringPath = mRingPath;
        // arcTo treats the sweep angle mod 360, so check for that, since we
        // think 360 means draw the entire oval
        if (sweep < 360 && sweep > -360) {
            ringPath.setFillType(Path.FillType.EVEN_ODD);
            // inner top
            ringPath.moveTo(x + radius, y);
            // outer top
            ringPath.lineTo(x + radius + thickness, y);
            // outer arc
            ringPath.arcTo(bounds, 0.0f, sweep, false);
            // inner arc
            ringPath.arcTo(innerBounds, sweep, -sweep, false);
            ringPath.close();
        } else {
            // add the entire ovals
            ringPath.addOval(bounds, Path.Direction.CW);
            ringPath.addOval(innerBounds, Path.Direction.CCW);
        }

        return ringPath;
    }

    /**
     * This checks mRectIsDirty, and if it is true, recomputes both our drawing
     * rectangle (mRect) and the gradient itself, since it depends on our
     * rectangle too.
     * @return true if the resulting rectangle is not empty, false otherwise
     */
    private boolean ensureValidRect() {
        if (!mRectDirty) {
            return !mRect.isEmpty();
        }

        mRectDirty = false;

        Rect bounds = getBounds();
        float inset = mStrokePaint.getStrokeWidth() * 0.5f;

        final ShapeState st = mShapeState;

        float let = bounds.left + inset + mShapeState.shadowSize;
        float top = bounds.top + inset + mShapeState.shadowSize;
        float right = bounds.right - inset - mShapeState.shadowSize;
        float bottom = bounds.bottom - inset - mShapeState.shadowSize;

        mRect.set(let, top, right, bottom);

        float shadowLet;
        float shadowTop;
        float shadowRight;
        float shadowBottom;

        if (mShapeState.shadowOffsetX > 0) {
            shadowLet = let + mShapeState.shadowOffsetX;
            shadowRight = right;
        } else {
            shadowLet = let;
            shadowRight = right + mShapeState.shadowOffsetX;
        }

        if (mShapeState.shadowOffsetY > 0) {
            shadowTop = top + mShapeState.shadowOffsetY;
            shadowBottom = bottom;
        } else {
            shadowTop = top;
            shadowBottom = bottom + mShapeState.shadowOffsetY;
        }

        mShadowRect.set(shadowLet, shadowTop, shadowRight, shadowBottom);

        if (st.solidColors == null) {
            mSolidPaint.setShader(null);
        }

        if (st.strokeColors == null) {
            mStrokePaint.setShader(null);
        }

        if (st.solidColors != null) {
            RectF rect = mRect;

            switch (st.solidGradientType) {
                case ShapeGradientType.LINEAR_GRADIENT: {
                    final float level = st.useLevel ? getLevel() / 10000f : 1f;
                    float[] coordinate = ShapeDrawableUtils.computeLinearGradientCoordinate(
                            mRect, level, st.solidGradientOrientation);
                    mSolidPaint.setShader(new LinearGradient(coordinate[0], coordinate[1], coordinate[2], coordinate[3],
                            st.solidColors, st.positions, Shader.TileMode.CLAMP));
                    break;
                }
                case ShapeGradientType.RADIAL_GRADIENT: {
                    float x0;
                    float y0;
                    x0 = rect.left + (rect.right - rect.left) * st.solidCenterX;
                    y0 = rect.top + (rect.bottom - rect.top) * st.solidCenterY;

                    final float level = st.useLevel ? getLevel() / 10000f : 1f;

                    mSolidPaint.setShader(new RadialGradient(x0, y0,
                            level * st.gradientRadius, st.solidColors, null,
                            Shader.TileMode.CLAMP));
                    break;
                }
                case ShapeGradientType.SWEEP_GRADIENT: {
                    float x0;
                    float y0;

                    x0 = rect.left + (rect.right - rect.left) * st.solidCenterX;
                    y0 = rect.top + (rect.bottom - rect.top) * st.solidCenterY;

                    int[] tempSolidColors = st.solidColors;
                    float[] tempSolidPositions = null;

                    if (st.useLevel) {
                        tempSolidColors = st.tempSolidColors;
                        final int length = st.solidColors.length;
                        if (tempSolidColors == null || tempSolidColors.length != length + 1) {
                            tempSolidColors = st.tempSolidColors = new int[length + 1];
                        }
                        System.arraycopy(st.solidColors, 0, tempSolidColors, 0, length);
                        tempSolidColors[length] = st.solidColors[length - 1];


                        tempSolidPositions = st.tempSolidPositions;
                        final float fraction = 1f / (length - 1);
                        if (tempSolidPositions == null || tempSolidPositions.length != length + 1) {
                            tempSolidPositions = st.tempSolidPositions = new float[length + 1];
                        }

                        final float level = getLevel() / 10000f;
                        for (int i = 0; i < length; i++) {
                            tempSolidPositions[i] = i * fraction * level;
                        }
                        tempSolidPositions[length] = 1f;
                    }

                    mSolidPaint.setShader(new SweepGradient(x0, y0, tempSolidColors, tempSolidPositions));
                    break;
                }
                default:
                    break;
            }

            // If we don't have a solid color, the alpha channel must be
            // maxed out so that alpha modulation works correctly.
            if (!st.hasSolidColor) {
                mSolidPaint.setColor(Color.BLACK);
            }
        }

        if (st.strokeColors != null) {
            final float level = st.useLevel ? getLevel() / 10000f : 1f;
            float[] coordinate = ShapeDrawableUtils.computeLinearGradientCoordinate(
                    mRect, level, st.strokeGradientOrientation);
            mStrokePaint.setShader(new LinearGradient(coordinate[0], coordinate[1], coordinate[2], coordinate[3],
                    st.strokeColors, st.positions, Shader.TileMode.CLAMP));

            if (!st.hasStrokeColor) {
                mStrokePaint.setColor(Color.BLACK);
            }
        }
        return !mRect.isEmpty();
    }

    @Override
    public int getIntrinsicWidth() {
        return mShapeState.width;
    }

    @Override
    public int getIntrinsicHeight() {
        return mShapeState.height;
    }
    
    @Override
    public ConstantState getConstantState() {
        mShapeState.changingConfigurations = getChangingConfigurations();
        return mShapeState;
    }

    @NonNull
    @Override
    public Drawable mutate() {
        if (!mMutated && super.mutate() == this) {
            mShapeState = new ShapeState(mShapeState);
            initializeWithState(mShapeState);
            mMutated = true;
        }
        return this;
    }

    private void initializeWithState(ShapeState state) {
        if (state.hasSolidColor) {
            mSolidPaint.setColor(state.solidColor);
        } else if (state.solidColors == null) {
            // If we don't have a solid color and we don't have a gradient,
            // the app is stroking the shape, set the color to the default
            // value of state.solidColor
            mSolidPaint.setColor(0);
        } else {
            // Otherwise, make sure the fill alpha is maxed out.
            mSolidPaint.setColor(Color.BLACK);
        }
        mPadding = state.padding;
        if (state.strokeSize >= 0) {
            if (state.hasStrokeColor) {
                setStrokeColor(state.strokeColor);
            } else {
                setStrokeColor(state.strokeColors);
            }
            setStrokeSize(state.strokeSize);
            setStrokeDashSize(state.strokeDashSize);
            setStrokeDashGap(state.strokeDashGap);
        }
    }
}