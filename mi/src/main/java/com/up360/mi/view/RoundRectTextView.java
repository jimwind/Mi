package com.up360.mi.view;


import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.widget.TextView;

import com.up360.mi.R;

public class RoundRectTextView extends TextView {
    private Paint mPaint;
    private float rx = 0f;
    private float ry = 0f;
    public RoundRectTextView(Context context) {
        this(context, null);
    }
    public RoundRectTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }
    public RoundRectTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        if (attrs != null) {
            TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.RoundRect);
            rx = a.getDimensionPixelSize(R.styleable.RoundRect_rx, 0);
            ry = a.getDimensionPixelSize(R.styleable.RoundRect_ry, 0);
            a.recycle();
        }
        mPaint = new Paint();
        mPaint.setColor(0xff1e90ff);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        canvas.drawRoundRect(0, 0, getWidth(), getHeight(), rx, ry, mPaint);

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.olympics_math);
        Shader shader = new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        mPaint.setShader(shader);
        canvas.drawCircle(300, 400, 200, mPaint);
    }
}
