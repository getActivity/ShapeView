package com.hjq.shape.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

import com.hjq.shape.IShapeDrawable;
import com.hjq.shape.IShapeTextColor;
import com.hjq.shape.R;

/**
 *    author : Android 轮子哥
 *    github : https://github.com/getActivity/ShapeView
 *    time   : 2021/07/17
 *    desc   : 支持直接定义 Shape 背景的 TextView
 */
public class ShapeTextView extends AppCompatTextView implements
        IShapeDrawable<ShapeTextView>, IShapeTextColor<ShapeTextView> {

    private int mShape;
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

    private int mTextColor;
    private int mTextPressedColor;
    private int mTextDisabledColor;
    private int mTextFocusedColor;
    private int mTextSelectedColor;

    public ShapeTextView(Context context) {
        this(context, null);
    }

    public ShapeTextView(Context context, AttributeSet attrs) {
        this(context, attrs, android.R.attr.textViewStyle);
    }

    public ShapeTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ShapeTextView);
        mShape = typedArray.getInt(R.styleable.ShapeTextView_shape, DEFAULT_SHAPE);
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

        mStartColor = typedArray.getColor(R.styleable.ShapeTextView_shape_startColor, DEFAULT_SHAPE_START_COLOR);
        mCenterColor = typedArray.getColor(R.styleable.ShapeTextView_shape_centerColor, DEFAULT_SHAPE_CENTER_COLOR);
        mEndColor = typedArray.getColor(R.styleable.ShapeTextView_shape_endColor, DEFAULT_SHAPE_END_COLOR);
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
        mDashWidth = (int) typedArray.getDimension(R.styleable.ShapeTextView_shape_dashWidth, DEFAULT_SHAPE_DASH_WIDTH);
        mDashGap = (int) typedArray.getDimension(R.styleable.ShapeTextView_shape_dashGap, DEFAULT_SHAPE_DASH_GAP);

        mTextColor = typedArray.getColor(R.styleable.ShapeTextView_shape_textColor, getTextColors().getDefaultColor());
        mTextPressedColor = typedArray.getColor(R.styleable.ShapeTextView_shape_textPressedColor, getTextColors().getColorForState(new int[]{android.R.attr.state_pressed}, mTextColor));
        mTextDisabledColor = typedArray.getColor(R.styleable.ShapeTextView_shape_textDisabledColor, getTextColors().getColorForState(new int[]{-android.R.attr.state_enabled}, mTextColor));
        mTextFocusedColor = typedArray.getColor(R.styleable.ShapeTextView_shape_textFocusedColor, getTextColors().getColorForState(new int[]{android.R.attr.state_focused}, mTextColor));
        mTextSelectedColor = typedArray.getColor(R.styleable.ShapeTextView_shape_textSelectedColor, getTextColors().getColorForState(new int[]{android.R.attr.state_selected}, mTextColor));

        typedArray.recycle();

        intoBackground();
        intoTextColor();
    }

    /**
     * {@link IShapeDrawable}
     */

    @Override
    public ShapeTextView setShape(int shape) {
        mShape = shape;
        return this;
    }

    @Override
    public int getShape() {
        return mShape;
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
    public void intoBackground() {
        setBackground(buildDrawable());
    }

    /**
     * {@link IShapeTextColor}
     */

    @Override
    public void setTextColor(int color) {
        super.setTextColor(color);
        mTextColor = color;
    }

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
}