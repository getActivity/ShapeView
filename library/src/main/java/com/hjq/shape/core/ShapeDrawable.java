package com.hjq.shape.core;

import android.annotation.SuppressLint;
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

/**
 *    author : Android 轮子哥
 *    github : https://github.com/getActivity/ToastUtils
 *    time   : 2021/08/14
 *    desc   : 在 {@link android.graphics.drawable.GradientDrawable} 的基础上改造
 */
public class ShapeDrawable extends Drawable {

    private ShapeState mShapeState;
    
    private final Paint mFillPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Rect mPadding;
    private Paint mStrokePaint;   // optional, set by the caller
    private ColorFilter mColorFilter;   // optional, set by the caller
    private int mAlpha = 0xFF;  // modified by the caller
    private boolean mDither;

    private final Path mPath = new Path();
    private final RectF mRect = new RectF();
    
    private Paint mLayerPaint;    // internal, used if we use saveLayer()
    private boolean mRectIsDirty;   // internal state
    private boolean mMutated;
    private Path mRingPath;
    private boolean mPathIsDirty = true;

    public ShapeDrawable() {
        this(new ShapeState(ShapeGradientOrientation.TOP_BOTTOM, null));
    }
    
    public ShapeDrawable(ShapeGradientOrientation orientation, int[] colors) {
        this(new ShapeState(orientation, colors));
    }

    public ShapeDrawable(ShapeState state) {
        mShapeState = state;
        initializeWithState(state);
        mRectIsDirty = true;
        mMutated = false;
    }
    
    @Override
    public boolean getPadding(Rect padding) {
        if (mPadding != null) {
            padding.set(mPadding);
            return true;
        } else {
            return super.getPadding(padding);
        }
    }

    public void setPadding(Rect padding) {
        mPadding = padding;
    }

    /**
     * 为 4 个角中的每一个指定半径。 对于每个角，该数组包含 2 个值， [X_radius, Y_radius] 。 角的顺序是左上角、右上角、右下角、左下角。 仅当形状为RECTANGLE类型时才使用此属性
     * 注意：更改此属性将影响从资源加载的可绘制对象的所有实例。 建议在更改此属性之前调用mutate()
     *
     * @param radii         每个角的 4 对 X 和 Y 半径，以像素为单位。 此数组的长度必须 >= 8
     */
    public void setCornerRadii(float[] radii) {
        mShapeState.setCornerRadii(radii);
        mPathIsDirty = true;
        invalidateSelf();
    }
    
    /**
     * 指定渐变角的半径。 如果这 > 0，则可绘制对象将绘制在圆角矩形中，而不是矩形中。 仅当形状为RECTANGLE类型时才使用此属性。
     * 注意：更改此属性将影响从资源加载的可绘制对象的所有实例。 建议在更改此属性之前调用mutate() 。
     *
     * @param radius       矩形角的半径（以像素为单位）
     */
    public void setCornerRadius(float radius) {
        mShapeState.setCornerRadius(radius);
        mPathIsDirty = true;
        invalidateSelf();
    }

    /**
     * 设置边框属性
     *
     * @param width       边框宽度
     * @param color       边框颜色
     */
    public void setStroke(int width, int color) {
        setStroke(width, color, 0, 0);
    }

    /**
     * 设置边框属性
     *
     * @param width             边框宽度
     * @param color             边框宽度
     * @param dashWidth         边框虚线宽度（为 0 就是实线，大于 0 就是虚线）
     * @param dashGap           边框虚线间隔（虚线与虚线之间的间隔）
     */
    public void setStroke(int width, int color, float dashWidth, float dashGap) {
        mShapeState.setStroke(width, color, dashWidth, dashGap);

        if (mStrokePaint == null)  {
            mStrokePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
            mStrokePaint.setStyle(Paint.Style.STROKE);
        }
        mStrokePaint.setStrokeWidth(width);
        mStrokePaint.setColor(color);
        
        DashPathEffect e = null;
        if (dashWidth > 0) {
            e = new DashPathEffect(new float[] { dashWidth, dashGap }, 0);
        }
        mStrokePaint.setPathEffect(e);
        invalidateSelf();
    }

    /**
     * 设置 Shape 大小
     *
     * @param width         Shape 宽度
     * @param height        Shape 高度
     */
    public void setSize(int width, int height) {
        mShapeState.setSize(width, height);
        mPathIsDirty = true;
        invalidateSelf();
    }

    /**
     * 设置 Shape 形状
     *
     * @param shape         Shape 形状类型
     */
    public void setShape(int shape) {
        mRingPath = null;
        mPathIsDirty = true;
        mShapeState.setShapeType(shape);
        invalidateSelf();
    }

    /**
     * 设置 Shape 渐变类型
     */
    public void setGradientType(int type) {
        mShapeState.setGradientType(type);
        mRectIsDirty = true;
        invalidateSelf();
    }

    /**
     * 设置渐变中心位置
     */
    public void setGradientCenter(float x, float y) {
        mShapeState.setGradientCenter(x, y);
        mRectIsDirty = true;
        invalidateSelf();
    }

    /**
     * 设置渐变半径大小
     */
    public void setGradientRadius(float radius) {
        mShapeState.setGradientRadius(radius);
        mRectIsDirty = true;
        invalidateSelf();
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
    public void setUseLevel(boolean useLevel) {
        mShapeState.mUseLevel = useLevel;
        mRectIsDirty = true;
        invalidateSelf();
    }
    
    private int modulateAlpha(int alpha) {
        int scale = mAlpha + (mAlpha >> 7);
        return alpha * scale >> 8;
    }

    /**
     * 设置渐变角度
     */
    public void setGradientAngle(int angle) {
        angle %= 360;
        // angle 必须为 45 的整数倍
        if (angle % 45 == 0) {
            switch (angle) {
                case 0:
                    setGradientOrientation(ShapeGradientOrientation.LEFT_RIGHT);
                    break;
                case 45:
                    setGradientOrientation(ShapeGradientOrientation.BL_TR);
                    break;
                case 90:
                    setGradientOrientation(ShapeGradientOrientation.BOTTOM_TOP);
                    break;
                case 135:
                    setGradientOrientation(ShapeGradientOrientation.BR_TL);
                    break;
                case 180:
                    setGradientOrientation(ShapeGradientOrientation.RIGHT_LEFT);
                    break;
                case 225:
                    setGradientOrientation(ShapeGradientOrientation.TR_BL);
                    break;
                case 270:
                    setGradientOrientation(ShapeGradientOrientation.TOP_BOTTOM);
                    break;
                case 315:
                    setGradientOrientation(ShapeGradientOrientation.TL_BR);
                    break;
                default:
                    break;
            }
        }
    }

    /**
     * 获取渐变方向
     */
    public ShapeGradientOrientation getGradientOrientation() {
        return mShapeState.mOrientation;
    }

    /**
     * 设置渐变方向
     */
    public void setGradientOrientation(ShapeGradientOrientation orientation) {
        mShapeState.mOrientation = orientation;
        mRectIsDirty = true;
        invalidateSelf();
    }

    /**
     * 设置填充颜色
     */
    public void setColor(int argb) {
        mShapeState.setSolidColor(argb);
        mFillPaint.setColor(argb);
        invalidateSelf();
    }

    /**
     * 设置渐变颜色
     */
    public void setColors(int[] colors) {
        mShapeState.setGradientColor(colors);
        mRectIsDirty = true;
        invalidateSelf();
    }

    /**
     * 设置内环的半径
     */
    public void setInnerRadius(int radius) {
        mShapeState.mInnerRadius = radius;
        mRectIsDirty = true;
        invalidateSelf();
    }

    /**
     * 设置内环的半径比率
     */
    public void setInnerRadiusRatio(float radiusRatio) {
        mShapeState.mInnerRadiusRatio = radiusRatio;
        mRectIsDirty = true;
        invalidateSelf();
    }

    /**
     * 设置外环的厚度
     */
    public void setThickness(int size) {
        mShapeState.mThickness = size;
        mRectIsDirty = true;
        invalidateSelf();
    }

    /**
     * 设置外环的厚度比率
     */
    public void setThicknessRatio(float radiusRatio) {
        mShapeState.mThicknessRatio = radiusRatio;
        mRectIsDirty = true;
        invalidateSelf();
    }

    /**
     * 设置阴影颜色
     */
    public void setShadowColor(int color) {
        mShapeState.setShadowColor(color);
        mPathIsDirty = true;
        invalidateSelf();
    }

    /**
     * 设置阴影大小
     */
    public void setShadowSize(int size) {
        mShapeState.setShadowSize(size);
        mPathIsDirty = true;
        invalidateSelf();
    }

    /**
     * 设置阴影水平偏移
     */
    public void setShadowOffsetX(int offsetX) {
        mShapeState.setShadowOffsetX(offsetX);
        mPathIsDirty = true;
        invalidateSelf();
    }

    /**
     * 阴影垂直偏移
     */
    public void setShadowOffsetY(int offsetY) {
        mShapeState.setShadowOffsetY(offsetY);
        mPathIsDirty = true;
        invalidateSelf();
    }

    @SuppressLint("WrongConstant")
    @Override
    public void draw(Canvas canvas) {
        if (!ensureValidRect()) {
            // nothing to draw
            return;
        }

        // remember the alpha values, in case we temporarily overwrite them
        // when we modulate them with mAlpha
        final int prevFillAlpha = mFillPaint.getAlpha();
        final int prevStrokeAlpha = mStrokePaint != null ? mStrokePaint.getAlpha() : 0;
        // compute the modulate alpha values
        final int currFillAlpha = modulateAlpha(prevFillAlpha);
        final int currStrokeAlpha = modulateAlpha(prevStrokeAlpha);

        final boolean haveStroke = currStrokeAlpha > 0 && mStrokePaint != null &&
                mStrokePaint.getStrokeWidth() > 0;
        final boolean haveFill = currFillAlpha > 0;
        final ShapeState st = mShapeState;
        /*  we need a layer iff we're drawing both a fill and stroke, and the
            stroke is non-opaque, and our shapetype actually supports
            fill+stroke. Otherwise we can just draw the stroke (if any) on top
            of the fill (if any) without worrying about blending artifacts.
         */
         final boolean useLayer = haveStroke && haveFill && st.mShapeType != ShapeType.LINE &&
                 currStrokeAlpha < 255 && (mAlpha < 255 || mColorFilter != null);

        /*  Drawing with a layer is slower than direct drawing, but it
            allows us to apply paint effects like alpha and colorfilter to
            the result of multiple separate draws. In our case, if the user
            asks for a non-opaque alpha value (via setAlpha), and we're
            stroking, then we need to apply the alpha AFTER we've drawn
            both the fill and the stroke.
        */

        if (mShapeState.mShadowSize > 0) {
            if (haveStroke) {
                mStrokePaint.setShadowLayer(mShapeState.mShadowSize, mShapeState.mShadowOffsetX, mShapeState.mShadowOffsetY, mShapeState.mShadowColor);
            } else {
                mFillPaint.setShadowLayer(mShapeState.mShadowSize, mShapeState.mShadowOffsetX, mShapeState.mShadowOffsetY, mShapeState.mShadowColor);
            }
        } else {
            if (haveStroke) {
                mStrokePaint.clearShadowLayer();
            } else {
                mFillPaint.clearShadowLayer();
            }
        }

        if (useLayer) {
            if (mLayerPaint == null) {
                mLayerPaint = new Paint();
            }
            mLayerPaint.setDither(mDither);
            mLayerPaint.setAlpha(mAlpha);
            mLayerPaint.setColorFilter(mColorFilter);

            float rad = mStrokePaint.getStrokeWidth();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                canvas.saveLayer(mRect.left - rad, mRect.top - rad,
                        mRect.right + rad, mRect.bottom + rad,
                        mLayerPaint);
            } else {
                canvas.saveLayer(mRect.left - rad, mRect.top - rad,
                        mRect.right + rad, mRect.bottom + rad,
                        mLayerPaint, 0x04);
            }

            // don't perform the filter in our individual paints
            // since the layer will do it for us
            mFillPaint.setColorFilter(null);
            mStrokePaint.setColorFilter(null);
        } else {
            /*  if we're not using a layer, apply the dither/filter to our
                individual paints
            */
            mFillPaint.setAlpha(currFillAlpha);
            mFillPaint.setDither(mDither);
            mFillPaint.setColorFilter(mColorFilter);
            if (mColorFilter != null && !mShapeState.mHasSolidColor) {
                mFillPaint.setColor(mAlpha << 24);
            }
            if (haveStroke) {
                mStrokePaint.setAlpha(currStrokeAlpha);
                mStrokePaint.setDither(mDither);
                mStrokePaint.setColorFilter(mColorFilter);
            }
        }

        switch (st.mShapeType) {
            case ShapeType.RECTANGLE:
                if (st.mRadiusArray != null) {
                    if (mPathIsDirty || mRectIsDirty) {
                        mPath.reset();
                        mPath.addRoundRect(mRect, st.mRadiusArray, Path.Direction.CW);
                        mPathIsDirty = mRectIsDirty = false;
                    }
                    canvas.drawPath(mPath, mFillPaint);
                    if (haveStroke) {
                        canvas.drawPath(mPath, mStrokePaint);
                    }
                } else if (st.mRadius > 0.0f) {
                    // since the caller is only giving us 1 value, we will force
                    // it to be square if the rect is too small in one dimension
                    // to show it. If we did nothing, Skia would clamp the rad
                    // independently along each axis, giving us a thin ellipse
                    // if the rect were very wide but not very tall
                    float rad = st.mRadius;
                    float r = Math.min(mRect.width(), mRect.height()) * 0.5f;
                    if (rad > r) {
                        rad = r;
                    }
                    canvas.drawRoundRect(mRect, rad, rad, mFillPaint);
                    if (haveStroke) {
                        canvas.drawRoundRect(mRect, rad, rad, mStrokePaint);
                    }
                } else {
                    if (mFillPaint.getColor() != 0 || mColorFilter != null ||
                            mFillPaint.getShader() != null) {
                        canvas.drawRect(mRect, mFillPaint);
                    }
                    if (haveStroke) {
                        canvas.drawRect(mRect, mStrokePaint);
                    }
                }
                break;
            case ShapeType.OVAL:
                canvas.drawOval(mRect, mFillPaint);
                if (haveStroke) {
                    canvas.drawOval(mRect, mStrokePaint);
                }
                break;
            case ShapeType.LINE: {
                RectF r = mRect;
                float y = r.centerY();
                canvas.drawLine(r.left, y, r.right, y, mStrokePaint);
                break;
            }
            case ShapeType.RING:
                Path path = buildRing(st);
                canvas.drawPath(path, mFillPaint);
                if (haveStroke) {
                    canvas.drawPath(path, mStrokePaint);
                }
                break;
        }

        if (useLayer) {
            canvas.restore();
        } else {
            mFillPaint.setAlpha(prevFillAlpha);
            if (haveStroke) {
                mStrokePaint.setAlpha(prevStrokeAlpha);
            }
        }
    }
    
    @Override
    public int getChangingConfigurations() {
        return super.getChangingConfigurations() | mShapeState.mChangingConfigurations;
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
        return mShapeState.mOpaque ? PixelFormat.OPAQUE : PixelFormat.TRANSLUCENT;
    }

    @Override
    protected void onBoundsChange(Rect r) {
        super.onBoundsChange(r);
        mRingPath = null;
        mPathIsDirty = true;
        mRectIsDirty = true;
    }

    @Override
    protected boolean onLevelChange(int level) {
        super.onLevelChange(level);
        mRectIsDirty = true;
        mPathIsDirty = true;
        invalidateSelf();
        return true;
    }

    private Path buildRing(ShapeState st) {
        if (mRingPath != null && (!st.mUseLevelForShape || !mPathIsDirty)) {
            return mRingPath;
        }
        mPathIsDirty = false;

        float sweep = st.mUseLevelForShape ? (360.0f * getLevel() / 10000.0f) : 360f;

        RectF bounds = new RectF(mRect);

        float x = bounds.width() / 2.0f;
        float y = bounds.height() / 2.0f;

        float thickness = st.mThickness != -1 ?
                st.mThickness : bounds.width() / st.mThicknessRatio;
        // inner radius
        float radius = st.mInnerRadius != -1 ?
                st.mInnerRadius : bounds.width() / st.mInnerRadiusRatio;

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
        if (mRectIsDirty) {
            mRectIsDirty = false;

            Rect bounds = getBounds();
            float inset = 0;
            
            if (mStrokePaint != null) {
                inset = mStrokePaint.getStrokeWidth() * 0.5f;
            }

            final ShapeState st = mShapeState;

            mRect.set(bounds.left + inset + mShapeState.mShadowSize, bounds.top + inset + mShapeState.mShadowSize,
                      bounds.right - inset - mShapeState.mShadowSize, bounds.bottom - inset - mShapeState.mShadowSize);

            final int[] colors = st.mGradientColors;
            if (colors != null) {
                RectF r = mRect;
                float x0, x1, y0, y1;

                if (st.mGradient == ShapeGradientType.LINEAR_GRADIENT) {
                    final float level = st.mUseLevel ? (float) getLevel() / 10000.0f : 1.0f;                    
                    switch (st.mOrientation) {
                    case TOP_BOTTOM:
                        x0 = r.left;            y0 = r.top;
                        x1 = x0;                y1 = level * r.bottom;
                        break;
                    case TR_BL:
                        x0 = r.right;           y0 = r.top;
                        x1 = level * r.left;    y1 = level * r.bottom;
                        break;
                    case RIGHT_LEFT:
                        x0 = r.right;           y0 = r.top;
                        x1 = level * r.left;    y1 = y0;
                        break;
                    case BR_TL:
                        x0 = r.right;           y0 = r.bottom;
                        x1 = level * r.left;    y1 = level * r.top;
                        break;
                    case BOTTOM_TOP:
                        x0 = r.left;            y0 = r.bottom;
                        x1 = x0;                y1 = level * r.top;
                        break;
                    case BL_TR:
                        x0 = r.left;            y0 = r.bottom;
                        x1 = level * r.right;   y1 = level * r.top;
                        break;
                    case LEFT_RIGHT:
                        x0 = r.left;            y0 = r.top;
                        x1 = level * r.right;   y1 = y0;
                        break;
                    default:/* TL_BR */
                        x0 = r.left;            y0 = r.top;
                        x1 = level * r.right;   y1 = level * r.bottom;
                        break;
                    }

                    mFillPaint.setShader(new LinearGradient(x0, y0, x1, y1,
                            colors, st.mPositions, Shader.TileMode.CLAMP));
                } else if (st.mGradient == ShapeGradientType.RADIAL_GRADIENT) {
                    x0 = r.left + (r.right - r.left) * st.mCenterX;
                    y0 = r.top + (r.bottom - r.top) * st.mCenterY;

                    final float level = st.mUseLevel ? (float) getLevel() / 10000.0f : 1.0f;

                    mFillPaint.setShader(new RadialGradient(x0, y0,
                            level * st.mGradientRadius, colors, null,
                            Shader.TileMode.CLAMP));
                } else if (st.mGradient == ShapeGradientType.SWEEP_GRADIENT) {
                    x0 = r.left + (r.right - r.left) * st.mCenterX;
                    y0 = r.top + (r.bottom - r.top) * st.mCenterY;

                    int[] tempColors = colors;
                    float[] tempPositions = null;

                    if (st.mUseLevel) {
                        tempColors = st.mTempColors;
                        final int length = colors.length;
                        if (tempColors == null || tempColors.length != length + 1) {
                            tempColors = st.mTempColors = new int[length + 1];
                        }
                        System.arraycopy(colors, 0, tempColors, 0, length);
                        tempColors[length] = colors[length - 1];

                        tempPositions = st.mTempPositions;
                        final float fraction = 1.0f / (float) (length - 1);
                        if (tempPositions == null || tempPositions.length != length + 1) {
                            tempPositions = st.mTempPositions = new float[length + 1];
                        }

                        final float level = (float) getLevel() / 10000.0f;
                        for (int i = 0; i < length; i++) {
                            tempPositions[i] = i * fraction * level;
                        }
                        tempPositions[length] = 1.0f;

                    }
                    mFillPaint.setShader(new SweepGradient(x0, y0, tempColors, tempPositions));
                }

                // If we don't have a solid color, the alpha channel must be
                // maxed out so that alpha modulation works correctly.
                if (!st.mHasSolidColor) {
                    mFillPaint.setColor(Color.BLACK);
                }
            }
        }
        return !mRect.isEmpty();
    }

    @Override
    public int getIntrinsicWidth() {
        return mShapeState.mWidth;
    }

    @Override
    public int getIntrinsicHeight() {
        return mShapeState.mHeight;
    }
    
    @Override
    public ConstantState getConstantState() {
        mShapeState.mChangingConfigurations = getChangingConfigurations();
        return mShapeState;
    }

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
        if (state.mHasSolidColor) {
            mFillPaint.setColor(state.mSolidColor);
        } else if (state.mGradientColors == null) {
            // If we don't have a solid color and we don't have a gradient,
            // the app is stroking the shape, set the color to the default
            // value of state.mSolidColor
            mFillPaint.setColor(0);
        } else {
            // Otherwise, make sure the fill alpha is maxed out.
            mFillPaint.setColor(Color.BLACK);
        }
        mPadding = state.mPadding;
        if (state.mStrokeWidth >= 0) {
            mStrokePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
            mStrokePaint.setStyle(Paint.Style.STROKE);
            mStrokePaint.setStrokeWidth(state.mStrokeWidth);
            mStrokePaint.setColor(state.mStrokeColor);

            if (state.mStrokeDashWidth != 0.0f) {
                DashPathEffect e = new DashPathEffect(
                        new float[] { state.mStrokeDashWidth, state.mStrokeDashGap }, 0);
                mStrokePaint.setPathEffect(e);
            }
        }
    }
}