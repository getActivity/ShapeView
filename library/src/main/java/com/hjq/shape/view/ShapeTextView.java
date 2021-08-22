package com.hjq.shape.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.view.View;

import com.hjq.shape.R;
import com.hjq.shape.core.IShapeDrawable;
import com.hjq.shape.core.IShapeTextColor;

/**
 *    author : Android 轮子哥
 *    github : https://github.com/getActivity/ShapeView
 *    time   : 2021/07/17
 *    desc   : 支持直接定义 Shape 背景的 TextView
 */
public class ShapeTextView extends AppCompatTextView implements
        IShapeDrawable<ShapeTextView>, IShapeTextColor<ShapeTextView> {

    private int mShapeType;
    private int mShapeWidth;
    private int mShapeHeight;

    private int mSolidColor;
    private int mSolidPressedColor;
    private int mSolidDisabledColor;
    private int mSolidFocusedColor;
    private int mSolidSelectedColor;

    private int mTopLeftRadius;
    private int mTopRightRadius;
    private int mBottomLeftRadius;
    private int mBottomRightRadius;

    private int mStartColor;
    private int mCenterColor;
    private int mEndColor;
    private boolean mUseLevel;
    private int mAngle;
    private int mGradientType;
    private float mCenterX;
    private float mCenterY;
    private int mGradientRadius;

    private int mStrokeColor;
    private int mStrokePressedColor;
    private int mStrokeDisabledColor;
    private int mStrokeFocusedColor;
    private int mStrokeSelectedColor;

    private int mStrokeWidth;
    private int mDashWidth;
    private int mDashGap;

    private int mInnerRadius;
    private float mInnerRadiusRatio;
    private int mThickness;
    private float mThicknessRatio;

    private int mShadowSize;
    private int mShadowColor;
    private int mShadowOffsetX;
    private int mShadowOffsetY;

    private int mTextColor;
    private int mTextPressedColor;
    private int mTextDisabledColor;
    private int mTextFocusedColor;
    private int mTextSelectedColor;

    private int mTextStartColor;
    private int mTextCenterColor;
    private int mTextEndColor;
    private int mTextGradientOrientation;

    public ShapeTextView(Context context) {
        this(context, null);
    }

    public ShapeTextView(Context context, AttributeSet attrs) {
        this(context, attrs, android.R.attr.textViewStyle);
    }

    public ShapeTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ShapeTextView);
        mShapeType = typedArray.getInt(R.styleable.ShapeTextView_shape, DEFAULT_SHAPE_TYPE);
        mShapeWidth = typedArray.getDimensionPixelSize(R.styleable.ShapeTextView_shape_width, DEFAULT_SHAPE_WIDTH);
        mShapeHeight = typedArray.getDimensionPixelSize(R.styleable.ShapeTextView_shape_height, DEFAULT_SHAPE_HEIGHT);

        mSolidColor = typedArray.getColor(R.styleable.ShapeTextView_shape_solidColor, DEFAULT_SHAPE_SOLID_COLOR);
        mSolidPressedColor = typedArray.getColor(R.styleable.ShapeTextView_shape_solidPressedColor, mSolidColor);
        mSolidDisabledColor = typedArray.getColor(R.styleable.ShapeTextView_shape_solidDisabledColor, mSolidColor);
        mSolidFocusedColor = typedArray.getColor(R.styleable.ShapeTextView_shape_solidFocusedColor, mSolidColor);
        mSolidSelectedColor = typedArray.getColor(R.styleable.ShapeTextView_shape_solidSelectedColor, mSolidColor);

        int radius = typedArray.getDimensionPixelSize(R.styleable.ShapeTextView_shape_radius, DEFAULT_SHAPE_RADIUS);
        mTopLeftRadius = typedArray.getDimensionPixelSize(R.styleable.ShapeTextView_shape_topLeftRadius, radius);
        mTopRightRadius = typedArray.getDimensionPixelSize(R.styleable.ShapeTextView_shape_topRightRadius, radius);
        mBottomLeftRadius = typedArray.getDimensionPixelSize(R.styleable.ShapeTextView_shape_bottomLeftRadius, radius);
        mBottomRightRadius = typedArray.getDimensionPixelSize(R.styleable.ShapeTextView_shape_bottomRightRadius, radius);

        mStartColor = typedArray.getColor(R.styleable.ShapeTextView_shape_startColor, mSolidColor);
        mCenterColor = typedArray.getColor(R.styleable.ShapeTextView_shape_centerColor, mSolidColor);
        mEndColor = typedArray.getColor(R.styleable.ShapeTextView_shape_endColor, mSolidColor);
        mUseLevel = typedArray.getBoolean(R.styleable.ShapeTextView_shape_useLevel, DEFAULT_SHAPE_USE_LEVEL);
        mAngle = (int) typedArray.getFloat(R.styleable.ShapeTextView_shape_angle, DEFAULT_SHAPE_ANGLE);
        mGradientType = typedArray.getInt(R.styleable.ShapeTextView_shape_gradientType, DEFAULT_SHAPE_GRADIENT_TYPE);
        mCenterX = typedArray.getFloat(R.styleable.ShapeTextView_shape_centerX, DEFAULT_SHAPE_CENTER_X);
        mCenterY = typedArray.getFloat(R.styleable.ShapeTextView_shape_centerY, DEFAULT_SHAPE_CENTER_Y);
        mGradientRadius = typedArray.getDimensionPixelSize(R.styleable.ShapeTextView_shape_gradientRadius, radius);

        mStrokeColor = typedArray.getColor(R.styleable.ShapeTextView_shape_strokeColor, DEFAULT_SHAPE_STROKE_COLOR);
        mStrokePressedColor = typedArray.getColor(R.styleable.ShapeTextView_shape_strokePressedColor, mStrokeColor);
        mStrokeDisabledColor = typedArray.getColor(R.styleable.ShapeTextView_shape_strokeDisabledColor, mStrokeColor);
        mStrokeFocusedColor = typedArray.getColor(R.styleable.ShapeTextView_shape_strokeFocusedColor, mStrokeColor);
        mStrokeSelectedColor = typedArray.getColor(R.styleable.ShapeTextView_shape_strokeSelectedColor, mStrokeColor);

        mStrokeWidth = typedArray.getDimensionPixelSize(R.styleable.ShapeTextView_shape_strokeWidth, DEFAULT_SHAPE_STROKE_WIDTH);
        mDashWidth = typedArray.getDimensionPixelOffset(R.styleable.ShapeTextView_shape_dashWidth, DEFAULT_SHAPE_DASH_WIDTH);
        mDashGap = typedArray.getDimensionPixelOffset(R.styleable.ShapeTextView_shape_dashGap, DEFAULT_SHAPE_DASH_GAP);

        mInnerRadius = typedArray.getDimensionPixelOffset(R.styleable.ShapeTextView_shape_innerRadius, DEFAULT_SHAPE_INNER_RADIUS);
        mInnerRadiusRatio = typedArray.getFloat(R.styleable.ShapeTextView_shape_innerRadiusRatio, DEFAULT_SHAPE_INNER_RADIUS_RATIO);
        mThickness = typedArray.getDimensionPixelOffset(R.styleable.ShapeTextView_shape_thickness, DEFAULT_SHAPE_THICKNESS);
        mThicknessRatio = typedArray.getFloat(R.styleable.ShapeTextView_shape_thicknessRatio, DEFAULT_SHAPE_THICKNESS_RATIO);

        mShadowSize = typedArray.getDimensionPixelSize(R.styleable.ShapeTextView_shape_shadowSize, DEFAULT_SHAPE_SHADOW_SIZE);
        mShadowColor = typedArray.getColor(R.styleable.ShapeTextView_shape_shadowColor, DEFAULT_SHAPE_SHADOW_COLOR);
        mShadowOffsetX = typedArray.getDimensionPixelOffset(R.styleable.ShapeTextView_shape_shadowOffsetX, DEFAULT_SHAPE_SHADOW_OFFSET_X);
        mShadowOffsetY = typedArray.getDimensionPixelOffset(R.styleable.ShapeTextView_shape_shadowOffsetY, DEFAULT_SHAPE_SHADOW_OFFSET_Y);

        mTextColor = typedArray.getColor(R.styleable.ShapeTextView_shape_textColor, getTextColors().getDefaultColor());
        mTextPressedColor = typedArray.getColor(R.styleable.ShapeTextView_shape_textPressedColor, getTextColors().getColorForState(new int[]{android.R.attr.state_pressed}, mTextColor));
        mTextDisabledColor = typedArray.getColor(R.styleable.ShapeTextView_shape_textDisabledColor, getTextColors().getColorForState(new int[]{-android.R.attr.state_enabled}, mTextColor));
        mTextFocusedColor = typedArray.getColor(R.styleable.ShapeTextView_shape_textFocusedColor, getTextColors().getColorForState(new int[]{android.R.attr.state_focused}, mTextColor));
        mTextSelectedColor = typedArray.getColor(R.styleable.ShapeTextView_shape_textSelectedColor, getTextColors().getColorForState(new int[]{android.R.attr.state_selected}, mTextColor));

        mTextStartColor = typedArray.getColor(R.styleable.ShapeTextView_shape_textStartColor, mTextColor);
        mTextCenterColor = typedArray.getColor(R.styleable.ShapeTextView_shape_textCenterColor, mTextColor);
        mTextEndColor = typedArray.getColor(R.styleable.ShapeTextView_shape_textEndColor, mTextColor);
        mTextGradientOrientation = typedArray.getColor(R.styleable.ShapeTextView_shape_textGradientOrientation, SHAPE_TEXT_GRADIENT_TYPE_HORIZONTAL);

        typedArray.recycle();

        intoBackground();

        if (isTextGradientColor()) {
            setText(getText());
        } else {
            intoTextColor();
        }
    }

    @Override
    public void setTextColor(int color) {
        super.setTextColor(color);
        mTextColor = color;

        clearTextGradientColor();
    }

    /**
     * {@link IShapeDrawable}
     */

    @Override
    public ShapeTextView setShapeType(int type) {
        mShapeType = type;
        return this;
    }

    @Override
    public int getShapeType() {
        return mShapeType;
    }

    @Override
    public ShapeTextView setShapeWidth(int width) {
        mShapeWidth = width;
        return this;
    }

    @Override
    public int getShapeWidth() {
        return mShapeWidth;
    }

    @Override
    public ShapeTextView setShapeHeight(int height) {
        mShapeHeight = height;
        return this;
    }

    @Override
    public int getShapeHeight() {
        return mShapeHeight;
    }

    @Override
    public ShapeTextView setSolidColor(int color) {
        mSolidColor = color;
        return this;
    }

    @Override
    public int getSolidColor() {
        return mSolidColor;
    }

    @Override
    public ShapeTextView setSolidPressedColor(int color) {
        mSolidPressedColor = color;
        return this;
    }

    @Override
    public int getSolidPressedColor() {
        return mSolidPressedColor;
    }

    @Override
    public ShapeTextView setSolidDisabledColor(int color) {
        mSolidDisabledColor = color;
        return this;
    }

    @Override
    public int getSolidDisabledColor() {
        return mSolidDisabledColor;
    }

    @Override
    public ShapeTextView setSolidFocusedColor(int color) {
        mSolidFocusedColor = color;
        return this;
    }

    @Override
    public int getSolidFocusedColor() {
        return mSolidFocusedColor;
    }

    @Override
    public ShapeTextView setSolidSelectedColor(int color) {
        mSolidSelectedColor = color;
        return this;
    }

    @Override
    public int getSolidSelectedColor() {
        return mSolidSelectedColor;
    }

    @Override
    public ShapeTextView setTopLeftRadius(int radius) {
        mTopLeftRadius = radius;
        return this;
    }

    @Override
    public int getTopLeftRadius() {
        return mTopLeftRadius;
    }

    @Override
    public ShapeTextView setTopRightRadius(int radius) {
        mTopRightRadius = radius;
        return this;
    }

    @Override
    public int getTopRightRadius() {
        return mTopRightRadius;
    }

    @Override
    public ShapeTextView setBottomLeftRadius(int radius) {
        mBottomLeftRadius = radius;
        return this;
    }

    @Override
    public int getBottomLeftRadius() {
        return mBottomLeftRadius;
    }

    @Override
    public ShapeTextView setBottomRightRadius(int radius) {
        mBottomRightRadius = radius;
        return this;
    }

    @Override
    public int getBottomRightRadius() {
        return mBottomRightRadius;
    }

    @Override
    public ShapeTextView setStartColor(int color) {
        mStartColor = color;
        return this;
    }

    @Override
    public int getStartColor() {
        return mStartColor;
    }

    @Override
    public ShapeTextView setCenterColor(int color) {
        mCenterColor = color;
        return this;
    }

    @Override
    public int getCenterColor() {
        return mCenterColor;
    }

    @Override
    public ShapeTextView setEndColor(int color) {
        mEndColor = color;
        return this;
    }

    @Override
    public int getEndColor() {
        return mEndColor;
    }

    @Override
    public ShapeTextView setUseLevel(boolean useLevel) {
        mUseLevel = useLevel;
        return this;
    }

    @Override
    public boolean isUseLevel() {
        return mUseLevel;
    }

    @Override
    public ShapeTextView setAngle(int angle) {
        mAngle = angle;
        return this;
    }

    @Override
    public int getAngle() {
        return mAngle;
    }

    @Override
    public ShapeTextView setGradientType(int type) {
        mGradientType = type;
        return this;
    }

    @Override
    public int getGradientType() {
        return mGradientType;
    }

    @Override
    public ShapeTextView setCenterX(float x) {
        mCenterX = x;
        return this;
    }

    @Override
    public float getCenterX() {
        return mCenterX;
    }

    @Override
    public ShapeTextView setCenterY(float y) {
        mCenterY = y;
        return this;
    }

    @Override
    public float getCenterY() {
        return mCenterY;
    }

    @Override
    public ShapeTextView setGradientRadius(int radius) {
        mGradientRadius = radius;
        return this;
    }

    @Override
    public int getGradientRadius() {
        return mGradientRadius;
    }

    @Override
    public ShapeTextView setStrokeColor(int color) {
        mStrokeColor = color;
        return this;
    }

    @Override
    public int getStrokeColor() {
        return mStrokeColor;
    }

    @Override
    public ShapeTextView setStrokePressedColor(int color) {
        mStrokePressedColor = color;
        return this;
    }

    @Override
    public int getStrokePressedColor() {
        return mStrokePressedColor;
    }

    @Override
    public ShapeTextView setStrokeDisabledColor(int color) {
        mStrokeDisabledColor = color;
        return this;
    }

    @Override
    public int getStrokeDisabledColor() {
        return mStrokeDisabledColor;
    }

    @Override
    public ShapeTextView setStrokeFocusedColor(int color) {
        mStrokeFocusedColor = color;
        return this;
    }

    @Override
    public int getStrokeFocusedColor() {
        return mStrokeFocusedColor;
    }

    @Override
    public ShapeTextView setStrokeSelectedColor(int color) {
        mStrokeSelectedColor = color;
        return this;
    }

    @Override
    public int getStrokeSelectedColor() {
        return mStrokeSelectedColor;
    }

    @Override
    public ShapeTextView setStrokeWidth(int width) {
        mStrokeWidth = width;
        return this;
    }

    @Override
    public int getStrokeWidth() {
        return mStrokeWidth;
    }

    @Override
    public ShapeTextView setDashWidth(int width) {
        mDashWidth = width;
        return this;
    }

    @Override
    public int getDashWidth() {
        return mDashWidth;
    }

    @Override
    public ShapeTextView setDashGap(int gap) {
        mDashGap = gap;
        return this;
    }

    @Override
    public int getDashGap() {
        return mDashGap;
    }

    @Override
    public ShapeTextView setInnerRadius(int radius) {
        mInnerRadius = radius;
        return this;
    }

    @Override
    public int getInnerRadius() {
        return mInnerRadius;
    }

    @Override
    public ShapeTextView setInnerRadiusRatio(float ratio) {
        mInnerRadiusRatio = ratio;
        return this;
    }

    @Override
    public float getInnerRadiusRatio() {
        return mInnerRadiusRatio;
    }

    @Override
    public ShapeTextView setThickness(int size) {
        mThickness = size;
        return this;
    }

    @Override
    public int getThickness() {
        return mThickness;
    }

    @Override
    public ShapeTextView setThicknessRatio(float ratio) {
        mThicknessRatio = ratio;
        return this;
    }

    @Override
    public float getThicknessRatio() {
        return mThicknessRatio;
    }

    @Override
    public ShapeTextView setShadowSize(int size) {
        mShadowSize = size;
        return this;
    }

    @Override
    public int getShadowSize() {
        return mShadowSize;
    }

    @Override
    public ShapeTextView setShadowColor(int color) {
        mShadowColor = color;
        return this;
    }

    @Override
    public int getShadowColor() {
        return mShadowColor;
    }

    /**
     * 获取文本阴影颜色（避免和 TextView 原有的方法冲突）
     */
    public int getTextShadowColor() {
        return super.getShadowColor();
    }

    @Override
    public ShapeTextView setShadowOffsetX(int offsetX) {
        mShadowOffsetX = offsetX;
        return this;
    }

    @Override
    public int getShadowOffsetX() {
        return mShadowOffsetX;
    }

    @Override
    public ShapeTextView setShadowOffsetY(int offsetY) {
        mShadowOffsetY = offsetY;
        return this;
    }

    @Override
    public int getShadowOffsetY() {
        return mShadowOffsetY;
    }

    @Override
    public void intoBackground() {
        Drawable drawable = buildBackgroundDrawable();
        if (drawable == null) {
            return;
        }
        if (isShadowEnable()) {
            // 需要关闭硬件加速，否则阴影无法生效
            setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        }
        setBackground(drawable);
    }

    /**
     * {@link IShapeTextColor}
     */

    @Override
    public ShapeTextView setNormalTextColor(int color) {
        mTextColor = color;
        return this;
    }

    @Override
    public int getNormalTextColor() {
        return mTextColor;
    }

    @Override
    public ShapeTextView setTextPressedColor(int color) {
        mTextPressedColor = color;
        return this;
    }

    @Override
    public int getTextPressedColor() {
        return mTextPressedColor;
    }

    @Override
    public ShapeTextView setTextDisabledColor(int color) {
        mTextDisabledColor = color;
        return this;
    }

    @Override
    public int getTextDisabledColor() {
        return mTextDisabledColor;
    }

    @Override
    public ShapeTextView setTextFocusedColor(int color) {
        mTextFocusedColor = color;
        return this;
    }

    @Override
    public int getTextFocusedColor() {
        return mTextFocusedColor;
    }

    @Override
    public ShapeTextView setTextSelectedColor(int color) {
        mTextSelectedColor = color;
        return this;
    }

    @Override
    public int getTextSelectedColor() {
        return mTextSelectedColor;
    }

    @Override
    public void intoTextColor() {
        setTextColor(buildColorState());
    }

    @Override
    public ShapeTextView setTextStartColor(int color) {
        mTextStartColor = color;
        return this;
    }

    @Override
    public int getTextStartColor() {
        return mTextStartColor;
    }

    @Override
    public ShapeTextView setTextCenterColor(int color) {
        mTextCenterColor = color;
        return this;
    }

    @Override
    public int getTextCenterColor() {
        return mTextCenterColor;
    }

    @Override
    public ShapeTextView setTextEndColor(int color) {
        mTextEndColor = color;
        return this;
    }

    @Override
    public int getTextEndColor() {
        return mTextEndColor;
    }

    @Override
    public ShapeTextView setTextGradientOrientation(int orientation) {
        mTextGradientOrientation = orientation;
        return this;
    }

    @Override
    public int getTextGradientOrientation() {
        return mTextGradientOrientation;
    }

    @Override
    public void setText(CharSequence text, BufferType type) {
        if (isTextGradientColor()) {
            super.setText(buildLinearGradientSpannable(text), type);
        } else {
            super.setText(text, type);
        }
    }
}