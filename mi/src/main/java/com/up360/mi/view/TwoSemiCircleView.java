package com.up360.mi.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.widget.TextView;

import com.up360.mi.R;


/**
 * Created by mi.gao on 2018/3/12.
 *
 * 左右两边半圆的View
 *
 */

public class TwoSemiCircleView extends TextView {
    private Paint paint;
    private Paint paint2;
    private int mRadius;
    private int mWidth;
    private int mHeight;
    private int mBeginColor = 0xffffffff;
    private int mEndColor = 0xff000000;

    public TwoSemiCircleView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context, attrs);
    }

    public TwoSemiCircleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public TwoSemiCircleView(Context context) {
        super(context);
        init(context, null);
    }

    private void init(Context context, AttributeSet attrs) {
        if (attrs != null) {
            TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.GradientColor);
            mBeginColor = a.getColor(R.styleable.GradientColor_beginColor, 0xffffffff);
            mEndColor = a.getColor(R.styleable.GradientColor_endColor, 0xff000000);
            a.recycle();
        }
        paint = new Paint();
        paint.setColor(Color.WHITE);
        paint.setAntiAlias(true);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));

        paint2 = new Paint();
        paint2.setXfermode(null);
    }

    @Override
    protected void onDraw(Canvas canvas) {
//        UPUtility.loge("jimwind", "TwoSemiCircleView onDraw");
        super.onDraw(canvas);


    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
//        UPUtility.loge("jimwind", "TwoSemiCircleView onSizeChanged "+w+"/"+h);
        LinearGradient lg = new LinearGradient(0, 0, getWidth(), getHeight(), mBeginColor, mEndColor, Shader.TileMode.CLAMP);
        paint2.setShader(lg);

    }

    @Override
    public void draw(Canvas canvas) {
//        UPUtility.loge("jimwind", "TwoSemiCircleView draw");
        Bitmap bitmap = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas2 = new Canvas(bitmap);
        canvas2.drawRect(0, 0, getWidth(), getHeight(), paint2);
        super.draw(canvas2);

        //左右半圆,就是根据高度的一半作为半径,分别画
        mRadius = getHeight()/2;
        mHeight = getHeight();
        mWidth = getWidth();

        drawLeftUp(canvas2);
        drawRightUp(canvas2);
        drawLeftDown(canvas2);
        drawRightDown(canvas2);
        canvas.drawBitmap(bitmap, 0, 0, paint2);
        bitmap.recycle();
    }

    public void setGradientColor(int beginColor, int endColor){
        mBeginColor = beginColor;
        mEndColor = endColor;
        LinearGradient lg = new LinearGradient(0, 0, getWidth(), getHeight(), mBeginColor, mEndColor, Shader.TileMode.CLAMP);
        paint2.setShader(lg);
        invalidate();
    }

    /**
     *
     * 0, 0                 mRadius, 0
     * 0, mRadius
     * @param canvas
     */
    private void drawLeftUp(Canvas canvas) {
        Path path = new Path();
        path.moveTo(0, mRadius);
        path.lineTo(0, 0);
        path.lineTo(mRadius, 0);
        path.arcTo(new RectF(
                        0,
                        0,
                        mRadius * 2,
                        mRadius * 2),
                -90,
                -90);
        path.close();
        canvas.drawPath(path, paint);
    }

    /**
     *
     * 0, getHeight() - mRadius
     * 0, getHeight()               mRadius, getHeight()
     *
     * @param canvas
     */
    private void drawLeftDown(Canvas canvas) {
        Path path = new Path();
        path.moveTo(0, mHeight - mRadius);
        path.lineTo(0, mHeight);
        path.lineTo(mRadius, mHeight);
        path.arcTo(new RectF(
                        0,
                        mHeight - mRadius * 2,
                        mRadius * 2,
                        mHeight),
                90,
                90);
        path.close();
        canvas.drawPath(path, paint);
    }

    /**
     *                                                       getWidth(), getHeight() - mRadius
     *              getWidth() - mRadius, getHeight()        getWidth(), getHeight()
     * @param canvas
     */
    private void drawRightDown(Canvas canvas) {
        Path path = new Path();
        path.moveTo(mWidth - mRadius, mHeight);
        path.lineTo(mWidth, mHeight);
        path.lineTo(mWidth, mHeight - mRadius);
        path.arcTo(new RectF(
                mWidth - mRadius * 2,
                mHeight - mRadius * 2,
                mWidth,
                mHeight), 0, 90);
        path.close();
        canvas.drawPath(path, paint);
    }

    /**
     *              getWidth() - mRadius, 0     getWidth(), 0
     *                                          getWidth(), mRadius
     * @param canvas
     */
    private void drawRightUp(Canvas canvas) {
        Path path = new Path();
        path.moveTo(mWidth, mRadius);
        path.lineTo(mWidth, 0);
        path.lineTo(mWidth - mRadius, 0);
        path.arcTo(new RectF(
                        mWidth - mRadius * 2,
                        0,
                        mWidth,
                        mRadius * 2),
                -90,
                90);
        path.close();
        canvas.drawPath(path, paint);
    }

}

