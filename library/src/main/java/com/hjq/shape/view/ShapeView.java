package com.hjq.shape.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;

import com.hjq.shape.IShapeDrawable;
import com.hjq.shape.R;

/**
 *    author : Android 轮子哥
 *    github : https://github.com/getActivity/ShapeView
 *    time   : 2021/07/17
 *    desc   : 支持直接定义 Shape 背景的 View
 */
public class ShapeView extends View implements IShapeDrawable<ShapeView> {

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

    public ShapeView(Context context) {
        this(context, null);
    }

    public ShapeView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ShapeView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ShapeView);
        mShape = typedArray.getInt(R.styleable.ShapeView_shape, DEFAULT_SHAPE);
        mShapeWidth = typedArray.getDimensionPixelSize(R.styleable.ShapeView_shape_width, DEFAULT_SHAPE_WIDTH);
        mShapeHeight = typedArray.getDimensionPixelSize(R.styleable.ShapeView_shape_height, DEFAULT_SHAPE_HEIGHT);

        mSolidColor = typedArray.getColor(R.styleable.ShapeView_shape_solidColor, DEFAULT_SHAPE_SOLID_COLOR);
        mSolidPressedColor = typedArray.getColor(R.styleable.ShapeView_shape_solidPressedColor, mSolidColor);
        mSolidDisabledColor = typedArray.getColor(R.styleable.ShapeView_shape_solidDisabledColor, mSolidColor);
        mSolidFocusedColor = typedArray.getColor(R.styleable.ShapeView_shape_solidFocusedColor, mSolidColor);
        mSolidSelectedColor = typedArray.getColor(R.styleable.ShapeView_shape_solidSelectedColor, mSolidColor);

        int radius = typedArray.getDimensionPixelSize(R.styleable.ShapeView_shape_radius, DEFAULT_SHAPE_RADIUS);
        mTopLeftRadius = typedArray.getDimensionPixelSize(R.styleable.ShapeView_shape_topLeftRadius, radius);
        mTopRightRadius = typedArray.getDimensionPixelSize(R.styleable.ShapeView_shape_topRightRadius, radius);
        mBottomLeftRadius = typedArray.getDimensionPixelSize(R.styleable.ShapeView_shape_bottomLeftRadius, radius);
        mBottomRightRadius = typedArray.getDimensionPixelSize(R.styleable.ShapeView_shape_bottomRightRadius, radius);

        mStartColor = typedArray.getColor(R.styleable.ShapeView_shape_startColor, DEFAULT_SHAPE_START_COLOR);
        mCenterColor = typedArray.getColor(R.styleable.ShapeView_shape_centerColor, DEFAULT_SHAPE_CENTER_COLOR);
        mEndColor = typedArray.getColor(R.styleable.ShapeView_shape_endColor, DEFAULT_SHAPE_END_COLOR);
        mUseLevel = typedArray.getBoolean(R.styleable.ShapeView_shape_useLevel, DEFAULT_SHAPE_USE_LEVEL);
        mAngle = (int) typedArray.getFloat(R.styleable.ShapeView_shape_angle, DEFAULT_SHAPE_ANGLE);
        mGradientType = typedArray.getInt(R.styleable.ShapeView_shape_gradientType, DEFAULT_SHAPE_GRADIENT_TYPE);
        mCenterX = typedArray.getFloat(R.styleable.ShapeView_shape_centerX, DEFAULT_SHAPE_CENTER_X);
        mCenterY = typedArray.getFloat(R.styleable.ShapeView_shape_centerY, DEFAULT_SHAPE_CENTER_Y);
        mGradientRadius = typedArray.getDimensionPixelSize(R.styleable.ShapeView_shape_gradientRadius, radius);

        mStrokeColor = typedArray.getColor(R.styleable.ShapeView_shape_strokeColor, DEFAULT_SHAPE_STROKE_COLOR);
        mStrokePressedColor = typedArray.getColor(R.styleable.ShapeView_shape_strokePressedColor, mStrokeColor);
        mStrokeDisabledColor = typedArray.getColor(R.styleable.ShapeView_shape_strokeDisabledColor, mStrokeColor);
        mStrokeFocusedColor = typedArray.getColor(R.styleable.ShapeView_shape_strokeFocusedColor, mStrokeColor);
        mStrokeSelectedColor = typedArray.getColor(R.styleable.ShapeView_shape_strokeSelectedColor, mStrokeColor);

        mStrokeWidth = typedArray.getDimensionPixelSize(R.styleable.ShapeView_shape_strokeWidth, DEFAULT_SHAPE_STROKE_WIDTH);
        mDashWidth = (int) typedArray.getDimension(R.styleable.ShapeView_shape_dashWidth, DEFAULT_SHAPE_DASH_WIDTH);
        mDashGap = (int) typedArray.getDimension(R.styleable.ShapeView_shape_dashGap, DEFAULT_SHAPE_DASH_GAP);

        typedArray.recycle();

        intoBackground();
    }

    /**
     * {@link IShapeDrawable}
     */

    @Override
    public ShapeView setShape(int shape) {
        mShape = shape;
        return this;
    }

    @Override
    public int getShape() {
        return mShape;
    }

    @Override
    public ShapeView setShapeWidth(int width) {
        mShapeWidth = width;
        return this;
    }

    @Override
    public int getShapeWidth() {
        return mShapeWidth;
    }

    @Override
    public ShapeView setShapeHeight(int height) {
        mShapeHeight = height;
        return this;
    }

    @Override
    public int getShapeHeight() {
        return mShapeHeight;
    }

    @Override
    public ShapeView setSolidColor(int color) {
        mSolidColor = color;
        return this;
    }

    @Override
    public int getSolidColor() {
        return mSolidColor;
    }

    @Override
    public ShapeView setSolidPressedColor(int color) {
        mSolidPressedColor = color;
        return this;
    }

    @Override
    public int getSolidPressedColor() {
        return mSolidPressedColor;
    }

    @Override
    public ShapeView setSolidDisabledColor(int color) {
        mSolidDisabledColor = color;
        return this;
    }

    @Override
    public int getSolidDisabledColor() {
        return mSolidDisabledColor;
    }

    @Override
    public ShapeView setSolidFocusedColor(int color) {
        mSolidFocusedColor = color;
        return this;
    }

    @Override
    public int getSolidFocusedColor() {
        return mSolidFocusedColor;
    }

    @Override
    public ShapeView setSolidSelectedColor(int color) {
        mSolidSelectedColor = color;
        return this;
    }

    @Override
    public int getSolidSelectedColor() {
        return mSolidSelectedColor;
    }

    @Override
    public ShapeView setTopLeftRadius(int radius) {
        mTopLeftRadius = radius;
        return this;
    }

    @Override
    public int getTopLeftRadius() {
        return mTopLeftRadius;
    }

    @Override
    public ShapeView setTopRightRadius(int radius) {
        mTopRightRadius = radius;
        return this;
    }

    @Override
    public int getTopRightRadius() {
        return mTopRightRadius;
    }

    @Override
    public ShapeView setBottomLeftRadius(int radius) {
        mBottomLeftRadius = radius;
        return this;
    }

    @Override
    public int getBottomLeftRadius() {
        return mBottomLeftRadius;
    }

    @Override
    public ShapeView setBottomRightRadius(int radius) {
        mBottomRightRadius = radius;
        return this;
    }

    @Override
    public int getBottomRightRadius() {
        return mBottomRightRadius;
    }

    @Override
    public ShapeView setStartColor(int color) {
        mStartColor = color;
        return this;
    }

    @Override
    public int getStartColor() {
        return mStartColor;
    }

    @Override
    public ShapeView setCenterColor(int color) {
        mCenterColor = color;
        return this;
    }

    @Override
    public int getCenterColor() {
        return mCenterColor;
    }

    @Override
    public ShapeView setEndColor(int color) {
        mEndColor = color;
        return this;
    }

    @Override
    public int getEndColor() {
        return mEndColor;
    }

    @Override
    public ShapeView setUseLevel(boolean useLevel) {
        mUseLevel = useLevel;
        return this;
    }

    @Override
    public boolean isUseLevel() {
        return mUseLevel;
    }

    @Override
    public ShapeView setAngle(int angle) {
        mAngle = angle;
        return this;
    }

    @Override
    public int getAngle() {
        return mAngle;
    }

    @Override
    public ShapeView setGradientType(int type) {
        mGradientType = type;
        return this;
    }

    @Override
    public int getGradientType() {
        return mGradientType;
    }

    @Override
    public ShapeView setCenterX(float x) {
        mCenterX = x;
        return this;
    }

    @Override
    public float getCenterX() {
        return mCenterX;
    }

    @Override
    public ShapeView setCenterY(float y) {
        mCenterY = y;
        return this;
    }

    @Override
    public float getCenterY() {
        return mCenterY;
    }

    @Override
    public ShapeView setGradientRadius(int radius) {
        mGradientRadius = radius;
        return this;
    }

    @Override
    public int getGradientRadius() {
        return mGradientRadius;
    }

    @Override
    public ShapeView setStrokeColor(int color) {
        mStrokeColor = color;
        return this;
    }

    @Override
    public int getStrokeColor() {
        return mStrokeColor;
    }

    @Override
    public ShapeView setStrokePressedColor(int color) {
        mStrokePressedColor = color;
        return this;
    }

    @Override
    public int getStrokePressedColor() {
        return mStrokePressedColor;
    }

    @Override
    public ShapeView setStrokeDisabledColor(int color) {
        mStrokeDisabledColor = color;
        return this;
    }

    @Override
    public int getStrokeDisabledColor() {
        return mStrokeDisabledColor;
    }

    @Override
    public ShapeView setStrokeFocusedColor(int color) {
        mStrokeFocusedColor = color;
        return this;
    }

    @Override
    public int getStrokeFocusedColor() {
        return mStrokeFocusedColor;
    }

    @Override
    public ShapeView setStrokeSelectedColor(int color) {
        mStrokeSelectedColor = color;
        return this;
    }

    @Override
    public int getStrokeSelectedColor() {
        return mStrokeSelectedColor;
    }

    @Override
    public ShapeView setStrokeWidth(int width) {
        mStrokeWidth = width;
        return this;
    }

    @Override
    public int getStrokeWidth() {
        return mStrokeWidth;
    }

    @Override
    public ShapeView setDashWidth(int width) {
        mDashWidth = width;
        return this;
    }

    @Override
    public int getDashWidth() {
        return mDashWidth;
    }

    @Override
    public ShapeView setDashGap(int gap) {
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
}