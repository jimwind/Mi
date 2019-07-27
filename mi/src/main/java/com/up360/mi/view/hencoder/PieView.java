package com.up360.mi.view.hencoder;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.view.WindowManager;

import com.up360.mi.R;

public class PieView extends View {
    private int widthScreen;

    private int radius = 0;

    public PieView(Context context) {
        this(context, null);
    }
    public PieView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }
    public PieView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        widthScreen = wm.getDefaultDisplay().getWidth();
        init();
    }

    Paint paint = new Paint();
    private void init(){
        radius = widthScreen / 4;

    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(0xfff4f4f4);

        //drawArc() 是使用一个椭圆来描述弧形的。left, top, right, bottom 描述的是这个弧形所在的椭圆；
        // startAngle 是弧形的起始角度（x 轴的正向，即正右的方向，是 0 度的位置；顺时针为正角度，逆时针为负角度），
        // sweepAngle 是弧形划过的角度；
        // useCenter 表示是否连接到圆心，如果不连接到圆心，就是弧形，如果连接到圆心，就是扇形。
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(getResources().getColor(R.color.bg_red));
        //所在的椭圆
        canvas.drawArc(0, 0, radius * 2, radius * 2, -110, 100, true, paint); // 绘制扇形
        paint.setColor(getResources().getColor(R.color.bg_blue));
        canvas.drawArc(0, 0, radius * 2, radius * 2, 20, 140, true, paint); // 绘制弧形
        paint.setStyle(Paint.Style.STROKE); // 画线模式
        canvas.drawArc(0, 0, radius * 2, radius * 2, 180, 60, false, paint); // 绘制不封口的弧线




    }


}
