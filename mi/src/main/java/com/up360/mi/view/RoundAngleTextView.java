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
 * 圆角TextView
 */
public class RoundAngleTextView extends TextView {
    private Paint paint;
    private Paint paint2;
    private int mRadiusH = 5;
    private int mRadiusV = 5;
    private int mBeginColor = 0xffffffff;
    private int mEndColor = 0xffffffff;

    public RoundAngleTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context, attrs);
    }

    public RoundAngleTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public RoundAngleTextView(Context context) {
        super(context);
        init(context, null);
    }

    private void init(Context context, AttributeSet attrs) {
        if (attrs != null) {
            {
                TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.RoundAngle);
                mRadiusH = a.getDimensionPixelSize(R.styleable.RoundAngle_radiusHorizontal, mRadiusH);
                mRadiusV = a.getDimensionPixelSize(R.styleable.RoundAngle_radiusVertical, mRadiusV);
                a.recycle();
            }
            {
                TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.GradientColor);
                mBeginColor = a.getColor(R.styleable.GradientColor_beginColor, 0xffffffff);
                mEndColor = a.getColor(R.styleable.GradientColor_endColor, 0xffffffff);
                a.recycle();
            }
        } else {
            float density = context.getResources().getDisplayMetrics().density;
            mRadiusH = (int) (mRadiusH * density);
            mRadiusV = (int) (mRadiusV * density);
        }

        paint = new Paint();
        paint.setColor(Color.WHITE);
        paint.setAntiAlias(true);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));

        paint2 = new Paint();
        paint2.setXfermode(null);
    }
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        LinearGradient lg = new LinearGradient(0, 0, getWidth(), getHeight(), mBeginColor, mEndColor, Shader.TileMode.CLAMP);
        paint2.setShader(lg);
    }
    @Override
    public void draw(Canvas canvas) {
        if(getWidth() <= 0 || getHeight() <= 0){
            return;
        }
        Bitmap bitmap = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas2 = new Canvas(bitmap);
        canvas2.drawRect(0, 0, getWidth(), getHeight(), paint2);
        super.draw(canvas2);

        drawLeftUp(canvas2);
        drawRightUp(canvas2);
        drawLeftDown(canvas2);
        drawRightDown(canvas2);
        canvas.drawBitmap(bitmap, 0, 0, paint2);
        bitmap.recycle();
    }

    private void drawLeftUp(Canvas canvas) {
        Path path = new Path();
        path.moveTo(0, mRadiusV);
        path.lineTo(0, 0);
        path.lineTo(mRadiusH, 0);
        path.arcTo(new RectF(
                        0,
                        0,
                        mRadiusH * 2,
                        mRadiusV * 2),
                -90,
                -90);
        path.close();
        canvas.drawPath(path, paint);
    }

    private void drawLeftDown(Canvas canvas) {
        Path path = new Path();
        path.moveTo(0, getHeight() - mRadiusV);
        path.lineTo(0, getHeight());
        path.lineTo(mRadiusH, getHeight());
        path.arcTo(new RectF(
                        0,
                        getHeight() - mRadiusV * 2,
                        0 + mRadiusH * 2,
                        getHeight()),
                90,
                90);
        path.close();
        canvas.drawPath(path, paint);
    }

    private void drawRightDown(Canvas canvas) {
        Path path = new Path();
        path.moveTo(getWidth() - mRadiusH, getHeight());
        path.lineTo(getWidth(), getHeight());
        path.lineTo(getWidth(), getHeight() - mRadiusV);
        path.arcTo(new RectF(
                getWidth() - mRadiusH * 2,
                getHeight() - mRadiusV * 2,
                getWidth(),
                getHeight()), 0, 90);
        path.close();
        canvas.drawPath(path, paint);
    }

    private void drawRightUp(Canvas canvas) {
        Path path = new Path();
        path.moveTo(getWidth(), mRadiusV);
        path.lineTo(getWidth(), 0);
        path.lineTo(getWidth() - mRadiusH, 0);
        path.arcTo(new RectF(
                        getWidth() - mRadiusH * 2,
                        0,
                        getWidth(),
                        0 + mRadiusV * 2),
                -90,
                90);
        path.close();
        canvas.drawPath(path, paint);
    }

    public void setRadius(int radiusH,int radiusV){
        this.mRadiusH = radiusH;
        this.mRadiusV = radiusV;
    }

    public void setGradientColor(int beginColor, int endColor){
        mBeginColor = beginColor;
        mEndColor = endColor;
        LinearGradient lg = new LinearGradient(0, 0, getWidth(), getHeight(), mBeginColor, mEndColor, Shader.TileMode.CLAMP);
        paint2.setShader(lg);
        invalidate();
    }
}
